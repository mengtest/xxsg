/*    */ package com.linlongyx.sanguo.webgame.proto.binary.arena;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ArenaReportData;
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
/*    */ public class ArenaReportResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<ArenaReportData> arenaReports = new ArrayList<>();
/*    */   
/*    */   public ArenaReportResponse() {
/* 23 */     this.eventResponseId = 21704;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ArenaReportResponse(short retCode) {
/* 28 */     this.eventResponseId = 21704;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.arenaReports.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       ArenaReportData tmp_0 = this.arenaReports.get(index_0);
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
/* 51 */       ArenaReportData tmp_0 = new ArenaReportData();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.arenaReports.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ArenaReportResponse clone() throws CloneNotSupportedException {
/* 59 */     ArenaReportResponse clone = (ArenaReportResponse)super.clone();
/* 60 */     int size_0 = this.arenaReports.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       ArenaReportData tmp_0 = this.arenaReports.get(index_0);
/* 64 */       clone.arenaReports.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.arenaReports.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"arenaReports\":" + this.arenaReports.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\arena\ArenaReportResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */