/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NeutralBossSceneData
/*    */   implements Serializable
/*    */ {
/*    */   public int insId;
/*    */   public int size;
/* 16 */   public ArrayList<NeutralBossData> datas = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeInt(out, this.insId);
/* 21 */     ProtocolUtil.writeInt(out, this.size);
/*    */     
/* 23 */     int size_0 = this.datas.size();
/* 24 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 25 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 27 */       NeutralBossData tmp_0 = this.datas.get(index_0);
/* 28 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/* 35 */     this.size = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 37 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       NeutralBossData tmp_0 = new NeutralBossData();
/* 41 */       tmp_0.unserial(in);
/* 42 */       this.datas.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public NeutralBossSceneData clone() throws CloneNotSupportedException {
/* 48 */     NeutralBossSceneData clone = (NeutralBossSceneData)super.clone();
/* 49 */     int size_0 = this.datas.size();
/* 50 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 52 */       NeutralBossData tmp_0 = this.datas.get(index_0);
/* 53 */       clone.datas.add(tmp_0.clone());
/*    */     } 
/* 55 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 60 */     this.insId = 0;
/* 61 */     this.size = 0;
/* 62 */     this.datas.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 67 */     String retVal = "{\"insId\":" + this.insId + ",\"size\":" + this.size + ",\"datas\":" + this.datas.toString() + "}";
/* 68 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\NeutralBossSceneData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */