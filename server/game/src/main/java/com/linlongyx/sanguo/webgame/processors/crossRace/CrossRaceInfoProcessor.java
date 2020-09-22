/*    */ package com.linlongyx.sanguo.webgame.processors.crossRace;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.crossRace.CrossRaceComponent;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossRace.CrossRaceInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossRace.CrossRaceInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossRaceInfoProcessor
/*    */   extends ProcessorBase<CrossRaceInfoRequest, CrossRaceInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new CrossRaceInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossRaceInfoRequest request) {
/* 31 */     CrossRaceComponent crossRaceComponent = (CrossRaceComponent)playerSession.getPlayer().createIfNotExist(CrossRaceComponent.class);
/* 32 */     int curRaceId = CrossUtil.getCurRaceId("CrossRaceInfoProcessor");
/* 33 */     if (curRaceId <= 0) {
/* 34 */       return 0;
/*    */     }
/* 36 */     int curState = CrossUtil.getRaceState("CrossRaceInfoProcessor");
/* 37 */     if (crossRaceComponent.getRaceId() != curRaceId) {
/* 38 */       crossRaceComponent.resetRaceData(curRaceId);
/*    */       
/* 40 */       ((CrossRaceInfoResponse)this.response).state = curState;
/* 41 */       ((CrossRaceInfoResponse)this.response).point = crossRaceComponent.getPoint();
/* 42 */       ((CrossRaceInfoResponse)this.response).rank = 0;
/* 43 */       ((CrossRaceInfoResponse)this.response).winTimes = crossRaceComponent.getWinTimes();
/* 44 */       ((CrossRaceInfoResponse)this.response).fightTimes = crossRaceComponent.getFightTimes();
/* 45 */       ((CrossRaceInfoResponse)this.response).times = crossRaceComponent.getTimes();
/* 46 */       return 0;
/*    */     } 
/* 48 */     if (crossRaceComponent.getPoint() == 0) {
/* 49 */       ((CrossRaceInfoResponse)this.response).rank = 0;
/*    */     } else {
/* 51 */       RankingData data = CrossUtil.getPlayerRaceRank("", playerSession.getPlayer().getPlayerId(), crossRaceComponent.getPoint());
/* 52 */       if (data != null) {
/* 53 */         ((CrossRaceInfoResponse)this.response).rank = data.rank;
/*    */       }
/*    */     } 
/* 56 */     ((CrossRaceInfoResponse)this.response).state = curState;
/* 57 */     ((CrossRaceInfoResponse)this.response).point = crossRaceComponent.getPoint();
/* 58 */     ((CrossRaceInfoResponse)this.response).winTimes = crossRaceComponent.getWinTimes();
/* 59 */     ((CrossRaceInfoResponse)this.response).fightTimes = crossRaceComponent.getFightTimes();
/* 60 */     ((CrossRaceInfoResponse)this.response).times = crossRaceComponent.getTimes();
/* 61 */     for (Map.Entry<Integer, Integer> kv : (Iterable<Map.Entry<Integer, Integer>>)crossRaceComponent.getJoinReward().entrySet()) {
/* 62 */       if (((Integer)kv.getValue()).intValue() == 2) {
/* 63 */         ((CrossRaceInfoResponse)this.response).rewardList.add(kv.getKey());
/*    */       }
/*    */     } 
/* 66 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossRace\CrossRaceInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */