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
/*    */ public class SpaPlayerInfo
/*    */   implements Serializable
/*    */ {
/*    */   public int ranking;
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public int popularity;
/*    */   public int gift;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 21 */     ProtocolUtil.writeInt(out, this.ranking);
/* 22 */     ProtocolUtil.writeLong(out, this.playerId);
/* 23 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 24 */     ProtocolUtil.writeInt(out, this.popularity);
/* 25 */     ProtocolUtil.writeInt(out, this.gift);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 30 */     this.ranking = ProtocolUtil.readUTFBinInt(in);
/* 31 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 32 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 33 */     this.popularity = ProtocolUtil.readUTFBinInt(in);
/* 34 */     this.gift = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SpaPlayerInfo clone() throws CloneNotSupportedException {
/* 39 */     SpaPlayerInfo clone = (SpaPlayerInfo)super.clone();
/* 40 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 45 */     this.ranking = 0;
/* 46 */     this.playerId = 0L;
/* 47 */     this.playerName = null;
/* 48 */     this.popularity = 0;
/* 49 */     this.gift = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"ranking\":" + this.ranking + ",\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"popularity\":" + this.popularity + ",\"gift\":" + this.gift + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\SpaPlayerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */