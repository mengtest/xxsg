/*    */ package com.linlongyx.sanguo.webgame.proto.binary.skill;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.SkillData;
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
/*    */ public class SkillListResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<SkillData> skills = new ArrayList<>();
/*    */   
/*    */   public SkillListResponse() {
/* 23 */     this.eventResponseId = 20901;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public SkillListResponse(short retCode) {
/* 28 */     this.eventResponseId = 20901;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.skills.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       SkillData tmp_0 = this.skills.get(index_0);
/* 41 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 48 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 49 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 51 */       SkillData tmp_0 = new SkillData();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.skills.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public SkillListResponse clone() throws CloneNotSupportedException {
/* 59 */     SkillListResponse clone = (SkillListResponse)super.clone();
/* 60 */     int size_0 = this.skills.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       SkillData tmp_0 = this.skills.get(index_0);
/* 64 */       clone.skills.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.skills.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"skills\":" + this.skills.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\skill\SkillListResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */