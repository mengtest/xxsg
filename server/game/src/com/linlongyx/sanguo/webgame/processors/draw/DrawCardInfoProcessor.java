/*    */ package com.linlongyx.sanguo.webgame.processors.draw;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.draw.DrawCardComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.draw.DrawCardEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.DrawBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.DrawRaffleBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.DrawCardParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.draw.DrawCardInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.draw.DrawCardInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DrawCardData;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DrawCardInfoProcessor
/*    */   extends ProcessorBase<DrawCardInfoRequest, DrawCardInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 31 */     this.response = (ResponseBase)new DrawCardInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DrawCardInfoRequest request) {
/* 36 */     int drawId = request.drawId;
/* 37 */     DrawCardParameter drawCardParameter = (DrawCardParameter)ParameterConstant.getParameter(48);
/* 38 */     if (!drawCardParameter.isActOpen(drawId)) {
/* 39 */       return 11801;
/*    */     }
/* 41 */     FestivalTime festivalTime = drawCardParameter.getActTime(drawId);
/* 42 */     if (null == festivalTime) {
/* 43 */       return 11802;
/*    */     }
/* 45 */     DrawBean drawBean = (DrawBean)JsonTableService.getJsonData(drawId, DrawBean.class);
/* 46 */     if (null == drawBean) {
/* 47 */       return 11802;
/*    */     }
/* 49 */     DrawRaffleBean drawRaffleBean = (DrawRaffleBean)JsonTableService.getJsonData(drawBean.getRaffle(), DrawRaffleBean.class);
/* 50 */     if (null == drawRaffleBean) {
/* 51 */       return 11802;
/*    */     }
/*    */     
/* 54 */     DrawCardComponent drawCardComponent = (DrawCardComponent)playerSession.getPlayer().createIfNotExist(DrawCardComponent.class);
/* 55 */     DrawCardEntity drawCardEntity = drawCardComponent.getEntity(drawId);
/* 56 */     if (drawCardEntity.getAwards().size() == 0) {
/* 57 */       DrawCardUtil.drawCard(playerSession, drawId);
/*    */     }
/*    */     
/* 60 */     int round = drawCardEntity.getRound();
/* 61 */     Map<Integer, DrawRaffleBean.RoundBean> rounds = drawRaffleBean.getRound();
/* 62 */     DrawRaffleBean.RoundBean roundBean = rounds.get(Integer.valueOf(round));
/* 63 */     if (null == roundBean) {
/* 64 */       if (round > 0 && round >= rounds.size()) {
/* 65 */         roundBean = rounds.get(Integer.valueOf(rounds.size() - 1));
/*    */       } else {
/* 67 */         return 11802;
/*    */       } 
/*    */     }
/*    */     
/* 71 */     ((DrawCardInfoResponse)this.response).drawId = drawId;
/* 72 */     ((DrawCardInfoResponse)this.response).playTimes = round;
/* 73 */     ((DrawCardInfoResponse)this.response).freeTime = (drawCardEntity.getFreeTime() == 0) ? TimeUtil.currentTime() : drawCardEntity.getFreeTime();
/* 74 */     ((DrawCardInfoResponse)this.response).endTime = drawCardParameter.getActEndTime(drawId);
/* 75 */     Map<Integer, Integer> openCards = drawCardEntity.getOpenCards();
/*    */     
/* 77 */     for (Map.Entry<Integer, Integer> entry : openCards.entrySet()) {
/* 78 */       DrawCardData drawCardData = new DrawCardData();
/* 79 */       drawCardData.pos = ((Integer)entry.getKey()).intValue();
/* 80 */       drawCardData.warehouseId = drawCardParameter.getWarehouseId(((Integer)entry.getValue()).intValue());
/* 81 */       drawCardData.id = ((Integer)entry.getValue()).intValue();
/* 82 */       ((DrawCardInfoResponse)this.response).openList.add(drawCardData);
/*    */     } 
/* 84 */     Map<Integer, KeyValue> awards = drawCardEntity.getAwards();
/* 85 */     for (Map.Entry<Integer, KeyValue> entry : awards.entrySet()) {
/* 86 */       KeyValue keyValue = entry.getValue();
/* 87 */       if (!DrawCardUtil.isNormalCard(keyValue)) {
/* 88 */         ((DrawCardInfoResponse)this.response).rares.add(entry.getKey());
/*    */       }
/*    */     } 
/* 91 */     Set<Integer> counts = drawCardEntity.getCounts();
/* 92 */     ((DrawCardInfoResponse)this.response).counts = new ArrayList<>(counts);
/* 93 */     ((DrawCardInfoResponse)this.response).records.addAll(DrawCardUtil.getRecords());
/* 94 */     int refreshCount = drawCardEntity.getRefreshCount();
/* 95 */     ((DrawCardInfoResponse)this.response).cleanCCY = DrawCardUtil.getRefreshCost(refreshCount + 1).getDrawGlod();
/*    */     
/* 97 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\draw\DrawCardInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */