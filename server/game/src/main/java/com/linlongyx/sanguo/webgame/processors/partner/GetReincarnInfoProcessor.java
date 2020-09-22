/*     */ package com.linlongyx.sanguo.webgame.processors.partner;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterReincarnBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TaskTypeBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.task.TaskUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.GetReincarnInfoRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.GetReincarnInfoResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class GetReincarnInfoProcessor
/*     */   extends ProcessorBase<GetReincarnInfoRequest, GetReincarnInfoResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  31 */     this.response = (ResponseBase)new GetReincarnInfoResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, GetReincarnInfoRequest request) {
/*  36 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 9807))
/*  37 */       return 10061; 
/*  38 */     long pid = request.pid;
/*  39 */     int fighterId = 0;
/*  40 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  41 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  42 */     int level = 0;
/*  43 */     Set<Integer> reined = new HashSet<>();
/*  44 */     Map<Integer, Map<Integer, Long>> taskIdMap = new HashMap<>();
/*  45 */     PartnerEntity partnerEntity = null;
/*  46 */     if (pid == -1L) {
/*  47 */       fighterId = playerComponent.getLeaderId();
/*  48 */       level = playerComponent.getLevel();
/*  49 */       reined = playerComponent.getReincarnationIds();
/*  50 */       taskIdMap = playerComponent.getReincarnationMap();
/*     */     } else {
/*  52 */       partnerEntity = partnerComponent.getEntity(pid);
/*  53 */       if (null != partnerEntity) {
/*  54 */         fighterId = partnerEntity.getTableId();
/*  55 */         level = partnerEntity.getLevel();
/*  56 */         reined = partnerEntity.getReincarnationIds();
/*  57 */         taskIdMap = partnerEntity.getReincarnationMap();
/*     */       } 
/*     */     } 
/*  60 */     if (fighterId != 0) {
/*  61 */       FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(fighterId, FighterBean.class);
/*  62 */       if (null == fighterBean) {
/*  63 */         LogUtils.errorLog(new Object[] { "GetReincarnInfoProcessor", Long.valueOf(playerSession.getPlayer().getPlayerId()), Long.valueOf(pid), Integer.valueOf(fighterId) });
/*  64 */         return 10006;
/*     */       } 
/*  66 */       ArrayList<Integer> list = fighterBean.getReincarn();
/*  67 */       for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int rein = ((Integer)iterator.next()).intValue();
/*  68 */         FighterReincarnBean fighterReincarnBean = (FighterReincarnBean)JsonTableService.getJsonData(rein, FighterReincarnBean.class);
/*  69 */         if (null == fighterReincarnBean) {
/*  70 */           return 10006;
/*     */         }
/*  72 */         if (level < fighterReincarnBean.getOpenLevel()) {
/*     */           continue;
/*     */         }
/*  75 */         ArrayList<FighterReincarnBean.TaskBean> taskBeans = fighterReincarnBean.getTask();
/*  76 */         for (FighterReincarnBean.TaskBean taskBean : taskBeans) {
/*  77 */           TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(taskBean.getType(), TaskTypeBean.class);
/*  78 */           long value = 0L;
/*  79 */           if (null == taskIdMap.get(Integer.valueOf(rein))) {
/*  80 */             taskIdMap.put(Integer.valueOf(rein), new HashMap<>());
/*     */           }
/*  82 */           long lastValue = ((Long)((Map)taskIdMap.get(Integer.valueOf(rein))).getOrDefault(Integer.valueOf(taskBean.getType()), Long.valueOf(0L))).longValue();
/*  83 */           if (taskTypeBean.getCountType() == 0) {
/*  84 */             if (taskBean.getTargetId() == -1) {
/*  85 */               if (pid == -1L) {
/*  86 */                 value = TaskUtil.getSchedule(playerSession.getPlayer().getPlayerId(), taskBean.getType(), playerComponent.getLeaderId());
/*     */               } else {
/*  88 */                 value = TaskUtil.getSchedule(playerSession.getPlayer().getPlayerId(), taskBean.getType(), partnerEntity.getTableId());
/*     */               } 
/*     */             } else {
/*  91 */               value = TaskUtil.getSchedule(playerSession.getPlayer().getPlayerId(), taskBean.getType(), taskBean.getTargetId());
/*     */             } 
/*     */           } else {
/*  94 */             value = lastValue;
/*     */           } 
/*  96 */           if (lastValue < taskBean.getNum()) {
/*  97 */             if (value > taskBean.getNum()) {
/*  98 */               ((Map<Integer, Long>)taskIdMap.get(Integer.valueOf(rein))).put(Integer.valueOf(taskBean.getType()), Long.valueOf(taskBean.getNum()));
/*     */             } else {
/* 100 */               ((Map<Integer, Long>)taskIdMap.get(Integer.valueOf(rein))).put(Integer.valueOf(taskBean.getType()), Long.valueOf(value));
/*     */             } 
/* 102 */             if (null == partnerEntity) {
/* 103 */               playerComponent.setReincarnationMap(taskIdMap);
/*     */             } else {
/* 105 */               partnerEntity.setReincarnationMap(taskIdMap);
/* 106 */               partnerComponent.updateReincarnationMapDB(partnerEntity.getPid());
/*     */             } 
/*     */           } 
/* 109 */           KeyValue keyValue = new KeyValue();
/* 110 */           keyValue.key = rein;
/* 111 */           keyValue.value = taskBean.getType();
/* 112 */           keyValue.valueStr = (new StringBuilder()).append(((Map)taskIdMap.get(Integer.valueOf(rein))).getOrDefault(Integer.valueOf(taskBean.getType()), Long.valueOf(0L))).append("").toString();
/* 113 */           ((GetReincarnInfoResponse)this.response).list.add(keyValue);
/*     */         }  }
/*     */     
/*     */     } 
/* 117 */     ((GetReincarnInfoResponse)this.response).pid = pid;
/* 118 */     ((GetReincarnInfoResponse)this.response).reincarnList = new ArrayList<>(reined);
/* 119 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\GetReincarnInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */