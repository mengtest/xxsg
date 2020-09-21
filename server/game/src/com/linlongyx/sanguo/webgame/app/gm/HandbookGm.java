/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.processors.partner.GetHandbookRewardProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerHandbookInfoProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerStarUpProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.GetHandbookRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerHandbookInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerStarUpRequest;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HandbookGm
/*    */   implements IGm
/*    */ {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 19 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/* 20 */     if (playerComponent == null)
/* 21 */       return;  if (strings[2].equals("getReward")) {
/* 22 */       int type = Integer.parseInt(strings[3]);
/* 23 */       GetHandbookRewardRequest request = new GetHandbookRewardRequest();
/* 24 */       request.type = type;
/* 25 */       (new GetHandbookRewardProcessor()).handle(playerSession, (RequestBase)request);
/* 26 */     } else if (strings[2].equals("getInfo")) {
/* 27 */       PartnerHandbookInfoRequest request = new PartnerHandbookInfoRequest();
/* 28 */       (new PartnerHandbookInfoProcessor()).handle(playerSession, (RequestBase)request);
/* 29 */     } else if (strings[2].equals("UpStar")) {
/* 30 */       PartnerStarUpRequest request = new PartnerStarUpRequest();
/* 31 */       long pid = Long.parseLong(strings[3]);
/* 32 */       request.pid = pid;
/* 33 */       (new PartnerStarUpProcessor()).handle(playerSession, (RequestBase)request);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\HandbookGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */