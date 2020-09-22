/*     */ package com.linlongyx.sanguo.webgame.rmi.data;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ModelData;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PersonalData
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3317320564955566098L;
/*     */   private int serverId;
/*     */   private long playerId;
/*     */   private int rankScore;
/*     */   private int level;
/*     */   private long fightValue;
/*     */   private String name;
/*     */   private ModelData modelData;
/*     */   private long[] attrs;
/*     */   private HashMap<Integer, Integer> skills;
/*     */   private HashMap<Integer, Integer> partnerSkills;
/*     */   
/*     */   public int getServerId() {
/*  28 */     return this.serverId;
/*     */   }
/*     */   
/*     */   public void setServerId(int serverId) {
/*  32 */     this.serverId = serverId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  36 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public void setPlayerId(long playerId) {
/*  40 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public int getRankScore() {
/*  44 */     return this.rankScore;
/*     */   }
/*     */   
/*     */   public void setRankScore(int rankScore) {
/*  48 */     this.rankScore = rankScore;
/*     */   }
/*     */   
/*     */   public int getLevel() {
/*  52 */     return this.level;
/*     */   }
/*     */   
/*     */   public void setLevel(int level) {
/*  56 */     this.level = level;
/*     */   }
/*     */   
/*     */   public long getFightValue() {
/*  60 */     return this.fightValue;
/*     */   }
/*     */   
/*     */   public void setFightValue(long fightValue) {
/*  64 */     this.fightValue = fightValue;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  68 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  72 */     this.name = name;
/*     */   }
/*     */   
/*     */   public ModelData getModelData() {
/*  76 */     return this.modelData;
/*     */   }
/*     */   
/*     */   public void setModelData(ModelData modelData) {
/*  80 */     this.modelData = modelData;
/*     */   }
/*     */   
/*     */   public long[] getAttrs() {
/*  84 */     return this.attrs;
/*     */   }
/*     */   
/*     */   public void setAttrs(long[] attrs) {
/*  88 */     this.attrs = attrs;
/*     */   }
/*     */   
/*     */   public HashMap<Integer, Integer> getSkills() {
/*  92 */     return this.skills;
/*     */   }
/*     */   
/*     */   public void setSkills(HashMap<Integer, Integer> skills) {
/*  96 */     this.skills = skills;
/*     */   }
/*     */   
/*     */   public HashMap<Integer, Integer> getPartnerSkills() {
/* 100 */     return this.partnerSkills;
/*     */   }
/*     */   
/*     */   public void setPartnerSkills(HashMap<Integer, Integer> partnerSkills) {
/* 104 */     this.partnerSkills = partnerSkills;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\PersonalData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */