/*    */ package com.linlongyx.sanguo.webgame.proto.binary.fight;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
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
/*    */ public class InsRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public byte type;
/*    */   public int insId;
/*    */   public int star;
/*    */   public int percent;
/* 24 */   public ArrayList<Reward> list = new ArrayList<>();
/*    */   
/*    */   public InsRewardResponse() {
/* 27 */     this.eventResponseId = 20206;
/* 28 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public InsRewardResponse(short retCode) {
/* 32 */     this.eventResponseId = 20206;
/* 33 */     this.codeType = 2;
/* 34 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 39 */     ProtocolUtil.writeByte(out, this.type);
/* 40 */     ProtocolUtil.writeInt(out, this.insId);
/* 41 */     ProtocolUtil.writeInt(out, this.star);
/* 42 */     ProtocolUtil.writeInt(out, this.percent);
/*    */     
/* 44 */     int size_0 = this.list.size();
/* 45 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 48 */       Reward tmp_0 = this.list.get(index_0);
/* 49 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 55 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 56 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/* 57 */     this.star = ProtocolUtil.readUTFBinInt(in);
/* 58 */     this.percent = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 60 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       Reward tmp_0 = new Reward();
/* 64 */       tmp_0.unserial(in);
/* 65 */       this.list.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public InsRewardResponse clone() throws CloneNotSupportedException {
/* 71 */     InsRewardResponse clone = (InsRewardResponse)super.clone();
/* 72 */     int size_0 = this.list.size();
/* 73 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 75 */       Reward tmp_0 = this.list.get(index_0);
/* 76 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 78 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 83 */     this.type = 0;
/* 84 */     this.insId = 0;
/* 85 */     this.star = 0;
/* 86 */     this.percent = 0;
/* 87 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 92 */     String retVal = "{\"type\":" + this.type + ",\"insId\":" + this.insId + ",\"star\":" + this.star + ",\"percent\":" + this.percent + ",\"list\":" + this.list.toString() + "}";
/* 93 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\fight\InsRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */