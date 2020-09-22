/*    */ package com.linlongyx.sanguo.webgame.proto.binary.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GroupMemberData;
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
/*    */ public class GroupApplyListResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<GroupMemberData> datas = new ArrayList<>();
/*    */   public int auto;
/*    */   
/*    */   public GroupApplyListResponse() {
/* 24 */     this.eventResponseId = 21108;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GroupApplyListResponse(short retCode) {
/* 29 */     this.eventResponseId = 21108;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     int size_0 = this.datas.size();
/* 38 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 39 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 41 */       GroupMemberData tmp_0 = this.datas.get(index_0);
/* 42 */       tmp_0.serial(out);
/*    */     } 
/* 44 */     ProtocolUtil.writeInt(out, this.auto);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 50 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 51 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 53 */       GroupMemberData tmp_0 = new GroupMemberData();
/* 54 */       tmp_0.unserial(in);
/* 55 */       this.datas.add(tmp_0);
/*    */     } 
/* 57 */     this.auto = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupApplyListResponse clone() throws CloneNotSupportedException {
/* 62 */     GroupApplyListResponse clone = (GroupApplyListResponse)super.clone();
/* 63 */     int size_0 = this.datas.size();
/* 64 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 66 */       GroupMemberData tmp_0 = this.datas.get(index_0);
/* 67 */       clone.datas.add(tmp_0.clone());
/*    */     } 
/* 69 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 74 */     this.datas.clear();
/* 75 */     this.auto = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     String retVal = "{\"datas\":" + this.datas.toString() + ",\"auto\":" + this.auto + "}";
/* 81 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\group\GroupApplyListResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */