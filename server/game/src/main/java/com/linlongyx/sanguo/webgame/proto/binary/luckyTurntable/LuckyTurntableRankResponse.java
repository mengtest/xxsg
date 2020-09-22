/*    */ package com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
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
/*    */ public class LuckyTurntableRankResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<KeyValue> rankList = new ArrayList<>();
/*    */   public int point;
/*    */   
/*    */   public LuckyTurntableRankResponse() {
/* 24 */     this.eventResponseId = 23507;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     int size_0 = this.rankList.size();
/* 32 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 33 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 35 */       KeyValue tmp_0 = this.rankList.get(index_0);
/* 36 */       tmp_0.serial(out);
/*    */     } 
/* 38 */     ProtocolUtil.writeInt(out, this.point);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 44 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 45 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 47 */       KeyValue tmp_0 = new KeyValue();
/* 48 */       tmp_0.unserial(in);
/* 49 */       this.rankList.add(tmp_0);
/*    */     } 
/* 51 */     this.point = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LuckyTurntableRankResponse clone() throws CloneNotSupportedException {
/* 56 */     LuckyTurntableRankResponse clone = (LuckyTurntableRankResponse)super.clone();
/* 57 */     int size_0 = this.rankList.size();
/* 58 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 60 */       KeyValue tmp_0 = this.rankList.get(index_0);
/* 61 */       clone.rankList.add(tmp_0.clone());
/*    */     } 
/* 63 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 68 */     this.rankList.clear();
/* 69 */     this.point = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     String retVal = "{\"rankList\":" + this.rankList.toString() + ",\"point\":" + this.point + "}";
/* 75 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\luckyTurntable\LuckyTurntableRankResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */