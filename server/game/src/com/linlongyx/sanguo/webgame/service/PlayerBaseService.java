/*     */ package com.linlongyx.sanguo.webgame.service;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*     */ import com.linlongyx.core.framework.dao.redis.RedisDAO;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import redis.clients.jedis.Jedis;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerBaseService
/*     */ {
/*  25 */   private Map<String, Long> nameToPlayerId = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  31 */     if (AppContext.getDAO() instanceof RedisDAO) {
/*  32 */       RedisDAO redis = (RedisDAO)AppContext.getDAO();
/*  33 */       Jedis jedis = redis.getTemplate().getRedisDataSource().getRedisClient();
/*  34 */       Set<String> set = jedis.keys("player_*");
/*  35 */       for (String string : set) {
/*  36 */         String[] keys = string.split("_");
/*  37 */         Long playerId = Long.valueOf(Long.parseLong(keys[2]));
/*  38 */         String playerName = jedis.hget(string, "playerName");
/*  39 */         this.nameToPlayerId.put(playerName, playerId);
/*     */       }
/*     */     
/*  42 */     } else if (AppContext.getDAO() instanceof MysqlDAO) {
/*  43 */       MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/*  44 */       JdbcTemplate template = mysql.getTemplate();
/*  45 */       String selectSql = "SELECT playerId,playerName FROM tb_player";
/*     */       
/*  47 */       List<Map<String, Object>> info = template.queryForList(selectSql);
/*  48 */       for (Map<String, Object> map : info) {
/*  49 */         Long playerId = Long.valueOf(((Long)map.get("playerId")).longValue());
/*  50 */         String playerName = (String)map.get("playerName");
/*  51 */         this.nameToPlayerId.put(playerName, playerId);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<Long> searchLikeName(String likeName) {
/*  57 */     List<Long> list = new ArrayList<>();
/*  58 */     list.addAll((Collection<? extends Long>)this.nameToPlayerId.entrySet().stream()
/*  59 */         .filter(entry -> ((String)entry.getKey()).contains(likeName))
/*  60 */         .map(entry -> (Long)entry.getValue())
/*  61 */         .collect(Collectors.toList()));
/*  62 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkName(String name) {
/*  73 */     return this.nameToPlayerId.containsKey(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean add(String name, Long playerId) {
/*  84 */     if (this.nameToPlayerId.containsKey(name)) {
/*  85 */       return false;
/*     */     }
/*  87 */     this.nameToPlayerId.put(name, playerId);
/*  88 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(String oldName, String newName) {
/*  98 */     if (this.nameToPlayerId.get(oldName) == null) {
/*     */       return;
/*     */     }
/* 101 */     Long playerId = this.nameToPlayerId.get(oldName);
/* 102 */     this.nameToPlayerId.put(newName, playerId);
/* 103 */     this.nameToPlayerId.remove(oldName);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\PlayerBaseService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */