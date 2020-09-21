/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.WorldBossFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BossWorldRewardBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BossDamageData;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldRewardProcessor
/*    */   extends ProcessorBase<WorldRewardRequest, WorldRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new WorldRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WorldRewardRequest request) {
/* 31 */     int id = request.id;
/* 32 */     BossWorldRewardBean bossWorldRewardBean = (BossWorldRewardBean)JsonTableService.getJsonData(id, BossWorldRewardBean.class);
/* 33 */     if (null == bossWorldRewardBean) {
/* 34 */       return 10301;
/*    */     }
/*    */     
/* 37 */     WorldBossFightSide worldBossFightSide = WorldBossUtil.getWorldBossFightSide();
/* 38 */     if (worldBossFightSide == null || !worldBossFightSide.isOpen()) {
/* 39 */       return 10302;
/*    */     }
/* 41 */     BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/* 42 */     List<Integer> list = bossHomeComponent.getWorldRewards();
/* 43 */     if (list.contains(Integer.valueOf(id))) {
/* 44 */       return 10304;
/*    */     }
/* 46 */     if (bossWorldRewardBean.getType() == 1) {
/* 47 */       BossDamageData bossDamageData = (BossDamageData)worldBossFightSide.getDamageMap().get(Long.valueOf(playerSession.getPlayer().getPlayerId()));
/* 48 */       if (bossDamageData.damage < bossWorldRewardBean.getPoint()) {
/* 49 */         return 10305;
/*    */       }
/* 51 */     } else if (bossWorldRewardBean.getType() == 2) {
/* 52 */       if (worldBossFightSide.getKillNum() < bossWorldRewardBean.getPoint()) {
/* 53 */         return 10305;
/*    */       }
/*    */     } else {
/* 56 */       return 10303;
/*    */     } 
/* 58 */     list.add(Integer.valueOf(id));
/* 59 */     bossHomeComponent.setWorldRewards(list);
/*    */     
/* 61 */     FinanceUtil.rewardGet(FinanceUtil.transform(bossWorldRewardBean.getReward()), playerSession.getPlayer(), ResourceEvent.WorldBossReward, true);
/*    */     
/* 63 */     ((WorldRewardResponse)this.response).id = id;
/* 64 */     LogUtils.errorLog(new Object[] { "WorldReward", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(id) });
/* 65 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\WorldRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */