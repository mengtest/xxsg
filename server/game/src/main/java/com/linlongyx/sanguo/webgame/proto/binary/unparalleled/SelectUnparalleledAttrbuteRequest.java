/*    */ package com.linlongyx.sanguo.webgame.proto.binary.unparalleled;
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
/*    */ public class SelectUnparalleledAttrbuteRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int insId;
/*    */   public int id;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.insId);
/* 30 */     ProtocolUtil.writeInt(out, this.id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.id = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SelectUnparalleledAttrbuteRequest clone() throws CloneNotSupportedException {
/* 41 */     SelectUnparalleledAttrbuteRequest clone = (SelectUnparalleledAttrbuteRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.insId = 0;
/* 48 */     this.id = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"insId\":" + this.insId + ",\"id\":" + this.id + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binar\\unparalleled\SelectUnparalleledAttrbuteRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */