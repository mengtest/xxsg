/*    */ package com.linlongyx.sanguo.webgame.proto.binary.divine;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.StringUtil;
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
/*    */ public class DivineDrawTenResponse
/*    */   extends ResponseBase
/*    */ {
/* 21 */   public ArrayList<String> divineNumList = new ArrayList<>();
/*    */   public int divineTimes;
/* 23 */   public ArrayList<Reward> list = new ArrayList<>();
/*    */   
/*    */   public DivineDrawTenResponse() {
/* 26 */     this.eventResponseId = 24303;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     int size_0 = this.divineNumList.size();
/* 34 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 35 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 37 */       String tmp_0 = this.divineNumList.get(index_0);
/* 38 */       ProtocolUtil.writeUTFBinary(out, tmp_0);
/*    */     } 
/* 40 */     ProtocolUtil.writeInt(out, this.divineTimes);
/*    */     
/* 42 */     int size_1 = this.list.size();
/* 43 */     ProtocolUtil.writeStrArraySize(out, size_1);
/* 44 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 46 */       Reward tmp_1 = this.list.get(index_1);
/* 47 */       tmp_1.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 54 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       String tmp_0 = ProtocolUtil.readUTFStr(in);
/* 58 */       this.divineNumList.add(tmp_0);
/*    */     } 
/* 60 */     this.divineTimes = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 62 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 63 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 65 */       Reward tmp_1 = new Reward();
/* 66 */       tmp_1.unserial(in);
/* 67 */       this.list.add(tmp_1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public DivineDrawTenResponse clone() throws CloneNotSupportedException {
/* 73 */     DivineDrawTenResponse clone = (DivineDrawTenResponse)super.clone();
/* 74 */     int size_0 = this.divineNumList.size();
/* 75 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 77 */       String tmp_0 = this.divineNumList.get(index_0);
/* 78 */       clone.divineNumList.add(tmp_0);
/*    */     } 
/* 80 */     int size_1 = this.list.size();
/* 81 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 83 */       Reward tmp_1 = this.list.get(index_1);
/* 84 */       clone.list.add(tmp_1.clone());
/*    */     } 
/* 86 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 91 */     this.divineNumList.clear();
/* 92 */     this.divineTimes = 0;
/* 93 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 98 */     String retVal = "{\"divineNumList\":" + StringUtil.strArray2Str(this.divineNumList) + "\"divineTimes\":" + this.divineTimes + ",\"list\":" + this.list.toString() + "}";
/* 99 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\divine\DivineDrawTenResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */