/*    */ package com.linlongyx.sanguo.webgame.proto.binary.welfare;
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
/*    */ @Message
/*    */ public class WelfareVipDailyResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int vipLevel;
/*    */   
/*    */   public WelfareVipDailyResponse() {
/* 21 */     this.eventResponseId = 21902;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WelfareVipDailyResponse(short retCode) {
/* 26 */     this.eventResponseId = 21902;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.vipLevel);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.vipLevel = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WelfareVipDailyResponse clone() throws CloneNotSupportedException {
/* 43 */     WelfareVipDailyResponse clone = (WelfareVipDailyResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.vipLevel = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"vipLevel\":" + this.vipLevel + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\welfare\WelfareVipDailyResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */