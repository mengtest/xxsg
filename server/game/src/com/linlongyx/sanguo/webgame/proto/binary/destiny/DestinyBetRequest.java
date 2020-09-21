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
/*    */ public class DestinyBetRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public String pkId;
/*    */   public long playerId;
/*    */   public int num;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     ProtocolUtil.writeUTFBinary(out, this.pkId);
/* 32 */     ProtocolUtil.writeLong(out, this.playerId);
/* 33 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.pkId = ProtocolUtil.readUTFStr(in);
/* 39 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 40 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyBetRequest clone() throws CloneNotSupportedException {
/* 45 */     DestinyBetRequest clone = (DestinyBetRequest)super.clone();
/* 46 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 51 */     this.pkId = null;
/* 52 */     this.playerId = 0L;
/* 53 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"pkId\":" + StringUtil.str2Str(this.pkId) + ",\"playerId\":" + this.playerId + ",\"num\":" + this.num + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\destiny\DestinyBetRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */