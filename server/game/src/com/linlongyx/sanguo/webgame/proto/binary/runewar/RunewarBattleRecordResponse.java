/*    */ package com.linlongyx.sanguo.webgame.proto.binary.runewar;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
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
/*    */ public class RunewarBattleRecordResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<BattleRecordData> records = new ArrayList<>();
/*    */   
/*    */   public RunewarBattleRecordResponse() {
/* 23 */     this.eventResponseId = 24506;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     int size_0 = this.records.size();
/* 31 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 32 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 34 */       BattleRecordData tmp_0 = this.records.get(index_0);
/* 35 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 43 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 45 */       BattleRecordData tmp_0 = new BattleRecordData();
/* 46 */       tmp_0.unserial(in);
/* 47 */       this.records.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public RunewarBattleRecordResponse clone() throws CloneNotSupportedException {
/* 53 */     RunewarBattleRecordResponse clone = (RunewarBattleRecordResponse)super.clone();
/* 54 */     int size_0 = this.records.size();
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       BattleRecordData tmp_0 = this.records.get(index_0);
/* 58 */       clone.records.add(tmp_0.clone());
/*    */     } 
/* 60 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 65 */     this.records.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     String retVal = "{\"records\":" + this.records.toString() + "}";
/* 71 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\runewar\RunewarBattleRecordResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */