/*    */ package com.linlongyx.sanguo.webgame.processors.mail;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.mail.MailComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.mail.MailEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mail.MailReadRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mail.MailReadResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MailReadProcessor
/*    */   extends ProcessorBase<MailReadRequest, MailReadResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 18 */     this.response = (ResponseBase)new MailReadResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, MailReadRequest request) {
/* 23 */     MailComponent mailComponent = (MailComponent)playerSession.getPlayer().createIfNotExist(MailComponent.class);
/* 24 */     MailEntity mailEntity = (MailEntity)mailComponent.getEntity(String.valueOf(request.id));
/* 25 */     if (mailEntity == null) {
/* 26 */       return 10503;
/*    */     }
/* 28 */     if (mailEntity.getIsRead() != 1) {
/* 29 */       mailEntity.setIsRead((byte)1);
/*    */     }
/* 31 */     ((MailReadResponse)this.response).id = request.id;
/* 32 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mail\MailReadProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */