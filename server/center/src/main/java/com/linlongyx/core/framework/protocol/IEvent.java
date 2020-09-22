/*    */ package com.linlongyx.core.framework.protocol;public interface IEvent {
/*    */   public static final short E_ID_S2P_TRANSMIT = 1;
/*    */   
/*    */   EVENT_TYPE getType();
/*    */   
/*    */   void setType(EVENT_TYPE paramEVENT_TYPE);
/*    */   
/*    */   public enum EVENT_TYPE {
/*  9 */     EVENT_C2S,
/* 10 */     EVENT_S2C,
/* 11 */     EVENT_S2S,
/* 12 */     EVENT_INTER;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\protocol\IEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */