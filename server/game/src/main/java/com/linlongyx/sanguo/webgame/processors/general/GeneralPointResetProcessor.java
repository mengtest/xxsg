/*    */ package com.linlongyx.sanguo.webgame.processors.general;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.GeneralInsBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GeneralParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralPointResetRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralPointResetResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GeneralPointResetProcessor
/*    */   extends ProcessorBase<GeneralPointResetRequest, GeneralPointResetResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new GeneralPointResetResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GeneralPointResetRequest request) {
/* 32 */     int chapter = request.chapter;
/* 33 */     GeneralParameter generalParameter = (GeneralParameter)ParameterConstant.getParameter(12);
/* 34 */     Map<Integer, Integer> pointIdMap = generalParameter.getPointIdMap(chapter);
/* 35 */     if (null == pointIdMap) {
/* 36 */       return 11203;
/*    */     }
/* 38 */     int point = request.point;
/* 39 */     int id = ((Integer)pointIdMap.getOrDefault(Integer.valueOf(point), Integer.valueOf(0))).intValue();
/* 40 */     GeneralInsBean generalInsBean = (GeneralInsBean)JsonTableService.getJsonData(id, GeneralInsBean.class);
/* 41 */     if (null == generalInsBean) {
/* 42 */       return 11203;
/*    */     }
/*    */     
/* 45 */     GeneralComponent generalComponent = (GeneralComponent)playerSession.getPlayer().createIfNotExist(GeneralComponent.class);
/* 46 */     Map<Integer, Integer> resetTimes = generalComponent.getResetTimes();
/* 47 */     Map<Integer, Integer> challenges = generalComponent.getChallenges();
/* 48 */     int challenge = ((Integer)challenges.getOrDefault(Integer.valueOf(id), Integer.valueOf(0))).intValue();
/* 49 */     int resetTime = ((Integer)resetTimes.getOrDefault(Integer.valueOf(id), Integer.valueOf(0))).intValue();
/* 50 */     int leftNum = generalInsBean.getDailyTimes() + resetTime * generalInsBean.getDailyTimes() - challenge;
/* 51 */     if (leftNum > 0) {
/* 52 */       return 11208;
/*    */     }
/* 54 */     int ccy = generalParameter.getResetCost(resetTime + 1);
/* 55 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, ccy)) {
/* 56 */       return 10052;
/*    */     }
/* 58 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, ccy, ResourceEvent.GeneralPointReset, true);
/* 59 */     resetTime++;
/* 60 */     resetTimes.put(Integer.valueOf(id), Integer.valueOf(resetTime));
/* 61 */     generalComponent.setResetTimes(resetTimes);
/*    */     
/* 63 */     ((GeneralPointResetResponse)this.response).data = GeneralUtil.getGeneralPointData(generalInsBean, generalComponent);
/* 64 */     ((GeneralPointResetResponse)this.response).chapter = chapter;
/* 65 */     LogUtils.errorLog(new Object[] { "GeneralPointReset", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(chapter), ((GeneralPointResetResponse)this.response).toString() });
/* 66 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\general\GeneralPointResetProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */