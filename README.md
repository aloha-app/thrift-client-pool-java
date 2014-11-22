thrift-client-pool-java
=======================

A Thrift Client pool for Java

* TServiceClient pool
* Multi Backend Servers support
* ~~Backend Servers replace on the fly~~ (unimplemented)
* ~~Backend route by hash or any other algorithm~~ (unimplemented)
* java.io.Closeable resources (for try with resources)
* Ease of use
* jdk 1.8 only (1.7 is okay)


## Usage
 
<pre><code>
    // pool config
    PoolConfig config = new PoolConfig();
    config.setFailover(true);
    config.setTimeout(10);
    ThriftClientPool<TestThriftService.Client> pool = new ThriftClientPool<>(
        serverList,
        e -> new Client(new TBinaryProtocol(e)),
        config);

    // or pre jdk1.8
    ThriftClientPool<TestThriftService.Client> pool = new ThriftClientPool<>(serverList,
        new ThriftClientFactory() {
            
            @Override
            public TServiceClient createClient(TTransport transport) {
                return new Client(new TBinaryProtocol(transport));
            }
        }, config);
    
    // call thrift
    try (ThriftClient<Client> thriftClient = pool.getClient()) {
        Iface iFace = thriftClient.iFace();
        String response = iFace.echo("Hello " + counter + "!");
        thriftClient.finish();
        logger.info("get response: {}", response);
    } catch (TException e) {
        logger.error("call echo fail", e);
    }
</pre></code>