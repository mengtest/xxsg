/*    */ package com.linlongyx.sanguo.webgame.processors.rechargeAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.rechargeActivity.RechargeActivityComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.rechargeActivity.RechargeActivityEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.rechargeActivity.RechargeActivityUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TiringrebatelistBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rechargeAct.RechargeActRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rechargeAct.RechargeActRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RechargeActRewardProcessor
/*    */   extends ProcessorBase<RechargeActRewardRequest, RechargeActRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new RechargeActRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RechargeActRewardRequest request) {
/* 30 */     RechargeActivityComponent rechargeActivityComponent = (RechargeActivityComponent)playerSession.getPlayer().createIfNotExist(RechargeActivityComponent.class);
/* 31 */     RechargeActivityEntity entity = (RechargeActivityEntity)rechargeActivityComponent.getEntity(String.valueOf(request.id));
/* 32 */     if (entity == null || !RechargeActivityUtil.isOpen(request.id)) {
/* 33 */       return 12301;
/*    */     }
/* 35 */     if (!entity.getStates().containsKey(Integer.valueOf(request.itemId))) {
/* 36 */       return 12302;
/*    */     }
/* 38 */     if (((Integer)entity.getStates().get(Integer.valueOf(request.itemId))).intValue() == 2) {
/* 39 */       return 12303;
/*    */     }
/* 41 */     if (((Integer)entity.getStates().get(Integer.valueOf(request.itemId))).intValue() == 1) {
/* 42 */       TiringrebatelistBean bean = (TiringrebatelistBean)JsonTableService.getJsonData(request.itemId, TiringrebatelistBean.class);
/* 43 */       FinanceUtil.reward(FinanceUtil.transform(bean.getReward()), playerSession.getPlayer(), ResourceEvent.RechargeAct, true);
/* 44 */       entity.getStates().put(Integer.valueOf(request.itemId), Integer.valueOf(2));
/* 45 */       IntIntValue intIntValue = new IntIntValue();
/* 46 */       intIntValue.key = request.itemId;
/* 47 */       intIntValue.value = ((Integer)entity.getStates().get(Integer.valueOf(request.itemId))).intValue();
/* 48 */       ((RechargeActRewardResponse)this.response).id = request.id;
/* 49 */       ((RechargeActRewardResponse)this.response).data = intIntValue;
/*    */     } 
/* 51 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rechargeAct\RechargeActRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */