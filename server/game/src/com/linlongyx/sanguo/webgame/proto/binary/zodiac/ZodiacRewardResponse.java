/*    */ package com.linlongyx.sanguo.webgame.proto.binary.zodiac;
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
/*    */ public class ZodiacRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public int actId;
/*    */   public int boxId;
/*    */   public int num;
/*    */   
/*    */   public ZodiacRewardResponse() {
/* 24 */     this.eventResponseId = 27503;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ZodiacRewardResponse(short retCode) {
/* 29 */     this.eventResponseId = 27503;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeInt(out, this.type);
/* 37 */     ProtocolUtil.writeInt(out, this.actId);
/* 38 */     ProtocolUtil.writeInt(out, this.boxId);
/* 39 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 44 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 45 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/* 46 */     this.boxId = ProtocolUtil.readUTFBinInt(in);
/* 47 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ZodiacRewardResponse clone() throws CloneNotSupportedException {
/* 52 */     ZodiacRewardResponse clone = (ZodiacRewardResponse)super.clone();
/* 53 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 58 */     this.type = 0;
/* 59 */     this.actId = 0;
/* 60 */     this.boxId = 0;
/* 61 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     String retVal = "{\"type\":" + this.type + ",\"actId\":" + this.actId + ",\"boxId\":" + this.boxId + ",\"num\":" + this.num + "}";
/* 67 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\zodiac\ZodiacRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */