/*    */ package com.linlongyx.core.framework.dao.entity;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class IExteMapEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   protected long playerId;
/*    */   @TableField(isKey = true, len = 200)
/*    */   protected String sysClass;
/*    */   
/*    */   public abstract void setData(IExteData paramIExteData);
/*    */   
/*    */   public abstract IExteData getData();
/*    */   
/*    */   public String mapKey() {
/* 23 */     return this.sysClass;
/*    */   }
/*    */   
/*    */   public abstract void setSysClass(String paramString);
/*    */   
/*    */   public abstract String getSysClass();
/*    */   
/*    */   public abstract void setPlayerId(long paramLong);
/*    */   
/*    */   public abstract long getPlayerId();
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\dao\entity\IExteMapEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */