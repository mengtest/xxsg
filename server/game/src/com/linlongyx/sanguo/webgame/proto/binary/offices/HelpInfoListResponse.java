/*    */ package com.linlongyx.sanguo.webgame.proto.binary.offices;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.HelpInfoData;
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
/*    */ public class HelpInfoListResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<HelpInfoData> list = new ArrayList<>();
/*    */   public int helpTimes;
/*    */   
/*    */   public HelpInfoListResponse() {
/* 24 */     this.eventResponseId = 22502;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public HelpInfoListResponse(short retCode) {
/* 29 */     this.eventResponseId = 22502;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     int size_0 = this.list.size();
/* 38 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 39 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 41 */       HelpInfoData tmp_0 = this.list.get(index_0);
/* 42 */       tmp_0.serial(out);
/*    */     } 
/* 44 */     ProtocolUtil.writeInt(out, this.helpTimes);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 50 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 51 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 53 */       HelpInfoData tmp_0 = new HelpInfoData();
/* 54 */       tmp_0.unserial(in);
/* 55 */       this.list.add(tmp_0);
/*    */     } 
/* 57 */     this.helpTimes = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public HelpInfoListResponse clone() throws CloneNotSupportedException {
/* 62 */     HelpInfoListResponse clone = (HelpInfoListResponse)super.clone();
/* 63 */     int size_0 = this.list.size();
/* 64 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 66 */       HelpInfoData tmp_0 = this.list.get(index_0);
/* 67 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 69 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 74 */     this.list.clear();
/* 75 */     this.helpTimes = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     String retVal = "{\"list\":" + this.list.toString() + ",\"helpTimes\":" + this.helpTimes + "}";
/* 81 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\offices\HelpInfoListResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */