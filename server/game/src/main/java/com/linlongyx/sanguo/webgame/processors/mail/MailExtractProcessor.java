/*    */ package com.linlongyx.sanguo.webgame.processors.mail;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.mail.MailComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.mail.MailEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mail.MailExtractRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mail.MailExtractResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MailExtractProcessor
/*    */   extends ProcessorBase<MailExtractRequest, MailExtractResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 29 */     this.response = (ResponseBase)new MailExtractResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, MailExtractRequest request) {
/* 34 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 5)) {
/* 35 */       return 10061;
/*    */     }
/* 37 */     List<Integer> deletes = new ArrayList<>();
/* 38 */     ArrayList<Reward> arrayList = new ArrayList<>();
/* 39 */     MailComponent mailComponent = (MailComponent)playerSession.getPlayer().createIfNotExist(MailComponent.class);
/* 40 */     if (request.id == 0) {
/* 41 */       for (Map.Entry<String, IMapEntity> entry : (Iterable<Map.Entry<String, IMapEntity>>)mailComponent.getProxy().getEntityMap().entrySet()) {
/* 42 */         MailEntity mailEntity = (MailEntity)entry.getValue();
/* 43 */         if (mailEntity.getIsExtract() != 1) {
/* 44 */           if (!mailEntity.getRewards().isEmpty()) {
/* 45 */             arrayList.addAll(mailEntity.getRewards());
/*    */           }
/* 47 */           mailEntity.setIsRead((byte)1);
/* 48 */           mailEntity.setIsExtract((byte)1);
/*    */         } 
/* 50 */         deletes.add(Integer.valueOf(mailEntity.getId()));
/*    */       } 
/*    */     } else {
/* 53 */       MailEntity mailEntity = (MailEntity)mailComponent.getEntity(String.valueOf(request.id));
/* 54 */       mailEntity.setIsRead((byte)1);
/* 55 */       if (mailEntity.getIsExtract() != 1) {
/* 56 */         mailEntity.setIsExtract((byte)1);
/* 57 */         if (!mailEntity.getRewards().isEmpty()) {
/* 58 */           arrayList.addAll(mailEntity.getRewards());
/*    */         }
/*    */       } 
/* 61 */       deletes.add(Integer.valueOf(mailEntity.getId()));
/*    */     } 
/* 63 */     if (!arrayList.isEmpty()) {
/* 64 */       ArrayList<Reward> list = FinanceUtil.handleRepeat(arrayList);
/* 65 */       ((MailExtractResponse)this.response).rewards = list;
/* 66 */       FinanceUtil.reward(list, playerSession.getPlayer(), ResourceEvent.MailExtract, true);
/*    */     } 
/* 68 */     for (Integer id : deletes) {
/* 69 */       short code = mailComponent.deleteMail(id.intValue());
/* 70 */       if (0 == code) {
/* 71 */         ((MailExtractResponse)this.response).ids.add(id);
/*    */       }
/*    */     } 
/* 74 */     LogUtils.errorLog(new Object[] { "MailExtract", Long.valueOf(playerSession.getPlayer().getPlayerId()), ((MailExtractResponse)this.response).ids.toString() });
/* 75 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mail\MailExtractProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */