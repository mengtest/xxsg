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
/* 23 */     ProtocolUtil.writeLong(out, this.id);
/* 24 */     ProtocolUtil.writeUTFBinary(out, this.groupName);
/* 25 */     ProtocolUtil.writeInt(out, this.level);
/* 26 */     ProtocolUtil.writeInt(out, this.size);
/* 27 */     ProtocolUtil.writeInt(out, this.rank);
/* 28 */     ProtocolUtil.writeLong(out, this.exp);
/* 29 */     out.writeBoolean(this.isApplyed);
/* 30 */     out.writeBoolean(this.mayApply);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.id = ProtocolUtil.readUTFBinLong(in);
/* 36 */     this.groupName = ProtocolUtil.readUTFStr(in);
/* 37 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.size = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 40 */     this.exp = ProtocolUtil.readUTFBinLong(in);
/* 41 */     this.isApplyed = in.readBoolean();
/* 42 */     this.mayApply = in.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupTipData clone() throws CloneNotSupportedException {
/* 47 */     GroupTipData clone = (GroupTipData)super.clone();
/* 48 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 53 */     this.id = 0L;
/* 54 */     this.groupName = null;
/* 55 */     this.level = 0;
/* 56 */     this.size = 0;
/* 57 */     this.rank = 0;
/* 58 */     this.exp = 0L;
/* 59 */     this.isApplyed = false;
/* 60 */     this.mayApply = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 65 */     String retVal = "{\"id\":" + this.id + ",\"groupName\":" + StringUtil.str2Str(this.groupName) + ",\"level\":" + this.level + ",\"size\":" + this.size + ",\"rank\":" + this.rank + ",\"exp\":" + this.exp + ",\"isApplyed\":" + this.isApplyed + ",\"mayApply\":" + this.mayApply + "}";
/* 66 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\GroupTipData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */