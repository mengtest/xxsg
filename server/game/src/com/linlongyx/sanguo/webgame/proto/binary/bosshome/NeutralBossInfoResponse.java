/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.NeutralBossData;
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
/*    */ public class NeutralBossInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int surpRewardCount;
/*    */   public int buyTimes;
/* 22 */   public ArrayList<NeutralBossData> bossList = new ArrayList<>();
/*    */   
/*    */   public NeutralBossInfoResponse() {
/* 25 */     this.eventResponseId = 20315;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public NeutralBossInfoResponse(short retCode) {
/* 30 */     this.eventResponseId = 20315;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeInt(out, this.surpRewardCount);
/* 38 */     ProtocolUtil.writeInt(out, this.buyTimes);
/*    */     
/* 40 */     int size_0 = this.bossList.size();
/* 41 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 44 */       NeutralBossData tmp_0 = this.bossList.get(index_0);
/* 45 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     this.surpRewardCount = ProtocolUtil.readUTFBinInt(in);
/* 52 */     this.buyTimes = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 54 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       NeutralBossData tmp_0 = new NeutralBossData();
/* 58 */       tmp_0.unserial(in);
/* 59 */       this.bossList.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public NeutralBossInfoResponse clone() throws CloneNotSupportedException {
/* 65 */     NeutralBossInfoResponse clone = (NeutralBossInfoResponse)super.clone();
/* 66 */     int size_0 = this.bossList.size();
/* 67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 69 */       NeutralBossData tmp_0 = this.bossList.get(index_0);
/* 70 */       clone.bossList.add(tmp_0.clone());
/*    */     } 
/* 72 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 77 */     this.surpRewardCount = 0;
/* 78 */     this.buyTimes = 0;
/* 79 */     this.bossList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     String retVal = "{\"surpRewardCount\":" + this.surpRewardCount + ",\"buyTimes\":" + this.buyTimes + ",\"bossList\":" + this.bossList.toString() + "}";
/* 85 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bosshome\NeutralBossInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */