/*    */ package com.linlongyx.sanguo.webgame.proto.binary.common;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.AttrValue;
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
/*    */ public class AttrUpdateResponse
/*    */   extends ResponseBase
/*    */ {
/* 21 */   public ArrayList<AttrValue> attrValues = new ArrayList<>();
/*    */   public String checksum;
/*    */   
/*    */   public AttrUpdateResponse() {
/* 25 */     this.eventResponseId = 21003;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public AttrUpdateResponse(short retCode) {
/* 30 */     this.eventResponseId = 21003;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     int size_0 = this.attrValues.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       AttrValue tmp_0 = this.attrValues.get(index_0);
/* 43 */       tmp_0.serial(out);
/*    */     } 
/* 45 */     ProtocolUtil.writeUTFBinary(out, this.checksum);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       AttrValue tmp_0 = new AttrValue();
/* 55 */       tmp_0.unserial(in);
/* 56 */       this.attrValues.add(tmp_0);
/*    */     } 
/* 58 */     this.checksum = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public AttrUpdateResponse clone() throws CloneNotSupportedException {
/* 63 */     AttrUpdateResponse clone = (AttrUpdateResponse)super.clone();
/* 64 */     int size_0 = this.attrValues.size();
/* 65 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 67 */       AttrValue tmp_0 = this.attrValues.get(index_0);
/* 68 */       clone.attrValues.add(tmp_0.clone());
/*    */     } 
/* 70 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 75 */     this.attrValues.clear();
/* 76 */     this.checksum = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 81 */     String retVal = "{\"attrValues\":" + this.attrValues.toString() + ",\"checksum\":" + StringUtil.str2Str(this.checksum) + "}";
/* 82 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\common\AttrUpdateResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */