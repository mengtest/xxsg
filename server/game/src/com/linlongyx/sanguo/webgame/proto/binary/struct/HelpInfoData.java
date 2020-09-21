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
/*    */ public class HelpInfoData
/*    */ {
/*    */   public String head;
/*    */   public String playerName;
/*    */   public long playerId;
/*    */   public int playerLevel;
/*    */   public int officeId;
/*    */   public int times;
/*    */   public int quality;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 22 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 23 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 24 */     ProtocolUtil.writeLong(out, this.playerId);
/* 25 */     ProtocolUtil.writeInt(out, this.playerLevel);
/* 26 */     ProtocolUtil.writeInt(out, this.officeId);
/* 27 */     ProtocolUtil.writeInt(out, this.times);
/* 28 */     ProtocolUtil.writeInt(out, this.quality);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 33 */     this.head = ProtocolUtil.readUTFStr(in);
/* 34 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 35 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 36 */     this.playerLevel = ProtocolUtil.readUTFBinInt(in);
/* 37 */     this.officeId = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.times = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public HelpInfoData clone() throws CloneNotSupportedException {
/* 44 */     HelpInfoData clone = (HelpInfoData)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.head = null;
/* 51 */     this.playerName = null;
/* 52 */     this.playerId = 0L;
/* 53 */     this.playerLevel = 0;
/* 54 */     this.officeId = 0;
/* 55 */     this.times = 0;
/* 56 */     this.quality = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "{\"head\":" + StringUtil.str2Str(this.head) + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"playerId\":" + this.playerId + ",\"playerLevel\":" + this.playerLevel + ",\"officeId\":" + this.officeId + ",\"times\":" + this.times + ",\"quality\":" + this.quality + "}";
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\HelpInfoData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */