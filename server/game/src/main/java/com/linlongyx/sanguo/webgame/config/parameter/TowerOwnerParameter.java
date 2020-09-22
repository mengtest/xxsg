/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class TowerOwnerParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private int rankLimit;
/*    */   private int showLimit;
/*    */   
/*    */   public TowerOwnerParameter() {
/* 14 */     super(67);
/*    */   }
/*    */ 
/*    */   
/*    */   private ArrayList<Reward> mobaiReward;
/*    */   
/*    */   private int recordLimit;
/*    */   
/*    */   private int cdTime;
/*    */   
/*    */   void init(ParameterBean bean) {
/* 25 */     this.rankLimit = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue());
/* 26 */     this.showLimit = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue());
/*    */     
/* 28 */     this.mobaiReward = new ArrayList<>();
/* 29 */     String[] mobaiArr = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue().split(";");
/* 30 */     for (String str : mobaiArr) {
/* 31 */       Reward reward = new Reward();
/* 32 */       String[] rewardArr = str.split(",");
/* 33 */       reward.type = Byte.parseByte(rewardArr[0]);
/* 34 */       reward.id = Integer.parseInt(rewardArr[1]);
/* 35 */       reward.num = Long.parseLong(rewardArr[2]);
/* 36 */       this.mobaiReward.add(reward);
/*    */     } 
/* 38 */     this.recordLimit = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue());
/* 39 */     this.cdTime = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue());
/*    */   }
/*    */   
/*    */   public int getRankLimit() {
/* 43 */     return this.rankLimit;
/*    */   }
/*    */   
/*    */   public int getShowLimit() {
/* 47 */     return this.showLimit;
/*    */   }
/*    */   
/*    */   public ArrayList<Reward> getMobaiReward() {
/* 51 */     return this.mobaiReward;
/*    */   }
/*    */   
/*    */   public int getRecordLimit() {
/* 55 */     return this.recordLimit;
/*    */   }
/*    */   
/*    */   public int getCdTime() {
/* 59 */     return this.cdTime;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\TowerOwnerParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */