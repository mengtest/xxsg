/*    */ package linlongyx.sanguo.webgame.proto.binary.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import linlongyx.sanguo.webgame.proto.binary.struct.CrossBattlePlayerData;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class CrossBattleCollectStateResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int state;
/*    */   public int resourceId;
/* 22 */   public ArrayList<CrossBattlePlayerData> defensePlayer = new ArrayList<>();
/*    */   
/*    */   public CrossBattleCollectStateResponse() {
/* 25 */     this.eventResponseId = 21313;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     ProtocolUtil.writeInt(out, this.state);
/* 32 */     ProtocolUtil.writeInt(out, this.resourceId);
/*    */     
/* 34 */     int size_0 = this.defensePlayer.size();
/* 35 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 36 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 38 */       CrossBattlePlayerData tmp_0 = this.defensePlayer.get(index_0);
/* 39 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 45 */     this.state = ProtocolUtil.readUTFBinInt(in);
/* 46 */     this.resourceId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 48 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 49 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 51 */       CrossBattlePlayerData tmp_0 = new CrossBattlePlayerData();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.defensePlayer.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossBattleCollectStateResponse clone() throws CloneNotSupportedException {
/* 59 */     CrossBattleCollectStateResponse clone = (CrossBattleCollectStateResponse)super.clone();
/* 60 */     int size_0 = this.defensePlayer.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       CrossBattlePlayerData tmp_0 = this.defensePlayer.get(index_0);
/* 64 */       clone.defensePlayer.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.state = 0;
/* 72 */     this.resourceId = 0;
/* 73 */     this.defensePlayer.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     String retVal = "{\"state\":" + this.state + ",\"resourceId\":" + this.resourceId + ",\"defensePlayer\":" + this.defensePlayer.toString() + "}";
/* 79 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cross\CrossBattleCollectStateResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */