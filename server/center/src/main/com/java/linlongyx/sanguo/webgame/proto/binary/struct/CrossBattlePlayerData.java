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
/*    */ public class CrossBattlePlayerData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long playerId;
/*    */   public String name;
/*    */   public String head;
/*    */   public int quality;
/*    */   public int level;
/*    */   public long fightValue;
/*    */   public long hp;
/*    */   public long maxHp;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 25 */     ProtocolUtil.writeLong(out, this.playerId);
/* 26 */     ProtocolUtil.writeUTFBinary(out, this.name);
/* 27 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 28 */     ProtocolUtil.writeInt(out, this.quality);
/* 29 */     ProtocolUtil.writeInt(out, this.level);
/* 30 */     ProtocolUtil.writeLong(out, this.fightValue);
/* 31 */     ProtocolUtil.writeLong(out, this.hp);
/* 32 */     ProtocolUtil.writeLong(out, this.maxHp);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 38 */     this.name = ProtocolUtil.readUTFStr(in);
/* 39 */     this.head = ProtocolUtil.readUTFStr(in);
/* 40 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 43 */     this.hp = ProtocolUtil.readUTFBinLong(in);
/* 44 */     this.maxHp = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossBattlePlayerData clone() throws CloneNotSupportedException {
/* 49 */     CrossBattlePlayerData clone = (CrossBattlePlayerData)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.playerId = 0L;
/* 56 */     this.name = null;
/* 57 */     this.head = null;
/* 58 */     this.quality = 0;
/* 59 */     this.level = 0;
/* 60 */     this.fightValue = 0L;
/* 61 */     this.hp = 0L;
/* 62 */     this.maxHp = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 67 */     String retVal = "{\"playerId\":" + this.playerId + ",\"name\":" + StringUtil.str2Str(this.name) + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"quality\":" + this.quality + ",\"level\":" + this.level + ",\"fightValue\":" + this.fightValue + ",\"hp\":" + this.hp + ",\"maxHp\":" + this.maxHp + "}";
/* 68 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\CrossBattlePlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */