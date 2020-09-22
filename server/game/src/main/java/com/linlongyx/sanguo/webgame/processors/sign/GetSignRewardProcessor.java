/*    */ package com.linlongyx.sanguo.webgame.processors.sign;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.sign.SignComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.CheckDeluxeBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.sign.GetSignRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.sign.GetSignRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetSignRewardProcessor
/*    */   extends ProcessorBase<GetSignRewardRequest, GetSignRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new GetSignRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetSignRewardRequest request) {
/* 29 */     SignComponent signComponent = (SignComponent)playerSession.getPlayer().createIfNotExist(SignComponent.class);
/* 30 */     int level = signComponent.getShowLevel();
/* 31 */     CheckDeluxeBean checkDeluxeBean = (CheckDeluxeBean)JsonTableService.getJsonData(request.id, CheckDeluxeBean.class);
/* 32 */     if (null == checkDeluxeBean) {
/* 33 */       return 15015;
/*    */     }
/* 35 */     if (checkDeluxeBean.getConnectortype() != PlayerUtil.getPlatformType()) {
/* 36 */       LogUtil.errorLog(new Object[] { "GetSignRewardProcessor", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(request.id), MContext.getPlatform(), Integer.valueOf(PlayerUtil.getPlatformType()) });
/* 37 */       return 11808;
/*    */     } 
/* 39 */     if (level != checkDeluxeBean.getLevel()) {
/* 40 */       return 15019;
/*    */     }
/*    */     
/* 43 */     if (request.type == 0) {
/*    */       
/* 45 */       if (checkDeluxeBean.getType() == 1) {
/* 46 */         return 15015;
/*    */       }
/* 48 */       if (signComponent.getDayCharge() < checkDeluxeBean.getRmb()) {
/* 49 */         return 15018;
/*    */       }
/* 51 */       if (signComponent.getTodayReward().indexOf(Integer.valueOf(checkDeluxeBean.getRmb())) >= 0) {
/* 52 */         return 15014;
/*    */       }
/* 54 */       signComponent.getTodayReward().add(Integer.valueOf(checkDeluxeBean.getRmb()));
/* 55 */       signComponent.setTodayReward(signComponent.getTodayReward());
/*    */     } else {
/*    */       
/* 58 */       if (checkDeluxeBean.getType() == 0) {
/* 59 */         return 15015;
/*    */       }
/* 61 */       if (signComponent.getWeekCharge() < checkDeluxeBean.getRmb()) {
/* 62 */         return 15018;
/*    */       }
/* 64 */       if (signComponent.getWeekReward().indexOf(Integer.valueOf(checkDeluxeBean.getRmb())) >= 0) {
/* 65 */         return 15014;
/*    */       }
/* 67 */       signComponent.getWeekReward().add(Integer.valueOf(checkDeluxeBean.getRmb()));
/* 68 */       signComponent.setWeekReward(signComponent.getWeekReward());
/*    */     } 
/* 70 */     FinanceUtil.reward(FinanceUtil.transform(checkDeluxeBean.getReward()), playerSession.getPlayer(), ResourceEvent.LuxurySign, true);
/* 71 */     ((GetSignRewardResponse)this.response).type = request.type;
/* 72 */     ((GetSignRewardResponse)this.response).id = request.id;
/* 73 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\sign\GetSignRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */