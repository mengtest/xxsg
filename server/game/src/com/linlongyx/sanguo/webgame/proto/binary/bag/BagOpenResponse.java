/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bag;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BagItemInfo;
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
/*    */ public class BagOpenResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<BagItemInfo> bagItemInfos = new ArrayList<>();
/*    */   public int bagSize;
/*    */   public int buyGoldTimes;
/*    */   public int buyGoldCost;
/*    */   
/*    */   public BagOpenResponse() {
/* 26 */     this.eventResponseId = 20701;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public BagOpenResponse(short retCode) {
/* 31 */     this.eventResponseId = 20701;
/* 32 */     this.codeType = 2;
/* 33 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 39 */     int size_0 = this.bagItemInfos.size();
/* 40 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 41 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 43 */       BagItemInfo tmp_0 = this.bagItemInfos.get(index_0);
/* 44 */       tmp_0.serial(out);
/*    */     } 
/* 46 */     ProtocolUtil.writeInt(out, this.bagSize);
/* 47 */     ProtocolUtil.writeInt(out, this.buyGoldTimes);
/* 48 */     ProtocolUtil.writeInt(out, this.buyGoldCost);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 54 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       BagItemInfo tmp_0 = new BagItemInfo();
/* 58 */       tmp_0.unserial(in);
/* 59 */       this.bagItemInfos.add(tmp_0);
/*    */     } 
/* 61 */     this.bagSize = ProtocolUtil.readUTFBinInt(in);
/* 62 */     this.buyGoldTimes = ProtocolUtil.readUTFBinInt(in);
/* 63 */     this.buyGoldCost = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BagOpenResponse clone() throws CloneNotSupportedException {
/* 68 */     BagOpenResponse clone = (BagOpenResponse)super.clone();
/* 69 */     int size_0 = this.bagItemInfos.size();
/* 70 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 72 */       BagItemInfo tmp_0 = this.bagItemInfos.get(index_0);
/* 73 */       clone.bagItemInfos.add(tmp_0.clone());
/*    */     } 
/* 75 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 80 */     this.bagItemInfos.clear();
/* 81 */     this.bagSize = 0;
/* 82 */     this.buyGoldTimes = 0;
/* 83 */     this.buyGoldCost = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     String retVal = "{\"bagItemInfos\":" + this.bagItemInfos.toString() + ",\"bagSize\":" + this.bagSize + ",\"buyGoldTimes\":" + this.buyGoldTimes + ",\"buyGoldCost\":" + this.buyGoldCost + "}";
/* 89 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bag\BagOpenResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */