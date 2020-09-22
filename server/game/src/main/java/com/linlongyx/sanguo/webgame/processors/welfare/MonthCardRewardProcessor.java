/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.WelfareParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.MonthCardRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.MonthCardRewardResponse;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class MonthCardRewardProcessor
/*    */   extends ProcessorBase<MonthCardRewardRequest, MonthCardRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new MonthCardRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, MonthCardRewardRequest request) {
/* 30 */     int state, type = request.type;
/* 31 */     if (type != 1 && type != 2 && type != 5) {
/* 32 */       return 11904;
/*    */     }
/*    */     
/* 35 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 36 */     Map<Integer, Integer> monthEndTime = welfareComponent.getMonthEndTime();
/* 37 */     Map<Integer, Integer> monthGetTime = welfareComponent.getMonthGetTime();
/*    */     
/* 39 */     int normalEndTime = ((Integer)monthEndTime.getOrDefault(Integer.valueOf(1), Integer.valueOf(0))).intValue();
/* 40 */     int specialEndTime = ((Integer)monthEndTime.getOrDefault(Integer.valueOf(2), Integer.valueOf(0))).intValue();
/* 41 */     int weekEndTime = ((Integer)monthEndTime.getOrDefault(Integer.valueOf(5), Integer.valueOf(0))).intValue();
/*    */     
/* 43 */     int normalGetTime = ((Integer)monthGetTime.getOrDefault(Integer.valueOf(1), Integer.valueOf(0))).intValue();
/* 44 */     int specialGetTime = ((Integer)monthGetTime.getOrDefault(Integer.valueOf(2), Integer.valueOf(0))).intValue();
/* 45 */     int weekGetTime = ((Integer)monthGetTime.getOrDefault(Integer.valueOf(5), Integer.valueOf(0))).intValue();
/*    */     
/* 47 */     WelfareParameter welfareParameter = (WelfareParameter)ParameterConstant.getParameter(19);
/*    */     
/* 49 */     Reward reward = new Reward();
/* 50 */     reward.type = 1;
/* 51 */     reward.id = CurrencyType.CCY.getType();
/*    */     
/* 53 */     if (type == 1) {
/* 54 */       state = MonthCardUtil.getNormalCardState(normalEndTime, normalGetTime);
/* 55 */       reward.num = welfareParameter.getNormalCCY();
/* 56 */     } else if (type == 2) {
/* 57 */       state = MonthCardUtil.getSpecialCardState(specialEndTime, specialGetTime);
/* 58 */       reward.num = welfareParameter.getSpecialCCY();
/*    */     } else {
/* 60 */       state = MonthCardUtil.getWeekCardState(weekEndTime, weekGetTime);
/* 61 */       reward.num = welfareParameter.getWeekCCY();
/*    */     } 
/*    */     
/* 64 */     if (0 == state) {
/* 65 */       return 11905;
/*    */     }
/* 67 */     if (2 == state) {
/* 68 */       return 11906;
/*    */     }
/* 70 */     monthGetTime.put(Integer.valueOf(type), Integer.valueOf(TimeUtil.currentTime()));
/* 71 */     welfareComponent.setMonthGetTime(monthGetTime);
/* 72 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 73 */     rewards.add(reward);
/* 74 */     FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.MonthCardReward, true);
/*    */     
/* 76 */     ((MonthCardRewardResponse)this.response).type = type;
/* 77 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\MonthCardRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */