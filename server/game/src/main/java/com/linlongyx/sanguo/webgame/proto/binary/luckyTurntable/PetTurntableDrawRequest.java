/*    */ package com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable;
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
/*    */ public class PetTurntableDrawRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int actId;
/*    */   public int type;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.actId);
/* 30 */     ProtocolUtil.writeInt(out, this.type);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public PetTurntableDrawRequest clone() throws CloneNotSupportedException {
/* 41 */     PetTurntableDrawRequest clone = (PetTurntableDrawRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.actId = 0;
/* 48 */     this.type = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"actId\":" + this.actId + ",\"type\":" + this.type + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\luckyTurntable\PetTurntableDrawRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */