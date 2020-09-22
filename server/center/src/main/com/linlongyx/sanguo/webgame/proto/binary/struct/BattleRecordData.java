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
/*    */ public class BattleRecordData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int type;
/*    */   public int time;
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public int robNum;
/*    */   public boolean win;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 23 */     ProtocolUtil.writeInt(out, this.type);
/* 24 */     ProtocolUtil.writeInt(out, this.time);
/* 25 */     ProtocolUtil.writeLong(out, this.playerId);
/* 26 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 27 */     ProtocolUtil.writeInt(out, this.robNum);
/* 28 */     out.writeBoolean(this.win);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 33 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 34 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 35 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 36 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 37 */     this.robNum = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.win = in.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public BattleRecordData clone() throws CloneNotSupportedException {
/* 43 */     BattleRecordData clone = (BattleRecordData)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.type = 0;
/* 50 */     this.time = 0;
/* 51 */     this.playerId = 0L;
/* 52 */     this.playerName = null;
/* 53 */     this.robNum = 0;
/* 54 */     this.win = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 59 */     String retVal = "{\"type\":" + this.type + ",\"time\":" + this.time + ",\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"robNum\":" + this.robNum + ",\"win\":" + this.win + "}";
/* 60 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\BattleRecordData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */