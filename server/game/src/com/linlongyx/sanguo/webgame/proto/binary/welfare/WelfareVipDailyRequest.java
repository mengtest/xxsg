/*    */ package com.linlongyx.sanguo.webgame.proto.binary.welfare;
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
/*    */ public class WelfareVipDailyRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int vipLevel;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 28 */     ProtocolUtil.writeInt(out, this.vipLevel);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 33 */     this.vipLevel = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WelfareVipDailyRequest clone() throws CloneNotSupportedException {
/* 38 */     WelfareVipDailyRequest clone = (WelfareVipDailyRequest)super.clone();
/* 39 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 44 */     this.vipLevel = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     String retVal = "{\"vipLevel\":" + this.vipLevel + "}";
/* 50 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\welfare\WelfareVipDailyRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */