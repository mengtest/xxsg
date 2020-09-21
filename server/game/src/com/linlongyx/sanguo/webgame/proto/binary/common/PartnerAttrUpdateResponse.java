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
/*    */ public class PartnerAttrUpdateResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long pid;
/*    */   public long fightValue;
/* 23 */   public ArrayList<AttrValue> attrValues = new ArrayList<>();
/*    */   public String checksum;
/*    */   
/*    */   public PartnerAttrUpdateResponse() {
/* 27 */     this.eventResponseId = 21007;
/* 28 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public PartnerAttrUpdateResponse(short retCode) {
/* 32 */     this.eventResponseId = 21007;
/* 33 */     this.codeType = 2;
/* 34 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 39 */     ProtocolUtil.writeLong(out, this.pid);
/* 40 */     ProtocolUtil.writeLong(out, this.fightValue);
/*    */     
/* 42 */     int size_0 = this.attrValues.size();
/* 43 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       AttrValue tmp_0 = this.attrValues.get(index_0);
/* 47 */       tmp_0.serial(out);
/*    */     } 
/* 49 */     ProtocolUtil.writeUTFBinary(out, this.checksum);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 54 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/* 55 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/*    */     
/* 57 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 58 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 60 */       AttrValue tmp_0 = new AttrValue();
/* 61 */       tmp_0.unserial(in);
/* 62 */       this.attrValues.add(tmp_0);
/*    */     } 
/* 64 */     this.checksum = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public PartnerAttrUpdateResponse clone() throws CloneNotSupportedException {
/* 69 */     PartnerAttrUpdateResponse clone = (PartnerAttrUpdateResponse)super.clone();
/* 70 */     int size_0 = this.attrValues.size();
/* 71 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 73 */       AttrValue tmp_0 = this.attrValues.get(index_0);
/* 74 */       clone.attrValues.add(tmp_0.clone());
/*    */     } 
/* 76 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 81 */     this.pid = 0L;
/* 82 */     this.fightValue = 0L;
/* 83 */     this.attrValues.clear();
/* 84 */     this.checksum = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 89 */     String retVal = "{\"pid\":" + this.pid + ",\"fightValue\":" + this.fightValue + ",\"attrValues\":" + this.attrValues.toString() + ",\"checksum\":" + StringUtil.str2Str(this.checksum) + "}";
/* 90 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\common\PartnerAttrUpdateResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */