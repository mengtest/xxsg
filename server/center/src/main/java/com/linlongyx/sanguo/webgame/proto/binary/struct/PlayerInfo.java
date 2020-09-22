/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerInfo
/*    */ {
/*    */   public long playerId;
/*    */   public byte sex;
/*    */   public String name;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 17 */     ProtocolUtil.writeLong(out, this.playerId);
/* 18 */     ProtocolUtil.writeByte(out, this.sex);
/* 19 */     ProtocolUtil.writeUTFBinary(out, this.name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 24 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 25 */     this.sex = ProtocolUtil.readUTFBinByte(in);
/* 26 */     this.name = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerInfo clone() throws CloneNotSupportedException {
/* 31 */     PlayerInfo clone = (PlayerInfo)super.clone();
/* 32 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 37 */     this.playerId = 0L;
/* 38 */     this.sex = 0;
/* 39 */     this.name = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     String retVal = "{\"playerId\":" + this.playerId + ",\"sex\":" + this.sex + ",\"name\":" + StringUtil.str2Str(this.name) + "}";
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\PlayerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */