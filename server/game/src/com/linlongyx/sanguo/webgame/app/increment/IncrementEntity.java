/*    */ package com.linlongyx.sanguo.webgame.app.increment;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import java.util.concurrent.atomic.AtomicLong;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(prefix = "increment_key", tableName = "tb_increment", keyField = "id")
/*    */ public class IncrementEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient String id;
/*    */   private AtomicLong increment;
/*    */   
/*    */   public IncrementEntity(String id) {
/* 20 */     this.id = id;
/*    */   }
/*    */   
/*    */   public void setInitVal(long initVal) {
/* 24 */     this.increment = new AtomicLong(initVal);
/*    */   }
/*    */   
/*    */   public String getId() {
/* 28 */     return this.id;
/*    */   }
/*    */   
/*    */   public AtomicLong getIncrement() {
/* 32 */     return this.increment;
/*    */   }
/*    */   
/*    */   public void setIncrement(AtomicLong increment) {
/* 36 */     this.increment = increment;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long incr() {
/* 44 */     return this.increment.incrementAndGet();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     return "IncrementEntity{id='" + this.id + '\'' + ", increment=" + this.increment + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 57 */     return this.id;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\increment\IncrementEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */