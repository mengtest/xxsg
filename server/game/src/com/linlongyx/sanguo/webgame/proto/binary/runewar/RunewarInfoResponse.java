/*    */ package com.linlongyx.sanguo.webgame.proto.binary.runewar;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RunewarPlayerData;
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
/*    */ public class RunewarInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<RunewarPlayerData> dataList = new ArrayList<>();
/*    */   public int time;
/*    */   
/*    */   public RunewarInfoResponse() {
/* 24 */     this.eventResponseId = 24501;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     int size_0 = this.dataList.size();
/* 32 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 33 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 35 */       RunewarPlayerData tmp_0 = this.dataList.get(index_0);
/* 36 */       tmp_0.serial(out);
/*    */     } 
/* 38 */     ProtocolUtil.writeInt(out, this.time);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 44 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 45 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 47 */       RunewarPlayerData tmp_0 = new RunewarPlayerData();
/* 48 */       tmp_0.unserial(in);
/* 49 */       this.dataList.add(tmp_0);
/*    */     } 
/* 51 */     this.time = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RunewarInfoResponse clone() throws CloneNotSupportedException {
/* 56 */     RunewarInfoResponse clone = (RunewarInfoResponse)super.clone();
/* 57 */     int size_0 = this.dataList.size();
/* 58 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 60 */       RunewarPlayerData tmp_0 = this.dataList.get(index_0);
/* 61 */       clone.dataList.add(tmp_0.clone());
/*    */     } 
/* 63 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 68 */     this.dataList.clear();
/* 69 */     this.time = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     String retVal = "{\"dataList\":" + this.dataList.toString() + ",\"time\":" + this.time + "}";
/* 75 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\runewar\RunewarInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */