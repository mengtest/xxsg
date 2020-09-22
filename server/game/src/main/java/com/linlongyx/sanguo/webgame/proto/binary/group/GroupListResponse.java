/*    */ package com.linlongyx.sanguo.webgame.proto.binary.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GroupTipData;
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
/*    */ public class GroupListResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int page;
/*    */   public int total;
/* 22 */   public ArrayList<GroupTipData> datas = new ArrayList<>();
/*    */   
/*    */   public GroupListResponse() {
/* 25 */     this.eventResponseId = 21102;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GroupListResponse(short retCode) {
/* 30 */     this.eventResponseId = 21102;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeInt(out, this.page);
/* 38 */     ProtocolUtil.writeInt(out, this.total);
/*    */     
/* 40 */     int size_0 = this.datas.size();
/* 41 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 44 */       GroupTipData tmp_0 = this.datas.get(index_0);
/* 45 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     this.page = ProtocolUtil.readUTFBinInt(in);
/* 52 */     this.total = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 54 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       GroupTipData tmp_0 = new GroupTipData();
/* 58 */       tmp_0.unserial(in);
/* 59 */       this.datas.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupListResponse clone() throws CloneNotSupportedException {
/* 65 */     GroupListResponse clone = (GroupListResponse)super.clone();
/* 66 */     int size_0 = this.datas.size();
/* 67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 69 */       GroupTipData tmp_0 = this.datas.get(index_0);
/* 70 */       clone.datas.add(tmp_0.clone());
/*    */     } 
/* 72 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 77 */     this.page = 0;
/* 78 */     this.total = 0;
/* 79 */     this.datas.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     String retVal = "{\"page\":" + this.page + ",\"total\":" + this.total + ",\"datas\":" + this.datas.toString() + "}";
/* 85 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\group\GroupListResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */