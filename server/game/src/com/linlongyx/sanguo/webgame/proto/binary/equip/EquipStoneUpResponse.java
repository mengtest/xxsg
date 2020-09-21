/*    */ package com.linlongyx.sanguo.webgame.proto.binary.equip;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
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
/*    */ public class EquipStoneUpResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long pid;
/*    */   public long mid;
/* 22 */   public ArrayList<IntIntValue> kvList = new ArrayList<>();
/*    */   public int count;
/*    */   
/*    */   public EquipStoneUpResponse() {
/* 26 */     this.eventResponseId = 20813;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public EquipStoneUpResponse(short retCode) {
/* 31 */     this.eventResponseId = 20813;
/* 32 */     this.codeType = 2;
/* 33 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     ProtocolUtil.writeLong(out, this.pid);
/* 39 */     ProtocolUtil.writeLong(out, this.mid);
/*    */     
/* 41 */     int size_0 = this.kvList.size();
/* 42 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 43 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 45 */       IntIntValue tmp_0 = this.kvList.get(index_0);
/* 46 */       tmp_0.serial(out);
/*    */     } 
/* 48 */     ProtocolUtil.writeInt(out, this.count);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 53 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/* 54 */     this.mid = ProtocolUtil.readUTFBinLong(in);
/*    */     
/* 56 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 57 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 59 */       IntIntValue tmp_0 = new IntIntValue();
/* 60 */       tmp_0.unserial(in);
/* 61 */       this.kvList.add(tmp_0);
/*    */     } 
/* 63 */     this.count = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public EquipStoneUpResponse clone() throws CloneNotSupportedException {
/* 68 */     EquipStoneUpResponse clone = (EquipStoneUpResponse)super.clone();
/* 69 */     int size_0 = this.kvList.size();
/* 70 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 72 */       IntIntValue tmp_0 = this.kvList.get(index_0);
/* 73 */       clone.kvList.add(tmp_0.clone());
/*    */     } 
/* 75 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 80 */     this.pid = 0L;
/* 81 */     this.mid = 0L;
/* 82 */     this.kvList.clear();
/* 83 */     this.count = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     String retVal = "{\"pid\":" + this.pid + ",\"mid\":" + this.mid + ",\"kvList\":" + this.kvList.toString() + ",\"count\":" + this.count + "}";
/* 89 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\equip\EquipStoneUpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */