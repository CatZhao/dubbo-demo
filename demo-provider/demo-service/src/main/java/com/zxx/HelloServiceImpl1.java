package com.zxx;

import org.apache.dubbo.config.annotation.DubboService;

import java.util.concurrent.CompletableFuture;

@DubboService(group = "group1",version = "v1.0")
public class HelloServiceImpl1 implements HelloService{
    @Override
    public String hello(String name) {
        return "hello 1:";
    }

    @Override
    public CompletableFuture<String> asyncHello(String name) {
        return CompletableFuture.supplyAsync(()->{
            return "hello async1:";
        });
    }

    @Override
    public String rpcContextHello(String name) {
        return "todo";
    }
}
