/*    */ package com.linlongyx.sanguo.logclient.coder;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.util.MsgpackUtil;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MsgpackEventEncoder
/*    */   implements EventEncoder
/*    */ {
/*    */   public <T> byte[] write(T object) throws IOException {
/* 17 */     return MsgpackUtil.pack.write(object);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\logclient\coder\MsgpackEventEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */