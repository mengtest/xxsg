/*    */ package com.linlongyx.sanguo.webgame.proto.binary.rechargeAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
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
/*    */ public class RechargeActInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int id;
/*    */   public long value;
/* 22 */   public ArrayList<IntIntValue> list = new ArrayList<>();
/*    */   
/*    */   public RechargeActInfoResponse() {
/* 25 */     this.eventResponseId = 22301;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RechargeActInfoResponse(short retCode) {
/* 30 */     this.eventResponseId = 22301;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeInt(out, this.id);
/* 38 */     ProtocolUtil.writeLong(out, this.value);
/*    */     
/* 40 */     int size_0 = this.list.size();
/* 41 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 44 */       IntIntValue tmp_0 = this.list.get(index_0);
/* 45 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 52 */     this.value = ProtocolUtil.readUTFBinLong(in);
/*    */     
/* 54 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       IntIntValue tmp_0 = new IntIntValue();
/* 58 */       tmp_0.unserial(in);
/* 59 */       this.list.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public RechargeActInfoResponse clone() throws CloneNotSupportedException {
/* 65 */     RechargeActInfoResponse clone = (RechargeActInfoResponse)super.clone();
/* 66 */     int size_0 = this.list.size();
/* 67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 69 */       IntIntValue tmp_0 = this.list.get(index_0);
/* 70 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 72 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 77 */     this.id = 0;
/* 78 */     this.value = 0L;
/* 79 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     String retVal = "{\"id\":" + this.id + ",\"value\":" + this.value + ",\"list\":" + this.list.toString() + "}";
/* 85 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rechargeAct\RechargeActInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */