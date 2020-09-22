/*    */ package com.linlongyx.sanguo.webgame.processors.equip;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.equip.DeleteEquipNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.equip.DeleteEquipNoticeResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DeleteEquipNoticeProcessor
/*    */   extends ProcessorBase<DeleteEquipNoticeRequest, DeleteEquipNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new DeleteEquipNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DeleteEquipNoticeRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\equip\DeleteEquipNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */