/*     */ package com.linlongyx.sanguo.webgame.proto.binary.rank;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ModelData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ import org.msgpack.annotation.Message;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Message
/*     */ public class RankingInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int type;
/*  21 */   public ArrayList<RankingData> datas = new ArrayList<>();
/*  22 */   public ModelData firstModel = new ModelData();
/*     */   public int firstWorship;
/*     */   public int myRank;
/*     */   public int worships;
/*     */   public int isWorship;
/*     */   public int worldLevel;
/*     */   public long myValue;
/*     */   public int titleId;
/*     */   
/*     */   public RankingInfoResponse() {
/*  32 */     this.eventResponseId = 27001;
/*  33 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public RankingInfoResponse(short retCode) {
/*  37 */     this.eventResponseId = 27001;
/*  38 */     this.codeType = 2;
/*  39 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  44 */     ProtocolUtil.writeInt(out, this.type);
/*     */     
/*  46 */     int size_0 = this.datas.size();
/*  47 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  48 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  50 */       RankingData tmp_0 = this.datas.get(index_0);
/*  51 */       tmp_0.serial(out);
/*     */     } 
/*  53 */     this.firstModel.serial(out);
/*  54 */     ProtocolUtil.writeInt(out, this.firstWorship);
/*  55 */     ProtocolUtil.writeInt(out, this.myRank);
/*  56 */     ProtocolUtil.writeInt(out, this.worships);
/*  57 */     ProtocolUtil.writeInt(out, this.isWorship);
/*  58 */     ProtocolUtil.writeInt(out, this.worldLevel);
/*  59 */     ProtocolUtil.writeLong(out, this.myValue);
/*  60 */     ProtocolUtil.writeInt(out, this.titleId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  65 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  67 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  68 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  70 */       RankingData tmp_0 = new RankingData();
/*  71 */       tmp_0.unserial(in);
/*  72 */       this.datas.add(tmp_0);
/*     */     } 
/*  74 */     this.firstModel = new ModelData();
/*  75 */     this.firstModel.unserial(in);
/*  76 */     this.firstWorship = ProtocolUtil.readUTFBinInt(in);
/*  77 */     this.myRank = ProtocolUtil.readUTFBinInt(in);
/*  78 */     this.worships = ProtocolUtil.readUTFBinInt(in);
/*  79 */     this.isWorship = ProtocolUtil.readUTFBinInt(in);
/*  80 */     this.worldLevel = ProtocolUtil.readUTFBinInt(in);
/*  81 */     this.myValue = ProtocolUtil.readUTFBinLong(in);
/*  82 */     this.titleId = ProtocolUtil.readUTFBinInt(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public RankingInfoResponse clone() throws CloneNotSupportedException {
/*  87 */     RankingInfoResponse clone = (RankingInfoResponse)super.clone();
/*  88 */     int size_0 = this.datas.size();
/*  89 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  91 */       RankingData tmp_0 = this.datas.get(index_0);
/*  92 */       clone.datas.add(tmp_0.clone());
/*     */     } 
/*  94 */     clone.firstModel = this.firstModel.clone();
/*  95 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 100 */     this.type = 0;
/* 101 */     this.datas.clear();
/* 102 */     this.firstModel.reset();
/* 103 */     this.firstWorship = 0;
/* 104 */     this.myRank = 0;
/* 105 */     this.worships = 0;
/* 106 */     this.isWorship = 0;
/* 107 */     this.worldLevel = 0;
/* 108 */     this.myValue = 0L;
/* 109 */     this.titleId = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 114 */     String retVal = "{\"type\":" + this.type + ",\"datas\":" + this.datas.toString() + ",\"firstModel\":" + this.firstModel.toString() + ",\"firstWorship\":" + this.firstWorship + ",\"myRank\":" + this.myRank + ",\"worships\":" + this.worships + ",\"isWorship\":" + this.isWorship + ",\"worldLevel\":" + this.worldLevel + ",\"myValue\":" + this.myValue + ",\"titleId\":" + this.titleId + "}";
/* 115 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rank\RankingInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */