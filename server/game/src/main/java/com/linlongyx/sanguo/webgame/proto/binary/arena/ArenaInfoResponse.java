/*     */ package com.linlongyx.sanguo.webgame.proto.binary.arena;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ArenaData;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ import org.msgpack.annotation.Message;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Message
/*     */ public class ArenaInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int rank;
/*     */   public int maxRank;
/*  22 */   public ArrayList<ArenaData> arenaDatas = new ArrayList<>();
/*     */   public int fightTimes;
/*     */   public int buyTimes;
/*     */   public int eliminateTimes;
/*     */   public int remainTime;
/*     */   
/*     */   public ArenaInfoResponse() {
/*  29 */     this.eventResponseId = 21701;
/*  30 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public ArenaInfoResponse(short retCode) {
/*  34 */     this.eventResponseId = 21701;
/*  35 */     this.codeType = 2;
/*  36 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  41 */     ProtocolUtil.writeInt(out, this.rank);
/*  42 */     ProtocolUtil.writeInt(out, this.maxRank);
/*     */     
/*  44 */     int size_0 = this.arenaDatas.size();
/*  45 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  48 */       ArenaData tmp_0 = this.arenaDatas.get(index_0);
/*  49 */       tmp_0.serial(out);
/*     */     } 
/*  51 */     ProtocolUtil.writeInt(out, this.fightTimes);
/*  52 */     ProtocolUtil.writeInt(out, this.buyTimes);
/*  53 */     ProtocolUtil.writeInt(out, this.eliminateTimes);
/*  54 */     ProtocolUtil.writeInt(out, this.remainTime);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  59 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/*  60 */     this.maxRank = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  62 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  63 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  65 */       ArenaData tmp_0 = new ArenaData();
/*  66 */       tmp_0.unserial(in);
/*  67 */       this.arenaDatas.add(tmp_0);
/*     */     } 
/*  69 */     this.fightTimes = ProtocolUtil.readUTFBinInt(in);
/*  70 */     this.buyTimes = ProtocolUtil.readUTFBinInt(in);
/*  71 */     this.eliminateTimes = ProtocolUtil.readUTFBinInt(in);
/*  72 */     this.remainTime = ProtocolUtil.readUTFBinInt(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public ArenaInfoResponse clone() throws CloneNotSupportedException {
/*  77 */     ArenaInfoResponse clone = (ArenaInfoResponse)super.clone();
/*  78 */     int size_0 = this.arenaDatas.size();
/*  79 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  81 */       ArenaData tmp_0 = this.arenaDatas.get(index_0);
/*  82 */       clone.arenaDatas.add(tmp_0.clone());
/*     */     } 
/*  84 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  89 */     this.rank = 0;
/*  90 */     this.maxRank = 0;
/*  91 */     this.arenaDatas.clear();
/*  92 */     this.fightTimes = 0;
/*  93 */     this.buyTimes = 0;
/*  94 */     this.eliminateTimes = 0;
/*  95 */     this.remainTime = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 100 */     String retVal = "{\"rank\":" + this.rank + ",\"maxRank\":" + this.maxRank + ",\"arenaDatas\":" + this.arenaDatas.toString() + ",\"fightTimes\":" + this.fightTimes + ",\"buyTimes\":" + this.buyTimes + ",\"eliminateTimes\":" + this.eliminateTimes + ",\"remainTime\":" + this.remainTime + "}";
/* 101 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\arena\ArenaInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */