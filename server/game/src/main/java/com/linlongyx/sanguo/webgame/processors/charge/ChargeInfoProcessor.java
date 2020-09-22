/*    */ package com.linlongyx.sanguo.webgame.processors.charge;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.FundParameter;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GoldBuyParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.KeyValueConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.welfare.WelfareUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.charge.ChargeInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.charge.ChargeInfoResponse;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChargeInfoProcessor
/*    */   extends ProcessorBase<ChargeInfoRequest, ChargeInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 29 */     this.response = (ResponseBase)new ChargeInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ChargeInfoRequest request) {
/* 34 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 35 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 36 */     ((ChargeInfoResponse)this.response).firstList = new ArrayList(extendComponent.getFirstReChargeSet());
/* 37 */     ((ChargeInfoResponse)this.response).firstReward = new ArrayList(extendComponent.getNewFirstReward());
/* 38 */     ((ChargeInfoResponse)this.response).firstCharge = new ArrayList(extendComponent.getNewFirstCharge());
/* 39 */     ((ChargeInfoResponse)this.response).firstChargeCount = WelfareUtil.groupCharegeNum.get();
/* 40 */     FundParameter fundParameter = (FundParameter)ParameterConstant.getParameter(25);
/* 41 */     ((ChargeInfoResponse)this.response).chargeEndTime = WelfareUtil.getGiftTime(fundParameter.getFirstChargeDays());
/* 42 */     ((ChargeInfoResponse)this.response).grooupReward = new ArrayList(extendComponent.getGroupReward().keySet());
/* 43 */     ((ChargeInfoResponse)this.response).totalCharge = extendComponent.getTotalChargeCCB();
/* 44 */     ((ChargeInfoResponse)this.response).todayFirstCharge = extendComponent.getTodayRecharge();
/* 45 */     ((ChargeInfoResponse)this.response).todayFirstReward = new ArrayList(extendComponent.getTodayFirstReward().keySet());
/* 46 */     if (playerComponent.getTodayLevel() == 0) {
/* 47 */       playerComponent.setTodayLevel(ChargeUtil.updateEveryDayChargeLevel(playerComponent.getLevel()));
/*    */     }
/* 49 */     ((ChargeInfoResponse)this.response).showLevel = playerComponent.getTodayLevel();
/* 50 */     RuneComponent runeComponent = (RuneComponent)playerSession.getPlayer().createIfNotExist(RuneComponent.class);
/* 51 */     if (!MContext.isHeFu() && !extendComponent.getNewFirstReward().isEmpty() && runeComponent.getGoldEndTime() == 0) {
/* 52 */       GoldBuyParameter goldBuyParameter = (GoldBuyParameter)ParameterConstant.getParameter(70);
/* 53 */       runeComponent.setGoldEndTime(TimeUtil.currentTime() + goldBuyParameter.getLimitTime());
/* 54 */       PlayerUtil.updateKeyValueInfo(playerSession, KeyValueConstant.GOLDENDTIME.getKey(), runeComponent.getGoldEndTime());
/* 55 */     } else if (!MContext.isHeFu() && !extendComponent.getNewFirstReward().isEmpty() && runeComponent.getGoldEndTime() != 0 && TimeUtil.currentTime() < runeComponent.getGoldEndTime()) {
/* 56 */       PlayerUtil.updateKeyValueInfo(playerSession, KeyValueConstant.GOLDENDTIME.getKey(), runeComponent.getGoldEndTime());
/*    */     } 
/* 58 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\charge\ChargeInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */