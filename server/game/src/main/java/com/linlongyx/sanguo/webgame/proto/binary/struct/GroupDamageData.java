/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupDamageData
/*    */ {
/*    */   public long playerId;
/*    */   public String head;
/*    */   public String playerName;
/*    */   public int fightCount;
/*    */   public long maxDamage;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeLong(out, this.playerId);
/* 21 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 22 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 23 */     ProtocolUtil.writeInt(out, this.fightCount);
/* 24 */     ProtocolUtil.writeLong(out, this.maxDamage);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 29 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 30 */     this.head = ProtocolUtil.readUTFStr(in);
/* 31 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 32 */     this.fightCount = ProtocolUtil.readUTFBinInt(in);
/* 33 */     this.maxDamage = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupDamageData clone() throws CloneNotSupportedException {
/* 38 */     GroupDamageData clone = (GroupDamageData)super.clone();
/* 39 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 44 */     this.playerId = 0L;
/* 45 */     this.head = null;
/* 46 */     this.playerName = null;
/* 47 */     this.fightCount = 0;
/* 48 */     this.maxDamage = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"playerId\":" + this.playerId + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"fightCount\":" + this.fightCount + ",\"maxDamage\":" + this.maxDamage + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\GroupDamageData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */