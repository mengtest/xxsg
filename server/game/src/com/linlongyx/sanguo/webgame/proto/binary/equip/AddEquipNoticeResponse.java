/*    */ package com.linlongyx.sanguo.webgame.proto.binary.equip;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.EquipData;
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
/*    */ public class AddEquipNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<EquipData> equipDatas = new ArrayList<>();
/*    */   
/*    */   public AddEquipNoticeResponse() {
/* 23 */     this.eventResponseId = 20808;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public AddEquipNoticeResponse(short retCode) {
/* 28 */     this.eventResponseId = 20808;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.equipDatas.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       EquipData tmp_0 = this.equipDatas.get(index_0);
/* 41 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 48 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 49 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 51 */       EquipData tmp_0 = new EquipData();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.equipDatas.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AddEquipNoticeResponse clone() throws CloneNotSupportedException {
/* 59 */     AddEquipNoticeResponse clone = (AddEquipNoticeResponse)super.clone();
/* 60 */     int size_0 = this.equipDatas.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       EquipData tmp_0 = this.equipDatas.get(index_0);
/* 64 */       clone.equipDatas.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.equipDatas.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"equipDatas\":" + this.equipDatas.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\equip\AddEquipNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */