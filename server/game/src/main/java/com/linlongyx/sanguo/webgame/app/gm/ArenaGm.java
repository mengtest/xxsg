/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.app.arena.ArenaComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ArenaRuleBean;
/*    */ import com.linlongyx.sanguo.webgame.net.http.answer.ArenaRewardEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.arena.ArenaBuyTimesProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.arena.ArenaReportProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.arena.ArenaSweepProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.arena.ArenaUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.fight.FightRecordProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.arena.ArenaBuyTimesRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.arena.ArenaInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.arena.ArenaReportRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.arena.ArenaSweepRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ArenaData;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ArenaGm implements IGm {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 22 */     ArenaComponent arenaComponent = (ArenaComponent)playerSession.getPlayer().createIfNotExist(ArenaComponent.class);
/* 23 */     if (strings[2].equals("reward")) {
/* 24 */       ArenaRewardEvent arenaRewardEvent = new ArenaRewardEvent();
/* 25 */       arenaRewardEvent.process(null);
/* 26 */     } else if (strings[2].equals("exchange")) {
/* 27 */       int targetRank = Integer.valueOf(strings[3]).intValue();
/* 28 */       ArenaRuleBean arenaRuleBean = ArenaUtil.getArenaRuleBean(arenaComponent.getRank());
/* 29 */       Map<Integer, Integer> showMap = arenaComponent.getShowMap();
/* 30 */       ArenaData arenaData = ArenaUtil.getArenaData(targetRank, arenaRuleBean);
/* 31 */       if (null == arenaData) {
/*    */         return;
/*    */       }
/* 34 */       if (arenaData.isRobot) {
/* 35 */         showMap.put(Integer.valueOf(arenaData.rank), Integer.valueOf(arenaData.objectId));
/*    */       }
/* 37 */       FightRecordRequest recordRequest = new FightRecordRequest();
/* 38 */       recordRequest.id = targetRank + "";
/* 39 */       recordRequest.type = 4;
/* 40 */       (new FightRecordProcessor()).handle(playerSession, (RequestBase)recordRequest);
/* 41 */     } else if (strings[2].equals("resetTimes")) {
/* 42 */       arenaComponent.setBuyTimes(0);
/* 43 */       arenaComponent.setFightTimes(0);
/*    */     
/*    */     }
/* 46 */     else if (strings[2].equals("t1")) {
/* 47 */       (new ArenaInfoProcessor()).handle(playerSession, (RequestBase)new ArenaInfoRequest());
/* 48 */     } else if (strings[2].equals("t2")) {
/* 49 */       (new ArenaBuyTimesProcessor()).handle(playerSession, (RequestBase)new ArenaBuyTimesRequest());
/* 50 */     } else if (strings[2].equals("t4")) {
/* 51 */       (new ArenaReportProcessor()).handle(playerSession, (RequestBase)new ArenaReportRequest());
/* 52 */     } else if (strings[2].equals("t5")) {
/* 53 */       ArenaSweepRequest request = new ArenaSweepRequest();
/* 54 */       request.rank = Integer.valueOf(strings[3]).intValue();
/* 55 */       (new ArenaSweepProcessor()).handle(playerSession, (RequestBase)request);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\ArenaGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */