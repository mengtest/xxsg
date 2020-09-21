/*    */ package com.linlongyx.sanguo.webgame.proto.binary.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyRankData;
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
/*    */ public class DestinyBattleRankResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<DestinyRankData> datas = new ArrayList<>();
/*    */   public int myrank;
/*    */   public int destinyPoint;
/*    */   
/*    */   public DestinyBattleRankResponse() {
/* 25 */     this.eventResponseId = 27403;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 32 */     int size_0 = this.datas.size();
/* 33 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       DestinyRankData tmp_0 = this.datas.get(index_0);
/* 37 */       tmp_0.serial(out);
/*    */     } 
/* 39 */     ProtocolUtil.writeInt(out, this.myrank);
/* 40 */     ProtocolUtil.writeInt(out, this.destinyPoint);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 46 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 47 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 49 */       DestinyRankData tmp_0 = new DestinyRankData();
/* 50 */       tmp_0.unserial(in);
/* 51 */       this.datas.add(tmp_0);
/*    */     } 
/* 53 */     this.myrank = ProtocolUtil.readUTFBinInt(in);
/* 54 */     this.destinyPoint = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyBattleRankResponse clone() throws CloneNotSupportedException {
/* 59 */     DestinyBattleRankResponse clone = (DestinyBattleRankResponse)super.clone();
/* 60 */     int size_0 = this.datas.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       DestinyRankData tmp_0 = this.datas.get(index_0);
/* 64 */       clone.datas.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.datas.clear();
/* 72 */     this.myrank = 0;
/* 73 */     this.destinyPoint = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     String retVal = "{\"datas\":" + this.datas.toString() + ",\"myrank\":" + this.myrank + ",\"destinyPoint\":" + this.destinyPoint + "}";
/* 79 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\destiny\DestinyBattleRankResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */