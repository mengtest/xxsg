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
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.MountsActivityRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.MountsActivityResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class MountsActivityProcessor
/*    */   extends ProcessorBase<MountsActivityRequest, MountsActivityResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 30 */     this.response = (ResponseBase)new MountsActivityResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, MountsActivityRequest request) {
/* 35 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 42)) {
/* 36 */       return 10061;
/*    */     }
/* 38 */     MountsComponent mountsComponent = (MountsComponent)playerSession.getPlayer().createIfNotExist(MountsComponent.class);
/* 39 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 40 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 41 */     MountBean mountBean = (MountBean)JsonTableService.getJsonData(request.mounts, MountBean.class);
/* 42 */     if (null == mountBean) {
/* 43 */       return 10006;
/*    */     }
/* 45 */     MountsEntity mountsEntity = mountsComponent.getEntity(request.mounts);
/* 46 */     if (mountsEntity != null) {
/* 47 */       return 16001;
/*    */     }
/* 49 */     Map<Integer, MountBean.StarBean> starBeanMap = mountBean.getStar();
/* 50 */     ArrayList<Reward> rewards = null;
/* 51 */     if (starBeanMap.get(Integer.valueOf(-1)) == null) {
/* 52 */       return 10006;
/*    */     }
/* 54 */     rewards = FinanceUtil.transform(((MountBean.StarBean)starBeanMap.get(Integer.valueOf(-1))).getCard());
/* 55 */     short resCode = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/* 56 */     if (resCode != 0) {
/* 57 */       return resCode;
/*    */     }
/* 59 */     CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.MountsActivity);
/* 60 */     mountsComponent.addMounts(request.mounts);
/* 61 */     mountsEntity = mountsComponent.getEntity(request.mounts);
/* 62 */     ((MountsActivityResponse)this.response).data.mounts = request.mounts;
/* 63 */     ((MountsActivityResponse)this.response).data.star = mountsEntity.getStar();
/* 64 */     ((MountsActivityResponse)this.response).data.level = mountsEntity.getLevel();
/* 65 */     ((MountsActivityResponse)this.response).data.battle = mountsEntity.getBattle();
/* 66 */     ((MountsActivityResponse)this.response).data.breaksLevel.addAll(mountsEntity.getBreaksLevel().keySet());
/*    */     
/* 68 */     AttrUpdateUtil.refreshMounts(playerSession);
/*    */     
/* 70 */     PlayerUtil.sendNotice(4, playerSession.getPlayer(), mountsEntity.getMountsId(), null);
/* 71 */     if (null != taskComponent) {
/* 72 */       taskComponent.refreshSchedule(TaskType.ActivityMounts, 0, 0L);
/*    */     }
/* 74 */     MountsUtil.updateMountsFightValue(mountsEntity, playerSession, mountsComponent, true);
/* 75 */     ((MountsActivityResponse)this.response).data.fightValue = mountsEntity.getFightValue();
/* 76 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mounts\MountsActivityProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */