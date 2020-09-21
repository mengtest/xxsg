/*    */ package com.linlongyx.sanguo.webgame.proto.binary.recruit;
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
/*    */ public class RedRecruitBoxRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int type;
/*    */   public int boxId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.type);
/* 30 */     ProtocolUtil.writeInt(out, this.boxId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.boxId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RedRecruitBoxRequest clone() throws CloneNotSupportedException {
/* 41 */     RedRecruitBoxRequest clone = (RedRecruitBoxRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.type = 0;
/* 48 */     this.boxId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"type\":" + this.type + ",\"boxId\":" + this.boxId + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\recruit\RedRecruitBoxRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */