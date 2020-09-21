/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class MailParameter
/*    */   extends AbstractParameter {
/*    */   private Map<Byte, Integer> mailMaxNumMap;
/*    */   private int mailPartSize;
/*    */   private int mailMaxDay;
/*    */   
/*    */   public MailParameter() {
/* 14 */     super(5);
/*    */ 
/*    */     
/* 17 */     this.mailMaxNumMap = new HashMap<>();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private int maxTitle;
/*    */   
/*    */   private int maxContext;
/*    */   
/*    */   private String mailName;
/*    */ 
/*    */   
/*    */   public int getMailMaxNum(byte type) {
/* 30 */     if (this.mailMaxNumMap.containsKey(Byte.valueOf(type))) {
/* 31 */       return ((Integer)this.mailMaxNumMap.get(Byte.valueOf(type))).intValue();
/*    */     }
/* 33 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMailPartSize() {
/* 42 */     return this.mailPartSize;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMailMaxDay() {
/* 50 */     return this.mailMaxDay;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxTitle() {
/* 58 */     return this.maxTitle;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxContext() {
/* 66 */     return this.maxContext;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMailName() {
/* 74 */     return this.mailName;
/*    */   }
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 79 */     this.mailMaxNumMap.clear();
/* 80 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue().split(";");
/* 81 */     for (String string : strings) {
/* 82 */       String[] strings2 = string.split(":");
/* 83 */       this.mailMaxNumMap.put(Byte.valueOf(Byte.parseByte(strings2[0])), Integer.valueOf(Integer.parseInt(strings2[1])));
/*    */     } 
/* 85 */     this.mailPartSize = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue());
/* 86 */     this.mailMaxDay = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue());
/* 87 */     this.maxTitle = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue());
/* 88 */     this.maxContext = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue());
/* 89 */     this.mailName = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\MailParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */