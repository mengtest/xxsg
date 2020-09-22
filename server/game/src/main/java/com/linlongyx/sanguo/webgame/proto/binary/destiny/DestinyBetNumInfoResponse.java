/*    */ package com.linlongyx.sanguo.webgame.proto.binary.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LongIntValue;
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
/*    */ public class DestinyBetNumInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public String pkId;
/* 22 */   public ArrayList<LongIntValue> playerBetList = new ArrayList<>();
/*    */   
/*    */   public DestinyBetNumInfoResponse() {
/* 25 */     this.eventResponseId = 27412;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     ProtocolUtil.writeUTFBinary(out, this.pkId);
/*    */     
/* 33 */     int size_0 = this.playerBetList.size();
/* 34 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 35 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 37 */       LongIntValue tmp_0 = this.playerBetList.get(index_0);
/* 38 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 44 */     this.pkId = ProtocolUtil.readUTFStr(in);
/*    */     
/* 46 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 47 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 49 */       LongIntValue tmp_0 = new LongIntValue();
/* 50 */       tmp_0.unserial(in);
/* 51 */       this.playerBetList.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyBetNumInfoResponse clone() throws CloneNotSupportedException {
/* 57 */     DestinyBetNumInfoResponse clone = (DestinyBetNumInfoResponse)super.clone();
/* 58 */     int size_0 = this.playerBetList.size();
/* 59 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 61 */       LongIntValue tmp_0 = this.playerBetList.get(index_0);
/* 62 */       clone.playerBetList.add(tmp_0.clone());
/*    */     } 
/* 64 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 69 */     this.pkId = null;
/* 70 */     this.playerBetList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     String retVal = "{\"pkId\":" + StringUtil.str2Str(this.pkId) + ",\"playerBetList\":" + this.playerBetList.toString() + "}";
/* 76 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\destiny\DestinyBetNumInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */