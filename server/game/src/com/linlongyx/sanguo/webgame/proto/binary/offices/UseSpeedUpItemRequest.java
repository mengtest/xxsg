/*    */ package com.linlongyx.sanguo.webgame.proto.binary.offices;
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
/*    */ public class UseSpeedUpItemRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int type;
/*    */   public int itemId;
/*    */   public int itemNum;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeInt(out, this.type);
/* 31 */     ProtocolUtil.writeInt(out, this.itemId);
/* 32 */     ProtocolUtil.writeInt(out, this.itemNum);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.itemNum = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public UseSpeedUpItemRequest clone() throws CloneNotSupportedException {
/* 44 */     UseSpeedUpItemRequest clone = (UseSpeedUpItemRequest)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.type = 0;
/* 51 */     this.itemId = 0;
/* 52 */     this.itemNum = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"type\":" + this.type + ",\"itemId\":" + this.itemId + ",\"itemNum\":" + this.itemNum + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\offices\UseSpeedUpItemRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */