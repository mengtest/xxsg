/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.WorldBossFightSide;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldRewardInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldRewardInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BossDamageData;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldRewardInfoProcessor
/*    */   extends ProcessorBase<WorldRewardInfoRequest, WorldRewardInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new WorldRewardInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WorldRewardInfoRequest request) {
/* 26 */     BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/* 27 */     ((WorldRewardInfoResponse)this.response).list = new ArrayList(bossHomeComponent.getWorldRewards());
/* 28 */     WorldBossFightSide worldBossFightSide = WorldBossUtil.getWorldBossFightSide();
/*    */     
/* 30 */     if (worldBossFightSide == null || !worldBossFightSide.isOpen()) {
/* 31 */       return 0;
/*    */     }
/* 33 */     BossDamageData damageData = (BossDamageData)worldBossFightSide.getDamageMap().get(Long.valueOf(playerSession.getPlayer().getPlayerId()));
/* 34 */     if (damageData != null) {
/* 35 */       ((WorldRewardInfoResponse)this.response).hurt = damageData.damage;
/*    */     }
/* 37 */     ((WorldRewardInfoResponse)this.response).killNum = worldBossFightSide.getKillNum();
/* 38 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\WorldRewardInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */