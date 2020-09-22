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
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.kungfu.KungFuStarUpRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.kungfu.KungFuStarUpResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class KungFuStarUpProcessor
/*    */   extends ProcessorBase<KungFuStarUpRequest, KungFuStarUpResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 29 */     this.response = (ResponseBase)new KungFuStarUpResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, KungFuStarUpRequest request) {
/* 34 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 53))
/* 35 */       return 10061; 
/* 36 */     KungFuComponent kungFuComponent = (KungFuComponent)playerSession.getPlayer().createIfNotExist(KungFuComponent.class);
/* 37 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 38 */     KungfuBean kungfuBean = (KungfuBean)JsonTableService.getJsonData(request.kungfu, KungfuBean.class);
/* 39 */     if (null == kungfuBean) {
/* 40 */       return 10006;
/*    */     }
/* 42 */     KungFuEntity kungFuEntity = kungFuComponent.getEntity(request.kungfu);
/* 43 */     if (kungFuEntity == null) {
/* 44 */       return 16007;
/*    */     }
/* 46 */     Map<Integer, KungfuBean.StarBean> starBeanMap = kungfuBean.getStar();
/* 47 */     ArrayList<Reward> rewards = null;
/* 48 */     if (((KungfuBean.StarBean)starBeanMap.get(Integer.valueOf(kungFuEntity.getStar()))).getCard().isEmpty()) {
/* 49 */       return 16005;
/*    */     }
/* 51 */     rewards = FinanceUtil.transform(((KungfuBean.StarBean)starBeanMap.get(Integer.valueOf(kungFuEntity.getStar()))).getCard());
/* 52 */     short resCode = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/* 53 */     if (resCode != 0) {
/* 54 */       return resCode;
/*    */     }
/* 56 */     CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.KungFuStarUp);
/* 57 */     kungFuEntity.setStar(kungFuEntity.getStar() + 1);
/* 58 */     kungFuComponent.updateStarDB(kungFuEntity.getKungFuId());
/* 59 */     ((KungFuStarUpResponse)this.response).star = kungFuEntity.getStar();
/* 60 */     ((KungFuStarUpResponse)this.response).kungfu = request.kungfu;
/* 61 */     AttrUpdateUtil.refreshKungFu(playerSession);
/* 62 */     KungFuUtil.updateKungfuFightValue(kungFuEntity, playerSession, kungFuComponent, false);
/* 63 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 64 */     if (null != taskComponent) {
/* 65 */       taskComponent.refreshSchedule(TaskType.TotalKungFuStar, 0, 0L);
/*    */     }
/* 67 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\kungfu\KungFuStarUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */