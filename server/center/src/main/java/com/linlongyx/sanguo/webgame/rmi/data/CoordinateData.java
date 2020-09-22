/*     */ package com.linlongyx.sanguo.webgame.rmi.data;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import java.io.Serializable;
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CoordinateData
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private PlayerData playerData;
/*     */   private int point;
/*     */   private long updatetime;
/*  20 */   private AxisData[] axisDataList = new AxisData[4];
/*     */ 
/*     */   
/*     */   public CoordinateData() {}
/*     */ 
/*     */   
/*     */   public CoordinateData(PlayerData playerData) {
/*  27 */     this.playerData = playerData;
/*  28 */     this.point = 0;
/*  29 */     this.updatetime = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public static class AxisData
/*     */     implements Serializable {
/*     */     private static final long serialVersionUID = 1L;
/*  35 */     private LinkedList<CoordinateData.NodeData> linkedList = new LinkedList<>();
/*     */     
/*     */     public LinkedList<CoordinateData.NodeData> getLinkedList() {
/*  38 */       return this.linkedList;
/*     */     }
/*     */     
/*     */     public void setLinkedList(LinkedList<CoordinateData.NodeData> linkedList) {
/*  42 */       this.linkedList = linkedList;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class NodeData
/*     */     implements Serializable
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     private int distance;
/*     */     private long playerId;
/*     */     
/*     */     public NodeData() {}
/*     */     
/*     */     public NodeData(int distance, long playerId) {
/*  58 */       this.distance = distance;
/*  59 */       this.playerId = playerId;
/*     */     }
/*     */     
/*     */     public int getDistance() {
/*  63 */       return this.distance;
/*     */     }
/*     */     
/*     */     public void setDistance(int distance) {
/*  67 */       this.distance = distance;
/*     */     }
/*     */     
/*     */     public long getPlayerId() {
/*  71 */       return this.playerId;
/*     */     }
/*     */     
/*     */     public void setPlayerId(long playerId) {
/*  75 */       this.playerId = playerId;
/*     */     }
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public boolean isEmpty() {
/*  81 */     for (AxisData data : this.axisDataList) {
/*  82 */       if (data != null) {
/*  83 */         return false;
/*     */       }
/*     */     } 
/*  86 */     return true;
/*     */   }
/*     */   
/*     */   public PlayerData getPlayerData() {
/*  90 */     return this.playerData;
/*     */   }
/*     */   
/*     */   public void setPlayerData(PlayerData playerData) {
/*  94 */     this.playerData = playerData;
/*     */   }
/*     */   
/*     */   public int getPoint() {
/*  98 */     return this.point;
/*     */   }
/*     */   
/*     */   public void setPoint(int point) {
/* 102 */     this.point = point;
/*     */   }
/*     */   
/*     */   public AxisData[] getAxisDataList() {
/* 106 */     return this.axisDataList;
/*     */   }
/*     */   
/*     */   public void setAxisDataList(AxisData[] axisDataList) {
/* 110 */     this.axisDataList = axisDataList;
/*     */   }
/*     */   
/*     */   public long getUpdatetime() {
/* 114 */     return this.updatetime;
/*     */   }
/*     */   
/*     */   public void setUpdatetime(long updatetime) {
/* 118 */     this.updatetime = updatetime;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\CoordinateData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */