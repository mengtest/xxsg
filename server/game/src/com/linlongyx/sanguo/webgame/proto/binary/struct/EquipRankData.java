/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EquipRankData
/*    */ {
/*    */   public int rank;
/*    */   public String name;
/*    */   public int point;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 17 */     ProtocolUtil.writeInt(out, this.rank);
/* 18 */     ProtocolUtil.writeUTFBinary(out, this.name);
/* 19 */     ProtocolUtil.writeInt(out, this.point);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 24 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 25 */     this.name = ProtocolUtil.readUTFStr(in);
/* 26 */     this.point = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public EquipRankData clone() throws CloneNotSupportedException {
/* 31 */     EquipRankData clone = (EquipRankData)super.clone();
/* 32 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 37 */     this.rank = 0;
/* 38 */     this.name = null;
/* 39 */     this.point = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     String retVal = "{\"rank\":" + this.rank + ",\"name\":" + StringUtil.str2Str(this.name) + ",\"point\":" + this.point + "}";
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\EquipRankData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */