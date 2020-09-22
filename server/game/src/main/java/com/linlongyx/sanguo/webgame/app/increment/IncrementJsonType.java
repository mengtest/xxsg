/*    */ package com.linlongyx.sanguo.webgame.app.increment;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.type.JsonType;
/*    */ import java.util.concurrent.atomic.AtomicLong;
/*    */ 
/*    */ 
/*    */ public class IncrementJsonType
/*    */   extends JsonType
/*    */ {
/* 10 */   private AtomicLong increment = new AtomicLong(0L);
/*    */   
/*    */   public long incr() {
/* 13 */     return this.increment.incrementAndGet();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\increment\IncrementJsonType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */