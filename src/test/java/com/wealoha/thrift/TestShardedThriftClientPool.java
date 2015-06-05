package com.wealoha.thrift;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wealoha.thrift.service.TestThriftService.Client;

/**
 * 
 * @author javamonk
 * @createTime 2015年6月5日 下午2:32:15
 */
public class TestShardedThriftClientPool {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public void testGetShardedPool() {
        PoolConfig config = new PoolConfig();
        config.setFailover(true);
        config.setTimeout(1000);

        List<ServiceInfo> serviceList = Arrays.asList( //
                new ServiceInfo("127.0.0.1", 9090), //
                new ServiceInfo("127.0.0.1", 9091), //
                new ServiceInfo("127.0.0.1", 9092));
        ShardedThriftClientPool<Integer, Client> shardedPool = new ShardedThriftClientPool<>(
                serviceList, //
                key -> key, //
                servers -> new ThriftClientPool<>(servers, Client::new, config));

        Integer key = 10;
        ThriftClientPool<Client> pool = shardedPool.getShardedPool(key);
        Assert.assertEquals(Arrays.asList(new ServiceInfo("127.0.0.1", 9091)), pool.getServices());
    }

    @Test
    public void testComplexGetShardedPool() {
        PoolConfig config = new PoolConfig();
        config.setFailover(true);
        config.setTimeout(1000);

        List<ServiceInfo> serviceList = Arrays.asList( //
                new ServiceInfo("127.0.0.1", 9090), //
                new ServiceInfo("127.0.0.1", 9091), //
                new ServiceInfo("127.0.0.1", 9092), //
                new ServiceInfo("127.0.0.1", 9093), //
                new ServiceInfo("127.0.0.1", 9094));

        ShardedThriftClientPool<Integer, Client> shardedPool = new ShardedThriftClientPool<>(
                serviceList, //
                key -> key, //
                servers -> {
                    return Arrays.asList( //
                            Arrays.asList(servers.get(0), servers.get(1)), //
                            Arrays.asList(servers.get(2), servers.get(3)), //
                            Arrays.asList(servers.get(4)));
                }, //
                servers -> new ThriftClientPool<>(servers, Client::new, config));

        Integer key = 10;
        ThriftClientPool<Client> pool = shardedPool.getShardedPool(key);
        Assert.assertEquals(Arrays.asList(new ServiceInfo("127.0.0.1", 9092), //
                new ServiceInfo("127.0.0.1", 9093)), //
                pool.getServices());

        key = 8;
        pool = shardedPool.getShardedPool(key);
        Assert.assertEquals(Arrays.asList(new ServiceInfo("127.0.0.1", 9094)), //
                pool.getServices());
    }
}
