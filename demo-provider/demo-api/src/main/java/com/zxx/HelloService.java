package com.zxx;

import java.util.concurrent.CompletableFuture;

public interface HelloService {
    public String hello(String name);

    public CompletableFuture<String> asyncHello(String name);

    public String rpcContextHello(String name);
}
