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
/*    */ public class PlayerSimpleData
/*    */   implements Serializable
/*    */ {
/*    */   public long playerId;
/*    */   public long fightValue;
/*    */   public String head;
/*    */   public String name;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeLong(out, this.playerId);
/* 21 */     ProtocolUtil.writeLong(out, this.fightValue);
/* 22 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 23 */     ProtocolUtil.writeUTFBinary(out, this.name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 28 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 29 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 30 */     this.head = ProtocolUtil.readUTFStr(in);
/* 31 */     this.name = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerSimpleData clone() throws CloneNotSupportedException {
/* 36 */     PlayerSimpleData clone = (PlayerSimpleData)super.clone();
/* 37 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 42 */     this.playerId = 0L;
/* 43 */     this.fightValue = 0L;
/* 44 */     this.head = null;
/* 45 */     this.name = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     String retVal = "{\"playerId\":" + this.playerId + ",\"fightValue\":" + this.fightValue + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"name\":" + StringUtil.str2Str(this.name) + "}";
/* 51 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\PlayerSimpleData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */