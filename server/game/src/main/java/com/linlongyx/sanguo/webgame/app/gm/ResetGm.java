/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.sanguo.webgame.net.http.answer.DailyResetEvent;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResetGm
/*    */   implements IGm
/*    */ {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 14 */     if (strings[2].equals("daily")) {
/* 15 */       DailyResetEvent dailyResetEvent = new DailyResetEvent();
/* 16 */       dailyResetEvent.process(null);
/* 17 */     } else if (strings[2].equals("0")) {
/* 18 */       LookUpService.reset(0);
/* 19 */       DailyResetEvent dailyResetEvent = new DailyResetEvent();
/* 20 */       dailyResetEvent.process(null);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\ResetGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */