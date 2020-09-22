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
/*    */ public class PkDataItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String id;
/*    */   private PkPlayerData leftPlayer;
/*    */   private PkPlayerData rightPlayer;
/*    */   private long winner;
/*    */   
/*    */   public String getId() {
/* 22 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(String id) {
/* 26 */     this.id = id;
/*    */   }
/*    */   
/*    */   public PkPlayerData getLeftPlayer() {
/* 30 */     return this.leftPlayer;
/*    */   }
/*    */   
/*    */   public void setLeftPlayer(PkPlayerData leftPlayer) {
/* 34 */     this.leftPlayer = leftPlayer;
/*    */   }
/*    */   
/*    */   public PkPlayerData getRightPlayer() {
/* 38 */     return this.rightPlayer;
/*    */   }
/*    */   
/*    */   public void setRightPlayer(PkPlayerData rightPlayer) {
/* 42 */     this.rightPlayer = rightPlayer;
/*    */   }
/*    */   
/*    */   public long getWinner() {
/* 46 */     return this.winner;
/*    */   }
/*    */   
/*    */   public void setWinner(long winner) {
/* 50 */     this.winner = winner;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     return "PkDataItem{id='" + this.id + '\'' + ", leftPlayer=" + this.leftPlayer + ", rightPlayer=" + this.rightPlayer + ", winner=" + this.winner + '}';
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\PkDataItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */