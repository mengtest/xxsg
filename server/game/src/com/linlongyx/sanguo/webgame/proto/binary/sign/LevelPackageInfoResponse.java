/*    */ package com.linlongyx.sanguo.webgame.proto.binary.sign;
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
/*    */ public class LevelPackageInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<KeyValue> levelBuyCount = new ArrayList<>();
/*    */   
/*    */   public LevelPackageInfoResponse() {
/* 23 */     this.eventResponseId = 25206;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public LevelPackageInfoResponse(short retCode) {
/* 28 */     this.eventResponseId = 25206;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.levelBuyCount.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       KeyValue tmp_0 = this.levelBuyCount.get(index_0);
/* 41 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 48 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 49 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 51 */       KeyValue tmp_0 = new KeyValue();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.levelBuyCount.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public LevelPackageInfoResponse clone() throws CloneNotSupportedException {
/* 59 */     LevelPackageInfoResponse clone = (LevelPackageInfoResponse)super.clone();
/* 60 */     int size_0 = this.levelBuyCount.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       KeyValue tmp_0 = this.levelBuyCount.get(index_0);
/* 64 */       clone.levelBuyCount.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.levelBuyCount.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"levelBuyCount\":" + this.levelBuyCount.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\sign\LevelPackageInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */