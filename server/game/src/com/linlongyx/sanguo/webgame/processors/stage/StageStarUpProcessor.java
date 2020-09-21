/*    */ package com.linlongyx.sanguo.webgame.processors.stage;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.stage.StageComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.stage.StageEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.StageBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.stage.StageStarUpRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.stage.StageStarUpResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class StageStarUpProcessor
/*    */   extends ProcessorBase<StageStarUpRequest, StageStarUpResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 29 */     this.response = (ResponseBase)new StageStarUpResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, StageStarUpRequest request) {
/* 34 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 54))
/* 35 */       return 10061; 
/* 36 */     StageComponent stageComponent = (StageComponent)playerSession.getPlayer().createIfNotExist(StageComponent.class);
/* 37 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 38 */     StageBean stageBean = (StageBean)JsonTableService.getJsonData(request.stage, StageBean.class);
/* 39 */     if (null == stageBean) {
/* 40 */       return 10006;
/*    */     }
/* 42 */     StageEntity stageEntity = stageComponent.getEntity(request.stage);
/* 43 */     if (stageEntity == null) {
/* 44 */       return 16007;
/*    */     }
/* 46 */     Map<Integer, StageBean.StarBean> starBeanMap = stageBean.getStar();
/* 47 */     ArrayList<Reward> rewards = null;
/* 48 */     if (((StageBean.StarBean)starBeanMap.get(Integer.valueOf(stageEntity.getStar()))).getCard().isEmpty()) {
/* 49 */       return 16005;
/*    */     }
/* 51 */     rewards = FinanceUtil.transform(((StageBean.StarBean)starBeanMap.get(Integer.valueOf(stageEntity.getStar()))).getCard());
/* 52 */     short resCode = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/* 53 */     if (resCode != 0) {
/* 54 */       return resCode;
/*    */     }
/* 56 */     CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.KungFuStarUp);
/* 57 */     stageEntity.setStar(stageEntity.getStar() + 1);
/* 58 */     stageComponent.updateStarDB(stageEntity.getId());
/* 59 */     ((StageStarUpResponse)this.response).star = stageEntity.getStar();
/* 60 */     ((StageStarUpResponse)this.response).stage = request.stage;
/* 61 */     AttrUpdateUtil.refreshStage(playerSession);
/* 62 */     StageUtil.updateStageFightValue(stageEntity, playerSession, stageComponent, false);
/* 63 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 64 */     if (null != taskComponent) {
/* 65 */       taskComponent.refreshSchedule(TaskType.TotalStageStar, 0, 0L);
/*    */     }
/* 67 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\stage\StageStarUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */