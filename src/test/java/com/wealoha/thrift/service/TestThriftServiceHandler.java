package com.wealoha.thrift.service;

import org.apache.thrift.TException;

import com.wealoha.thrift.service.TestThriftService.Iface;

/**
 * 
 * @author javamonk
 * @createTime 2014年7月4日 下午4:59:26
 */
public class TestThriftServiceHandler implements Iface {

    @Override
    public String echo(String message) throws TException {
        return message;
    }

}
