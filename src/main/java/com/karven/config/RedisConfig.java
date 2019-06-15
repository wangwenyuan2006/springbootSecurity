package com.karven.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

//@Configuration
public class RedisConfig {

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxIdle(120);
        config.setMinIdle(10);
        config.setMaxTotal(100);
        return config;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig config){
        JedisConnectionFactory factory=new JedisConnectionFactory();
        factory.setPoolConfig(config);
        factory.setHostName("192.168.89.128");
        factory.setPort(6379);
        return  factory;
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(JedisConnectionFactory factory){
        RedisTemplate<String,Object> template=new RedisTemplate<>();
        factory.setHostName("192.168.89.128");
        factory.setPort(6379);
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }
}
