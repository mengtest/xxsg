/*    */ package com.linlongyx.sanguo.webgame.proto.binary.login;
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
/*    */ public class OfflineIncomeRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   
/*    */   public OfflineIncomeRewardResponse() {
/* 21 */     this.eventResponseId = 20007;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public OfflineIncomeRewardResponse(short retCode) {
/* 26 */     this.eventResponseId = 20007;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.type);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public OfflineIncomeRewardResponse clone() throws CloneNotSupportedException {
/* 43 */     OfflineIncomeRewardResponse clone = (OfflineIncomeRewardResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.type = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"type\":" + this.type + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\OfflineIncomeRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */