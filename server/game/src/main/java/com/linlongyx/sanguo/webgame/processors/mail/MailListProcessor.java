/*    */ package com.linlongyx.sanguo.webgame.processors.mail;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.mail.MailComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.mail.MailEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.MailParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mail.MailListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mail.MailListResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MailInfo;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MailListProcessor
/*    */   extends ProcessorBase<MailListRequest, MailListResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new MailListResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, MailListRequest request) {
/* 31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 5)) {
/* 32 */       return 10061;
/*    */     }
/* 34 */     MailComponent mailComponent = (MailComponent)playerSession.getPlayer().createIfNotExist(MailComponent.class);
/* 35 */     List<Integer> list = mailComponent.getMailIdsByType(request.type);
/* 36 */     if (null == list) {
/* 37 */       return 0;
/*    */     }
/* 39 */     List<Integer> arrayList = new ArrayList<>(list);
/* 40 */     int start = request.start;
/* 41 */     MailParameter parameter = (MailParameter)ParameterConstant.getParameter(5);
/* 42 */     int end = start + parameter.getMailPartSize();
/* 43 */     for (int i = start; i < end && 
/* 44 */       i < arrayList.size(); i++) {
/*    */ 
/*    */       
/* 47 */       MailEntity mailEntity = (MailEntity)mailComponent.getEntity(String.valueOf(arrayList.get(i)));
/* 48 */       if (mailEntity == null) {
/*    */         break;
/*    */       }
/* 51 */       MailInfo mailInfo = new MailInfo();
/* 52 */       mailInfo.id = mailEntity.getId();
/* 53 */       mailInfo.type = mailEntity.getType();
/* 54 */       mailInfo.sendId = mailEntity.getSendId();
/* 55 */       mailInfo.sendName = mailEntity.getSendName();
/* 56 */       mailInfo.sendTime = mailEntity.getSendTime();
/* 57 */       mailInfo.title = mailEntity.getTitle();
/* 58 */       mailInfo.context = mailEntity.getContext();
/* 59 */       mailInfo.rewards = mailEntity.getRewards();
/* 60 */       mailInfo.isRead = mailEntity.getIsRead();
/* 61 */       mailInfo.isExtract = mailEntity.getIsExtract();
/* 62 */       ((MailListResponse)this.response).mails.add(mailInfo);
/*    */     } 
/* 64 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mail\MailListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */