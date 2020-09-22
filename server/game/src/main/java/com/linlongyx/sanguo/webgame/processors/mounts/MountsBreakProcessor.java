/*    */ package com.linlongyx.sanguo.webgame.processors.mounts;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.mounts.MountsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.mounts.MountsEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MountLevelBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.MountsBreakRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.MountsBreakResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MountsBreakProcessor
/*    */   extends ProcessorBase<MountsBreakRequest, MountsBreakResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new MountsBreakResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, MountsBreakRequest request) {
/* 31 */     MountsComponent mountsComponent = (MountsComponent)playerSession.getPlayer().createIfNotExist(MountsComponent.class);
/* 32 */     MountsEntity mountsEntity = mountsComponent.getEntity(request.mounts);
/* 33 */     if (null == mountsEntity) {
/* 34 */       return 19002;
/*    */     }
/* 36 */     MountLevelBean mountLevelBean = (MountLevelBean)JsonTableService.getJsonData(mountsEntity.getLevel(), MountLevelBean.class);
/* 37 */     if (mountLevelBean.getBreakC().isEmpty()) {
/* 38 */       return 19003;
/*    */     }
/* 40 */     if (mountsEntity.getBreaksLevel().containsKey(Integer.valueOf(mountsEntity.getLevel()))) {
/* 41 */       return 19003;
/*    */     }
/* 43 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 44 */     short code = CostUtil.checkRewards(FinanceUtil.transform(mountLevelBean.getBreakC()), playerSession, bagComponent);
/* 45 */     if (code != 0) {
/* 46 */       return code;
/*    */     }
/* 48 */     CostUtil.costs(FinanceUtil.transform(mountLevelBean.getBreakC()), playerSession, bagComponent, ResourceEvent.MountsBreak);
/* 49 */     mountsEntity.getBreaksLevel().put(Integer.valueOf(mountsEntity.getLevel()), Integer.valueOf(TimeUtil.currentTime()));
/* 50 */     mountsComponent.updateBreakDB(request.mounts);
/* 51 */     ((MountsBreakResponse)this.response).mounts = request.mounts;
/* 52 */     ((MountsBreakResponse)this.response).breaksLevel.addAll(mountsEntity.getBreaksLevel().keySet());
/* 53 */     MountsUtil.updateMountsFightValue(mountsEntity, playerSession, mountsComponent, false);
/* 54 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mounts\MountsBreakProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */