/*    */ package com.linlongyx.sanguo.webgame.proto.binary.offices;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
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
/*    */ public class MilitaryOfficeInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<KeyValue> list = new ArrayList<>();
/*    */   public int currJobId;
/*    */   public int endTime;
/*    */   public int status;
/*    */   public int officeHelpTimes;
/*    */   
/*    */   public MilitaryOfficeInfoResponse() {
/* 27 */     this.eventResponseId = 22501;
/* 28 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public MilitaryOfficeInfoResponse(short retCode) {
/* 32 */     this.eventResponseId = 22501;
/* 33 */     this.codeType = 2;
/* 34 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 40 */     int size_0 = this.list.size();
/* 41 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 44 */       KeyValue tmp_0 = this.list.get(index_0);
/* 45 */       tmp_0.serial(out);
/*    */     } 
/* 47 */     ProtocolUtil.writeInt(out, this.currJobId);
/* 48 */     ProtocolUtil.writeInt(out, this.endTime);
/* 49 */     ProtocolUtil.writeInt(out, this.status);
/* 50 */     ProtocolUtil.writeInt(out, this.officeHelpTimes);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 56 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 57 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 59 */       KeyValue tmp_0 = new KeyValue();
/* 60 */       tmp_0.unserial(in);
/* 61 */       this.list.add(tmp_0);
/*    */     } 
/* 63 */     this.currJobId = ProtocolUtil.readUTFBinInt(in);
/* 64 */     this.endTime = ProtocolUtil.readUTFBinInt(in);
/* 65 */     this.status = ProtocolUtil.readUTFBinInt(in);
/* 66 */     this.officeHelpTimes = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MilitaryOfficeInfoResponse clone() throws CloneNotSupportedException {
/* 71 */     MilitaryOfficeInfoResponse clone = (MilitaryOfficeInfoResponse)super.clone();
/* 72 */     int size_0 = this.list.size();
/* 73 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 75 */       KeyValue tmp_0 = this.list.get(index_0);
/* 76 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 78 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 83 */     this.list.clear();
/* 84 */     this.currJobId = 0;
/* 85 */     this.endTime = 0;
/* 86 */     this.status = 0;
/* 87 */     this.officeHelpTimes = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 92 */     String retVal = "{\"list\":" + this.list.toString() + ",\"currJobId\":" + this.currJobId + ",\"endTime\":" + this.endTime + ",\"status\":" + this.status + ",\"officeHelpTimes\":" + this.officeHelpTimes + "}";
/* 93 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\offices\MilitaryOfficeInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */