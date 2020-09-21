/*    */ package com.linlongyx.sanguo.webgame.proto.binary.partner;
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
/*    */ public class PartnerReincarnRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long pid;
/*    */   public int reincarnId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeLong(out, this.pid);
/* 30 */     ProtocolUtil.writeInt(out, this.reincarnId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/* 36 */     this.reincarnId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public PartnerReincarnRequest clone() throws CloneNotSupportedException {
/* 41 */     PartnerReincarnRequest clone = (PartnerReincarnRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.pid = 0L;
/* 48 */     this.reincarnId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"pid\":" + this.pid + ",\"reincarnId\":" + this.reincarnId + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\partner\PartnerReincarnRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */