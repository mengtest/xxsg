/*    */ package com.linlongyx.sanguo.webgame.common.timer;
/*    */ 
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BonusTimer
/*    */   implements ITimer
/*    */ {
/*    */   public void execute(Object obj, int delay, int period) {}
/*    */   
/*    */   public void execute(Object obj, int delay) {
/* 19 */     Timer timer = new Timer();
/* 20 */     timer.schedule(new TimerTask() { public void run() {} }, delay);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\timer\BonusTimer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */