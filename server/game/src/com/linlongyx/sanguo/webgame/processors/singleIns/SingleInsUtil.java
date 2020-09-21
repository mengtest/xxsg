/*    */ package com.linlongyx.sanguo.webgame.processors.singleIns;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
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
/*    */ public class SingleInsUtil
/*    */ {
/*    */   public static boolean isDayValue(int day) {
/* 21 */     int week = TimeUtil.nowWeek();
/* 22 */     if (0 == week) {
/* 23 */       week = 7;
/*    */     }
/* 25 */     return (0 == day || day == week);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isDayValue(ArrayList<Integer> list) {
/* 34 */     for (Integer day : list) {
/* 35 */       if (isDayValue(day.intValue())) {
/* 36 */         return true;
/*    */       }
/*    */     } 
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getChallengeTimes(IPlayer player, int bossId) {
/* 49 */     SingleInsComponent singleInsComponent = (SingleInsComponent)player.createIfNotExist(SingleInsComponent.class);
/* 50 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 51 */     int currTimes = 0;
/* 52 */     currTimes += bossHomeParameter.getDailyChallenge();
/* 53 */     if (null != singleInsComponent.getBuyTimeMap().get(Integer.valueOf(bossId))) {
/* 54 */       currTimes += ((Integer)singleInsComponent.getBuyTimeMap().get(Integer.valueOf(bossId))).intValue();
/*    */     }
/* 56 */     return currTimes;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\singleIns\SingleInsUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */