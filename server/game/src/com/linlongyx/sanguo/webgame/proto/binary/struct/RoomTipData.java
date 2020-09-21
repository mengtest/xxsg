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
/*    */ public class RoomTipData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long roomId;
/*    */   public String roomName;
/*    */   public String roomHead;
/*    */   public int size;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 21 */     ProtocolUtil.writeLong(out, this.roomId);
/* 22 */     ProtocolUtil.writeUTFBinary(out, this.roomName);
/* 23 */     ProtocolUtil.writeUTFBinary(out, this.roomHead);
/* 24 */     ProtocolUtil.writeInt(out, this.size);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 29 */     this.roomId = ProtocolUtil.readUTFBinLong(in);
/* 30 */     this.roomName = ProtocolUtil.readUTFStr(in);
/* 31 */     this.roomHead = ProtocolUtil.readUTFStr(in);
/* 32 */     this.size = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RoomTipData clone() throws CloneNotSupportedException {
/* 37 */     RoomTipData clone = (RoomTipData)super.clone();
/* 38 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 43 */     this.roomId = 0L;
/* 44 */     this.roomName = null;
/* 45 */     this.roomHead = null;
/* 46 */     this.size = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "{\"roomId\":" + this.roomId + ",\"roomName\":" + StringUtil.str2Str(this.roomName) + ",\"roomHead\":" + StringUtil.str2Str(this.roomHead) + ",\"size\":" + this.size + "}";
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\RoomTipData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */