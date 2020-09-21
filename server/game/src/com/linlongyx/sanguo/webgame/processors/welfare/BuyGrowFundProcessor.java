/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.FundParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.KeyValueConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.login.VipUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.BuyGrowFundRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.BuyGrowFundResponse;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class BuyGrowFundProcessor extends ProcessorBase<BuyGrowFundRequest, BuyGrowFundResponse> {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new BuyGrowFundResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BuyGrowFundRequest request) {
/* 27 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 28 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 29 */     FundParameter fundParameter = (FundParameter)ParameterConstant.getParameter(25);
/*    */ 
/*    */ 
/*    */     
/* 33 */     if (!VipUtil.isAllow(playerComponent.getVip(), 4)) {
/* 34 */       return 10088;
/*    */     }
/* 36 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, fundParameter.getCostCCB())) {
/* 37 */       return 10052;
/*    */     }
/* 39 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, fundParameter.getCostCCB(), ResourceEvent.BuyGrowFund);
/* 40 */     welfareComponent.setBuyFund(true);
/* 41 */     WelfareUtil.growfundNum.incrementAndGet();
/* 42 */     ((BuyGrowFundResponse)this.response).isBuy = (welfareComponent.isBuyFund() == true) ? 1 : 0;
/* 43 */     for (Iterator<Integer> iterator = welfareComponent.getFundReward().keySet().iterator(); iterator.hasNext(); ) { int key = ((Integer)iterator.next()).intValue();
/* 44 */       ((BuyGrowFundResponse)this.response).fundLevel.add(Integer.valueOf(key)); }
/*    */     
/* 46 */     ((BuyGrowFundResponse)this.response).groupNUm = WelfareUtil.growfundNum.get();
/* 47 */     PlayerUtil.sendKeyValue(KeyValueConstant.GROUPCHARGE.getKey(), WelfareUtil.growfundNum.get());
/* 48 */     PlayerUtil.sendNotice(9, playerSession.getPlayer(), 0, null);
/* 49 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\BuyGrowFundProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */