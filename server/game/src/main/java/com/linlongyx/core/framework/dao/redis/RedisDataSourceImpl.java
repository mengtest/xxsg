/*    */ package com.linlongyx.core.framework.dao.redis;
/*    */ 
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import redis.clients.jedis.Jedis;
/*    */ import redis.clients.jedis.JedisPool;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RedisDataSourceImpl
/*    */   implements RedisDataSource
/*    */ {
/* 15 */   private static final Logger LOG = LoggerFactory.getLogger(RedisDataSourceImpl.class);
/*    */   
/*    */   private JedisPool jedisPool;
/*    */ 
/*    */   
/*    */   public JedisPool getJedisPool() {
/* 21 */     return this.jedisPool;
/*    */   }
/*    */   
/*    */   public void setJedisPool(JedisPool jedisPool) {
/* 25 */     this.jedisPool = jedisPool;
/*    */   }
/*    */ 
/*    */   
/*    */   public Jedis getRedisClient() {
/*    */     try {
/* 31 */       Jedis jedis = this.jedisPool.getResource();
/* 32 */       return jedis;
/* 33 */     } catch (Exception e) {
/* 34 */       LOG.error("getRedisClent error", e);
/*    */       
/* 36 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\redis\RedisDataSourceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */