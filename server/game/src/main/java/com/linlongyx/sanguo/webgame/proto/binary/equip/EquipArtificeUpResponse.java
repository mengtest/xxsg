/*    */ package com.linlongyx.sanguo.webgame.proto.binary.equip;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class EquipArtificeUpResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public long pid;
/*    */   public long mid;
/*    */   public int process;
/*    */   public int lucky;
/*    */   public int level;
/*    */   public int isSuccess;
/*    */   
/*    */   public EquipArtificeUpResponse() {
/* 27 */     this.eventResponseId = 20816;
/* 28 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public EquipArtificeUpResponse(short retCode) {
/* 32 */     this.eventResponseId = 20816;
/* 33 */     this.codeType = 2;
/* 34 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 39 */     ProtocolUtil.writeInt(out, this.type);
/* 40 */     ProtocolUtil.writeLong(out, this.pid);
/* 41 */     ProtocolUtil.writeLong(out, this.mid);
/* 42 */     ProtocolUtil.writeInt(out, this.process);
/* 43 */     ProtocolUtil.writeInt(out, this.lucky);
/* 44 */     ProtocolUtil.writeInt(out, this.level);
/* 45 */     ProtocolUtil.writeInt(out, this.isSuccess);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 50 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 51 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/* 52 */     this.mid = ProtocolUtil.readUTFBinLong(in);
/* 53 */     this.process = ProtocolUtil.readUTFBinInt(in);
/* 54 */     this.lucky = ProtocolUtil.readUTFBinInt(in);
/* 55 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 56 */     this.isSuccess = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public EquipArtificeUpResponse clone() throws CloneNotSupportedException {
/* 61 */     EquipArtificeUpResponse clone = (EquipArtificeUpResponse)super.clone();
/* 62 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 67 */     this.type = 0;
/* 68 */     this.pid = 0L;
/* 69 */     this.mid = 0L;
/* 70 */     this.process = 0;
/* 71 */     this.lucky = 0;
/* 72 */     this.level = 0;
/* 73 */     this.isSuccess = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     String retVal = "{\"type\":" + this.type + ",\"pid\":" + this.pid + ",\"mid\":" + this.mid + ",\"process\":" + this.process + ",\"lucky\":" + this.lucky + ",\"level\":" + this.level + ",\"isSuccess\":" + this.isSuccess + "}";
/* 79 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\equip\EquipArtificeUpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */