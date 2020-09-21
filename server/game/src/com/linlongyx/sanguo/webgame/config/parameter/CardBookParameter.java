/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class CardBookParameter extends AbstractParameter {
/*    */   private ArrayList<Integer> colorList;
/*    */   private int bomCardAskLimit;
/*    */   private int colorDanAsLimit;
/*    */   private int giveLimit;
/*    */   
/*    */   public CardBookParameter() {
/* 13 */     super(50);
/*    */ 
/*    */     
/* 16 */     this.colorList = new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   private int robatorResponse;
/*    */   
/*    */   private int responesQuality;
/*    */   private int askCD;
/*    */   private int maxStar;
/*    */   private int resColorDanQuality;
/*    */   
/*    */   void init(ParameterBean bean) {
/* 28 */     this.colorList.clear();
/* 29 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue().split(",");
/* 30 */     for (String string : strings) {
/* 31 */       this.colorList.add(Integer.valueOf(Integer.parseInt(string)));
/*    */     }
/* 33 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue().split(",");
/* 34 */     this.bomCardAskLimit = Integer.parseInt(strings[0]);
/* 35 */     this.colorDanAsLimit = Integer.parseInt(strings[1]);
/* 36 */     this.giveLimit = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue());
/* 37 */     this.robatorResponse = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue());
/* 38 */     this.responesQuality = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue());
/* 39 */     this.askCD = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue());
/* 40 */     this.maxStar = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(7))).getValue());
/* 41 */     this.resColorDanQuality = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(9))).getValue());
/*    */   }
/*    */   
/*    */   public ArrayList<Integer> getColorList() {
/* 45 */     return this.colorList;
/*    */   }
/*    */   
/*    */   public int getBomCardAskLimit() {
/* 49 */     return this.bomCardAskLimit;
/*    */   }
/*    */   
/*    */   public int getColorDanAsLimit() {
/* 53 */     return this.colorDanAsLimit;
/*    */   }
/*    */   
/*    */   public int getGiveLimit() {
/* 57 */     return this.giveLimit;
/*    */   }
/*    */   
/*    */   public int getRobatorResponse() {
/* 61 */     return this.robatorResponse;
/*    */   }
/*    */   
/*    */   public int getResponesQuality() {
/* 65 */     return this.responesQuality;
/*    */   }
/*    */   
/*    */   public int getAskCD() {
/* 69 */     return this.askCD;
/*    */   }
/*    */   
/*    */   public int getMaxStar() {
/* 73 */     return this.maxStar;
/*    */   }
/*    */   
/*    */   public int getResColorDanQuality() {
/* 77 */     return this.resColorDanQuality;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\CardBookParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */