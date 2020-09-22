/*    */ package com.linlongyx.sanguo.webgame.proto.binary.draw;
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
/*    */ public class DrawCardPlayRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int drawId;
/*    */   public int type;
/*    */   public int pos;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeInt(out, this.drawId);
/* 31 */     ProtocolUtil.writeInt(out, this.type);
/* 32 */     ProtocolUtil.writeInt(out, this.pos);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.drawId = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.pos = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DrawCardPlayRequest clone() throws CloneNotSupportedException {
/* 44 */     DrawCardPlayRequest clone = (DrawCardPlayRequest)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.drawId = 0;
/* 51 */     this.type = 0;
/* 52 */     this.pos = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"drawId\":" + this.drawId + ",\"type\":" + this.type + ",\"pos\":" + this.pos + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\draw\DrawCardPlayRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */