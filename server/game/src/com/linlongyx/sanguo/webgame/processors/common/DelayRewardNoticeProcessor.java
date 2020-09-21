/*    */ package com.linlongyx.sanguo.webgame.processors.common;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.cat.FortuneCatComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.divine.DivineComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.luckyTurntable.LuckyTurntableComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.rebate.RechargeRebateComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.talisman.TalismanComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.turnplate.TurnplateComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.common.DelayRewardNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.common.DelayRewardNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DelayRewardNoticeProcessor
/*    */   extends ProcessorBase<DelayRewardNoticeRequest, DelayRewardNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new DelayRewardNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DelayRewardNoticeRequest request) {
/* 32 */     if (1 == request.id) {
/* 33 */       FortuneCatComponent fortuneCatComponent = (FortuneCatComponent)playerSession.getPlayer().createIfNotExist(FortuneCatComponent.class);
/* 34 */       ArrayList<Reward> rewards = fortuneCatComponent.getRewards();
/* 35 */       if (rewards.size() > 0) {
/* 36 */         FinanceUtil.rewardNotice(rewards, playerSession.getPlayer(), ResourceEvent.FortuneCatReward);
/* 37 */         fortuneCatComponent.resetRewards();
/*    */       } 
/* 39 */     } else if (2 == request.id) {
/* 40 */       LuckyTurntableComponent luckyTurntableComponent = (LuckyTurntableComponent)playerSession.getPlayer().createIfNotExist(LuckyTurntableComponent.class);
/* 41 */       ArrayList<Reward> rewards = (ArrayList<Reward>)luckyTurntableComponent.getRewardMap().remove(Integer.valueOf(LuckyTurntableComponent.TYPE_LUCKY_TURNTABLE_KEY));
/* 42 */       if (rewards != null) {
/* 43 */         FinanceUtil.rewardNotice(rewards, playerSession.getPlayer(), ResourceEvent.LuckyTurntable);
/*    */       }
/* 45 */     } else if (3 == request.id) {
/* 46 */       TurnplateComponent turnplateComponent = (TurnplateComponent)playerSession.getPlayer().createIfNotExist(TurnplateComponent.class);
/* 47 */       ArrayList<Reward> rewards = turnplateComponent.getRewards();
/* 48 */       if (rewards.size() > 0) {
/* 49 */         FinanceUtil.rewardNotice(rewards, playerSession.getPlayer(), ResourceEvent.Turnplate);
/* 50 */         turnplateComponent.getRewards().clear();
/*    */       } 
/* 52 */     } else if (4 == request.id) {
/* 53 */       LuckyTurntableComponent luckyTurntableComponent = (LuckyTurntableComponent)playerSession.getPlayer().createIfNotExist(LuckyTurntableComponent.class);
/* 54 */       ArrayList<Reward> rewards = (ArrayList<Reward>)luckyTurntableComponent.getRewardMap().remove(Integer.valueOf(LuckyTurntableComponent.TYPE_PET_TURNTABLE_KEY));
/* 55 */       if (rewards != null) {
/* 56 */         FinanceUtil.rewardNotice(rewards, playerSession.getPlayer(), ResourceEvent.PetTurntable);
/*    */       }
/* 58 */     } else if (5 == request.id) {
/* 59 */       RechargeRebateComponent rechargeRebateComponent = (RechargeRebateComponent)playerSession.getPlayer().createIfNotExist(RechargeRebateComponent.class);
/* 60 */       ArrayList<Reward> rewards = rechargeRebateComponent.getReward();
/* 61 */       if (rewards.size() > 0) {
/* 62 */         FinanceUtil.rewardNotice(rewards, playerSession.getPlayer(), ResourceEvent.ChargeRebateReward);
/* 63 */         rechargeRebateComponent.getReward().clear();
/*    */       } 
/* 65 */     } else if (6 == request.id) {
/* 66 */       DivineComponent divineComponent = (DivineComponent)playerSession.getPlayer().createIfNotExist(DivineComponent.class);
/* 67 */       ArrayList<Reward> rewards = divineComponent.getRewardList();
/* 68 */       if (rewards.size() > 0) {
/* 69 */         FinanceUtil.rewardNotice(rewards, playerSession.getPlayer(), ResourceEvent.DivineOneDraw);
/* 70 */         divineComponent.getRewardList().clear();
/*    */       } 
/* 72 */     } else if (7 == request.id) {
/* 73 */       TalismanComponent talismanComponent = (TalismanComponent)playerSession.getPlayer().createIfNotExist(TalismanComponent.class);
/* 74 */       ArrayList<Reward> rewards = (ArrayList<Reward>)talismanComponent.getRewardMap().remove(Integer.valueOf(TalismanComponent.TYPE_TALISMAN_KEY));
/* 75 */       if (rewards != null) {
/* 76 */         FinanceUtil.rewardNotice(rewards, playerSession.getPlayer(), ResourceEvent.TalismanDraw);
/*    */       }
/*    */     } 
/* 79 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\common\DelayRewardNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */