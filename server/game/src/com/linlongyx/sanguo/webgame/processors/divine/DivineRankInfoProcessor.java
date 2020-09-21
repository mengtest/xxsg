/*    */ package com.linlongyx.sanguo.webgame.processors.divine;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.divine.DivineComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.divine.DivineEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.divine.DivineRankInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.divine.DivineRankInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DivineRankData;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DivineRankInfoProcessor
/*    */   extends ProcessorBase<DivineRankInfoRequest, DivineRankInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new DivineRankInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DivineRankInfoRequest request) {
/* 27 */     int curActId = DivineUtil.getCurDivineActId();
/* 28 */     if (curActId == -1) {
/* 29 */       return 14301;
/*    */     }
/* 31 */     DivineComponent divineComponent = (DivineComponent)playerSession.getPlayer().createIfNotExist(DivineComponent.class);
/* 32 */     DivineEntity divineEntity = divineComponent.getEntity(curActId);
/* 33 */     DivineRankData myRankdata = new DivineRankData();
/* 34 */     myRankdata.playerId = playerSession.getPlayer().getPlayerId();
/* 35 */     myRankdata.name = playerSession.getPlayer().getPlayerName();
/* 36 */     myRankdata.divineNum = (divineEntity.getMaxDivineNum() == 0) ? "" : DivineUtil.fmtDivineNum(divineEntity.getMaxDivineNum());
/*    */     
/* 38 */     List<DivineRankData> rankDataList = DivineUtil.getRankList(curActId, -1);
/* 39 */     for (int i = 0; i < rankDataList.size(); i++) {
/* 40 */       DivineRankData data = rankDataList.get(i);
/* 41 */       data.rank = i + 1;
/* 42 */       if (data.playerId == myRankdata.playerId) {
/* 43 */         myRankdata.rank = data.rank;
/*    */       }
/*    */     } 
/* 46 */     ((DivineRankInfoResponse)this.response).rankList = rankDataList;
/* 47 */     ((DivineRankInfoResponse)this.response).myRankData = myRankdata;
/* 48 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\divine\DivineRankInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */