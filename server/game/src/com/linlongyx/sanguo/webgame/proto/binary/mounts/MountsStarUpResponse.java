/*    */ package com.linlongyx.sanguo.webgame.proto.binary.mounts;
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
/*    */ public class MountsStarUpResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int mounts;
/*    */   public int star;
/*    */   
/*    */   public MountsStarUpResponse() {
/* 22 */     this.eventResponseId = 29005;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public MountsStarUpResponse(short retCode) {
/* 27 */     this.eventResponseId = 29005;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.mounts);
/* 35 */     ProtocolUtil.writeInt(out, this.star);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.mounts = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.star = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MountsStarUpResponse clone() throws CloneNotSupportedException {
/* 46 */     MountsStarUpResponse clone = (MountsStarUpResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.mounts = 0;
/* 53 */     this.star = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"mounts\":" + this.mounts + ",\"star\":" + this.star + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\mounts\MountsStarUpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */