/*    */ package com.linlongyx.sanguo.webgame.processors.kungfu;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.KungfuBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.kungfu.ActivityKungFuRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.kungfu.ActivityKungFuResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ActivityKungFuProcessor
/*    */   extends ProcessorBase<ActivityKungFuRequest, ActivityKungFuResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 29 */     this.response = (ResponseBase)new ActivityKungFuResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ActivityKungFuRequest request) {
/* 34 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 53))
/* 35 */       return 10061; 
/* 36 */     KungFuComponent kungFuComponent = (KungFuComponent)playerSession.getPlayer().createIfNotExist(KungFuComponent.class);
/* 37 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 38 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 39 */     KungfuBean kungfuBean = (KungfuBean)JsonTableService.getJsonData(request.kungfu, KungfuBean.class);
/* 40 */     if (null == kungfuBean) {
/* 41 */       return 10006;
/*    */     }
/* 43 */     KungFuEntity kungFuEntity = kungFuComponent.getEntity(request.kungfu);
/* 44 */     if (kungFuEntity != null) {
/* 45 */       return 15005;
/*    */     }
/* 47 */     Map<Integer, KungfuBean.StarBean> starBeanMap = kungfuBean.getStar();
/* 48 */     ArrayList<Reward> rewards = null;
/* 49 */     if (starBeanMap.get(Integer.valueOf(-1)) == null) {
/* 50 */       return 10006;
/*    */     }
/* 52 */     rewards = FinanceUtil.transform(((KungfuBean.StarBean)starBeanMap.get(Integer.valueOf(-1))).getCard());
/* 53 */     short resCode = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/* 54 */     if (resCode != 0) {
/* 55 */       return resCode;
/*    */     }
/* 57 */     CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.ActivityKungFu);
/* 58 */     kungFuComponent.addKungFu(request.kungfu);
/* 59 */     kungFuEntity = kungFuComponent.getEntity(request.kungfu);
/* 60 */     ((ActivityKungFuResponse)this.response).data.kungFu = request.kungfu;
/* 61 */     ((ActivityKungFuResponse)this.response).data.star = kungFuEntity.getStar();
/* 62 */     ((ActivityKungFuResponse)this.response).data.level = kungFuEntity.getLevel();
/* 63 */     ((ActivityKungFuResponse)this.response).data.battle = kungFuEntity.getBattle();
/* 64 */     ((ActivityKungFuResponse)this.response).data.exp = kungFuEntity.getExp();
/*    */     
/* 66 */     AttrUpdateUtil.refreshKungFu(playerSession);
/* 67 */     KungFuUtil.updateKungfuFightValue(kungFuEntity, playerSession, kungFuComponent, true);
/* 68 */     ((ActivityKungFuResponse)this.response).data.fightValue = kungFuEntity.getFightValue();
/* 69 */     if (null != taskComponent) {
/* 70 */       taskComponent.refreshSchedule(TaskType.ActKungFu, 0, 0L);
/*    */     }
/* 72 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\kungfu\ActivityKungFuProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */