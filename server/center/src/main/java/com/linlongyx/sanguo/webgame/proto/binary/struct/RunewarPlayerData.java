/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RunewarPlayerData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long playerId;
/*    */   public String name;
/*    */   public long fightValue;
/*    */   public int hp;
/*    */   public int point;
/*    */   public int axis;
/*    */   public int distance;
/* 22 */   public ModelData data = new ModelData();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 26 */     ProtocolUtil.writeLong(out, this.playerId);
/* 27 */     ProtocolUtil.writeUTFBinary(out, this.name);
/* 28 */     ProtocolUtil.writeLong(out, this.fightValue);
/* 29 */     ProtocolUtil.writeInt(out, this.hp);
/* 30 */     ProtocolUtil.writeInt(out, this.point);
/* 31 */     ProtocolUtil.writeInt(out, this.axis);
/* 32 */     ProtocolUtil.writeInt(out, this.distance);
/* 33 */     this.data.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 39 */     this.name = ProtocolUtil.readUTFStr(in);
/* 40 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 41 */     this.hp = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.point = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.axis = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.distance = ProtocolUtil.readUTFBinInt(in);
/* 45 */     this.data = new ModelData();
/* 46 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RunewarPlayerData clone() throws CloneNotSupportedException {
/* 51 */     RunewarPlayerData clone = (RunewarPlayerData)super.clone();
/* 52 */     clone.data = this.data.clone();
/* 53 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 58 */     this.playerId = 0L;
/* 59 */     this.name = null;
/* 60 */     this.fightValue = 0L;
/* 61 */     this.hp = 0;
/* 62 */     this.point = 0;
/* 63 */     this.axis = 0;
/* 64 */     this.distance = 0;
/* 65 */     this.data.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     String retVal = "{\"playerId\":" + this.playerId + ",\"name\":" + StringUtil.str2Str(this.name) + ",\"fightValue\":" + this.fightValue + ",\"hp\":" + this.hp + ",\"point\":" + this.point + ",\"axis\":" + this.axis + ",\"distance\":" + this.distance + ",\"data\":" + this.data.toString() + "}";
/* 71 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\RunewarPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */