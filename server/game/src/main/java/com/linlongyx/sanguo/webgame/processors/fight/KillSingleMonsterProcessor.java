/*    */ package com.linlongyx.sanguo.webgame.processors.fight;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MapTableBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.FightParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.KillSingleMonsterRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.KillSingleMonsterResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KillSingleMonsterProcessor
/*    */   extends ProcessorBase<KillSingleMonsterRequest, KillSingleMonsterResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 30 */     this.response = (ResponseBase)new KillSingleMonsterResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, KillSingleMonsterRequest request) {
/* 35 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 36 */     FightParameter fightParameter = (FightParameter)ParameterConstant.getParameter(1);
/* 37 */     int preKillMonsterTime = playerComponent.getPreKillMonsterTime();
/* 38 */     int time = TimeUtil.currentTime();
/* 39 */     if (0 == preKillMonsterTime || time - preKillMonsterTime >= fightParameter.getKillMonsterCD()) {
/* 40 */       playerComponent.setPreKillMonsterTime(time);
/*    */       
/* 42 */       TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 43 */       taskComponent.refreshSchedule(TaskType.KillSingleMonster, 0, 1L);
/*    */       
/* 45 */       int sceneId = playerComponent.getMainSceneId();
/* 46 */       MapTableBean mapTableBean = (MapTableBean)JsonTableService.getJsonData(sceneId, MapTableBean.class);
/* 47 */       if (null != mapTableBean && mapTableBean.getMapIncome().size() > 0) {
/* 48 */         WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 49 */         ArrayList<Reward> list = FinanceUtil.transform(mapTableBean.getMapIncome());
/* 50 */         double monthCardRate = welfareComponent.getMonthCardRate();
/* 51 */         if (monthCardRate > 0.0D) {
/* 52 */           for (Reward reward : list) {
/* 53 */             reward.num = (long)(reward.num + reward.num * monthCardRate);
/*    */           }
/*    */         }
/* 56 */         FinanceUtil.reward(list, playerSession.getPlayer(), ResourceEvent.OnlineIncome, true);
/*    */       } 
/*    */     } 
/*    */     
/* 60 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\fight\KillSingleMonsterProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */