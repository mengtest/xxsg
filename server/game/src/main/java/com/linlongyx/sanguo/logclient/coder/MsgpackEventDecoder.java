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
/*    */ public class MsgpackEventDecoder
/*    */   implements EventDecoder
/*    */ {
/*    */   public <T> T read(byte[] bytes, Class<T> c) throws IOException {
/* 16 */     return (T)MsgpackUtil.pack.read(bytes, c);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\logclient\coder\MsgpackEventDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */