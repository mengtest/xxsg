/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TeamFighterData
/*    */ {
/*    */   public int fighterId;
/*    */   public int pid;
/*    */   public byte pos;
/*    */   public String name;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 19 */     ProtocolUtil.writeInt(out, this.fighterId);
/* 20 */     ProtocolUtil.writeInt(out, this.pid);
/* 21 */     ProtocolUtil.writeByte(out, this.pos);
/* 22 */     ProtocolUtil.writeUTFBinary(out, this.name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 27 */     this.fighterId = ProtocolUtil.readUTFBinInt(in);
/* 28 */     this.pid = ProtocolUtil.readUTFBinInt(in);
/* 29 */     this.pos = ProtocolUtil.readUTFBinByte(in);
/* 30 */     this.name = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TeamFighterData clone() throws CloneNotSupportedException {
/* 35 */     TeamFighterData clone = (TeamFighterData)super.clone();
/* 36 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 41 */     this.fighterId = 0;
/* 42 */     this.pid = 0;
/* 43 */     this.pos = 0;
/* 44 */     this.name = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     String retVal = "{\"fighterId\":" + this.fighterId + ",\"pid\":" + this.pid + ",\"pos\":" + this.pos + ",\"name\":" + StringUtil.str2Str(this.name) + "}";
/* 50 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\TeamFighterData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */