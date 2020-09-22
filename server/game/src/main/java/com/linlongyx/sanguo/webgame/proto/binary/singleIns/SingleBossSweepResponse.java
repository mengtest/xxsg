/*    */ package com.linlongyx.sanguo.webgame.proto.binary.singleIns;
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
/*    */ public class SingleBossSweepResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public int bossId;
/* 22 */   public ArrayList<Reward> list = new ArrayList<>();
/*    */   
/*    */   public SingleBossSweepResponse() {
/* 25 */     this.eventResponseId = 23006;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public SingleBossSweepResponse(short retCode) {
/* 30 */     this.eventResponseId = 23006;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeInt(out, this.type);
/* 38 */     ProtocolUtil.writeInt(out, this.bossId);
/*    */     
/* 40 */     int size_0 = this.list.size();
/* 41 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 44 */       Reward tmp_0 = this.list.get(index_0);
/* 45 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 52 */     this.bossId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 54 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       Reward tmp_0 = new Reward();
/* 58 */       tmp_0.unserial(in);
/* 59 */       this.list.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public SingleBossSweepResponse clone() throws CloneNotSupportedException {
/* 65 */     SingleBossSweepResponse clone = (SingleBossSweepResponse)super.clone();
/* 66 */     int size_0 = this.list.size();
/* 67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 69 */       Reward tmp_0 = this.list.get(index_0);
/* 70 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 72 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 77 */     this.type = 0;
/* 78 */     this.bossId = 0;
/* 79 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     String retVal = "{\"type\":" + this.type + ",\"bossId\":" + this.bossId + ",\"list\":" + this.list.toString() + "}";
/* 85 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\singleIns\SingleBossSweepResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */