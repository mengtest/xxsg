/*    */ package com.linlongyx.sanguo.webgame.proto.internal;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.IEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AddAvatarEvent
/*    */   extends InterEvent
/*    */   implements IEvent
/*    */ {
/*    */   public byte type;
/*    */   public int objectId;
/*    */   public String name;
/*    */   public short posx;
/*    */   public short posy;
/*    */   public byte direction;
/*    */   public boolean isPlayer;
/*    */   public long hp;
/*    */   public long hpMax;
/*    */   public int pkmod;
/*    */   public int level;
/*    */   public byte sex;
/*    */   public long blocId;
/*    */   public int size;
/*    */   
/*    */   public AddAvatarEvent(byte type, long playerId, int pkmod, String name, long hp, long hpMax, int objectId, int level, byte sex, int size, long blocId, short posx, short posy, byte direction, boolean isPlayer) {
/* 28 */     this.type = type;
/* 29 */     this.name = name;
/* 30 */     this.pkmod = pkmod;
/* 31 */     this.hp = hp;
/* 32 */     this.hpMax = hpMax;
/* 33 */     this.objectId = objectId;
/* 34 */     this.eventId = 30002;
/* 35 */     this.playerId = playerId;
/* 36 */     this.level = level;
/* 37 */     this.posx = posx;
/* 38 */     this.posy = posy;
/* 39 */     this.direction = direction;
/* 40 */     this.isPlayer = isPlayer;
/* 41 */     this.sex = sex;
/* 42 */     this.blocId = blocId;
/* 43 */     this.size = size;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\internal\AddAvatarEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */