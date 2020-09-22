/*    */ package com.linlongyx.sanguo.webgame.proto.binary.mental;
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
/*    */ public class OpenMentalBoxResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int rewardId;
/* 21 */   public ArrayList<Reward> rewards = new ArrayList<>();
/*    */   public int showLevel;
/*    */   
/*    */   public OpenMentalBoxResponse() {
/* 25 */     this.eventResponseId = 27304;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public OpenMentalBoxResponse(short retCode) {
/* 30 */     this.eventResponseId = 27304;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeInt(out, this.rewardId);
/*    */     
/* 39 */     int size_0 = this.rewards.size();
/* 40 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 41 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 43 */       Reward tmp_0 = this.rewards.get(index_0);
/* 44 */       tmp_0.serial(out);
/*    */     } 
/* 46 */     ProtocolUtil.writeInt(out, this.showLevel);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     this.rewardId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 53 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 54 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 56 */       Reward tmp_0 = new Reward();
/* 57 */       tmp_0.unserial(in);
/* 58 */       this.rewards.add(tmp_0);
/*    */     } 
/* 60 */     this.showLevel = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public OpenMentalBoxResponse clone() throws CloneNotSupportedException {
/* 65 */     OpenMentalBoxResponse clone = (OpenMentalBoxResponse)super.clone();
/* 66 */     int size_0 = this.rewards.size();
/* 67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 69 */       Reward tmp_0 = this.rewards.get(index_0);
/* 70 */       clone.rewards.add(tmp_0.clone());
/*    */     } 
/* 72 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 77 */     this.rewardId = 0;
/* 78 */     this.rewards.clear();
/* 79 */     this.showLevel = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     String retVal = "{\"rewardId\":" + this.rewardId + ",\"rewards\":" + this.rewards.toString() + ",\"showLevel\":" + this.showLevel + "}";
/* 85 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\mental\OpenMentalBoxResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */