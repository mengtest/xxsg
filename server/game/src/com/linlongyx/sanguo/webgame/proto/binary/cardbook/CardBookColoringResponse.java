/*    */ package com.linlongyx.sanguo.webgame.proto.binary.cardbook;
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
/*    */ public class CardBookColoringResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int cardId;
/*    */   public int totalRefColor;
/*    */   public int totalLastColor;
/*    */   public int refColor;
/*    */   public int lastColor;
/* 25 */   public ArrayList<Reward> list = new ArrayList<>();
/*    */   
/*    */   public CardBookColoringResponse() {
/* 28 */     this.eventResponseId = 22602;
/* 29 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public CardBookColoringResponse(short retCode) {
/* 33 */     this.eventResponseId = 22602;
/* 34 */     this.codeType = 2;
/* 35 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 40 */     ProtocolUtil.writeInt(out, this.cardId);
/* 41 */     ProtocolUtil.writeInt(out, this.totalRefColor);
/* 42 */     ProtocolUtil.writeInt(out, this.totalLastColor);
/* 43 */     ProtocolUtil.writeInt(out, this.refColor);
/* 44 */     ProtocolUtil.writeInt(out, this.lastColor);
/*    */     
/* 46 */     int size_0 = this.list.size();
/* 47 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 48 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 50 */       Reward tmp_0 = this.list.get(index_0);
/* 51 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 57 */     this.cardId = ProtocolUtil.readUTFBinInt(in);
/* 58 */     this.totalRefColor = ProtocolUtil.readUTFBinInt(in);
/* 59 */     this.totalLastColor = ProtocolUtil.readUTFBinInt(in);
/* 60 */     this.refColor = ProtocolUtil.readUTFBinInt(in);
/* 61 */     this.lastColor = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 63 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 64 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 66 */       Reward tmp_0 = new Reward();
/* 67 */       tmp_0.unserial(in);
/* 68 */       this.list.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public CardBookColoringResponse clone() throws CloneNotSupportedException {
/* 74 */     CardBookColoringResponse clone = (CardBookColoringResponse)super.clone();
/* 75 */     int size_0 = this.list.size();
/* 76 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 78 */       Reward tmp_0 = this.list.get(index_0);
/* 79 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 81 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 86 */     this.cardId = 0;
/* 87 */     this.totalRefColor = 0;
/* 88 */     this.totalLastColor = 0;
/* 89 */     this.refColor = 0;
/* 90 */     this.lastColor = 0;
/* 91 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 96 */     String retVal = "{\"cardId\":" + this.cardId + ",\"totalRefColor\":" + this.totalRefColor + ",\"totalLastColor\":" + this.totalLastColor + ",\"refColor\":" + this.refColor + ",\"lastColor\":" + this.lastColor + ",\"list\":" + this.list.toString() + "}";
/* 97 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cardbook\CardBookColoringResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */