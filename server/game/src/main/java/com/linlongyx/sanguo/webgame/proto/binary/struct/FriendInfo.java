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
/*    */ public class FriendInfo
/*    */ {
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public String head;
/*    */   public int level;
/*    */   public int logoutTime;
/*    */   public long fightVale;
/*    */   public String blocName;
/*    */   public int vip;
/*    */   public int sendState;
/*    */   public int receiveState;
/*    */   public long favorValue;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 26 */     ProtocolUtil.writeLong(out, this.playerId);
/* 27 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 28 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 29 */     ProtocolUtil.writeInt(out, this.level);
/* 30 */     ProtocolUtil.writeInt(out, this.logoutTime);
/* 31 */     ProtocolUtil.writeLong(out, this.fightVale);
/* 32 */     ProtocolUtil.writeUTFBinary(out, this.blocName);
/* 33 */     ProtocolUtil.writeInt(out, this.vip);
/* 34 */     ProtocolUtil.writeInt(out, this.sendState);
/* 35 */     ProtocolUtil.writeInt(out, this.receiveState);
/* 36 */     ProtocolUtil.writeLong(out, this.favorValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 42 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 43 */     this.head = ProtocolUtil.readUTFStr(in);
/* 44 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 45 */     this.logoutTime = ProtocolUtil.readUTFBinInt(in);
/* 46 */     this.fightVale = ProtocolUtil.readUTFBinLong(in);
/* 47 */     this.blocName = ProtocolUtil.readUTFStr(in);
/* 48 */     this.vip = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.sendState = ProtocolUtil.readUTFBinInt(in);
/* 50 */     this.receiveState = ProtocolUtil.readUTFBinInt(in);
/* 51 */     this.favorValue = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FriendInfo clone() throws CloneNotSupportedException {
/* 56 */     FriendInfo clone = (FriendInfo)super.clone();
/* 57 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 62 */     this.playerId = 0L;
/* 63 */     this.playerName = null;
/* 64 */     this.head = null;
/* 65 */     this.level = 0;
/* 66 */     this.logoutTime = 0;
/* 67 */     this.fightVale = 0L;
/* 68 */     this.blocName = null;
/* 69 */     this.vip = 0;
/* 70 */     this.sendState = 0;
/* 71 */     this.receiveState = 0;
/* 72 */     this.favorValue = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 77 */     String retVal = "{\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"level\":" + this.level + ",\"logoutTime\":" + this.logoutTime + ",\"fightVale\":" + this.fightVale + ",\"blocName\":" + StringUtil.str2Str(this.blocName) + ",\"vip\":" + this.vip + ",\"sendState\":" + this.sendState + ",\"receiveState\":" + this.receiveState + ",\"favorValue\":" + this.favorValue + "}";
/* 78 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\FriendInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */