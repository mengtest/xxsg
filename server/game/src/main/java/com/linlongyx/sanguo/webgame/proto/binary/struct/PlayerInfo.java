/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
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
/* 18 */     ProtocolUtil.writeLong(out, this.playerId);
/* 19 */     ProtocolUtil.writeByte(out, this.sex);
/* 20 */     ProtocolUtil.writeUTFBinary(out, this.name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 25 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 26 */     this.sex = ProtocolUtil.readUTFBinByte(in);
/* 27 */     this.name = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerInfo clone() throws CloneNotSupportedException {
/* 32 */     PlayerInfo clone = (PlayerInfo)super.clone();
/* 33 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 38 */     this.playerId = 0L;
/* 39 */     this.sex = 0;
/* 40 */     this.name = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     String retVal = "{\"playerId\":" + this.playerId + ",\"sex\":" + this.sex + ",\"name\":" + StringUtil.str2Str(this.name) + "}";
/* 46 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\PlayerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */