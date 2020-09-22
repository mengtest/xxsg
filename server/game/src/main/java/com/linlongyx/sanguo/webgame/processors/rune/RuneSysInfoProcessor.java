/*    */ package com.linlongyx.sanguo.webgame.processors.rune;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.RuneParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rune.RuneSysInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rune.RuneSysInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RuneSysInfoProcessor
/*    */   extends ProcessorBase<RuneSysInfoRequest, RuneSysInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new RuneSysInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RuneSysInfoRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 69)) {
/* 31 */       return 10061;
/*    */     }
/* 33 */     long pid = request.pid;
/* 34 */     RuneParameter runeParameter = (RuneParameter)ParameterConstant.getParameter(69);
/* 35 */     if (pid == -1L) {
/* 36 */       RuneComponent runeComponent = (RuneComponent)playerSession.getPlayer().createIfNotExist(RuneComponent.class);
/* 37 */       for (int i = 1; i <= runeParameter.getHoleLimit(); i++) {
/* 38 */         KeyValue keyValue = new KeyValue();
/* 39 */         keyValue.key = i;
/* 40 */         keyValue.value = ((Long)runeComponent.getRuneMap().getOrDefault(Integer.valueOf(i), Long.valueOf(0L))).longValue();
/* 41 */         ((RuneSysInfoResponse)this.response).runes.add(keyValue);
/*    */       } 
/*    */     } else {
/* 44 */       PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/* 45 */       PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 46 */       if (partnerEntity == null) {
/* 47 */         return 13303;
/*    */       }
/* 49 */       for (int i = 1; i <= runeParameter.getHoleLimit(); i++) {
/* 50 */         KeyValue keyValue = new KeyValue();
/* 51 */         keyValue.key = i;
/* 52 */         keyValue.value = ((Long)partnerEntity.getRuneMap().getOrDefault(Integer.valueOf(i), Long.valueOf(0L))).longValue();
/* 53 */         ((RuneSysInfoResponse)this.response).runes.add(keyValue);
/*    */       } 
/*    */     } 
/* 56 */     ((RuneSysInfoResponse)this.response).pid = pid;
/* 57 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rune\RuneSysInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */