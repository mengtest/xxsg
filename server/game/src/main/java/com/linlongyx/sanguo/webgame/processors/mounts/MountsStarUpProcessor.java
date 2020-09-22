/*    */ package com.linlongyx.sanguo.webgame.processors.mounts;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.mounts.MountsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.mounts.MountsEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MountBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.MountsStarUpRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.MountsStarUpResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MountsStarUpProcessor
/*    */   extends ProcessorBase<MountsStarUpRequest, MountsStarUpResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 31 */     this.response = (ResponseBase)new MountsStarUpResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, MountsStarUpRequest request) {
/* 36 */     MountsComponent mountsComponent = (MountsComponent)playerSession.getPlayer().createIfNotExist(MountsComponent.class);
/* 37 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 38 */     MountBean mountBean = (MountBean)JsonTableService.getJsonData(request.mounts, MountBean.class);
/* 39 */     if (null == mountBean) {
/* 40 */       return 10006;
/*    */     }
/* 42 */     MountsEntity mountsEntity = mountsComponent.getEntity(request.mounts);
/* 43 */     if (mountsEntity == null) {
/* 44 */       return 19002;
/*    */     }
/* 46 */     Map<Integer, MountBean.StarBean> starBeanMap = mountBean.getStar();
/*    */     
/* 48 */     if (((MountBean.StarBean)starBeanMap.get(Integer.valueOf(mountsEntity.getStar()))).getCard().isEmpty()) {
/* 49 */       return 16005;
/*    */     }
/* 51 */     ArrayList<Reward> rewards = FinanceUtil.transform(((MountBean.StarBean)starBeanMap.get(Integer.valueOf(mountsEntity.getStar()))).getCard());
/* 52 */     short resCode = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/* 53 */     if (resCode != 0) {
/* 54 */       return resCode;
/*    */     }
/* 56 */     CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.MountsStarUp);
/* 57 */     mountsEntity.setStar(mountsEntity.getStar() + 1);
/* 58 */     mountsComponent.updateStarDB(request.mounts);
/* 59 */     ((MountsStarUpResponse)this.response).star = mountsEntity.getStar();
/* 60 */     ((MountsStarUpResponse)this.response).mounts = request.mounts;
/* 61 */     AttrUpdateUtil.refreshMounts(playerSession);
/* 62 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 63 */     if (null != taskComponent) {
/* 64 */       taskComponent.refreshSchedule(TaskType.MountsTotalStar, 0, 0L);
/*    */     }
/* 66 */     MountsUtil.updateMountsFightValue(mountsEntity, playerSession, mountsComponent, false);
/* 67 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mounts\MountsStarUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */