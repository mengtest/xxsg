/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DonateLogData
/*    */ {
/*    */   public String playerName;
/*    */   public int time;
/*    */   public int event;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 17 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 18 */     ProtocolUtil.writeInt(out, this.time);
/* 19 */     ProtocolUtil.writeInt(out, this.event);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 24 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 25 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 26 */     this.event = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DonateLogData clone() throws CloneNotSupportedException {
/* 31 */     DonateLogData clone = (DonateLogData)super.clone();
/* 32 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 37 */     this.playerName = null;
/* 38 */     this.time = 0;
/* 39 */     this.event = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     String retVal = "{\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"time\":" + this.time + ",\"event\":" + this.event + "}";
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\DonateLogData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */