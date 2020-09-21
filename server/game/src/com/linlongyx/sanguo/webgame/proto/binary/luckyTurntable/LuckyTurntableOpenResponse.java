/*    */ package com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
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
/*    */ public class LuckyTurntableOpenResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public ArrayList<Integer> actIdList = new ArrayList<>();
/*    */   
/*    */   public LuckyTurntableOpenResponse() {
/* 22 */     this.eventResponseId = 23505;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     int size_0 = this.actIdList.size();
/* 30 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 31 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 33 */       int tmp_0 = ((Integer)this.actIdList.get(index_0)).intValue();
/* 34 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 44 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 45 */       this.actIdList.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public LuckyTurntableOpenResponse clone() throws CloneNotSupportedException {
/* 51 */     LuckyTurntableOpenResponse clone = (LuckyTurntableOpenResponse)super.clone();
/* 52 */     int size_0 = this.actIdList.size();
/* 53 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 55 */       int tmp_0 = ((Integer)this.actIdList.get(index_0)).intValue();
/* 56 */       clone.actIdList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 58 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 63 */     this.actIdList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 68 */     String retVal = "{\"actIdList\":" + this.actIdList.toString() + "}";
/* 69 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\luckyTurntable\LuckyTurntableOpenResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */