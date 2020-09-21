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
/* 24 */     ProtocolUtil.writeLong(out, this.playerId);
/* 25 */     ProtocolUtil.writeInt(out, this.vip);
/* 26 */     ProtocolUtil.writeInt(out, this.level);
/* 27 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 28 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 29 */     ProtocolUtil.writeInt(out, this.lastTime);
/* 30 */     ProtocolUtil.writeInt(out, this.position);
/* 31 */     ProtocolUtil.writeLong(out, this.fightValue);
/* 32 */     ProtocolUtil.writeLong(out, this.donate);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 38 */     this.vip = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 40 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 41 */     this.head = ProtocolUtil.readUTFStr(in);
/* 42 */     this.lastTime = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.position = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 45 */     this.donate = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupMemberData clone() throws CloneNotSupportedException {
/* 50 */     GroupMemberData clone = (GroupMemberData)super.clone();
/* 51 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 56 */     this.playerId = 0L;
/* 57 */     this.vip = 0;
/* 58 */     this.level = 0;
/* 59 */     this.playerName = null;
/* 60 */     this.head = null;
/* 61 */     this.lastTime = 0;
/* 62 */     this.position = 0;
/* 63 */     this.fightValue = 0L;
/* 64 */     this.donate = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 69 */     String retVal = "{\"playerId\":" + this.playerId + ",\"vip\":" + this.vip + ",\"level\":" + this.level + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"lastTime\":" + this.lastTime + ",\"position\":" + this.position + ",\"fightValue\":" + this.fightValue + ",\"donate\":" + this.donate + "}";
/* 70 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\GroupMemberData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */