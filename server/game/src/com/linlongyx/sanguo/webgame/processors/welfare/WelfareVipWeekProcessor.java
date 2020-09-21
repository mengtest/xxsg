/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.VipWeekBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.WelfareVipWeekRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.WelfareVipWeekResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WelfareVipWeekProcessor
/*    */   extends ProcessorBase<WelfareVipWeekRequest, WelfareVipWeekResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new WelfareVipWeekResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WelfareVipWeekRequest request) {
/* 30 */     int packageType = request.packageType;
/* 31 */     VipWeekBean vipWeekBean = (VipWeekBean)JsonTableService.getJsonData(packageType, VipWeekBean.class);
/* 32 */     if (null == vipWeekBean) {
/* 33 */       return 11902;
/*    */     }
/* 35 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 36 */     Map<Integer, Integer> vipWeekGoods = welfareComponent.getVipWeekGoods();
/* 37 */     int num = ((Integer)vipWeekGoods.getOrDefault(Integer.valueOf(packageType), Integer.valueOf(0))).intValue();
/* 38 */     if (vipWeekBean.getLimitType() != 0 && num >= vipWeekBean.getSelltimes()) {
/* 39 */       return 11903;
/*    */     }
/* 41 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, vipWeekBean.getPrice())) {
/* 42 */       return 10052;
/*    */     }
/* 44 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, vipWeekBean.getPrice(), ResourceEvent.WelfareVipWeek, true);
/* 45 */     num++;
/* 46 */     vipWeekGoods.put(Integer.valueOf(packageType), Integer.valueOf(num));
/* 47 */     welfareComponent.setVipWeekGoods(vipWeekGoods);
/*    */     
/* 49 */     FinanceUtil.reward(FinanceUtil.transform(vipWeekBean.getReward()), playerSession.getPlayer(), ResourceEvent.WelfareVipWeek, true);
/*    */     
/* 51 */     ((WelfareVipWeekResponse)this.response).packageType = packageType;
/* 52 */     ((WelfareVipWeekResponse)this.response).num = num;
/*    */     
/* 54 */     LogUtils.errorLog(new Object[] { "WelfareVipWeek", Long.valueOf(playerSession.getPlayer().getPlayerId()), ((WelfareVipWeekResponse)this.response).toString() });
/*    */     
/* 56 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\WelfareVipWeekProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */