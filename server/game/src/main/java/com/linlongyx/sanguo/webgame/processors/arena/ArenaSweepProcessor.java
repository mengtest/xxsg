/*    */ package com.linlongyx.sanguo.webgame.processors.arena;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.arena.ArenaComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ArenaRuleBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ArenaParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.MilitaryInsType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.processors.offices.MilitaryUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.task.FindRewardType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.arena.ArenaSweepRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.arena.ArenaSweepResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ArenaSweepProcessor
/*    */   extends ProcessorBase<ArenaSweepRequest, ArenaSweepResponse> {
/*    */   protected void initResponse() {
/* 28 */     this.response = (ResponseBase)new ArenaSweepResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ArenaSweepRequest request) {
/* 33 */     int rank = request.rank;
/* 34 */     int num = request.num;
/* 35 */     if (num <= 0) {
/* 36 */       return 10090;
/*    */     }
/* 38 */     ArenaComponent arenaComponent = (ArenaComponent)playerSession.getPlayer().createIfNotExist(ArenaComponent.class);
/* 39 */     if (rank <= arenaComponent.getRank()) {
/* 40 */       return 11710;
/*    */     }
/* 42 */     ArenaParameter arenaParameter = (ArenaParameter)ParameterConstant.getParameter(17);
/* 43 */     int buyTimes = arenaComponent.getBuyTimes();
/* 44 */     int fightTimes = arenaComponent.getFightTimes();
/* 45 */     if (fightTimes + num > arenaParameter.getMaxChallengeTime() + buyTimes) {
/* 46 */       return 11707;
/*    */     }
/* 48 */     ArenaRuleBean arenaRuleBean = ArenaUtil.getArenaRuleBean(rank);
/* 49 */     if (null == arenaRuleBean) {
/* 50 */       return 11701;
/*    */     }
/* 52 */     fightTimes += num;
/* 53 */     arenaComponent.setFightTimes(fightTimes);
/* 54 */     ArrayList<Reward> list = new ArrayList<>();
/* 55 */     for (int i = 0; i < num; i++) {
/* 56 */       list.addAll(FinanceUtil.transform(arenaRuleBean.getWinReward()));
/*    */     }
/*    */     
/* 59 */     MilitaryUtil.getRewardResult(list, MilitaryInsType.ArenaIns.getType(), playerSession.getPlayer().getPlayerId(), 0);
/* 60 */     ((ArenaSweepResponse)this.response).list = FinanceUtil.rewardGet(list, playerSession.getPlayer(), ResourceEvent.ArenaSweep, true);
/* 61 */     ((ArenaSweepResponse)this.response).fightTimes = arenaParameter.getMaxChallengeTime() + arenaComponent.getBuyTimes() - fightTimes;
/* 62 */     ((ArenaSweepResponse)this.response).num = num;
/* 63 */     ((ArenaSweepResponse)this.response).rank = rank;
/* 64 */     arenaComponent.setTotalFightTimes(arenaComponent.getTotalFightTimes() + num);
/* 65 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 66 */     taskComponent.refreshSchedule(TaskType.ChallengeArena, 0, num);
/* 67 */     taskComponent.refreshSchedule(TaskType.TotalFightTimes, 0, num);
/* 68 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/* 69 */     singleInsComponent.saveFindTimes(true, FindRewardType.ARENA.getType(), num);
/* 70 */     LogUtils.errorLog(new Object[] { "ArenaSweep", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(num), Integer.valueOf(fightTimes), Integer.valueOf(((ArenaSweepResponse)this.response).fightTimes) });
/* 71 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\arena\ArenaSweepProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */