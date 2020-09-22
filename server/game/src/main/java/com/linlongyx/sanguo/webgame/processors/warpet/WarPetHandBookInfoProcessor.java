/*    */ package com.linlongyx.sanguo.webgame.processors.warpet;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.WarPetHandBookInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.WarPetHandBookInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WarPetHandBookInfoProcessor
/*    */   extends ProcessorBase<WarPetHandBookInfoRequest, WarPetHandBookInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new WarPetHandBookInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WarPetHandBookInfoRequest request) {
/* 26 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/* 27 */     int handType = request.handType;
/* 28 */     if (handType == 0) {
/* 29 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 30))
/* 30 */         return 10061; 
/* 31 */       ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 32 */       extendComponent.getWpHandbook().keySet().forEach(id -> {
/*    */             KeyValue keyValue = new KeyValue();
/*    */             keyValue.key = id.intValue();
/*    */             keyValue.value = ((Integer)extendComponent.getWpHandbook().get(id)).intValue();
/*    */             ((WarPetHandBookInfoResponse)this.response).infos.add(keyValue);
/*    */           });
/* 38 */     } else if (handType == 1) {
/* 39 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 53))
/* 40 */         return 10061; 
/* 41 */       singleInsComponent.getKfHandbook().keySet().forEach(id -> {
/*    */             KeyValue keyValue = new KeyValue();
/*    */             keyValue.key = id.intValue();
/*    */             keyValue.value = ((Integer)singleInsComponent.getKfHandbook().get(id)).intValue();
/*    */             ((WarPetHandBookInfoResponse)this.response).infos.add(keyValue);
/*    */           });
/* 47 */     } else if (handType == 2) {
/* 48 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 54))
/* 49 */         return 10061; 
/* 50 */       singleInsComponent.getStageHandbook().keySet().forEach(id -> {
/*    */             KeyValue keyValue = new KeyValue();
/*    */             keyValue.key = id.intValue();
/*    */             keyValue.value = ((Integer)singleInsComponent.getStageHandbook().get(id)).intValue();
/*    */             ((WarPetHandBookInfoResponse)this.response).infos.add(keyValue);
/*    */           });
/* 56 */     } else if (handType == 3) {
/*    */     
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 66 */     ((WarPetHandBookInfoResponse)this.response).handType = handType;
/* 67 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\warpet\WarPetHandBookInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */