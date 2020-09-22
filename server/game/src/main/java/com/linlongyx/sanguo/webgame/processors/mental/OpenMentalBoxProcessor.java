/*    */ package com.linlongyx.sanguo.webgame.processors.mental;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.mental.MentalComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.mental.MentalUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaTempleRewardBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mental.OpenMentalBoxRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mental.OpenMentalBoxResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OpenMentalBoxProcessor
/*    */   extends ProcessorBase<OpenMentalBoxRequest, OpenMentalBoxResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new OpenMentalBoxResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, OpenMentalBoxRequest request) {
/* 31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 58))
/* 32 */       return 10061; 
/* 33 */     IPlayer player = playerSession.getPlayer();
/* 34 */     MentalComponent mentalComponent = (MentalComponent)player.createIfNotExist(MentalComponent.class);
/*    */     
/* 36 */     int point = mentalComponent.getPoint();
/* 37 */     if (point <= 0) {
/* 38 */       return 17308;
/*    */     }
/* 40 */     int showLevel = MentalUtil.getShowLevel();
/* 41 */     ZhenfaTempleRewardBean zhenfaTempleRewardBean = (ZhenfaTempleRewardBean)JsonTableService.getJsonData(request.rewardId, ZhenfaTempleRewardBean.class);
/* 42 */     if (null == zhenfaTempleRewardBean) {
/* 43 */       return 10006;
/*    */     }
/* 45 */     if (zhenfaTempleRewardBean.getWorldLevel() != showLevel) {
/* 46 */       return 17307;
/*    */     }
/* 48 */     if (mentalComponent.getRewardIds().contains(Integer.valueOf(request.rewardId))) {
/* 49 */       return 15002;
/*    */     }
/* 51 */     if (point < zhenfaTempleRewardBean.getIntegral()) {
/* 52 */       return 15003;
/*    */     }
/* 54 */     mentalComponent.getRewardIds().add(Integer.valueOf(request.rewardId));
/* 55 */     mentalComponent.setRewardIds(mentalComponent.getRewardIds());
/*    */     
/* 57 */     ((OpenMentalBoxResponse)this.response).rewards = FinanceUtil.rewardGet(FinanceUtil.transform(zhenfaTempleRewardBean.getReward()), player, ResourceEvent.MentalLotteryBox, true);
/* 58 */     ((OpenMentalBoxResponse)this.response).rewardId = request.rewardId;
/* 59 */     ((OpenMentalBoxResponse)this.response).showLevel = showLevel;
/* 60 */     LogUtils.errorLog(new Object[] { "OpenMentalBoxProcessor", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(request.rewardId) });
/* 61 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mental\OpenMentalBoxProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */