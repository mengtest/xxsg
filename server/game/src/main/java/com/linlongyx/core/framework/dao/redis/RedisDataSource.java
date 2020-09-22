package com.linlongyx.core.framework.dao.redis;

import redis.clients.jedis.Jedis;

public interface RedisDataSource {
  Jedis getRedisClient();
}


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\redis\RedisDataSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */