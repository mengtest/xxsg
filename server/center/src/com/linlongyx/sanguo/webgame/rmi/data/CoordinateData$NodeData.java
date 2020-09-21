/*    */ package com.linlongyx.sanguo.webgame.rmi.data;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NodeData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int distance;
/*    */   private long playerId;
/*    */   
/*    */   public NodeData() {}
/*    */   
/*    */   public NodeData(int distance, long playerId) {
/* 58 */     this.distance = distance;
/* 59 */     this.playerId = playerId;
/*    */   }
/*    */   
/*    */   public int getDistance() {
/* 63 */     return this.distance;
/*    */   }
/*    */   
/*    */   public void setDistance(int distance) {
/* 67 */     this.distance = distance;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 71 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public void setPlayerId(long playerId) {
/* 75 */     this.playerId = playerId;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\CoordinateData$NodeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */