/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.processors.turnplate.TurnplateDrawProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.turnplate.TurnplateDrawRequest;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TurnplateGm
/*    */   implements IGm
/*    */ {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 15 */     if (strings[2].equals("draw")) {
/* 16 */       TurnplateDrawRequest request = new TurnplateDrawRequest();
/* 17 */       request.type = Integer.parseInt(strings[3]);
/* 18 */       (new TurnplateDrawProcessor()).handle(playerSession, (RequestBase)request);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\TurnplateGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */