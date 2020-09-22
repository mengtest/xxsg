/*    */ package com.linlongyx.sanguo.webgame.processors.sign;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.sign.SignComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.sign.SignInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.sign.SignInfoResponse;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ public class SignInfoProcessor
/*    */   extends ProcessorBase<SignInfoRequest, SignInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 17 */     this.response = (ResponseBase)new SignInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SignInfoRequest request) {
/* 22 */     SignComponent signComponent = (SignComponent)playerSession.getPlayer().createIfNotExist(SignComponent.class);
/*    */     
/* 24 */     if (signComponent.getShowLevel() == 0) {
/* 25 */       SignUtil.refreshShowLevel(playerSession.getPlayer());
/*    */     }
/* 27 */     ((SignInfoResponse)this.response).signToday = signComponent.isSignToday() ? 1 : 0;
/* 28 */     ((SignInfoResponse)this.response).signCount = signComponent.getSignCount();
/* 29 */     for (Iterator<Integer> iterator = signComponent.getSignReward().keySet().iterator(); iterator.hasNext(); ) { int day = ((Integer)iterator.next()).intValue();
/* 30 */       ((SignInfoResponse)this.response).signReward.add(Integer.valueOf(day)); }
/*    */     
/* 32 */     ((SignInfoResponse)this.response).todayCharge = signComponent.getDayCharge();
/* 33 */     ((SignInfoResponse)this.response).weekCharge = signComponent.getWeekCharge();
/* 34 */     ((SignInfoResponse)this.response).weekCharge = signComponent.getWeekCharge();
/* 35 */     ((SignInfoResponse)this.response).todayReward = signComponent.getTodayReward();
/* 36 */     ((SignInfoResponse)this.response).level = signComponent.getShowLevel();
/* 37 */     ((SignInfoResponse)this.response).weekReward = signComponent.getWeekReward();
/* 38 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\sign\SignInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */