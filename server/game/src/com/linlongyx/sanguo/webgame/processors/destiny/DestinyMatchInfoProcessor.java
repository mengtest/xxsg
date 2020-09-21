/*    */ package com.linlongyx.sanguo.webgame.processors.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyMatchInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyMatchInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyRankData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyMatchInfoProcessor
/*    */   extends ProcessorBase<DestinyMatchInfoRequest, DestinyMatchInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new DestinyMatchInfoResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DestinyMatchInfoRequest request) {
/* 26 */     DestinyRankData myRankData = DestinyUtil.getTargetPlayerRankData(playerSession.getPlayer().getPlayerId());
/* 27 */     int weekDay = TimeUtil.getWeek();
/* 28 */     byte type = (byte)((weekDay > 0 && weekDay < 6) ? 0 : 1);
/* 29 */     int curtime = TimeUtil.currentTime();
/* 30 */     int leftTime = TimeUtil.getTimeDiffToNow(8 - weekDay) - curtime;
/* 31 */     ((DestinyMatchInfoResponse)this.response).type = type;
/* 32 */     ((DestinyMatchInfoResponse)this.response).destinyPoint = myRankData.destinyPoint;
/* 33 */     ((DestinyMatchInfoResponse)this.response).rank = myRankData.rank;
/* 34 */     ((DestinyMatchInfoResponse)this.response).time = leftTime;
/* 35 */     if (DestinyUtil.zoneId == 0) {
/* 36 */       DestinyUtil.zoneId = CrossUtil.getZoneId("DestinyMatchInfoProcessor::handleRequest");
/*    */     }
/* 38 */     ((DestinyMatchInfoResponse)this.response).zoneId = DestinyUtil.zoneId;
/* 39 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\destiny\DestinyMatchInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */