/*    */ package com.linlongyx.sanguo.webgame.processors.login;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.VipAccessBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
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
/*    */ 
/*    */ public class VipUtil
/*    */ {
/*    */   public static final int TYPE_1 = 1;
/*    */   public static final int TYPE_3 = 3;
/*    */   public static final int TYPE_4 = 4;
/*    */   public static final int TYPE_7 = 7;
/*    */   public static final int TYPE_8 = 8;
/*    */   public static final int TYPE_9 = 9;
/*    */   public static final int TYPE_10 = 10;
/*    */   public static final int TYPE_11 = 11;
/*    */   public static final int TYPE_12 = 12;
/*    */   public static final int TYPE_13 = 13;
/*    */   public static final int TYPE_14 = 14;
/*    */   public static final int TYPE_17 = 17;
/*    */   public static final int TYPE_18 = 18;
/*    */   public static final int TYPE_19 = 19;
/*    */   public static final int TYPE_20 = 20;
/*    */   public static final int TYPE_21 = 21;
/*    */   public static final int TYPE_22 = 22;
/*    */   public static final int TYPE_23 = 23;
/*    */   
/*    */   public static int getNum(int vip, int type) {
/* 40 */     VipAccessBean vipAccessBean = (VipAccessBean)JsonTableService.getJsonData(vip, VipAccessBean.class);
/* 41 */     if (null == vipAccessBean) {
/* 42 */       return 0;
/*    */     }
/* 44 */     ArrayList<VipAccessBean.AccessTimesBean> accessTimes = vipAccessBean.getAccessTimes();
/* 45 */     for (VipAccessBean.AccessTimesBean accessTimesBean : accessTimes) {
/* 46 */       if (accessTimesBean.getType() == type) {
/* 47 */         return accessTimesBean.getValue();
/*    */       }
/*    */     } 
/* 50 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isAllow(int vip, int type) {
/* 60 */     return (getNum(vip, type) > 0);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\VipUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */