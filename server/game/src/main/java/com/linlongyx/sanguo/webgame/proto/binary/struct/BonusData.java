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
/*    */ public class BonusData
/*    */   implements Serializable
/*    */ {
/*    */   public int insId;
/*    */   public long bonusId;
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public int num;
/*    */   public int time;
/*    */   public String head;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 23 */     ProtocolUtil.writeInt(out, this.insId);
/* 24 */     ProtocolUtil.writeLong(out, this.bonusId);
/* 25 */     ProtocolUtil.writeLong(out, this.playerId);
/* 26 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 27 */     ProtocolUtil.writeInt(out, this.num);
/* 28 */     ProtocolUtil.writeInt(out, this.time);
/* 29 */     ProtocolUtil.writeUTFBinary(out, this.head);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/* 35 */     this.bonusId = ProtocolUtil.readUTFBinLong(in);
/* 36 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 37 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 38 */     this.num = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 40 */     this.head = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BonusData clone() throws CloneNotSupportedException {
/* 45 */     BonusData clone = (BonusData)super.clone();
/* 46 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 51 */     this.insId = 0;
/* 52 */     this.bonusId = 0L;
/* 53 */     this.playerId = 0L;
/* 54 */     this.playerName = null;
/* 55 */     this.num = 0;
/* 56 */     this.time = 0;
/* 57 */     this.head = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"insId\":" + this.insId + ",\"bonusId\":" + this.bonusId + ",\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"num\":" + this.num + ",\"time\":" + this.time + ",\"head\":" + StringUtil.str2Str(this.head) + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\BonusData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */