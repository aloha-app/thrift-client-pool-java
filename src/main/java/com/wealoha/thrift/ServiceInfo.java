package com.wealoha.thrift;

/**
 * 
 * @author javamonk
 * @createTime 2014年11月22日 下午2:30:39
 */
public class ServiceInfo {

    private final String host;

    private final int port;

    public ServiceInfo(String host, int port) {
        super();
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((host == null) ? 0 : host.hashCode());
        result = prime * result + port;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ServiceInfo other = (ServiceInfo) obj;
        if (host == null) {
            if (other.host != null) return false;
        } else if (!host.equals(other.host)) return false;
        if (port != other.port) return false;
        return true;
    }

    @Override
    public String toString() {
        return "ServiceInfo [host=" + host + ", port=" + port + "]";
    }
}
