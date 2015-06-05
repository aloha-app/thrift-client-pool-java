package com.wealoha.thrift.exception;

/**
 * 
 * @author javamonk
 * @createTime 2014年11月22日 下午4:01:39
 */
public class ConnectionFailException extends ThriftException {

    private static final long serialVersionUID = 4457437871618462115L;

    public ConnectionFailException() {
        super();
    }

    public ConnectionFailException(String message) {
        super(message);
    }

    public ConnectionFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
