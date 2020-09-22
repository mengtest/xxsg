/*    */ package com.linlongyx.sanguo.webgame.proto.binary.luckymoney;
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
/*    */ public class LuckyMoneyAddResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long item_num;
/*    */   public int type;
/*    */   
/*    */   public LuckyMoneyAddResponse() {
/* 22 */     this.eventResponseId = 24307;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public LuckyMoneyAddResponse(short retCode) {
/* 27 */     this.eventResponseId = 24307;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeLong(out, this.item_num);
/* 35 */     ProtocolUtil.writeInt(out, this.type);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.item_num = ProtocolUtil.readUTFBinLong(in);
/* 41 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LuckyMoneyAddResponse clone() throws CloneNotSupportedException {
/* 46 */     LuckyMoneyAddResponse clone = (LuckyMoneyAddResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.item_num = 0L;
/* 53 */     this.type = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"item_num\":" + this.item_num + ",\"type\":" + this.type + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\luckymoney\LuckyMoneyAddResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */