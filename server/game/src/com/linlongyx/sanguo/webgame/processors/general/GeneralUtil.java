/*    */ package com.linlongyx.sanguo.webgame.processors.general;
/*    */ 
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.GeneralInsBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GeneralParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.MilitaryInsType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.offices.MilitaryUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GeneralPointData;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GeneralUtil
/*    */ {
/*    */   public static int getLeftOrder(GeneralComponent generalComponent) {
/* 28 */     GeneralParameter generalParameter = (GeneralParameter)ParameterConstant.getParameter(12);
/* 29 */     int costOrder = generalComponent.getCostOrder();
/* 30 */     int restore = generalComponent.getRestore();
/* 31 */     int nextTime = generalComponent.getNextTime();
/* 32 */     int buyTime = generalComponent.getBuyTime();
/* 33 */     int refuseTime = MilitaryUtil.getTime(generalComponent.getPlayerId(), MilitaryInsType.GeneralIns.getType(), 0);
/* 34 */     int cd = generalParameter.getRestoreCd() - refuseTime;
/* 35 */     int maxOrder = generalParameter.getMaxOrder();
/* 36 */     int leftNum = maxOrder + buyTime + restore - costOrder;
/* 37 */     int curTime = TimeUtil.currentTime();
/* 38 */     if (leftNum < 0) {
/* 39 */       restore -= leftNum;
/* 40 */       leftNum = -leftNum;
/* 41 */       generalComponent.setRestore(restore);
/* 42 */       if (nextTime == 0) {
/* 43 */         nextTime = curTime + cd;
/*    */       }
/*    */     } 
/* 46 */     if (nextTime > 0) {
/* 47 */       int delta = curTime - nextTime;
/* 48 */       if (delta > 0) {
/* 49 */         nextTime = curTime + cd - delta % cd;
/* 50 */         restore += delta / cd + 1;
/*    */       } 
/* 52 */       leftNum = maxOrder + buyTime + restore - costOrder;
/* 53 */       delta = leftNum - maxOrder;
/* 54 */       if (delta >= 0) {
/* 55 */         restore -= delta;
/* 56 */         nextTime = 0;
/* 57 */         leftNum = maxOrder + buyTime + restore - costOrder;
/*    */       } 
/* 59 */       generalComponent.setRestore(restore);
/* 60 */       generalComponent.setNextTime(nextTime);
/*    */     } 
/* 62 */     return leftNum;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static GeneralPointData getGeneralPointData(GeneralInsBean generalInsBean, GeneralComponent generalComponent) {
/* 73 */     GeneralPointData generalPointData = new GeneralPointData();
/* 74 */     generalPointData.insId = generalInsBean.getInsId();
/* 75 */     generalPointData.point = generalInsBean.getCheckPoint();
/* 76 */     generalPointData.star = ((Integer)generalComponent.getStars().getOrDefault(Integer.valueOf(generalInsBean.getInsId()), Integer.valueOf(0))).intValue();
/* 77 */     int reset = ((Integer)generalComponent.getResetTimes().getOrDefault(Integer.valueOf(generalInsBean.getInsId()), Integer.valueOf(0))).intValue();
/* 78 */     generalPointData.reset = reset;
/* 79 */     generalPointData.time = ((Integer)generalComponent.getChallenges().getOrDefault(Integer.valueOf(generalInsBean.getInsId()), Integer.valueOf(0))).intValue() - reset * generalInsBean.getDailyTimes();
/* 80 */     return generalPointData;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void getReward(GeneralInsBean generalInsBean, ArrayList<Reward> rewards) {
/* 90 */     rewards.addAll(FinanceUtil.transform(generalInsBean.getSureReward()));
/* 91 */     rewards.addAll(FinanceUtil.transform(generalInsBean.getProReward()));
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\general\GeneralUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */