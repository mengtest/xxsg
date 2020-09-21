/*    */ package com.linlongyx.sanguo.webgame.proto.binary.partner;
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
/*    */ public class BattlePartnerRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int type;
/* 20 */   public ArrayList<Long> pids = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public BattlePartnerRequest() {
/* 24 */     this.eventRequestId = 13303;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeInt(out, this.type);
/*    */     
/* 32 */     int size_0 = this.pids.size();
/* 33 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       long tmp_0 = ((Long)this.pids.get(index_0)).longValue();
/* 37 */       ProtocolUtil.writeLong(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 43 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 45 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 48 */       long tmp_0 = ProtocolUtil.readUTFBinLong(in);
/* 49 */       this.pids.add(Long.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public BattlePartnerRequest clone() throws CloneNotSupportedException {
/* 55 */     BattlePartnerRequest clone = (BattlePartnerRequest)super.clone();
/* 56 */     int size_0 = this.pids.size();
/* 57 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 59 */       long tmp_0 = ((Long)this.pids.get(index_0)).longValue();
/* 60 */       clone.pids.add(Long.valueOf(tmp_0));
/*    */     } 
/* 62 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 67 */     this.type = 0;
/* 68 */     this.pids.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 73 */     String retVal = "{\"type\":" + this.type + ",\"pids\":" + this.pids.toString() + "}";
/* 74 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\partner\BattlePartnerRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */