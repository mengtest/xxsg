/*    */ package com.linlongyx.sanguo.webgame.proto.binary.draw;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DrawCardData;
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
/*    */ public class DrawCardPlayResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int drawId;
/* 21 */   public ArrayList<DrawCardData> addList = new ArrayList<>();
/*    */   public int nextFreeTime;
/*    */   
/*    */   public DrawCardPlayResponse() {
/* 25 */     this.eventResponseId = 21804;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public DrawCardPlayResponse(short retCode) {
/* 30 */     this.eventResponseId = 21804;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeInt(out, this.drawId);
/*    */     
/* 39 */     int size_0 = this.addList.size();
/* 40 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 41 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 43 */       DrawCardData tmp_0 = this.addList.get(index_0);
/* 44 */       tmp_0.serial(out);
/*    */     } 
/* 46 */     ProtocolUtil.writeInt(out, this.nextFreeTime);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     this.drawId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 53 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 54 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 56 */       DrawCardData tmp_0 = new DrawCardData();
/* 57 */       tmp_0.unserial(in);
/* 58 */       this.addList.add(tmp_0);
/*    */     } 
/* 60 */     this.nextFreeTime = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DrawCardPlayResponse clone() throws CloneNotSupportedException {
/* 65 */     DrawCardPlayResponse clone = (DrawCardPlayResponse)super.clone();
/* 66 */     int size_0 = this.addList.size();
/* 67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 69 */       DrawCardData tmp_0 = this.addList.get(index_0);
/* 70 */       clone.addList.add(tmp_0.clone());
/*    */     } 
/* 72 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 77 */     this.drawId = 0;
/* 78 */     this.addList.clear();
/* 79 */     this.nextFreeTime = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     String retVal = "{\"drawId\":" + this.drawId + ",\"addList\":" + this.addList.toString() + ",\"nextFreeTime\":" + this.nextFreeTime + "}";
/* 85 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\draw\DrawCardPlayResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */