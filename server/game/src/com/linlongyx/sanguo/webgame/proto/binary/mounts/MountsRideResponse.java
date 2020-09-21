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
/*    */ public class MountsRideResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int mounts;
/*    */   public int state;
/*    */   
/*    */   public MountsRideResponse() {
/* 22 */     this.eventResponseId = 29003;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public MountsRideResponse(short retCode) {
/* 27 */     this.eventResponseId = 29003;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.mounts);
/* 35 */     ProtocolUtil.writeInt(out, this.state);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.mounts = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.state = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MountsRideResponse clone() throws CloneNotSupportedException {
/* 46 */     MountsRideResponse clone = (MountsRideResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.mounts = 0;
/* 53 */     this.state = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"mounts\":" + this.mounts + ",\"state\":" + this.state + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\mounts\MountsRideResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */