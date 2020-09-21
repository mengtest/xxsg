/*     */ package com.linlongyx.sanguo.webgame.proto.binary.recruit;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
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
/*     */ public class RedRecruitInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int type;
/*     */   public int free;
/*     */   public int score;
/*  22 */   public ArrayList<Integer> boxList = new ArrayList<>();
/*  23 */   public ArrayList<Integer> today = new ArrayList<>();
/*  24 */   public ArrayList<Integer> tommorow = new ArrayList<>();
/*     */   public int refreshNum;
/*     */   public int actId;
/*     */   
/*     */   public RedRecruitInfoResponse() {
/*  29 */     this.eventResponseId = 24003;
/*  30 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public RedRecruitInfoResponse(short retCode) {
/*  34 */     this.eventResponseId = 24003;
/*  35 */     this.codeType = 2;
/*  36 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  41 */     ProtocolUtil.writeInt(out, this.type);
/*  42 */     ProtocolUtil.writeInt(out, this.free);
/*  43 */     ProtocolUtil.writeInt(out, this.score);
/*     */     
/*  45 */     int size_0 = this.boxList.size();
/*  46 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  47 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  49 */       int tmp_0 = ((Integer)this.boxList.get(index_0)).intValue();
/*  50 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*     */     
/*  53 */     int size_1 = this.today.size();
/*  54 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  55 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  57 */       int tmp_1 = ((Integer)this.today.get(index_1)).intValue();
/*  58 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*     */     
/*  61 */     int size_2 = this.tommorow.size();
/*  62 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  63 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  65 */       int tmp_2 = ((Integer)this.tommorow.get(index_2)).intValue();
/*  66 */       ProtocolUtil.writeInt(out, tmp_2);
/*     */     } 
/*  68 */     ProtocolUtil.writeInt(out, this.refreshNum);
/*  69 */     ProtocolUtil.writeInt(out, this.actId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  74 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*  75 */     this.free = ProtocolUtil.readUTFBinInt(in);
/*  76 */     this.score = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  78 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  79 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  81 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  82 */       this.boxList.add(Integer.valueOf(tmp_0));
/*     */     } 
/*     */     
/*  85 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  86 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  88 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/*  89 */       this.today.add(Integer.valueOf(tmp_1));
/*     */     } 
/*     */     
/*  92 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/*  93 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  95 */       int tmp_2 = ProtocolUtil.readUTFBinInt(in);
/*  96 */       this.tommorow.add(Integer.valueOf(tmp_2));
/*     */     } 
/*  98 */     this.refreshNum = ProtocolUtil.readUTFBinInt(in);
/*  99 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public RedRecruitInfoResponse clone() throws CloneNotSupportedException {
/* 104 */     RedRecruitInfoResponse clone = (RedRecruitInfoResponse)super.clone();
/* 105 */     int size_0 = this.boxList.size();
/* 106 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 108 */       int tmp_0 = ((Integer)this.boxList.get(index_0)).intValue();
/* 109 */       clone.boxList.add(Integer.valueOf(tmp_0));
/*     */     } 
/* 111 */     int size_1 = this.today.size();
/* 112 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 114 */       int tmp_1 = ((Integer)this.today.get(index_1)).intValue();
/* 115 */       clone.today.add(Integer.valueOf(tmp_1));
/*     */     } 
/* 117 */     int size_2 = this.tommorow.size();
/* 118 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 120 */       int tmp_2 = ((Integer)this.tommorow.get(index_2)).intValue();
/* 121 */       clone.tommorow.add(Integer.valueOf(tmp_2));
/*     */     } 
/* 123 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 128 */     this.type = 0;
/* 129 */     this.free = 0;
/* 130 */     this.score = 0;
/* 131 */     this.boxList.clear();
/* 132 */     this.today.clear();
/* 133 */     this.tommorow.clear();
/* 134 */     this.refreshNum = 0;
/* 135 */     this.actId = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 140 */     String retVal = "{\"type\":" + this.type + ",\"free\":" + this.free + ",\"score\":" + this.score + ",\"boxList\":" + this.boxList.toString() + ",\"today\":" + this.today.toString() + ",\"tommorow\":" + this.tommorow.toString() + ",\"refreshNum\":" + this.refreshNum + ",\"actId\":" + this.actId + "}";
/* 141 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\recruit\RedRecruitInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */