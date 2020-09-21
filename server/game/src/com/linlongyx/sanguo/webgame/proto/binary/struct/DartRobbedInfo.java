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
/*    */ public class DartRobbedInfo
/*    */   implements Serializable
/*    */ {
/*    */   public int isWin;
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public int time;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeInt(out, this.isWin);
/* 21 */     ProtocolUtil.writeLong(out, this.playerId);
/* 22 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 23 */     ProtocolUtil.writeInt(out, this.time);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 28 */     this.isWin = ProtocolUtil.readUTFBinInt(in);
/* 29 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 30 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 31 */     this.time = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DartRobbedInfo clone() throws CloneNotSupportedException {
/* 36 */     DartRobbedInfo clone = (DartRobbedInfo)super.clone();
/* 37 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 42 */     this.isWin = 0;
/* 43 */     this.playerId = 0L;
/* 44 */     this.playerName = null;
/* 45 */     this.time = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     String retVal = "{\"isWin\":" + this.isWin + ",\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"time\":" + this.time + "}";
/* 51 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\DartRobbedInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */