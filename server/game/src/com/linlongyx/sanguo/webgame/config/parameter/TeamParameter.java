/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.structure.ODTime;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class TeamParameter
/*    */   extends AbstractParameter {
/*    */   private int maxFightTimes;
/*    */   private int intervalSeconds;
/*    */   private int doubleTimeRate;
/*    */   private int teamFullRate;
/*    */   private List<ODTime> doubleTimeList;
/*    */   
/*    */   public TeamParameter() {
/* 17 */     super(37);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 25 */     this.doubleTimeList = new ArrayList<>();
/*    */   }
/*    */   
/*    */   void init(ParameterBean bean) {
/* 29 */     this.maxFightTimes = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue()).intValue();
/* 30 */     this.intervalSeconds = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue()).intValue();
/* 31 */     this.doubleTimeRate = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue());
/* 32 */     this.teamFullRate = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue());
/* 33 */     String[] strs = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(8))).getValue().split(";");
/* 34 */     for (String s : strs) {
/* 35 */       String[] tmp = s.split(":");
/* 36 */       ODTime odTime = new ODTime(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
/* 37 */       this.doubleTimeList.add(odTime);
/*    */     } 
/*    */   }
/*    */   
/*    */   public int getMaxFightTimes() {
/* 42 */     return this.maxFightTimes;
/*    */   }
/*    */   
/*    */   public int getIntervalSeconds() {
/* 46 */     return this.intervalSeconds;
/*    */   }
/*    */   
/*    */   public int getDoubleTimeRate() {
/* 50 */     return this.doubleTimeRate;
/*    */   }
/*    */   
/*    */   public int getTeamFullRate() {
/* 54 */     return this.teamFullRate;
/*    */   }
/*    */   
/*    */   public List<ODTime> getDoubleTimeList() {
/* 58 */     return this.doubleTimeList;
/*    */   }
/*    */   
/*    */   public boolean isInDoubleTime(int min) {
/* 62 */     for (ODTime odTime : this.doubleTimeList) {
/* 63 */       if (min >= odTime.getOriginTime() && min < odTime.getDestinationTime()) {
/* 64 */         return true;
/*    */       }
/*    */     } 
/* 67 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\TeamParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */