/*    */ package com.linlongyx.sanguo.webgame.processors.draw;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.draw.DrawCardPushRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.draw.DrawCardPushResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DrawCardPushProcessor
/*    */   extends ProcessorBase<DrawCardPushRequest, DrawCardPushResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new DrawCardPushResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DrawCardPushRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\draw\DrawCardPushProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */