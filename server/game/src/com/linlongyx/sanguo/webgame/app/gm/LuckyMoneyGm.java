/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.processors.luckymoney.LuckyMoneyOpenProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckymoney.LuckyMoneyOpenRequest;
/*    */ 
/*    */ public class LuckyMoneyGm
/*    */   implements IGm {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 11 */     if (strings[2].equals("open")) {
/* 12 */       LuckyMoneyOpenRequest request = new LuckyMoneyOpenRequest();
/* 13 */       (new LuckyMoneyOpenProcessor()).handle(playerSession, (RequestBase)request);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\LuckyMoneyGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */