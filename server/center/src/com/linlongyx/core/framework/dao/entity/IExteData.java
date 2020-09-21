/*    */ package com.linlongyx.core.framework.dao.entity;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class IExteData
/*    */ {
/*    */   private boolean update;
/*    */   
/*    */   public abstract void setVal(String paramString);
/*    */   
/*    */   public abstract String toStr();
/*    */   
/*    */   public void reset() {}
/*    */   
/*    */   public synchronized void setUpdate(boolean update) {
/* 31 */     this.update = update;
/*    */   }
/*    */   
/*    */   public synchronized boolean generateSql(StringBuilder stringBuilder, long playerId) {
/* 35 */     if (!this.update) return false; 
/* 36 */     stringBuilder.append("(");
/* 37 */     stringBuilder.append("'").append(playerId).append("',");
/* 38 */     stringBuilder.append("'").append(getClass().getName()).append("',");
/* 39 */     stringBuilder.append("'").append(toStr()).append("'");
/* 40 */     stringBuilder.append("),");
/* 41 */     this.update = false;
/* 42 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\dao\entity\IExteData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */