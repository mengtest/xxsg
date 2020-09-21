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
/*    */ public class ArenaReportData
/*    */ {
/*    */   public int time;
/*    */   public String fightName;
/*    */   public int isPlayer;
/*    */   public int win;
/*    */   public int dt;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeInt(out, this.time);
/* 21 */     ProtocolUtil.writeUTFBinary(out, this.fightName);
/* 22 */     ProtocolUtil.writeInt(out, this.isPlayer);
/* 23 */     ProtocolUtil.writeInt(out, this.win);
/* 24 */     ProtocolUtil.writeInt(out, this.dt);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 29 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 30 */     this.fightName = ProtocolUtil.readUTFStr(in);
/* 31 */     this.isPlayer = ProtocolUtil.readUTFBinInt(in);
/* 32 */     this.win = ProtocolUtil.readUTFBinInt(in);
/* 33 */     this.dt = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ArenaReportData clone() throws CloneNotSupportedException {
/* 38 */     ArenaReportData clone = (ArenaReportData)super.clone();
/* 39 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 44 */     this.time = 0;
/* 45 */     this.fightName = null;
/* 46 */     this.isPlayer = 0;
/* 47 */     this.win = 0;
/* 48 */     this.dt = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"time\":" + this.time + ",\"fightName\":" + StringUtil.str2Str(this.fightName) + ",\"isPlayer\":" + this.isPlayer + ",\"win\":" + this.win + ",\"dt\":" + this.dt + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\ArenaReportData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */