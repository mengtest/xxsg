/*     */ package com.linlongyx.sanguo.webgame.proto.binary.common;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.EquipData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PartnerInfo;
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
/*     */ public class GetPlayerBattleInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*  20 */   public ArrayList<PartnerInfo> partnerList = new ArrayList<>();
/*  21 */   public ArrayList<EquipData> equips = new ArrayList<>();
/*     */   
/*     */   public GetPlayerBattleInfoResponse() {
/*  24 */     this.eventResponseId = 21116;
/*  25 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public GetPlayerBattleInfoResponse(short retCode) {
/*  29 */     this.eventResponseId = 21116;
/*  30 */     this.codeType = 2;
/*  31 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  37 */     int size_0 = this.partnerList.size();
/*  38 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  39 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  41 */       PartnerInfo tmp_0 = this.partnerList.get(index_0);
/*  42 */       tmp_0.serial(out);
/*     */     } 
/*     */     
/*  45 */     int size_1 = this.equips.size();
/*  46 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  47 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  49 */       EquipData tmp_1 = this.equips.get(index_1);
/*  50 */       tmp_1.serial(out);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  57 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  58 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  60 */       PartnerInfo tmp_0 = new PartnerInfo();
/*  61 */       tmp_0.unserial(in);
/*  62 */       this.partnerList.add(tmp_0);
/*     */     } 
/*     */     
/*  65 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  66 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  68 */       EquipData tmp_1 = new EquipData();
/*  69 */       tmp_1.unserial(in);
/*  70 */       this.equips.add(tmp_1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public GetPlayerBattleInfoResponse clone() throws CloneNotSupportedException {
/*  76 */     GetPlayerBattleInfoResponse clone = (GetPlayerBattleInfoResponse)super.clone();
/*  77 */     int size_0 = this.partnerList.size();
/*  78 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  80 */       PartnerInfo tmp_0 = this.partnerList.get(index_0);
/*  81 */       clone.partnerList.add(tmp_0.clone());
/*     */     } 
/*  83 */     int size_1 = this.equips.size();
/*  84 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  86 */       EquipData tmp_1 = this.equips.get(index_1);
/*  87 */       clone.equips.add(tmp_1.clone());
/*     */     } 
/*  89 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  94 */     this.partnerList.clear();
/*  95 */     this.equips.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 100 */     String retVal = "{\"partnerList\":" + this.partnerList.toString() + ",\"equips\":" + this.equips.toString() + "}";
/* 101 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\common\GetPlayerBattleInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */