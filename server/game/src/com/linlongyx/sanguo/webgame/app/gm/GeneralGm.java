/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*    */ import com.linlongyx.sanguo.webgame.processors.general.GeneralBuyOrderProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralBoxRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralBuyOrderRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralChapterInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralPointResetRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralPointSweepRequest;
/*    */ 
/*    */ public class GeneralGm implements IGm {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 14 */     if (strings[2].equals("t1")) {
/* 15 */       (new GeneralTotalInfoProcessor()).handle(playerSession, (RequestBase)new GeneralTotalInfoRequest());
/* 16 */     } else if (strings[2].equals("t2")) {
/* 17 */       GeneralChapterInfoRequest request = new GeneralChapterInfoRequest();
/* 18 */       request.chapter = Integer.valueOf(strings[3]).intValue();
/* 19 */       (new GeneralChapterInfoProcessor()).handle(playerSession, (RequestBase)request);
/* 20 */     } else if (strings[2].equals("t3")) {
/* 21 */       GeneralPointSweepRequest request = new GeneralPointSweepRequest();
/* 22 */       request.chapter = Integer.valueOf(strings[3]).intValue();
/* 23 */       request.point = Integer.valueOf(strings[4]).intValue();
/* 24 */       request.num = Integer.valueOf(strings[5]).intValue();
/* 25 */       (new GeneralPointSweepProcessor()).handle(playerSession, (RequestBase)request);
/* 26 */     } else if (strings[2].equals("t4")) {
/* 27 */       GeneralBoxRewardRequest request = new GeneralBoxRewardRequest();
/* 28 */       request.chapter = Integer.valueOf(strings[3]).intValue();
/* 29 */       request.box = Integer.valueOf(strings[4]).intValue();
/* 30 */       (new GeneralBoxRewardProcessor()).handle(playerSession, (RequestBase)request);
/* 31 */     } else if (strings[2].equals("t5")) {
/* 32 */       GeneralBuyOrderRequest request = new GeneralBuyOrderRequest();
/* 33 */       request.time = Integer.valueOf(strings[3]).intValue();
/* 34 */       (new GeneralBuyOrderProcessor()).handle(playerSession, (RequestBase)request);
/* 35 */     } else if (strings[2].equals("t6")) {
/* 36 */       GeneralPointResetRequest request = new GeneralPointResetRequest();
/* 37 */       request.chapter = Integer.valueOf(strings[3]).intValue();
/* 38 */       request.point = Integer.valueOf(strings[4]).intValue();
/* 39 */       (new GeneralPointResetProcessor()).handle(playerSession, (RequestBase)request);
/* 40 */     } else if (strings[2].equals("t7")) {
/* 41 */       int time = Integer.valueOf(strings[3]).intValue();
/* 42 */       GeneralComponent generalComponent = (GeneralComponent)playerSession.getPlayer().createIfNotExist(GeneralComponent.class);
/* 43 */       generalComponent.setBuyTime(generalComponent.getBuyTime() + time);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\GeneralGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */