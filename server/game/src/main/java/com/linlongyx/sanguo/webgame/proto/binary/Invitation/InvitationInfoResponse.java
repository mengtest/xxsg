/*     */ package com.linlongyx.sanguo.webgame.proto.binary.Invitation;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WxPlayerInfo;
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
/*     */ public class InvitationInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int recruitInvitation;
/*  21 */   public ArrayList<WxPlayerInfo> wxLevelInfo = new ArrayList<>();
/*  22 */   public ArrayList<Integer> normalRewards = new ArrayList<>();
/*     */   public int invitationNum;
/*     */   public int invitationTotal;
/*     */   public int num;
/*  26 */   public ArrayList<Integer> numRewards = new ArrayList<>();
/*  27 */   public ArrayList<WxPlayerInfo> wxCharge = new ArrayList<>();
/*     */   
/*     */   public InvitationInfoResponse() {
/*  30 */     this.eventResponseId = 25001;
/*  31 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public InvitationInfoResponse(short retCode) {
/*  35 */     this.eventResponseId = 25001;
/*  36 */     this.codeType = 2;
/*  37 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  42 */     ProtocolUtil.writeInt(out, this.recruitInvitation);
/*     */     
/*  44 */     int size_0 = this.wxLevelInfo.size();
/*  45 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  48 */       WxPlayerInfo tmp_0 = this.wxLevelInfo.get(index_0);
/*  49 */       tmp_0.serial(out);
/*     */     } 
/*     */     
/*  52 */     int size_1 = this.normalRewards.size();
/*  53 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  54 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  56 */       int tmp_1 = ((Integer)this.normalRewards.get(index_1)).intValue();
/*  57 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*  59 */     ProtocolUtil.writeInt(out, this.invitationNum);
/*  60 */     ProtocolUtil.writeInt(out, this.invitationTotal);
/*  61 */     ProtocolUtil.writeInt(out, this.num);
/*     */     
/*  63 */     int size_2 = this.numRewards.size();
/*  64 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  65 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  67 */       int tmp_2 = ((Integer)this.numRewards.get(index_2)).intValue();
/*  68 */       ProtocolUtil.writeInt(out, tmp_2);
/*     */     } 
/*     */     
/*  71 */     int size_3 = this.wxCharge.size();
/*  72 */     ProtocolUtil.writeStrArraySize(out, size_3);
/*  73 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/*  75 */       WxPlayerInfo tmp_3 = this.wxCharge.get(index_3);
/*  76 */       tmp_3.serial(out);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  82 */     this.recruitInvitation = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  84 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  85 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  87 */       WxPlayerInfo tmp_0 = new WxPlayerInfo();
/*  88 */       tmp_0.unserial(in);
/*  89 */       this.wxLevelInfo.add(tmp_0);
/*     */     } 
/*     */     
/*  92 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  93 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  95 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/*  96 */       this.normalRewards.add(Integer.valueOf(tmp_1));
/*     */     } 
/*  98 */     this.invitationNum = ProtocolUtil.readUTFBinInt(in);
/*  99 */     this.invitationTotal = ProtocolUtil.readUTFBinInt(in);
/* 100 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*     */     
/* 102 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/* 103 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 105 */       int tmp_2 = ProtocolUtil.readUTFBinInt(in);
/* 106 */       this.numRewards.add(Integer.valueOf(tmp_2));
/*     */     } 
/*     */     
/* 109 */     int size_3 = ProtocolUtil.readStrArraySize(in);
/* 110 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 112 */       WxPlayerInfo tmp_3 = new WxPlayerInfo();
/* 113 */       tmp_3.unserial(in);
/* 114 */       this.wxCharge.add(tmp_3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public InvitationInfoResponse clone() throws CloneNotSupportedException {
/* 120 */     InvitationInfoResponse clone = (InvitationInfoResponse)super.clone();
/* 121 */     int size_0 = this.wxLevelInfo.size();
/* 122 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 124 */       WxPlayerInfo tmp_0 = this.wxLevelInfo.get(index_0);
/* 125 */       clone.wxLevelInfo.add(tmp_0.clone());
/*     */     } 
/* 127 */     int size_1 = this.normalRewards.size();
/* 128 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 130 */       int tmp_1 = ((Integer)this.normalRewards.get(index_1)).intValue();
/* 131 */       clone.normalRewards.add(Integer.valueOf(tmp_1));
/*     */     } 
/* 133 */     int size_2 = this.numRewards.size();
/* 134 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 136 */       int tmp_2 = ((Integer)this.numRewards.get(index_2)).intValue();
/* 137 */       clone.numRewards.add(Integer.valueOf(tmp_2));
/*     */     } 
/* 139 */     int size_3 = this.wxCharge.size();
/* 140 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 142 */       WxPlayerInfo tmp_3 = this.wxCharge.get(index_3);
/* 143 */       clone.wxCharge.add(tmp_3.clone());
/*     */     } 
/* 145 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 150 */     this.recruitInvitation = 0;
/* 151 */     this.wxLevelInfo.clear();
/* 152 */     this.normalRewards.clear();
/* 153 */     this.invitationNum = 0;
/* 154 */     this.invitationTotal = 0;
/* 155 */     this.num = 0;
/* 156 */     this.numRewards.clear();
/* 157 */     this.wxCharge.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 162 */     String retVal = "{\"recruitInvitation\":" + this.recruitInvitation + ",\"wxLevelInfo\":" + this.wxLevelInfo.toString() + ",\"normalRewards\":" + this.normalRewards.toString() + ",\"invitationNum\":" + this.invitationNum + ",\"invitationTotal\":" + this.invitationTotal + ",\"num\":" + this.num + ",\"numRewards\":" + this.numRewards.toString() + ",\"wxCharge\":" + this.wxCharge.toString() + "}";
/* 163 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\Invitation\InvitationInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */