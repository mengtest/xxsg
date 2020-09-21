/*    */ package com.linlongyx.sanguo.webgame.processors.recruit;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.recruit.RecruitComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.RecruitParamter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.RedRecruitRefreshRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.RedRecruitRefreshResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RedRecruitRefreshProcessor
/*    */   extends ProcessorBase<RedRecruitRefreshRequest, RedRecruitRefreshResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new RedRecruitRefreshResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RedRecruitRefreshRequest request) {
/* 27 */     RecruitComponent recruitComponent = (RecruitComponent)playerSession.getPlayer().createIfNotExist(RecruitComponent.class);
/* 28 */     RecruitParamter parameter = (RecruitParamter)ParameterConstant.getParameter(15);
/* 29 */     int type = request.type;
/* 30 */     if (type == 1) {
/* 31 */       int cost = parameter.getCost(recruitComponent.getRefreshNum() + 1);
/* 32 */       if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost)) {
/* 33 */         return 10052;
/*    */       }
/* 35 */       if (RecruitUtil.getRecruitLib(parameter, true, type).size() <= 1 || RecruitUtil.getRecruitLib(parameter, false, type).size() <= 1) {
/* 36 */         return 14025;
/*    */       }
/* 38 */       FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost, ResourceEvent.RecruitRed);
/* 39 */       recruitComponent.setRefreshNum(recruitComponent.getRefreshNum() + 1);
/* 40 */       recruitComponent.refreshRedRecruit2(type);
/* 41 */       ((RedRecruitRefreshResponse)this.response).refreshNum = recruitComponent.getRefreshNum();
/* 42 */       ((RedRecruitRefreshResponse)this.response).today.addAll(recruitComponent.getToday());
/* 43 */       ((RedRecruitRefreshResponse)this.response).tommorow.addAll(recruitComponent.getTommorow());
/* 44 */     } else if (type == 2) {
/* 45 */       int cost = parameter.getCost(recruitComponent.getGoldRefreshNum() + 1);
/* 46 */       if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost)) {
/* 47 */         return 10052;
/*    */       }
/* 49 */       if (RecruitUtil.getRecruitLib(parameter, true, type).size() <= 1 || RecruitUtil.getRecruitLib(parameter, false, type).size() <= 1) {
/* 50 */         return 14025;
/*    */       }
/* 52 */       FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost, ResourceEvent.RecruitGoldRefresh);
/* 53 */       recruitComponent.setGoldRefreshNum(recruitComponent.getGoldRefreshNum() + 1);
/* 54 */       recruitComponent.refreshRedRecruit2(type);
/* 55 */       ((RedRecruitRefreshResponse)this.response).refreshNum = recruitComponent.getGoldRefreshNum();
/* 56 */       ((RedRecruitRefreshResponse)this.response).today.addAll(recruitComponent.getGoldToday());
/* 57 */       ((RedRecruitRefreshResponse)this.response).tommorow.addAll(recruitComponent.getGoldTommorow());
/*    */     } 
/* 59 */     request.type = type;
/* 60 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\recruit\RedRecruitRefreshProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */