/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AttackData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long fromId;
/* 16 */   public ArrayList<Long> toIds = new ArrayList<>();
/*    */   
/*    */   public int skillId;
/*    */   public short x;
/*    */   public short y;
/*    */   public String playerName;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 24 */     ProtocolUtil.writeLong(out, this.fromId);
/*    */     
/* 26 */     int size_0 = this.toIds.size();
/* 27 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 28 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 30 */       long tmp_0 = ((Long)this.toIds.get(index_0)).longValue();
/* 31 */       ProtocolUtil.writeLong(out, tmp_0);
/*    */     } 
/* 33 */     ProtocolUtil.writeInt(out, this.skillId);
/* 34 */     ProtocolUtil.writeShort(out, this.x);
/* 35 */     ProtocolUtil.writeShort(out, this.y);
/* 36 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.fromId = ProtocolUtil.readUTFBinLong(in);
/*    */     
/* 43 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       long tmp_0 = ProtocolUtil.readUTFBinLong(in);
/* 47 */       this.toIds.add(Long.valueOf(tmp_0));
/*    */     } 
/* 49 */     this.skillId = ProtocolUtil.readUTFBinInt(in);
/* 50 */     this.x = ProtocolUtil.readUTFBinShort(in);
/* 51 */     this.y = ProtocolUtil.readUTFBinShort(in);
/* 52 */     this.playerName = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public AttackData clone() throws CloneNotSupportedException {
/* 57 */     AttackData clone = (AttackData)super.clone();
/* 58 */     int size_0 = this.toIds.size();
/* 59 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 61 */       long tmp_0 = ((Long)this.toIds.get(index_0)).longValue();
/* 62 */       clone.toIds.add(Long.valueOf(tmp_0));
/*    */     } 
/* 64 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 69 */     this.fromId = 0L;
/* 70 */     this.toIds.clear();
/* 71 */     this.skillId = 0;
/* 72 */     this.x = 0;
/* 73 */     this.y = 0;
/* 74 */     this.playerName = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 79 */     String retVal = "{\"fromId\":" + this.fromId + ",\"toIds\":" + this.toIds.toString() + ",\"skillId\":" + this.skillId + ",\"x\":" + this.x + ",\"y\":" + this.y + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + "}";
/* 80 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\AttackData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */