/*    */ package com.linlongyx.sanguo.webgame.proto.binary.kungfu;
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
/*    */ public class KungFuBattleResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int kungfu;
/*    */   public int state;
/*    */   
/*    */   public KungFuBattleResponse() {
/* 22 */     this.eventResponseId = 25603;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public KungFuBattleResponse(short retCode) {
/* 27 */     this.eventResponseId = 25603;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.kungfu);
/* 35 */     ProtocolUtil.writeInt(out, this.state);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.kungfu = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.state = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public KungFuBattleResponse clone() throws CloneNotSupportedException {
/* 46 */     KungFuBattleResponse clone = (KungFuBattleResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.kungfu = 0;
/* 53 */     this.state = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"kungfu\":" + this.kungfu + ",\"state\":" + this.state + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\kungfu\KungFuBattleResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */