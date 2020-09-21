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
/*    */ public class CampMemberInfo
/*    */   implements Serializable
/*    */ {
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public int level;
/*    */   public int fightValue;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeLong(out, this.playerId);
/* 21 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 22 */     ProtocolUtil.writeInt(out, this.level);
/* 23 */     ProtocolUtil.writeInt(out, this.fightValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 28 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 29 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 30 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 31 */     this.fightValue = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CampMemberInfo clone() throws CloneNotSupportedException {
/* 36 */     CampMemberInfo clone = (CampMemberInfo)super.clone();
/* 37 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 42 */     this.playerId = 0L;
/* 43 */     this.playerName = null;
/* 44 */     this.level = 0;
/* 45 */     this.fightValue = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     String retVal = "{\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"level\":" + this.level + ",\"fightValue\":" + this.fightValue + "}";
/* 51 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\CampMemberInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */