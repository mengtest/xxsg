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
/*    */ public class ArenaData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long playerId;
/*    */   public int objectId;
/*    */   public String playerName;
/*    */   public int rank;
/*    */   public long fightValue;
/*    */   public int sex;
/*    */   public boolean isRobot;
/*    */   public int title;
/*    */   public int fashionId;
/*    */   public int mounts;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 27 */     ProtocolUtil.writeLong(out, this.playerId);
/* 28 */     ProtocolUtil.writeInt(out, this.objectId);
/* 29 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 30 */     ProtocolUtil.writeInt(out, this.rank);
/* 31 */     ProtocolUtil.writeLong(out, this.fightValue);
/* 32 */     ProtocolUtil.writeInt(out, this.sex);
/* 33 */     out.writeBoolean(this.isRobot);
/* 34 */     ProtocolUtil.writeInt(out, this.title);
/* 35 */     ProtocolUtil.writeInt(out, this.fashionId);
/* 36 */     ProtocolUtil.writeInt(out, this.mounts);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 42 */     this.objectId = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 44 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 45 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 46 */     this.sex = ProtocolUtil.readUTFBinInt(in);
/* 47 */     this.isRobot = in.readBoolean();
/* 48 */     this.title = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.fashionId = ProtocolUtil.readUTFBinInt(in);
/* 50 */     this.mounts = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ArenaData clone() throws CloneNotSupportedException {
/* 55 */     ArenaData clone = (ArenaData)super.clone();
/* 56 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 61 */     this.playerId = 0L;
/* 62 */     this.objectId = 0;
/* 63 */     this.playerName = null;
/* 64 */     this.rank = 0;
/* 65 */     this.fightValue = 0L;
/* 66 */     this.sex = 0;
/* 67 */     this.isRobot = false;
/* 68 */     this.title = 0;
/* 69 */     this.fashionId = 0;
/* 70 */     this.mounts = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     String retVal = "{\"playerId\":" + this.playerId + ",\"objectId\":" + this.objectId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"rank\":" + this.rank + ",\"fightValue\":" + this.fightValue + ",\"sex\":" + this.sex + ",\"isRobot\":" + this.isRobot + ",\"title\":" + this.title + ",\"fashionId\":" + this.fashionId + ",\"mounts\":" + this.mounts + "}";
/* 76 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\ArenaData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */