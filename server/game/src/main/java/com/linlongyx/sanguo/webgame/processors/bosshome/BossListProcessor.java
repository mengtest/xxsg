/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.BossListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.BossListResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BossData;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class BossListProcessor
/*    */   extends ProcessorBase<BossListRequest, BossListResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new BossListResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BossListRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 301)) {
/* 31 */       return 10061;
/*    */     }
/* 33 */     int type = request.type;
/* 34 */     BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/* 35 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 36 */     Map<Integer, Integer> counts = bossHomeComponent.getCounts();
/* 37 */     Map<Integer, Integer> times = bossHomeComponent.getTimes();
/* 38 */     Map<Integer, Integer> restores = bossHomeComponent.getRestores();
/* 39 */     Map<Integer, Integer> buys = bossHomeComponent.getBuys();
/*    */     
/* 41 */     int currentTime = TimeUtil.currentTime();
/* 42 */     if (1 == type) {
/* 43 */       int fightCount = ((Integer)counts.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 44 */       int time = ((Integer)times.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 45 */       int restore = ((Integer)restores.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 46 */       int maxCount = bossHomeParameter.getRankMaxCount();
/* 47 */       int buyCount = ((Integer)buys.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 48 */       int leftCount = maxCount + restore + buyCount - fightCount;
/* 49 */       int rankRestoreCD = bossHomeParameter.getRankRestoreCD();
/* 50 */       if (leftCount > maxCount) {
/* 51 */         restore = fightCount - buyCount;
/* 52 */         time = 0;
/* 53 */         leftCount = maxCount + restore + buyCount - fightCount;
/* 54 */         times.put(Integer.valueOf(type), Integer.valueOf(time));
/* 55 */         restores.put(Integer.valueOf(type), Integer.valueOf(restore));
/* 56 */         bossHomeComponent.setRestores(restores);
/* 57 */         bossHomeComponent.setTimes(times);
/*    */       } 
/* 59 */       if (time > 0) {
/* 60 */         int delta = currentTime - time;
/* 61 */         if (delta > 0) {
/* 62 */           time = currentTime + rankRestoreCD - delta % rankRestoreCD;
/* 63 */           restore += delta / rankRestoreCD + 1;
/* 64 */           leftCount = maxCount + restore + buyCount - fightCount;
/* 65 */           if (leftCount >= maxCount) {
/* 66 */             restore = fightCount - buyCount;
/* 67 */             time = 0;
/* 68 */             leftCount = maxCount + restore + buyCount - fightCount;
/*    */           } 
/* 70 */           times.put(Integer.valueOf(type), Integer.valueOf(time));
/* 71 */           restores.put(Integer.valueOf(type), Integer.valueOf(restore));
/* 72 */           bossHomeComponent.setRestores(restores);
/* 73 */           bossHomeComponent.setTimes(times);
/*    */         } 
/*    */       } 
/*    */       
/* 77 */       ((BossListResponse)this.response).fightTimes = leftCount;
/* 78 */       ((BossListResponse)this.response).time = time;
/* 79 */       ((BossListResponse)this.response).buyTime = buyCount;
/* 80 */       List<Integer> rankBossList = bossHomeParameter.getBossList(type);
/* 81 */       for (Integer insId : rankBossList) {
/* 82 */         BossData bossData = BossUtil.getBossData(insId.intValue());
/* 83 */         ((BossListResponse)this.response).datas.add(bossData);
/*    */       } 
/*    */     } 
/* 86 */     ((BossListResponse)this.response).type = type;
/* 87 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 88 */     if (null != taskComponent) {
/* 89 */       taskComponent.refreshSchedule(TaskType.OpenRankBossPanel, 0, 1L);
/*    */     }
/* 91 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\BossListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */