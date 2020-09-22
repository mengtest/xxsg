/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*    */ import com.linlongyx.sanguo.webgame.processors.fight.FightRecordProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.unparalleled.GetAttrbuteInfoProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.CheckAccountRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.GetAttrbuteInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.GetBoxRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.GetUnparalleledInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.GetUnparalleledRankRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.ResetUnparalleledRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.SelectUnparalleledAttrbuteRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.SweepUnparalleledRequest;
/*    */ 
/*    */ public class UnparalleledInsGm implements IGm {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 19 */     UnparalleledComponent unparalleledComponent = (UnparalleledComponent)playerSession.getPlayer().createIfNotExist(UnparalleledComponent.class);
/* 20 */     if (strings[2].equals("t1")) {
/* 21 */       GetUnparalleledInfoRequest request = new GetUnparalleledInfoRequest();
/* 22 */       (new GetUnparalleledInfoProcessor()).handle(playerSession, (RequestBase)request);
/* 23 */     } else if (strings[2].equals("sweep")) {
/* 24 */       SweepUnparalleledRequest request = new SweepUnparalleledRequest();
/* 25 */       (new SweepUnparalleledProcessor()).handle(playerSession, (RequestBase)request);
/* 26 */     } else if (strings[2].equals("fight")) {
/* 27 */       FightRecordRequest recordRequest = new FightRecordRequest();
/* 28 */       recordRequest.type = Byte.valueOf(strings[3]).byteValue();
/* 29 */       recordRequest.id = strings[4];
/* 30 */       (new FightRecordProcessor()).handle(playerSession, (RequestBase)recordRequest);
/* 31 */     } else if (strings[2].equals("rank")) {
/* 32 */       GetUnparalleledRankRequest request = new GetUnparalleledRankRequest();
/* 33 */       (new GetUnparalleledRankProcessor()).handle(playerSession, (RequestBase)request);
/* 34 */     } else if (strings[2].equals("reset")) {
/* 35 */       ResetUnparalleledRequest request = new ResetUnparalleledRequest();
/* 36 */       (new ResetUnparalleledProcessor()).handle(playerSession, (RequestBase)request);
/* 37 */     } else if (strings[2].equals("reward")) {
/* 38 */       GetBoxRewardRequest request = new GetBoxRewardRequest();
/* 39 */       request.boxId = Integer.parseInt(strings[3]);
/* 40 */       (new GetBoxRewardProcessor()).handle(playerSession, (RequestBase)request);
/* 41 */     } else if (strings[2].equals("info")) {
/* 42 */       GetAttrbuteInfoRequest request = new GetAttrbuteInfoRequest();
/* 43 */       request.insId = Integer.parseInt(strings[3]);
/* 44 */       (new GetAttrbuteInfoProcessor()).handle(playerSession, (RequestBase)request);
/* 45 */     } else if (strings[2].equals("select")) {
/* 46 */       SelectUnparalleledAttrbuteRequest request = new SelectUnparalleledAttrbuteRequest();
/* 47 */       request.insId = Integer.parseInt(strings[3]);
/* 48 */       request.id = Integer.parseInt(strings[4]);
/* 49 */       (new SelectUnparalleledAttrbuteProcessor()).handle(playerSession, (RequestBase)request);
/* 50 */     } else if (strings[2].equals("clear")) {
/* 51 */       unparalleledComponent.reset(0);
/* 52 */     } else if (strings[2].equals("login")) {
/* 53 */       CheckAccountRequest request = new CheckAccountRequest();
/* 54 */       request.accountId = strings[3];
/* 55 */       request.pwd = strings[4];
/* 56 */       (new CheckAccountProcessor()).handle(playerSession, (RequestBase)request);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\UnparalleledInsGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */