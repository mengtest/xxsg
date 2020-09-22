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
/*    */ public class DartInfo
/*    */   implements Serializable
/*    */ {
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public long fightValue;
/*    */   public int level;
/*    */   public int dartType;
/*    */   public int robbedNum;
/*    */   public int endTime;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 23 */     ProtocolUtil.writeLong(out, this.playerId);
/* 24 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 25 */     ProtocolUtil.writeLong(out, this.fightValue);
/* 26 */     ProtocolUtil.writeInt(out, this.level);
/* 27 */     ProtocolUtil.writeInt(out, this.dartType);
/* 28 */     ProtocolUtil.writeInt(out, this.robbedNum);
/* 29 */     ProtocolUtil.writeInt(out, this.endTime);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 35 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 36 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 37 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.dartType = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.robbedNum = ProtocolUtil.readUTFBinInt(in);
/* 40 */     this.endTime = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DartInfo clone() throws CloneNotSupportedException {
/* 45 */     DartInfo clone = (DartInfo)super.clone();
/* 46 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 51 */     this.playerId = 0L;
/* 52 */     this.playerName = null;
/* 53 */     this.fightValue = 0L;
/* 54 */     this.level = 0;
/* 55 */     this.dartType = 0;
/* 56 */     this.robbedNum = 0;
/* 57 */     this.endTime = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"fightValue\":" + this.fightValue + ",\"level\":" + this.level + ",\"dartType\":" + this.dartType + ",\"robbedNum\":" + this.robbedNum + ",\"endTime\":" + this.endTime + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\DartInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */