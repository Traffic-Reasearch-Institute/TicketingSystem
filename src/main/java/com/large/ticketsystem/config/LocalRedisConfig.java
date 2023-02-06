package com.large.ticketsystem.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Slf4j
//이 config 클래스가 로컬에서만 적용되도록 지정
@Profile("local")
@Configuration
public class LocalRedisConfig {

    //application.properties에서 포트 값 가져와서 redisPort에 저장
    @Value("${spring.redis.port}")
    private int redisPort;
    private RedisServer redisServer;

    //객체가 생성될 때 실행되는 메서드
    @PostConstruct
    public void redisServer() throws IOException {
        redisServer = new RedisServer(redisPort);
        redisServer.start();
    }

    //객체가 삭제될 때 실행되는 메서드
    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
