/*    */ package com.linlongyx.sanguo.webgame.processors.runewar;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RuneWarBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RuneWarRewardBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.runewar.RunewarRewardGetRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.runewar.RunewarRewardGetResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RunewarRewardGetProcessor
/*    */   extends ProcessorBase<RunewarRewardGetRequest, RunewarRewardGetResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new RunewarRewardGetResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RunewarRewardGetRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 45))
/* 31 */       return 10061; 
/* 32 */     int rewardId = request.id;
/* 33 */     RuneComponent runeComponent = (RuneComponent)playerSession.getPlayer().createIfNotExist(RuneComponent.class);
/* 34 */     if (request.type == 1) {
/* 35 */       if (!runeComponent.getDailyReward().containsKey(Integer.valueOf(rewardId))) {
/* 36 */         return 10095;
/*    */       }
/* 38 */       if (((Integer)runeComponent.getDailyReward().get(Integer.valueOf(rewardId))).intValue() != 1) {
/* 39 */         return 10091;
/*    */       }
/*    */       
/* 42 */       RuneWarRewardBean rewardBean = (RuneWarRewardBean)JsonTableService.getJsonData(rewardId, RuneWarRewardBean.class);
/* 43 */       if (rewardBean == null) {
/* 44 */         return 10095;
/*    */       }
/* 46 */       FinanceUtil.reward(FinanceUtil.transform(rewardBean.getReward()), playerSession.getPlayer(), ResourceEvent.RunewarDailyReward, true);
/*    */       
/* 48 */       runeComponent.getDailyReward().put(Integer.valueOf(rewardId), Integer.valueOf(2));
/* 49 */       runeComponent.setDailyReward(runeComponent.getDailyReward());
/* 50 */     } else if (request.type == 2) {
/* 51 */       if (!runeComponent.getStageReward().containsKey(Integer.valueOf(rewardId))) {
/* 52 */         return 10095;
/*    */       }
/* 54 */       if (((Integer)runeComponent.getStageReward().get(Integer.valueOf(rewardId))).intValue() != 1) {
/* 55 */         return 10091;
/*    */       }
/*    */       
/* 58 */       RuneWarBean rewardBean = (RuneWarBean)JsonTableService.getJsonData(rewardId, RuneWarBean.class);
/* 59 */       if (rewardBean == null) {
/* 60 */         return 10095;
/*    */       }
/* 62 */       FinanceUtil.reward(FinanceUtil.transform(rewardBean.getRankReward()), playerSession.getPlayer(), ResourceEvent.RunewarStageReward, true);
/*    */       
/* 64 */       runeComponent.getStageReward().put(Integer.valueOf(rewardId), Integer.valueOf(2));
/* 65 */       runeComponent.setStageReward(runeComponent.getStageReward());
/*    */     } 
/* 67 */     ((RunewarRewardGetResponse)this.response).type = request.type;
/* 68 */     ((RunewarRewardGetResponse)this.response).id = request.id;
/* 69 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\runewar\RunewarRewardGetProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */