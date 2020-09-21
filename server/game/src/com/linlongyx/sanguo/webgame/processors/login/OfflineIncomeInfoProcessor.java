/*    */ package com.linlongyx.sanguo.webgame.processors.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.welfare.MonthCardUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.OfflineIncomeInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.OfflineIncomeInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OfflineIncomeInfoProcessor
/*    */   extends ProcessorBase<OfflineIncomeInfoRequest, OfflineIncomeInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new OfflineIncomeInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, OfflineIncomeInfoRequest request) {
/* 30 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 31 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 32 */     Map<Integer, Integer> monthEndTime = welfareComponent.getMonthEndTime();
/* 33 */     int normalEndTime = ((Integer)monthEndTime.getOrDefault(Integer.valueOf(1), Integer.valueOf(0))).intValue();
/* 34 */     int specialEndTime = ((Integer)monthEndTime.getOrDefault(Integer.valueOf(2), Integer.valueOf(0))).intValue();
/*    */     
/* 36 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 37 */     PlayerUtil.handleOfflineIncome(playerSession, playerComponent.getLoginOutTime(), playerComponent.getMainSceneId(), rewards);
/* 38 */     if (rewards.size() > 0) {
/* 39 */       ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 40 */       ((OfflineIncomeInfoResponse)this.response).sec = extendComponent.getOfflineSec();
/* 41 */       ((OfflineIncomeInfoResponse)this.response).offlineRewards = rewards;
/*    */     } 
/* 43 */     ((OfflineIncomeInfoResponse)this.response).card1 = MonthCardUtil.isCardValue(1, normalEndTime);
/* 44 */     ((OfflineIncomeInfoResponse)this.response).card2 = MonthCardUtil.isCardValue(2, specialEndTime);
/* 45 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\OfflineIncomeInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */