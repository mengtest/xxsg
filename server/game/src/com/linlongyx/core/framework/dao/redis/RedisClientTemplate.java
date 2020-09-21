/*      */ package com.linlongyx.core.framework.dao.redis;
/*      */ 
/*      */ import java.util.Collection;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ import redis.clients.jedis.BinaryClient;
/*      */ import redis.clients.jedis.Jedis;
/*      */ import redis.clients.jedis.SortingParams;
/*      */ import redis.clients.jedis.Tuple;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RedisClientTemplate
/*      */ {
/*   22 */   private static final Logger LOG = LoggerFactory.getLogger(RedisClientTemplate.class);
/*      */ 
/*      */   
/*      */   private RedisDataSource redisDataSource;
/*      */ 
/*      */   
/*      */   public RedisDataSource getRedisDataSource() {
/*   29 */     return this.redisDataSource;
/*      */   }
/*      */   
/*      */   public void setRedisDataSource(RedisDataSource redisDataSource) {
/*   33 */     this.redisDataSource = redisDataSource;
/*      */   }
/*      */   
/*      */   public void disconnect() {
/*   37 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*   38 */     jedis.disconnect();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String set(String key, String value) {
/*   50 */     String result = null;
/*      */     
/*   52 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*   53 */     if (null == jedis) {
/*   54 */       return result;
/*      */     }
/*   56 */     jedis.pipelined();
/*   57 */     boolean broken = false;
/*      */     try {
/*   59 */       result = jedis.set(key, value);
/*   60 */     } catch (Exception e) {
/*   61 */       LOG.error(e.getMessage(), e);
/*   62 */       broken = true;
/*      */     } finally {
/*      */       
/*   65 */       jedis.close();
/*      */     } 
/*   67 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String get(String key) {
/*   76 */     String result = null;
/*   77 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*   78 */     if (null == jedis) {
/*   79 */       return result;
/*      */     }
/*      */     
/*      */     try {
/*   83 */       result = jedis.get(key);
/*      */     }
/*   85 */     catch (Exception e) {
/*   86 */       LOG.error(e.getMessage(), e);
/*      */     } finally {
/*      */       
/*   89 */       jedis.close();
/*      */     } 
/*   91 */     return result;
/*      */   }
/*      */   
/*      */   public Boolean exists(String key) {
/*   95 */     Boolean result = Boolean.valueOf(false);
/*   96 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*   97 */     if (null == jedis) {
/*   98 */       return result;
/*      */     }
/*  100 */     boolean broken = false;
/*      */     try {
/*  102 */       result = jedis.exists(key);
/*  103 */     } catch (Exception e) {
/*  104 */       LOG.error(e.getMessage(), e);
/*  105 */       broken = true;
/*      */     } finally {
/*      */       
/*  108 */       jedis.close();
/*      */     } 
/*  110 */     return result;
/*      */   }
/*      */   
/*      */   public String type(String key) {
/*  114 */     String result = null;
/*  115 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  116 */     if (null == jedis) {
/*  117 */       return result;
/*      */     }
/*  119 */     boolean broken = false;
/*      */     try {
/*  121 */       result = jedis.type(key);
/*  122 */     } catch (Exception e) {
/*  123 */       LOG.error(e.getMessage(), e);
/*      */     } finally {
/*      */       
/*  126 */       jedis.close();
/*      */     } 
/*      */     
/*  129 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Long expire(String key, int seconds) {
/*  139 */     Long result = null;
/*  140 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  141 */     if (null == jedis) {
/*  142 */       return result;
/*      */     }
/*      */     try {
/*  145 */       result = jedis.expire(key, seconds);
/*      */     }
/*  147 */     catch (Exception e) {
/*  148 */       LOG.error(e.getMessage(), e);
/*      */     } finally {
/*      */       
/*  151 */       jedis.close();
/*      */     } 
/*      */     
/*  154 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Long expireAt(String key, long unixTime) {
/*  165 */     Long result = null;
/*  166 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  167 */     if (null == jedis) {
/*  168 */       return result;
/*      */     }
/*  170 */     boolean broken = false;
/*      */     try {
/*  172 */       result = jedis.expireAt(key, unixTime);
/*      */     }
/*  174 */     catch (Exception e) {
/*  175 */       LOG.error(e.getMessage(), e);
/*  176 */       broken = true;
/*      */     } finally {
/*      */       
/*  179 */       jedis.close();
/*      */     } 
/*      */     
/*  182 */     return result;
/*      */   }
/*      */   
/*      */   public Long ttl(String key) {
/*  186 */     Long result = null;
/*  187 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  188 */     if (null == jedis) {
/*  189 */       return result;
/*      */     }
/*  191 */     boolean broken = false;
/*      */     try {
/*  193 */       result = jedis.ttl(key);
/*      */     }
/*  195 */     catch (Exception e) {
/*  196 */       LOG.error(e.getMessage(), e);
/*  197 */       broken = true;
/*      */     } finally {
/*      */       
/*  200 */       jedis.close();
/*      */     } 
/*  202 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setbit(String key, long offset, boolean value) {
/*  207 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  208 */     boolean result = false;
/*  209 */     if (jedis == null) {
/*  210 */       return result;
/*      */     }
/*  212 */     boolean broken = false;
/*      */     try {
/*  214 */       result = jedis.setbit(key, offset, value).booleanValue();
/*  215 */     } catch (Exception e) {
/*  216 */       LOG.error(e.getMessage(), e);
/*  217 */       broken = true;
/*      */     } finally {
/*      */       
/*  220 */       jedis.close();
/*      */     } 
/*  222 */     return result;
/*      */   }
/*      */   
/*      */   public boolean getbit(String key, long offset) {
/*  226 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  227 */     boolean result = false;
/*  228 */     if (jedis == null) {
/*  229 */       return result;
/*      */     }
/*  231 */     boolean broken = false;
/*      */     
/*      */     try {
/*  234 */       result = jedis.getbit(key, offset).booleanValue();
/*  235 */     } catch (Exception e) {
/*  236 */       LOG.error(e.getMessage(), e);
/*  237 */       broken = true;
/*      */     } finally {
/*      */       
/*  240 */       jedis.close();
/*      */     } 
/*  242 */     return result;
/*      */   }
/*      */   
/*      */   public long setrange(String key, long offset, String value) {
/*  246 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  247 */     long result = 0L;
/*  248 */     if (jedis == null) {
/*  249 */       return result;
/*      */     }
/*  251 */     boolean broken = false;
/*      */     try {
/*  253 */       result = jedis.setrange(key, offset, value).longValue();
/*  254 */     } catch (Exception e) {
/*  255 */       LOG.error(e.getMessage(), e);
/*  256 */       broken = true;
/*      */     } finally {
/*      */       
/*  259 */       jedis.close();
/*      */     } 
/*  261 */     return result;
/*      */   }
/*      */   
/*      */   public String getrange(String key, long startOffset, long endOffset) {
/*  265 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  266 */     String result = null;
/*  267 */     if (jedis == null) {
/*  268 */       return result;
/*      */     }
/*  270 */     boolean broken = false;
/*      */     try {
/*  272 */       result = jedis.getrange(key, startOffset, endOffset);
/*      */     }
/*  274 */     catch (Exception e) {
/*  275 */       LOG.error(e.getMessage(), e);
/*  276 */       broken = true;
/*      */     } finally {
/*      */       
/*  279 */       jedis.close();
/*      */     } 
/*  281 */     return result;
/*      */   }
/*      */   
/*      */   public String getSet(String key, String value) {
/*  285 */     String result = null;
/*  286 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  287 */     if (jedis == null) {
/*  288 */       return result;
/*      */     }
/*  290 */     boolean broken = false;
/*      */     try {
/*  292 */       result = jedis.getSet(key, value);
/*  293 */     } catch (Exception e) {
/*  294 */       LOG.error(e.getMessage(), e);
/*  295 */       broken = true;
/*      */     } finally {
/*      */       
/*  298 */       jedis.close();
/*      */     } 
/*  300 */     return result;
/*      */   }
/*      */   
/*      */   public Long setnx(String key, String value) {
/*  304 */     Long result = null;
/*  305 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  306 */     if (jedis == null) {
/*  307 */       return result;
/*      */     }
/*  309 */     boolean broken = false;
/*      */     try {
/*  311 */       result = jedis.setnx(key, value);
/*  312 */     } catch (Exception e) {
/*  313 */       LOG.error(e.getMessage(), e);
/*  314 */       broken = true;
/*      */     } finally {
/*      */       
/*  317 */       jedis.close();
/*      */     } 
/*  319 */     return result;
/*      */   }
/*      */   
/*      */   public String setex(String key, int seconds, String value) {
/*  323 */     String result = null;
/*  324 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  325 */     if (jedis == null) {
/*  326 */       return result;
/*      */     }
/*  328 */     boolean broken = false;
/*      */     try {
/*  330 */       result = jedis.setex(key, seconds, value);
/*      */     }
/*  332 */     catch (Exception e) {
/*  333 */       LOG.error(e.getMessage(), e);
/*  334 */       broken = true;
/*      */     } finally {
/*      */       
/*  337 */       jedis.close();
/*      */     } 
/*  339 */     return result;
/*      */   }
/*      */   
/*      */   public Long decrBy(String key, long integer) {
/*  343 */     Long result = null;
/*  344 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  345 */     if (jedis == null) {
/*  346 */       return result;
/*      */     }
/*  348 */     boolean broken = false;
/*      */     try {
/*  350 */       result = jedis.decrBy(key, integer);
/*      */     }
/*  352 */     catch (Exception e) {
/*  353 */       LOG.error(e.getMessage(), e);
/*  354 */       broken = true;
/*      */     } finally {
/*      */       
/*  357 */       jedis.close();
/*      */     } 
/*  359 */     return result;
/*      */   }
/*      */   
/*      */   public Long decr(String key) {
/*  363 */     Long result = null;
/*  364 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  365 */     if (jedis == null) {
/*  366 */       return result;
/*      */     }
/*  368 */     boolean broken = false;
/*      */     try {
/*  370 */       result = jedis.decr(key);
/*      */     }
/*  372 */     catch (Exception e) {
/*  373 */       LOG.error(e.getMessage(), e);
/*  374 */       broken = true;
/*      */     } finally {
/*      */       
/*  377 */       jedis.close();
/*      */     } 
/*  379 */     return result;
/*      */   }
/*      */   
/*      */   public Long incrBy(String key, long integer) {
/*  383 */     Long result = null;
/*  384 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  385 */     if (jedis == null) {
/*  386 */       return result;
/*      */     }
/*  388 */     boolean broken = false;
/*      */     try {
/*  390 */       result = jedis.incrBy(key, integer);
/*      */     }
/*  392 */     catch (Exception e) {
/*  393 */       LOG.error(e.getMessage(), e);
/*  394 */       broken = true;
/*      */     } finally {
/*      */       
/*  397 */       jedis.close();
/*      */     } 
/*  399 */     return result;
/*      */   }
/*      */   
/*      */   public Long incr(String key) {
/*  403 */     Long result = null;
/*  404 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  405 */     if (jedis == null) {
/*  406 */       return result;
/*      */     }
/*  408 */     boolean broken = false;
/*      */     try {
/*  410 */       result = jedis.incr(key);
/*      */     }
/*  412 */     catch (Exception e) {
/*  413 */       LOG.error(e.getMessage(), e);
/*  414 */       broken = true;
/*      */     } finally {
/*      */       
/*  417 */       jedis.close();
/*      */     } 
/*  419 */     return result;
/*      */   }
/*      */   
/*      */   public Long append(String key, String value) {
/*  423 */     Long result = null;
/*  424 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  425 */     if (jedis == null) {
/*  426 */       return result;
/*      */     }
/*  428 */     boolean broken = false;
/*      */     try {
/*  430 */       result = jedis.append(key, value);
/*      */     }
/*  432 */     catch (Exception e) {
/*  433 */       LOG.error(e.getMessage(), e);
/*  434 */       broken = true;
/*      */     } finally {
/*      */       
/*  437 */       jedis.close();
/*      */     } 
/*  439 */     return result;
/*      */   }
/*      */   
/*      */   public String substr(String key, int start, int end) {
/*  443 */     String result = null;
/*  444 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  445 */     if (jedis == null) {
/*  446 */       return result;
/*      */     }
/*  448 */     boolean broken = false;
/*      */     try {
/*  450 */       result = jedis.substr(key, start, end);
/*      */     }
/*  452 */     catch (Exception e) {
/*  453 */       LOG.error(e.getMessage(), e);
/*  454 */       broken = true;
/*      */     } finally {
/*      */       
/*  457 */       jedis.close();
/*      */     } 
/*  459 */     return result;
/*      */   }
/*      */   
/*      */   public Long hset(String key, String field, String value) {
/*  463 */     Long result = null;
/*  464 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  465 */     if (jedis == null) {
/*  466 */       return result;
/*      */     }
/*  468 */     boolean broken = false;
/*      */     try {
/*  470 */       result = jedis.hset(key, field, value);
/*      */     }
/*  472 */     catch (Exception e) {
/*  473 */       LOG.error(e.getMessage(), e);
/*  474 */       broken = true;
/*      */     } finally {
/*      */       
/*  477 */       jedis.close();
/*      */     } 
/*  479 */     return result;
/*      */   }
/*      */   
/*      */   public String hget(String key, String field) {
/*  483 */     String result = null;
/*  484 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  485 */     if (jedis == null) {
/*  486 */       return result;
/*      */     }
/*  488 */     boolean broken = false;
/*      */     try {
/*  490 */       result = jedis.hget(key, field);
/*      */     }
/*  492 */     catch (Exception e) {
/*  493 */       LOG.error(e.getMessage(), e);
/*  494 */       broken = true;
/*      */     } finally {
/*      */       
/*  497 */       jedis.close();
/*      */     } 
/*  499 */     return result;
/*      */   }
/*      */   
/*      */   public Long hsetnx(String key, String field, String value) {
/*  503 */     Long result = null;
/*  504 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  505 */     if (jedis == null) {
/*  506 */       return result;
/*      */     }
/*  508 */     boolean broken = false;
/*      */     try {
/*  510 */       result = jedis.hsetnx(key, field, value);
/*      */     }
/*  512 */     catch (Exception e) {
/*  513 */       LOG.error(e.getMessage(), e);
/*  514 */       broken = true;
/*      */     } finally {
/*      */       
/*  517 */       jedis.close();
/*      */     } 
/*  519 */     return result;
/*      */   }
/*      */   
/*      */   public String hmset(String key, Map<String, String> hash) {
/*  523 */     String result = null;
/*  524 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  525 */     if (jedis == null) {
/*  526 */       return result;
/*      */     }
/*  528 */     boolean broken = false;
/*      */     try {
/*  530 */       result = jedis.hmset(key, hash);
/*  531 */     } catch (Exception e) {
/*  532 */       LOG.error(e.getMessage(), e);
/*  533 */       broken = true;
/*      */     } finally {
/*      */       
/*  536 */       jedis.close();
/*      */     } 
/*  538 */     return result;
/*      */   }
/*      */   
/*      */   public List<String> hmget(String key, String... fields) {
/*  542 */     List<String> result = null;
/*  543 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  544 */     if (jedis == null) {
/*  545 */       return result;
/*      */     }
/*      */     
/*      */     try {
/*  549 */       result = jedis.hmget(key, fields);
/*      */     }
/*  551 */     catch (Exception e) {
/*  552 */       LOG.error(e.getMessage(), e);
/*      */     } finally {
/*      */       
/*  555 */       jedis.close();
/*      */     } 
/*  557 */     return result;
/*      */   }
/*      */   
/*      */   public Long hincrBy(String key, String field, long value) {
/*  561 */     Long result = null;
/*  562 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  563 */     if (jedis == null) {
/*  564 */       return result;
/*      */     }
/*  566 */     boolean broken = false;
/*      */     try {
/*  568 */       result = jedis.hincrBy(key, field, value);
/*      */     }
/*  570 */     catch (Exception e) {
/*  571 */       LOG.error(e.getMessage(), e);
/*  572 */       broken = true;
/*      */     } finally {
/*      */       
/*  575 */       jedis.close();
/*      */     } 
/*  577 */     return result;
/*      */   }
/*      */   
/*      */   public Boolean hexists(String key, String field) {
/*  581 */     Boolean result = Boolean.valueOf(false);
/*  582 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  583 */     if (jedis == null) {
/*  584 */       return result;
/*      */     }
/*  586 */     boolean broken = false;
/*      */     try {
/*  588 */       result = jedis.hexists(key, field);
/*      */     }
/*  590 */     catch (Exception e) {
/*  591 */       LOG.error(e.getMessage(), e);
/*  592 */       broken = true;
/*      */     } finally {
/*      */       
/*  595 */       jedis.close();
/*      */     } 
/*  597 */     return result;
/*      */   }
/*      */   
/*      */   public Long del(String key) {
/*  601 */     Long result = null;
/*  602 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  603 */     if (jedis == null) {
/*  604 */       return result;
/*      */     }
/*  606 */     boolean broken = false;
/*      */     try {
/*  608 */       result = jedis.del(key);
/*      */     }
/*  610 */     catch (Exception e) {
/*  611 */       LOG.error(e.getMessage(), e);
/*  612 */       broken = true;
/*      */     } finally {
/*      */       
/*  615 */       jedis.close();
/*      */     } 
/*  617 */     return result;
/*      */   }
/*      */   
/*      */   public Long hdel(String key, String field) {
/*  621 */     Long result = null;
/*  622 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  623 */     if (jedis == null) {
/*  624 */       return result;
/*      */     }
/*  626 */     boolean broken = false;
/*      */     try {
/*  628 */       result = jedis.hdel(key, new String[] { field });
/*      */     }
/*  630 */     catch (Exception e) {
/*  631 */       LOG.error(e.getMessage(), e);
/*  632 */       broken = true;
/*      */     } finally {
/*      */       
/*  635 */       jedis.close();
/*      */     } 
/*  637 */     return result;
/*      */   }
/*      */   
/*      */   public Long hlen(String key) {
/*  641 */     Long result = null;
/*  642 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  643 */     if (jedis == null) {
/*  644 */       return result;
/*      */     }
/*  646 */     boolean broken = false;
/*      */     try {
/*  648 */       result = jedis.hlen(key);
/*      */     }
/*  650 */     catch (Exception e) {
/*  651 */       LOG.error(e.getMessage(), e);
/*  652 */       broken = true;
/*      */     } finally {
/*      */       
/*  655 */       jedis.close();
/*      */     } 
/*  657 */     return result;
/*      */   }
/*      */   
/*      */   public Set<String> hkeys(String key) {
/*  661 */     Set<String> result = null;
/*  662 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  663 */     if (jedis == null) {
/*  664 */       return result;
/*      */     }
/*  666 */     boolean broken = false;
/*      */     try {
/*  668 */       result = jedis.hkeys(key);
/*      */     }
/*  670 */     catch (Exception e) {
/*  671 */       LOG.error(e.getMessage(), e);
/*  672 */       broken = true;
/*      */     } finally {
/*      */       
/*  675 */       jedis.close();
/*      */     } 
/*  677 */     return result;
/*      */   }
/*      */   
/*      */   public List<String> hvals(String key) {
/*  681 */     List<String> result = null;
/*  682 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  683 */     if (jedis == null) {
/*  684 */       return result;
/*      */     }
/*  686 */     boolean broken = false;
/*      */     try {
/*  688 */       result = jedis.hvals(key);
/*      */     }
/*  690 */     catch (Exception e) {
/*  691 */       LOG.error(e.getMessage(), e);
/*  692 */       broken = true;
/*      */     } finally {
/*      */       
/*  695 */       jedis.close();
/*      */     } 
/*  697 */     return result;
/*      */   }
/*      */   
/*      */   public Map<String, String> hgetAll(String key) {
/*  701 */     Map<String, String> result = null;
/*  702 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  703 */     if (jedis == null) {
/*  704 */       return result;
/*      */     }
/*  706 */     boolean broken = false;
/*      */     try {
/*  708 */       result = jedis.hgetAll(key);
/*      */     }
/*  710 */     catch (Exception e) {
/*  711 */       LOG.error(e.getMessage(), e);
/*  712 */       broken = true;
/*      */     } finally {
/*      */       
/*  715 */       jedis.close();
/*      */     } 
/*  717 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Long rpush(String key, String string) {
/*  723 */     Long result = null;
/*  724 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  725 */     if (jedis == null) {
/*  726 */       return null;
/*      */     }
/*  728 */     boolean broken = false;
/*      */     try {
/*  730 */       result = jedis.rpush(key, new String[] { string });
/*      */     }
/*  732 */     catch (Exception e) {
/*  733 */       LOG.error(e.getMessage(), e);
/*  734 */       broken = true;
/*      */     } finally {
/*      */       
/*  737 */       jedis.close();
/*      */     } 
/*  739 */     return result;
/*      */   }
/*      */   
/*      */   public Long lpush(String key, String string) {
/*  743 */     Long result = null;
/*  744 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  745 */     if (jedis == null) {
/*  746 */       return result;
/*      */     }
/*  748 */     boolean broken = false;
/*      */     try {
/*  750 */       result = jedis.lpush(key, new String[] { string });
/*      */     }
/*  752 */     catch (Exception e) {
/*  753 */       LOG.error(e.getMessage(), e);
/*  754 */       broken = true;
/*      */     } finally {
/*      */       
/*  757 */       jedis.close();
/*      */     } 
/*  759 */     return result;
/*      */   }
/*      */   
/*      */   public Long llen(String key) {
/*  763 */     Long result = null;
/*  764 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  765 */     if (jedis == null) {
/*  766 */       return result;
/*      */     }
/*  768 */     boolean broken = false;
/*      */     try {
/*  770 */       result = jedis.llen(key);
/*      */     }
/*  772 */     catch (Exception e) {
/*  773 */       LOG.error(e.getMessage(), e);
/*  774 */       broken = true;
/*      */     } finally {
/*      */       
/*  777 */       jedis.close();
/*      */     } 
/*  779 */     return result;
/*      */   }
/*      */   
/*      */   public List<String> lrange(String key, long start, long end) {
/*  783 */     List<String> result = null;
/*  784 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  785 */     if (jedis == null) {
/*  786 */       return result;
/*      */     }
/*  788 */     boolean broken = false;
/*      */     try {
/*  790 */       result = jedis.lrange(key, start, end);
/*      */     }
/*  792 */     catch (Exception e) {
/*  793 */       LOG.error(e.getMessage(), e);
/*  794 */       broken = true;
/*      */     } finally {
/*      */       
/*  797 */       jedis.close();
/*      */     } 
/*  799 */     return result;
/*      */   }
/*      */   
/*      */   public String ltrim(String key, long start, long end) {
/*  803 */     String result = null;
/*  804 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  805 */     if (jedis == null) {
/*  806 */       return result;
/*      */     }
/*  808 */     boolean broken = false;
/*      */     try {
/*  810 */       result = jedis.ltrim(key, start, end);
/*      */     }
/*  812 */     catch (Exception e) {
/*  813 */       LOG.error(e.getMessage(), e);
/*  814 */       broken = true;
/*      */     } finally {
/*      */       
/*  817 */       jedis.close();
/*      */     } 
/*  819 */     return result;
/*      */   }
/*      */   
/*      */   public String lindex(String key, long index) {
/*  823 */     String result = null;
/*  824 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  825 */     if (jedis == null) {
/*  826 */       return result;
/*      */     }
/*  828 */     boolean broken = false;
/*      */     try {
/*  830 */       result = jedis.lindex(key, index);
/*      */     }
/*  832 */     catch (Exception e) {
/*  833 */       LOG.error(e.getMessage(), e);
/*  834 */       broken = true;
/*      */     } finally {
/*      */       
/*  837 */       jedis.close();
/*      */     } 
/*  839 */     return result;
/*      */   }
/*      */   
/*      */   public String lset(String key, long index, String value) {
/*  843 */     String result = null;
/*  844 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  845 */     if (jedis == null) {
/*  846 */       return result;
/*      */     }
/*  848 */     boolean broken = false;
/*      */     try {
/*  850 */       result = jedis.lset(key, index, value);
/*      */     }
/*  852 */     catch (Exception e) {
/*  853 */       LOG.error(e.getMessage(), e);
/*  854 */       broken = true;
/*      */     } finally {
/*      */       
/*  857 */       jedis.close();
/*      */     } 
/*  859 */     return result;
/*      */   }
/*      */   
/*      */   public Long lrem(String key, long count, String value) {
/*  863 */     Long result = null;
/*  864 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  865 */     if (jedis == null) {
/*  866 */       return result;
/*      */     }
/*  868 */     boolean broken = false;
/*      */     try {
/*  870 */       result = jedis.lrem(key, count, value);
/*      */     }
/*  872 */     catch (Exception e) {
/*  873 */       LOG.error(e.getMessage(), e);
/*  874 */       broken = true;
/*      */     } finally {
/*      */       
/*  877 */       jedis.close();
/*      */     } 
/*  879 */     return result;
/*      */   }
/*      */   
/*      */   public String lpop(String key) {
/*  883 */     String result = null;
/*  884 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  885 */     if (jedis == null) {
/*  886 */       return result;
/*      */     }
/*  888 */     boolean broken = false;
/*      */     try {
/*  890 */       result = jedis.lpop(key);
/*      */     }
/*  892 */     catch (Exception e) {
/*  893 */       LOG.error(e.getMessage(), e);
/*  894 */       broken = true;
/*      */     } finally {
/*      */       
/*  897 */       jedis.close();
/*      */     } 
/*  899 */     return result;
/*      */   }
/*      */   
/*      */   public String rpop(String key) {
/*  903 */     String result = null;
/*  904 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  905 */     if (jedis == null) {
/*  906 */       return result;
/*      */     }
/*  908 */     boolean broken = false;
/*      */     try {
/*  910 */       result = jedis.rpop(key);
/*      */     }
/*  912 */     catch (Exception e) {
/*  913 */       LOG.error(e.getMessage(), e);
/*  914 */       broken = true;
/*      */     } finally {
/*      */       
/*  917 */       jedis.close();
/*      */     } 
/*  919 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Long sadd(String key, String member) {
/*  925 */     Long result = null;
/*  926 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  927 */     if (jedis == null) {
/*  928 */       return result;
/*      */     }
/*  930 */     boolean broken = false;
/*      */     try {
/*  932 */       result = jedis.sadd(key, new String[] { member });
/*      */     }
/*  934 */     catch (Exception e) {
/*  935 */       LOG.error(e.getMessage(), e);
/*  936 */       broken = true;
/*      */     } finally {
/*      */       
/*  939 */       jedis.close();
/*      */     } 
/*  941 */     return result;
/*      */   }
/*      */   
/*      */   public Set<String> smembers(String key) {
/*  945 */     Set<String> result = null;
/*  946 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  947 */     if (jedis == null) {
/*  948 */       return result;
/*      */     }
/*  950 */     boolean broken = false;
/*      */     try {
/*  952 */       result = jedis.smembers(key);
/*      */     }
/*  954 */     catch (Exception e) {
/*  955 */       LOG.error(e.getMessage(), e);
/*  956 */       broken = true;
/*      */     } finally {
/*      */       
/*  959 */       jedis.close();
/*      */     } 
/*  961 */     return result;
/*      */   }
/*      */   
/*      */   public Long srem(String key, String member) {
/*  965 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*      */     
/*  967 */     Long result = null;
/*  968 */     if (jedis == null) {
/*  969 */       return result;
/*      */     }
/*  971 */     boolean broken = false;
/*      */     try {
/*  973 */       result = jedis.srem(key, new String[] { member });
/*  974 */     } catch (Exception e) {
/*  975 */       LOG.error(e.getMessage(), e);
/*  976 */       broken = true;
/*      */     } finally {
/*      */       
/*  979 */       jedis.close();
/*      */     } 
/*  981 */     return result;
/*      */   }
/*      */   
/*      */   public String spop(String key) {
/*  985 */     Jedis jedis = this.redisDataSource.getRedisClient();
/*  986 */     String result = null;
/*  987 */     if (jedis == null) {
/*  988 */       return result;
/*      */     }
/*  990 */     boolean broken = false;
/*      */     try {
/*  992 */       result = jedis.spop(key);
/*  993 */     } catch (Exception e) {
/*  994 */       LOG.error(e.getMessage(), e);
/*  995 */       broken = true;
/*      */     } finally {
/*      */       
/*  998 */       jedis.close();
/*      */     } 
/* 1000 */     return result;
/*      */   }
/*      */   
/*      */   public Long scard(String key) {
/* 1004 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1005 */     Long result = null;
/* 1006 */     if (jedis == null) {
/* 1007 */       return result;
/*      */     }
/* 1009 */     boolean broken = false;
/*      */     try {
/* 1011 */       result = jedis.scard(key);
/*      */     }
/* 1013 */     catch (Exception e) {
/* 1014 */       LOG.error(e.getMessage(), e);
/* 1015 */       broken = true;
/*      */     } finally {
/*      */       
/* 1018 */       jedis.close();
/*      */     } 
/* 1020 */     return result;
/*      */   }
/*      */   
/*      */   public Boolean sismember(String key, String member) {
/* 1024 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1025 */     Boolean result = null;
/* 1026 */     if (jedis == null) {
/* 1027 */       return result;
/*      */     }
/* 1029 */     boolean broken = false;
/*      */     try {
/* 1031 */       result = jedis.sismember(key, member);
/* 1032 */     } catch (Exception e) {
/* 1033 */       LOG.error(e.getMessage(), e);
/* 1034 */       broken = true;
/*      */     } finally {
/*      */       
/* 1037 */       jedis.close();
/*      */     } 
/* 1039 */     return result;
/*      */   }
/*      */   
/*      */   public String srandmember(String key) {
/* 1043 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1044 */     String result = null;
/* 1045 */     if (jedis == null) {
/* 1046 */       return result;
/*      */     }
/* 1048 */     boolean broken = false;
/*      */     try {
/* 1050 */       result = jedis.srandmember(key);
/* 1051 */     } catch (Exception e) {
/* 1052 */       LOG.error(e.getMessage(), e);
/* 1053 */       broken = true;
/*      */     } finally {
/* 1055 */       jedis.close();
/*      */     } 
/* 1057 */     return result;
/*      */   }
/*      */   
/*      */   public Long zadd(String key, double score, String member) {
/* 1061 */     Long result = null;
/* 1062 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1063 */     if (jedis == null) {
/* 1064 */       return result;
/*      */     }
/* 1066 */     boolean broken = false;
/*      */     try {
/* 1068 */       result = jedis.zadd(key, score, member);
/* 1069 */     } catch (Exception e) {
/* 1070 */       LOG.error(e.getMessage(), e);
/* 1071 */       broken = true;
/*      */     } finally {
/* 1073 */       jedis.close();
/*      */     } 
/* 1075 */     return result;
/*      */   }
/*      */   
/*      */   public Set<String> zrange(String key, int start, int end) {
/* 1079 */     Set<String> result = null;
/* 1080 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1081 */     if (jedis == null) {
/* 1082 */       return result;
/*      */     }
/* 1084 */     boolean broken = false;
/*      */     try {
/* 1086 */       result = jedis.zrange(key, start, end);
/* 1087 */     } catch (Exception e) {
/* 1088 */       LOG.error(e.getMessage(), e);
/* 1089 */       broken = true;
/*      */     } finally {
/* 1091 */       jedis.close();
/*      */     } 
/* 1093 */     return result;
/*      */   }
/*      */   
/*      */   public Long zrem(String key, String member) {
/* 1097 */     Long result = null;
/* 1098 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1099 */     if (jedis == null) {
/* 1100 */       return result;
/*      */     }
/* 1102 */     boolean broken = false;
/*      */     try {
/* 1104 */       result = jedis.zrem(key, new String[] { member });
/* 1105 */     } catch (Exception e) {
/* 1106 */       LOG.error(e.getMessage(), e);
/* 1107 */       broken = true;
/*      */     } finally {
/* 1109 */       jedis.close();
/*      */     } 
/* 1111 */     return result;
/*      */   }
/*      */   
/*      */   public Double zincrby(String key, double score, String member) {
/* 1115 */     Double result = null;
/* 1116 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1117 */     if (jedis == null) {
/* 1118 */       return result;
/*      */     }
/* 1120 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1123 */       result = jedis.zincrby(key, score, member);
/*      */     }
/* 1125 */     catch (Exception e) {
/* 1126 */       LOG.error(e.getMessage(), e);
/* 1127 */       broken = true;
/*      */     } finally {
/* 1129 */       jedis.close();
/*      */     } 
/* 1131 */     return result;
/*      */   }
/*      */   
/*      */   public Long zrank(String key, String member) {
/* 1135 */     Long result = null;
/* 1136 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1137 */     if (jedis == null) {
/* 1138 */       return result;
/*      */     }
/* 1140 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1143 */       result = jedis.zrank(key, member);
/*      */     }
/* 1145 */     catch (Exception e) {
/* 1146 */       LOG.error(e.getMessage(), e);
/* 1147 */       broken = true;
/*      */     } finally {
/*      */       
/* 1150 */       jedis.close();
/*      */     } 
/* 1152 */     return result;
/*      */   }
/*      */   
/*      */   public Long zrevrank(String key, String member) {
/* 1156 */     Long result = null;
/* 1157 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1158 */     if (jedis == null) {
/* 1159 */       return result;
/*      */     }
/* 1161 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1164 */       result = jedis.zrevrank(key, member);
/*      */     }
/* 1166 */     catch (Exception e) {
/* 1167 */       LOG.error(e.getMessage(), e);
/* 1168 */       broken = true;
/*      */     } finally {
/*      */       
/* 1171 */       jedis.close();
/*      */     } 
/* 1173 */     return result;
/*      */   }
/*      */   
/*      */   public Set<String> zrevrange(String key, int start, int end) {
/* 1177 */     Set<String> result = null;
/* 1178 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1179 */     if (jedis == null) {
/* 1180 */       return result;
/*      */     }
/* 1182 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1185 */       result = jedis.zrevrange(key, start, end);
/*      */     }
/* 1187 */     catch (Exception e) {
/* 1188 */       LOG.error(e.getMessage(), e);
/* 1189 */       broken = true;
/*      */     } finally {
/*      */       
/* 1192 */       jedis.close();
/*      */     } 
/* 1194 */     return result;
/*      */   }
/*      */   
/*      */   public Set<Tuple> zrangeWithScores(String key, int start, int end) {
/* 1198 */     Set<Tuple> result = null;
/* 1199 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1200 */     if (jedis == null) {
/* 1201 */       return result;
/*      */     }
/* 1203 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1206 */       result = jedis.zrangeWithScores(key, start, end);
/*      */     }
/* 1208 */     catch (Exception e) {
/* 1209 */       LOG.error(e.getMessage(), e);
/* 1210 */       broken = true;
/*      */     } finally {
/*      */       
/* 1213 */       jedis.close();
/*      */     } 
/* 1215 */     return result;
/*      */   }
/*      */   
/*      */   public Set<Tuple> zrevrangeWithScores(String key, int start, int end) {
/* 1219 */     Set<Tuple> result = null;
/* 1220 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1221 */     if (jedis == null) {
/* 1222 */       return result;
/*      */     }
/* 1224 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1227 */       result = jedis.zrevrangeWithScores(key, start, end);
/*      */     }
/* 1229 */     catch (Exception e) {
/* 1230 */       LOG.error(e.getMessage(), e);
/* 1231 */       broken = true;
/*      */     } finally {
/*      */       
/* 1234 */       jedis.close();
/*      */     } 
/* 1236 */     return result;
/*      */   }
/*      */   
/*      */   public Long zcard(String key) {
/* 1240 */     Long result = null;
/* 1241 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1242 */     if (jedis == null) {
/* 1243 */       return result;
/*      */     }
/* 1245 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1248 */       result = jedis.zcard(key);
/*      */     }
/* 1250 */     catch (Exception e) {
/* 1251 */       LOG.error(e.getMessage(), e);
/* 1252 */       broken = true;
/*      */     } finally {
/*      */       
/* 1255 */       jedis.close();
/*      */     } 
/* 1257 */     return result;
/*      */   }
/*      */   
/*      */   public Double zscore(String key, String member) {
/* 1261 */     Double result = null;
/* 1262 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1263 */     if (jedis == null) {
/* 1264 */       return result;
/*      */     }
/* 1266 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1269 */       result = jedis.zscore(key, member);
/*      */     }
/* 1271 */     catch (Exception e) {
/* 1272 */       LOG.error(e.getMessage(), e);
/* 1273 */       broken = true;
/*      */     } finally {
/*      */       
/* 1276 */       jedis.close();
/*      */     } 
/* 1278 */     return result;
/*      */   }
/*      */   
/*      */   public List<String> sort(String key) {
/* 1282 */     List<String> result = null;
/* 1283 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1284 */     if (jedis == null) {
/* 1285 */       return result;
/*      */     }
/* 1287 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1290 */       result = jedis.sort(key);
/*      */     }
/* 1292 */     catch (Exception e) {
/* 1293 */       LOG.error(e.getMessage(), e);
/* 1294 */       broken = true;
/*      */     } finally {
/*      */       
/* 1297 */       jedis.close();
/*      */     } 
/* 1299 */     return result;
/*      */   }
/*      */   
/*      */   public List<String> sort(String key, SortingParams sortingParameters) {
/* 1303 */     List<String> result = null;
/* 1304 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1305 */     if (jedis == null) {
/* 1306 */       return result;
/*      */     }
/* 1308 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1311 */       result = jedis.sort(key, sortingParameters);
/*      */     }
/* 1313 */     catch (Exception e) {
/* 1314 */       LOG.error(e.getMessage(), e);
/* 1315 */       broken = true;
/*      */     } finally {
/*      */       
/* 1318 */       jedis.close();
/*      */     } 
/* 1320 */     return result;
/*      */   }
/*      */   
/*      */   public Long zcount(String key, double min, double max) {
/* 1324 */     Long result = null;
/* 1325 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1326 */     if (jedis == null) {
/* 1327 */       return result;
/*      */     }
/* 1329 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1332 */       result = jedis.zcount(key, min, max);
/*      */     }
/* 1334 */     catch (Exception e) {
/* 1335 */       LOG.error(e.getMessage(), e);
/* 1336 */       broken = true;
/*      */     } finally {
/*      */       
/* 1339 */       jedis.close();
/*      */     } 
/* 1341 */     return result;
/*      */   }
/*      */   
/*      */   public Set<String> zrangeByScore(String key, double min, double max) {
/* 1345 */     Set<String> result = null;
/* 1346 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1347 */     if (jedis == null) {
/* 1348 */       return result;
/*      */     }
/* 1350 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1353 */       result = jedis.zrangeByScore(key, min, max);
/*      */     }
/* 1355 */     catch (Exception e) {
/* 1356 */       LOG.error(e.getMessage(), e);
/* 1357 */       broken = true;
/*      */     } finally {
/*      */       
/* 1360 */       jedis.close();
/*      */     } 
/* 1362 */     return result;
/*      */   }
/*      */   
/*      */   public Set<String> zrevrangeByScore(String key, double max, double min) {
/* 1366 */     Set<String> result = null;
/* 1367 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1368 */     if (jedis == null) {
/* 1369 */       return result;
/*      */     }
/* 1371 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1374 */       result = jedis.zrevrangeByScore(key, max, min);
/*      */     }
/* 1376 */     catch (Exception e) {
/* 1377 */       LOG.error(e.getMessage(), e);
/* 1378 */       broken = true;
/*      */     } finally {
/*      */       
/* 1381 */       jedis.close();
/*      */     } 
/* 1383 */     return result;
/*      */   }
/*      */   
/*      */   public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
/* 1387 */     Set<String> result = null;
/* 1388 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1389 */     if (jedis == null) {
/* 1390 */       return result;
/*      */     }
/* 1392 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1395 */       result = jedis.zrangeByScore(key, min, max, offset, count);
/*      */     }
/* 1397 */     catch (Exception e) {
/* 1398 */       LOG.error(e.getMessage(), e);
/* 1399 */       broken = true;
/*      */     } finally {
/*      */       
/* 1402 */       jedis.close();
/*      */     } 
/* 1404 */     return result;
/*      */   }
/*      */   
/*      */   public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
/* 1408 */     Set<String> result = null;
/* 1409 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1410 */     if (jedis == null) {
/* 1411 */       return result;
/*      */     }
/* 1413 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1416 */       result = jedis.zrevrangeByScore(key, max, min, offset, count);
/*      */     }
/* 1418 */     catch (Exception e) {
/* 1419 */       LOG.error(e.getMessage(), e);
/* 1420 */       broken = true;
/*      */     } finally {
/*      */       
/* 1423 */       jedis.close();
/*      */     } 
/* 1425 */     return result;
/*      */   }
/*      */   
/*      */   public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
/* 1429 */     Set<Tuple> result = null;
/* 1430 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1431 */     if (jedis == null) {
/* 1432 */       return result;
/*      */     }
/* 1434 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1437 */       result = jedis.zrangeByScoreWithScores(key, min, max);
/*      */     }
/* 1439 */     catch (Exception e) {
/* 1440 */       LOG.error(e.getMessage(), e);
/* 1441 */       broken = true;
/*      */     } finally {
/*      */       
/* 1444 */       jedis.close();
/*      */     } 
/* 1446 */     return result;
/*      */   }
/*      */   
/*      */   public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
/* 1450 */     Set<Tuple> result = null;
/* 1451 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1452 */     if (jedis == null) {
/* 1453 */       return result;
/*      */     }
/* 1455 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1458 */       result = jedis.zrevrangeByScoreWithScores(key, max, min);
/*      */     }
/* 1460 */     catch (Exception e) {
/* 1461 */       LOG.error(e.getMessage(), e);
/* 1462 */       broken = true;
/*      */     } finally {
/*      */       
/* 1465 */       jedis.close();
/*      */     } 
/* 1467 */     return result;
/*      */   }
/*      */   
/*      */   public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
/* 1471 */     Set<Tuple> result = null;
/* 1472 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1473 */     if (jedis == null) {
/* 1474 */       return result;
/*      */     }
/* 1476 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1479 */       result = jedis.zrangeByScoreWithScores(key, min, max, offset, count);
/*      */     }
/* 1481 */     catch (Exception e) {
/* 1482 */       LOG.error(e.getMessage(), e);
/* 1483 */       broken = true;
/*      */     } finally {
/*      */       
/* 1486 */       jedis.close();
/*      */     } 
/* 1488 */     return result;
/*      */   }
/*      */   
/*      */   public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
/* 1492 */     Set<Tuple> result = null;
/* 1493 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1494 */     if (jedis == null) {
/* 1495 */       return result;
/*      */     }
/* 1497 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1500 */       result = jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
/*      */     }
/* 1502 */     catch (Exception e) {
/* 1503 */       LOG.error(e.getMessage(), e);
/* 1504 */       broken = true;
/*      */     } finally {
/*      */       
/* 1507 */       jedis.close();
/*      */     } 
/* 1509 */     return result;
/*      */   }
/*      */   
/*      */   public Long zremrangeByRank(String key, int start, int end) {
/* 1513 */     Long result = null;
/* 1514 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1515 */     if (jedis == null) {
/* 1516 */       return result;
/*      */     }
/* 1518 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1521 */       result = jedis.zremrangeByRank(key, start, end);
/*      */     }
/* 1523 */     catch (Exception e) {
/* 1524 */       LOG.error(e.getMessage(), e);
/* 1525 */       broken = true;
/*      */     } finally {
/*      */       
/* 1528 */       jedis.close();
/*      */     } 
/* 1530 */     return result;
/*      */   }
/*      */   
/*      */   public Long zremrangeByScore(String key, double start, double end) {
/* 1534 */     Long result = null;
/* 1535 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1536 */     if (jedis == null) {
/* 1537 */       return result;
/*      */     }
/* 1539 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1542 */       result = jedis.zremrangeByScore(key, start, end);
/*      */     }
/* 1544 */     catch (Exception e) {
/* 1545 */       LOG.error(e.getMessage(), e);
/* 1546 */       broken = true;
/*      */     } finally {
/*      */       
/* 1549 */       jedis.close();
/*      */     } 
/* 1551 */     return result;
/*      */   }
/*      */   
/*      */   public Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
/* 1555 */     Long result = null;
/* 1556 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1557 */     if (jedis == null) {
/* 1558 */       return result;
/*      */     }
/* 1560 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1563 */       result = jedis.linsert(key, where, pivot, value);
/*      */     }
/* 1565 */     catch (Exception e) {
/* 1566 */       LOG.error(e.getMessage(), e);
/* 1567 */       broken = true;
/*      */     } finally {
/*      */       
/* 1570 */       jedis.close();
/*      */     } 
/* 1572 */     return result;
/*      */   }
/*      */   
/*      */   public String set(byte[] key, byte[] value) {
/* 1576 */     String result = null;
/* 1577 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1578 */     if (jedis == null) {
/* 1579 */       return result;
/*      */     }
/* 1581 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1584 */       result = jedis.set(key, value);
/*      */     }
/* 1586 */     catch (Exception e) {
/* 1587 */       LOG.error(e.getMessage(), e);
/* 1588 */       broken = true;
/*      */     } finally {
/*      */       
/* 1591 */       jedis.close();
/*      */     } 
/* 1593 */     return result;
/*      */   }
/*      */   
/*      */   public byte[] get(byte[] key) {
/* 1597 */     byte[] result = null;
/* 1598 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1599 */     if (jedis == null) {
/* 1600 */       return result;
/*      */     }
/* 1602 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1605 */       result = jedis.get(key);
/*      */     }
/* 1607 */     catch (Exception e) {
/* 1608 */       LOG.error(e.getMessage(), e);
/* 1609 */       broken = true;
/*      */     } finally {
/*      */       
/* 1612 */       jedis.close();
/*      */     } 
/* 1614 */     return result;
/*      */   }
/*      */   
/*      */   public Boolean exists(byte[] key) {
/* 1618 */     Boolean result = Boolean.valueOf(false);
/* 1619 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1620 */     if (jedis == null) {
/* 1621 */       return result;
/*      */     }
/* 1623 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1626 */       result = jedis.exists(key);
/*      */     }
/* 1628 */     catch (Exception e) {
/* 1629 */       LOG.error(e.getMessage(), e);
/* 1630 */       broken = true;
/*      */     } finally {
/*      */       
/* 1633 */       jedis.close();
/*      */     } 
/* 1635 */     return result;
/*      */   }
/*      */   
/*      */   public String type(byte[] key) {
/* 1639 */     String result = null;
/* 1640 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1641 */     if (jedis == null) {
/* 1642 */       return result;
/*      */     }
/* 1644 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1647 */       result = jedis.type(key);
/*      */     }
/* 1649 */     catch (Exception e) {
/* 1650 */       LOG.error(e.getMessage(), e);
/* 1651 */       broken = true;
/*      */     } finally {
/*      */       
/* 1654 */       jedis.close();
/*      */     } 
/* 1656 */     return result;
/*      */   }
/*      */   
/*      */   public Long expire(byte[] key, int seconds) {
/* 1660 */     Long result = null;
/* 1661 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1662 */     if (jedis == null) {
/* 1663 */       return result;
/*      */     }
/* 1665 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1668 */       result = jedis.expire(key, seconds);
/*      */     }
/* 1670 */     catch (Exception e) {
/* 1671 */       LOG.error(e.getMessage(), e);
/* 1672 */       broken = true;
/*      */     } finally {
/*      */       
/* 1675 */       jedis.close();
/*      */     } 
/* 1677 */     return result;
/*      */   }
/*      */   
/*      */   public Long expireAt(byte[] key, long unixTime) {
/* 1681 */     Long result = null;
/* 1682 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1683 */     if (jedis == null) {
/* 1684 */       return result;
/*      */     }
/* 1686 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1689 */       result = jedis.expireAt(key, unixTime);
/*      */     }
/* 1691 */     catch (Exception e) {
/* 1692 */       LOG.error(e.getMessage(), e);
/* 1693 */       broken = true;
/*      */     } finally {
/*      */       
/* 1696 */       jedis.close();
/*      */     } 
/* 1698 */     return result;
/*      */   }
/*      */   
/*      */   public Long ttl(byte[] key) {
/* 1702 */     Long result = null;
/* 1703 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1704 */     if (jedis == null) {
/* 1705 */       return result;
/*      */     }
/* 1707 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1710 */       result = jedis.ttl(key);
/*      */     }
/* 1712 */     catch (Exception e) {
/* 1713 */       LOG.error(e.getMessage(), e);
/* 1714 */       broken = true;
/*      */     } finally {
/*      */       
/* 1717 */       jedis.close();
/*      */     } 
/* 1719 */     return result;
/*      */   }
/*      */   
/*      */   public byte[] getSet(byte[] key, byte[] value) {
/* 1723 */     byte[] result = null;
/* 1724 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1725 */     if (jedis == null) {
/* 1726 */       return result;
/*      */     }
/* 1728 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1731 */       result = jedis.getSet(key, value);
/*      */     }
/* 1733 */     catch (Exception e) {
/* 1734 */       LOG.error(e.getMessage(), e);
/* 1735 */       broken = true;
/*      */     } finally {
/*      */       
/* 1738 */       jedis.close();
/*      */     } 
/* 1740 */     return result;
/*      */   }
/*      */   
/*      */   public Long setnx(byte[] key, byte[] value) {
/* 1744 */     Long result = null;
/* 1745 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1746 */     if (jedis == null) {
/* 1747 */       return result;
/*      */     }
/* 1749 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1752 */       result = jedis.setnx(key, value);
/*      */     }
/* 1754 */     catch (Exception e) {
/* 1755 */       LOG.error(e.getMessage(), e);
/* 1756 */       broken = true;
/*      */     } finally {
/*      */       
/* 1759 */       jedis.close();
/*      */     } 
/* 1761 */     return result;
/*      */   }
/*      */   
/*      */   public String setex(byte[] key, int seconds, byte[] value) {
/* 1765 */     String result = null;
/* 1766 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1767 */     if (jedis == null) {
/* 1768 */       return result;
/*      */     }
/* 1770 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1773 */       result = jedis.setex(key, seconds, value);
/*      */     }
/* 1775 */     catch (Exception e) {
/* 1776 */       LOG.error(e.getMessage(), e);
/* 1777 */       broken = true;
/*      */     } finally {
/*      */       
/* 1780 */       jedis.close();
/*      */     } 
/* 1782 */     return result;
/*      */   }
/*      */   
/*      */   public Long decrBy(byte[] key, long integer) {
/* 1786 */     Long result = null;
/* 1787 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1788 */     if (jedis == null) {
/* 1789 */       return result;
/*      */     }
/* 1791 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1794 */       result = jedis.decrBy(key, integer);
/*      */     }
/* 1796 */     catch (Exception e) {
/* 1797 */       LOG.error(e.getMessage(), e);
/* 1798 */       broken = true;
/*      */     } finally {
/*      */       
/* 1801 */       jedis.close();
/*      */     } 
/* 1803 */     return result;
/*      */   }
/*      */   
/*      */   public Long decr(byte[] key) {
/* 1807 */     Long result = null;
/* 1808 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1809 */     if (jedis == null) {
/* 1810 */       return result;
/*      */     }
/* 1812 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1815 */       result = jedis.decr(key);
/*      */     }
/* 1817 */     catch (Exception e) {
/* 1818 */       LOG.error(e.getMessage(), e);
/* 1819 */       broken = true;
/*      */     } finally {
/*      */       
/* 1822 */       jedis.close();
/*      */     } 
/* 1824 */     return result;
/*      */   }
/*      */   
/*      */   public Long incrBy(byte[] key, long integer) {
/* 1828 */     Long result = null;
/* 1829 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1830 */     if (jedis == null) {
/* 1831 */       return result;
/*      */     }
/* 1833 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1836 */       result = jedis.incrBy(key, integer);
/*      */     }
/* 1838 */     catch (Exception e) {
/* 1839 */       LOG.error(e.getMessage(), e);
/* 1840 */       broken = true;
/*      */     } finally {
/*      */       
/* 1843 */       jedis.close();
/*      */     } 
/* 1845 */     return result;
/*      */   }
/*      */   
/*      */   public Long incr(byte[] key) {
/* 1849 */     Long result = null;
/* 1850 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1851 */     if (jedis == null) {
/* 1852 */       return result;
/*      */     }
/* 1854 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1857 */       result = jedis.incr(key);
/*      */     }
/* 1859 */     catch (Exception e) {
/* 1860 */       LOG.error(e.getMessage(), e);
/* 1861 */       broken = true;
/*      */     } finally {
/*      */       
/* 1864 */       jedis.close();
/*      */     } 
/* 1866 */     return result;
/*      */   }
/*      */   
/*      */   public Long append(byte[] key, byte[] value) {
/* 1870 */     Long result = null;
/* 1871 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1872 */     if (jedis == null) {
/* 1873 */       return result;
/*      */     }
/* 1875 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1878 */       result = jedis.append(key, value);
/*      */     }
/* 1880 */     catch (Exception e) {
/* 1881 */       LOG.error(e.getMessage(), e);
/* 1882 */       broken = true;
/*      */     } finally {
/*      */       
/* 1885 */       jedis.close();
/*      */     } 
/* 1887 */     return result;
/*      */   }
/*      */   
/*      */   public byte[] substr(byte[] key, int start, int end) {
/* 1891 */     byte[] result = null;
/* 1892 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1893 */     if (jedis == null) {
/* 1894 */       return result;
/*      */     }
/* 1896 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1899 */       result = jedis.substr(key, start, end);
/*      */     }
/* 1901 */     catch (Exception e) {
/* 1902 */       LOG.error(e.getMessage(), e);
/* 1903 */       broken = true;
/*      */     } finally {
/*      */       
/* 1906 */       jedis.close();
/*      */     } 
/* 1908 */     return result;
/*      */   }
/*      */   
/*      */   public Long hset(byte[] key, byte[] field, byte[] value) {
/* 1912 */     Long result = null;
/* 1913 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1914 */     if (jedis == null) {
/* 1915 */       return result;
/*      */     }
/* 1917 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1920 */       result = jedis.hset(key, field, value);
/*      */     }
/* 1922 */     catch (Exception e) {
/* 1923 */       LOG.error(e.getMessage(), e);
/* 1924 */       broken = true;
/*      */     }
/*      */     finally {
/*      */       
/* 1928 */       jedis.close();
/*      */     } 
/* 1930 */     return result;
/*      */   }
/*      */   
/*      */   public byte[] hget(byte[] key, byte[] field) {
/* 1934 */     byte[] result = null;
/* 1935 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1936 */     if (jedis == null) {
/* 1937 */       return result;
/*      */     }
/* 1939 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1942 */       result = jedis.hget(key, field);
/*      */     }
/* 1944 */     catch (Exception e) {
/* 1945 */       LOG.error(e.getMessage(), e);
/* 1946 */       broken = true;
/*      */     }
/*      */     finally {
/*      */       
/* 1950 */       jedis.close();
/*      */     } 
/* 1952 */     return result;
/*      */   }
/*      */   
/*      */   public Long hsetnx(byte[] key, byte[] field, byte[] value) {
/* 1956 */     Long result = null;
/* 1957 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1958 */     if (jedis == null) {
/* 1959 */       return result;
/*      */     }
/* 1961 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1964 */       result = jedis.hsetnx(key, field, value);
/*      */     }
/* 1966 */     catch (Exception e) {
/*      */       
/* 1968 */       LOG.error(e.getMessage(), e);
/* 1969 */       broken = true;
/*      */     }
/*      */     finally {
/*      */       
/* 1973 */       jedis.close();
/*      */     } 
/* 1975 */     return result;
/*      */   }
/*      */   
/*      */   public String hmset(byte[] key, Map<byte[], byte[]> hash) {
/* 1979 */     String result = null;
/* 1980 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 1981 */     if (jedis == null) {
/* 1982 */       return result;
/*      */     }
/* 1984 */     boolean broken = false;
/*      */     
/*      */     try {
/* 1987 */       result = jedis.hmset(key, hash);
/*      */     }
/* 1989 */     catch (Exception e) {
/*      */       
/* 1991 */       LOG.error(e.getMessage(), e);
/* 1992 */       broken = true;
/*      */     } finally {
/*      */       
/* 1995 */       jedis.close();
/*      */     } 
/* 1997 */     return result;
/*      */   }
/*      */   
/*      */   public List<byte[]> hmget(byte[] key, byte[]... fields) {
/* 2001 */     List<byte[]> result = null;
/* 2002 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2003 */     if (jedis == null) {
/* 2004 */       return result;
/*      */     }
/* 2006 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2009 */       result = jedis.hmget(key, fields);
/*      */     }
/* 2011 */     catch (Exception e) {
/*      */       
/* 2013 */       LOG.error(e.getMessage(), e);
/* 2014 */       broken = true;
/*      */     } finally {
/*      */       
/* 2017 */       jedis.close();
/*      */     } 
/* 2019 */     return result;
/*      */   }
/*      */   
/*      */   public Long hincrBy(byte[] key, byte[] field, long value) {
/* 2023 */     Long result = null;
/* 2024 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2025 */     if (jedis == null) {
/* 2026 */       return result;
/*      */     }
/* 2028 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2031 */       result = jedis.hincrBy(key, field, value);
/*      */     }
/* 2033 */     catch (Exception e) {
/*      */       
/* 2035 */       LOG.error(e.getMessage(), e);
/* 2036 */       broken = true;
/*      */     } finally {
/*      */       
/* 2039 */       jedis.close();
/*      */     } 
/* 2041 */     return result;
/*      */   }
/*      */   
/*      */   public Boolean hexists(byte[] key, byte[] field) {
/* 2045 */     Boolean result = Boolean.valueOf(false);
/* 2046 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2047 */     if (jedis == null) {
/* 2048 */       return result;
/*      */     }
/* 2050 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2053 */       result = jedis.hexists(key, field);
/*      */     }
/* 2055 */     catch (Exception e) {
/*      */       
/* 2057 */       LOG.error(e.getMessage(), e);
/* 2058 */       broken = true;
/*      */     } finally {
/*      */       
/* 2061 */       jedis.close();
/*      */     } 
/* 2063 */     return result;
/*      */   }
/*      */   
/*      */   public Long hdel(byte[] key, byte[] field) {
/* 2067 */     Long result = null;
/* 2068 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2069 */     if (jedis == null) {
/* 2070 */       return result;
/*      */     }
/* 2072 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2075 */       result = jedis.hdel(key, new byte[][] { field });
/*      */     }
/* 2077 */     catch (Exception e) {
/*      */       
/* 2079 */       LOG.error(e.getMessage(), e);
/* 2080 */       broken = true;
/*      */     } finally {
/*      */       
/* 2083 */       jedis.close();
/*      */     } 
/* 2085 */     return result;
/*      */   }
/*      */   
/*      */   public Long hlen(byte[] key) {
/* 2089 */     Long result = null;
/* 2090 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2091 */     if (jedis == null) {
/* 2092 */       return result;
/*      */     }
/* 2094 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2097 */       result = jedis.hlen(key);
/*      */     }
/* 2099 */     catch (Exception e) {
/*      */       
/* 2101 */       LOG.error(e.getMessage(), e);
/* 2102 */       broken = true;
/*      */     } finally {
/*      */       
/* 2105 */       jedis.close();
/*      */     } 
/* 2107 */     return result;
/*      */   }
/*      */   
/*      */   public Set<byte[]> hkeys(byte[] key) {
/* 2111 */     Set<byte[]> result = null;
/* 2112 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2113 */     if (jedis == null) {
/* 2114 */       return result;
/*      */     }
/* 2116 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2119 */       result = jedis.hkeys(key);
/*      */     }
/* 2121 */     catch (Exception e) {
/*      */       
/* 2123 */       LOG.error(e.getMessage(), e);
/* 2124 */       broken = true;
/*      */     } finally {
/*      */       
/* 2127 */       jedis.close();
/*      */     } 
/* 2129 */     return result;
/*      */   }
/*      */   
/*      */   public Collection<byte[]> hvals(byte[] key) {
/* 2133 */     Collection<byte[]> result = null;
/* 2134 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2135 */     if (jedis == null) {
/* 2136 */       return result;
/*      */     }
/* 2138 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2141 */       result = jedis.hvals(key);
/*      */     }
/* 2143 */     catch (Exception e) {
/*      */       
/* 2145 */       LOG.error(e.getMessage(), e);
/* 2146 */       broken = true;
/*      */     } finally {
/*      */       
/* 2149 */       jedis.close();
/*      */     } 
/* 2151 */     return result;
/*      */   }
/*      */   
/*      */   public Map<byte[], byte[]> hgetAll(byte[] key) {
/* 2155 */     Map<byte[], byte[]> result = null;
/* 2156 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2157 */     if (jedis == null) {
/* 2158 */       return result;
/*      */     }
/* 2160 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2163 */       result = jedis.hgetAll(key);
/*      */     }
/* 2165 */     catch (Exception e) {
/*      */       
/* 2167 */       LOG.error(e.getMessage(), e);
/* 2168 */       broken = true;
/*      */     } finally {
/*      */       
/* 2171 */       jedis.close();
/*      */     } 
/* 2173 */     return result;
/*      */   }
/*      */   
/*      */   public Long rpush(byte[] key, byte[] string) {
/* 2177 */     Long result = null;
/* 2178 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2179 */     if (jedis == null) {
/* 2180 */       return result;
/*      */     }
/* 2182 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2185 */       result = jedis.rpush(key, new byte[][] { string });
/*      */     }
/* 2187 */     catch (Exception e) {
/*      */       
/* 2189 */       LOG.error(e.getMessage(), e);
/* 2190 */       broken = true;
/*      */     } finally {
/*      */       
/* 2193 */       jedis.close();
/*      */     } 
/* 2195 */     return result;
/*      */   }
/*      */   
/*      */   public Long lpush(byte[] key, byte[] string) {
/* 2199 */     Long result = null;
/* 2200 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2201 */     if (jedis == null) {
/* 2202 */       return result;
/*      */     }
/* 2204 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2207 */       result = jedis.lpush(key, new byte[][] { string });
/*      */     }
/* 2209 */     catch (Exception e) {
/*      */       
/* 2211 */       LOG.error(e.getMessage(), e);
/* 2212 */       broken = true;
/*      */     } finally {
/*      */       
/* 2215 */       jedis.close();
/*      */     } 
/* 2217 */     return result;
/*      */   }
/*      */   
/*      */   public Long llen(byte[] key) {
/* 2221 */     Long result = null;
/* 2222 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2223 */     if (jedis == null) {
/* 2224 */       return result;
/*      */     }
/* 2226 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2229 */       result = jedis.llen(key);
/*      */     }
/* 2231 */     catch (Exception e) {
/*      */       
/* 2233 */       LOG.error(e.getMessage(), e);
/* 2234 */       broken = true;
/*      */     } finally {
/*      */       
/* 2237 */       jedis.close();
/*      */     } 
/* 2239 */     return result;
/*      */   }
/*      */   
/*      */   public List<byte[]> lrange(byte[] key, int start, int end) {
/* 2243 */     List<byte[]> result = null;
/* 2244 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2245 */     if (jedis == null) {
/* 2246 */       return result;
/*      */     }
/* 2248 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2251 */       result = jedis.lrange(key, start, end);
/*      */     }
/* 2253 */     catch (Exception e) {
/*      */       
/* 2255 */       LOG.error(e.getMessage(), e);
/* 2256 */       broken = true;
/*      */     } finally {
/*      */       
/* 2259 */       jedis.close();
/*      */     } 
/* 2261 */     return result;
/*      */   }
/*      */   
/*      */   public String ltrim(byte[] key, int start, int end) {
/* 2265 */     String result = null;
/* 2266 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2267 */     if (jedis == null) {
/* 2268 */       return result;
/*      */     }
/* 2270 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2273 */       result = jedis.ltrim(key, start, end);
/*      */     }
/* 2275 */     catch (Exception e) {
/* 2276 */       LOG.error(e.getMessage(), e);
/* 2277 */       broken = true;
/*      */     } finally {
/*      */       
/* 2280 */       jedis.close();
/*      */     } 
/* 2282 */     return result;
/*      */   }
/*      */   
/*      */   public byte[] lindex(byte[] key, int index) {
/* 2286 */     byte[] result = null;
/* 2287 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2288 */     if (jedis == null) {
/* 2289 */       return result;
/*      */     }
/* 2291 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2294 */       result = jedis.lindex(key, index);
/*      */     }
/* 2296 */     catch (Exception e) {
/*      */       
/* 2298 */       LOG.error(e.getMessage(), e);
/* 2299 */       broken = true;
/*      */     } finally {
/*      */       
/* 2302 */       jedis.close();
/*      */     } 
/* 2304 */     return result;
/*      */   }
/*      */   
/*      */   public String lset(byte[] key, int index, byte[] value) {
/* 2308 */     String result = null;
/* 2309 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2310 */     if (jedis == null) {
/* 2311 */       return result;
/*      */     }
/* 2313 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2316 */       result = jedis.lset(key, index, value);
/*      */     }
/* 2318 */     catch (Exception e) {
/*      */       
/* 2320 */       LOG.error(e.getMessage(), e);
/* 2321 */       broken = true;
/*      */     } finally {
/*      */       
/* 2324 */       jedis.close();
/*      */     } 
/* 2326 */     return result;
/*      */   }
/*      */   
/*      */   public Long lrem(byte[] key, int count, byte[] value) {
/* 2330 */     Long result = null;
/* 2331 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2332 */     if (jedis == null) {
/* 2333 */       return result;
/*      */     }
/* 2335 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2338 */       result = jedis.lrem(key, count, value);
/*      */     }
/* 2340 */     catch (Exception e) {
/*      */       
/* 2342 */       LOG.error(e.getMessage(), e);
/* 2343 */       broken = true;
/*      */     } finally {
/*      */       
/* 2346 */       jedis.close();
/*      */     } 
/* 2348 */     return result;
/*      */   }
/*      */   
/*      */   public byte[] lpop(byte[] key) {
/* 2352 */     byte[] result = null;
/* 2353 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2354 */     if (jedis == null) {
/* 2355 */       return result;
/*      */     }
/* 2357 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2360 */       result = jedis.lpop(key);
/*      */     }
/* 2362 */     catch (Exception e) {
/*      */       
/* 2364 */       LOG.error(e.getMessage(), e);
/* 2365 */       broken = true;
/*      */     } finally {
/*      */       
/* 2368 */       jedis.close();
/*      */     } 
/* 2370 */     return result;
/*      */   }
/*      */   
/*      */   public byte[] rpop(byte[] key) {
/* 2374 */     byte[] result = null;
/* 2375 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2376 */     if (jedis == null) {
/* 2377 */       return result;
/*      */     }
/* 2379 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2382 */       result = jedis.rpop(key);
/*      */     }
/* 2384 */     catch (Exception e) {
/*      */       
/* 2386 */       LOG.error(e.getMessage(), e);
/* 2387 */       broken = true;
/*      */     } finally {
/*      */       
/* 2390 */       jedis.close();
/*      */     } 
/* 2392 */     return result;
/*      */   }
/*      */   
/*      */   public Long sadd(byte[] key, byte[] member) {
/* 2396 */     Long result = null;
/* 2397 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2398 */     if (jedis == null) {
/* 2399 */       return result;
/*      */     }
/* 2401 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2404 */       result = jedis.sadd(key, new byte[][] { member });
/*      */     }
/* 2406 */     catch (Exception e) {
/*      */       
/* 2408 */       LOG.error(e.getMessage(), e);
/* 2409 */       broken = true;
/*      */     } finally {
/*      */       
/* 2412 */       jedis.close();
/*      */     } 
/* 2414 */     return result;
/*      */   }
/*      */   
/*      */   public Set<byte[]> smembers(byte[] key) {
/* 2418 */     Set<byte[]> result = null;
/* 2419 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2420 */     if (jedis == null) {
/* 2421 */       return result;
/*      */     }
/* 2423 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2426 */       result = jedis.smembers(key);
/*      */     }
/* 2428 */     catch (Exception e) {
/*      */       
/* 2430 */       LOG.error(e.getMessage(), e);
/* 2431 */       broken = true;
/*      */     } finally {
/*      */       
/* 2434 */       jedis.close();
/*      */     } 
/* 2436 */     return result;
/*      */   }
/*      */   
/*      */   public Long srem(byte[] key, byte[] member) {
/* 2440 */     Long result = null;
/* 2441 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2442 */     if (jedis == null) {
/* 2443 */       return result;
/*      */     }
/* 2445 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2448 */       result = jedis.srem(key, new byte[][] { member });
/*      */     }
/* 2450 */     catch (Exception e) {
/*      */       
/* 2452 */       LOG.error(e.getMessage(), e);
/* 2453 */       broken = true;
/*      */     } finally {
/*      */       
/* 2456 */       jedis.close();
/*      */     } 
/* 2458 */     return result;
/*      */   }
/*      */   
/*      */   public byte[] spop(byte[] key) {
/* 2462 */     byte[] result = null;
/* 2463 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2464 */     if (jedis == null) {
/* 2465 */       return result;
/*      */     }
/* 2467 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2470 */       result = jedis.spop(key);
/*      */     }
/* 2472 */     catch (Exception e) {
/*      */       
/* 2474 */       LOG.error(e.getMessage(), e);
/* 2475 */       broken = true;
/*      */     } finally {
/*      */       
/* 2478 */       jedis.close();
/*      */     } 
/* 2480 */     return result;
/*      */   }
/*      */   
/*      */   public Long scard(byte[] key) {
/* 2484 */     Long result = null;
/* 2485 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2486 */     if (jedis == null) {
/* 2487 */       return result;
/*      */     }
/* 2489 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2492 */       result = jedis.scard(key);
/*      */     }
/* 2494 */     catch (Exception e) {
/*      */       
/* 2496 */       LOG.error(e.getMessage(), e);
/* 2497 */       broken = true;
/*      */     } finally {
/*      */       
/* 2500 */       jedis.close();
/*      */     } 
/* 2502 */     return result;
/*      */   }
/*      */   
/*      */   public Boolean sismember(byte[] key, byte[] member) {
/* 2506 */     Boolean result = Boolean.valueOf(false);
/* 2507 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2508 */     if (jedis == null) {
/* 2509 */       return result;
/*      */     }
/* 2511 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2514 */       result = jedis.sismember(key, member);
/*      */     }
/* 2516 */     catch (Exception e) {
/*      */       
/* 2518 */       LOG.error(e.getMessage(), e);
/* 2519 */       broken = true;
/*      */     } finally {
/*      */       
/* 2522 */       jedis.close();
/*      */     } 
/* 2524 */     return result;
/*      */   }
/*      */   
/*      */   public byte[] srandmember(byte[] key) {
/* 2528 */     byte[] result = null;
/* 2529 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2530 */     if (jedis == null) {
/* 2531 */       return result;
/*      */     }
/* 2533 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2536 */       result = jedis.srandmember(key);
/*      */     }
/* 2538 */     catch (Exception e) {
/*      */       
/* 2540 */       LOG.error(e.getMessage(), e);
/* 2541 */       broken = true;
/*      */     } finally {
/*      */       
/* 2544 */       jedis.close();
/*      */     } 
/* 2546 */     return result;
/*      */   }
/*      */   
/*      */   public Long zadd(byte[] key, double score, byte[] member) {
/* 2550 */     Long result = null;
/* 2551 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2552 */     if (jedis == null) {
/* 2553 */       return result;
/*      */     }
/* 2555 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2558 */       result = jedis.zadd(key, score, member);
/*      */     }
/* 2560 */     catch (Exception e) {
/*      */       
/* 2562 */       LOG.error(e.getMessage(), e);
/* 2563 */       broken = true;
/*      */     } finally {
/*      */       
/* 2566 */       jedis.close();
/*      */     } 
/* 2568 */     return result;
/*      */   }
/*      */   
/*      */   public Set<byte[]> zrange(byte[] key, int start, int end) {
/* 2572 */     Set<byte[]> result = null;
/* 2573 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2574 */     if (jedis == null) {
/* 2575 */       return result;
/*      */     }
/* 2577 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2580 */       result = jedis.zrange(key, start, end);
/*      */     }
/* 2582 */     catch (Exception e) {
/*      */       
/* 2584 */       LOG.error(e.getMessage(), e);
/* 2585 */       broken = true;
/*      */     } finally {
/*      */       
/* 2588 */       jedis.close();
/*      */     } 
/*      */     
/* 2591 */     return result;
/*      */   }
/*      */   
/*      */   public Long zrem(byte[] key, byte[] member) {
/* 2595 */     Long result = null;
/* 2596 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2597 */     if (jedis == null) {
/* 2598 */       return result;
/*      */     }
/* 2600 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2603 */       result = jedis.zrem(key, new byte[][] { member });
/*      */     }
/* 2605 */     catch (Exception e) {
/*      */       
/* 2607 */       LOG.error(e.getMessage(), e);
/* 2608 */       broken = true;
/*      */     } finally {
/*      */       
/* 2611 */       jedis.close();
/*      */     } 
/*      */     
/* 2614 */     return result;
/*      */   }
/*      */   
/*      */   public Double zincrby(byte[] key, double score, byte[] member) {
/* 2618 */     Double result = null;
/* 2619 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2620 */     if (jedis == null) {
/* 2621 */       return result;
/*      */     }
/* 2623 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2626 */       result = jedis.zincrby(key, score, member);
/*      */     }
/* 2628 */     catch (Exception e) {
/*      */       
/* 2630 */       LOG.error(e.getMessage(), e);
/* 2631 */       broken = true;
/*      */     } finally {
/*      */       
/* 2634 */       jedis.close();
/*      */     } 
/*      */     
/* 2637 */     return result;
/*      */   }
/*      */   
/*      */   public Long zrank(byte[] key, byte[] member) {
/* 2641 */     Long result = null;
/* 2642 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2643 */     if (jedis == null) {
/* 2644 */       return result;
/*      */     }
/* 2646 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2649 */       result = jedis.zrank(key, member);
/*      */     }
/* 2651 */     catch (Exception e) {
/*      */       
/* 2653 */       LOG.error(e.getMessage(), e);
/* 2654 */       broken = true;
/*      */     } finally {
/*      */       
/* 2657 */       jedis.close();
/*      */     } 
/* 2659 */     return result;
/*      */   }
/*      */   
/*      */   public Long zrevrank(byte[] key, byte[] member) {
/* 2663 */     Long result = null;
/* 2664 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2665 */     if (jedis == null) {
/* 2666 */       return result;
/*      */     }
/* 2668 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2671 */       result = jedis.zrevrank(key, member);
/*      */     }
/* 2673 */     catch (Exception e) {
/*      */       
/* 2675 */       LOG.error(e.getMessage(), e);
/* 2676 */       broken = true;
/*      */     } finally {
/*      */       
/* 2679 */       jedis.close();
/*      */     } 
/*      */     
/* 2682 */     return result;
/*      */   }
/*      */   
/*      */   public Set<byte[]> zrevrange(byte[] key, int start, int end) {
/* 2686 */     Set<byte[]> result = null;
/* 2687 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2688 */     if (jedis == null) {
/* 2689 */       return result;
/*      */     }
/* 2691 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2694 */       result = jedis.zrevrange(key, start, end);
/*      */     }
/* 2696 */     catch (Exception e) {
/*      */       
/* 2698 */       LOG.error(e.getMessage(), e);
/* 2699 */       broken = true;
/*      */     } finally {
/*      */       
/* 2702 */       jedis.close();
/*      */     } 
/* 2704 */     return result;
/*      */   }
/*      */   
/*      */   public Set<Tuple> zrangeWithScores(byte[] key, int start, int end) {
/* 2708 */     Set<Tuple> result = null;
/* 2709 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2710 */     if (jedis == null) {
/* 2711 */       return result;
/*      */     }
/* 2713 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2716 */       result = jedis.zrangeWithScores(key, start, end);
/*      */     }
/* 2718 */     catch (Exception e) {
/*      */       
/* 2720 */       LOG.error(e.getMessage(), e);
/* 2721 */       broken = true;
/*      */     } finally {
/*      */       
/* 2724 */       jedis.close();
/*      */     } 
/*      */     
/* 2727 */     return result;
/*      */   }
/*      */   
/*      */   public Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end) {
/* 2731 */     Set<Tuple> result = null;
/* 2732 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2733 */     if (jedis == null) {
/* 2734 */       return result;
/*      */     }
/* 2736 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2739 */       result = jedis.zrevrangeWithScores(key, start, end);
/*      */     }
/* 2741 */     catch (Exception e) {
/*      */       
/* 2743 */       LOG.error(e.getMessage(), e);
/* 2744 */       broken = true;
/*      */     } finally {
/*      */       
/* 2747 */       jedis.close();
/*      */     } 
/*      */     
/* 2750 */     return result;
/*      */   }
/*      */   
/*      */   public Long zcard(byte[] key) {
/* 2754 */     Long result = null;
/* 2755 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2756 */     if (jedis == null) {
/* 2757 */       return result;
/*      */     }
/* 2759 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2762 */       result = jedis.zcard(key);
/*      */     }
/* 2764 */     catch (Exception e) {
/*      */       
/* 2766 */       LOG.error(e.getMessage(), e);
/* 2767 */       broken = true;
/*      */     } finally {
/*      */       
/* 2770 */       jedis.close();
/*      */     } 
/* 2772 */     return result;
/*      */   }
/*      */   
/*      */   public Double zscore(byte[] key, byte[] member) {
/* 2776 */     Double result = null;
/* 2777 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2778 */     if (jedis == null) {
/* 2779 */       return result;
/*      */     }
/* 2781 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2784 */       result = jedis.zscore(key, member);
/*      */     }
/* 2786 */     catch (Exception e) {
/*      */       
/* 2788 */       LOG.error(e.getMessage(), e);
/* 2789 */       broken = true;
/*      */     } finally {
/*      */       
/* 2792 */       jedis.close();
/*      */     } 
/* 2794 */     return result;
/*      */   }
/*      */   
/*      */   public List<byte[]> sort(byte[] key) {
/* 2798 */     List<byte[]> result = null;
/* 2799 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2800 */     if (jedis == null) {
/* 2801 */       return result;
/*      */     }
/* 2803 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2806 */       result = jedis.sort(key);
/*      */     }
/* 2808 */     catch (Exception e) {
/*      */       
/* 2810 */       LOG.error(e.getMessage(), e);
/* 2811 */       broken = true;
/*      */     } finally {
/*      */       
/* 2814 */       jedis.close();
/*      */     } 
/* 2816 */     return result;
/*      */   }
/*      */   
/*      */   public List<byte[]> sort(byte[] key, SortingParams sortingParameters) {
/* 2820 */     List<byte[]> result = null;
/* 2821 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2822 */     if (jedis == null) {
/* 2823 */       return result;
/*      */     }
/* 2825 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2828 */       result = jedis.sort(key, sortingParameters);
/*      */     }
/* 2830 */     catch (Exception e) {
/*      */       
/* 2832 */       LOG.error(e.getMessage(), e);
/* 2833 */       broken = true;
/*      */     } finally {
/*      */       
/* 2836 */       jedis.close();
/*      */     } 
/* 2838 */     return result;
/*      */   }
/*      */   
/*      */   public Long zcount(byte[] key, double min, double max) {
/* 2842 */     Long result = null;
/* 2843 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2844 */     if (jedis == null) {
/* 2845 */       return result;
/*      */     }
/* 2847 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2850 */       result = jedis.zcount(key, min, max);
/*      */     }
/* 2852 */     catch (Exception e) {
/*      */       
/* 2854 */       LOG.error(e.getMessage(), e);
/* 2855 */       broken = true;
/*      */     } finally {
/*      */       
/* 2858 */       jedis.close();
/*      */     } 
/* 2860 */     return result;
/*      */   }
/*      */   
/*      */   public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
/* 2864 */     Set<byte[]> result = null;
/* 2865 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2866 */     if (jedis == null) {
/* 2867 */       return result;
/*      */     }
/* 2869 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2872 */       result = jedis.zrangeByScore(key, min, max);
/*      */     }
/* 2874 */     catch (Exception e) {
/*      */       
/* 2876 */       LOG.error(e.getMessage(), e);
/* 2877 */       broken = true;
/*      */     } finally {
/*      */       
/* 2880 */       jedis.close();
/*      */     } 
/* 2882 */     return result;
/*      */   }
/*      */   
/*      */   public Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count) {
/* 2886 */     Set<byte[]> result = null;
/* 2887 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2888 */     if (jedis == null) {
/* 2889 */       return result;
/*      */     }
/* 2891 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2894 */       result = jedis.zrangeByScore(key, min, max, offset, count);
/*      */     
/*      */     }
/* 2897 */     catch (Exception e) {
/*      */       
/* 2899 */       LOG.error(e.getMessage(), e);
/* 2900 */       broken = true;
/*      */     } finally {
/*      */       
/* 2903 */       jedis.close();
/*      */     } 
/* 2905 */     return result;
/*      */   }
/*      */   
/*      */   public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
/* 2909 */     Set<Tuple> result = null;
/* 2910 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2911 */     if (jedis == null) {
/* 2912 */       return result;
/*      */     }
/* 2914 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2917 */       result = jedis.zrangeByScoreWithScores(key, min, max);
/*      */     }
/* 2919 */     catch (Exception e) {
/*      */       
/* 2921 */       LOG.error(e.getMessage(), e);
/* 2922 */       broken = true;
/*      */     } finally {
/*      */       
/* 2925 */       jedis.close();
/*      */     } 
/* 2927 */     return result;
/*      */   }
/*      */   
/*      */   public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
/* 2931 */     Set<Tuple> result = null;
/* 2932 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2933 */     if (jedis == null) {
/* 2934 */       return result;
/*      */     }
/* 2936 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2939 */       result = jedis.zrangeByScoreWithScores(key, min, max, offset, count);
/*      */     }
/* 2941 */     catch (Exception e) {
/*      */       
/* 2943 */       LOG.error(e.getMessage(), e);
/* 2944 */       broken = true;
/*      */     } finally {
/*      */       
/* 2947 */       jedis.close();
/*      */     } 
/* 2949 */     return result;
/*      */   }
/*      */   
/*      */   public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
/* 2953 */     Set<byte[]> result = null;
/* 2954 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2955 */     if (jedis == null) {
/* 2956 */       return result;
/*      */     }
/* 2958 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2961 */       result = jedis.zrevrangeByScore(key, max, min);
/*      */     }
/* 2963 */     catch (Exception e) {
/*      */       
/* 2965 */       LOG.error(e.getMessage(), e);
/* 2966 */       broken = true;
/*      */     } finally {
/*      */       
/* 2969 */       jedis.close();
/*      */     } 
/* 2971 */     return result;
/*      */   }
/*      */   
/*      */   public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
/* 2975 */     Set<byte[]> result = null;
/* 2976 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2977 */     if (jedis == null) {
/* 2978 */       return result;
/*      */     }
/* 2980 */     boolean broken = false;
/*      */     
/*      */     try {
/* 2983 */       result = jedis.zrevrangeByScore(key, max, min, offset, count);
/*      */     }
/* 2985 */     catch (Exception e) {
/*      */       
/* 2987 */       LOG.error(e.getMessage(), e);
/* 2988 */       broken = true;
/*      */     } finally {
/*      */       
/* 2991 */       jedis.close();
/*      */     } 
/* 2993 */     return result;
/*      */   }
/*      */   
/*      */   public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
/* 2997 */     Set<Tuple> result = null;
/* 2998 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 2999 */     if (jedis == null) {
/* 3000 */       return result;
/*      */     }
/* 3002 */     boolean broken = false;
/*      */     
/*      */     try {
/* 3005 */       result = jedis.zrevrangeByScoreWithScores(key, max, min);
/*      */     }
/* 3007 */     catch (Exception e) {
/*      */       
/* 3009 */       LOG.error(e.getMessage(), e);
/* 3010 */       broken = true;
/*      */     } finally {
/*      */       
/* 3013 */       jedis.close();
/*      */     } 
/* 3015 */     return result;
/*      */   }
/*      */   
/*      */   public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
/* 3019 */     Set<Tuple> result = null;
/* 3020 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 3021 */     if (jedis == null) {
/* 3022 */       return result;
/*      */     }
/* 3024 */     boolean broken = false;
/*      */     
/*      */     try {
/* 3027 */       result = jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
/*      */     }
/* 3029 */     catch (Exception e) {
/*      */       
/* 3031 */       LOG.error(e.getMessage(), e);
/* 3032 */       broken = true;
/*      */     } finally {
/*      */       
/* 3035 */       jedis.close();
/*      */     } 
/* 3037 */     return result;
/*      */   }
/*      */   
/*      */   public Long zremrangeByRank(byte[] key, int start, int end) {
/* 3041 */     Long result = null;
/* 3042 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 3043 */     if (jedis == null) {
/* 3044 */       return result;
/*      */     }
/* 3046 */     boolean broken = false;
/*      */     
/*      */     try {
/* 3049 */       result = jedis.zremrangeByRank(key, start, end);
/*      */     }
/* 3051 */     catch (Exception e) {
/*      */       
/* 3053 */       LOG.error(e.getMessage(), e);
/* 3054 */       broken = true;
/*      */     } finally {
/*      */       
/* 3057 */       jedis.close();
/*      */     } 
/*      */     
/* 3060 */     return result;
/*      */   }
/*      */   
/*      */   public Long zremrangeByScore(byte[] key, double start, double end) {
/* 3064 */     Long result = null;
/* 3065 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 3066 */     if (jedis == null) {
/* 3067 */       return result;
/*      */     }
/* 3069 */     boolean broken = false;
/*      */     
/*      */     try {
/* 3072 */       result = jedis.zremrangeByScore(key, start, end);
/*      */     }
/* 3074 */     catch (Exception e) {
/* 3075 */       LOG.error(e.getMessage(), e);
/* 3076 */       broken = true;
/*      */     } finally {
/*      */       
/* 3079 */       jedis.close();
/*      */     } 
/* 3081 */     return result;
/*      */   }
/*      */   
/*      */   public Long linsert(byte[] key, BinaryClient.LIST_POSITION where, byte[] pivot, byte[] value) {
/* 3085 */     Long result = null;
/* 3086 */     Jedis jedis = this.redisDataSource.getRedisClient();
/* 3087 */     if (jedis == null) {
/* 3088 */       return result;
/*      */     }
/* 3090 */     boolean broken = false;
/*      */     
/*      */     try {
/* 3093 */       result = jedis.linsert(key, where, pivot, value);
/*      */     }
/* 3095 */     catch (Exception e) {
/* 3096 */       LOG.error(e.getMessage(), e);
/* 3097 */       broken = true;
/*      */     } finally {
/*      */       
/* 3100 */       jedis.close();
/*      */     } 
/* 3102 */     return result;
/*      */   }
/*      */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\redis\RedisClientTemplate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */