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
/*     */ import com.linlongyx.sanguo.webgame.common.attribute.PartnerAttrUpdate;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterReincarnBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerReincarnRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerReincarnResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PartnerReincarnProcessor
/*     */   extends ProcessorBase<PartnerReincarnRequest, PartnerReincarnResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  35 */     this.response = (ResponseBase)new PartnerReincarnResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, PartnerReincarnRequest request) {
/*  40 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 9807)) {
/*  41 */       return 10061;
/*     */     }
/*  43 */     long pid = request.pid;
/*  44 */     int reincarnId = request.reincarnId;
/*  45 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  46 */     if (pid == -1L) {
/*  47 */       PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  48 */       FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/*  49 */       if (null == fighterBean) {
/*  50 */         return 10006;
/*     */       }
/*  52 */       if (fighterBean.getReincarn().indexOf(Integer.valueOf(reincarnId)) < 0) {
/*  53 */         return 11808;
/*     */       }
/*  55 */       FighterReincarnBean fighterReincarnBean = (FighterReincarnBean)JsonTableService.getJsonData(reincarnId, FighterReincarnBean.class);
/*  56 */       if (null == fighterReincarnBean) {
/*  57 */         return 10006;
/*     */       }
/*  59 */       if (playerComponent.getReincarnationIds().contains(Integer.valueOf(reincarnId))) {
/*  60 */         return 13333;
/*     */       }
/*  62 */       if (fighterReincarnBean.getOpenLevel() > playerComponent.getLevel()) {
/*  63 */         return 10084;
/*     */       }
/*  65 */       int num = 0;
/*  66 */       Map<Integer, Long> reinMap = (Map<Integer, Long>)playerComponent.getReincarnationMap().getOrDefault(Integer.valueOf(reincarnId), new HashMap<>());
/*  67 */       for (FighterReincarnBean.TaskBean taskBean : fighterReincarnBean.getTask()) {
/*  68 */         long process = ((Long)reinMap.getOrDefault(Integer.valueOf(taskBean.getType()), Long.valueOf(0L))).longValue();
/*  69 */         if (process >= taskBean.getNum()) {
/*  70 */           num++;
/*     */         }
/*     */       } 
/*  73 */       if (num < fighterReincarnBean.getTask().size()) {
/*  74 */         return 12201;
/*     */       }
/*  76 */       ArrayList<Reward> cost = FinanceUtil.transform(fighterReincarnBean.getCost());
/*     */       
/*  78 */       short res = CostUtil.checkRewards(cost, playerSession, bagComponent);
/*  79 */       if (res != 0) {
/*  80 */         return res;
/*     */       }
/*  82 */       CostUtil.costs(cost, playerSession, bagComponent, ResourceEvent.ReincarnCost);
/*  83 */       playerComponent.getReincarnationIds().add(Integer.valueOf(reincarnId));
/*  84 */       playerComponent.setReincarnationIds(playerComponent.getReincarnationIds());
/*  85 */       PlayerUtil.addExp(playerSession.getPlayer(), 1L);
/*  86 */       ((PartnerReincarnResponse)this.response).reincarnList = new ArrayList(playerComponent.getReincarnationIds());
/*     */       
/*  88 */       PartnerAttrUpdate.refreshReincarn(playerSession, pid);
/*     */     } else {
/*  90 */       PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  91 */       PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  92 */       if (null == partnerEntity) {
/*  93 */         return 13303;
/*     */       }
/*  95 */       FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/*  96 */       if (null == fighterBean) {
/*  97 */         return 10006;
/*     */       }
/*  99 */       if (fighterBean.getReincarn().indexOf(Integer.valueOf(reincarnId)) < 0) {
/* 100 */         return 11808;
/*     */       }
/* 102 */       FighterReincarnBean fighterReincarnBean = (FighterReincarnBean)JsonTableService.getJsonData(reincarnId, FighterReincarnBean.class);
/* 103 */       if (null == fighterReincarnBean) {
/* 104 */         return 10006;
/*     */       }
/* 106 */       if (partnerEntity.getReincarnationIds().contains(Integer.valueOf(reincarnId))) {
/* 107 */         return 13333;
/*     */       }
/* 109 */       if (fighterReincarnBean.getOpenLevel() > partnerEntity.getLevel()) {
/* 110 */         return 10084;
/*     */       }
/* 112 */       int num = 0;
/* 113 */       Map<Integer, Long> reinMap = (Map<Integer, Long>)partnerEntity.getReincarnationMap().getOrDefault(Integer.valueOf(reincarnId), new HashMap<>());
/* 114 */       for (FighterReincarnBean.TaskBean taskBean : fighterReincarnBean.getTask()) {
/* 115 */         long process = ((Long)reinMap.getOrDefault(Integer.valueOf(taskBean.getType()), Long.valueOf(0L))).longValue();
/* 116 */         if (process >= taskBean.getNum()) {
/* 117 */           num++;
/*     */         }
/*     */       } 
/* 120 */       if (num < fighterReincarnBean.getTask().size()) {
/* 121 */         return 12201;
/*     */       }
/*     */       
/* 124 */       ArrayList<Reward> cost = FinanceUtil.transform(fighterReincarnBean.getCost());
/*     */       
/* 126 */       short res = CostUtil.checkRewards(cost, playerSession, bagComponent);
/* 127 */       if (res != 0) {
/* 128 */         return res;
/*     */       }
/* 130 */       CostUtil.costs(cost, playerSession, bagComponent, ResourceEvent.ReincarnCost);
/* 131 */       partnerEntity.getReincarnationIds().add(Integer.valueOf(reincarnId));
/* 132 */       partnerComponent.updateReincarnationIdsDB(pid);
/* 133 */       ((PartnerReincarnResponse)this.response).reincarnList = new ArrayList(partnerEntity.getReincarnationIds());
/*     */       
/* 135 */       PartnerAttrUpdate.refreshReincarn(playerSession, pid);
/*     */     } 
/* 137 */     ((PartnerReincarnResponse)this.response).pid = pid;
/* 138 */     ((PartnerReincarnResponse)this.response).reincarnId = reincarnId;
/* 139 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\PartnerReincarnProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */