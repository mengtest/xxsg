/*    */ package com.linlongyx.sanguo.webgame.processors.tower;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.tower.TowerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TowerBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.tower.TowerRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.tower.TowerRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ public class TowerRewardProcessor extends ProcessorBase<TowerRewardRequest, TowerRewardResponse> {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new TowerRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TowerRewardRequest request) {
/* 26 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 40))
/* 27 */       return 10061; 
/* 28 */     TowerComponent towerComponent = (TowerComponent)playerSession.getPlayer().createIfNotExist(TowerComponent.class);
/*    */     
/* 30 */     if (request.type == 0) {
/*    */       
/* 32 */       if (towerComponent.getPreLayers() == 0) {
/* 33 */         return 16803;
/*    */       }
/* 35 */       TowerBean towerBean = (TowerBean)JsonTableService.getJsonData(towerComponent.getPreLayers(), TowerBean.class);
/* 36 */       if (null == towerBean) {
/* 37 */         return 16804;
/*    */       }
/* 39 */       if (towerComponent.getTodayNormalReward() == 1) {
/* 40 */         return 16805;
/*    */       }
/* 42 */       towerComponent.setTodayNormalReward(1);
/* 43 */       FinanceUtil.reward(FinanceUtil.transform(towerBean.getOrdinaryReward()), playerSession.getPlayer(), ResourceEvent.TowerDailyReward, true);
/* 44 */     } else if (request.type == 1) {
/*    */       
/* 46 */       if (towerComponent.getPreLayers() == 0) {
/* 47 */         return 16803;
/*    */       }
/* 49 */       TowerBean towerBean = (TowerBean)JsonTableService.getJsonData(towerComponent.getPreLayers(), TowerBean.class);
/* 50 */       if (null == towerBean) {
/* 51 */         return 16804;
/*    */       }
/* 53 */       if (towerComponent.getTodayCCYReward() == 1) {
/* 54 */         return 16805;
/*    */       }
/* 56 */       if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, towerBean.getMoney())) {
/* 57 */         return 10052;
/*    */       }
/* 59 */       FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, towerBean.getMoney(), ResourceEvent.TowerBoxCost);
/* 60 */       towerComponent.setTodayCCYReward(1);
/* 61 */       FinanceUtil.reward(FinanceUtil.transform(towerBean.getAdditionalReward()), playerSession.getPlayer(), ResourceEvent.TowerDailyReward, true);
/* 62 */       TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 63 */       if (null != taskComponent) {
/* 64 */         taskComponent.refreshSchedule(TaskType.TowerExtendReward, 0, 1L);
/*    */       }
/*    */     } 
/* 67 */     ((TowerRewardResponse)this.response).type = request.type;
/* 68 */     ((TowerRewardResponse)this.response).todayNormalReward = towerComponent.getTodayNormalReward();
/* 69 */     ((TowerRewardResponse)this.response).todayCCYReward = towerComponent.getTodayCCYReward();
/* 70 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\tower\TowerRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */