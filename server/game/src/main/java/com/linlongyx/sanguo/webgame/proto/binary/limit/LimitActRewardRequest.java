/*    */ package com.linlongyx.sanguo.webgame.proto.binary.limit;
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
/*    */ public class LimitActRewardRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int actId;
/*    */   public int itemId;
/*    */   public int num;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeInt(out, this.actId);
/* 31 */     ProtocolUtil.writeInt(out, this.itemId);
/* 32 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LimitActRewardRequest clone() throws CloneNotSupportedException {
/* 44 */     LimitActRewardRequest clone = (LimitActRewardRequest)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.actId = 0;
/* 51 */     this.itemId = 0;
/* 52 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"actId\":" + this.actId + ",\"itemId\":" + this.itemId + ",\"num\":" + this.num + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\limit\LimitActRewardRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */