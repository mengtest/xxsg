/*    */ package com.linlongyx.sanguo.webgame.processors.warlineup;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.ActivityWarLineupRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.ActivityWarLineupResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ActivityWarLineupProcessor
/*    */   extends ProcessorBase<ActivityWarLineupRequest, ActivityWarLineupResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 29 */     this.response = (ResponseBase)new ActivityWarLineupResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ActivityWarLineupRequest request) {
/* 34 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 57))
/* 35 */       return 10061; 
/* 36 */     WarLineupComponent warLineupComponent = (WarLineupComponent)playerSession.getPlayer().createIfNotExist(WarLineupComponent.class);
/* 37 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 38 */     ZhenfaBean zhenfaBean = (ZhenfaBean)JsonTableService.getJsonData(request.warlineup, ZhenfaBean.class);
/* 39 */     if (null == zhenfaBean) {
/* 40 */       return 10006;
/*    */     }
/* 42 */     WarLineupEntity warLineupEntity = warLineupComponent.getEntity(request.warlineup);
/* 43 */     if (warLineupEntity != null) {
/* 44 */       return 15201;
/*    */     }
/* 46 */     Map<Integer, ZhenfaBean.StarBean> starBeanMap = zhenfaBean.getStar();
/* 47 */     ArrayList<Reward> rewards = null;
/* 48 */     if (starBeanMap.get(Integer.valueOf(-1)) == null) {
/* 49 */       return 10006;
/*    */     }
/* 51 */     rewards = FinanceUtil.transformWarLineup(((ZhenfaBean.StarBean)starBeanMap.get(Integer.valueOf(-1))).getCard());
/* 52 */     short resCode = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/* 53 */     if (resCode != 0) {
/* 54 */       return resCode;
/*    */     }
/* 56 */     CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.ActWarZhenfa);
/* 57 */     warLineupComponent.addWarLineup(request.warlineup);
/* 58 */     warLineupEntity = warLineupComponent.getEntity(request.warlineup);
/* 59 */     ((ActivityWarLineupResponse)this.response).data.warLineup = request.warlineup;
/* 60 */     ((ActivityWarLineupResponse)this.response).data.star = warLineupEntity.getStar();
/* 61 */     ((ActivityWarLineupResponse)this.response).data.level = warLineupEntity.getLevel();
/* 62 */     ((ActivityWarLineupResponse)this.response).data.battle = warLineupEntity.getBattle();
/* 63 */     WarLineupUtil.updateWarLineupFightValue(warLineupEntity, playerSession, warLineupComponent, true);
/* 64 */     ((ActivityWarLineupResponse)this.response).data.fightValue = warLineupEntity.getFightValue();
/* 65 */     AttrUpdateUtil.refreshWarLineup(playerSession);
/* 66 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 67 */     if (null != taskComponent) {
/* 68 */       taskComponent.refreshSchedule(TaskType.ActZhenFa, 0, 0L);
/*    */     }
/* 70 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\warlineup\ActivityWarLineupProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */