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
/*    */ public class NeutralBossData
/*    */ {
/*    */   public int insId;
/*    */   public long hp;
/*    */   public long hpMax;
/*    */   public int remainTime;
/*    */   public int nextTime;
/*    */   public long plyaerId;
/*    */   public String playerName;
/*    */   public boolean isOpen;
/*    */   public int playerTime;
/*    */   public int sex;
/*    */   public int fashion;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 26 */     ProtocolUtil.writeInt(out, this.insId);
/* 27 */     ProtocolUtil.writeLong(out, this.hp);
/* 28 */     ProtocolUtil.writeLong(out, this.hpMax);
/* 29 */     ProtocolUtil.writeInt(out, this.remainTime);
/* 30 */     ProtocolUtil.writeInt(out, this.nextTime);
/* 31 */     ProtocolUtil.writeLong(out, this.plyaerId);
/* 32 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 33 */     out.writeBoolean(this.isOpen);
/* 34 */     ProtocolUtil.writeInt(out, this.playerTime);
/* 35 */     ProtocolUtil.writeInt(out, this.sex);
/* 36 */     ProtocolUtil.writeInt(out, this.fashion);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.hp = ProtocolUtil.readUTFBinLong(in);
/* 43 */     this.hpMax = ProtocolUtil.readUTFBinLong(in);
/* 44 */     this.remainTime = ProtocolUtil.readUTFBinInt(in);
/* 45 */     this.nextTime = ProtocolUtil.readUTFBinInt(in);
/* 46 */     this.plyaerId = ProtocolUtil.readUTFBinLong(in);
/* 47 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 48 */     this.isOpen = in.readBoolean();
/* 49 */     this.playerTime = ProtocolUtil.readUTFBinInt(in);
/* 50 */     this.sex = ProtocolUtil.readUTFBinInt(in);
/* 51 */     this.fashion = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public NeutralBossData clone() throws CloneNotSupportedException {
/* 56 */     NeutralBossData clone = (NeutralBossData)super.clone();
/* 57 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 62 */     this.insId = 0;
/* 63 */     this.hp = 0L;
/* 64 */     this.hpMax = 0L;
/* 65 */     this.remainTime = 0;
/* 66 */     this.nextTime = 0;
/* 67 */     this.plyaerId = 0L;
/* 68 */     this.playerName = null;
/* 69 */     this.isOpen = false;
/* 70 */     this.playerTime = 0;
/* 71 */     this.sex = 0;
/* 72 */     this.fashion = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 77 */     String retVal = "{\"insId\":" + this.insId + ",\"hp\":" + this.hp + ",\"hpMax\":" + this.hpMax + ",\"remainTime\":" + this.remainTime + ",\"nextTime\":" + this.nextTime + ",\"plyaerId\":" + this.plyaerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"isOpen\":" + this.isOpen + ",\"playerTime\":" + this.playerTime + ",\"sex\":" + this.sex + ",\"fashion\":" + this.fashion + "}";
/* 78 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\NeutralBossData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */