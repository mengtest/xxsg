/*    */ package com.linlongyx.sanguo.webgame.proto.binary.cat;
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
/*    */ public class FortuneCatPlayResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int id;
/*    */   public int leftCount;
/*    */   public int cost;
/*    */   public int num;
/*    */   public int maxValue;
/*    */   public int minValue;
/*    */   
/*    */   public FortuneCatPlayResponse() {
/* 26 */     this.eventResponseId = 28003;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public FortuneCatPlayResponse(short retCode) {
/* 31 */     this.eventResponseId = 28003;
/* 32 */     this.codeType = 2;
/* 33 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     ProtocolUtil.writeInt(out, this.id);
/* 39 */     ProtocolUtil.writeInt(out, this.leftCount);
/* 40 */     ProtocolUtil.writeInt(out, this.cost);
/* 41 */     ProtocolUtil.writeInt(out, this.num);
/* 42 */     ProtocolUtil.writeInt(out, this.maxValue);
/* 43 */     ProtocolUtil.writeInt(out, this.minValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 48 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.leftCount = ProtocolUtil.readUTFBinInt(in);
/* 50 */     this.cost = ProtocolUtil.readUTFBinInt(in);
/* 51 */     this.num = ProtocolUtil.readUTFBinInt(in);
/* 52 */     this.maxValue = ProtocolUtil.readUTFBinInt(in);
/* 53 */     this.minValue = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FortuneCatPlayResponse clone() throws CloneNotSupportedException {
/* 58 */     FortuneCatPlayResponse clone = (FortuneCatPlayResponse)super.clone();
/* 59 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 64 */     this.id = 0;
/* 65 */     this.leftCount = 0;
/* 66 */     this.cost = 0;
/* 67 */     this.num = 0;
/* 68 */     this.maxValue = 0;
/* 69 */     this.minValue = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     String retVal = "{\"id\":" + this.id + ",\"leftCount\":" + this.leftCount + ",\"cost\":" + this.cost + ",\"num\":" + this.num + ",\"maxValue\":" + this.maxValue + ",\"minValue\":" + this.minValue + "}";
/* 75 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cat\FortuneCatPlayResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */