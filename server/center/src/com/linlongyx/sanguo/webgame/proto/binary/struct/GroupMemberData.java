/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupMemberData
/*    */ {
/*    */   public long playerId;
/*    */   public int vip;
/*    */   public int level;
/*    */   public String playerName;
/*    */   public String head;
/*    */   public int lastTime;
/*    */   public int position;
/*    */   public long fightValue;
/*    */   public long donate;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 23 */     ProtocolUtil.writeLong(out, this.playerId);
/* 24 */     ProtocolUtil.writeInt(out, this.vip);
/* 25 */     ProtocolUtil.writeInt(out, this.level);
/* 26 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 27 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 28 */     ProtocolUtil.writeInt(out, this.lastTime);
/* 29 */     ProtocolUtil.writeInt(out, this.position);
/* 30 */     ProtocolUtil.writeLong(out, this.fightValue);
/* 31 */     ProtocolUtil.writeLong(out, this.donate);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 36 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 37 */     this.vip = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 40 */     this.head = ProtocolUtil.readUTFStr(in);
/* 41 */     this.lastTime = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.position = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 44 */     this.donate = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupMemberData clone() throws CloneNotSupportedException {
/* 49 */     GroupMemberData clone = (GroupMemberData)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.playerId = 0L;
/* 56 */     this.vip = 0;
/* 57 */     this.level = 0;
/* 58 */     this.playerName = null;
/* 59 */     this.head = null;
/* 60 */     this.lastTime = 0;
/* 61 */     this.position = 0;
/* 62 */     this.fightValue = 0L;
/* 63 */     this.donate = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 68 */     String retVal = "{\"playerId\":" + this.playerId + ",\"vip\":" + this.vip + ",\"level\":" + this.level + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"lastTime\":" + this.lastTime + ",\"position\":" + this.position + ",\"fightValue\":" + this.fightValue + ",\"donate\":" + this.donate + "}";
/* 69 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\GroupMemberData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */