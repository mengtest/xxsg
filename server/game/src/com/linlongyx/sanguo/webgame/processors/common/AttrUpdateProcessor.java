/*    */ package com.linlongyx.sanguo.webgame.processors.common;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.common.AttrUpdateRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.common.AttrUpdateResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AttrUpdateProcessor
/*    */   extends ProcessorBase<AttrUpdateRequest, AttrUpdateResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new AttrUpdateResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, AttrUpdateRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\common\AttrUpdateProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */