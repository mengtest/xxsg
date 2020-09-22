/*    */ package com.linlongyx.sanguo.webgame.proto.binary.common;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
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
/*    */ @Message
/*    */ public class TabNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int sys;
/*    */   public int index;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 28 */     ProtocolUtil.writeInt(out, this.sys);
/* 29 */     ProtocolUtil.writeInt(out, this.index);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     this.sys = ProtocolUtil.readUTFBinInt(in);
/* 35 */     this.index = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TabNoticeResponse clone() throws CloneNotSupportedException {
/* 40 */     TabNoticeResponse clone = (TabNoticeResponse)super.clone();
/* 41 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 46 */     this.sys = 0;
/* 47 */     this.index = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 52 */     String retVal = "{\"sys\":" + this.sys + ",\"index\":" + this.index + "}";
/* 53 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\common\TabNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */