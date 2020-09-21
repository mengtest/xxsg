/*    */ package com.linlongyx.sanguo.webgame.processors.towerOwner;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.tower.TowerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.TowerOwnerParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.towerOwner.TowerOwnerMobaiRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.towerOwner.TowerOwnerMobaiResponse;
/*    */ 
/*    */ public class TowerOwnerMobaiProcessor
/*    */   extends ProcessorBase<TowerOwnerMobaiRequest, TowerOwnerMobaiResponse> {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new TowerOwnerMobaiResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TowerOwnerMobaiRequest request) {
/* 24 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 67))
/* 25 */       return 10061; 
/* 26 */     TowerComponent towerComponent = (TowerComponent)playerSession.getPlayer().createIfNotExist(TowerComponent.class);
/* 27 */     if (towerComponent.getMobai() <= 0) {
/* 28 */       return 16701;
/*    */     }
/* 30 */     TowerOwnerParameter towerOwnerParameter = (TowerOwnerParameter)ParameterConstant.getParameter(67);
/* 31 */     towerComponent.setMobai(towerComponent.getMobai() - 1);
/* 32 */     FinanceUtil.reward(towerOwnerParameter.getMobaiReward(), playerSession.getPlayer(), ResourceEvent.TowerMobai, true);
/* 33 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\towerOwner\TowerOwnerMobaiProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */