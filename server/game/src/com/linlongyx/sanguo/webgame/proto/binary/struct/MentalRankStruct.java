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
/*    */ public class MentalRankStruct
/*    */   implements Serializable
/*    */ {
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public int point;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 19 */     ProtocolUtil.writeLong(out, this.playerId);
/* 20 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 21 */     ProtocolUtil.writeInt(out, this.point);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 26 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 27 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 28 */     this.point = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MentalRankStruct clone() throws CloneNotSupportedException {
/* 33 */     MentalRankStruct clone = (MentalRankStruct)super.clone();
/* 34 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 39 */     this.playerId = 0L;
/* 40 */     this.playerName = null;
/* 41 */     this.point = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     String retVal = "{\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"point\":" + this.point + "}";
/* 47 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\MentalRankStruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */