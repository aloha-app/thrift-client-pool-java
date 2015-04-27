package com.wealoha.thrift;

import lombok.Value;

/**
 * @author javamonk
 * @createTime 2014年11月22日 下午2:30:39
 */
@Value
public class ServiceInfo {

    private final String host;

    private final int port;
}
