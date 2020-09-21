/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DamageData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long toId;
/*    */   public int objectId;
/*    */   public byte type;
/*    */   public byte status;
/*    */   public long hp;
/*    */   public int recoverhp;
/*    */   public int injuredhp;
/* 22 */   public ArrayList<BuffData> buffs = new ArrayList<>();
/*    */   
/*    */   public byte isRevive;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 27 */     ProtocolUtil.writeLong(out, this.toId);
/* 28 */     ProtocolUtil.writeInt(out, this.objectId);
/* 29 */     ProtocolUtil.writeByte(out, this.type);
/* 30 */     ProtocolUtil.writeByte(out, this.status);
/* 31 */     ProtocolUtil.writeLong(out, this.hp);
/* 32 */     ProtocolUtil.writeInt(out, this.recoverhp);
/* 33 */     ProtocolUtil.writeInt(out, this.injuredhp);
/*    */     
/* 35 */     int size_0 = this.buffs.size();
/* 36 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 37 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 39 */       BuffData tmp_0 = this.buffs.get(index_0);
/* 40 */       tmp_0.serial(out);
/*    */     } 
/* 42 */     ProtocolUtil.writeByte(out, this.isRevive);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 47 */     this.toId = ProtocolUtil.readUTFBinLong(in);
/* 48 */     this.objectId = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 50 */     this.status = ProtocolUtil.readUTFBinByte(in);
/* 51 */     this.hp = ProtocolUtil.readUTFBinLong(in);
/* 52 */     this.recoverhp = ProtocolUtil.readUTFBinInt(in);
/* 53 */     this.injuredhp = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 55 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 56 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 58 */       BuffData tmp_0 = new BuffData();
/* 59 */       tmp_0.unserial(in);
/* 60 */       this.buffs.add(tmp_0);
/*    */     } 
/* 62 */     this.isRevive = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DamageData clone() throws CloneNotSupportedException {
/* 67 */     DamageData clone = (DamageData)super.clone();
/* 68 */     int size_0 = this.buffs.size();
/* 69 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 71 */       BuffData tmp_0 = this.buffs.get(index_0);
/* 72 */       clone.buffs.add(tmp_0.clone());
/*    */     } 
/* 74 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 79 */     this.toId = 0L;
/* 80 */     this.objectId = 0;
/* 81 */     this.type = 0;
/* 82 */     this.status = 0;
/* 83 */     this.hp = 0L;
/* 84 */     this.recoverhp = 0;
/* 85 */     this.injuredhp = 0;
/* 86 */     this.buffs.clear();
/* 87 */     this.isRevive = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 92 */     String retVal = "{\"toId\":" + this.toId + ",\"objectId\":" + this.objectId + ",\"type\":" + this.type + ",\"status\":" + this.status + ",\"hp\":" + this.hp + ",\"recoverhp\":" + this.recoverhp + ",\"injuredhp\":" + this.injuredhp + ",\"buffs\":" + this.buffs.toString() + ",\"isRevive\":" + this.isRevive + "}";
/* 93 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\DamageData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */