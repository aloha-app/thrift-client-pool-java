package com.wealoha.thrift;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Pool configurations all by passing to commons-pool2, see
 * {@link GenericObjectPoolConfig} for details.
 * 
 * @author javamonk
 * @createTime 2014年11月22日 下午2:25:30
 */
public class PoolConfig extends GenericObjectPoolConfig {

    private int timeout = 0;

    private boolean failover = false;

    /**
     * get default connection socket timeout (default 0, means not timeout)
     * 
     * @return
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * set default connection socket timeout
     * 
     * @param timeout
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * get connect to next service if one service fail(default false)
     * 
     * @return
     */
    public boolean isFailover() {
        return failover;
    }

    /**
     * set connect to next service if one service fail
     * 
     * @param failover
     */
    public void setFailover(boolean failover) {
        this.failover = failover;
    }
}
