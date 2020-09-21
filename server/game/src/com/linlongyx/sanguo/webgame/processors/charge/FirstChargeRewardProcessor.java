/*    */ package com.linlongyx.sanguo.webgame.processors.charge;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FirstRechargeBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GoldBuyParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.KeyValueConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.charge.FirstChargeRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.charge.FirstChargeRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class FirstChargeRewardProcessor extends ProcessorBase<FirstChargeRewardRequest, FirstChargeRewardResponse> {
/*    */   protected void initResponse() {
/* 28 */     this.response = (ResponseBase)new FirstChargeRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FirstChargeRewardRequest request) {
/* 33 */     int id = request.id;
/*    */     
/* 35 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 36 */     int type = PlayerUtil.getPlatformType();
/* 37 */     Set<Integer> firstReward = extendComponent.getNewFirstReward();
/* 38 */     if (extendComponent.getNewFirstCharge().isEmpty()) {
/* 39 */       return 12302;
/*    */     }
/* 41 */     FirstRechargeBean firstRechargeBean = null;
/* 42 */     Map<Integer, Object> map = JsonTableService.getJsonTable(FirstRechargeBean.class);
/* 43 */     int money = 0;
/* 44 */     for (Iterator<Integer> iterator = extendComponent.getNewFirstCharge().iterator(); iterator.hasNext(); ) { int mo = ((Integer)iterator.next()).intValue();
/* 45 */       money = mo; }
/*    */     
/* 47 */     int hefu = (MContext.isHeFu() == true) ? 1 : 0;
/* 48 */     for (Object object : map.values()) {
/* 49 */       FirstRechargeBean firstRechargeBean1 = (FirstRechargeBean)object;
/* 50 */       if (firstRechargeBean1.getConnectortype() == type && firstRechargeBean1.getMoney() == money && hefu == firstRechargeBean1.getHefu()) {
/* 51 */         firstRechargeBean = firstRechargeBean1;
/*    */       }
/*    */     } 
/* 54 */     if (null == firstRechargeBean) {
/* 55 */       return 11601;
/*    */     }
/* 57 */     if (!extendComponent.getNewFirstCharge().contains(Integer.valueOf(firstRechargeBean.getMoney()))) {
/* 58 */       return 12302;
/*    */     }
/* 60 */     if (!firstReward.isEmpty() || firstReward.contains(Integer.valueOf(firstRechargeBean.getMoney()))) {
/* 61 */       return 10091;
/*    */     }
/* 63 */     firstReward.add(Integer.valueOf(firstRechargeBean.getMoney()));
/* 64 */     extendComponent.setNewFirstReward(firstReward);
/*    */     
/* 66 */     FinanceUtil.reward(FinanceUtil.transform(firstRechargeBean.getRechargeReward()), playerSession.getPlayer(), ResourceEvent.FirstChargeReward, true);
/* 67 */     ((FirstChargeRewardResponse)this.response).id = id;
/*    */     
/* 69 */     if (!MContext.isHeFu()) {
/* 70 */       GoldBuyParameter goldBuyParameter = (GoldBuyParameter)ParameterConstant.getParameter(70);
/* 71 */       RuneComponent runeComponent = (RuneComponent)playerSession.getPlayer().createIfNotExist(RuneComponent.class);
/* 72 */       runeComponent.setGoldEndTime(TimeUtil.currentTime() + goldBuyParameter.getLimitTime());
/* 73 */       PlayerUtil.updateKeyValueInfo(playerSession, KeyValueConstant.GOLDENDTIME.getKey(), runeComponent.getGoldEndTime());
/*    */     } 
/*    */     
/* 76 */     LogUtils.errorLog(new Object[] { "newFirstChargeReward", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(id), MContext.getPlatform() });
/* 77 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\charge\FirstChargeRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */