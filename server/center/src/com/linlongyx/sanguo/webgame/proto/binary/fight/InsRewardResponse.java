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
/*    */ @Message
/*    */ public class InsRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public byte type;
/*    */   public int insId;
/*    */   public int star;
/*    */   public int percent;
/* 23 */   public ArrayList<Reward> list = new ArrayList<>();
/*    */   
/*    */   public InsRewardResponse() {
/* 26 */     this.eventResponseId = 20206;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public InsRewardResponse(short retCode) {
/* 31 */     this.eventResponseId = 20206;
/* 32 */     this.codeType = 2;
/* 33 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     ProtocolUtil.writeByte(out, this.type);
/* 39 */     ProtocolUtil.writeInt(out, this.insId);
/* 40 */     ProtocolUtil.writeInt(out, this.star);
/* 41 */     ProtocolUtil.writeInt(out, this.percent);
/*    */     
/* 43 */     int size_0 = this.list.size();
/* 44 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 45 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 47 */       Reward tmp_0 = this.list.get(index_0);
/* 48 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 54 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 55 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/* 56 */     this.star = ProtocolUtil.readUTFBinInt(in);
/* 57 */     this.percent = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 59 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 60 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 62 */       Reward tmp_0 = new Reward();
/* 63 */       tmp_0.unserial(in);
/* 64 */       this.list.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public InsRewardResponse clone() throws CloneNotSupportedException {
/* 70 */     InsRewardResponse clone = (InsRewardResponse)super.clone();
/* 71 */     int size_0 = this.list.size();
/* 72 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 74 */       Reward tmp_0 = this.list.get(index_0);
/* 75 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 77 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 82 */     this.type = 0;
/* 83 */     this.insId = 0;
/* 84 */     this.star = 0;
/* 85 */     this.percent = 0;
/* 86 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 91 */     String retVal = "{\"type\":" + this.type + ",\"insId\":" + this.insId + ",\"star\":" + this.star + ",\"percent\":" + this.percent + ",\"list\":" + this.list.toString() + "}";
/* 92 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\fight\InsRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */