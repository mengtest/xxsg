/*     */ package com.linlongyx.sanguo.webgame.processors.destiny;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyMyMatchInfoRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyMyMatchInfoResponse;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.MatchState;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PkDataItem;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DestinyMyMatchInfoProcessor
/*     */   extends ProcessorBase<DestinyMyMatchInfoRequest, DestinyMyMatchInfoResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  21 */     this.response = (ResponseBase)new DestinyMyMatchInfoResponse();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, DestinyMyMatchInfoRequest request) {
/*  27 */     if (DestinyUtil.curMatchState == MatchState.end) {
/*  28 */       return 17410;
/*     */     }
/*     */     
/*  31 */     MatchState curMatchState = CrossUtil.getCurrentMatchState("DestinyBetMatchInfoProcessor::handleRequest");
/*  32 */     int curState = (curMatchState == null) ? DestinyUtil.curMatchState.getState() : curMatchState.getState();
/*  33 */     long playerId = playerSession.getPlayer().getPlayerId();
/*  34 */     if (MatchState.begin.getState() < curState && curState < MatchState.stepFourPrepare.getState()) {
/*  35 */       int groupIndex = -1;
/*  36 */       for (Map.Entry<Integer, PkDataItem[]> kv : DestinyUtil.groupPkDataMap.entrySet()) {
/*  37 */         if (kv.getValue() != null) {
/*  38 */           for (PkDataItem pkDataItem : (PkDataItem[])kv.getValue()) {
/*  39 */             if (pkDataItem != null) {
/*  40 */               if (pkDataItem.getLeftPlayer() != null && pkDataItem.getLeftPlayer().getPlayerId() == playerId) {
/*  41 */                 groupIndex = ((Integer)kv.getKey()).intValue();
/*     */                 break;
/*     */               } 
/*  44 */               if (pkDataItem.getRightPlayer() != null && pkDataItem.getRightPlayer().getPlayerId() == playerId) {
/*  45 */                 groupIndex = ((Integer)kv.getKey()).intValue();
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*  50 */           if (groupIndex != -1)
/*     */             break; 
/*     */         } 
/*     */       } 
/*  54 */       if (groupIndex == -1) return 0; 
/*  55 */       if (curState == MatchState.stepOnePrepare.getState() || curState == MatchState.stepOneBalance.getState()) {
/*  56 */         PkDataItem curPkDataItem = getCurrentDatItem(playerId, DestinyUtil.groupPkDataMap.get(Integer.valueOf(groupIndex)), 0, 4);
/*  57 */         if (curPkDataItem == null) return 0;
/*     */         
/*  59 */         ((DestinyMyMatchInfoResponse)this.response).playerList = DestinyUtil.transformPkDataItem2(curPkDataItem, (request.type == 1));
/*  60 */         ((DestinyMyMatchInfoResponse)this.response).pkId = curPkDataItem.getId();
/*  61 */       } else if (curState == MatchState.stepTwoPrepare.getState() || curState == MatchState.stepTwoBalance.getState()) {
/*  62 */         PkDataItem curPkDataItem = getCurrentDatItem(playerId, DestinyUtil.groupPkDataMap.get(Integer.valueOf(groupIndex)), 4, 6);
/*  63 */         if (curPkDataItem == null) return 0;
/*     */         
/*  65 */         ((DestinyMyMatchInfoResponse)this.response).playerList = DestinyUtil.transformPkDataItem2(curPkDataItem, (request.type == 1));
/*  66 */         ((DestinyMyMatchInfoResponse)this.response).pkId = curPkDataItem.getId();
/*     */       } else {
/*  68 */         PkDataItem curPkDataItem = ((PkDataItem[])DestinyUtil.groupPkDataMap.get(Integer.valueOf(groupIndex)))[6];
/*  69 */         if (curPkDataItem == null) return 0;
/*     */         
/*  71 */         ((DestinyMyMatchInfoResponse)this.response).playerList = DestinyUtil.transformPkDataItem2(curPkDataItem, (request.type == 1));
/*  72 */         ((DestinyMyMatchInfoResponse)this.response).pkId = curPkDataItem.getId();
/*     */       } 
/*  74 */     } else if (MatchState.stepFourPrepare.getState() <= curState && curState < MatchState.end.getState()) {
/*  75 */       PkDataItem[] src = DestinyUtil.groupPkDataMap.get(Integer.valueOf(5));
/*  76 */       if (curState == MatchState.stepFourPrepare.getState() || curState == MatchState.stepFourBalance.getState()) {
/*  77 */         PkDataItem curPkDataItem = getCurrentDatItem(playerId, src, 0, 2);
/*  78 */         if (curPkDataItem == null) return 0;
/*     */         
/*  80 */         ((DestinyMyMatchInfoResponse)this.response).playerList = DestinyUtil.transformPkDataItem2(curPkDataItem, (request.type == 1));
/*  81 */         ((DestinyMyMatchInfoResponse)this.response).pkId = curPkDataItem.getId();
/*     */       } else {
/*  83 */         PkDataItem curPkDataItem = src[2];
/*  84 */         if (curPkDataItem == null) return 0;
/*     */         
/*  86 */         ((DestinyMyMatchInfoResponse)this.response).playerList = DestinyUtil.transformPkDataItem2(curPkDataItem, (request.type == 1));
/*  87 */         ((DestinyMyMatchInfoResponse)this.response).pkId = curPkDataItem.getId();
/*     */       } 
/*     */     } 
/*  90 */     return 0;
/*     */   }
/*     */   
/*     */   private PkDataItem getCurrentDatItem(long playerId, PkDataItem[] src, int start, int end) {
/*  94 */     if (src == null) return null;
/*     */     
/*  96 */     PkDataItem curPkDataItem = null;
/*  97 */     for (int i = start; i < end; i++) {
/*  98 */       if (src[i] != null && ((
/*  99 */         src[i].getLeftPlayer() != null && src[i].getLeftPlayer().getPlayerId() == playerId) || (src[i]
/* 100 */         .getRightPlayer() != null && src[i].getRightPlayer().getPlayerId() == playerId))) {
/* 101 */         curPkDataItem = src[i];
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 106 */     return curPkDataItem;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\destiny\DestinyMyMatchInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */