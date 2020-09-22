/*    */ package com.linlongyx.sanguo.webgame.proto.binary.login;
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
/*    */ public class OfflineIncomeInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int sec;
/* 21 */   public ArrayList<Reward> offlineRewards = new ArrayList<>();
/*    */   public boolean card1;
/*    */   public boolean card2;
/*    */   
/*    */   public OfflineIncomeInfoResponse() {
/* 26 */     this.eventResponseId = 20006;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public OfflineIncomeInfoResponse(short retCode) {
/* 31 */     this.eventResponseId = 20006;
/* 32 */     this.codeType = 2;
/* 33 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     ProtocolUtil.writeInt(out, this.sec);
/*    */     
/* 40 */     int size_0 = this.offlineRewards.size();
/* 41 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 44 */       Reward tmp_0 = this.offlineRewards.get(index_0);
/* 45 */       tmp_0.serial(out);
/*    */     } 
/* 47 */     out.writeBoolean(this.card1);
/* 48 */     out.writeBoolean(this.card2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 53 */     this.sec = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 55 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 56 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 58 */       Reward tmp_0 = new Reward();
/* 59 */       tmp_0.unserial(in);
/* 60 */       this.offlineRewards.add(tmp_0);
/*    */     } 
/* 62 */     this.card1 = in.readBoolean();
/* 63 */     this.card2 = in.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public OfflineIncomeInfoResponse clone() throws CloneNotSupportedException {
/* 68 */     OfflineIncomeInfoResponse clone = (OfflineIncomeInfoResponse)super.clone();
/* 69 */     int size_0 = this.offlineRewards.size();
/* 70 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 72 */       Reward tmp_0 = this.offlineRewards.get(index_0);
/* 73 */       clone.offlineRewards.add(tmp_0.clone());
/*    */     } 
/* 75 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 80 */     this.sec = 0;
/* 81 */     this.offlineRewards.clear();
/* 82 */     this.card1 = false;
/* 83 */     this.card2 = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     String retVal = "{\"sec\":" + this.sec + ",\"offlineRewards\":" + this.offlineRewards.toString() + ",\"card1\":" + this.card1 + ",\"card2\":" + this.card2 + "}";
/* 89 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\OfflineIncomeInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */