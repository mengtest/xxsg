/*    */ package com.linlongyx.sanguo.webgame.common.structure;
/*    */ 
/*    */ import com.linlongyx.core.utils.TimeUtil;
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
/*    */ public class ODTime
/*    */   implements Serializable
/*    */ {
/*    */   private int originTime;
/*    */   private int destinationTime;
/*    */   
/*    */   public ODTime() {}
/*    */   
/*    */   public ODTime(int originTime, int destinationTime) {
/* 24 */     this.originTime = originTime;
/* 25 */     this.destinationTime = destinationTime;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isValidate(int nowTime) {
/* 33 */     return (this.originTime < nowTime && nowTime < this.destinationTime);
/*    */   }
/*    */   
/*    */   void setDuration(int durationTime) {
/* 37 */     this.originTime = TimeUtil.currentTime();
/* 38 */     this.destinationTime = this.originTime + durationTime;
/*    */   }
/*    */   
/*    */   public int getOriginTime() {
/* 42 */     return this.originTime;
/*    */   }
/*    */   
/*    */   public void setOriginTime(int originTime) {
/* 46 */     this.originTime = originTime;
/*    */   }
/*    */   
/*    */   public int getDestinationTime() {
/* 50 */     return this.destinationTime;
/*    */   }
/*    */   
/*    */   public void setDestinationTime(int destinationTime) {
/* 54 */     this.destinationTime = destinationTime;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\structure\ODTime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */