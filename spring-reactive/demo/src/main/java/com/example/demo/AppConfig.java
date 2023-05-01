package com.example.demo;

import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadFactory;

@Configuration
public class AppConfig {
    @Bean
    public ReactiveWebServerFactory reactiveWebServerFactory() {

        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(20, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new ReactorThreadFactory
            }
        });
        var factory = new NettyReactiveWebServerFactory();
//        factory.addServerCustomizers(builder -> builder.runOn(nioEventLoopGroup));
        factory.addServerCustomizers(builder -> builder.);
        return factory;
    }
}
