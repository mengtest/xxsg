/*    */ package com.linlongyx.sanguo.webgame.proto.binary.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.utils.StringUtil;
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
/*    */ public class DestinyBetNumInfoRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public String pkId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeUTFBinary(out, this.pkId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     this.pkId = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyBetNumInfoRequest clone() throws CloneNotSupportedException {
/* 39 */     DestinyBetNumInfoRequest clone = (DestinyBetNumInfoRequest)super.clone();
/* 40 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 45 */     this.pkId = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     String retVal = "{\"pkId\":" + StringUtil.str2Str(this.pkId) + "}";
/* 51 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\destiny\DestinyBetNumInfoRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */