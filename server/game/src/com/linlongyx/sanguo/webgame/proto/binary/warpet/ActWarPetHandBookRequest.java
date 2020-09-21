/*    */ package com.linlongyx.sanguo.webgame.proto.binary.warpet;
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
/*    */ public class ActWarPetHandBookRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int handType;
/*    */   public int type;
/*    */   public int handbookId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeInt(out, this.handType);
/* 31 */     ProtocolUtil.writeInt(out, this.type);
/* 32 */     ProtocolUtil.writeInt(out, this.handbookId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.handType = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.handbookId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ActWarPetHandBookRequest clone() throws CloneNotSupportedException {
/* 44 */     ActWarPetHandBookRequest clone = (ActWarPetHandBookRequest)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.handType = 0;
/* 51 */     this.type = 0;
/* 52 */     this.handbookId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"handType\":" + this.handType + ",\"type\":" + this.type + ",\"handbookId\":" + this.handbookId + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\warpet\ActWarPetHandBookRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */