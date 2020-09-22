/*    */ package com.linlongyx.sanguo.webgame.processors.crossRace;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.crossRace.CrossRaceComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossRace.CrossRaceMatchRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossRace.CrossRaceMatchResponse;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.RacePlayerData;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossRaceMatchProcessor
/*    */   extends ProcessorBase<CrossRaceMatchRequest, CrossRaceMatchResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new CrossRaceMatchResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossRaceMatchRequest request) {
/* 25 */     CrossRaceComponent crossRaceComponent = (CrossRaceComponent)playerSession.getPlayer().createIfNotExist(CrossRaceComponent.class);
/* 26 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 27 */     int curRaceId = CrossUtil.getCurRaceId("CrossRaceInfoProcessor");
/* 28 */     boolean isOpen = CrossUtil.isRaceOpen("CrossRaceInfoProcessor");
/* 29 */     if (curRaceId <= 0 || !isOpen) {
/* 30 */       return 12801;
/*    */     }
/* 32 */     if (crossRaceComponent.getCacheRacePlayerData() != null) {
/* 33 */       ((CrossRaceMatchResponse)this.response).playerId = crossRaceComponent.getCacheRacePlayerData().getPlayerData().getPlayerId();
/*    */     } else {
/* 35 */       RacePlayerData recommentData = CrossUtil.recommentOne("CrossRaceMatchProcessor", playerComponent.getPlayerId(), playerComponent.getTotalValue(), crossRaceComponent.getPoint());
/* 36 */       if (recommentData == null) {
/* 37 */         return 12802;
/*    */       }
/* 39 */       crossRaceComponent.setCacheRacePlayerData(recommentData);
/* 40 */       ((CrossRaceMatchResponse)this.response).playerId = recommentData.getPlayerData().getPlayerId();
/*    */     } 
/* 42 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossRace\CrossRaceMatchProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */