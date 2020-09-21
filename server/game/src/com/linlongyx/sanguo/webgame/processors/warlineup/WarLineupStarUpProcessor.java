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
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.WarLineupStarUpRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.WarLineupStarUpResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WarLineupStarUpProcessor
/*    */   extends ProcessorBase<WarLineupStarUpRequest, WarLineupStarUpResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 31 */     this.response = (ResponseBase)new WarLineupStarUpResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WarLineupStarUpRequest request) {
/* 36 */     WarLineupComponent warLineupComponent = (WarLineupComponent)playerSession.getPlayer().createIfNotExist(WarLineupComponent.class);
/* 37 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 38 */     ZhenfaBean ZhenfaBean = (ZhenfaBean)JsonTableService.getJsonData(request.warlineup, ZhenfaBean.class);
/* 39 */     if (null == ZhenfaBean) {
/* 40 */       return 10006;
/*    */     }
/* 42 */     WarLineupEntity warLineupEntity = warLineupComponent.getEntity(request.warlineup);
/* 43 */     if (warLineupEntity == null) {
/* 44 */       return 15202;
/*    */     }
/* 46 */     Map<Integer, ZhenfaBean.StarBean> starBeanMap = ZhenfaBean.getStar();
/* 47 */     ArrayList<Reward> rewards = null;
/* 48 */     if (((ZhenfaBean.StarBean)starBeanMap.get(Integer.valueOf(warLineupEntity.getStar()))).getCard().isEmpty()) {
/* 49 */       return 15203;
/*    */     }
/* 51 */     rewards = FinanceUtil.transformWarLineup(((ZhenfaBean.StarBean)starBeanMap.get(Integer.valueOf(warLineupEntity.getStar()))).getCard());
/* 52 */     short resCode = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/* 53 */     if (resCode != 0) {
/* 54 */       return resCode;
/*    */     }
/* 56 */     CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.WarZhenfaStarUp);
/* 57 */     warLineupEntity.setStar(warLineupEntity.getStar() + 1);
/* 58 */     ((WarLineupStarUpResponse)this.response).star = warLineupEntity.getStar();
/* 59 */     ((WarLineupStarUpResponse)this.response).warlineup = request.warlineup;
/* 60 */     AttrUpdateUtil.refreshWarLineup(playerSession);
/* 61 */     WarLineupUtil.updateWarLineupFightValue(warLineupEntity, playerSession, warLineupComponent, false);
/* 62 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 63 */     if (null != taskComponent) {
/* 64 */       taskComponent.refreshSchedule(TaskType.TotalZhenFaStar, 0, 0L);
/*    */     }
/* 66 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\warlineup\WarLineupStarUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */