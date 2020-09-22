/*    */ package com.linlongyx.core.framework.dao.type;
/*    */ 
/*    */ import java.text.DateFormat;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DateType
/*    */   extends BaseType
/*    */ {
/*    */   Date value;
/*    */   
/*    */   public Class<?> getType() {
/* 20 */     return Date.class;
/*    */   }
/*    */ 
/*    */   
/*    */   public String serial() {
/* 25 */     return this.value.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(String str) {
/* 30 */     DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/*    */     try {
/* 32 */       this.value = format.parse(str);
/* 33 */     } catch (ParseException e) {
/* 34 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void unserial(String str, String format) {
/* 39 */     DateFormat dateFormat = new SimpleDateFormat(format);
/*    */     try {
/* 41 */       this.value = dateFormat.parse(str);
/* 42 */     } catch (ParseException e) {
/* 43 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public Date getValue() {
/* 48 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(Date value) {
/* 52 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\type\DateType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */