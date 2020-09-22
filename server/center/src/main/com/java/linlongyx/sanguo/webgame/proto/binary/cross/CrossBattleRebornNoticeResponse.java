/*    */ package linlongyx.sanguo.webgame.proto.binary.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import linlongyx.sanguo.webgame.proto.binary.struct.CrossBattleTeamData;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class CrossBattleRebornNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public long leaderId;
/*    */   public long playerId;
/*    */   public int x;
/*    */   public int y;
/* 24 */   public CrossBattleTeamData playerData = new CrossBattleTeamData();
/*    */   
/*    */   public CrossBattleRebornNoticeResponse() {
/* 27 */     this.eventResponseId = 21307;
/* 28 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.type);
/* 34 */     ProtocolUtil.writeLong(out, this.leaderId);
/* 35 */     ProtocolUtil.writeLong(out, this.playerId);
/* 36 */     ProtocolUtil.writeInt(out, this.x);
/* 37 */     ProtocolUtil.writeInt(out, this.y);
/* 38 */     this.playerData.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 43 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.leaderId = ProtocolUtil.readUTFBinLong(in);
/* 45 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 46 */     this.x = ProtocolUtil.readUTFBinInt(in);
/* 47 */     this.y = ProtocolUtil.readUTFBinInt(in);
/* 48 */     this.playerData = new CrossBattleTeamData();
/* 49 */     this.playerData.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossBattleRebornNoticeResponse clone() throws CloneNotSupportedException {
/* 54 */     CrossBattleRebornNoticeResponse clone = (CrossBattleRebornNoticeResponse)super.clone();
/* 55 */     clone.playerData = this.playerData.clone();
/* 56 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 61 */     this.type = 0;
/* 62 */     this.leaderId = 0L;
/* 63 */     this.playerId = 0L;
/* 64 */     this.x = 0;
/* 65 */     this.y = 0;
/* 66 */     this.playerData.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 71 */     String retVal = "{\"type\":" + this.type + ",\"leaderId\":" + this.leaderId + ",\"playerId\":" + this.playerId + ",\"x\":" + this.x + ",\"y\":" + this.y + ",\"playerData\":" + this.playerData.toString() + "}";
/* 72 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cross\CrossBattleRebornNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */