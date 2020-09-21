/*    */ package com.linlongyx.sanguo.webgame.processors.sign;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.sign.SignComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.CheckFreeBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.sign.SignTodayRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.sign.SignTodayResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SignTodayProcessor
/*    */   extends ProcessorBase<SignTodayRequest, SignTodayResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new SignTodayResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SignTodayRequest request) {
/* 27 */     SignComponent signComponent = (SignComponent)playerSession.getPlayer().createIfNotExist(SignComponent.class);
/* 28 */     if (signComponent.isSignToday()) {
/* 29 */       return 15013;
/*    */     }
/* 31 */     signComponent.setSignToday(true);
/* 32 */     signComponent.setSignCount(signComponent.getSignCount() + 1);
/* 33 */     signComponent.getSignReward().put(Integer.valueOf(signComponent.getSignCount()), Integer.valueOf(TimeUtil.currentTime()));
/* 34 */     signComponent.setSignReward(signComponent.getSignReward());
/* 35 */     CheckFreeBean checkFreeBean = (CheckFreeBean)JsonTableService.getJsonData(signComponent.getSignCount(), CheckFreeBean.class);
/* 36 */     FinanceUtil.reward(FinanceUtil.transform(checkFreeBean.getReward()), playerSession.getPlayer(), ResourceEvent.DailySign, true);
/* 37 */     ((SignTodayResponse)this.response).signToday = signComponent.isSignToday() ? 1 : 0;
/* 38 */     ((SignTodayResponse)this.response).signCount = signComponent.getSignCount();
/* 39 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\sign\SignTodayProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */