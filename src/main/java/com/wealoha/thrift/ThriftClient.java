package com.wealoha.thrift;

import java.io.Closeable;

import org.apache.commons.pool2.ObjectPool;
import org.apache.thrift.TServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The thrift client which hold the connection to backend server.<br/>
 * 
 * ThriftClient is not thread-safe, you must obtain separately from
 * {@link ThriftClientPool} for each thread.
 * 
 * @author javamonk
 * @createTime 2014年7月4日 下午3:50:51
 */
public class ThriftClient<T extends TServiceClient> implements Closeable {

    private static final Logger logger = LoggerFactory.getLogger(ThriftClient.class);

    private final T client;

    private final ObjectPool<ThriftClient<T>> pool;

    private final ServiceInfo serviceInfo;

    private boolean finish;

    public ThriftClient(T client, ObjectPool<ThriftClient<T>> pool,
            ServiceInfo serviceInfo) {
        super();
        this.client = client;
        this.pool = pool;
        this.serviceInfo = serviceInfo;
    }

    /**
     * get backend service which this client current connect to
     * 
     * @return
     */
    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    /**
     * Retrieve the IFace
     * 
     * @return
     */
    public T iFace() {
        return client;
    }

    @Override
    public void close() {
        try {
            if (finish) {
                logger.info("return object to pool: " + this);
                finish = false;
                pool.returnObject(this);
            } else {
                logger.warn("not return object cause not finish {}", client);
                closeClient();
                pool.invalidateObject(this);
            }
        } catch (Exception e) {
            logger.warn("return object fail, close", e);
            closeClient();
        }
    }

    void closeClient() {
        logger.debug("close client {}", this);
        ThriftUtil.closeClient(this.client);
    }

    /**
     * client should return to pool
     * 
     */
    public void finish() {
        this.finish = true;
    }

    void setFinish(boolean finish) {
        this.finish = finish;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        closeClient();
    }
}
