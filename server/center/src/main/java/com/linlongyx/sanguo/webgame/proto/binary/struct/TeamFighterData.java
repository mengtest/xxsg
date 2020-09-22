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
/*    */ public class TeamFighterData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int fighterId;
/*    */   public int pid;
/*    */   public byte pos;
/*    */   public String name;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 21 */     ProtocolUtil.writeInt(out, this.fighterId);
/* 22 */     ProtocolUtil.writeInt(out, this.pid);
/* 23 */     ProtocolUtil.writeByte(out, this.pos);
/* 24 */     ProtocolUtil.writeUTFBinary(out, this.name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 29 */     this.fighterId = ProtocolUtil.readUTFBinInt(in);
/* 30 */     this.pid = ProtocolUtil.readUTFBinInt(in);
/* 31 */     this.pos = ProtocolUtil.readUTFBinByte(in);
/* 32 */     this.name = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TeamFighterData clone() throws CloneNotSupportedException {
/* 37 */     TeamFighterData clone = (TeamFighterData)super.clone();
/* 38 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 43 */     this.fighterId = 0;
/* 44 */     this.pid = 0;
/* 45 */     this.pos = 0;
/* 46 */     this.name = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "{\"fighterId\":" + this.fighterId + ",\"pid\":" + this.pid + ",\"pos\":" + this.pos + ",\"name\":" + StringUtil.str2Str(this.name) + "}";
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\TeamFighterData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */