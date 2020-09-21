/*     */ package com.linlongyx.sanguo.webgame.processors.sanguozhi;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.skill.SkillComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.skill.SkillEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.NewSkillBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecordStarBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.KeyValueConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.skill.SkillUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.sanguozhi.ActivateRecordStarRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.sanguozhi.ActivateRecordStarResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ActivateRecordStarProcessor extends ProcessorBase<ActivateRecordStarRequest, ActivateRecordStarResponse> {
/*     */   protected void initResponse() {
/*  36 */     this.response = (ResponseBase)new ActivateRecordStarResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, ActivateRecordStarRequest request) {
/*  41 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 16))
/*  42 */       return 10061; 
/*  43 */     SanGuoZhiComponent sanGuoZhiComponent = (SanGuoZhiComponent)playerSession.getPlayer().createIfNotExist(SanGuoZhiComponent.class);
/*  44 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  45 */     RecordStarBean recordStarBean = (RecordStarBean)JsonTableService.getJsonData(request.recordStar, RecordStarBean.class);
/*  46 */     if (null == recordStarBean) {
/*  47 */       return 15001;
/*     */     }
/*     */     
/*  50 */     SanGuoZhiEntity sanGuoZhiEntity = sanGuoZhiComponent.getEntity(request.recordStar);
/*  51 */     for (RecordStarBean.TaskTpyeBean taskTpyeBean : recordStarBean.getTaskTpye()) {
/*  52 */       if (!sanGuoZhiEntity.getRewarded().contains(Integer.valueOf(taskTpyeBean.getType()))) {
/*  53 */         return 15004;
/*     */       }
/*     */     } 
/*  56 */     if (sanGuoZhiEntity.isActivity()) {
/*  57 */       return 15005;
/*     */     }
/*     */     
/*  60 */     if (recordStarBean.getPreStar() != 0 && !sanGuoZhiComponent.getEntity(recordStarBean.getPreStar()).isActivity()) {
/*  61 */       return 15006;
/*     */     }
/*     */     
/*  64 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  65 */     ArrayList<Reward> cost = new ArrayList<>();
/*  66 */     cost = FinanceUtil.transform(recordStarBean.getCost());
/*  67 */     short recode = CostUtil.checkRewards(cost, playerSession, bagComponent);
/*  68 */     if (recode != 0) {
/*  69 */       return recode;
/*     */     }
/*  71 */     CostUtil.costs(cost, playerSession, bagComponent, ResourceEvent.RecordStar);
/*  72 */     sanGuoZhiEntity.setActivity(true);
/*  73 */     sanGuoZhiComponent.updateActivityDB(request.recordStar);
/*  74 */     FinanceUtil.reward(FinanceUtil.transform(recordStarBean.getReward()), playerSession.getPlayer(), ResourceEvent.RecordStar, true);
/*  75 */     if (recordStarBean.getQuality() != 0 && recordStarBean.getQuality() != 999) {
/*  76 */       playerComponent.setQuality(recordStarBean.getQuality());
/*  77 */       PlayerUtil.updateKeyValueInfo(playerSession, KeyValueConstant.QUILITY.getKey(), playerComponent.getQuality());
/*  78 */       playerComponent.getPlayerAttrUp().levelUp(playerSession.getPlayer(), playerComponent.getLevel());
/*     */     } 
/*  80 */     if (!recordStarBean.getRecordSkill().isEmpty() && recordStarBean.getRecordSkill().size() == 2) {
/*  81 */       int recordSkill = 0;
/*  82 */       if (playerComponent.getSex() == 1) {
/*     */         
/*  84 */         recordSkill = ((Integer)recordStarBean.getRecordSkill().get(0)).intValue();
/*     */       } else {
/*  86 */         recordSkill = ((Integer)recordStarBean.getRecordSkill().get(1)).intValue();
/*     */       } 
/*  88 */       NewSkillBean newSkillBean = (NewSkillBean)JsonTableService.getJsonData(recordSkill, NewSkillBean.class);
/*  89 */       if (null != newSkillBean) {
/*  90 */         SkillComponent skillComponent = (SkillComponent)playerSession.getPlayer().createIfNotExist(SkillComponent.class);
/*  91 */         skillComponent.addSkillEntity(playerSession.getPlayer().getPlayerId(), recordSkill);
/*  92 */         SkillEntity skillEntity = skillComponent.getSkillEntity(recordSkill);
/*  93 */         skillEntity.setLevel(1);
/*  94 */         SkillUtil.sendNewSkill(playerSession.getPlayer(), skillComponent.getSkillEntity(recordSkill));
/*  95 */         skillComponent.saveAllToDB();
/*     */       } 
/*     */     } 
/*  98 */     ((ActivateRecordStarResponse)this.response).recordStar = request.recordStar;
/*  99 */     ((ActivateRecordStarResponse)this.response).quality = playerComponent.getQuality();
/* 100 */     sanGuoZhiComponent.getEntityMap().values().forEach(iMapEntity -> {
/*     */           SanGuoZhiEntity sanGuoZhiEntity2 = (SanGuoZhiEntity)iMapEntity;
/*     */           if (sanGuoZhiEntity2.isActivity()) {
/*     */             ((ActivateRecordStarResponse)this.response).starList.add(Integer.valueOf(sanGuoZhiEntity2.getRecordStarId()));
/*     */           }
/*     */         });
/* 106 */     Map<Integer, Object> starMap = JsonTableService.getJsonTable(RecordStarBean.class);
/* 107 */     for (Iterator<Integer> iterator = starMap.keySet().iterator(); iterator.hasNext(); ) { int recordStar = ((Integer)iterator.next()).intValue();
/* 108 */       RecordStarBean recordStarBean2 = (RecordStarBean)starMap.get(Integer.valueOf(recordStar));
/* 109 */       if (recordStarBean2.getPreStar() == request.recordStar) {
/* 110 */         sanGuoZhiComponent.getEntity(recordStar);
/*     */       } }
/*     */     
/* 113 */     AttrUpdateUtil.refreshLeader(playerSession);
/* 114 */     AttrUpdateUtil.refreshPartner(playerSession);
/* 115 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 116 */     taskComponent.refreshSchedule(TaskType.ChapterSanGuoZhi, request.recordStar, 0L);
/* 117 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\sanguozhi\ActivateRecordStarProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */