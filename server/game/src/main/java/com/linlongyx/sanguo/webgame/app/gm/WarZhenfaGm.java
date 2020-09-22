/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.app.mental.MentalComponent;
/*    */ import com.linlongyx.sanguo.webgame.processors.warlineup.ActivityWarLineupProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.ActivityWarLineupRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.WarLineupBattleRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.WarLineupInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.WarLineupStarUpRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.WarLineuprUpgradeRequest;
/*    */ import com.linlongyx.sanguo.webgame.service.MentalRankService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class WarZhenfaGm implements IGm {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 16 */     if (strings[2].equals("act")) {
/* 17 */       ActivityWarLineupRequest request = new ActivityWarLineupRequest();
/* 18 */       request.warlineup = Integer.valueOf(strings[3]).intValue();
/* 19 */       (new ActivityWarLineupProcessor()).handle(playerSession, (RequestBase)request);
/* 20 */     } else if (strings[2].equals("info")) {
/* 21 */       WarLineupInfoRequest request = new WarLineupInfoRequest();
/* 22 */       (new WarLineupInfoProcessor()).handle(playerSession, (RequestBase)request);
/* 23 */     } else if (strings[2].equals("battle")) {
/* 24 */       WarLineupBattleRequest request = new WarLineupBattleRequest();
/* 25 */       request.warlineup = Integer.valueOf(strings[3]).intValue();
/* 26 */       (new WarLineupBattleProcessor()).handle(playerSession, (RequestBase)request);
/* 27 */     } else if (strings[2].equals("upstar")) {
/* 28 */       WarLineupStarUpRequest request = new WarLineupStarUpRequest();
/* 29 */       request.warlineup = Integer.valueOf(strings[3]).intValue();
/* 30 */       (new WarLineupStarUpProcessor()).handle(playerSession, (RequestBase)request);
/* 31 */     } else if (strings[2].equals("uplevel")) {
/* 32 */       WarLineuprUpgradeRequest request = new WarLineuprUpgradeRequest();
/* 33 */       int s = strings.length;
/* 34 */       request.warlineup = Integer.valueOf(strings[3]).intValue();
/* 35 */       ArrayList<Integer> itemList = new ArrayList<>();
/* 36 */       itemList.add(Integer.valueOf(strings[4]));
/* 37 */       request.itemId = itemList;
/* 38 */       ArrayList<Integer> itemNums = new ArrayList<>();
/* 39 */       itemNums.add(Integer.valueOf(strings[5]));
/* 40 */       request.ItemNum = itemNums;
/* 41 */       (new WarLineuprUpgradeProcessor()).handle(playerSession, (RequestBase)request);
/*    */     }
/* 43 */     else if (strings[2].equals("clear")) {
/* 44 */       MentalRankService rankService = (MentalRankService)MContext.getBean("mentalRankService");
/* 45 */       if (rankService != null) {
/* 46 */         rankService.component.setShowList(new ArrayList());
/*    */       }
/* 48 */     } else if (strings[2].equals("cReward")) {
/* 49 */       MentalComponent mentalComponent = (MentalComponent)playerSession.getPlayer().createIfNotExist(MentalComponent.class);
/* 50 */       mentalComponent.setRewardIds(new HashSet());
/* 51 */     } else if (strings[2].equals("cPoint")) {
/* 52 */       MentalComponent mentalComponent = (MentalComponent)playerSession.getPlayer().createIfNotExist(MentalComponent.class);
/* 53 */       mentalComponent.setPoint(0);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\WarZhenfaGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */