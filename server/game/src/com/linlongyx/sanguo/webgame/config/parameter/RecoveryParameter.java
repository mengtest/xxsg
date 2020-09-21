/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
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
/*    */ public class RecoveryParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private int ratio;
/* 19 */   private Reward rebornCost = new Reward();
/*    */   private int stoneRatio;
/*    */   
/*    */   public RecoveryParameter() {
/* 23 */     super(8);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 29 */     this.ratio = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/*    */     
/* 31 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue().split(",");
/* 32 */     this.rebornCost.type = Byte.parseByte(strings[0]);
/* 33 */     this.rebornCost.id = Integer.parseInt(strings[1]);
/* 34 */     this.rebornCost.num = Integer.parseInt(strings[2]);
/*    */     
/* 36 */     this.stoneRatio = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRatio() {
/* 41 */     return this.ratio;
/*    */   }
/*    */   
/*    */   public Reward getRebornCost() {
/* 45 */     return this.rebornCost;
/*    */   }
/*    */   
/*    */   public int getStoneRatio() {
/* 49 */     return this.stoneRatio;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\RecoveryParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */