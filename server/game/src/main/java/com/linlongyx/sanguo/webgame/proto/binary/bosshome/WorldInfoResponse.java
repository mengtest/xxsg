/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class WorldInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int fightTimes;
/*    */   public int buyTime;
/*    */   public int time;
/*    */   public int insId;
/*    */   public long curHp;
/*    */   public long maxHp;
/*    */   public long hurt;
/*    */   public int rank;
/*    */   public int num;
/*    */   public int nextTime;
/*    */   public int status;
/*    */   
/*    */   public WorldInfoResponse() {
/* 31 */     this.eventResponseId = 20304;
/* 32 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WorldInfoResponse(short retCode) {
/* 36 */     this.eventResponseId = 20304;
/* 37 */     this.codeType = 2;
/* 38 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 43 */     ProtocolUtil.writeInt(out, this.fightTimes);
/* 44 */     ProtocolUtil.writeInt(out, this.buyTime);
/* 45 */     ProtocolUtil.writeInt(out, this.time);
/* 46 */     ProtocolUtil.writeInt(out, this.insId);
/* 47 */     ProtocolUtil.writeLong(out, this.curHp);
/* 48 */     ProtocolUtil.writeLong(out, this.maxHp);
/* 49 */     ProtocolUtil.writeLong(out, this.hurt);
/* 50 */     ProtocolUtil.writeInt(out, this.rank);
/* 51 */     ProtocolUtil.writeInt(out, this.num);
/* 52 */     ProtocolUtil.writeInt(out, this.nextTime);
/* 53 */     ProtocolUtil.writeInt(out, this.status);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 58 */     this.fightTimes = ProtocolUtil.readUTFBinInt(in);
/* 59 */     this.buyTime = ProtocolUtil.readUTFBinInt(in);
/* 60 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 61 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/* 62 */     this.curHp = ProtocolUtil.readUTFBinLong(in);
/* 63 */     this.maxHp = ProtocolUtil.readUTFBinLong(in);
/* 64 */     this.hurt = ProtocolUtil.readUTFBinLong(in);
/* 65 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 66 */     this.num = ProtocolUtil.readUTFBinInt(in);
/* 67 */     this.nextTime = ProtocolUtil.readUTFBinInt(in);
/* 68 */     this.status = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldInfoResponse clone() throws CloneNotSupportedException {
/* 73 */     WorldInfoResponse clone = (WorldInfoResponse)super.clone();
/* 74 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 79 */     this.fightTimes = 0;
/* 80 */     this.buyTime = 0;
/* 81 */     this.time = 0;
/* 82 */     this.insId = 0;
/* 83 */     this.curHp = 0L;
/* 84 */     this.maxHp = 0L;
/* 85 */     this.hurt = 0L;
/* 86 */     this.rank = 0;
/* 87 */     this.num = 0;
/* 88 */     this.nextTime = 0;
/* 89 */     this.status = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 94 */     String retVal = "{\"fightTimes\":" + this.fightTimes + ",\"buyTime\":" + this.buyTime + ",\"time\":" + this.time + ",\"insId\":" + this.insId + ",\"curHp\":" + this.curHp + ",\"maxHp\":" + this.maxHp + ",\"hurt\":" + this.hurt + ",\"rank\":" + this.rank + ",\"num\":" + this.num + ",\"nextTime\":" + this.nextTime + ",\"status\":" + this.status + "}";
/* 95 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bosshome\WorldInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */