/*     */ package com.linlongyx.sanguo.webgame.processors.partner;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.SoulBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerSoulLevelRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerSoulLevelResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ public class PartnerSoulLevelProcessor
/*     */   extends ProcessorBase<PartnerSoulLevelRequest, PartnerSoulLevelResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  31 */     this.response = (ResponseBase)new PartnerSoulLevelResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, PartnerSoulLevelRequest request) {
/*  36 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 9806)) {
/*  37 */       return 10061;
/*     */     }
/*  39 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  40 */     long pid = request.pid;
/*  41 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  42 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  43 */     if (pid == -1L) {
/*     */       
/*  45 */       SoulBean soulBean = (SoulBean)JsonTableService.getJsonData(playerComponent.getSoulLevel(), SoulBean.class);
/*  46 */       if (null == soulBean) {
/*  47 */         return 10006;
/*     */       }
/*  49 */       if (playerComponent.getLevel() < soulBean.getFighteLevel()) {
/*  50 */         return 13318;
/*     */       }
/*  52 */       if (null == soulBean.getCost() || soulBean.getCost().isEmpty()) {
/*  53 */         return 10011;
/*     */       }
/*  55 */       ArrayList<Reward> rewards = new ArrayList<>();
/*  56 */       rewards.addAll(FinanceUtil.transform(soulBean.getCost()));
/*  57 */       rewards.addAll(FinanceUtil.transform(soulBean.getCurrency()));
/*  58 */       short result = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/*  59 */       if (result != 0) {
/*  60 */         return result;
/*     */       }
/*  62 */       playerComponent.setSoulLevel(playerComponent.getSoulLevel() + 1);
/*  63 */       CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.partnerSoul);
/*  64 */       AttrUpdateUtil.refreshPartner(playerSession);
/*  65 */       AttrUpdateUtil.refreshLeader(playerSession);
/*  66 */       ((PartnerSoulLevelResponse)this.response).soulLevel = playerComponent.getSoulLevel();
/*  67 */       taskComponent.refreshSchedule(TaskType.PartnerReincarnSouls, playerComponent.getLeaderId(), 0L);
/*     */     } else {
/*  69 */       PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  70 */       PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  71 */       if (null == partnerEntity) {
/*  72 */         return 13303;
/*     */       }
/*  74 */       SoulBean soulBean = (SoulBean)JsonTableService.getJsonData(partnerEntity.getSoulLevel(), SoulBean.class);
/*  75 */       if (null == soulBean) {
/*  76 */         return 10006;
/*     */       }
/*  78 */       if (partnerEntity.getLevel() < soulBean.getFighteLevel()) {
/*  79 */         return 13318;
/*     */       }
/*  81 */       if (null == soulBean.getCost() || soulBean.getCost().isEmpty()) {
/*  82 */         return 10011;
/*     */       }
/*  84 */       ArrayList<Reward> rewards = new ArrayList<>();
/*  85 */       rewards.addAll(FinanceUtil.transform(soulBean.getCost()));
/*  86 */       rewards.addAll(FinanceUtil.transform(soulBean.getCurrency()));
/*     */       
/*  88 */       short result = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/*  89 */       if (result != 0) {
/*  90 */         return result;
/*     */       }
/*  92 */       partnerEntity.setSoulLevel(partnerEntity.getSoulLevel() + 1);
/*  93 */       partnerComponent.updateSoulLevelDB(partnerEntity.getPid());
/*  94 */       CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.partnerSoul);
/*     */       
/*  96 */       AttrUpdateUtil.refreshPartner(playerSession);
/*  97 */       ((PartnerSoulLevelResponse)this.response).soulLevel = partnerEntity.getSoulLevel();
/*  98 */       taskComponent.refreshSchedule(TaskType.PartnerReincarnSouls, partnerEntity.getTableId(), 0L);
/*     */     } 
/*     */     
/* 101 */     taskComponent.refreshSchedule(TaskType.TotalPartentSoul, 0, 0L);
/*     */     
/* 103 */     ((PartnerSoulLevelResponse)this.response).pid = request.pid;
/* 104 */     LogUtils.errorLog(new Object[] { "PartnerSoulLevelProcessor", Long.valueOf(playerSession.getPlayer().getPlayerId()), Long.valueOf(request.pid) });
/* 105 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\PartnerSoulLevelProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */