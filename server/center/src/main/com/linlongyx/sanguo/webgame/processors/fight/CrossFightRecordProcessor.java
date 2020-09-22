/*    */ package com.linlongyx.sanguo.webgame.processors.fight;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.CrossFightRecordRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.CrossFightRecordResponse;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.Battle;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleResource;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossFightRecordProcessor
/*    */   extends ProcessorBase<CrossFightRecordRequest, CrossFightRecordResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new CrossFightRecordResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossFightRecordRequest request) {
/* 27 */     int type = request.type;
/* 28 */     String insId = request.id;
/* 29 */     ((CrossFightRecordResponse)this.response).type = request.type;
/* 30 */     ((CrossFightRecordResponse)this.response).id = request.id;
/* 31 */     if (type == 50) {
/* 32 */       return handleBattleFight(playerSession.getPlayer(), Integer.parseInt(insId), (CrossFightRecordResponse)this.response);
/*    */     }
/* 34 */     return 0;
/*    */   }
/*    */   
/*    */   private short handleBattleFight(IPlayer player, int resourceId, CrossFightRecordResponse response) {
/* 38 */     Battle battle = BattleUtil.getCurBattle(player.getPlayerId());
/* 39 */     if (battle == null) {
/* 40 */       return 11302;
/*    */     }
/* 42 */     BattleResource battleResource = (BattleResource)battle.getResourceMap().get(Integer.valueOf(resourceId));
/* 43 */     if (battleResource == null) {
/* 44 */       return 11308;
/*    */     }
/* 46 */     if (battleResource.getType() != BattleResource.ResourceType.RESOURCE || (battleResource
/* 47 */       .getType() == BattleResource.ResourceType.RESOURCE && battleResource.isCampOwn())) {
/* 48 */       return 11308;
/*    */     }
/* 50 */     return battleResource.battle(battle, player.getPlayerId(), response);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\fight\CrossFightRecordProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */