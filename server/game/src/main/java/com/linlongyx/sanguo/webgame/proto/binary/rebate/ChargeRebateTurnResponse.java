/*    */ package com.linlongyx.sanguo.webgame.proto.binary.rebate;
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
/*    */ public class ChargeRebateTurnResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int actId;
/*    */   public int sorce;
/*    */   public int times;
/* 23 */   public ArrayList<KeyValue> reward = new ArrayList<>();
/*    */   
/*    */   public ChargeRebateTurnResponse() {
/* 26 */     this.eventResponseId = 24012;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ChargeRebateTurnResponse(short retCode) {
/* 31 */     this.eventResponseId = 24012;
/* 32 */     this.codeType = 2;
/* 33 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     ProtocolUtil.writeInt(out, this.actId);
/* 39 */     ProtocolUtil.writeInt(out, this.sorce);
/* 40 */     ProtocolUtil.writeInt(out, this.times);
/*    */     
/* 42 */     int size_0 = this.reward.size();
/* 43 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       KeyValue tmp_0 = this.reward.get(index_0);
/* 47 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 53 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/* 54 */     this.sorce = ProtocolUtil.readUTFBinInt(in);
/* 55 */     this.times = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 57 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 58 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 60 */       KeyValue tmp_0 = new KeyValue();
/* 61 */       tmp_0.unserial(in);
/* 62 */       this.reward.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ChargeRebateTurnResponse clone() throws CloneNotSupportedException {
/* 68 */     ChargeRebateTurnResponse clone = (ChargeRebateTurnResponse)super.clone();
/* 69 */     int size_0 = this.reward.size();
/* 70 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 72 */       KeyValue tmp_0 = this.reward.get(index_0);
/* 73 */       clone.reward.add(tmp_0.clone());
/*    */     } 
/* 75 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 80 */     this.actId = 0;
/* 81 */     this.sorce = 0;
/* 82 */     this.times = 0;
/* 83 */     this.reward.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     String retVal = "{\"actId\":" + this.actId + ",\"sorce\":" + this.sorce + ",\"times\":" + this.times + ",\"reward\":" + this.reward.toString() + "}";
/* 89 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rebate\ChargeRebateTurnResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */