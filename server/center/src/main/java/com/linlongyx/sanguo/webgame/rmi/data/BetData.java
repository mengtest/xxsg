/*    */ package com.linlongyx.sanguo.webgame.rmi.data;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @JsonIgnoreProperties(ignoreUnknown = true)
/*    */ public class BetData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private long playerId;
/*    */   private int betNum;
/*    */   private int createtime;
/*    */   
/*    */   public BetData() {}
/*    */   
/*    */   public BetData(long playerId, int betNum) {
/* 24 */     this.playerId = playerId;
/* 25 */     this.betNum = betNum;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 29 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public void setPlayerId(long playerId) {
/* 33 */     this.playerId = playerId;
/*    */   }
/*    */   
/*    */   public int getBetNum() {
/* 37 */     return this.betNum;
/*    */   }
/*    */   
/*    */   public void setBetNum(int betNum) {
/* 41 */     this.betNum = betNum;
/*    */   }
/*    */   
/*    */   public int getCreatetime() {
/* 45 */     return this.createtime;
/*    */   }
/*    */   
/*    */   public void setCreatetime(int createtime) {
/* 49 */     this.createtime = createtime;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     return "BetData{playerId=" + this.playerId + ", betNum=" + this.betNum + ", createtime=" + this.createtime + '}';
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\BetData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */