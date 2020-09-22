/*    */ package com.linlongyx.sanguo.webgame.proto.binary.talisman;
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
/*    */ public class TalismanInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public byte freeTimes;
/*    */   public byte freeRefresh;
/*    */   public int refreshTimes;
/*    */   public int times;
/* 24 */   public ArrayList<IntIntValue> itemList = new ArrayList<>();
/*    */   
/*    */   public TalismanInfoResponse() {
/* 27 */     this.eventResponseId = 22903;
/* 28 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeByte(out, this.freeTimes);
/* 34 */     ProtocolUtil.writeByte(out, this.freeRefresh);
/* 35 */     ProtocolUtil.writeInt(out, this.refreshTimes);
/* 36 */     ProtocolUtil.writeInt(out, this.times);
/*    */     
/* 38 */     int size_0 = this.itemList.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       IntIntValue tmp_0 = this.itemList.get(index_0);
/* 43 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 49 */     this.freeTimes = ProtocolUtil.readUTFBinByte(in);
/* 50 */     this.freeRefresh = ProtocolUtil.readUTFBinByte(in);
/* 51 */     this.refreshTimes = ProtocolUtil.readUTFBinInt(in);
/* 52 */     this.times = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 54 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       IntIntValue tmp_0 = new IntIntValue();
/* 58 */       tmp_0.unserial(in);
/* 59 */       this.itemList.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public TalismanInfoResponse clone() throws CloneNotSupportedException {
/* 65 */     TalismanInfoResponse clone = (TalismanInfoResponse)super.clone();
/* 66 */     int size_0 = this.itemList.size();
/* 67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 69 */       IntIntValue tmp_0 = this.itemList.get(index_0);
/* 70 */       clone.itemList.add(tmp_0.clone());
/*    */     } 
/* 72 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 77 */     this.freeTimes = 0;
/* 78 */     this.freeRefresh = 0;
/* 79 */     this.refreshTimes = 0;
/* 80 */     this.times = 0;
/* 81 */     this.itemList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 86 */     String retVal = "{\"freeTimes\":" + this.freeTimes + ",\"freeRefresh\":" + this.freeRefresh + ",\"refreshTimes\":" + this.refreshTimes + ",\"times\":" + this.times + ",\"itemList\":" + this.itemList.toString() + "}";
/* 87 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\talisman\TalismanInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */