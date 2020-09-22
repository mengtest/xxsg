/*     */ package com.linlongyx.sanguo.webgame.proto.binary.destiny;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.StringUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyPlayerData;
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
/*     */ public class DestinyBetMatchInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*  21 */   public ArrayList<DestinyPlayerData> playerList = new ArrayList<>();
/*  22 */   public ArrayList<Integer> betNumList = new ArrayList<>();
/*     */   public byte state;
/*     */   public long winner;
/*     */   public String pkId;
/*     */   
/*     */   public DestinyBetMatchInfoResponse() {
/*  28 */     this.eventResponseId = 27411;
/*  29 */     this.codeType = 2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  35 */     int size_0 = this.playerList.size();
/*  36 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  37 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  39 */       DestinyPlayerData tmp_0 = this.playerList.get(index_0);
/*  40 */       tmp_0.serial(out);
/*     */     } 
/*     */     
/*  43 */     int size_1 = this.betNumList.size();
/*  44 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  45 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  47 */       int tmp_1 = ((Integer)this.betNumList.get(index_1)).intValue();
/*  48 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*  50 */     ProtocolUtil.writeByte(out, this.state);
/*  51 */     ProtocolUtil.writeLong(out, this.winner);
/*  52 */     ProtocolUtil.writeUTFBinary(out, this.pkId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  58 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  59 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  61 */       DestinyPlayerData tmp_0 = new DestinyPlayerData();
/*  62 */       tmp_0.unserial(in);
/*  63 */       this.playerList.add(tmp_0);
/*     */     } 
/*     */     
/*  66 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  67 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  69 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/*  70 */       this.betNumList.add(Integer.valueOf(tmp_1));
/*     */     } 
/*  72 */     this.state = ProtocolUtil.readUTFBinByte(in);
/*  73 */     this.winner = ProtocolUtil.readUTFBinLong(in);
/*  74 */     this.pkId = ProtocolUtil.readUTFStr(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public DestinyBetMatchInfoResponse clone() throws CloneNotSupportedException {
/*  79 */     DestinyBetMatchInfoResponse clone = (DestinyBetMatchInfoResponse)super.clone();
/*  80 */     int size_0 = this.playerList.size();
/*  81 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  83 */       DestinyPlayerData tmp_0 = this.playerList.get(index_0);
/*  84 */       clone.playerList.add(tmp_0.clone());
/*     */     } 
/*  86 */     int size_1 = this.betNumList.size();
/*  87 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  89 */       int tmp_1 = ((Integer)this.betNumList.get(index_1)).intValue();
/*  90 */       clone.betNumList.add(Integer.valueOf(tmp_1));
/*     */     } 
/*  92 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  97 */     this.playerList.clear();
/*  98 */     this.betNumList.clear();
/*  99 */     this.state = 0;
/* 100 */     this.winner = 0L;
/* 101 */     this.pkId = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 106 */     String retVal = "{\"playerList\":" + this.playerList.toString() + ",\"betNumList\":" + this.betNumList.toString() + ",\"state\":" + this.state + ",\"winner\":" + this.winner + ",\"pkId\":" + StringUtil.str2Str(this.pkId) + "}";
/* 107 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\destiny\DestinyBetMatchInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */