/*    */ package com.linlongyx.sanguo.webgame.proto.binary.equip;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.EquipData;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class EquipDataSysResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public EquipData equipData = new EquipData();
/*    */   
/*    */   public EquipDataSysResponse() {
/* 22 */     this.eventResponseId = 20815;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public EquipDataSysResponse(short retCode) {
/* 27 */     this.eventResponseId = 20815;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     this.equipData.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.equipData = new EquipData();
/* 40 */     this.equipData.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public EquipDataSysResponse clone() throws CloneNotSupportedException {
/* 45 */     EquipDataSysResponse clone = (EquipDataSysResponse)super.clone();
/* 46 */     clone.equipData = this.equipData.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.equipData.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"equipData\":" + this.equipData.toString() + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\equip\EquipDataSysResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */