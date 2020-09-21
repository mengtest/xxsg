/*    */ package com.linlongyx.sanguo.webgame.proto.binary.general;
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
/*    */ public class GeneralTotalInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int level;
/* 21 */   public ArrayList<IntIntValue> list = new ArrayList<>();
/*    */   public int orderNum;
/*    */   public int curChapter;
/*    */   public int currOrder;
/*    */   public int diffChapter;
/*    */   
/*    */   public GeneralTotalInfoResponse() {
/* 28 */     this.eventResponseId = 21201;
/* 29 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GeneralTotalInfoResponse(short retCode) {
/* 33 */     this.eventResponseId = 21201;
/* 34 */     this.codeType = 2;
/* 35 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 40 */     ProtocolUtil.writeInt(out, this.level);
/*    */     
/* 42 */     int size_0 = this.list.size();
/* 43 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       IntIntValue tmp_0 = this.list.get(index_0);
/* 47 */       tmp_0.serial(out);
/*    */     } 
/* 49 */     ProtocolUtil.writeInt(out, this.orderNum);
/* 50 */     ProtocolUtil.writeInt(out, this.curChapter);
/* 51 */     ProtocolUtil.writeInt(out, this.currOrder);
/* 52 */     ProtocolUtil.writeInt(out, this.diffChapter);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 57 */     this.level = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 59 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 60 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 62 */       IntIntValue tmp_0 = new IntIntValue();
/* 63 */       tmp_0.unserial(in);
/* 64 */       this.list.add(tmp_0);
/*    */     } 
/* 66 */     this.orderNum = ProtocolUtil.readUTFBinInt(in);
/* 67 */     this.curChapter = ProtocolUtil.readUTFBinInt(in);
/* 68 */     this.currOrder = ProtocolUtil.readUTFBinInt(in);
/* 69 */     this.diffChapter = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GeneralTotalInfoResponse clone() throws CloneNotSupportedException {
/* 74 */     GeneralTotalInfoResponse clone = (GeneralTotalInfoResponse)super.clone();
/* 75 */     int size_0 = this.list.size();
/* 76 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 78 */       IntIntValue tmp_0 = this.list.get(index_0);
/* 79 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 81 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 86 */     this.level = 0;
/* 87 */     this.list.clear();
/* 88 */     this.orderNum = 0;
/* 89 */     this.curChapter = 0;
/* 90 */     this.currOrder = 0;
/* 91 */     this.diffChapter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 96 */     String retVal = "{\"level\":" + this.level + ",\"list\":" + this.list.toString() + ",\"orderNum\":" + this.orderNum + ",\"curChapter\":" + this.curChapter + ",\"currOrder\":" + this.currOrder + ",\"diffChapter\":" + this.diffChapter + "}";
/* 97 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\general\GeneralTotalInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */