/*    */ package linlongyx.core.framework.service.impl;
/*    */ 
/*    */ import linlongyx.core.framework.service.IUniqueIDGeneratorService;
/*    */ import java.util.concurrent.atomic.AtomicLong;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SessionIDGeneratorService
/*    */   implements IUniqueIDGeneratorService
/*    */ {
/* 12 */   public static final AtomicLong ID = new AtomicLong(0L);
/*    */ 
/*    */   
/*    */   public Object generate() {
/* 16 */     return Long.valueOf(ID.incrementAndGet());
/*    */   }
/*    */ 
/*    */   
/*    */   public Object generateFor(Class klass) {
/* 21 */     return klass.getSimpleName() + ID.incrementAndGet();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\service\impl\SessionIDGeneratorService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */