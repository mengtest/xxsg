/*    */ package com.linlongyx.sanguo.webgame.proto.binary.equip;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LongIntValue;
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
/*    */ public class EquipStrengthUpResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long pid;
/*    */   public int type;
/* 22 */   public ArrayList<LongIntValue> equips = new ArrayList<>();
/*    */   
/*    */   public EquipStrengthUpResponse() {
/* 25 */     this.eventResponseId = 20811;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public EquipStrengthUpResponse(short retCode) {
/* 30 */     this.eventResponseId = 20811;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeLong(out, this.pid);
/* 38 */     ProtocolUtil.writeInt(out, this.type);
/*    */     
/* 40 */     int size_0 = this.equips.size();
/* 41 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 44 */       LongIntValue tmp_0 = this.equips.get(index_0);
/* 45 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/* 52 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 54 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       LongIntValue tmp_0 = new LongIntValue();
/* 58 */       tmp_0.unserial(in);
/* 59 */       this.equips.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public EquipStrengthUpResponse clone() throws CloneNotSupportedException {
/* 65 */     EquipStrengthUpResponse clone = (EquipStrengthUpResponse)super.clone();
/* 66 */     int size_0 = this.equips.size();
/* 67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 69 */       LongIntValue tmp_0 = this.equips.get(index_0);
/* 70 */       clone.equips.add(tmp_0.clone());
/*    */     } 
/* 72 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 77 */     this.pid = 0L;
/* 78 */     this.type = 0;
/* 79 */     this.equips.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     String retVal = "{\"pid\":" + this.pid + ",\"type\":" + this.type + ",\"equips\":" + this.equips.toString() + "}";
/* 85 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\equip\EquipStrengthUpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */