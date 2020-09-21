/*     */ package com.linlongyx.sanguo.webgame.app.task;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DailyBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FunctionPredictBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MainTaskBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecordStarBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TaskTypeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TaskParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.KeyValueConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.RankActType;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.consume.ConsumeUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.sanguozhi.SanGuoZhiUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.task.FindRewardType;
/*     */ import com.linlongyx.sanguo.webgame.processors.task.TaskUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.zodiac.ZodiacUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FindRewardData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskCompleteNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class TaskComponent extends AbstractComponent<TaskEntity> {
/*     */   public TaskComponent(long playerId) {
/*  44 */     super(TaskEntity.class, playerId);
/*  45 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */   private int tempGuide;
/*     */   
/*     */   public String getType() {
/*  50 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  55 */     setPlayerId(playerId);
/*  56 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*  57 */     setId(loginParameter.getDefaultTaskId());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  62 */     if (time == 0) {
/*  63 */       setPoint(0);
/*  64 */       setDailyProcess(new HashMap<>());
/*  65 */       setDailyDone(new HashSet<>());
/*  66 */       setDailyReward(new HashSet<>());
/*  67 */       setPointReward(new HashSet<>());
/*  68 */       getPlatformReward().put(Integer.valueOf(6), Integer.valueOf(0));
/*  69 */       setPlatformReward(getPlatformReward());
/*  70 */       getPlatformReward().put(Integer.valueOf(7), Integer.valueOf(0));
/*  71 */       setPlatformReward(getPlatformReward());
/*  72 */       getPlatformReward().put(Integer.valueOf(8), Integer.valueOf(0));
/*  73 */       setPlatformReward(getPlatformReward());
/*     */       
/*  75 */       resetActPlay();
/*  76 */       savePreMax();
/*     */     } 
/*  78 */     return true;
/*     */   }
/*     */   
/*     */   public void refreshSchedule(TaskType taskType, int targetId, long value) {
/*  82 */     refreshMainTask(taskType, targetId, value);
/*  83 */     refreshDaily(taskType, targetId, value);
/*  84 */     AchieveUtil.refreshAchieve(taskType, targetId, value, this.playerId);
/*  85 */     updatePreviewTask(taskType, targetId, value);
/*  86 */     if (null != LookUpService.getByPlayerId(getPlayerId())) {
/*  87 */       IPlayerSession playerSession = LookUpService.getByPlayerId(getPlayerId()).getSession();
/*  88 */       Map<Integer, Object> starMap = JsonTableService.getJsonTable(RecordStarBean.class);
/*  89 */       for (Iterator<Integer> iterator = starMap.keySet().iterator(); iterator.hasNext(); ) { int recordStar = ((Integer)iterator.next()).intValue();
/*  90 */         SanGuoZhiUtil.updateRecord(playerSession, recordStar, targetId, value, taskType); }
/*     */     
/*     */     } 
/*  93 */     LimitUtil.addFestValue(taskType.getType(), value, this.playerId, targetId);
/*  94 */     ZodiacUtil.addZodiacValue(this.playerId, taskType.getType(), targetId, value);
/*  95 */     ChargeRebateUtil.addChargeValue(taskType.getType(), value, this.playerId);
/*  96 */     if (taskType.getType() == TaskType.ConsumeCCY.getType()) {
/*  97 */       ExtendComponent extendComponent = (ExtendComponent)LookUpService.getComponent(this.playerId, ExtendComponent.class);
/*  98 */       RankActUtil.refreshRankValue(RankActType.Cost.getType(), value, this.playerId);
/*  99 */       RankActUtil.refreshRankValue(RankActType.Cost2.getType(), value, this.playerId);
/* 100 */       CrossRankActUtil.refreshRankValue(CrossRankActType.Cost.getType(), value, this.playerId);
/*     */       
/* 102 */       ConsumeUtil.updateConsumeRebate(this.playerId, value);
/*     */       
/* 104 */       LuckyMoneyUtil.addLucky(this.playerId, value);
/* 105 */       extendComponent.setDailyConsume(extendComponent.getDailyConsume() + value);
/*     */     } 
/* 107 */     PartnerUtil.updateReincarn(taskType, value, this.playerId, targetId);
/*     */   }
/*     */   
/*     */   public void refreshDailySchedule(TaskType taskType, int targetId, long value) {
/* 111 */     refreshDaily(taskType, targetId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void refreshMainTask(TaskType taskType, int targetId, long value) {
/* 122 */     MainTaskBean mainTaskBean = (MainTaskBean)JsonTableService.getJsonData(getId(), MainTaskBean.class);
/* 123 */     TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(taskType.getType(), TaskTypeBean.class);
/* 124 */     if (null == taskTypeBean || null == mainTaskBean || mainTaskBean.getPara() != taskType.getType()) {
/*     */       return;
/*     */     }
/* 127 */     boolean isDone = TaskUtil.isTaskDone(getSchedule(), mainTaskBean.getNum(), taskTypeBean.getCount());
/* 128 */     if (isDone) {
/*     */       return;
/*     */     }
/* 131 */     PlayerComponent playerComponent = LookUpService.getPlayerComponent(getPlayerId());
/* 132 */     if (mainTaskBean.getTargeId() == 0 || targetId == 0 || targetId == mainTaskBean.getTargeId()) {
/* 133 */       if (taskTypeBean.getCountType() == 0) {
/* 134 */         value = TaskUtil.getSchedule(this.playerId, mainTaskBean.getPara(), mainTaskBean.getTargeId());
/*     */       } else {
/* 136 */         value += getSchedule();
/*     */       } 
/* 138 */       isDone = TaskUtil.isTaskDone(value, mainTaskBean.getNum(), taskTypeBean.getCount());
/* 139 */       if (isDone) {
/* 140 */         setSchedule(mainTaskBean.getNum());
/* 141 */         if (null != playerComponent)
/* 142 */           LogUtil.gameLog(LogType.TASK, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(getId()), Integer.valueOf(0), TimeUtil.getFormatDate() }); 
/*     */       } else {
/* 144 */         setSchedule(value);
/*     */       } 
/*     */     } 
/* 147 */     if (isDone && mainTaskBean.getMainScene() > 0)
/*     */     {
/* 149 */       if (null != playerComponent) {
/* 150 */         playerComponent.setMainSceneId(mainTaskBean.getMainScene());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void refreshDaily(TaskType taskType, int targetId, long value) {
/* 163 */     TaskParameter taskParameter = (TaskParameter)ParameterConstant.getParameter(22);
/* 164 */     List<Integer> list = taskParameter.getDaily(taskType.getType());
/* 165 */     if (null == list || list.isEmpty()) {
/*     */       return;
/*     */     }
/* 168 */     TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(taskType.getType(), TaskTypeBean.class);
/* 169 */     if (null == taskTypeBean) {
/*     */       return;
/*     */     }
/* 172 */     Set<Integer> dailyDone = getDailyDone();
/* 173 */     Map<Integer, Long> dailyProcess = getDailyProcess();
/* 174 */     int playerLevel = 0;
/* 175 */     PlayerComponent playerComponent = LookUpService.getPlayerComponent(getPlayerId());
/* 176 */     if (null != playerComponent) {
/* 177 */       playerLevel = playerComponent.getLevel();
/*     */     }
/* 179 */     boolean dirty1 = false;
/* 180 */     boolean dirty2 = false;
/* 181 */     for (Integer id : list) {
/* 182 */       if (dailyDone.contains(id)) {
/*     */         continue;
/*     */       }
/* 185 */       DailyBean dailyBean = (DailyBean)JsonTableService.getJsonData(id.intValue(), DailyBean.class);
/* 186 */       if (null == dailyBean || playerLevel < dailyBean.getLevel()) {
/*     */         continue;
/*     */       }
/* 189 */       if (dailyBean.getTargeId() != 0 && targetId != dailyBean.getTargeId()) {
/*     */         continue;
/*     */       }
/* 192 */       long process = ((Long)dailyProcess.getOrDefault(id, Long.valueOf(0L))).longValue();
/* 193 */       if (taskTypeBean.getCountType() == 0) {
/* 194 */         process = TaskUtil.getSchedule(this.playerId, taskType.getType(), dailyBean.getTargeId());
/*     */       } else {
/* 196 */         process += value;
/*     */       } 
/* 198 */       boolean isDone = TaskUtil.isTaskDone(process, dailyBean.getNum(), taskTypeBean.getCount());
/* 199 */       if (isDone) {
/* 200 */         process = dailyBean.getNum();
/* 201 */         dailyDone.add(id);
/* 202 */         dirty1 = true;
/*     */ 
/*     */         
/* 205 */         TaskCompleteNoticeResponse noticeResp = new TaskCompleteNoticeResponse();
/* 206 */         noticeResp.type = taskType.getType();
/* 207 */         noticeResp.id = id.intValue();
/* 208 */         if (LookUpService.isOnline(getPlayerId())) {
/* 209 */           LookUpService.getByPlayerId(getPlayerId()).getSession().sendMessage((ResponseBase)noticeResp);
/*     */         }
/*     */       } 
/* 212 */       dailyProcess.put(id, Long.valueOf(process));
/* 213 */       dirty2 = true;
/*     */     } 
/* 215 */     if (dirty1) {
/* 216 */       setDailyDone(dailyDone);
/*     */     }
/* 218 */     if (dirty2) {
/* 219 */       setDailyProcess(dailyProcess);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updatePreviewTask(TaskType taskType, int targetId, long value) {
/* 231 */     Map<Integer, Object> map = JsonTableService.getJsonTable(FunctionPredictBean.class);
/* 232 */     for (Object o : map.values()) {
/* 233 */       FunctionPredictBean functionPredictBean = (FunctionPredictBean)o;
/* 234 */       if (getPreviewReward().contains(Integer.valueOf(functionPredictBean.getId())) || functionPredictBean.getTargetType() != taskType.getType()) {
/*     */         continue;
/*     */       }
/* 237 */       TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(taskType.getType(), TaskTypeBean.class);
/* 238 */       if (taskTypeBean.getCountType() == 0) {
/* 239 */         value = TaskUtil.getSchedule(this.playerId, taskType.getType(), functionPredictBean.getTarget());
/*     */       } else {
/* 241 */         value += ((Long)getPreviewValue().getOrDefault(Integer.valueOf(functionPredictBean.getId()), Long.valueOf(0L))).longValue();
/*     */       } 
/* 243 */       getPreviewValue().put(Integer.valueOf(functionPredictBean.getId()), Long.valueOf(value));
/* 244 */       boolean isDone = TaskUtil.isTaskDone(value, functionPredictBean.getNum(), taskTypeBean.getCount());
/* 245 */       if (isDone) {
/* 246 */         PlayerUtil.sendRedNotice(Long.valueOf(this.playerId), RedNoticeConstant.TaskPreView.getSys(), functionPredictBean.getId());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void savePreMax() {
/* 255 */     IPlayer player = LookUpService.getByPlayerId(getPlayerId());
/* 256 */     if (null == player) {
/*     */       return;
/*     */     }
/* 259 */     setFindRewardMax(new HashMap<>());
/* 260 */     for (FindRewardType findRewardType : FindRewardType.values()) {
/* 261 */       FindRewardData findRewardData = TaskUtil.getFindRewardData(findRewardType, player.getSession(), false);
/* 262 */       TaskUtil.saveMaxReward(player.getSession(), findRewardData);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void resetActPlay() {
/* 267 */     SingleInsComponent singleInsComponent = (SingleInsComponent)LookUpService.getComponent(this.playerId, SingleInsComponent.class);
/* 268 */     TaskParameter taskParameter = (TaskParameter)ParameterConstant.getParameter(22);
/* 269 */     int time = TimeUtil.currentTime() - taskParameter.getDays() * 86400;
/* 270 */     int beforeYesterday = TimeUtil.getYearMonthDay(time);
/*     */     
/* 272 */     Map<Integer, Map<Integer, Integer>> actPlayTime = singleInsComponent.getActPlayTime();
/* 273 */     for (FindRewardType type : FindRewardType.values()) {
/* 274 */       Map<Integer, Integer> count = actPlayTime.getOrDefault(Integer.valueOf(type.getType()), new HashMap<>());
/* 275 */       List<Integer> del = new ArrayList<>();
/* 276 */       for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
/* 277 */         if (((Integer)entry.getKey()).intValue() < beforeYesterday) {
/* 278 */           del.add(entry.getKey());
/*     */         }
/*     */       } 
/* 281 */       for (Integer day : del) {
/* 282 */         count.remove(day);
/*     */       }
/* 284 */       actPlayTime.put(Integer.valueOf(type.getType()), count);
/*     */     } 
/* 286 */     singleInsComponent.setActPlayTime(actPlayTime);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTempGuide() {
/* 292 */     if (this.tempGuide == 0) {
/* 293 */       this.tempGuide = (getId() << 8) + getGuideId();
/*     */     }
/* 295 */     return this.tempGuide;
/*     */   }
/*     */   
/*     */   public void setTempGuide(int tempGuide) {
/* 299 */     this.tempGuide = tempGuide;
/*     */   }
/*     */   
/*     */   public int getId() {
/* 303 */     return ((TaskEntity)getEntity()).getId();
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/* 307 */     ((TaskEntity)getEntity()).setId(id);
/*     */   }
/*     */   
/*     */   public long getSchedule() {
/* 311 */     return ((TaskEntity)getEntity()).getSchedule();
/*     */   }
/*     */   
/*     */   public void setSchedule(long schedule) {
/* 315 */     ((TaskEntity)getEntity()).setSchedule(schedule);
/* 316 */     IPlayer iPlayer = LookUpService.getByPlayerId(getPlayerId());
/* 317 */     if (null != iPlayer) {
/* 318 */       PlayerUtil.updateKeyValueInfo(iPlayer.getSession(), KeyValueConstant.TASK_SCHEDULE.getKey(), schedule);
/*     */     }
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getChapterReward() {
/* 323 */     return ((TaskEntity)getEntity()).getChapterReward();
/*     */   }
/*     */   
/*     */   public void setChapterReward(Map<Integer, Integer> chapterReward) {
/* 327 */     ((TaskEntity)getEntity()).setChapterReward(chapterReward);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getInsMap() {
/* 331 */     return ((TaskEntity)getEntity()).getInsMap();
/*     */   }
/*     */   
/*     */   public void setInsMap(Map<Integer, Integer> insMap) {
/* 335 */     ((TaskEntity)getEntity()).setInsMap(insMap);
/*     */   }
/*     */   
/*     */   public int getPoint() {
/* 339 */     return ((TaskEntity)getEntity()).getPoint();
/*     */   }
/*     */   
/*     */   public void setPoint(int point) {
/* 343 */     ((TaskEntity)getEntity()).setPoint(point);
/*     */   }
/*     */   
/*     */   public Map<Integer, Long> getDailyProcess() {
/* 347 */     return ((TaskEntity)getEntity()).getDailyProcess();
/*     */   }
/*     */   
/*     */   public void setDailyProcess(Map<Integer, Long> dailyProcess) {
/* 351 */     ((TaskEntity)getEntity()).setDailyProcess(dailyProcess);
/*     */   }
/*     */   
/*     */   public Set<Integer> getDailyDone() {
/* 355 */     return ((TaskEntity)getEntity()).getDailyDone();
/*     */   }
/*     */   
/*     */   public void setDailyDone(Set<Integer> dailyDone) {
/* 359 */     ((TaskEntity)getEntity()).setDailyDone(dailyDone);
/*     */   }
/*     */   
/*     */   public Set<Integer> getDailyReward() {
/* 363 */     return ((TaskEntity)getEntity()).getDailyReward();
/*     */   }
/*     */   
/*     */   public void setDailyReward(Set<Integer> dailyReward) {
/* 367 */     ((TaskEntity)getEntity()).setDailyReward(dailyReward);
/*     */   }
/*     */   
/*     */   public Set<Integer> getPointReward() {
/* 371 */     return ((TaskEntity)getEntity()).getPointReward();
/*     */   }
/*     */   
/*     */   public void setPointReward(Set<Integer> pointReward) {
/* 375 */     ((TaskEntity)getEntity()).setPointReward(pointReward);
/*     */   }
/*     */   
/*     */   public int getChapter() {
/* 379 */     return ((TaskEntity)getEntity()).getChapter();
/*     */   }
/*     */   
/*     */   public void setChapter(int chapter) {
/* 383 */     ((TaskEntity)getEntity()).setChapter(chapter);
/*     */   }
/*     */   
/*     */   public int getGuideId() {
/* 387 */     return ((TaskEntity)getEntity()).getGuideId();
/*     */   }
/*     */   
/*     */   public void setGuideId(int guideId) {
/* 391 */     ((TaskEntity)getEntity()).setGuideId(guideId);
/*     */   }
/*     */   
/*     */   public int getFixedStatus() {
/* 395 */     return ((TaskEntity)getEntity()).getFixedStatus();
/*     */   }
/*     */   
/*     */   public void setFixedStatus(int fixedStatus) {
/* 399 */     ((TaskEntity)getEntity()).setFixedStatus(fixedStatus);
/*     */   }
/*     */   
/*     */   public int getFirstFail() {
/* 403 */     return ((TaskEntity)getEntity()).getFirstFail();
/*     */   }
/*     */   
/*     */   public void setFirstFail(int firstFail) {
/* 407 */     ((TaskEntity)getEntity()).setFirstFail(firstFail);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getAskReward() {
/* 411 */     return ((TaskEntity)getEntity()).getAskReward();
/*     */   }
/*     */   
/*     */   public void setAskReward(Map<Integer, Integer> askReward) {
/* 415 */     ((TaskEntity)getEntity()).setAskReward(askReward);
/*     */   }
/*     */   
/*     */   public int getFollow() {
/* 419 */     return ((TaskEntity)getEntity()).getFollow();
/*     */   }
/*     */   
/*     */   public void setFollow(int follow) {
/* 423 */     ((TaskEntity)getEntity()).setFollow(follow);
/*     */   }
/*     */   
/*     */   public boolean isRealName() {
/* 427 */     return ((TaskEntity)getEntity()).isRealName();
/*     */   }
/*     */   
/*     */   public void setRealName(boolean realName) {
/* 431 */     ((TaskEntity)getEntity()).setRealName(realName);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getChannelGift() {
/* 435 */     return ((TaskEntity)getEntity()).getChannelGift();
/*     */   }
/*     */   
/*     */   public void setChannelGift(Map<Integer, Integer> channelGift) {
/* 439 */     ((TaskEntity)getEntity()).setChannelGift(channelGift);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getChannelTime() {
/* 443 */     return ((TaskEntity)getEntity()).getChannelTime();
/*     */   }
/*     */   
/*     */   public void setChannelTime(Map<Integer, Integer> channelTime) {
/* 447 */     ((TaskEntity)getEntity()).setChannelTime(channelTime);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getPlatformReward() {
/* 451 */     return ((TaskEntity)getEntity()).getPlatformReward();
/*     */   }
/*     */   
/*     */   public void setPlatformReward(Map<Integer, Integer> platformReward) {
/* 455 */     ((TaskEntity)getEntity()).setPlatformReward(platformReward);
/*     */   }
/*     */   
/*     */   public Map<Integer, Long> getPreviewValue() {
/* 459 */     return ((TaskEntity)getEntity()).getPreviewValue();
/*     */   }
/*     */   
/*     */   public void setPreviewValue(Map<Integer, Long> previewValue) {
/* 463 */     ((TaskEntity)getEntity()).setPreviewValue(previewValue);
/*     */   }
/*     */   
/*     */   public Set<Integer> getPreviewReward() {
/* 467 */     return ((TaskEntity)getEntity()).getPreviewReward();
/*     */   }
/*     */   
/*     */   public void setPreviewReward(Set<Integer> previewReward) {
/* 471 */     ((TaskEntity)getEntity()).setPreviewReward(previewReward);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getFindRewardMax() {
/* 475 */     return ((TaskEntity)getEntity()).getFindRewardMax();
/*     */   }
/*     */   
/*     */   public void setFindRewardMax(Map<Integer, Integer> findRewardMax) {
/* 479 */     ((TaskEntity)getEntity()).setFindRewardMax(findRewardMax);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\task\TaskComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */