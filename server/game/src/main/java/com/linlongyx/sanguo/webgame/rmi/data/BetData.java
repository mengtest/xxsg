/*    */ package com.linlongyx.sanguo.webgame.rmi.data;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*    */ import com.linlongyx.core.utils.TimeUtil;
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
/* 25 */     this.playerId = playerId;
/* 26 */     this.betNum = betNum;
/* 27 */     this.createtime = TimeUtil.currentTime();
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 31 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public void setPlayerId(long playerId) {
/* 35 */     this.playerId = playerId;
/*    */   }
/*    */   
/*    */   public int getBetNum() {
/* 39 */     return this.betNum;
/*    */   }
/*    */   
/*    */   public void setBetNum(int betNum) {
/* 43 */     this.betNum = betNum;
/*    */   }
/*    */   
/*    */   public int getCreatetime() {
/* 47 */     return this.createtime;
/*    */   }
/*    */   
/*    */   public void setCreatetime(int createtime) {
/* 51 */     this.createtime = createtime;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     return "BetData{playerId=" + this.playerId + ", betNum=" + this.betNum + ", createtime=" + this.createtime + '}';
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\BetData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */