/*    */ package com.linlongyx.core.framework.base.mobile;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.Entrance;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Module
/*    */   implements IModule
/*    */ {
/*    */   private int type;
/* 13 */   private final Map<Integer, IProcessor> processorMap = new HashMap<>();
/*    */   private final Entrance entrance;
/*    */   
/*    */   public Module(int type, Entrance entrance) {
/* 17 */     this.type = type;
/* 18 */     this.entrance = entrance;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IProcessor getProcessor(int msgId) {
/* 24 */     return this.processorMap.get(Integer.valueOf(msgId));
/*    */   }
/*    */ 
/*    */   
/*    */   public void addProcessor(int msgId, IProcessor processor) {
/* 29 */     this.processorMap.put(Integer.valueOf(msgId), processor);
/*    */   }
/*    */   
/*    */   public boolean isOpen() {
/* 33 */     return this.entrance.isOpenFlag();
/*    */   }
/*    */   
/*    */   public void setOpen(boolean open) {
/* 37 */     this.entrance.setOpenFlag(open);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\base\mobile\Module.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */