/*    */ package com.linlongyx.sanguo.webgame.processors.draw;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.draw.DrawCardComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.draw.DrawCardEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.DrawBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.DrawRaffleBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.DrawCardParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.draw.DrawCardCleanRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.draw.DrawCardCleanResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DrawCardCleanProcessor
/*    */   extends ProcessorBase<DrawCardCleanRequest, DrawCardCleanResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 30 */     this.response = (ResponseBase)new DrawCardCleanResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DrawCardCleanRequest request) {
/* 35 */     int drawId = request.drawId;
/* 36 */     DrawCardParameter drawCardParameter = (DrawCardParameter)ParameterConstant.getParameter(48);
/* 37 */     if (!drawCardParameter.isActOpen(drawId)) {
/* 38 */       return 11801;
/*    */     }
/* 40 */     DrawBean drawBean = (DrawBean)JsonTableService.getJsonData(drawId, DrawBean.class);
/* 41 */     if (null == drawBean) {
/* 42 */       return 11802;
/*    */     }
/* 44 */     DrawRaffleBean drawRaffleBean = (DrawRaffleBean)JsonTableService.getJsonData(drawBean.getRaffle(), DrawRaffleBean.class);
/* 45 */     if (null == drawRaffleBean) {
/* 46 */       return 11802;
/*    */     }
/* 48 */     DrawCardComponent drawCardComponent = (DrawCardComponent)playerSession.getPlayer().createIfNotExist(DrawCardComponent.class);
/* 49 */     DrawCardEntity drawCardEntity = drawCardComponent.getEntity(drawId);
/*    */     
/* 51 */     boolean isFree = (drawCardEntity.getOpenCards().size() == 6);
/* 52 */     int refreshCount = drawCardEntity.getRefreshCount();
/*    */     
/* 54 */     int cost = DrawCardUtil.getRefreshCost(refreshCount + 1).getDrawGlod();
/* 55 */     if (!isFree && cost > 0) {
/* 56 */       if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost)) {
/* 57 */         return 10052;
/*    */       }
/* 59 */       FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost, ResourceEvent.DrawCardCost, true);
/*    */     } 
/*    */     
/* 62 */     if (!DrawCardUtil.drawCard(playerSession, drawId)) {
/* 63 */       return 11807;
/*    */     }
/*    */ 
/*    */     
/* 67 */     if (!isFree) {
/* 68 */       refreshCount++;
/* 69 */       drawCardEntity.setRefreshCount(refreshCount);
/* 70 */       drawCardComponent.updateRefreshCountDB(drawId);
/*    */     } 
/*    */     
/* 73 */     ((DrawCardCleanResponse)this.response).cleanCCY = DrawCardUtil.getRefreshCost(refreshCount + 1).getDrawGlod();
/*    */     
/* 75 */     drawCardEntity.setOpenCards(new HashMap<>());
/* 76 */     drawCardComponent.updateOpenCardsDB(drawId);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 83 */     Map<Integer, KeyValue> awards = drawCardEntity.getAwards();
/* 84 */     for (Map.Entry<Integer, KeyValue> entry : awards.entrySet()) {
/* 85 */       KeyValue keyValue = entry.getValue();
/* 86 */       if (!DrawCardUtil.isNormalCard(keyValue)) {
/* 87 */         ((DrawCardCleanResponse)this.response).rares.add(entry.getKey());
/*    */       }
/*    */     } 
/*    */     
/* 91 */     ((DrawCardCleanResponse)this.response).drawId = drawId;
/* 92 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\draw\DrawCardCleanProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */