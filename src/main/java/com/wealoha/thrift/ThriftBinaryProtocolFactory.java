package com.wealoha.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TTransport;

/**
 * ThriftProtocolFactory using TBinaryProtocol with TFramedTransport
 * This is also the default protocol factory
 * 
 */
public class ThriftBinaryProtocolFactory implements ThriftProtocolFactory {

    @Override
    public TProtocol makeProtocol(TTransport transport) {
        return new TBinaryProtocol(new TFramedTransport(transport));
    }
}
