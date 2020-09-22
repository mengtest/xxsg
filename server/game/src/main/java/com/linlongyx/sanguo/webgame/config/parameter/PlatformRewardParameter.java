/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlatformRewardParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private ArrayList<Reward> xinYueRewards;
/*    */   
/*    */   public PlatformRewardParameter() {
/* 16 */     super(72);
/*    */ 
/*    */     
/* 19 */     this.xinYueRewards = new ArrayList<>();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 26 */     this.xinYueRewards.clear();
/* 27 */     String[] string2 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue().split(";");
/* 28 */     for (String string : string2) {
/* 29 */       Reward reward = new Reward();
/* 30 */       String[] strings2 = string.split(",");
/* 31 */       tranform(reward, strings2);
/* 32 */       this.xinYueRewards.add(reward);
/*    */     } 
/*    */   }
/*    */   
/*    */   public ArrayList<Reward> getXinYueRewards() {
/* 37 */     return this.xinYueRewards;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\PlatformRewardParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */