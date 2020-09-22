/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.VipAccessBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.WelfareVipDailyRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.WelfareVipDailyResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WelfareVipDailyProcessor
/*    */   extends ProcessorBase<WelfareVipDailyRequest, WelfareVipDailyResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new WelfareVipDailyResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WelfareVipDailyRequest request) {
/* 30 */     int vipLevel = request.vipLevel;
/* 31 */     VipAccessBean vipAccessBean = (VipAccessBean)JsonTableService.getJsonData(vipLevel, VipAccessBean.class);
/* 32 */     if (null == vipAccessBean) {
/* 33 */       return 10092;
/*    */     }
/* 35 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 36 */     if (playerComponent.getVip() < vipAccessBean.getVipLevel()) {
/* 37 */       return 10088;
/*    */     }
/* 39 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 40 */     Set<Integer> vipDailySet = welfareComponent.getVipDailySet();
/* 41 */     if (vipDailySet.contains(Integer.valueOf(vipLevel))) {
/* 42 */       return 11901;
/*    */     }
/* 44 */     vipDailySet.add(Integer.valueOf(vipLevel));
/* 45 */     welfareComponent.setVipDailySet(vipDailySet);
/*    */     
/* 47 */     FinanceUtil.reward(FinanceUtil.transform(vipAccessBean.getDailyPackage()), playerSession.getPlayer(), ResourceEvent.WelfareVipDaily, true);
/*    */     
/* 49 */     ((WelfareVipDailyResponse)this.response).vipLevel = vipLevel;
/*    */     
/* 51 */     LogUtils.errorLog(new Object[] { "WelfareVipDaily", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(vipLevel) });
/*    */     
/* 53 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\WelfareVipDailyProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */