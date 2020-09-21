/*    */ package com.linlongyx.sanguo.webgame.processors.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBattleRankRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBattleRankResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyRankData;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyBattleRankProcessor
/*    */   extends ProcessorBase<DestinyBattleRankRequest, DestinyBattleRankResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new DestinyBattleRankResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DestinyBattleRankRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 74)) {
/* 31 */       return 10061;
/*    */     }
/*    */     
/* 34 */     ArrayList<DestinyRankData> resultList = DestinyUtil.getDestinyRankList(request.size);
/* 35 */     DestinyRankData myRankData = DestinyUtil.getTargetPlayerRankData(playerSession.getPlayer().getPlayerId());
/*    */     
/* 37 */     ((DestinyBattleRankResponse)this.response).datas = resultList;
/* 38 */     ((DestinyBattleRankResponse)this.response).myrank = myRankData.rank;
/* 39 */     ((DestinyBattleRankResponse)this.response).destinyPoint = myRankData.destinyPoint;
/* 40 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\destiny\DestinyBattleRankProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */