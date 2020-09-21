/*    */ package com.linlongyx.sanguo.webgame.proto.binary.turnplate;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.TurnplateRecord;
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
/*    */ public class TurnplateInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public byte firstDraw;
/*    */   public int point;
/*    */   public int yuanbao;
/*    */   public int endtime;
/* 24 */   public ArrayList<TurnplateRecord> recordList = new ArrayList<>();
/*    */   
/*    */   public TurnplateInfoResponse() {
/* 27 */     this.eventResponseId = 22401;
/* 28 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeByte(out, this.firstDraw);
/* 34 */     ProtocolUtil.writeInt(out, this.point);
/* 35 */     ProtocolUtil.writeInt(out, this.yuanbao);
/* 36 */     ProtocolUtil.writeInt(out, this.endtime);
/*    */     
/* 38 */     int size_0 = this.recordList.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       TurnplateRecord tmp_0 = this.recordList.get(index_0);
/* 43 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 49 */     this.firstDraw = ProtocolUtil.readUTFBinByte(in);
/* 50 */     this.point = ProtocolUtil.readUTFBinInt(in);
/* 51 */     this.yuanbao = ProtocolUtil.readUTFBinInt(in);
/* 52 */     this.endtime = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 54 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       TurnplateRecord tmp_0 = new TurnplateRecord();
/* 58 */       tmp_0.unserial(in);
/* 59 */       this.recordList.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public TurnplateInfoResponse clone() throws CloneNotSupportedException {
/* 65 */     TurnplateInfoResponse clone = (TurnplateInfoResponse)super.clone();
/* 66 */     int size_0 = this.recordList.size();
/* 67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 69 */       TurnplateRecord tmp_0 = this.recordList.get(index_0);
/* 70 */       clone.recordList.add(tmp_0.clone());
/*    */     } 
/* 72 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 77 */     this.firstDraw = 0;
/* 78 */     this.point = 0;
/* 79 */     this.yuanbao = 0;
/* 80 */     this.endtime = 0;
/* 81 */     this.recordList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 86 */     String retVal = "{\"firstDraw\":" + this.firstDraw + ",\"point\":" + this.point + ",\"yuanbao\":" + this.yuanbao + ",\"endtime\":" + this.endtime + ",\"recordList\":" + this.recordList.toString() + "}";
/* 87 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\turnplate\TurnplateInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */