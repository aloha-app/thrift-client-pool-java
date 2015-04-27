package com.wealoha.thrift;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author javamonk
 * @createTime 2014年11月22日 下午8:40:26
 */
@Slf4j
public abstract class ThriftUtil {

    /**
     * close internal transport
     * 
     * @param client
     */
    public static void closeClient(TServiceClient client) {
        if (client == null) {
            return;
        }
        try {
            TProtocol proto = client.getInputProtocol();
            if (proto != null) {
                proto.getTransport().close();
            }
        } catch (Throwable e) {
            log.warn("close input transport fail", e);
        }
        try {
            TProtocol proto = client.getOutputProtocol();
            if (proto != null) {
                proto.getTransport().close();
            }
        } catch (Throwable e) {
            log.warn("close output transport fail", e);
        }

    }
}
