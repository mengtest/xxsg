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
/*    */ public class TeamData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long teamId;
/*    */   public String head;
/*    */   public String leaderName;
/*    */   public int size;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 21 */     ProtocolUtil.writeLong(out, this.teamId);
/* 22 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 23 */     ProtocolUtil.writeUTFBinary(out, this.leaderName);
/* 24 */     ProtocolUtil.writeInt(out, this.size);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 29 */     this.teamId = ProtocolUtil.readUTFBinLong(in);
/* 30 */     this.head = ProtocolUtil.readUTFStr(in);
/* 31 */     this.leaderName = ProtocolUtil.readUTFStr(in);
/* 32 */     this.size = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TeamData clone() throws CloneNotSupportedException {
/* 37 */     TeamData clone = (TeamData)super.clone();
/* 38 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 43 */     this.teamId = 0L;
/* 44 */     this.head = null;
/* 45 */     this.leaderName = null;
/* 46 */     this.size = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "{\"teamId\":" + this.teamId + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"leaderName\":" + StringUtil.str2Str(this.leaderName) + ",\"size\":" + this.size + "}";
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\TeamData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */