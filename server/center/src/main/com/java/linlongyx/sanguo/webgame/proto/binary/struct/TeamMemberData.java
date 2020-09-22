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
/*    */ public class TeamMemberData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public String head;
/*    */   public int level;
/*    */   public long fightValue;
/*    */   public boolean isLeader;
/*    */   public int seat;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 24 */     ProtocolUtil.writeLong(out, this.playerId);
/* 25 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 26 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 27 */     ProtocolUtil.writeInt(out, this.level);
/* 28 */     ProtocolUtil.writeLong(out, this.fightValue);
/* 29 */     out.writeBoolean(this.isLeader);
/* 30 */     ProtocolUtil.writeInt(out, this.seat);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 36 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 37 */     this.head = ProtocolUtil.readUTFStr(in);
/* 38 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 40 */     this.isLeader = in.readBoolean();
/* 41 */     this.seat = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TeamMemberData clone() throws CloneNotSupportedException {
/* 46 */     TeamMemberData clone = (TeamMemberData)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.playerId = 0L;
/* 53 */     this.playerName = null;
/* 54 */     this.head = null;
/* 55 */     this.level = 0;
/* 56 */     this.fightValue = 0L;
/* 57 */     this.isLeader = false;
/* 58 */     this.seat = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     String retVal = "{\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"level\":" + this.level + ",\"fightValue\":" + this.fightValue + ",\"isLeader\":" + this.isLeader + ",\"seat\":" + this.seat + "}";
/* 64 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\TeamMemberData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */