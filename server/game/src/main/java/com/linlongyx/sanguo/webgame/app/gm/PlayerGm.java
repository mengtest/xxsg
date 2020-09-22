/*     */ package com.linlongyx.sanguo.webgame.app.gm;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.continuity.ContinuityComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.continuity.ContinuityEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.limit.LimitComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.limit.LimitEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.type.CrossRaceFight;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ChargeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ContinFillingBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ContinFillinglistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LimitActivitylistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TaskTypeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ContinuityParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LimitActParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.continuity.ContinueUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.welfare.MonthCardInfoProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.welfare.MonthCardRewardProcessor;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.MonthCardInfoRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.MonthCardRewardRequest;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import com.linlongyx.sanguo.webgame.service.MentalRankService;
/*     */ import com.linlongyx.sanguo.webgame.service.rank.RankingLevel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ public class PlayerGm
/*     */   implements IGm
/*     */ {
/*     */   public void gm(IPlayerSession playerSession, String[] strings) {
/*  51 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/*     */     
/*  53 */     if (playerComponent == null)
/*  54 */       return;  if (strings[2].equals("level")) {
/*  55 */       int level = Integer.parseInt(strings[3]);
/*  56 */       PlayerUtil.setLevel(playerSession.getPlayer(), level);
/*  57 */     } else if (strings[2].equals("addExp")) {
/*  58 */       int exp = Integer.parseInt(strings[3]);
/*  59 */       PlayerUtil.addExp(playerSession.getPlayer(), exp);
/*  60 */     } else if (strings[2].equals("resetTimes")) {
/*  61 */       ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/*  62 */       extendComponent.setCombatTimes(3);
/*  63 */       extendComponent.setCombatCostTimes(0);
/*  64 */     } else if (strings[2].equals("worldLevel")) {
/*  65 */       int worldLevel = Integer.parseInt(strings[3]);
/*  66 */       RankingLevel.setWorldLevel(worldLevel);
/*  67 */       PlayerUtil.sendWorldLevel();
/*  68 */     } else if (strings[2].equals("recharge")) {
/*  69 */       int id = Integer.parseInt(strings[3]);
/*  70 */       ChargeBean chargeBean = (ChargeBean)JsonTableService.getJsonData(id, ChargeBean.class);
/*  71 */       if (chargeBean != null) {
/*  72 */         PlayerUtil.charge(playerSession.getPlayer(), chargeBean);
/*     */       }
/*  74 */     } else if (strings[2].equals("clearCard")) {
/*  75 */       WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/*  76 */       welfareComponent.setMonthEndTime(new HashMap<>());
/*  77 */       welfareComponent.setMonthGetTime(new HashMap<>());
/*  78 */       welfareComponent.updateCardRate();
/*  79 */     } else if (strings[2].equals("t1")) {
/*  80 */       (new MonthCardInfoProcessor()).handle(playerSession, (RequestBase)new MonthCardInfoRequest());
/*  81 */     } else if (strings[2].equals("t2")) {
/*  82 */       MonthCardRewardRequest rewardRequest = new MonthCardRewardRequest();
/*  83 */       rewardRequest.type = Integer.parseInt(strings[3]);
/*  84 */       (new MonthCardRewardProcessor()).handle(playerSession, (RequestBase)rewardRequest);
/*  85 */     } else if (strings[2].equals("logout")) {
/*  86 */       long playerId = Long.parseLong(strings[3]);
/*  87 */       LookUpService.bePlayerLogoutGM(playerId);
/*  88 */     } else if (strings[2].equals("heFu")) {
/*  89 */       MContext.setHeFu(true);
/*  90 */       ParameterConstant.init();
/*  91 */     } else if (strings[2].equals("noHeFu")) {
/*  92 */       MContext.setHeFu(false);
/*  93 */       ParameterConstant.init();
/*  94 */     } else if (strings[2].equals("platform")) {
/*  95 */       String plat = strings[3];
/*  96 */       MContext.gmSetPlatfrom(plat);
/*  97 */       ParameterConstant.init();
/*  98 */     } else if (strings[2].equals("pvp")) {
/*  99 */       long playerId = 0L;
/* 100 */       if (StringUtils.isNumeric(strings[3])) {
/* 101 */         playerId = Long.parseLong(strings[3]);
/*     */       } else {
/* 103 */         for (Long id : LookUpService.getOnlinePlayer()) {
/* 104 */           PlayerComponent targetComponent = LookUpService.getPlayerComponent(id.longValue());
/* 105 */           if (targetComponent.getPlayerName().contains(strings[3])) {
/* 106 */             playerId = targetComponent.getPlayerId();
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 111 */       if (playerId > 0L) {
/* 112 */         FightRecordResponse response = new FightRecordResponse();
/* 113 */         response.type = 16;
/* 114 */         response.id = playerId + "";
/* 115 */         IPlayer leftPlayer = LookUpService.getByPlayerId(playerComponent.getPlayerId());
/* 116 */         PlayerData rightPlayer = CrossUtil.buildLocalPlayerData(playerId);
/* 117 */         CrossRaceFight crossRaceFight = new CrossRaceFight(leftPlayer, rightPlayer);
/* 118 */         byte result = crossRaceFight.start(response);
/* 119 */         leftPlayer.getSession().sendMessage((ResponseBase)response);
/*     */       } 
/* 121 */     } else if (strings[2].equals("serverId")) {
/* 122 */       String serverId = strings[3];
/* 123 */       MContext.setServerIdInt(serverId);
/* 124 */       ParameterConstant.init();
/* 125 */     } else if (strings[2].equals("contin")) {
/* 126 */       int curDay = Integer.parseInt(strings[3]);
/* 127 */       int todayC = Integer.parseInt(strings[4]);
/* 128 */       ContinuityParameter continuityParameter = (ContinuityParameter)ParameterConstant.getParameter(21);
/* 129 */       ArrayList<Integer> list = continuityParameter.getActId(true);
/* 130 */       if (null == list || list.isEmpty()) {
/*     */         return;
/*     */       }
/* 133 */       ContinuityComponent continuityComponent = (ContinuityComponent)LookUpService.getComponent(playerSession.getPlayer().getPlayerId(), ContinuityComponent.class);
/* 134 */       if (null == continuityComponent) {
/*     */         return;
/*     */       }
/* 137 */       for (Integer id : list) {
/* 138 */         ContinFillingBean continFillingBean = (ContinFillingBean)JsonTableService.getJsonData(id.intValue(), ContinFillingBean.class);
/* 139 */         if (null == continFillingBean) {
/*     */           continue;
/*     */         }
/* 142 */         FestivalTime festivalTime = continuityParameter.getFestivalTime(id.intValue());
/* 143 */         if (null == festivalTime) {
/*     */           continue;
/*     */         }
/* 146 */         ContinuityEntity continuityEntity = continuityComponent.getContinuityEntity(id.intValue());
/* 147 */         Map<Integer, Long> dateCharges = continuityEntity.getDateCharges();
/* 148 */         Map<Integer, Integer> states = continuityEntity.getStates();
/* 149 */         ArrayList<Integer> rankList = continFillingBean.getRankList();
/* 150 */         int type = PlayerUtil.getPlatformType();
/*     */         
/* 152 */         for (Integer tid : rankList) {
/* 153 */           ContinFillinglistBean continFillinglistBean = (ContinFillinglistBean)JsonTableService.getJsonData(tid.intValue(), ContinFillinglistBean.class);
/* 154 */           if (null == continFillinglistBean || ContinueUtil.judgeEntryDone(states, continFillinglistBean) || curDay != continFillinglistBean.getDay()) {
/*     */             continue;
/*     */           }
/* 157 */           if (type != continFillinglistBean.getConnectortype()) {
/*     */             continue;
/*     */           }
/* 160 */           long oldValue = ((Long)dateCharges.getOrDefault(Integer.valueOf(curDay), Long.valueOf(0L))).longValue();
/* 161 */           oldValue = todayC;
/* 162 */           LogUtil.errorLog(new Object[] { "repair11332", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(curDay), Long.valueOf(oldValue), Integer.valueOf(todayC) });
/* 163 */           if (oldValue >= continFillinglistBean.getRmb()) {
/* 164 */             oldValue = continFillinglistBean.getRmb();
/* 165 */             states.put(Integer.valueOf(continFillinglistBean.getId()), Integer.valueOf(1));
/*     */             
/* 167 */             if (!ContinueUtil.judgeTotalDone(states) && 
/* 168 */               ContinueUtil.getTotalNum(states) >= continFillingBean.getCommit()) {
/* 169 */               states.put(Integer.valueOf(-1), Integer.valueOf(1));
/*     */             }
/*     */             
/* 172 */             continuityEntity.setStates(states);
/* 173 */             continuityComponent.updateStatesDB(id.intValue());
/*     */           } 
/* 175 */           dateCharges.put(Integer.valueOf(curDay), Long.valueOf(oldValue));
/* 176 */           continuityEntity.setDateCharges(dateCharges);
/* 177 */           continuityComponent.updateDateChargesDB(id.intValue());
/*     */         } 
/*     */       } 
/* 180 */       continuityComponent.maybeSaveToDB();
/* 181 */     } else if (strings[2].equals("limit")) {
/* 182 */       int type = Integer.parseInt(strings[3]);
/* 183 */       long value = Long.parseLong(strings[4]);
/* 184 */       LimitActParameter limitActParameter = (LimitActParameter)ParameterConstant.getParameter(23);
/* 185 */       LimitComponent limitComponent = (LimitComponent)playerSession.getPlayer().createIfNotExist(LimitComponent.class);
/* 186 */       Set<Integer> sets = limitActParameter.getLimitActivitylist(TaskType.ConsumeCCY.getType());
/*     */       
/* 188 */       if (null == sets || sets.isEmpty()) {
/*     */         return;
/*     */       }
/*     */       
/* 192 */       for (Integer itemId : sets) {
/* 193 */         LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(itemId.intValue(), LimitActivitylistBean.class);
/* 194 */         if (null == limitActivitylistBean || 1 == limitActivitylistBean.getDisplayType()) {
/*     */           continue;
/*     */         }
/*     */         
/* 198 */         Set<Integer> ids = limitActParameter.getIds(itemId.intValue());
/* 199 */         if (null == ids || ids.isEmpty()) {
/*     */           continue;
/*     */         }
/* 202 */         for (Integer id1 : ids) {
/* 203 */           LimitEntity limitEntity = limitComponent.getEntity(id1.intValue());
/* 204 */           if (!limitEntity.isOpen()) {
/*     */             continue;
/*     */           }
/* 207 */           long defaultValue = 0L;
/* 208 */           int id = limitActivitylistBean.getId();
/* 209 */           int targetType = limitActivitylistBean.getTargetType();
/* 210 */           TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(targetType, TaskTypeBean.class);
/* 211 */           if (null == taskTypeBean) {
/*     */             return;
/*     */           }
/* 214 */           Map<Integer, Long> values = limitEntity.getValues();
/* 215 */           boolean isDone = false;
/* 216 */           values.put(Integer.valueOf(id), Long.valueOf(value));
/* 217 */           isDone = (((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() >= limitActivitylistBean.getTarget());
/* 218 */           if (isDone) {
/* 219 */             limitComponent.done(limitEntity, limitActivitylistBean);
/*     */           }
/* 221 */           limitEntity.setValues(values);
/* 222 */           limitComponent.updateValuesDB(limitEntity.getId());
/*     */         } 
/*     */       } 
/* 225 */     } else if (strings[2].equals("task")) {
/* 226 */       TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 227 */       int type = Integer.parseInt(strings[3]);
/* 228 */       int target = Integer.parseInt(strings[4]);
/* 229 */       int value = Integer.parseInt(strings[5]);
/* 230 */       TaskType taskType = TaskType.getTaskType(type);
/* 231 */       taskComponent.refreshSchedule(taskType, target, value);
/*     */     }
/* 233 */     else if (strings[2].equals("clearMental")) {
/* 234 */       MentalRankService rankService = (MentalRankService)MContext.getBean("mentalRankService");
/* 235 */       rankService.component.getRankList().clear();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\PlayerGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */