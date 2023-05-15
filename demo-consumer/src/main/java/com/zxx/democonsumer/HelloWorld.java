package com.zxx.democonsumer;

import com.zxx.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class HelloWorld {

    @DubboReference
    private HelloService helloService;


    public String hello(String name) throws ExecutionException, InterruptedException {
        return helloService.rpcContextHello(name);
    }
}
