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
/*    */ public class TeamData
/*    */ {
/*    */   public long teamId;
/*    */   public String head;
/*    */   public String leaderName;
/*    */   public int size;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 19 */     ProtocolUtil.writeLong(out, this.teamId);
/* 20 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 21 */     ProtocolUtil.writeUTFBinary(out, this.leaderName);
/* 22 */     ProtocolUtil.writeInt(out, this.size);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 27 */     this.teamId = ProtocolUtil.readUTFBinLong(in);
/* 28 */     this.head = ProtocolUtil.readUTFStr(in);
/* 29 */     this.leaderName = ProtocolUtil.readUTFStr(in);
/* 30 */     this.size = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TeamData clone() throws CloneNotSupportedException {
/* 35 */     TeamData clone = (TeamData)super.clone();
/* 36 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 41 */     this.teamId = 0L;
/* 42 */     this.head = null;
/* 43 */     this.leaderName = null;
/* 44 */     this.size = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     String retVal = "{\"teamId\":" + this.teamId + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"leaderName\":" + StringUtil.str2Str(this.leaderName) + ",\"size\":" + this.size + "}";
/* 50 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\TeamData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */