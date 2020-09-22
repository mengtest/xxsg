/*    */ package com.linlongyx.sanguo.webgame.proto.binary.zodiac;
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
/*    */ public class ZodiacRewardRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int type;
/*    */   public int actId;
/*    */   public int boxId;
/*    */   public int rewardType;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     ProtocolUtil.writeInt(out, this.type);
/* 32 */     ProtocolUtil.writeInt(out, this.actId);
/* 33 */     ProtocolUtil.writeInt(out, this.boxId);
/* 34 */     ProtocolUtil.writeInt(out, this.rewardType);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 40 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.boxId = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.rewardType = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ZodiacRewardRequest clone() throws CloneNotSupportedException {
/* 47 */     ZodiacRewardRequest clone = (ZodiacRewardRequest)super.clone();
/* 48 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 53 */     this.type = 0;
/* 54 */     this.actId = 0;
/* 55 */     this.boxId = 0;
/* 56 */     this.rewardType = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "{\"type\":" + this.type + ",\"actId\":" + this.actId + ",\"boxId\":" + this.boxId + ",\"rewardType\":" + this.rewardType + "}";
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\zodiac\ZodiacRewardRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */