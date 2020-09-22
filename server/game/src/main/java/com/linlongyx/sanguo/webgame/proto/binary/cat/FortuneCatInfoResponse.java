/*    */ package com.linlongyx.sanguo.webgame.proto.binary.cat;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
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
/*    */ public class FortuneCatInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public FestivalTime festivalTime = new FestivalTime();
/*    */   public int leftCount;
/*    */   public int cost;
/*    */   public int maxValue;
/*    */   public int minValue;
/*    */   public long actCharge;
/*    */   
/*    */   public FortuneCatInfoResponse() {
/* 27 */     this.eventResponseId = 28002;
/* 28 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public FortuneCatInfoResponse(short retCode) {
/* 32 */     this.eventResponseId = 28002;
/* 33 */     this.codeType = 2;
/* 34 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 39 */     this.festivalTime.serial(out);
/* 40 */     ProtocolUtil.writeInt(out, this.leftCount);
/* 41 */     ProtocolUtil.writeInt(out, this.cost);
/* 42 */     ProtocolUtil.writeInt(out, this.maxValue);
/* 43 */     ProtocolUtil.writeInt(out, this.minValue);
/* 44 */     ProtocolUtil.writeLong(out, this.actCharge);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 49 */     this.festivalTime = new FestivalTime();
/* 50 */     this.festivalTime.unserial(in);
/* 51 */     this.leftCount = ProtocolUtil.readUTFBinInt(in);
/* 52 */     this.cost = ProtocolUtil.readUTFBinInt(in);
/* 53 */     this.maxValue = ProtocolUtil.readUTFBinInt(in);
/* 54 */     this.minValue = ProtocolUtil.readUTFBinInt(in);
/* 55 */     this.actCharge = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FortuneCatInfoResponse clone() throws CloneNotSupportedException {
/* 60 */     FortuneCatInfoResponse clone = (FortuneCatInfoResponse)super.clone();
/* 61 */     clone.festivalTime = this.festivalTime.clone();
/* 62 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 67 */     this.festivalTime.reset();
/* 68 */     this.leftCount = 0;
/* 69 */     this.cost = 0;
/* 70 */     this.maxValue = 0;
/* 71 */     this.minValue = 0;
/* 72 */     this.actCharge = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 77 */     String retVal = "{\"festivalTime\":" + this.festivalTime.toString() + ",\"leftCount\":" + this.leftCount + ",\"cost\":" + this.cost + ",\"maxValue\":" + this.maxValue + ",\"minValue\":" + this.minValue + ",\"actCharge\":" + this.actCharge + "}";
/* 78 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cat\FortuneCatInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */