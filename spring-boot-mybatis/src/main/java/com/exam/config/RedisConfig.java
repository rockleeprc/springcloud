package com.exam.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

/**
 * Created by Administrator on 2018/7/8.
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
//    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
//        redisConnectionFactory.setHostName(env.getProperty("spring.redis.host"));
//        redisConnectionFactory.setPort(Integer.parseInt(env.getProperty("spring.redis.port")));
//        return redisConnectionFactory;
//    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
//
//    @Bean
//    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        cacheManager.setDefaultExpiration(600);
//        return cacheManager;
//    }
//
//    public CacheErrorHandler errorHandler() {
//        return new CacheErrorHandler() {
//            @Override
//            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
//                logger.warn("handleCacheGetError in redis: {}", exception.getMessage());
//            }
//
//            @Override
//            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
//                logger.warn("handleCachePutError in redis: {}", exception.getMessage());
//            }
//
//            @Override
//            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
//                logger.warn("handleCacheEvictError in redis: {}", exception.getMessage());
//            }
//
//            @Override
//            public void handleCacheClearError(RuntimeException exception, Cache cache) {
//                logger.warn("handleCacheClearError in redis: {}", exception.getMessage());
//            }
//        };
//    }
}
