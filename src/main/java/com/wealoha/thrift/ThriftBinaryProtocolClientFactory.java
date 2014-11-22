package com.wealoha.thrift;

import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;

/**
 * TServiceClientFactory using TBinaryProtocol
 * 
 * @author javamonk
 * @createTime 2014年11月22日 下午2:53:32
 */
public abstract class ThriftBinaryProtocolClientFactory implements ThriftClientFactory {

    @Override
    public TServiceClient createClient(TTransport transport) {
        return makeClient(new TBinaryProtocol(transport));
    }

    /**
     * return a new client using protocol<br/>
     * 
     * <code>
     * <pre>
     *     public TServiceClient makeClient(TProtocol protocol) {
     *         return new Client(protocol);
     *     }
     * </pre>
     * </code>
     * 
     * @param protocol TBinaryProtocol
     */
    public abstract TServiceClient makeClient(TProtocol protocol);

}
