/*    */ package com.linlongyx.sanguo.webgame.proto.binary.rune;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
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
/*    */ 
/*    */ @Message
/*    */ public class RuneSysLevelUpRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long mid;
/*    */   public int num;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeLong(out, this.mid);
/* 30 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.mid = ProtocolUtil.readUTFBinLong(in);
/* 36 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RuneSysLevelUpRequest clone() throws CloneNotSupportedException {
/* 41 */     RuneSysLevelUpRequest clone = (RuneSysLevelUpRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.mid = 0L;
/* 48 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"mid\":" + this.mid + ",\"num\":" + this.num + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rune\RuneSysLevelUpRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */