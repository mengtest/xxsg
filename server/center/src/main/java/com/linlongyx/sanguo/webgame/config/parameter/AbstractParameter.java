/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractParameter
/*    */ {
/*    */   public static final int TYPE_1 = 1;
/*    */   public static final int TYPE_2 = 2;
/*    */   public static final int TYPE_3 = 3;
/*    */   public static final int TYPE_4 = 4;
/*    */   public static final int TYPE_5 = 5;
/*    */   public static final int TYPE_6 = 6;
/*    */   public static final int TYPE_7 = 7;
/*    */   public static final int TYPE_8 = 8;
/*    */   public static final int TYPE_9 = 9;
/*    */   public static final int TYPE_10 = 10;
/*    */   public static final int TYPE_11 = 11;
/*    */   public static final int TYPE_12 = 12;
/*    */   public static final int TYPE_13 = 13;
/*    */   public static final int TYPE_14 = 14;
/*    */   public static final int TYPE_15 = 15;
/*    */   public static final int TYPE_16 = 16;
/*    */   public static final int TYPE_17 = 17;
/*    */   public static final int TYPE_18 = 18;
/*    */   public static final int TYPE_19 = 19;
/*    */   public static final int TYPE_20 = 20;
/*    */   public static final int TYPE_21 = 21;
/*    */   protected int type;
/*    */   
/*    */   public AbstractParameter(int type) {
/* 38 */     this.type = type;
/*    */   }
/*    */   
/*    */   int getType() {
/* 42 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void load() {
/* 49 */     ParameterBean parameterBean = (ParameterBean)JsonTableService.getJsonData(this.type, ParameterBean.class);
/* 50 */     if (null == parameterBean) {
/*    */       return;
/*    */     }
/* 53 */     init(parameterBean);
/*    */   }
/*    */   
/*    */   abstract void init(ParameterBean paramParameterBean);
/*    */   
/*    */   public void tranform(Reward reward, String[] strings1) {
/* 59 */     reward.type = Byte.parseByte(strings1[0]);
/* 60 */     reward.id = Integer.parseInt(strings1[1]);
/* 61 */     reward.num = Integer.parseInt(strings1[2]);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\AbstractParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */