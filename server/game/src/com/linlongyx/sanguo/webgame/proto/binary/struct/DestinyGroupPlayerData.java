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
/*    */ public class DestinyGroupPlayerData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 19 */     ProtocolUtil.writeLong(out, this.playerId);
/* 20 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 25 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 26 */     this.playerName = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyGroupPlayerData clone() throws CloneNotSupportedException {
/* 31 */     DestinyGroupPlayerData clone = (DestinyGroupPlayerData)super.clone();
/* 32 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 37 */     this.playerId = 0L;
/* 38 */     this.playerName = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 43 */     String retVal = "{\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + "}";
/* 44 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\DestinyGroupPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */