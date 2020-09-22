/*    */ package com.linlongyx.sanguo.webgame.proto.binary.crossTeam;
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
/*    */ public class CrossSendFighterRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long teamId;
/* 20 */   public ArrayList<Integer> fighterList = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public CrossSendFighterRequest() {
/* 24 */     this.eventRequestId = 11508;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeLong(out, this.teamId);
/*    */     
/* 32 */     int size_0 = this.fighterList.size();
/* 33 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       int tmp_0 = ((Integer)this.fighterList.get(index_0)).intValue();
/* 37 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 43 */     this.teamId = ProtocolUtil.readUTFBinLong(in);
/*    */     
/* 45 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 48 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 49 */       this.fighterList.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossSendFighterRequest clone() throws CloneNotSupportedException {
/* 55 */     CrossSendFighterRequest clone = (CrossSendFighterRequest)super.clone();
/* 56 */     int size_0 = this.fighterList.size();
/* 57 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 59 */       int tmp_0 = ((Integer)this.fighterList.get(index_0)).intValue();
/* 60 */       clone.fighterList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 62 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 67 */     this.teamId = 0L;
/* 68 */     this.fighterList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 73 */     String retVal = "{\"teamId\":" + this.teamId + ",\"fighterList\":" + this.fighterList.toString() + "}";
/* 74 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\crossTeam\CrossSendFighterRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */