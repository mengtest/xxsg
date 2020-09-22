/*    */ package linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FindRewardData
/*    */ {
/*    */   public int type;
/*    */   public int mold;
/*    */   public int curValue;
/*    */   public int maxValue;
/* 16 */   public ArrayList<Reward> rewards = new ArrayList<>();
/* 17 */   public ArrayList<Reward> costs = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 21 */     ProtocolUtil.writeInt(out, this.type);
/* 22 */     ProtocolUtil.writeInt(out, this.mold);
/* 23 */     ProtocolUtil.writeInt(out, this.curValue);
/* 24 */     ProtocolUtil.writeInt(out, this.maxValue);
/*    */     
/* 26 */     int size_0 = this.rewards.size();
/* 27 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 28 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 30 */       Reward tmp_0 = this.rewards.get(index_0);
/* 31 */       tmp_0.serial(out);
/*    */     } 
/*    */     
/* 34 */     int size_1 = this.costs.size();
/* 35 */     ProtocolUtil.writeStrArraySize(out, size_1);
/* 36 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 38 */       Reward tmp_1 = this.costs.get(index_1);
/* 39 */       tmp_1.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 45 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 46 */     this.mold = ProtocolUtil.readUTFBinInt(in);
/* 47 */     this.curValue = ProtocolUtil.readUTFBinInt(in);
/* 48 */     this.maxValue = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 50 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 51 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 53 */       Reward tmp_0 = new Reward();
/* 54 */       tmp_0.unserial(in);
/* 55 */       this.rewards.add(tmp_0);
/*    */     } 
/*    */     
/* 58 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 59 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 61 */       Reward tmp_1 = new Reward();
/* 62 */       tmp_1.unserial(in);
/* 63 */       this.costs.add(tmp_1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public FindRewardData clone() throws CloneNotSupportedException {
/* 69 */     FindRewardData clone = (FindRewardData)super.clone();
/* 70 */     int size_0 = this.rewards.size();
/* 71 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 73 */       Reward tmp_0 = this.rewards.get(index_0);
/* 74 */       clone.rewards.add(tmp_0.clone());
/*    */     } 
/* 76 */     int size_1 = this.costs.size();
/* 77 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 79 */       Reward tmp_1 = this.costs.get(index_1);
/* 80 */       clone.costs.add(tmp_1.clone());
/*    */     } 
/* 82 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 87 */     this.type = 0;
/* 88 */     this.mold = 0;
/* 89 */     this.curValue = 0;
/* 90 */     this.maxValue = 0;
/* 91 */     this.rewards.clear();
/* 92 */     this.costs.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 97 */     String retVal = "{\"type\":" + this.type + ",\"mold\":" + this.mold + ",\"curValue\":" + this.curValue + ",\"maxValue\":" + this.maxValue + ",\"rewards\":" + this.rewards.toString() + ",\"costs\":" + this.costs.toString() + "}";
/* 98 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\FindRewardData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */