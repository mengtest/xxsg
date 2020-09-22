/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.KeyValueConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.recruit.RedRecruitInfoProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.recruit.StartRecruitProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.RedRecruitInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.StartRecruitRequest;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecruitGm
/*    */   implements IGm
/*    */ {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 20 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/* 21 */     if (playerComponent == null)
/* 22 */       return;  if (strings[2].equals("start")) {
/* 23 */       int type = Integer.parseInt(strings[3]);
/* 24 */       int times = Integer.parseInt(strings[4]);
/* 25 */       StartRecruitRequest request = new StartRecruitRequest();
/* 26 */       request.type = type;
/* 27 */       request.times = times;
/* 28 */       (new StartRecruitProcessor()).handle(playerSession, (RequestBase)request);
/* 29 */     } else if (strings[2].equals("setLevel")) {
/* 30 */       short setLevel = Short.parseShort(strings[3]);
/* 31 */       playerComponent.setLevel(setLevel);
/* 32 */       PlayerUtil.updateKeyValueInfo(playerSession, KeyValueConstant.PLAYER_LEVEL.getKey(), playerComponent.getLevel());
/* 33 */     } else if (strings[2].equals("red")) {
/* 34 */       (new RedRecruitInfoProcessor()).handle(playerSession, (RequestBase)new RedRecruitInfoRequest());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\RecruitGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */