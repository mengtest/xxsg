/*    */ package com.linlongyx.sanguo.webgame.proto.binary.rebate;
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
/*    */ public class ChargeRebateTurnRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int actId;
/*    */   public int times;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.actId);
/* 30 */     ProtocolUtil.writeInt(out, this.times);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.times = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChargeRebateTurnRequest clone() throws CloneNotSupportedException {
/* 41 */     ChargeRebateTurnRequest clone = (ChargeRebateTurnRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.actId = 0;
/* 48 */     this.times = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"actId\":" + this.actId + ",\"times\":" + this.times + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rebate\ChargeRebateTurnRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */