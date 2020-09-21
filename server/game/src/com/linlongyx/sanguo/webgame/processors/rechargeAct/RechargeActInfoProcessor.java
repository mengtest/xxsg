/*    */ package com.linlongyx.sanguo.webgame.processors.rechargeAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.rechargeActivity.RechargeActivityComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.rechargeActivity.RechargeActivityEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.rechargeActivity.RechargeActivityUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rechargeAct.RechargeActInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rechargeAct.RechargeActInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RechargeActInfoProcessor
/*    */   extends ProcessorBase<RechargeActInfoRequest, RechargeActInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new RechargeActInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RechargeActInfoRequest request) {
/* 27 */     RechargeActivityComponent rechargeActivityComponent = (RechargeActivityComponent)playerSession.getPlayer().createIfNotExist(RechargeActivityComponent.class);
/* 28 */     RechargeActivityEntity entity = (RechargeActivityEntity)rechargeActivityComponent.getEntity(String.valueOf(request.id));
/* 29 */     if (!RechargeActivityUtil.isOpen(request.id)) {
/* 30 */       return 12301;
/*    */     }
/* 32 */     if (entity != null) {
/* 33 */       entity.getStates().forEach((k, v) -> {
/*    */             IntIntValue intIntValue = new IntIntValue();
/*    */             intIntValue.key = k.intValue();
/*    */             intIntValue.value = v.intValue();
/*    */             ((RechargeActInfoResponse)this.response).list.add(intIntValue);
/*    */           });
/* 39 */       ((RechargeActInfoResponse)this.response).value = entity.getRecharge();
/*    */     } 
/* 41 */     ((RechargeActInfoResponse)this.response).id = request.id;
/* 42 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rechargeAct\RechargeActInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */