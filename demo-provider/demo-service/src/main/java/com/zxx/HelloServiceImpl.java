package com.zxx;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@DubboService
//@Service
public class HelloServiceImpl implements HelloService{
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    @Override
    public String hello(String name) {
        return "hello " + name;
    }

    @Override
    public CompletableFuture<String> asyncHello(String name) {
       return CompletableFuture.supplyAsync(() -> {
//           try {
//               Thread.sleep(1000);
//           } catch (InterruptedException e) {
//               throw new RuntimeException(e);
//           }
           return "hello async " + name;
        },executorService);
    }

    @SentinelResource("helloService")
    @Override
    public String rpcContextHello(String name) {
        final AsyncContext asyncContext = RpcContext.startAsync();
        executorService.submit(()->{
            asyncContext.signalContextSwitch();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            asyncContext.write("hello async RpcContext" + name);
        });
        System.out.println("provider over....");
        return null;
    }
}
