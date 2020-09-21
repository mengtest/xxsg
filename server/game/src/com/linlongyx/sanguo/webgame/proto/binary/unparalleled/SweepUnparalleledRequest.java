/*    */ package com.linlongyx.sanguo.webgame.proto.binary.unparalleled;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
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
/*    */ public class SweepUnparalleledRequest
/*    */   extends RequestBase
/*    */ {
/* 19 */   public ArrayList<Integer> boxList = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public SweepUnparalleledRequest() {
/* 23 */     this.eventRequestId = 14410;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     int size_0 = this.boxList.size();
/* 31 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 32 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 34 */       int tmp_0 = ((Integer)this.boxList.get(index_0)).intValue();
/* 35 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 43 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 45 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 46 */       this.boxList.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public SweepUnparalleledRequest clone() throws CloneNotSupportedException {
/* 52 */     SweepUnparalleledRequest clone = (SweepUnparalleledRequest)super.clone();
/* 53 */     int size_0 = this.boxList.size();
/* 54 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 56 */       int tmp_0 = ((Integer)this.boxList.get(index_0)).intValue();
/* 57 */       clone.boxList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 59 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 64 */     this.boxList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 69 */     String retVal = "{\"boxList\":" + this.boxList.toString() + "}";
/* 70 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binar\\unparalleled\SweepUnparalleledRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */