/*    */ package com.linlongyx.sanguo.webgame.processors.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBetRecordInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBetRecordInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyBetRecordInfoProcessor
/*    */   extends ProcessorBase<DestinyBetRecordInfoRequest, DestinyBetRecordInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 17 */     this.response = (ResponseBase)new DestinyBetRecordInfoResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DestinyBetRecordInfoRequest request) {
/* 23 */     ((DestinyBetRecordInfoResponse)this.response).recordList = CrossUtil.getPlayerBetRecord("DestinyBetRecordInfoProcessor::getPlayerBetRecord", playerSession.getPlayer().getPlayerId());
/* 24 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\destiny\DestinyBetRecordInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */