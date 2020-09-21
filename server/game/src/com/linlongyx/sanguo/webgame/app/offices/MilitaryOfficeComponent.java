/*     */ package com.linlongyx.sanguo.webgame.app.offices;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import java.util.HashMap;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MilitaryOfficeComponent
/*     */   extends AbstractComponent<MilitaryOfficeEntity>
/*     */ {
/*     */   public MilitaryOfficeComponent(long playerId) {
/*  17 */     super(MilitaryOfficeEntity.class);
/*  18 */     this.playerId = playerId;
/*  19 */     makeKey();
/*  20 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  26 */     super.init();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  31 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  36 */     setPlayerId(playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  41 */     if (time == 0) {
/*  42 */       setHelpList(new HashMap<>());
/*  43 */       setOfficeHelpTimes(0);
/*     */     } 
/*  45 */     return true;
/*     */   }
/*     */   
/*     */   public HashMap<Integer, Integer> getOfficeList() {
/*  49 */     return ((MilitaryOfficeEntity)getEntity()).getOfficeList();
/*     */   }
/*     */   
/*     */   public void setOfficeList(HashMap<Integer, Integer> officeList) {
/*  53 */     ((MilitaryOfficeEntity)getEntity()).setOfficeList(officeList);
/*     */   }
/*     */   
/*     */   public int getEndTime() {
/*  57 */     return ((MilitaryOfficeEntity)getEntity()).getEndTime();
/*     */   }
/*     */   
/*     */   public void setEndTime(int endTime) {
/*  61 */     ((MilitaryOfficeEntity)getEntity()).setEndTime(endTime);
/*     */   }
/*     */   
/*     */   public int getStatus() {
/*  65 */     return ((MilitaryOfficeEntity)getEntity()).getStatus();
/*     */   }
/*     */   
/*     */   public void setStatus(int status) {
/*  69 */     ((MilitaryOfficeEntity)getEntity()).setStatus(status);
/*     */   }
/*     */   
/*     */   public int getOfficeHelpTimes() {
/*  73 */     return ((MilitaryOfficeEntity)getEntity()).getOfficeHelpTimes();
/*     */   }
/*     */   
/*     */   public void setOfficeHelpTimes(int officeHelpTimes) {
/*  77 */     ((MilitaryOfficeEntity)getEntity()).setOfficeHelpTimes(officeHelpTimes);
/*     */   }
/*     */   
/*     */   public HashMap<Long, KeyValue> getHelpList() {
/*  81 */     return ((MilitaryOfficeEntity)getEntity()).getHelpList();
/*     */   }
/*     */   
/*     */   public void setHelpList(HashMap<Long, KeyValue> helpList) {
/*  85 */     ((MilitaryOfficeEntity)getEntity()).setHelpList(helpList);
/*     */   }
/*     */   
/*     */   public int getCurrJobId() {
/*  89 */     return ((MilitaryOfficeEntity)getEntity()).getCurrJobId();
/*     */   }
/*     */   
/*     */   public void setCurrJobId(int currJobId) {
/*  93 */     ((MilitaryOfficeEntity)getEntity()).setCurrJobId(currJobId);
/*     */   }
/*     */   
/*     */   public int getHelpTimeAdd() {
/*  97 */     return ((MilitaryOfficeEntity)getEntity()).getHelpTimeAdd();
/*     */   }
/*     */   
/*     */   public void setHelpTimeAdd(int helpTimeAdd) {
/* 101 */     ((MilitaryOfficeEntity)getEntity()).setHelpTimeAdd(helpTimeAdd);
/*     */   }
/*     */   
/*     */   public int getGeneralRecovery() {
/* 105 */     return ((MilitaryOfficeEntity)getEntity()).getGeneralRecovery();
/*     */   }
/*     */   
/*     */   public void setGeneralRecovery(int generalRecovery) {
/* 109 */     ((MilitaryOfficeEntity)getEntity()).setGeneralRecovery(generalRecovery);
/*     */   }
/*     */   
/*     */   public HashMap<Integer, Set<Integer>> getTypeMap() {
/* 113 */     return ((MilitaryOfficeEntity)getEntity()).getTypeMap();
/*     */   }
/*     */   
/*     */   public void setTypeMap(HashMap<Integer, Set<Integer>> typeMap) {
/* 117 */     ((MilitaryOfficeEntity)getEntity()).setTypeMap(typeMap);
/*     */   }
/*     */   
/*     */   public int getCurrJobHelped() {
/* 121 */     return ((MilitaryOfficeEntity)getEntity()).getCurrJobHelped();
/*     */   }
/*     */   
/*     */   public void setCurrJobHelped(int currJobHelped) {
/* 125 */     ((MilitaryOfficeEntity)getEntity()).setCurrJobHelped(currJobHelped);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\offices\MilitaryOfficeComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */