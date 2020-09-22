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
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.stage.ActivityStageRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.stage.ActivityStageResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ActivityStageProcessor
/*    */   extends ProcessorBase<ActivityStageRequest, ActivityStageResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 29 */     this.response = (ResponseBase)new ActivityStageResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ActivityStageRequest request) {
/* 34 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 54))
/* 35 */       return 10061; 
/* 36 */     StageComponent stageComponent = (StageComponent)playerSession.getPlayer().createIfNotExist(StageComponent.class);
/* 37 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 38 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 39 */     StageBean stageBean = (StageBean)JsonTableService.getJsonData(request.stage, StageBean.class);
/* 40 */     if (null == stageBean) {
/* 41 */       return 10006;
/*    */     }
/* 43 */     StageEntity stageEntity = stageComponent.getEntity(request.stage);
/* 44 */     if (stageEntity != null) {
/* 45 */       return 15005;
/*    */     }
/* 47 */     Map<Integer, StageBean.StarBean> starBeanMap = stageBean.getStar();
/* 48 */     ArrayList<Reward> rewards = null;
/* 49 */     if (starBeanMap.get(Integer.valueOf(-1)) == null) {
/* 50 */       return 10006;
/*    */     }
/* 52 */     rewards = FinanceUtil.transform(((StageBean.StarBean)starBeanMap.get(Integer.valueOf(-1))).getCard());
/* 53 */     short resCode = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/* 54 */     if (resCode != 0) {
/* 55 */       return resCode;
/*    */     }
/* 57 */     CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.ActivityStage);
/* 58 */     stageComponent.addStage(request.stage);
/* 59 */     stageEntity = stageComponent.getEntity(request.stage);
/* 60 */     ((ActivityStageResponse)this.response).data.id = request.stage;
/* 61 */     ((ActivityStageResponse)this.response).data.star = stageEntity.getStar();
/* 62 */     ((ActivityStageResponse)this.response).data.level = stageEntity.getLevel();
/* 63 */     ((ActivityStageResponse)this.response).data.battle = stageEntity.getBattle();
/* 64 */     ((ActivityStageResponse)this.response).data.exp = stageEntity.getExp();
/*    */     
/* 66 */     AttrUpdateUtil.refreshStage(playerSession);
/* 67 */     StageUtil.updateStageFightValue(stageEntity, playerSession, stageComponent, true);
/* 68 */     ((ActivityStageResponse)this.response).data.fightValue = stageEntity.getFightValue();
/* 69 */     if (null != taskComponent) {
/* 70 */       taskComponent.refreshSchedule(TaskType.ActStage, 0, 0L);
/*    */     }
/* 72 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\stage\ActivityStageProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */