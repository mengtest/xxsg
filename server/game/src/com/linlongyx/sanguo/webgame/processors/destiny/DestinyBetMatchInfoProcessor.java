/*     */ package com.linlongyx.sanguo.webgame.processors.destiny;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBetMatchInfoRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBetMatchInfoResponse;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.MatchState;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PkDataItem;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PkPlayerData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class DestinyBetMatchInfoProcessor
/*     */   extends ProcessorBase<DestinyBetMatchInfoRequest, DestinyBetMatchInfoResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  23 */     this.response = (ResponseBase)new DestinyBetMatchInfoResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, DestinyBetMatchInfoRequest request) {
/*  28 */     byte type = request.type;
/*  29 */     if (type < 0 || type > 1) {
/*  30 */       return 17411;
/*     */     }
/*  32 */     MatchState curMatchState = CrossUtil.getCurrentMatchState("DestinyBetMatchInfoProcessor::handleRequest");
/*  33 */     if (curMatchState == null) {
/*  34 */       return 17411;
/*     */     }
/*  36 */     if (curMatchState != DestinyUtil.curMatchState) {
/*  37 */       DestinyUtil.curMatchState = curMatchState;
/*  38 */       CrossUtil.getPkDataItems("DestinyBetMatchInfoProcessor::handleRequest", DestinyUtil.isGroupMatch(curMatchState) ? 0 : 1);
/*     */     } 
/*     */     
/*  41 */     PkDataItem targetPkData = CrossUtil.getCurPkDataItem("DestinyBetMatchInfoProcessor::getCurPkDatItem");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  55 */     if (targetPkData == null) {
/*  56 */       return 17412;
/*     */     }
/*     */     
/*  59 */     ((DestinyBetMatchInfoResponse)this.response).playerList = DestinyUtil.transformPkDataItem2(targetPkData, (type == 1));
/*  60 */     ((DestinyBetMatchInfoResponse)this.response).state = (byte)(DestinyUtil.canBet(curMatchState) ? 1 : 0);
/*  61 */     ((DestinyBetMatchInfoResponse)this.response).winner = targetPkData.getWinner();
/*  62 */     ((DestinyBetMatchInfoResponse)this.response).pkId = targetPkData.getId();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     HashMap<Long, Integer> betNumMap = CrossUtil.getBetNum("DestinyBetMatchInfoProcessor::getBetNum", targetPkData.getId());
/*  74 */     if (betNumMap != null) {
/*  75 */       if (targetPkData.getLeftPlayer() != null && betNumMap.containsKey(Long.valueOf(targetPkData.getLeftPlayer().getPlayerId()))) {
/*  76 */         ((DestinyBetMatchInfoResponse)this.response).betNumList.add(betNumMap.get(Long.valueOf(targetPkData.getLeftPlayer().getPlayerId())));
/*     */       }
/*  78 */       if (targetPkData.getRightPlayer() != null && betNumMap.containsKey(Long.valueOf(targetPkData.getRightPlayer().getPlayerId()))) {
/*  79 */         ((DestinyBetMatchInfoResponse)this.response).betNumList.add(betNumMap.get(Long.valueOf(targetPkData.getRightPlayer().getPlayerId())));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  84 */     return 0;
/*     */   }
/*     */   
/*     */   private PkDataItem chooseTargetPk(int start, int end, boolean isGroup) {
/*  88 */     PkDataItem targetPkData = null;
/*  89 */     long balance = Long.MAX_VALUE;
/*  90 */     List<PkDataItem> fullPkDataItemList = new ArrayList<>();
/*  91 */     List<PkDataItem> halfPkDataItemList = new ArrayList<>();
/*  92 */     if (isGroup) {
/*  93 */       for (Map.Entry<Integer, PkDataItem[]> kv : DestinyUtil.groupPkDataMap.entrySet()) {
/*  94 */         if (((Integer)kv.getKey()).intValue() != 5) {
/*  95 */           PkDataItem[] arr = kv.getValue();
/*  96 */           if (arr != null) {
/*  97 */             for (int i = start; i < end; i++) {
/*  98 */               if (arr[i] != null) {
/*  99 */                 if (arr[i].getRightPlayer() != null && arr[i].getLeftPlayer() != null) {
/* 100 */                   fullPkDataItemList.add(arr[i]);
/*     */                 } else {
/* 102 */                   halfPkDataItemList.add(arr[i]);
/*     */                 } 
/*     */               }
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 111 */       PkDataItem[] arr = DestinyUtil.groupPkDataMap.get(Integer.valueOf(5));
/* 112 */       if (arr != null) {
/* 113 */         for (int i = start; i < end; i++) {
/* 114 */           if (arr[i] != null) {
/* 115 */             if (arr[i].getRightPlayer() != null && arr[i].getLeftPlayer() != null) {
/* 116 */               fullPkDataItemList.add(arr[i]);
/*     */             } else {
/* 118 */               halfPkDataItemList.add(arr[i]);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 125 */     if (fullPkDataItemList.isEmpty() && halfPkDataItemList.isEmpty()) return null;
/*     */     
/* 127 */     for (PkDataItem pkDataItem : fullPkDataItemList) {
/* 128 */       long tmp = Math.abs(pkDataItem.getLeftPlayer().getFightValue() - pkDataItem.getRightPlayer().getFightValue());
/* 129 */       if (tmp < balance) {
/* 130 */         balance = tmp;
/* 131 */         targetPkData = pkDataItem;
/*     */       } 
/*     */     } 
/*     */     
/* 135 */     if (targetPkData == null && !halfPkDataItemList.isEmpty()) {
/* 136 */       balance = 0L;
/* 137 */       for (PkDataItem pkDataItem : halfPkDataItemList) {
/* 138 */         PkPlayerData pkPlayerData = null;
/* 139 */         if (pkDataItem.getLeftPlayer() != null) {
/* 140 */           pkPlayerData = pkDataItem.getLeftPlayer();
/*     */         }
/* 142 */         if (pkDataItem.getRightPlayer() != null) {
/* 143 */           pkPlayerData = pkDataItem.getRightPlayer();
/*     */         }
/* 145 */         if (pkPlayerData != null) {
/* 146 */           long tmp = pkPlayerData.getFightValue();
/* 147 */           if (balance < tmp) {
/* 148 */             balance = tmp;
/* 149 */             targetPkData = pkDataItem;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 154 */     return targetPkData;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\destiny\DestinyBetMatchInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */