/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.WorldBossFightSide;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldInfoResponse;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldInfoProcessor
/*    */   extends ProcessorBase<WorldInfoRequest, WorldInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new WorldInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WorldInfoRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 302)) {
/* 31 */       return 10061;
/*    */     }
/* 33 */     int type = 2;
/* 34 */     BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/* 35 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/*    */     
/* 37 */     WorldBossFightSide worldBossFightSide = WorldBossUtil.getWorldBossFightSide();
/* 38 */     if (worldBossFightSide == null) {
/* 39 */       return 0;
/*    */     }
/* 41 */     Map<Integer, Integer> counts = bossHomeComponent.getCounts();
/* 42 */     Map<Integer, Integer> times = bossHomeComponent.getTimes();
/* 43 */     int fightCount = ((Integer)counts.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 44 */     int time = ((Integer)times.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 45 */     int currentTime = TimeUtil.currentTime();
/* 46 */     int worldNormalCount = bossHomeParameter.getWorldNormalCount();
/* 47 */     int worldRestoreCD = bossHomeParameter.getWorldRestoreCD();
/* 48 */     Map<Integer, Integer> buys = bossHomeComponent.getBuys();
/* 49 */     Map<Integer, Integer> restores = bossHomeComponent.getRestores();
/* 50 */     int buyCount = ((Integer)buys.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 51 */     int restoreCount = ((Integer)restores.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 52 */     if (worldBossFightSide.isOpen()) {
/* 53 */       if (0 == time) {
/* 54 */         if (worldBossFightSide.getOpenTime() > 0) {
/* 55 */           time = worldBossFightSide.getOpenTime();
/*    */         } else {
/* 57 */           time = currentTime + bossHomeParameter.getWorldRestoreCD();
/*    */         } 
/* 59 */         times.put(Integer.valueOf(type), Integer.valueOf(time));
/* 60 */         bossHomeComponent.setTimes(times);
/*    */       } 
/* 62 */       if (time > 0) {
/* 63 */         int delta = currentTime - time;
/* 64 */         if (delta > 0) {
/* 65 */           restoreCount += delta / worldRestoreCD + 1;
/* 66 */           time = currentTime + worldRestoreCD - delta % worldRestoreCD;
/* 67 */           times.put(Integer.valueOf(type), Integer.valueOf(time));
/* 68 */           restores.put(Integer.valueOf(type), Integer.valueOf(restoreCount));
/* 69 */           bossHomeComponent.setTimes(times);
/* 70 */           bossHomeComponent.setRestores(restores);
/*    */         } 
/*    */       } 
/* 73 */       ((WorldInfoResponse)this.response).time = time + 1;
/*    */     } 
/* 75 */     ((WorldInfoResponse)this.response).fightTimes = worldNormalCount + buyCount + restoreCount - fightCount;
/* 76 */     ((WorldInfoResponse)this.response).buyTime = buyCount;
/* 77 */     worldBossFightSide.getResponse(playerSession.getPlayer().getPlayerId(), (WorldInfoResponse)this.response);
/* 78 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\WorldInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */