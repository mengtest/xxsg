/*     */ package com.linlongyx.sanguo.webgame.app.mental;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.proxy.IEntityProxy;
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.MentalParamter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MentalRankStruct;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MentalShowStruct;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.rank.RankingLevel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MentalRankComponent
/*     */   extends AbstractComponent<MentalRankEntity>
/*     */ {
/*  25 */   MentalParamter mentalParamter = (MentalParamter)ParameterConstant.getParameter(58);
/*     */   
/*     */   public MentalRankComponent(long playerId) {
/*  28 */     super(MentalRankEntity.class);
/*  29 */     this.playerId = 1L;
/*  30 */     makeKey();
/*  31 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  36 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  41 */     setPlayerId(1L);
/*  42 */     this.proxy.setEntityStatus(IEntityProxy.ENTITY_STATUS.STATUS_ADD);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  47 */     if (time == 0 && 
/*  48 */       TimeUtil.getWeek() == 1) {
/*  49 */       getRankList().clear();
/*     */       
/*  51 */       setUpdate("rankList");
/*     */     } 
/*     */ 
/*     */     
/*  55 */     return true;
/*     */   }
/*     */   
/*     */   public synchronized void sortRank() {
/*  59 */     List<MentalRankStruct> list = getRankList();
/*  60 */     Collections.sort(list, (o1, o2) -> o2.point - o1.point);
/*  61 */     MentalParamter mentalParamter = (MentalParamter)ParameterConstant.getParameter(58);
/*  62 */     int size = list.size();
/*  63 */     if (size > mentalParamter.getRankNumber()) {
/*  64 */       list.remove(size - 1);
/*     */       
/*  66 */       setUpdate("rankList");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinPoint() {
/*  77 */     List<MentalRankStruct> ranks = getRankList();
/*  78 */     if (ranks.size() < 10) {
/*  79 */       return 0;
/*     */     }
/*     */     
/*  82 */     int point = Integer.MAX_VALUE;
/*  83 */     for (MentalRankStruct info : ranks) {
/*  84 */       if (point > info.point) {
/*  85 */         point = info.point;
/*     */       }
/*     */     } 
/*  88 */     if (point == Integer.MAX_VALUE)
/*  89 */       point = 0; 
/*  90 */     return point;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addShowRecord(String playerName, ArrayList<Reward> rewards) {
/* 101 */     MentalShowStruct struct = new MentalShowStruct();
/* 102 */     struct.playerName = playerName;
/* 103 */     struct.rewards = rewards;
/*     */     
/* 105 */     List<MentalShowStruct> showList = getShowList();
/* 106 */     if (showList.size() == this.mentalParamter.getPannelRecord()) {
/* 107 */       showList.remove(0);
/*     */     }
/* 109 */     showList.add(struct);
/* 110 */     setUpdate("showList");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLevel(int actId) {
/* 119 */     if (((Integer)getLevelMap().getOrDefault(Integer.valueOf(actId), Integer.valueOf(0))).intValue() == 0) {
/* 120 */       BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 121 */       ArrayList<Integer> list = new ArrayList<>(bossHomeParameter.getLevelBoss().keySet());
/* 122 */       list.sort(Integer::compareTo);
/* 123 */       for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int level = ((Integer)iterator.next()).intValue();
/* 124 */         if (RankingLevel.getWorldLevel() <= level) {
/* 125 */           getLevelMap().put(Integer.valueOf(actId), Integer.valueOf(level));
/*     */           break;
/*     */         }  }
/*     */       
/* 129 */       setUpdate("levelMap");
/*     */     } 
/* 131 */     return ((Integer)getLevelMap().get(Integer.valueOf(actId))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLevels(int actId) {
/* 140 */     getLevelMap().put(Integer.valueOf(actId), Integer.valueOf(0));
/* 141 */     setLevelMap(getLevelMap());
/* 142 */     setUpdate("levelMap");
/*     */   }
/*     */   
/*     */   public int getWorldLevel(int actId) {
/* 146 */     return ((Integer)getLevelMap().getOrDefault(Integer.valueOf(actId), Integer.valueOf(0))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getRevertShowList(List<MentalShowStruct> list) {
/* 155 */     List<MentalShowStruct> showList = getShowList();
/* 156 */     for (int i = showList.size() - 1; i >= 0; i--) {
/* 157 */       list.add(showList.get(i));
/*     */     }
/*     */   }
/*     */   
/*     */   public List<MentalShowStruct> getShowList() {
/* 162 */     return ((MentalRankEntity)getEntity()).getShowList();
/*     */   }
/*     */   
/*     */   public void setShowList(List<MentalShowStruct> showList) {
/* 166 */     ((MentalRankEntity)getEntity()).setShowList(showList);
/*     */   }
/*     */   
/*     */   public List<MentalRankStruct> getRankList() {
/* 170 */     return ((MentalRankEntity)getEntity()).getRankList();
/*     */   }
/*     */   
/*     */   public void setRankList(List<MentalRankStruct> rankList) {
/* 174 */     ((MentalRankEntity)getEntity()).setRankList(rankList);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getLevelMap() {
/* 178 */     return ((MentalRankEntity)getEntity()).getLevelMap();
/*     */   }
/*     */   
/*     */   public void setLevelMap(Map<Integer, Integer> levelMap) {
/* 182 */     ((MentalRankEntity)getEntity()).setLevelMap(levelMap);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\mental\MentalRankComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */