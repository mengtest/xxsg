/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupTipData
/*    */ {
/*    */   public long id;
/*    */   public String groupName;
/*    */   public int level;
/*    */   public int size;
/*    */   public int rank;
/*    */   public long exp;
/*    */   public boolean isApplyed;
/*    */   public boolean mayApply;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 22 */     ProtocolUtil.writeLong(out, this.id);
/* 23 */     ProtocolUtil.writeUTFBinary(out, this.groupName);
/* 24 */     ProtocolUtil.writeInt(out, this.level);
/* 25 */     ProtocolUtil.writeInt(out, this.size);
/* 26 */     ProtocolUtil.writeInt(out, this.rank);
/* 27 */     ProtocolUtil.writeLong(out, this.exp);
/* 28 */     out.writeBoolean(this.isApplyed);
/* 29 */     out.writeBoolean(this.mayApply);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     this.id = ProtocolUtil.readUTFBinLong(in);
/* 35 */     this.groupName = ProtocolUtil.readUTFStr(in);
/* 36 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 37 */     this.size = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.exp = ProtocolUtil.readUTFBinLong(in);
/* 40 */     this.isApplyed = in.readBoolean();
/* 41 */     this.mayApply = in.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupTipData clone() throws CloneNotSupportedException {
/* 46 */     GroupTipData clone = (GroupTipData)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.id = 0L;
/* 53 */     this.groupName = null;
/* 54 */     this.level = 0;
/* 55 */     this.size = 0;
/* 56 */     this.rank = 0;
/* 57 */     this.exp = 0L;
/* 58 */     this.isApplyed = false;
/* 59 */     this.mayApply = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 64 */     String retVal = "{\"id\":" + this.id + ",\"groupName\":" + StringUtil.str2Str(this.groupName) + ",\"level\":" + this.level + ",\"size\":" + this.size + ",\"rank\":" + this.rank + ",\"exp\":" + this.exp + ",\"isApplyed\":" + this.isApplyed + ",\"mayApply\":" + this.mayApply + "}";
/* 65 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\GroupTipData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */