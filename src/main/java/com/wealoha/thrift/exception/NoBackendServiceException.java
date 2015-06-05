package com.wealoha.thrift.exception;

/**
 * No backend service
 * 
 * @author javamonk
 * @createTime 2014年11月22日 下午3:39:30
 */
public class NoBackendServiceException extends ConnectionFailException {

    private static final long serialVersionUID = 8966434958841745191L;

    public NoBackendServiceException() {
        super();
    }

    public NoBackendServiceException(String message) {
        super(message);
    }

}
