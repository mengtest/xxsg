/*    */ package com.linlongyx.sanguo.webgame.processors.luckymoney;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.luckymoney.LuckyMoneyComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.luckymoney.LuckyMoneyEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.LuckyMoneyActParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckymoney.LuckyMoneyOpenInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckymoney.LuckyMoneyOpenInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LuckyMoneyOpenInfoProcessor
/*    */   extends ProcessorBase<LuckyMoneyOpenInfoRequest, LuckyMoneyOpenInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new LuckyMoneyOpenInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, LuckyMoneyOpenInfoRequest request) {
/* 27 */     LuckyMoneyActParameter luckyMoneyActParameter = (LuckyMoneyActParameter)ParameterConstant.getParameter(61);
/* 28 */     LuckyMoneyComponent luckyMoneyComponent = (LuckyMoneyComponent)playerSession.getPlayer().createIfNotExist(LuckyMoneyComponent.class);
/* 29 */     if (!luckyMoneyActParameter.isLuckySaveAct(request.id)) {
/* 30 */       return 12402;
/*    */     }
/* 32 */     LuckyMoneyEntity luckyMoneyEntity = luckyMoneyComponent.getEntity(request.id);
/* 33 */     ((LuckyMoneyOpenInfoResponse)this.response).goldMoneypot = luckyMoneyEntity.getGoldMoney();
/* 34 */     ((LuckyMoneyOpenInfoResponse)this.response).silverMoneypot = luckyMoneyEntity.getSilverMoney();
/* 35 */     ((LuckyMoneyOpenInfoResponse)this.response).goldMoneypotSum = luckyMoneyEntity.getGoldMoneySum();
/* 36 */     ((LuckyMoneyOpenInfoResponse)this.response).silverMoneypotSum = luckyMoneyEntity.getSilverMoneySum();
/* 37 */     ((LuckyMoneyOpenInfoResponse)this.response).battle = luckyMoneyEntity.getBattle();
/* 38 */     ((LuckyMoneyOpenInfoResponse)this.response).point = luckyMoneyEntity.getTaskPoint();
/* 39 */     if (luckyMoneyActParameter.isActSaveLuckyOpen(request.id)) {
/* 40 */       FestivalTime festivalTime = luckyMoneyActParameter.getActTime(request.id);
/* 41 */       ((LuckyMoneyOpenInfoResponse)this.response).day = TimeUtil.getDayDiff(festivalTime.endTime) + 1;
/*    */     } 
/* 43 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\luckymoney\LuckyMoneyOpenInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */