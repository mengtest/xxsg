/*    */ package com.linlongyx.sanguo.webgame.proto.binary.stage;
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
/*    */ public class StageBattleResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int stage;
/*    */   public int state;
/*    */   
/*    */   public StageBattleResponse() {
/* 22 */     this.eventResponseId = 25610;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public StageBattleResponse(short retCode) {
/* 27 */     this.eventResponseId = 25610;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.stage);
/* 35 */     ProtocolUtil.writeInt(out, this.state);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.stage = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.state = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public StageBattleResponse clone() throws CloneNotSupportedException {
/* 46 */     StageBattleResponse clone = (StageBattleResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.stage = 0;
/* 53 */     this.state = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"stage\":" + this.stage + ",\"state\":" + this.state + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\stage\StageBattleResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */