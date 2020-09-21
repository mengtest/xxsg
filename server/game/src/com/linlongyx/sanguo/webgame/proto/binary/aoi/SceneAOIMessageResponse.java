/*     */ package com.linlongyx.sanguo.webgame.proto.binary.aoi;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.SceneAOI;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.SceneAvatar;
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
/*     */ public class SceneAOIMessageResponse
/*     */   extends ResponseBase
/*     */ {
/*  20 */   public ArrayList<SceneAvatar> addAvatars = new ArrayList<>();
/*  21 */   public ArrayList<SceneAOI> updAvatars = new ArrayList<>();
/*  22 */   public ArrayList<Long> delAvatars = new ArrayList<>();
/*     */   public long time;
/*     */   
/*     */   public SceneAOIMessageResponse() {
/*  26 */     this.eventResponseId = 20100;
/*  27 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public SceneAOIMessageResponse(short retCode) {
/*  31 */     this.eventResponseId = 20100;
/*  32 */     this.codeType = 2;
/*  33 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  39 */     int size_0 = this.addAvatars.size();
/*  40 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  41 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  43 */       SceneAvatar tmp_0 = this.addAvatars.get(index_0);
/*  44 */       tmp_0.serial(out);
/*     */     } 
/*     */     
/*  47 */     int size_1 = this.updAvatars.size();
/*  48 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  49 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  51 */       SceneAOI tmp_1 = this.updAvatars.get(index_1);
/*  52 */       tmp_1.serial(out);
/*     */     } 
/*     */     
/*  55 */     int size_2 = this.delAvatars.size();
/*  56 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  57 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  59 */       long tmp_2 = ((Long)this.delAvatars.get(index_2)).longValue();
/*  60 */       ProtocolUtil.writeLong(out, tmp_2);
/*     */     } 
/*  62 */     ProtocolUtil.writeLong(out, this.time);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  68 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  69 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  71 */       SceneAvatar tmp_0 = new SceneAvatar();
/*  72 */       tmp_0.unserial(in);
/*  73 */       this.addAvatars.add(tmp_0);
/*     */     } 
/*     */     
/*  76 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  77 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  79 */       SceneAOI tmp_1 = new SceneAOI();
/*  80 */       tmp_1.unserial(in);
/*  81 */       this.updAvatars.add(tmp_1);
/*     */     } 
/*     */     
/*  84 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/*  85 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  87 */       long tmp_2 = ProtocolUtil.readUTFBinLong(in);
/*  88 */       this.delAvatars.add(Long.valueOf(tmp_2));
/*     */     } 
/*  90 */     this.time = ProtocolUtil.readUTFBinLong(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public SceneAOIMessageResponse clone() throws CloneNotSupportedException {
/*  95 */     SceneAOIMessageResponse clone = (SceneAOIMessageResponse)super.clone();
/*  96 */     int size_0 = this.addAvatars.size();
/*  97 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  99 */       SceneAvatar tmp_0 = this.addAvatars.get(index_0);
/* 100 */       clone.addAvatars.add(tmp_0.clone());
/*     */     } 
/* 102 */     int size_1 = this.updAvatars.size();
/* 103 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 105 */       SceneAOI tmp_1 = this.updAvatars.get(index_1);
/* 106 */       clone.updAvatars.add(tmp_1.clone());
/*     */     } 
/* 108 */     int size_2 = this.delAvatars.size();
/* 109 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 111 */       long tmp_2 = ((Long)this.delAvatars.get(index_2)).longValue();
/* 112 */       clone.delAvatars.add(Long.valueOf(tmp_2));
/*     */     } 
/* 114 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 119 */     this.addAvatars.clear();
/* 120 */     this.updAvatars.clear();
/* 121 */     this.delAvatars.clear();
/* 122 */     this.time = 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 127 */     String retVal = "{\"addAvatars\":" + this.addAvatars.toString() + ",\"updAvatars\":" + this.updAvatars.toString() + ",\"delAvatars\":" + this.delAvatars.toString() + ",\"time\":" + this.time + "}";
/* 128 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\aoi\SceneAOIMessageResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */