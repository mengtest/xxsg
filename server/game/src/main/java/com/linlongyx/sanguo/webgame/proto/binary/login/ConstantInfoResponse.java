/*    */ package com.linlongyx.sanguo.webgame.proto.binary.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
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
/*    */ public class ConstantInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int radio;
/*    */   public int isChoose;
/*    */   public int skipTimes;
/* 23 */   public ArrayList<Integer> questionnaire = new ArrayList<>();
/*    */   public String clientSets;
/*    */   public int shortCut;
/*    */   
/*    */   public ConstantInfoResponse() {
/* 28 */     this.eventResponseId = 20015;
/* 29 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ConstantInfoResponse(short retCode) {
/* 33 */     this.eventResponseId = 20015;
/* 34 */     this.codeType = 2;
/* 35 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 40 */     ProtocolUtil.writeInt(out, this.radio);
/* 41 */     ProtocolUtil.writeInt(out, this.isChoose);
/* 42 */     ProtocolUtil.writeInt(out, this.skipTimes);
/*    */     
/* 44 */     int size_0 = this.questionnaire.size();
/* 45 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 48 */       int tmp_0 = ((Integer)this.questionnaire.get(index_0)).intValue();
/* 49 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/* 51 */     ProtocolUtil.writeUTFBinary(out, this.clientSets);
/* 52 */     ProtocolUtil.writeInt(out, this.shortCut);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 57 */     this.radio = ProtocolUtil.readUTFBinInt(in);
/* 58 */     this.isChoose = ProtocolUtil.readUTFBinInt(in);
/* 59 */     this.skipTimes = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 61 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 62 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 64 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 65 */       this.questionnaire.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 67 */     this.clientSets = ProtocolUtil.readUTFStr(in);
/* 68 */     this.shortCut = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ConstantInfoResponse clone() throws CloneNotSupportedException {
/* 73 */     ConstantInfoResponse clone = (ConstantInfoResponse)super.clone();
/* 74 */     int size_0 = this.questionnaire.size();
/* 75 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 77 */       int tmp_0 = ((Integer)this.questionnaire.get(index_0)).intValue();
/* 78 */       clone.questionnaire.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 80 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 85 */     this.radio = 0;
/* 86 */     this.isChoose = 0;
/* 87 */     this.skipTimes = 0;
/* 88 */     this.questionnaire.clear();
/* 89 */     this.clientSets = null;
/* 90 */     this.shortCut = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 95 */     String retVal = "{\"radio\":" + this.radio + ",\"isChoose\":" + this.isChoose + ",\"skipTimes\":" + this.skipTimes + ",\"questionnaire\":" + this.questionnaire.toString() + ",\"clientSets\":" + StringUtil.str2Str(this.clientSets) + ",\"shortCut\":" + this.shortCut + "}";
/* 96 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\ConstantInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */