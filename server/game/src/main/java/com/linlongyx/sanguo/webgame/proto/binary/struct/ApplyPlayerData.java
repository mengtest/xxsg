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
/*    */ public class ApplyPlayerData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public String head;
/*    */   public int level;
/*    */   public int vip;
/*    */   public long fight;
/*    */   public int lastTime;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 24 */     ProtocolUtil.writeLong(out, this.playerId);
/* 25 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 26 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 27 */     ProtocolUtil.writeInt(out, this.level);
/* 28 */     ProtocolUtil.writeInt(out, this.vip);
/* 29 */     ProtocolUtil.writeLong(out, this.fight);
/* 30 */     ProtocolUtil.writeInt(out, this.lastTime);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 36 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 37 */     this.head = ProtocolUtil.readUTFStr(in);
/* 38 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.vip = ProtocolUtil.readUTFBinInt(in);
/* 40 */     this.fight = ProtocolUtil.readUTFBinLong(in);
/* 41 */     this.lastTime = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ApplyPlayerData clone() throws CloneNotSupportedException {
/* 46 */     ApplyPlayerData clone = (ApplyPlayerData)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.playerId = 0L;
/* 53 */     this.playerName = null;
/* 54 */     this.head = null;
/* 55 */     this.level = 0;
/* 56 */     this.vip = 0;
/* 57 */     this.fight = 0L;
/* 58 */     this.lastTime = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     String retVal = "{\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"level\":" + this.level + ",\"vip\":" + this.vip + ",\"fight\":" + this.fight + ",\"lastTime\":" + this.lastTime + "}";
/* 64 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\ApplyPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */