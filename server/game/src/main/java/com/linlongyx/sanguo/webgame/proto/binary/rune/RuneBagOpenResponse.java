/*    */ package com.linlongyx.sanguo.webgame.proto.binary.rune;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RuneItem;
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
/*    */ public class RuneBagOpenResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<RuneItem> infos = new ArrayList<>();
/*    */   
/*    */   public RuneBagOpenResponse() {
/* 23 */     this.eventResponseId = 27801;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RuneBagOpenResponse(short retCode) {
/* 28 */     this.eventResponseId = 27801;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.infos.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       RuneItem tmp_0 = this.infos.get(index_0);
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
/* 51 */       RuneItem tmp_0 = new RuneItem();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.infos.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public RuneBagOpenResponse clone() throws CloneNotSupportedException {
/* 59 */     RuneBagOpenResponse clone = (RuneBagOpenResponse)super.clone();
/* 60 */     int size_0 = this.infos.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       RuneItem tmp_0 = this.infos.get(index_0);
/* 64 */       clone.infos.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.infos.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"infos\":" + this.infos.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rune\RuneBagOpenResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */