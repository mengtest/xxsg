/*     */ package com.linlongyx.sanguo.webgame.proto.binary.cross;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.CrossBattleResourceData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.CrossBattleTeamData;
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
/*     */ public class CrossEnterBattleResponse
/*     */   extends ResponseBase
/*     */ {
/*  20 */   public ArrayList<CrossBattleTeamData> teamList = new ArrayList<>();
/*  21 */   public ArrayList<CrossBattleResourceData> resources = new ArrayList<>();
/*  22 */   public CrossBattleTeamData self = new CrossBattleTeamData();
/*     */   public int worldLevel;
/*     */   public int time;
/*     */   
/*     */   public CrossEnterBattleResponse() {
/*  27 */     this.eventResponseId = 21306;
/*  28 */     this.codeType = 2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  34 */     int size_0 = this.teamList.size();
/*  35 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  36 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  38 */       CrossBattleTeamData tmp_0 = this.teamList.get(index_0);
/*  39 */       tmp_0.serial(out);
/*     */     } 
/*     */     
/*  42 */     int size_1 = this.resources.size();
/*  43 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  44 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  46 */       CrossBattleResourceData tmp_1 = this.resources.get(index_1);
/*  47 */       tmp_1.serial(out);
/*     */     } 
/*  49 */     this.self.serial(out);
/*  50 */     ProtocolUtil.writeInt(out, this.worldLevel);
/*  51 */     ProtocolUtil.writeInt(out, this.time);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  57 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  58 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  60 */       CrossBattleTeamData tmp_0 = new CrossBattleTeamData();
/*  61 */       tmp_0.unserial(in);
/*  62 */       this.teamList.add(tmp_0);
/*     */     } 
/*     */     
/*  65 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  66 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  68 */       CrossBattleResourceData tmp_1 = new CrossBattleResourceData();
/*  69 */       tmp_1.unserial(in);
/*  70 */       this.resources.add(tmp_1);
/*     */     } 
/*  72 */     this.self = new CrossBattleTeamData();
/*  73 */     this.self.unserial(in);
/*  74 */     this.worldLevel = ProtocolUtil.readUTFBinInt(in);
/*  75 */     this.time = ProtocolUtil.readUTFBinInt(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public CrossEnterBattleResponse clone() throws CloneNotSupportedException {
/*  80 */     CrossEnterBattleResponse clone = (CrossEnterBattleResponse)super.clone();
/*  81 */     int size_0 = this.teamList.size();
/*  82 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  84 */       CrossBattleTeamData tmp_0 = this.teamList.get(index_0);
/*  85 */       clone.teamList.add(tmp_0.clone());
/*     */     } 
/*  87 */     int size_1 = this.resources.size();
/*  88 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  90 */       CrossBattleResourceData tmp_1 = this.resources.get(index_1);
/*  91 */       clone.resources.add(tmp_1.clone());
/*     */     } 
/*  93 */     clone.self = this.self.clone();
/*  94 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  99 */     this.teamList.clear();
/* 100 */     this.resources.clear();
/* 101 */     this.self.reset();
/* 102 */     this.worldLevel = 0;
/* 103 */     this.time = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 108 */     String retVal = "{\"teamList\":" + this.teamList.toString() + ",\"resources\":" + this.resources.toString() + ",\"self\":" + this.self.toString() + ",\"worldLevel\":" + this.worldLevel + ",\"time\":" + this.time + "}";
/* 109 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cross\CrossEnterBattleResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */