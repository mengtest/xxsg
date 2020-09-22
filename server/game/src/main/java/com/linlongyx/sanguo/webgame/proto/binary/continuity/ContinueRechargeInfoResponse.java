/*    */ package com.linlongyx.sanguo.webgame.proto.binary.continuity;
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
/*    */ public class ContinueRechargeInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int id;
/*    */   public long value;
/* 22 */   public ArrayList<IntIntValue> list = new ArrayList<>();
/*    */   public int time;
/*    */   public int days;
/*    */   
/*    */   public ContinueRechargeInfoResponse() {
/* 27 */     this.eventResponseId = 22102;
/* 28 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ContinueRechargeInfoResponse(short retCode) {
/* 32 */     this.eventResponseId = 22102;
/* 33 */     this.codeType = 2;
/* 34 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 39 */     ProtocolUtil.writeInt(out, this.id);
/* 40 */     ProtocolUtil.writeLong(out, this.value);
/*    */     
/* 42 */     int size_0 = this.list.size();
/* 43 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       IntIntValue tmp_0 = this.list.get(index_0);
/* 47 */       tmp_0.serial(out);
/*    */     } 
/* 49 */     ProtocolUtil.writeInt(out, this.time);
/* 50 */     ProtocolUtil.writeInt(out, this.days);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 55 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 56 */     this.value = ProtocolUtil.readUTFBinLong(in);
/*    */     
/* 58 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 59 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 61 */       IntIntValue tmp_0 = new IntIntValue();
/* 62 */       tmp_0.unserial(in);
/* 63 */       this.list.add(tmp_0);
/*    */     } 
/* 65 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 66 */     this.days = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ContinueRechargeInfoResponse clone() throws CloneNotSupportedException {
/* 71 */     ContinueRechargeInfoResponse clone = (ContinueRechargeInfoResponse)super.clone();
/* 72 */     int size_0 = this.list.size();
/* 73 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 75 */       IntIntValue tmp_0 = this.list.get(index_0);
/* 76 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 78 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 83 */     this.id = 0;
/* 84 */     this.value = 0L;
/* 85 */     this.list.clear();
/* 86 */     this.time = 0;
/* 87 */     this.days = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 92 */     String retVal = "{\"id\":" + this.id + ",\"value\":" + this.value + ",\"list\":" + this.list.toString() + ",\"time\":" + this.time + ",\"days\":" + this.days + "}";
/* 93 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\continuity\ContinueRechargeInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */