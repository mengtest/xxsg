/*    */ package linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BoutPlayData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public byte waveId;
/*    */   public int roundId;
/* 16 */   public ArrayList<ResultPlayData> buffActions = new ArrayList<>();
/* 17 */   public ArrayList<SkillPlayData> skillDatas = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 21 */     ProtocolUtil.writeByte(out, this.waveId);
/* 22 */     ProtocolUtil.writeInt(out, this.roundId);
/*    */     
/* 24 */     int size_0 = this.buffActions.size();
/* 25 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 26 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 28 */       ResultPlayData tmp_0 = this.buffActions.get(index_0);
/* 29 */       tmp_0.serial(out);
/*    */     } 
/*    */     
/* 32 */     int size_1 = this.skillDatas.size();
/* 33 */     ProtocolUtil.writeStrArraySize(out, size_1);
/* 34 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 36 */       SkillPlayData tmp_1 = this.skillDatas.get(index_1);
/* 37 */       tmp_1.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 43 */     this.waveId = ProtocolUtil.readUTFBinByte(in);
/* 44 */     this.roundId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 46 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 47 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 49 */       ResultPlayData tmp_0 = new ResultPlayData();
/* 50 */       tmp_0.unserial(in);
/* 51 */       this.buffActions.add(tmp_0);
/*    */     } 
/*    */     
/* 54 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 55 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 57 */       SkillPlayData tmp_1 = new SkillPlayData();
/* 58 */       tmp_1.unserial(in);
/* 59 */       this.skillDatas.add(tmp_1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public BoutPlayData clone() throws CloneNotSupportedException {
/* 65 */     BoutPlayData clone = (BoutPlayData)super.clone();
/* 66 */     int size_0 = this.buffActions.size();
/* 67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 69 */       ResultPlayData tmp_0 = this.buffActions.get(index_0);
/* 70 */       clone.buffActions.add(tmp_0.clone());
/*    */     } 
/* 72 */     int size_1 = this.skillDatas.size();
/* 73 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 75 */       SkillPlayData tmp_1 = this.skillDatas.get(index_1);
/* 76 */       clone.skillDatas.add(tmp_1.clone());
/*    */     } 
/* 78 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 83 */     this.waveId = 0;
/* 84 */     this.roundId = 0;
/* 85 */     this.buffActions.clear();
/* 86 */     this.skillDatas.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 91 */     String retVal = "{\"waveId\":" + this.waveId + ",\"roundId\":" + this.roundId + ",\"buffActions\":" + this.buffActions.toString() + ",\"skillDatas\":" + this.skillDatas.toString() + "}";
/* 92 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\BoutPlayData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */