/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*    */ import com.linlongyx.sanguo.webgame.processors.bosshome.BossUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.bosshome.WorldBossUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.bosshome.WorldBuyTimeProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.bosshome.WorldDamageListProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.bosshome.WorldInfoProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.bosshome.WorldRewardProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldBuyTimeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldDamageListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldRewardRequest;
/*    */ 
/*    */ public class BossGm implements IGm {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 18 */     BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/*    */     
/* 20 */     if (strings[2].equals("reset")) {
/* 21 */       bossHomeComponent.reset(0);
/* 22 */     } else if (strings[2].equals("worldOpen")) {
/*    */ 
/*    */       
/* 25 */       WorldBossUtil.openBoss();
/* 26 */     } else if (strings[2].equals("worldClose")) {
/*    */ 
/*    */       
/* 29 */       WorldBossUtil.closeBoss();
/* 30 */     } else if (strings[2].equals("rankDie")) {
/* 31 */       int insId = Integer.valueOf(strings[3]).intValue();
/* 32 */       BossUtil.bossDie(insId);
/* 33 */     } else if (strings[2].equals("rankBorn")) {
/* 34 */       int insId = Integer.valueOf(strings[3]).intValue();
/* 35 */       BossUtil.bossBorn(insId);
/* 36 */     } else if (strings[2].equals("save")) {
/* 37 */       WorldBossUtil.save();
/*    */ 
/*    */     
/*    */     }
/* 41 */     else if (strings[2].equals("t1")) {
/* 42 */       (new WorldInfoProcessor()).handle(playerSession, (RequestBase)new WorldInfoRequest());
/* 43 */     } else if (strings[2].equals("t2")) {
/* 44 */       WorldRewardRequest request = new WorldRewardRequest();
/* 45 */       request.id = Integer.valueOf(strings[3]).intValue();
/* 46 */       (new WorldRewardProcessor()).handle(playerSession, (RequestBase)request);
/* 47 */     } else if (strings[2].equals("t3")) {
/* 48 */       (new WorldDamageListProcessor()).handle(playerSession, (RequestBase)new WorldDamageListRequest());
/* 49 */     } else if (strings[2].equals("t4")) {
/* 50 */       (new WorldBuyTimeProcessor()).handle(playerSession, (RequestBase)new WorldBuyTimeRequest());
/* 51 */     } else if (strings[2].equals("t5")) {
/*    */     
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\BossGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */