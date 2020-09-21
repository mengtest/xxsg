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
/*    */ public class DivineRankData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long playerId;
/*    */   public int rank;
/*    */   public int time;
/*    */   public String name;
/*    */   public String divineNum;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 22 */     ProtocolUtil.writeLong(out, this.playerId);
/* 23 */     ProtocolUtil.writeInt(out, this.rank);
/* 24 */     ProtocolUtil.writeInt(out, this.time);
/* 25 */     ProtocolUtil.writeUTFBinary(out, this.name);
/* 26 */     ProtocolUtil.writeUTFBinary(out, this.divineNum);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 31 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 32 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 33 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 34 */     this.name = ProtocolUtil.readUTFStr(in);
/* 35 */     this.divineNum = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DivineRankData clone() throws CloneNotSupportedException {
/* 40 */     DivineRankData clone = (DivineRankData)super.clone();
/* 41 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 46 */     this.playerId = 0L;
/* 47 */     this.rank = 0;
/* 48 */     this.time = 0;
/* 49 */     this.name = null;
/* 50 */     this.divineNum = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     String retVal = "{\"playerId\":" + this.playerId + ",\"rank\":" + this.rank + ",\"time\":" + this.time + ",\"name\":" + StringUtil.str2Str(this.name) + ",\"divineNum\":" + StringUtil.str2Str(this.divineNum) + "}";
/* 56 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\DivineRankData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */