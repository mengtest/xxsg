/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BossData
/*    */ {
/*    */   public int insId;
/*    */   public long hp;
/*    */   public long hpMax;
/*    */   public int remainTime;
/*    */   public int nextTime;
/*    */   public int size;
/*    */   public boolean isOpen;
/* 20 */   public ArrayList<LongIntValue> rank = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 24 */     ProtocolUtil.writeInt(out, this.insId);
/* 25 */     ProtocolUtil.writeLong(out, this.hp);
/* 26 */     ProtocolUtil.writeLong(out, this.hpMax);
/* 27 */     ProtocolUtil.writeInt(out, this.remainTime);
/* 28 */     ProtocolUtil.writeInt(out, this.nextTime);
/* 29 */     ProtocolUtil.writeInt(out, this.size);
/* 30 */     out.writeBoolean(this.isOpen);
/*    */     
/* 32 */     int size_0 = this.rank.size();
/* 33 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       LongIntValue tmp_0 = this.rank.get(index_0);
/* 37 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 43 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.hp = ProtocolUtil.readUTFBinLong(in);
/* 45 */     this.hpMax = ProtocolUtil.readUTFBinLong(in);
/* 46 */     this.remainTime = ProtocolUtil.readUTFBinInt(in);
/* 47 */     this.nextTime = ProtocolUtil.readUTFBinInt(in);
/* 48 */     this.size = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.isOpen = in.readBoolean();
/*    */     
/* 51 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       LongIntValue tmp_0 = new LongIntValue();
/* 55 */       tmp_0.unserial(in);
/* 56 */       this.rank.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public BossData clone() throws CloneNotSupportedException {
/* 62 */     BossData clone = (BossData)super.clone();
/* 63 */     int size_0 = this.rank.size();
/* 64 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 66 */       LongIntValue tmp_0 = this.rank.get(index_0);
/* 67 */       clone.rank.add(tmp_0.clone());
/*    */     } 
/* 69 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 74 */     this.insId = 0;
/* 75 */     this.hp = 0L;
/* 76 */     this.hpMax = 0L;
/* 77 */     this.remainTime = 0;
/* 78 */     this.nextTime = 0;
/* 79 */     this.size = 0;
/* 80 */     this.isOpen = false;
/* 81 */     this.rank.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 86 */     String retVal = "{\"insId\":" + this.insId + ",\"hp\":" + this.hp + ",\"hpMax\":" + this.hpMax + ",\"remainTime\":" + this.remainTime + ",\"nextTime\":" + this.nextTime + ",\"size\":" + this.size + ",\"isOpen\":" + this.isOpen + ",\"rank\":" + this.rank.toString() + "}";
/* 87 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\BossData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */