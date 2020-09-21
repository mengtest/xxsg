/*    */ package com.linlongyx.core.framework.base;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class Module
/*    */   implements IModule
/*    */ {
/*    */   private int type;
/* 11 */   private final Map<Integer, IProcessor> processorMap = new HashMap<>();
/*    */   private final Entrance entrance;
/*    */   
/*    */   public Module(int type, Entrance entrance) {
/* 15 */     this.type = type;
/* 16 */     this.entrance = entrance;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IProcessor getProcessor(int msgId) {
/* 22 */     return this.processorMap.get(Integer.valueOf(msgId));
/*    */   }
/*    */ 
/*    */   
/*    */   public void addProcessor(int msgId, IProcessor processor) {
/* 27 */     this.processorMap.put(Integer.valueOf(msgId), processor);
/*    */   }
/*    */   
/*    */   public boolean isOpen() {
/* 31 */     return this.entrance.isOpenFlag();
/*    */   }
/*    */   
/*    */   public void setOpen(boolean open) {
/* 35 */     this.entrance.setOpenFlag(open);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\base\Module.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */