package com.wealoha.thrift;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wealoha.thrift.service.TestThriftService;
import com.wealoha.thrift.service.TestThriftService.Client;
import com.wealoha.thrift.service.TestThriftService.Iface;

/**
 * 
 * @author javamonk
 * @createTime 2014年7月4日 下午4:51:18
 */
public class TestThriftClientPool {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testEcho() throws InterruptedException {
        List<ServiceInfo> serverList = Arrays.asList( //
                new ServiceInfo("127.0.0.1", 9092), //
                new ServiceInfo("127.0.0.1", 9091), //
                new ServiceInfo("127.0.0.1", 9090));

        PoolConfig config = new PoolConfig();
        config.setFailover(true);
        config.setTimeout(10);
        ThriftClientPool<TestThriftService.Client> pool = new ThriftClientPool<>(serverList,
                e -> new Client(new TBinaryProtocol(new TFramedTransport(e))), config);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            int counter = i;
            executorService.submit(new Runnable() {

                @Override
                public void run() {
                    try {
                        try (ThriftClient<Client> thriftClient = pool.getClient()) {

                            Iface iFace = thriftClient.iFace();
                            String response = iFace.echo("Hello " + counter + "!");
                            logger.info("get response: {}", response);
                            thriftClient.finish();
                        } catch (TException e) {
                            logger.error("thrift fail", e);
                        }
                    } catch (Throwable e) {
                        logger.error("get client fail", e);
                    }
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
