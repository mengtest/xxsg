/*    */ package com.linlongyx.sanguo.webgame.proto.binary.arena;
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
/*    */ public class ArenaSweepRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int rank;
/*    */   public int num;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.rank);
/* 30 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ArenaSweepRequest clone() throws CloneNotSupportedException {
/* 41 */     ArenaSweepRequest clone = (ArenaSweepRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.rank = 0;
/* 48 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"rank\":" + this.rank + ",\"num\":" + this.num + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\arena\ArenaSweepRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */