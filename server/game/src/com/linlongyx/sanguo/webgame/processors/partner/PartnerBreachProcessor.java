/*     */ package com.linlongyx.sanguo.webgame.processors.partner;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBreakBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerBreachRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerBreachResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ public class PartnerBreachProcessor
/*     */   extends ProcessorBase<PartnerBreachRequest, PartnerBreachResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  30 */     this.response = (ResponseBase)new PartnerBreachResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, PartnerBreachRequest request) {
/*  35 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 9803)) {
/*  36 */       return 10061;
/*     */     }
/*  38 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  39 */     long pid = request.pid;
/*  40 */     if (pid == -1L) {
/*  41 */       int leaderId = playerComponent.getLeaderId();
/*  42 */       FighterBreakBean fighterBreakBean = (FighterBreakBean)JsonTableService.getJsonData(leaderId, FighterBreakBean.class);
/*  43 */       FighterBreakBean.LevelBean levelBean = (FighterBreakBean.LevelBean)fighterBreakBean.getLevel().get(Integer.valueOf(playerComponent.getBreakthroughs()));
/*  44 */       if (null == levelBean) {
/*  45 */         return 13317;
/*     */       }
/*  47 */       if (levelBean.getCost() == null || levelBean.getCost().isEmpty()) {
/*  48 */         return 13320;
/*     */       }
/*  50 */       if (playerComponent.getLevel() < levelBean.getFLevel()) {
/*  51 */         return 13318;
/*     */       }
/*  53 */       BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  58 */       ArrayList<Reward> rewards = new ArrayList<>();
/*  59 */       rewards.addAll(FinanceUtil.transform(levelBean.getCost()));
/*  60 */       rewards.addAll(FinanceUtil.transform(levelBean.getMoney()));
/*  61 */       short result = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/*  62 */       if (result != 0) {
/*  63 */         return result;
/*     */       }
/*  65 */       playerComponent.setBreakthroughs(playerComponent.getBreakthroughs() + 1);
/*  66 */       CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.PartnerBreach);
/*  67 */       AttrUpdateUtil.refreshLeader(playerSession);
/*  68 */       ((PartnerBreachResponse)this.response).breachCount = playerComponent.getBreakthroughs();
/*  69 */       TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  70 */       taskComponent.refreshSchedule(TaskType.PlayerBreak, 0, 0L);
/*  71 */       taskComponent.refreshSchedule(TaskType.PartnerReincarnBreak, playerComponent.getLeaderId(), 0L);
/*     */     } else {
/*  73 */       PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  74 */       PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  75 */       if (null == partnerEntity) {
/*  76 */         return 13303;
/*     */       }
/*  78 */       FighterBreakBean fighterBreakBean = (FighterBreakBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBreakBean.class);
/*  79 */       FighterBreakBean.LevelBean levelBean = (FighterBreakBean.LevelBean)fighterBreakBean.getLevel().get(Integer.valueOf(partnerEntity.getBreakthroughs()));
/*  80 */       if (null == levelBean) {
/*  81 */         return 13317;
/*     */       }
/*  83 */       if (levelBean.getCost() == null || levelBean.getCost().isEmpty()) {
/*  84 */         return 13320;
/*     */       }
/*  86 */       if (partnerEntity.getLevel() < levelBean.getFLevel()) {
/*  87 */         return 13318;
/*     */       }
/*  89 */       BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  94 */       ArrayList<Reward> rewards = new ArrayList<>();
/*  95 */       rewards.addAll(FinanceUtil.transform(levelBean.getCost()));
/*  96 */       rewards.addAll(FinanceUtil.transform(levelBean.getMoney()));
/*  97 */       short result = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/*  98 */       if (result != 0) {
/*  99 */         return result;
/*     */       }
/* 101 */       partnerEntity.setBreakthroughs(partnerEntity.getBreakthroughs() + 1);
/* 102 */       partnerComponent.updateBreakthroughsDB(partnerEntity.getPid());
/* 103 */       CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.PartnerBreach);
/*     */       
/* 105 */       AttrUpdateUtil.refreshPartner(playerSession);
/*     */       
/* 107 */       ((PartnerBreachResponse)this.response).breachCount = partnerEntity.getBreakthroughs();
/* 108 */       TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 109 */       if (taskComponent != null) {
/* 110 */         taskComponent.refreshSchedule(TaskType.BreakFighter, partnerEntity.getBreakthroughs(), 0L);
/* 111 */         taskComponent.refreshSchedule(TaskType.PartnerReincarnBreak, partnerEntity.getTableId(), 0L);
/*     */       } 
/*     */     } 
/* 114 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\PartnerBreachProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */