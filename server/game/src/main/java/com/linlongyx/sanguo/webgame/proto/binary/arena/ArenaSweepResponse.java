/*    */ package com.linlongyx.sanguo.webgame.proto.binary.arena;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
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
/*    */ public class ArenaSweepResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int rank;
/*    */   public int num;
/* 22 */   public ArrayList<Reward> list = new ArrayList<>();
/*    */   public int fightTimes;
/*    */   
/*    */   public ArenaSweepResponse() {
/* 26 */     this.eventResponseId = 21705;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ArenaSweepResponse(short retCode) {
/* 31 */     this.eventResponseId = 21705;
/* 32 */     this.codeType = 2;
/* 33 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     ProtocolUtil.writeInt(out, this.rank);
/* 39 */     ProtocolUtil.writeInt(out, this.num);
/*    */     
/* 41 */     int size_0 = this.list.size();
/* 42 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 43 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 45 */       Reward tmp_0 = this.list.get(index_0);
/* 46 */       tmp_0.serial(out);
/*    */     } 
/* 48 */     ProtocolUtil.writeInt(out, this.fightTimes);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 53 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 54 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 56 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 57 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 59 */       Reward tmp_0 = new Reward();
/* 60 */       tmp_0.unserial(in);
/* 61 */       this.list.add(tmp_0);
/*    */     } 
/* 63 */     this.fightTimes = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ArenaSweepResponse clone() throws CloneNotSupportedException {
/* 68 */     ArenaSweepResponse clone = (ArenaSweepResponse)super.clone();
/* 69 */     int size_0 = this.list.size();
/* 70 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 72 */       Reward tmp_0 = this.list.get(index_0);
/* 73 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 75 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 80 */     this.rank = 0;
/* 81 */     this.num = 0;
/* 82 */     this.list.clear();
/* 83 */     this.fightTimes = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     String retVal = "{\"rank\":" + this.rank + ",\"num\":" + this.num + ",\"list\":" + this.list.toString() + ",\"fightTimes\":" + this.fightTimes + "}";
/* 89 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\arena\ArenaSweepResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */