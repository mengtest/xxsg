/*     */ package com.linlongyx.sanguo.webgame.proto.binary.yearbeast;
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
/*     */ public class YearBeastBossInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int actId;
/*     */   public int bossId;
/*     */   public long currHp;
/*     */   public int changeTime;
/*     */   public int buyTimes;
/*     */   public long maxHp;
/*     */   public int currDay;
/*     */   public int worldLevel;
/*  27 */   public ArrayList<Integer> deathList = new ArrayList<>();
/*     */   
/*     */   public YearBeastBossInfoResponse() {
/*  30 */     this.eventResponseId = 20326;
/*  31 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public YearBeastBossInfoResponse(short retCode) {
/*  35 */     this.eventResponseId = 20326;
/*  36 */     this.codeType = 2;
/*  37 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  42 */     ProtocolUtil.writeInt(out, this.actId);
/*  43 */     ProtocolUtil.writeInt(out, this.bossId);
/*  44 */     ProtocolUtil.writeLong(out, this.currHp);
/*  45 */     ProtocolUtil.writeInt(out, this.changeTime);
/*  46 */     ProtocolUtil.writeInt(out, this.buyTimes);
/*  47 */     ProtocolUtil.writeLong(out, this.maxHp);
/*  48 */     ProtocolUtil.writeInt(out, this.currDay);
/*  49 */     ProtocolUtil.writeInt(out, this.worldLevel);
/*     */     
/*  51 */     int size_0 = this.deathList.size();
/*  52 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  53 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  55 */       int tmp_0 = ((Integer)this.deathList.get(index_0)).intValue();
/*  56 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  62 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/*  63 */     this.bossId = ProtocolUtil.readUTFBinInt(in);
/*  64 */     this.currHp = ProtocolUtil.readUTFBinLong(in);
/*  65 */     this.changeTime = ProtocolUtil.readUTFBinInt(in);
/*  66 */     this.buyTimes = ProtocolUtil.readUTFBinInt(in);
/*  67 */     this.maxHp = ProtocolUtil.readUTFBinLong(in);
/*  68 */     this.currDay = ProtocolUtil.readUTFBinInt(in);
/*  69 */     this.worldLevel = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  71 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  72 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  74 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  75 */       this.deathList.add(Integer.valueOf(tmp_0));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public YearBeastBossInfoResponse clone() throws CloneNotSupportedException {
/*  81 */     YearBeastBossInfoResponse clone = (YearBeastBossInfoResponse)super.clone();
/*  82 */     int size_0 = this.deathList.size();
/*  83 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  85 */       int tmp_0 = ((Integer)this.deathList.get(index_0)).intValue();
/*  86 */       clone.deathList.add(Integer.valueOf(tmp_0));
/*     */     } 
/*  88 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  93 */     this.actId = 0;
/*  94 */     this.bossId = 0;
/*  95 */     this.currHp = 0L;
/*  96 */     this.changeTime = 0;
/*  97 */     this.buyTimes = 0;
/*  98 */     this.maxHp = 0L;
/*  99 */     this.currDay = 0;
/* 100 */     this.worldLevel = 0;
/* 101 */     this.deathList.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 106 */     String retVal = "{\"actId\":" + this.actId + ",\"bossId\":" + this.bossId + ",\"currHp\":" + this.currHp + ",\"changeTime\":" + this.changeTime + ",\"buyTimes\":" + this.buyTimes + ",\"maxHp\":" + this.maxHp + ",\"currDay\":" + this.currDay + ",\"worldLevel\":" + this.worldLevel + ",\"deathList\":" + this.deathList.toString() + "}";
/* 107 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\yearbeast\YearBeastBossInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */