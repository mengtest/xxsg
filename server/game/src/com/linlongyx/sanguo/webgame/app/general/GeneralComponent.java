/*     */ package com.linlongyx.sanguo.webgame.app.general;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.proxy.IEntityProxy;
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GeneralComponent
/*     */   extends AbstractComponent<GeneralEntity>
/*     */ {
/*     */   private int curChapter;
/*     */   
/*     */   public GeneralComponent(long playerId) {
/*  16 */     super(GeneralEntity.class);
/*  17 */     this.playerId = playerId;
/*  18 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  23 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  28 */     setPlayerId(playerId);
/*  29 */     this.proxy.setEntityStatus(IEntityProxy.ENTITY_STATUS.STATUS_ADD);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  34 */     if (time == 0) {
/*  35 */       setChallenges(new HashMap<>());
/*  36 */       setResetTimes(new HashMap<>());
/*     */       
/*  38 */       setRestore(getRestore() + getBuyTime());
/*     */       
/*  40 */       setBuyTime(0);
/*     */     } 
/*  42 */     return true;
/*     */   }
/*     */   
/*     */   public int getCurChapter() {
/*  46 */     return this.curChapter;
/*     */   }
/*     */   
/*     */   public void setCurChapter(int curChapter) {
/*  50 */     this.curChapter = curChapter;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getChallenges() {
/*  54 */     return ((GeneralEntity)getEntity()).getChallenges();
/*     */   }
/*     */   
/*     */   public void setChallenges(Map<Integer, Integer> challenges) {
/*  58 */     ((GeneralEntity)getEntity()).setChallenges(challenges);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getResetTimes() {
/*  62 */     return ((GeneralEntity)getEntity()).getResetTimes();
/*     */   }
/*     */   
/*     */   public void setResetTimes(Map<Integer, Integer> resetTimes) {
/*  66 */     ((GeneralEntity)getEntity()).setResetTimes(resetTimes);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getStars() {
/*  70 */     return ((GeneralEntity)getEntity()).getStars();
/*     */   }
/*     */   
/*     */   public void setStars(Map<Integer, Integer> stars) {
/*  74 */     ((GeneralEntity)getEntity()).setStars(stars);
/*     */   }
/*     */   
/*     */   public Map<Integer, List<Integer>> getBoxReward() {
/*  78 */     return ((GeneralEntity)getEntity()).getBoxReward();
/*     */   }
/*     */   
/*     */   public void setBoxReward(Map<Integer, List<Integer>> boxReward) {
/*  82 */     ((GeneralEntity)getEntity()).setBoxReward(boxReward);
/*     */   }
/*     */   
/*     */   public int getRestore() {
/*  86 */     return ((GeneralEntity)getEntity()).getRestore();
/*     */   }
/*     */   
/*     */   public void setRestore(int restore) {
/*  90 */     ((GeneralEntity)getEntity()).setRestore(restore);
/*     */   }
/*     */   
/*     */   public int getNextTime() {
/*  94 */     return ((GeneralEntity)getEntity()).getNextTime();
/*     */   }
/*     */   
/*     */   public void setNextTime(int nextTime) {
/*  98 */     ((GeneralEntity)getEntity()).setNextTime(nextTime);
/*     */   }
/*     */   
/*     */   public int getBuyTime() {
/* 102 */     return ((GeneralEntity)getEntity()).getBuyTime();
/*     */   }
/*     */   
/*     */   public void setBuyTime(int buyTime) {
/* 106 */     ((GeneralEntity)getEntity()).setBuyTime(buyTime);
/*     */   }
/*     */   
/*     */   public int getCostOrder() {
/* 110 */     return ((GeneralEntity)getEntity()).getCostOrder();
/*     */   }
/*     */   
/*     */   public void setCostOrder(int costOrder) {
/* 114 */     ((GeneralEntity)getEntity()).setCostOrder(costOrder);
/*     */   }
/*     */   
/*     */   public int getAllStar() {
/* 118 */     return ((GeneralEntity)getEntity()).getAllStar();
/*     */   }
/*     */   
/*     */   public void setAllStar(int allStar) {
/* 122 */     ((GeneralEntity)getEntity()).setAllStar(allStar);
/*     */   }
/*     */   
/*     */   public int getTotalNum() {
/* 126 */     return ((GeneralEntity)getEntity()).getTotalNum();
/*     */   }
/*     */   
/*     */   public void setTotalNum(int totalNum) {
/* 130 */     ((GeneralEntity)getEntity()).setTotalNum(totalNum);
/*     */   }
/*     */   
/*     */   public ArrayList<Long> getAssistInBattle() {
/* 134 */     return ((GeneralEntity)getEntity()).getAssistInBattle();
/*     */   }
/*     */   
/*     */   public void setAssistInBattle(ArrayList<Long> assistInBattle) {
/* 138 */     ((GeneralEntity)getEntity()).setAssistInBattle(assistInBattle);
/*     */   }
/*     */   
/*     */   public int getDifficultyStar() {
/* 142 */     return ((GeneralEntity)getEntity()).getDifficultyStar();
/*     */   }
/*     */   
/*     */   public void setDifficultyStar(int difficultyStar) {
/* 146 */     ((GeneralEntity)getEntity()).setDifficultyStar(difficultyStar);
/*     */   }
/*     */   
/*     */   public int getNightmareStar() {
/* 150 */     return ((GeneralEntity)getEntity()).getNightmareStar();
/*     */   }
/*     */   
/*     */   public void setNightmareStar(int nightmareStar) {
/* 154 */     ((GeneralEntity)getEntity()).setNightmareStar(nightmareStar);
/*     */   }
/*     */   
/*     */   public Map<Integer, List<Integer>> getDiffboxReward() {
/* 158 */     return ((GeneralEntity)getEntity()).getDiffboxReward();
/*     */   }
/*     */   
/*     */   public void setDiffboxReward(Map<Integer, List<Integer>> diffboxReward) {
/* 162 */     ((GeneralEntity)getEntity()).setDiffboxReward(diffboxReward);
/*     */   }
/*     */   
/*     */   public Map<Integer, List<Integer>> getNightboxReward() {
/* 166 */     return ((GeneralEntity)getEntity()).getNightboxReward();
/*     */   }
/*     */   
/*     */   public void setNightboxReward(Map<Integer, List<Integer>> nightboxReward) {
/* 170 */     ((GeneralEntity)getEntity()).setNightboxReward(nightboxReward);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\general\GeneralComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */