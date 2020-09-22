/*     */ package com.linlongyx.sanguo.webgame.rmi.destiny.group;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PkPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.PkData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.Zone;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class HalfMatchGroup
/*     */   extends AbstractGroup
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int groupId;
/*     */   private String groupKey;
/*  26 */   private PkData[] halfPkData = new PkData[2];
/*     */   
/*     */   private PkData finalPkData;
/*     */   
/*     */   public HalfMatchGroup() {}
/*     */   
/*     */   public HalfMatchGroup(int groupId, String groupKey) {
/*  33 */     this.groupId = groupId;
/*  34 */     this.groupKey = groupKey;
/*     */   }
/*     */   
/*     */   public void initHalfPkData(Zone zone, List<PkPlayerData> pkPlayerData) {
/*  38 */     if (pkPlayerData.size() > 2) {
/*  39 */       Collections.shuffle(pkPlayerData);
/*     */     }
/*  41 */     for (int i = 0; i < pkPlayerData.size(); i++) {
/*  42 */       int halfIndex = i / 2;
/*  43 */       if (this.halfPkData[halfIndex] == null) {
/*  44 */         this.halfPkData[halfIndex] = new PkData(zone, this.groupKey + "_" + (halfIndex + 1));
/*     */       }
/*  46 */       if (i % 2 == 0) {
/*  47 */         this.halfPkData[halfIndex].initLeftPlayer(pkPlayerData.get(i));
/*     */       } else {
/*  49 */         this.halfPkData[halfIndex].initRightPlayer(pkPlayerData.get(i));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void halfPk(Zone zone) {
/*  55 */     for (PkData pkData : this.halfPkData) {
/*  56 */       if (pkData != null) {
/*  57 */         updatePkFighters(zone, pkData);
/*  58 */         pkData.startPkFight(zone);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initFinalPkData(Zone zone) {
/*  64 */     List<PkPlayerData> pkDataList = new ArrayList<>();
/*  65 */     for (PkData pkData : this.halfPkData) {
/*  66 */       if (pkData != null && pkData.getPkState() == PkData.PkState.end && pkData.getWinner() != 0L) {
/*  67 */         pkDataList.add(pkData.getWinnerData());
/*     */       }
/*     */     } 
/*  70 */     if (pkDataList.isEmpty())
/*  71 */       return;  if (this.finalPkData == null) {
/*  72 */       this.finalPkData = new PkData(zone, this.groupKey + "_" + '\003');
/*     */     }
/*  74 */     for (int i = 0; i < pkDataList.size(); i++) {
/*  75 */       if (i % 2 == 0) {
/*  76 */         this.finalPkData.initLeftPlayer(pkDataList.get(i));
/*     */       } else {
/*  78 */         this.finalPkData.initRightPlayer(pkDataList.get(i));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void finalPk(Zone zone) {
/*  84 */     if (this.finalPkData != null) {
/*  85 */       updatePkFighters(zone, this.finalPkData);
/*  86 */       this.finalPkData.startPkFight(zone);
/*  87 */       setWinner(this.finalPkData.getWinner());
/*     */     } else {
/*  89 */       LogUtil.errorLog(new Object[] { "finalPkdata if null, zone:", zone.getZoneKey(), "group:", this.groupKey });
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getGroupId() {
/*  94 */     return this.groupId;
/*     */   }
/*     */   
/*     */   public void setGroupId(int groupId) {
/*  98 */     this.groupId = groupId;
/*     */   }
/*     */   
/*     */   public String getGroupKey() {
/* 102 */     return this.groupKey;
/*     */   }
/*     */   
/*     */   public void setGroupKey(String groupKey) {
/* 106 */     this.groupKey = groupKey;
/*     */   }
/*     */   
/*     */   public PkData[] getHalfPkData() {
/* 110 */     return this.halfPkData;
/*     */   }
/*     */   
/*     */   public void setHalfPkData(PkData[] halfPkData) {
/* 114 */     this.halfPkData = halfPkData;
/*     */   }
/*     */   
/*     */   public PkData getFinalPkData() {
/* 118 */     return this.finalPkData;
/*     */   }
/*     */   
/*     */   public void setFinalPkData(PkData finalPkData) {
/* 122 */     this.finalPkData = finalPkData;
/*     */   }
/*     */ 
/*     */   
/*     */   @JsonIgnore
/*     */   public PkData[] getAllPkData() {
/* 128 */     int length = this.halfPkData.length + 1;
/* 129 */     PkData[] pkDatas = new PkData[length]; int i;
/* 130 */     for (i = 0; i < this.halfPkData.length; i++) {
/* 131 */       pkDatas[i] = this.halfPkData[i];
/*     */     }
/* 133 */     for (i = this.halfPkData.length; i < length; i++) {
/* 134 */       pkDatas[i] = this.halfPkData[i - this.halfPkData.length];
/*     */     }
/* 136 */     pkDatas[length - 1] = this.finalPkData;
/*     */ 
/*     */ 
/*     */     
/* 140 */     return pkDatas;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\group\HalfMatchGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */