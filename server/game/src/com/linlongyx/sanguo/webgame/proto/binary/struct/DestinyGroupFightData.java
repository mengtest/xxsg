/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyGroupFightData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long leftPlayerId;
/*    */   public long rightPlayerId;
/*    */   public long winner;
/*    */   public String recordId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 21 */     ProtocolUtil.writeLong(out, this.leftPlayerId);
/* 22 */     ProtocolUtil.writeLong(out, this.rightPlayerId);
/* 23 */     ProtocolUtil.writeLong(out, this.winner);
/* 24 */     ProtocolUtil.writeUTFBinary(out, this.recordId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 29 */     this.leftPlayerId = ProtocolUtil.readUTFBinLong(in);
/* 30 */     this.rightPlayerId = ProtocolUtil.readUTFBinLong(in);
/* 31 */     this.winner = ProtocolUtil.readUTFBinLong(in);
/* 32 */     this.recordId = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyGroupFightData clone() throws CloneNotSupportedException {
/* 37 */     DestinyGroupFightData clone = (DestinyGroupFightData)super.clone();
/* 38 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 43 */     this.leftPlayerId = 0L;
/* 44 */     this.rightPlayerId = 0L;
/* 45 */     this.winner = 0L;
/* 46 */     this.recordId = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "{\"leftPlayerId\":" + this.leftPlayerId + ",\"rightPlayerId\":" + this.rightPlayerId + ",\"winner\":" + this.winner + ",\"recordId\":" + StringUtil.str2Str(this.recordId) + "}";
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\DestinyGroupFightData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */