/*    */ package com.linlongyx.sanguo.webgame.proto.binary.fight;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
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
/*    */ @Message
/*    */ public class KillSingleMonsterResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public KillSingleMonsterResponse() {
/* 20 */     this.eventResponseId = 20205;
/* 21 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public KillSingleMonsterResponse(short retCode) {
/* 25 */     this.eventResponseId = 20205;
/* 26 */     this.codeType = 2;
/* 27 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {}
/*    */ 
/*    */   
/*    */   public KillSingleMonsterResponse clone() throws CloneNotSupportedException {
/* 40 */     KillSingleMonsterResponse clone = (KillSingleMonsterResponse)super.clone();
/* 41 */     return clone;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void reset() {}
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     String retVal = "{}";
/* 51 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\fight\KillSingleMonsterResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */