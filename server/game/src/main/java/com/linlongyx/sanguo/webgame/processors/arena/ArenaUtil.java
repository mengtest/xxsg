/*     */ package com.linlongyx.sanguo.webgame.processors.arena;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.arena.ArenaComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.mounts.MountsComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ArenaRobotBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ArenaRuleBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ArenaParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ArenaData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ public class ArenaUtil {
/*  27 */   private static final Object fight_lock = new Object();
/*  28 */   private static Map<Integer, Long> ranks = new HashMap<>();
/*  29 */   private static Map<Integer, Integer> fightingRanks = new HashMap<>();
/*     */   private static final int MAX_FIGHT_SEC = 30;
/*  31 */   private static Map<Long, ArenaData> players = new HashMap<>();
/*     */   public static void refresh() {
/*     */     List<Map<String, Object>> info;
/*  34 */     ArenaParameter arenaParameter = (ArenaParameter)ParameterConstant.getParameter(17);
/*     */     
/*     */     try {
/*  37 */       MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/*  38 */       JdbcTemplate template = mysql.getTemplate();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  43 */       String selectSql = "SELECT distinct p.playerId,p.leaderId, p.playerName,p.totalValue, p.sex, p.wearTitle, p.wearFashion, arank.rank, m.mountsId FROM tb_player p INNER JOIN tb_arena arank ON p.playerId = arank.playerId AND arank.rank != 0 LEFT JOIN tb_mounts m ON p.playerId=m.playerId AND m.battle=1 AND arank.rank < " + arenaParameter.getDefaultRank() + " ORDER BY `rank` ASC limit " + arenaParameter.getDefaultRank();
/*  44 */       info = template.queryForList(selectSql);
/*  45 */     } catch (Exception e) {
/*  46 */       LogUtils.errorLog(new Object[] { "ArenaRankService refresh failure", Arrays.toString((Object[])e.getStackTrace()) });
/*  47 */       e.printStackTrace();
/*     */       return;
/*     */     } 
/*  50 */     Set<ArenaData> set = new HashSet<>();
/*  51 */     synchronized (fight_lock) {
/*  52 */       ranks.clear();
/*  53 */       players.clear();
/*  54 */       for (Map<String, Object> map : info) {
/*  55 */         Long playerId = Long.valueOf(((Long)map.get("playerId")).longValue());
/*  56 */         String playerName = (String)map.get("playerName");
/*  57 */         long fightValue = ((Long)map.get("totalValue")).longValue();
/*  58 */         int rank = ((Integer)map.get("rank")).intValue();
/*  59 */         int leaderId = ((Integer)map.get("leaderId")).intValue();
/*  60 */         int sex = ((Integer)map.get("sex")).intValue();
/*  61 */         int title = ((Integer)map.get("wearTitle")).intValue();
/*  62 */         int fashion = ((Integer)map.get("wearFashion")).intValue();
/*  63 */         Object mounts = map.get("mountsId");
/*  64 */         ArenaData arenaData = new ArenaData();
/*  65 */         transformPlayer(playerId.longValue(), leaderId, playerName, rank, fightValue, (byte)sex, title, fashion, (mounts == null) ? 0 : Integer.parseInt(mounts.toString()), false, arenaData);
/*  66 */         if (ranks.containsKey(Integer.valueOf(rank))) {
/*  67 */           long oldPlayerId = ((Long)ranks.get(Integer.valueOf(rank))).longValue();
/*  68 */           if (players.containsKey(Long.valueOf(oldPlayerId))) {
/*  69 */             ArenaData arenaPlayerData_old = players.get(Long.valueOf(oldPlayerId));
/*  70 */             refreshPlayerTotalFightValue(playerId.longValue(), arenaPlayerData_old);
/*  71 */             if (arenaPlayerData_old.fightValue > arenaData.fightValue) {
/*  72 */               set.add(arenaData);
/*     */               continue;
/*     */             } 
/*  75 */             set.add(arenaPlayerData_old);
/*     */           } 
/*     */           
/*  78 */           LogUtils.errorLog(new Object[] { "ArenaRankError", Long.valueOf(oldPlayerId), Long.valueOf(arenaData.playerId), Integer.valueOf(rank) });
/*     */         } 
/*  80 */         ranks.put(Integer.valueOf(rank), playerId);
/*  81 */         players.put(playerId, arenaData);
/*     */       } 
/*  83 */       if (set.size() > 0) {
/*  84 */         for (ArenaData arenaData : set) {
/*  85 */           int oldPos = arenaData.rank;
/*  86 */           for (int i = oldPos; i <= arenaParameter.getDefaultRank(); i++) {
/*  87 */             if (!ranks.containsKey(Integer.valueOf(i))) {
/*  88 */               arenaData.rank = i;
/*  89 */               ArenaComponent arenaComponent = (ArenaComponent)LookUpService.getComponent(arenaData.playerId, ArenaComponent.class);
/*  90 */               if (null != arenaComponent) {
/*  91 */                 arenaComponent.setRank(i);
/*  92 */                 arenaComponent.saveToDB();
/*  93 */                 ranks.put(Integer.valueOf(i), Long.valueOf(arenaData.playerId));
/*  94 */                 players.put(Long.valueOf(arenaData.playerId), arenaData);
/*  95 */                 LogUtils.errorLog(new Object[] { "ArenaAutoExchange", Long.valueOf(arenaData.playerId), Integer.valueOf(oldPos), Integer.valueOf(i) });
/*     */               } 
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Map<Integer, Long> getRanks() {
/* 106 */     synchronized (fight_lock) {
/* 107 */       return ranks;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArenaData transformPlayer(long playerId, int objectId, String playerName, int rank, long fightValue, int sex, int title, int fashionId, int mounts, boolean isRobot, ArenaData arenaData) {
/* 124 */     ArenaParameter arenaParameter = (ArenaParameter)ParameterConstant.getParameter(17);
/* 125 */     arenaData.playerId = playerId;
/* 126 */     arenaData.objectId = objectId;
/* 127 */     arenaData.playerName = playerName;
/* 128 */     arenaData.rank = (rank == 0) ? arenaParameter.getDefaultRank() : rank;
/* 129 */     arenaData.fightValue = fightValue;
/* 130 */     arenaData.sex = sex;
/* 131 */     arenaData.isRobot = isRobot;
/* 132 */     arenaData.title = title;
/* 133 */     arenaData.fashionId = fashionId;
/* 134 */     arenaData.mounts = mounts;
/* 135 */     return arenaData;
/*     */   }
/*     */   
/*     */   private static void refreshPlayerTotalFightValue(long playerId, ArenaData arenaData) {
/* 139 */     PlayerComponent playerComponent = (PlayerComponent)LookUpService.getOnlineComponent(playerId, PlayerComponent.class);
/* 140 */     MountsComponent mountsComponent = (MountsComponent)LookUpService.getOnlineComponent(playerId, MountsComponent.class);
/* 141 */     if (playerComponent != null) {
/* 142 */       arenaData.fightValue = playerComponent.getTotalValue();
/* 143 */       arenaData.playerName = playerComponent.getPlayerName();
/* 144 */       arenaData.sex = playerComponent.getSex();
/* 145 */       arenaData.title = playerComponent.getWearTitle();
/* 146 */       arenaData.fashionId = playerComponent.getWearFashion();
/* 147 */       if (null != mountsComponent) {
/* 148 */         arenaData.mounts = mountsComponent.getTurnMounts();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArenaData getArenaData(long playerId) {
/* 159 */     synchronized (fight_lock) {
/* 160 */       if (players.containsKey(Long.valueOf(playerId))) {
/* 161 */         ArenaData arenaData1 = players.get(Long.valueOf(playerId));
/* 162 */         refreshPlayerTotalFightValue(playerId, arenaData1);
/* 163 */         return arenaData1;
/*     */       } 
/*     */     } 
/* 166 */     PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/* 167 */     ArenaComponent arenaComponent = (ArenaComponent)LookUpService.getComponent(playerId, ArenaComponent.class);
/* 168 */     MountsComponent mountsComponent = (MountsComponent)LookUpService.getComponent(playerId, MountsComponent.class);
/* 169 */     if (null == playerComponent || null == arenaComponent || mountsComponent == null) {
/* 170 */       return null;
/*     */     }
/*     */     
/* 173 */     ArenaData arenaData = new ArenaData();
/* 174 */     transformPlayer(playerId, playerComponent.getLeaderId(), playerComponent.getPlayerName(), arenaComponent.getRank(), playerComponent
/* 175 */         .getTotalValue(), playerComponent.getSex(), playerComponent.getWearTitle(), playerComponent.getWearFashion(), mountsComponent.getTurnMounts(), false, arenaData);
/* 176 */     synchronized (fight_lock) {
/* 177 */       players.put(Long.valueOf(playerId), arenaData);
/*     */     } 
/* 179 */     return arenaData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArenaData getArenaData(int rank, ArenaRuleBean arenaRuleBean) {
/* 189 */     synchronized (fight_lock) {
/* 190 */       if (ranks.containsKey(Integer.valueOf(rank))) {
/* 191 */         long playerId = ((Long)ranks.get(Integer.valueOf(rank))).longValue();
/* 192 */         if (players.containsKey(Long.valueOf(playerId))) {
/* 193 */           ArenaData arenaData1 = players.get(Long.valueOf(playerId));
/* 194 */           refreshPlayerTotalFightValue(playerId, arenaData1);
/* 195 */           return arenaData1;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 200 */     ArenaData arenaData = new ArenaData();
/* 201 */     int size = arenaRuleBean.getRebotFighter().size();
/* 202 */     if (size == 0) {
/* 203 */       return null;
/*     */     }
/* 205 */     int index = RandUtil.randNum(size - 1);
/* 206 */     int id = ((Integer)arenaRuleBean.getRebotFighter().get(index)).intValue();
/* 207 */     arenaData.objectId = id;
/* 208 */     ArenaRobotBean arenaRobotBean = (ArenaRobotBean)JsonTableService.getJsonData(id, ArenaRobotBean.class);
/* 209 */     if (null == arenaRobotBean) {
/* 210 */       return null;
/*     */     }
/* 212 */     arenaData.playerName = arenaRobotBean.getRobotName();
/* 213 */     arenaData.fightValue = arenaRobotBean.getRoboteff();
/* 214 */     arenaData.rank = rank;
/* 215 */     arenaData.sex = randRobotSex();
/* 216 */     arenaData.isRobot = true;
/*     */     
/* 218 */     return arenaData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int randRobotSex() {
/* 226 */     return RandUtil.isRandTrue() ? 1 : 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short getTargetArenaPlayerData(int myRank, int targetRank, ArenaData targetArenaData, Map<Integer, Integer> showMap) {
/* 237 */     int time = TimeUtil.currentTime();
/* 238 */     synchronized (fight_lock) {
/* 239 */       if (fightingRanks.containsKey(Integer.valueOf(myRank))) {
/* 240 */         if (time - ((Integer)fightingRanks.get(Integer.valueOf(myRank))).intValue() > 30) {
/* 241 */           fightingRanks.remove(Integer.valueOf(myRank));
/*     */         } else {
/* 243 */           return 11706;
/*     */         } 
/*     */       }
/* 246 */       if (fightingRanks.containsKey(Integer.valueOf(targetRank))) {
/* 247 */         if (time - ((Integer)fightingRanks.get(Integer.valueOf(targetRank))).intValue() > 30) {
/* 248 */           fightingRanks.remove(Integer.valueOf(targetRank));
/*     */         } else {
/* 250 */           return 11706;
/*     */         } 
/*     */       }
/*     */       
/* 254 */       if (ranks.containsKey(Integer.valueOf(targetRank))) {
/* 255 */         long playerId = ((Long)ranks.get(Integer.valueOf(targetRank))).longValue();
/* 256 */         ArenaData arenaPlayerData = players.get(Long.valueOf(playerId));
/* 257 */         refreshPlayerTotalFightValue(playerId, arenaPlayerData);
/*     */         
/* 259 */         targetArenaData.playerId = arenaPlayerData.playerId;
/* 260 */         targetArenaData.objectId = arenaPlayerData.objectId;
/* 261 */         targetArenaData.playerName = arenaPlayerData.playerName;
/* 262 */         targetArenaData.rank = arenaPlayerData.rank;
/* 263 */         targetArenaData.fightValue = arenaPlayerData.fightValue;
/* 264 */         targetArenaData.sex = arenaPlayerData.sex;
/* 265 */         targetArenaData.title = arenaPlayerData.title;
/* 266 */         targetArenaData.isRobot = arenaPlayerData.isRobot;
/* 267 */         targetArenaData.mounts = arenaPlayerData.mounts;
/*     */       } else {
/* 269 */         if (!showMap.containsKey(Integer.valueOf(targetRank))) {
/* 270 */           return 11709;
/*     */         }
/* 272 */         ArenaRobotBean arenaRobotBean = (ArenaRobotBean)JsonTableService.getJsonData(((Integer)showMap.get(Integer.valueOf(targetRank))).intValue(), ArenaRobotBean.class);
/* 273 */         if (null == arenaRobotBean) {
/* 274 */           return 11701;
/*     */         }
/* 276 */         transformPlayer(0L, arenaRobotBean.getRobotId(), arenaRobotBean.getRobotName(), targetRank, arenaRobotBean.getRoboteff(), randRobotSex(), 0, 0, 0, true, targetArenaData);
/*     */       } 
/*     */       
/* 279 */       fightingRanks.put(Integer.valueOf(myRank), Integer.valueOf(time));
/* 280 */       fightingRanks.put(Integer.valueOf(targetRank), Integer.valueOf(time));
/*     */     } 
/* 282 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArenaRuleBean getArenaRuleBean(int rank) {
/* 291 */     Map<Integer, Object> map = JsonTableService.getJsonTable(ArenaRuleBean.class);
/* 292 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 293 */       ArenaRuleBean arenaRuleBean = (ArenaRuleBean)entry.getValue();
/* 294 */       if (arenaRuleBean.getHighId() <= rank && rank <= arenaRuleBean.getLowId()) {
/* 295 */         return arenaRuleBean;
/*     */       }
/*     */     } 
/* 298 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void releaseRanks(int myRank, int targetRank) {
/* 307 */     synchronized (fight_lock) {
/* 308 */       fightingRanks.remove(Integer.valueOf(myRank));
/* 309 */       fightingRanks.remove(Integer.valueOf(targetRank));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateRank(ArenaData arenaData) {
/* 318 */     if (arenaData.isRobot) {
/*     */       return;
/*     */     }
/* 321 */     synchronized (fight_lock) {
/* 322 */       ranks.put(Integer.valueOf(arenaData.rank), Long.valueOf(arenaData.playerId));
/* 323 */       players.put(Long.valueOf(arenaData.playerId), arenaData);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removeRank(int rank) {
/* 332 */     synchronized (fight_lock) {
/* 333 */       ranks.remove(Integer.valueOf(rank));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\arena\ArenaUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */