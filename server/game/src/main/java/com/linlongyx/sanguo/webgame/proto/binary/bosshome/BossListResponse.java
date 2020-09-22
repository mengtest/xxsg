/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BossData;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class BossListResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public int fightTimes;
/* 22 */   public ArrayList<BossData> datas = new ArrayList<>();
/*    */   public int time;
/*    */   public int buyTime;
/*    */   
/*    */   public BossListResponse() {
/* 27 */     this.eventResponseId = 20301;
/* 28 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public BossListResponse(short retCode) {
/* 32 */     this.eventResponseId = 20301;
/* 33 */     this.codeType = 2;
/* 34 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 39 */     ProtocolUtil.writeInt(out, this.type);
/* 40 */     ProtocolUtil.writeInt(out, this.fightTimes);
/*    */     
/* 42 */     int size_0 = this.datas.size();
/* 43 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       BossData tmp_0 = this.datas.get(index_0);
/* 47 */       tmp_0.serial(out);
/*    */     } 
/* 49 */     ProtocolUtil.writeInt(out, this.time);
/* 50 */     ProtocolUtil.writeInt(out, this.buyTime);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 55 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 56 */     this.fightTimes = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 58 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 59 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 61 */       BossData tmp_0 = new BossData();
/* 62 */       tmp_0.unserial(in);
/* 63 */       this.datas.add(tmp_0);
/*    */     } 
/* 65 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 66 */     this.buyTime = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BossListResponse clone() throws CloneNotSupportedException {
/* 71 */     BossListResponse clone = (BossListResponse)super.clone();
/* 72 */     int size_0 = this.datas.size();
/* 73 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 75 */       BossData tmp_0 = this.datas.get(index_0);
/* 76 */       clone.datas.add(tmp_0.clone());
/*    */     } 
/* 78 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 83 */     this.type = 0;
/* 84 */     this.fightTimes = 0;
/* 85 */     this.datas.clear();
/* 86 */     this.time = 0;
/* 87 */     this.buyTime = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 92 */     String retVal = "{\"type\":" + this.type + ",\"fightTimes\":" + this.fightTimes + ",\"datas\":" + this.datas.toString() + ",\"time\":" + this.time + ",\"buyTime\":" + this.buyTime + "}";
/* 93 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bosshome\BossListResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */