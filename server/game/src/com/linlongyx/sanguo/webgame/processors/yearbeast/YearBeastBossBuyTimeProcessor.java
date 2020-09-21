/*    */ package com.linlongyx.sanguo.webgame.processors.yearbeast;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.yearbeast.YearBeastComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.yearbeast.YearBeastEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TimesGoldBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.YearBeastBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.draw.DrawCardUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.yearbeast.YearBeastBossBuyTimeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.yearbeast.YearBeastBossBuyTimeResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ public class YearBeastBossBuyTimeProcessor extends ProcessorBase<YearBeastBossBuyTimeRequest, YearBeastBossBuyTimeResponse> {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new YearBeastBossBuyTimeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, YearBeastBossBuyTimeRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 62))
/* 31 */       return 10061; 
/* 32 */     YearBeastComponent yearBeastComponent = (YearBeastComponent)playerSession.getPlayer().createIfNotExist(YearBeastComponent.class);
/* 33 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 34 */     if (!bossHomeParameter.isActOpen(request.actId)) {
/* 35 */       return 12702;
/*    */     }
/* 37 */     YearBeastBean yearBeastBean = (YearBeastBean)JsonTableService.getJsonData(request.actId, YearBeastBean.class);
/* 38 */     if (null == yearBeastBean) {
/* 39 */       return 11808;
/*    */     }
/* 41 */     if (!PlayerUtil.platformActIsOpen(yearBeastBean.getChannelType())) {
/* 42 */       return 12702;
/*    */     }
/* 44 */     YearBeastEntity yearBeastEntity = yearBeastComponent.getEntity(request.actId);
/* 45 */     if (yearBeastEntity.getBuyTimes() >= yearBeastBean.getPurchaseLimit()) {
/* 46 */       return 10306;
/*    */     }
/* 48 */     if (yearBeastBean.getChallLimit() + yearBeastEntity.getBuyTimes() - yearBeastEntity.getYearBeastTimes() >= yearBeastBean.getChallLimit()) {
/* 49 */       return 11208;
/*    */     }
/* 51 */     TimesGoldBean timesGoldBean = DrawCardUtil.getRefreshCost(yearBeastEntity.getBuyTimes() + 1);
/* 52 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, timesGoldBean.getYearBoss())) {
/* 53 */       return 10052;
/*    */     }
/* 55 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, timesGoldBean.getYearBoss(), ResourceEvent.YearBeastBuyTime, true);
/* 56 */     yearBeastEntity.setBuyTimes(yearBeastEntity.getBuyTimes() + 1);
/* 57 */     yearBeastComponent.updateBuyTimesDB(request.actId);
/* 58 */     ((YearBeastBossBuyTimeResponse)this.response).buyTimes = yearBeastEntity.getBuyTimes();
/* 59 */     ((YearBeastBossBuyTimeResponse)this.response).currTimes = yearBeastBean.getChallLimit() + yearBeastEntity.getBuyTimes() - yearBeastEntity.getYearBeastTimes();
/* 60 */     ((YearBeastBossBuyTimeResponse)this.response).actId = request.actId;
/* 61 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\yearbeast\YearBeastBossBuyTimeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */