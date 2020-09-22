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
/*    */ public class GroupData
/*    */ {
/*    */   public long id;
/*    */   public String groupName;
/*    */   public long leader;
/*    */   public String leaderName;
/*    */   public int level;
/*    */   public int size;
/*    */   public int rank;
/*    */   public String notice;
/*    */   public long exp;
/*    */   public long dayExp;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 25 */     ProtocolUtil.writeLong(out, this.id);
/* 26 */     ProtocolUtil.writeUTFBinary(out, this.groupName);
/* 27 */     ProtocolUtil.writeLong(out, this.leader);
/* 28 */     ProtocolUtil.writeUTFBinary(out, this.leaderName);
/* 29 */     ProtocolUtil.writeInt(out, this.level);
/* 30 */     ProtocolUtil.writeInt(out, this.size);
/* 31 */     ProtocolUtil.writeInt(out, this.rank);
/* 32 */     ProtocolUtil.writeUTFBinary(out, this.notice);
/* 33 */     ProtocolUtil.writeLong(out, this.exp);
/* 34 */     ProtocolUtil.writeLong(out, this.dayExp);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.id = ProtocolUtil.readUTFBinLong(in);
/* 40 */     this.groupName = ProtocolUtil.readUTFStr(in);
/* 41 */     this.leader = ProtocolUtil.readUTFBinLong(in);
/* 42 */     this.leaderName = ProtocolUtil.readUTFStr(in);
/* 43 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.size = ProtocolUtil.readUTFBinInt(in);
/* 45 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 46 */     this.notice = ProtocolUtil.readUTFStr(in);
/* 47 */     this.exp = ProtocolUtil.readUTFBinLong(in);
/* 48 */     this.dayExp = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupData clone() throws CloneNotSupportedException {
/* 53 */     GroupData clone = (GroupData)super.clone();
/* 54 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 59 */     this.id = 0L;
/* 60 */     this.groupName = null;
/* 61 */     this.leader = 0L;
/* 62 */     this.leaderName = null;
/* 63 */     this.level = 0;
/* 64 */     this.size = 0;
/* 65 */     this.rank = 0;
/* 66 */     this.notice = null;
/* 67 */     this.exp = 0L;
/* 68 */     this.dayExp = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 73 */     String retVal = "{\"id\":" + this.id + ",\"groupName\":" + StringUtil.str2Str(this.groupName) + ",\"leader\":" + this.leader + ",\"leaderName\":" + StringUtil.str2Str(this.leaderName) + ",\"level\":" + this.level + ",\"size\":" + this.size + ",\"rank\":" + this.rank + ",\"notice\":" + StringUtil.str2Str(this.notice) + ",\"exp\":" + this.exp + ",\"dayExp\":" + this.dayExp + "}";
/* 74 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\GroupData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */