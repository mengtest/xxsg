/*     */ package com.linlongyx.sanguo.webgame.processors.unparalleled;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.UnparalleledRankInfo;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ 
/*     */ public class UnparalleledUtil
/*     */ {
/*  19 */   private static final Object fight_lock = new Object();
/*  20 */   private static ArrayList<UnparalleledRankInfo> rankInfos = new ArrayList<>();
/*     */ 
/*     */   
/*     */   private static final int RANK_NUM = 50;
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initFromDB() {
/*     */     try {
/*  29 */       MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/*  30 */       JdbcTemplate template = mysql.getTemplate();
/*  31 */       String selectSql = "SELECT distinct u.playerId,p.playerName,u.lastMaxStar,p.totalValue,p.head FROM tb_player p, tb_unparalleled u where p.playerId = u.playerId and u.lastMaxStar > 0 ORDER BY u.lastMaxStar DESC,u.lastPassTime ASC limit 50";
/*     */ 
/*     */       
/*  34 */       List<Map<String, Object>> info = template.queryForList(selectSql);
/*  35 */       int rank = 0;
/*  36 */       for (Map<String, Object> map : info) {
/*  37 */         UnparalleledRankInfo rankInfo = new UnparalleledRankInfo();
/*  38 */         long playerId = ((Long)map.get("playerId")).longValue();
/*     */         
/*  40 */         rankInfo.rank = ++rank;
/*  41 */         rankInfo.playerId = playerId;
/*  42 */         rankInfo.playerName = (String)map.get("playerName");
/*  43 */         rankInfo.fightValuye = ((Long)map.get("totalValue")).longValue();
/*  44 */         rankInfo.stars = ((Integer)map.get("lastMaxStar")).intValue();
/*  45 */         rankInfo.head = (String)map.get("head");
/*  46 */         rankInfos.add(rankInfo);
/*     */       } 
/*  48 */     } catch (Exception e) {
/*  49 */       LogUtil.errorLog(new Object[] { "UnparalleledUtil::initFromDB", Arrays.toString((Object[])e.getStackTrace()) });
/*  50 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateRank(IPlayerSession playerSession, int star, String playerName, long fightValue) {
/*  58 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  59 */     synchronized (fight_lock) {
/*  60 */       boolean isHash = false;
/*  61 */       for (UnparalleledRankInfo rankInfo : rankInfos) {
/*  62 */         if (rankInfo.playerId == playerSession.getPlayer().getPlayerId()) {
/*  63 */           rankInfo.stars = star;
/*  64 */           isHash = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*  68 */       if (!isHash) {
/*  69 */         rankInfos.add(tramformRankInfo(playerComponent.getPlayerId(), star, playerName, fightValue, playerComponent.getHead(), rankInfos.size() + 1));
/*     */       }
/*  71 */       rankInfos.sort(new SortCard());
/*  72 */       int rank = 0;
/*  73 */       for (UnparalleledRankInfo rankInfo : rankInfos)
/*     */       {
/*  75 */         rankInfo.rank = ++rank;
/*     */       }
/*  77 */       if (rankInfos.size() > 50) {
/*  78 */         rankInfos.remove(rankInfos.size() - 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<UnparalleledRankInfo> GetRankInfos() {
/*  87 */     synchronized (fight_lock) {
/*  88 */       return rankInfos;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int GetRank(long playerId) {
/*  98 */     int rank = 0;
/*  99 */     synchronized (fight_lock) {
/* 100 */       for (UnparalleledRankInfo rankInfo : rankInfos) {
/* 101 */         if (rankInfo.playerId == playerId) {
/* 102 */           rank = rankInfo.rank;
/*     */         }
/*     */       } 
/* 105 */       return rank;
/*     */     } 
/*     */   }
/*     */   
/*     */   static class SortCard
/*     */     implements Comparator<UnparalleledRankInfo>
/*     */   {
/*     */     public int compare(UnparalleledRankInfo o1, UnparalleledRankInfo o2) {
/* 113 */       if (o1.stars > o2.stars)
/* 114 */         return -1; 
/* 115 */       if (o1.stars < o2.stars) {
/* 116 */         return 1;
/*     */       }
/* 118 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static UnparalleledRankInfo tramformRankInfo(long playerId, int star, String playerName, long fightValue, String head, int rank) {
/* 124 */     UnparalleledRankInfo rankInfo = new UnparalleledRankInfo();
/* 125 */     rankInfo.playerId = playerId;
/* 126 */     rankInfo.stars = star;
/* 127 */     rankInfo.fightValuye = fightValue;
/* 128 */     rankInfo.playerName = playerName;
/* 129 */     rankInfo.rank = rank;
/* 130 */     rankInfo.head = head;
/* 131 */     return rankInfo;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processor\\unparalleled\UnparalleledUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */