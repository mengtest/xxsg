/*     */ package com.linlongyx.sanguo.webgame.processors.singleIns;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.PersonalInsBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.login.VipUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.singleIns.NormalInsBuySweepRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.singleIns.NormalInsBuySweepResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class NormalInsBuySweepProcessor
/*     */   extends ProcessorBase<NormalInsBuySweepRequest, NormalInsBuySweepResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  26 */     this.response = (ResponseBase)new NormalInsBuySweepResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, NormalInsBuySweepRequest request) {
/*  31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 13)) {
/*  32 */       return 10061;
/*     */     }
/*  34 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*     */ 
/*     */ 
/*     */     
/*  38 */     int insId = request.insId;
/*  39 */     PersonalInsBean personalInsBean = (PersonalInsBean)JsonTableService.getJsonData(insId, PersonalInsBean.class);
/*  40 */     if (null == personalInsBean) {
/*  41 */       return 13002;
/*     */     }
/*  43 */     int type = personalInsBean.getIntType();
/*  44 */     if (!SingleInsUtil.isDayValue(personalInsBean.getDay())) {
/*  45 */       return 13005;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  51 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/*  52 */     Map<Integer, Integer> times = singleInsComponent.getTimes();
/*  53 */     Map<Integer, Integer> maxPoints = singleInsComponent.getMaxPoints();
/*  54 */     int point = ((Integer)maxPoints.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/*  55 */     Set<Integer> clears = singleInsComponent.getClears();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     int dailyTime = VipUtil.getNum(playerComponent.getVip(), 8);
/*  61 */     int sweepTime = VipUtil.getNum(playerComponent.getVip(), 17);
/*  62 */     int sweep = ((Integer)singleInsComponent.getVipSweepTimes().getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/*  63 */     int time = ((Integer)times.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/*  64 */     if (time < dailyTime) {
/*  65 */       return 13009;
/*     */     }
/*  67 */     if (sweepTime - sweep <= 0) {
/*  68 */       return 13004;
/*     */     }
/*     */     
/*  71 */     int cost = 0;
/*  72 */     if (((Integer)personalInsBean.getPrice().get(sweep)).intValue() < 0) {
/*  73 */       cost = ((Integer)personalInsBean.getPrice().get(personalInsBean.getPrice().size() - 1)).intValue();
/*     */     } else {
/*  75 */       cost = ((Integer)personalInsBean.getPrice().get(sweep)).intValue();
/*     */     } 
/*  77 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost)) {
/*  78 */       return 10052;
/*     */     }
/*  80 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost, ResourceEvent.NormalSweepBuy, true);
/*  81 */     sweep++;
/*  82 */     singleInsComponent.getVipSweepTimes().put(Integer.valueOf(type), Integer.valueOf(sweep));
/*  83 */     singleInsComponent.setVipSweepTimes(singleInsComponent.getVipSweepTimes());
/*     */     
/*  85 */     time--;
/*  86 */     times.put(Integer.valueOf(type), Integer.valueOf(time));
/*  87 */     singleInsComponent.setTimes(times);
/*     */ 
/*     */     
/*  90 */     ((NormalInsBuySweepResponse)this.response).insId = insId;
/*  91 */     ((NormalInsBuySweepResponse)this.response).leftCount = dailyTime - time;
/*  92 */     ((NormalInsBuySweepResponse)this.response).leftSweep = sweepTime - sweep;
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
/* 103 */     LogUtils.errorLog(new Object[] { "NormalInsVipSweep", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(insId), Integer.valueOf(time), Integer.valueOf(((NormalInsBuySweepResponse)this.response).leftCount) });
/* 104 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\singleIns\NormalInsBuySweepProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */