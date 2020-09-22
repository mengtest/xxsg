/*    */ package com.linlongyx.sanguo.webgame.processors.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyMatchGroupInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyMatchGroupInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyGroupPlayerData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.PkDataItem;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyMatchGroupInfoProcessor
/*    */   extends ProcessorBase<DestinyMatchGroupInfoRequest, DestinyMatchGroupInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new DestinyMatchGroupInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DestinyMatchGroupInfoRequest request) {
/* 28 */     int weekDay = TimeUtil.getWeek();
/*    */     
/* 30 */     int groupIndex = request.groupIndex;
/* 31 */     if (weekDay > 0 && weekDay < 6 && groupIndex == 5) {
/* 32 */       return 17409;
/*    */     }
/* 34 */     if ((weekDay == 6 || weekDay == 7) && (groupIndex < 1 || groupIndex > 5)) {
/* 35 */       return 11808;
/*    */     }
/* 37 */     PkDataItem[] pkDataItems = DestinyUtil.groupPkDataMap.get(Integer.valueOf(groupIndex));
/* 38 */     if (pkDataItems == null) {
/* 39 */       if (groupIndex > 0 && groupIndex < 5) {
/* 40 */         CrossUtil.getPkDataItems("DestinyMatchGroupInfoProcessor::getPkDataItems", 0);
/*    */       } else {
/* 42 */         CrossUtil.getPkDataItems("DestinyMatchGroupInfoProcessor::getPkDataItems", 1);
/*    */       } 
/*    */     }
/* 45 */     pkDataItems = DestinyUtil.groupPkDataMap.get(Integer.valueOf(groupIndex));
/* 46 */     if (pkDataItems == null) {
/* 47 */       return 0;
/*    */     }
/* 49 */     Map<Long, DestinyGroupPlayerData> playerMap = new HashMap<>();
/* 50 */     for (PkDataItem pkDataItem : pkDataItems) {
/* 51 */       ((DestinyMatchGroupInfoResponse)this.response).fightRecordList.add(DestinyUtil.transformPkDataItem(pkDataItem));
/* 52 */       if (pkDataItem != null) {
/* 53 */         if (pkDataItem.getLeftPlayer() != null && 
/* 54 */           !playerMap.containsKey(Long.valueOf(pkDataItem.getLeftPlayer().getPlayerId()))) {
/* 55 */           DestinyGroupPlayerData data = new DestinyGroupPlayerData();
/* 56 */           data.playerId = pkDataItem.getLeftPlayer().getPlayerId();
/* 57 */           data.playerName = pkDataItem.getLeftPlayer().getPlayerName();
/* 58 */           playerMap.putIfAbsent(Long.valueOf(pkDataItem.getLeftPlayer().getPlayerId()), data);
/*    */         } 
/*    */         
/* 61 */         if (pkDataItem.getRightPlayer() != null && 
/* 62 */           !playerMap.containsKey(Long.valueOf(pkDataItem.getRightPlayer().getPlayerId()))) {
/* 63 */           DestinyGroupPlayerData data = new DestinyGroupPlayerData();
/* 64 */           data.playerId = pkDataItem.getRightPlayer().getPlayerId();
/* 65 */           data.playerName = pkDataItem.getRightPlayer().getPlayerName();
/* 66 */           playerMap.putIfAbsent(Long.valueOf(pkDataItem.getRightPlayer().getPlayerId()), data);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 71 */     ((DestinyMatchGroupInfoResponse)this.response).playerList.addAll(playerMap.values());
/* 72 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\destiny\DestinyMatchGroupInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */