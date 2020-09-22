/*     */ package com.linlongyx.sanguo.webgame.rmi.destiny.group;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PkPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.PkData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.Zone;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class QuarterMatchGroup
/*     */   extends AbstractGroup
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int groupId;
/*     */   private String groupKey;
/*  26 */   private PkData[] quarterPkData = new PkData[4];
/*  27 */   private PkData[] halfPkData = new PkData[2];
/*     */   
/*     */   private PkData finalPkData;
/*     */   
/*     */   public QuarterMatchGroup() {}
/*     */   
/*     */   public QuarterMatchGroup(int groupId, String groupKey) {
/*  34 */     this.groupId = groupId;
/*  35 */     this.groupKey = groupKey;
/*     */   }
/*     */   
/*     */   public void initQuarterPkData(Zone zone, DestinyPlayerData destinyPlayerData, int pkIndex) {
/*  39 */     int quarterPkDataIndex = pkIndex / 2;
/*  40 */     if (this.quarterPkData[quarterPkDataIndex] == null) {
/*  41 */       this.quarterPkData[quarterPkDataIndex] = new PkData(zone, this.groupKey + "_" + (quarterPkDataIndex + 1));
/*     */     }
/*     */ 
/*     */     
/*  45 */     if (pkIndex % 2 == 0) {
/*  46 */       this.quarterPkData[quarterPkDataIndex].initLeftPlayer(destinyPlayerData);
/*     */     } else {
/*  48 */       this.quarterPkData[quarterPkDataIndex].initRightPlayer(destinyPlayerData);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void quarterPk(Zone zone) {
/*  53 */     for (PkData pkData : this.quarterPkData) {
/*  54 */       if (pkData != null) {
/*  55 */         updatePkFighters(zone, pkData);
/*  56 */         pkData.startPkFight(zone);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initHalfPkData(Zone zone) {
/*  62 */     List<PkPlayerData> pkDataList = new ArrayList<>();
/*  63 */     for (PkData pkData : this.quarterPkData) {
/*  64 */       if (pkData != null && pkData.getPkState() == PkData.PkState.end && pkData.getWinner() != 0L) {
/*  65 */         pkDataList.add(pkData.getWinnerData());
/*     */       }
/*     */     } 
/*  68 */     if (pkDataList.isEmpty())
/*  69 */       return;  for (int i = 0; i < pkDataList.size(); i++) {
/*  70 */       int halfIndex = i / 2;
/*  71 */       if (this.halfPkData[halfIndex] == null) {
/*  72 */         this.halfPkData[halfIndex] = new PkData(zone, this.groupKey + "_" + (4 + halfIndex + 1));
/*     */       }
/*  74 */       if (i % 2 == 0) {
/*  75 */         this.halfPkData[halfIndex].initLeftPlayer(pkDataList.get(i));
/*     */       } else {
/*  77 */         this.halfPkData[halfIndex].initRightPlayer(pkDataList.get(i));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void halfPk(Zone zone) {
/*  83 */     for (PkData pkData : this.halfPkData) {
/*  84 */       if (pkData != null) {
/*  85 */         updatePkFighters(zone, pkData);
/*  86 */         pkData.startPkFight(zone);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initFinalPkData(Zone zone) {
/*  92 */     List<PkPlayerData> pkDataList = new ArrayList<>();
/*  93 */     for (PkData pkData : this.halfPkData) {
/*  94 */       if (pkData != null && pkData.getPkState() == PkData.PkState.end && pkData.getWinner() != 0L) {
/*  95 */         pkDataList.add(pkData.getWinnerData());
/*     */       }
/*     */     } 
/*  98 */     if (pkDataList.isEmpty())
/*  99 */       return;  if (this.finalPkData == null) {
/* 100 */       this.finalPkData = new PkData(zone, this.groupKey + "_" + '\007');
/*     */     }
/* 102 */     for (int i = 0; i < pkDataList.size(); i++) {
/* 103 */       if (i % 2 == 0) {
/* 104 */         this.finalPkData.initLeftPlayer(pkDataList.get(i));
/*     */       } else {
/* 106 */         this.finalPkData.initRightPlayer(pkDataList.get(i));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void finalPk(Zone zone) {
/* 112 */     if (this.finalPkData != null) {
/* 113 */       updatePkFighters(zone, this.finalPkData);
/* 114 */       this.finalPkData.startPkFight(zone);
/*     */       
/* 116 */       setWinner(this.finalPkData.getWinner());
/*     */     } else {
/* 118 */       LogUtil.errorLog(new Object[] { "finalPkdata if null, zone:", zone.getZoneKey(), "group:", this.groupKey });
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public PkPlayerData getFinalWinner() {
/* 124 */     if (getWinner() == 0L || this.finalPkData == null) return null; 
/* 125 */     return this.finalPkData.getWinnerData();
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public PkData[] getAllPkData() {
/* 130 */     int length = this.quarterPkData.length + this.halfPkData.length + 1;
/* 131 */     PkData[] pkDatas = new PkData[length]; int i;
/* 132 */     for (i = 0; i < this.quarterPkData.length; i++) {
/* 133 */       pkDatas[i] = this.quarterPkData[i];
/*     */     }
/* 135 */     for (i = this.quarterPkData.length; i < length - 1; i++) {
/* 136 */       pkDatas[i] = this.halfPkData[i - this.quarterPkData.length];
/*     */     }
/* 138 */     pkDatas[length - 1] = this.finalPkData;
/*     */ 
/*     */ 
/*     */     
/* 142 */     return pkDatas;
/*     */   }
/*     */   
/*     */   public PkData[] getQuarterPkData() {
/* 146 */     return this.quarterPkData;
/*     */   }
/*     */   
/*     */   public void setQuarterPkData(PkData[] quarterPkData) {
/* 150 */     this.quarterPkData = quarterPkData;
/*     */   }
/*     */   
/*     */   public PkData[] getHalfPkData() {
/* 154 */     return this.halfPkData;
/*     */   }
/*     */   
/*     */   public void setHalfPkData(PkData[] halfPkData) {
/* 158 */     this.halfPkData = halfPkData;
/*     */   }
/*     */   
/*     */   public PkData getFinalPkData() {
/* 162 */     return this.finalPkData;
/*     */   }
/*     */   
/*     */   public void setFinalPkData(PkData finalPkData) {
/* 166 */     this.finalPkData = finalPkData;
/*     */   }
/*     */   
/*     */   public int getGroupId() {
/* 170 */     return this.groupId;
/*     */   }
/*     */   
/*     */   public void setGroupId(int groupId) {
/* 174 */     this.groupId = groupId;
/*     */   }
/*     */   
/*     */   public String getGroupKey() {
/* 178 */     return this.groupKey;
/*     */   }
/*     */   
/*     */   public void setGroupKey(String groupKey) {
/* 182 */     this.groupKey = groupKey;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\group\QuarterMatchGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */