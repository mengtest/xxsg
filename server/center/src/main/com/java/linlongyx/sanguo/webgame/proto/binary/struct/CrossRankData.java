/*    */ package linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossRankData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int rank;
/*    */   public long id;
/*    */   public String name;
/*    */   public int point;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 21 */     ProtocolUtil.writeInt(out, this.rank);
/* 22 */     ProtocolUtil.writeLong(out, this.id);
/* 23 */     ProtocolUtil.writeUTFBinary(out, this.name);
/* 24 */     ProtocolUtil.writeInt(out, this.point);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 29 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 30 */     this.id = ProtocolUtil.readUTFBinLong(in);
/* 31 */     this.name = ProtocolUtil.readUTFStr(in);
/* 32 */     this.point = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossRankData clone() throws CloneNotSupportedException {
/* 37 */     CrossRankData clone = (CrossRankData)super.clone();
/* 38 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 43 */     this.rank = 0;
/* 44 */     this.id = 0L;
/* 45 */     this.name = null;
/* 46 */     this.point = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "{\"rank\":" + this.rank + ",\"id\":" + this.id + ",\"name\":" + StringUtil.str2Str(this.name) + ",\"point\":" + this.point + "}";
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\CrossRankData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */