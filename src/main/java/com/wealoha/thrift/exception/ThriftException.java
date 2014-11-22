package com.wealoha.thrift.exception;

/**
 * 
 * @author javamonk
 * @createTime 2014年11月22日 下午1:28:23
 */
public class ThriftException extends RuntimeException {

    private static final long serialVersionUID = 2580948893299977073L;

    public ThriftException() {
    }

    public ThriftException(String message, Throwable cause) {
        super(message, cause);
    }

}
