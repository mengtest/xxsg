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
/*    */ public class FriendParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   public static final byte NEED_CHAT = 1;
/*    */   public static final byte AGREE = 1;
/*    */   public static final byte NEW_FRIEND = 1;
/*    */   public static final byte NEW_APPLY = 2;
/*    */   public static final byte LOST_FRIEND = 1;
/*    */   public static final byte NEW_CHAT = 2;
/*    */   public static final byte LOGIN = 3;
/*    */   public static final byte LOGOUT = 4;
/*    */   public static final byte SEND_POINT = 5;
/*    */   public static final byte NEED_RECEIVE_POWER = 1;
/*    */   public static final byte RECEIVED_POWER = 2;
/*    */   public static final byte SENT_POWER = 1;
/* 30 */   private int giftMax = 0;
/* 31 */   private int friendMax = 0;
/* 32 */   private int applyListMax = 0;
/* 33 */   private Reward friendReward = new Reward();
/*    */   private int goodFeeling;
/*    */   private int recommendLevel;
/*    */   private int recommendNum;
/*    */   
/*    */   public int getGiftMax() {
/* 39 */     return this.giftMax;
/*    */   }
/*    */   
/*    */   public int getFriendMax() {
/* 43 */     return this.friendMax;
/*    */   }
/*    */   
/*    */   public int getApplyListMax() {
/* 47 */     return this.applyListMax;
/*    */   }
/*    */   
/*    */   public Reward getFriendReward() {
/* 51 */     return this.friendReward;
/*    */   }
/*    */   
/*    */   public int getGoodFeeling() {
/* 55 */     return this.goodFeeling;
/*    */   }
/*    */   
/*    */   public int getRecommendLevel() {
/* 59 */     return this.recommendLevel;
/*    */   }
/*    */   
/*    */   public int getRecommendNum() {
/* 63 */     return this.recommendNum;
/*    */   }
/*    */   
/*    */   public FriendParameter() {
/* 67 */     super(32);
/*    */   }
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 72 */     this.giftMax = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/* 73 */     this.friendMax = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue());
/* 74 */     this.applyListMax = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue());
/*    */     
/* 76 */     String[] string = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue().split(",");
/* 77 */     this.friendReward.type = Byte.parseByte(string[0]);
/* 78 */     this.friendReward.id = Integer.parseInt(string[1]);
/* 79 */     this.friendReward.num = Integer.parseInt(string[2]);
/*    */     
/* 81 */     this.goodFeeling = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue());
/*    */     
/* 83 */     this.recommendLevel = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue());
/*    */     
/* 85 */     this.recommendNum = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(7))).getValue());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\FriendParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */