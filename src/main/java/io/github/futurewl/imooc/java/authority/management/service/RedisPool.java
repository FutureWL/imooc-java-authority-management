package io.github.futurewl.imooc.java.authority.management.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;

/**
 * 功能描述：Redis 池
 *
 * @author weilai create by 2019-04-24:21:11
 * @version 1.0
 */
@Slf4j
@Service
public class RedisPool {

    @Resource
    private ShardedJedisPool shardedJedisPool;

    public ShardedJedis instance() {
        return shardedJedisPool.getResource();
    }

    public void safeClose(ShardedJedis shardedJedis) {
        try {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        } catch (Exception e) {
            log.error("return redis resource exception", e);
        }
    }

}
