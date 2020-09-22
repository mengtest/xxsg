/*    */ package linlongyx.sanguo.webgame.proto.binary.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class CrossBattleCollectResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int time;
/*    */   public int resourceId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 28 */     ProtocolUtil.writeInt(out, this.time);
/* 29 */     ProtocolUtil.writeInt(out, this.resourceId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 35 */     this.resourceId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossBattleCollectResponse clone() throws CloneNotSupportedException {
/* 40 */     CrossBattleCollectResponse clone = (CrossBattleCollectResponse)super.clone();
/* 41 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 46 */     this.time = 0;
/* 47 */     this.resourceId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 52 */     String retVal = "{\"time\":" + this.time + ",\"resourceId\":" + this.resourceId + "}";
/* 53 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cross\CrossBattleCollectResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */