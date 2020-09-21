/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BossHomeBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.ReviveRankBossRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.ReviveRankBossResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReviveRankBossProcessor
/*    */   extends ProcessorBase<ReviveRankBossRequest, ReviveRankBossResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new ReviveRankBossResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ReviveRankBossRequest request) {
/* 27 */     BossHomeBean bossHomeBean = (BossHomeBean)JsonTableService.getJsonData(request.bossId, BossHomeBean.class);
/* 28 */     if (null == bossHomeBean) {
/* 29 */       return 11808;
/*    */     }
/* 31 */     BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/*    */     
/* 33 */     if (bossHomeBean.getFresh() == null || bossHomeBean.getFresh().isEmpty()) {
/* 34 */       return 10310;
/*    */     }
/* 36 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().getComponent(BagComponent.class);
/* 37 */     short retcode = CostUtil.check(bossHomeBean.getFresh(), playerSession, bagComponent);
/* 38 */     if (retcode != 0)
/* 39 */       return retcode; 
/* 40 */     if ((BossUtil.getBossData(request.bossId)).isOpen) {
/* 41 */       return 10311;
/*    */     }
/*    */     
/* 44 */     CostUtil.cost(bossHomeBean.getFresh(), playerSession, bagComponent, ResourceEvent.RankbossRevive);
/* 45 */     BossUtil.bossBorn(request.bossId);
/*    */     
/* 47 */     ((ReviveRankBossResponse)this.response).bossId = request.bossId;
/* 48 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\ReviveRankBossProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */