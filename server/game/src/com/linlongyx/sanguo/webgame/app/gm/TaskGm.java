/*     */ package com.linlongyx.sanguo.webgame.app.gm;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MainTaskBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.achieve.AchieveInfoProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.achieve.AchieveRewardProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.sanguozhi.ActivateRecordStarProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.sanguozhi.SanGuoZhiUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.task.FindRewardGetProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.task.FindRewardInfoProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.task.TaskDailyInfoProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.task.TaskDailyRewardProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.task.TaskUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.achieve.AchieveInfoRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.achieve.AchieveRewardRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.sanguozhi.ActivateRecordStarRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.task.FindRewardGetRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.task.FindRewardInfoRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskDailyInfoRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskDailyRewardRequest;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ 
/*     */ public class TaskGm implements IGm {
/*     */   public void gm(IPlayerSession playerSession, String[] strings) {
/*  28 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  29 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  30 */     if (taskComponent == null || playerComponent == null) {
/*     */       return;
/*     */     }
/*  33 */     if (strings[2].equals("main")) {
/*  34 */       MainTaskBean mainTaskBean = (MainTaskBean)JsonTableService.getJsonData(taskComponent.getId(), MainTaskBean.class);
/*  35 */       if (null == mainTaskBean) {
/*     */         return;
/*     */       }
/*  38 */       TaskType taskType = TaskType.getTaskType(mainTaskBean.getPara());
/*  39 */       if (null == taskType) {
/*     */         return;
/*     */       }
/*  42 */       taskComponent.refreshSchedule(taskType, mainTaskBean.getTargeId(), 1L);
/*  43 */     } else if (strings[2].equals("done")) {
/*  44 */       MainTaskBean mainTaskBean = (MainTaskBean)JsonTableService.getJsonData(taskComponent.getId(), MainTaskBean.class);
/*  45 */       if (null == mainTaskBean) {
/*     */         return;
/*     */       }
/*  48 */       taskComponent.setSchedule(mainTaskBean.getNum());
/*  49 */     } else if (strings[2].equals("setId")) {
/*  50 */       int id = Integer.valueOf(strings[3]).intValue();
/*  51 */       MainTaskBean mainTaskBean = (MainTaskBean)JsonTableService.getJsonData(id, MainTaskBean.class);
/*  52 */       if (null == mainTaskBean) {
/*     */         return;
/*     */       }
/*  55 */       taskComponent.setId(id);
/*  56 */       taskComponent.setSchedule(0L);
/*  57 */     } else if (strings[2].equals("t1")) {
/*  58 */       int id = Integer.valueOf(strings[3]).intValue();
/*  59 */       MainTaskBean mainTaskBean = (MainTaskBean)JsonTableService.getJsonData(id, MainTaskBean.class);
/*  60 */       if (null == mainTaskBean) {
/*     */         return;
/*     */       }
/*  63 */       System.out.println(TaskUtil.getSchedule(playerSession.getPlayer().getPlayerId(), mainTaskBean.getPara(), mainTaskBean.getTargeId()));
/*  64 */     } else if (strings[2].equals("t2")) {
/*  65 */       (new TaskDailyInfoProcessor()).handle(playerSession, (RequestBase)new TaskDailyInfoRequest());
/*  66 */     } else if (strings[2].equals("t3")) {
/*  67 */       TaskDailyRewardRequest request = new TaskDailyRewardRequest();
/*  68 */       request.type = Integer.valueOf(strings[3]).intValue();
/*  69 */       request.id = Integer.valueOf(strings[4]).intValue();
/*  70 */       (new TaskDailyRewardProcessor()).handle(playerSession, (RequestBase)request);
/*  71 */     } else if (strings[2].equals("t4")) {
/*  72 */       (new AchieveInfoProcessor()).handle(playerSession, (RequestBase)new AchieveInfoRequest());
/*  73 */     } else if (strings[2].equals("t5")) {
/*  74 */       AchieveRewardRequest request = new AchieveRewardRequest();
/*  75 */       request.type = Integer.valueOf(strings[3]).intValue();
/*  76 */       request.id = Integer.valueOf(strings[4]).intValue();
/*  77 */       (new AchieveRewardProcessor()).handle(playerSession, (RequestBase)request);
/*  78 */     } else if (strings[2].equals("t6")) {
/*  79 */       ActivateRecordStarRequest request = new ActivateRecordStarRequest();
/*  80 */       request.recordStar = Integer.valueOf(strings[3]).intValue();
/*  81 */       SanGuoZhiUtil.gm(playerSession, request.recordStar);
/*  82 */       (new ActivateRecordStarProcessor()).handle(playerSession, (RequestBase)request);
/*  83 */     } else if (strings[2].equals("setdone")) {
/*  84 */       int id = Integer.valueOf(strings[3]).intValue();
/*  85 */       MainTaskBean mainTaskBean = (MainTaskBean)JsonTableService.getJsonData(id, MainTaskBean.class);
/*  86 */       if (null == mainTaskBean) {
/*     */         return;
/*     */       }
/*  89 */       taskComponent.setId(id);
/*  90 */       taskComponent.setSchedule(0L);
/*  91 */       if (null == mainTaskBean) {
/*     */         return;
/*     */       }
/*  94 */       taskComponent.setSchedule(mainTaskBean.getNum());
/*  95 */     } else if (strings[2].equals("findInfo")) {
/*  96 */       (new FindRewardInfoProcessor()).handle(playerSession, (RequestBase)new FindRewardInfoRequest());
/*  97 */     } else if (strings[2].equals("findGet")) {
/*  98 */       FindRewardGetRequest request = new FindRewardGetRequest();
/*  99 */       request.type = Integer.parseInt(strings[3]);
/* 100 */       request.costType = Integer.parseInt(strings[4]);
/* 101 */       request.value = Integer.parseInt(strings[5]);
/* 102 */       (new FindRewardGetProcessor()).handle(playerSession, (RequestBase)request);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\TaskGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */