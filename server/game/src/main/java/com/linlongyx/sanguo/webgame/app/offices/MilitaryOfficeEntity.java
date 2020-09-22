/*     */ package com.linlongyx.sanguo.webgame.app.offices;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import java.util.HashMap;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_offices", prefix = "offices_%serverId_%playerId", isPlayerIdKey = true)
/*     */ public class MilitaryOfficeEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*  20 */   private HashMap<Integer, Integer> officeList = new HashMap<>();
/*     */   private int currJobId;
/*     */   private int endTime;
/*     */   private int status;
/*     */   private int officeHelpTimes;
/*  25 */   private HashMap<Long, KeyValue> helpList = new HashMap<>();
/*     */   private int helpTimeAdd;
/*     */   private int generalRecovery;
/*  28 */   private HashMap<Integer, Set<Integer>> typeMap = new HashMap<>();
/*     */   
/*     */   private int currJobHelped;
/*     */   
/*     */   public MilitaryOfficeEntity(long playerId) {
/*  33 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  37 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public HashMap<Integer, Integer> getOfficeList() {
/*  41 */     return this.officeList;
/*     */   }
/*     */   
/*     */   public void setOfficeList(HashMap<Integer, Integer> officeList) {
/*  45 */     this.officeList = officeList;
/*     */   }
/*     */   
/*     */   public int getCurrJobId() {
/*  49 */     return this.currJobId;
/*     */   }
/*     */   
/*     */   public void setCurrJobId(int currJobId) {
/*  53 */     this.currJobId = currJobId;
/*     */   }
/*     */   
/*     */   public int getEndTime() {
/*  57 */     return this.endTime;
/*     */   }
/*     */   
/*     */   public void setEndTime(int endTime) {
/*  61 */     this.endTime = endTime;
/*     */   }
/*     */   
/*     */   public int getStatus() {
/*  65 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(int status) {
/*  69 */     this.status = status;
/*     */   }
/*     */   
/*     */   public int getOfficeHelpTimes() {
/*  73 */     return this.officeHelpTimes;
/*     */   }
/*     */   
/*     */   public void setOfficeHelpTimes(int officeHelpTimes) {
/*  77 */     this.officeHelpTimes = officeHelpTimes;
/*     */   }
/*     */   
/*     */   public HashMap<Long, KeyValue> getHelpList() {
/*  81 */     return this.helpList;
/*     */   }
/*     */   
/*     */   public void setHelpList(HashMap<Long, KeyValue> helpList) {
/*  85 */     this.helpList = helpList;
/*     */   }
/*     */   
/*     */   public int getHelpTimeAdd() {
/*  89 */     return this.helpTimeAdd;
/*     */   }
/*     */   
/*     */   public void setHelpTimeAdd(int helpTimeAdd) {
/*  93 */     this.helpTimeAdd = helpTimeAdd;
/*     */   }
/*     */   
/*     */   public int getGeneralRecovery() {
/*  97 */     return this.generalRecovery;
/*     */   }
/*     */   
/*     */   public void setGeneralRecovery(int generalRecovery) {
/* 101 */     this.generalRecovery = generalRecovery;
/*     */   }
/*     */   
/*     */   public HashMap<Integer, Set<Integer>> getTypeMap() {
/* 105 */     return this.typeMap;
/*     */   }
/*     */   
/*     */   public void setTypeMap(HashMap<Integer, Set<Integer>> typeMap) {
/* 109 */     this.typeMap = typeMap;
/*     */   }
/*     */   
/*     */   public int getCurrJobHelped() {
/* 113 */     return this.currJobHelped;
/*     */   }
/*     */   
/*     */   public void setCurrJobHelped(int currJobHelped) {
/* 117 */     this.currJobHelped = currJobHelped;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 122 */     return "MilitaryOfficeEntity{playerId=" + this.playerId + "officeList=" + this.officeList + "currJobId=" + this.currJobId + "endTime=" + this.endTime + "status=" + this.status + "officeHelpTimes=" + this.officeHelpTimes + "helpList=" + this.helpList + "helpTimeAdd=" + this.helpTimeAdd + "generalRecovery=" + this.generalRecovery + "typeMap=" + this.typeMap + "currJobHelped=" + this.currJobHelped + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\offices\MilitaryOfficeEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */