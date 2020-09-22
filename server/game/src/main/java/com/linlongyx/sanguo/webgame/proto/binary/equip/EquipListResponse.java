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
/*    */ public class EquipListResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<EquipData> equips = new ArrayList<>();
/*    */   
/*    */   public EquipListResponse() {
/* 23 */     this.eventResponseId = 20806;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public EquipListResponse(short retCode) {
/* 28 */     this.eventResponseId = 20806;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.equips.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       EquipData tmp_0 = this.equips.get(index_0);
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
/* 53 */       this.equips.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public EquipListResponse clone() throws CloneNotSupportedException {
/* 59 */     EquipListResponse clone = (EquipListResponse)super.clone();
/* 60 */     int size_0 = this.equips.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       EquipData tmp_0 = this.equips.get(index_0);
/* 64 */       clone.equips.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.equips.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"equips\":" + this.equips.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\equip\EquipListResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */