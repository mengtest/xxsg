/*     */ package com.linlongyx.sanguo.webgame.processors.rankAct;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.IDAO;
/*     */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.sanguo.webgame.app.rankAct.RankActComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RankingActivityBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RankingActivitylistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RankActParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.RankActType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.rank.RankingUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.rankAct.comparator.RankType1Comparator;
/*     */ import com.linlongyx.sanguo.webgame.processors.rankAct.comparator.RankType2Comparator;
/*     */ import com.linlongyx.sanguo.webgame.processors.rankAct.comparator.RankType3Comparator;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RankAct
/*     */ {
/*     */   private volatile RankActState state;
/*     */   private int id;
/*     */   private int type;
/*  39 */   private List<RankingData> rankList = new CopyOnWriteArrayList<>();
/*     */   
/*     */   public RankAct(RankActState state, int id, int type) {
/*  42 */     this.state = state;
/*  43 */     this.id = id;
/*  44 */     this.type = type;
/*     */   }
/*     */   
/*     */   public ArrayList<RankingData> getRankList() {
/*  48 */     RankingActivityBean rankingActivityBean = (RankingActivityBean)JsonTableService.getJsonData(this.id, RankingActivityBean.class);
/*     */     
/*  50 */     if (this.state == RankActState.Expire && this.rankList.size() > 0) {
/*  51 */       return new ArrayList<>(this.rankList);
/*     */     }
/*  53 */     if (this.state == RankActState.NotOpen) {
/*  54 */       return new ArrayList<>();
/*     */     }
/*     */     
/*  57 */     RankActType rankActType = RankActType.getRankActType(rankingActivityBean.getType());
/*  58 */     if (null == rankActType) {
/*  59 */       return new ArrayList<>();
/*     */     }
/*  61 */     RankActParameter rankActParameter = (RankActParameter)ParameterConstant.getParameter(20);
/*  62 */     RankingActivitylistBean rankingActivitylistBean = (RankingActivitylistBean)JsonTableService.getJsonData(rankingActivityBean.getEntryConditions(), RankingActivitylistBean.class);
/*  63 */     int enterNum = (null != rankingActivitylistBean) ? Integer.valueOf(rankingActivitylistBean.getTarget()).intValue() : 0;
/*  64 */     if (AppContext.getDAO().getType() == IDAO.TYPE.MYSQL) {
/*     */       List<Map<String, Object>> info; try {
/*     */         String selectSql;
/*  67 */         MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/*  68 */         JdbcTemplate template = mysql.getTemplate();
/*     */         
/*  70 */         if (rankActType.isDesc()) {
/*     */           
/*  72 */           selectSql = "SELECT distinct p.playerId,p.playerName,p.level,p.totalValue,p.head,p.sex,p.vip,p.wearTitle,p.wearFashion,p.quality,act.value FROM tb_player p, tb_rankAct act  where p.playerId = act.playerId and act.id = " + this.id + " and act.value >= " + enterNum + " ORDER BY act.value desc LIMIT " + rankingActivityBean.getPlayerNum();
/*     */         } else {
/*     */           
/*  75 */           selectSql = "SELECT distinct p.playerId,p.playerName,p.level,p.totalValue,p.head,p.sex,p.vip,p.wearTitle,p.wearFashion,p.quality,act.value FROM tb_player p, tb_rankAct act  where p.playerId = act.playerId and act.id = " + this.id + " and act.value > 0 and act.value <= " + enterNum + " ORDER BY act.value LIMIT " + rankingActivityBean.getPlayerNum();
/*     */         } 
/*     */         
/*  78 */         info = template.queryForList(selectSql);
/*  79 */       } catch (Exception e) {
/*  80 */         LogUtil.errorLog(new Object[] { "RankActUtil::getList", Arrays.toString((Object[])e.getStackTrace()) });
/*  81 */         return new ArrayList<>();
/*     */       } 
/*     */       
/*  84 */       if (info.size() == 0) {
/*  85 */         return new ArrayList<>();
/*     */       }
/*  87 */       this.rankList.clear();
/*  88 */       for (Map<String, Object> map : info) {
/*  89 */         Long value = Long.valueOf(((Long)map.get("value")).longValue());
/*  90 */         Long playerId = Long.valueOf(((Long)map.get("playerId")).longValue());
/*  91 */         String playerName = (String)map.get("playerName");
/*  92 */         int level = ((Integer)map.get("level")).intValue();
/*  93 */         long totalValue = ((Long)map.get("totalValue")).longValue();
/*  94 */         String head = (String)map.get("head");
/*  95 */         byte vip = Byte.parseByte(map.get("vip").toString());
/*  96 */         byte sex = Byte.parseByte(map.get("sex").toString());
/*  97 */         int wearTitle = Integer.parseInt(map.get("wearTitle").toString());
/*  98 */         int wearFashion = Integer.parseInt(map.get("wearFashion").toString());
/*  99 */         int quality = Integer.parseInt(map.get("quality").toString());
/* 100 */         this.rankList.add(RankingUtil.transform(playerId.longValue(), playerName, totalValue, level, head, vip, sex, wearTitle, wearFashion, quality, value.longValue(), 0));
/*     */       } 
/* 102 */       if (rankActType.getOrder() == 2) {
/* 103 */         this.rankList.sort((Comparator<? super RankingData>)new RankType2Comparator());
/* 104 */       } else if (rankActType.getOrder() == 3) {
/* 105 */         this.rankList.sort((Comparator<? super RankingData>)new RankType3Comparator());
/*     */       } else {
/* 107 */         this.rankList.sort((Comparator<? super RankingData>)new RankType1Comparator());
/*     */       } 
/*     */       
/* 110 */       int rank = 1;
/* 111 */       for (RankingData rankingData : this.rankList) {
/* 112 */         rankingData.rank = rank++;
/*     */       }
/* 114 */       int size = this.rankList.size();
/* 115 */       int index = 0;
/* 116 */       for (Integer itemId : rankingActivityBean.getRankReward()) {
/* 117 */         List<Integer> range = rankActParameter.getRange(itemId.intValue());
/* 118 */         if (null == range || 3 != range.size()) {
/*     */           continue;
/*     */         }
/* 121 */         int max = ((Integer)range.get(0)).intValue();
/* 122 */         int min = ((Integer)range.get(1)).intValue();
/* 123 */         int condition = ((Integer)range.get(2)).intValue();
/*     */         
/* 125 */         for (int j = 0; j <= 10000 && 
/* 126 */           index <= size - 1 && max <= min; j++) {
/*     */ 
/*     */           
/* 129 */           RankingData rankingData = this.rankList.get(index);
/* 130 */           if (rankActType.isDesc()) {
/* 131 */             if (rankingData.value >= condition) {
/* 132 */               rankingData.rank = max++;
/* 133 */               index++;
/*     */             } else {
/*     */               
/*     */               break;
/*     */             } 
/* 138 */           } else if (rankingData.value <= condition) {
/* 139 */             rankingData.rank = max++;
/* 140 */             index++;
/*     */           } else {
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 147 */       for (int i = size - 1; i >= index; i--) {
/* 148 */         this.rankList.remove(i);
/*     */       }
/*     */     } 
/* 151 */     return new ArrayList<>(this.rankList);
/*     */   }
/*     */ 
/*     */   
/*     */   public void balance() {
/* 156 */     if (this.state != RankActState.Expire) {
/*     */       return;
/*     */     }
/* 159 */     LookUpService.getOnlinePlayer().forEach(playerId -> {
/*     */           IPlayer player = LookUpService.getByPlayerId(playerId.longValue());
/*     */           RankActComponent rankActComponent = (RankActComponent)player.createIfNotExist(RankActComponent.class);
/*     */           rankActComponent.balanceRankAct(this.id);
/*     */         });
/*     */   }
/*     */   
/*     */   public int getRankByPlayerId(long playerId) {
/* 167 */     if (this.rankList.isEmpty() && 
/* 168 */       getRankList().isEmpty()) {
/* 169 */       return 0;
/*     */     }
/*     */     
/* 172 */     for (RankingData rankingData : this.rankList) {
/* 173 */       if (rankingData.playerId == playerId) {
/* 174 */         return rankingData.rank;
/*     */       }
/*     */     } 
/* 177 */     return 0;
/*     */   }
/*     */   
/*     */   public enum RankActState {
/* 181 */     NotOpen(0),
/* 182 */     Opening(1),
/* 183 */     Expire(2);
/*     */     
/*     */     int state;
/*     */     
/*     */     RankActState(int state) {
/* 188 */       this.state = state;
/*     */     }
/*     */     
/*     */     public int getState() {
/* 192 */       return this.state;
/*     */     }
/*     */   }
/*     */   
/*     */   public RankActState getState() {
/* 197 */     return this.state;
/*     */   }
/*     */   
/*     */   public void setState(RankActState state) {
/* 201 */     this.state = state;
/*     */   }
/*     */   
/*     */   public int getId() {
/* 205 */     return this.id;
/*     */   }
/*     */   
/*     */   public int getType() {
/* 209 */     return this.type;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rankAct\RankAct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */