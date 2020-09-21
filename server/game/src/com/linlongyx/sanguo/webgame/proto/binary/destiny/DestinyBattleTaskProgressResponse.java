/*    */ package com.linlongyx.sanguo.webgame.proto.binary.destiny;
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
/*    */ public class DestinyBattleTaskProgressResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public ArrayList<Integer> tasks = new ArrayList<>();
/*    */   public int progress;
/*    */   
/*    */   public DestinyBattleTaskProgressResponse() {
/* 23 */     this.eventResponseId = 27406;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     int size_0 = this.tasks.size();
/* 31 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 32 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 34 */       int tmp_0 = ((Integer)this.tasks.get(index_0)).intValue();
/* 35 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/* 37 */     ProtocolUtil.writeInt(out, this.progress);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 43 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 47 */       this.tasks.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 49 */     this.progress = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyBattleTaskProgressResponse clone() throws CloneNotSupportedException {
/* 54 */     DestinyBattleTaskProgressResponse clone = (DestinyBattleTaskProgressResponse)super.clone();
/* 55 */     int size_0 = this.tasks.size();
/* 56 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 58 */       int tmp_0 = ((Integer)this.tasks.get(index_0)).intValue();
/* 59 */       clone.tasks.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 61 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 66 */     this.tasks.clear();
/* 67 */     this.progress = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 72 */     String retVal = "{\"tasks\":" + this.tasks.toString() + ",\"progress\":" + this.progress + "}";
/* 73 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\destiny\DestinyBattleTaskProgressResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */