/*    */ package com.linlongyx.sanguo.webgame.proto.binary.souls;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.SoulsData;
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
/*    */ public class SoulsActOrStarUpResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/* 21 */   public SoulsData data = new SoulsData();
/* 22 */   public ArrayList<Integer> combinationList = new ArrayList<>();
/*    */   
/*    */   public SoulsActOrStarUpResponse() {
/* 25 */     this.eventResponseId = 21209;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public SoulsActOrStarUpResponse(short retCode) {
/* 30 */     this.eventResponseId = 21209;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeInt(out, this.type);
/* 38 */     this.data.serial(out);
/*    */     
/* 40 */     int size_0 = this.combinationList.size();
/* 41 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 44 */       int tmp_0 = ((Integer)this.combinationList.get(index_0)).intValue();
/* 45 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 52 */     this.data = new SoulsData();
/* 53 */     this.data.unserial(in);
/*    */     
/* 55 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 56 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 58 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 59 */       this.combinationList.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public SoulsActOrStarUpResponse clone() throws CloneNotSupportedException {
/* 65 */     SoulsActOrStarUpResponse clone = (SoulsActOrStarUpResponse)super.clone();
/* 66 */     clone.data = this.data.clone();
/* 67 */     int size_0 = this.combinationList.size();
/* 68 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 70 */       int tmp_0 = ((Integer)this.combinationList.get(index_0)).intValue();
/* 71 */       clone.combinationList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 73 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 78 */     this.type = 0;
/* 79 */     this.data.reset();
/* 80 */     this.combinationList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 85 */     String retVal = "{\"type\":" + this.type + ",\"data\":" + this.data.toString() + ",\"combinationList\":" + this.combinationList.toString() + "}";
/* 86 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\souls\SoulsActOrStarUpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */