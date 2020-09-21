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
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.attribute.PartnerAttrUpdate;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterDestinyBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.PartnerParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerDestinyUpRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerDestinyUpResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class PartnerDestinyUpProcessor
/*     */   extends ProcessorBase<PartnerDestinyUpRequest, PartnerDestinyUpResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  36 */     this.response = (ResponseBase)new PartnerDestinyUpResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, PartnerDestinyUpRequest request) {
/*  41 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 9805)) {
/*  42 */       return 10061;
/*     */     }
/*  44 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  45 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  46 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  47 */     PartnerParameter partnerParameter = (PartnerParameter)ParameterConstant.getParameter(33);
/*  48 */     PlayerEntity playerEntity = (PlayerEntity)playerComponent.getEntity();
/*  49 */     PartnerEntity partnerEntity = partnerComponent.getEntity(request.pid);
/*     */     
/*  51 */     if (request.pid == -1L && playerEntity.getLevel() < partnerParameter.getDestinyLevelLimit()) {
/*  52 */       return 10084;
/*     */     }
/*  54 */     if (request.pid != -1L) {
/*  55 */       if (partnerEntity.getLevel() < partnerParameter.getDestinyLevelLimit()) {
/*  56 */         return 10084;
/*     */       }
/*  58 */       if (playerComponent.getFighters().indexOf(Long.valueOf(partnerEntity.getPid())) < 0) {
/*  59 */         return 13302;
/*     */       }
/*     */     } 
/*     */     
/*  63 */     if (request.pid != -1L && partnerEntity == null) {
/*  64 */       return 13303;
/*     */     }
/*  66 */     int desLv = (request.pid == -1L) ? playerEntity.getDesLv() : partnerEntity.getDesLv();
/*  67 */     int progress = (request.pid == -1L) ? playerEntity.getDesProgress() : partnerEntity.getProgress();
/*     */ 
/*     */     
/*  70 */     FighterDestinyBean fighterDestinyBean = (FighterDestinyBean)JsonTableService.getJsonData(desLv, FighterDestinyBean.class);
/*  71 */     if (fighterDestinyBean == null || fighterDestinyBean.getMaxVal() == 0) {
/*  72 */       return 10011;
/*     */     }
/*  74 */     Set<Integer> costIdSet = new HashSet<>();
/*  75 */     for (RewardBean rewardBean : fighterDestinyBean.getCost()) {
/*  76 */       costIdSet.add(Integer.valueOf(rewardBean.getId()));
/*     */     }
/*  78 */     List<IntIntValue> costList = new ArrayList<>();
/*  79 */     for (int i = 0; i < request.itemIdList.size(); i++) {
/*  80 */       if (!costIdSet.contains(request.itemIdList.get(i))) {
/*  81 */         return 10717;
/*     */       }
/*  83 */       IntIntValue intIntValue = new IntIntValue();
/*  84 */       intIntValue.key = ((Integer)request.itemIdList.get(i)).intValue();
/*  85 */       intIntValue.value = ((Integer)request.itemNumList.get(i)).intValue();
/*  86 */       costList.add(intIntValue);
/*     */     } 
/*  88 */     int maxVal = fighterDestinyBean.getMaxVal(), prob = 0;
/*  89 */     boolean levelUp = false;
/*  90 */     int tick = 0;
/*  91 */     while (tick < 1 && isEnough(bagComponent, costList) && progress < maxVal) {
/*  92 */       tick++;
/*  93 */       delItem(playerSession, bagComponent, costList, fighterDestinyBean.getCost());
/*  94 */       for (FighterDestinyBean.SucessProbBean sp : fighterDestinyBean.getSucessProb()) {
/*  95 */         if (progress < sp.getBlessVal()) {
/*  96 */           prob = sp.getSucessProb();
/*     */           break;
/*     */         } 
/*     */       } 
/* 100 */       int rand = RandUtil.randNum(0, 10000);
/* 101 */       if (rand < prob) {
/* 102 */         levelUp = true;
/* 103 */         progress = 0;
/* 104 */         fighterDestinyBean = (FighterDestinyBean)JsonTableService.getJsonData(desLv + 1, FighterDestinyBean.class);
/* 105 */         if (fighterDestinyBean == null)
/* 106 */           break;  desLv++;
/* 107 */         if (fighterDestinyBean.getMaxVal() == 0)
/* 108 */           break;  maxVal = fighterDestinyBean.getMaxVal(); continue;
/*     */       } 
/* 110 */       int randProgress = RandUtil.randNum(((FighterDestinyBean.DesValBean)fighterDestinyBean.getDesVal().get(0)).getLow(), ((FighterDestinyBean.DesValBean)fighterDestinyBean.getDesVal().get(0)).getHigh());
/* 111 */       progress += randProgress;
/* 112 */       if (progress > maxVal) {
/* 113 */         fighterDestinyBean = (FighterDestinyBean)JsonTableService.getJsonData(desLv + 1, FighterDestinyBean.class);
/* 114 */         if (fighterDestinyBean == null) {
/*     */           break;
/*     */         }
/* 117 */         levelUp = true;
/* 118 */         if (fighterDestinyBean.getMaxVal() == 0) {
/* 119 */           desLv++;
/* 120 */           progress = 0;
/*     */           break;
/*     */         } 
/* 123 */         progress -= maxVal;
/* 124 */         desLv++;
/* 125 */         maxVal = fighterDestinyBean.getMaxVal();
/*     */       } 
/*     */     } 
/*     */     
/* 129 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 130 */     if (request.pid == -1L) {
/* 131 */       playerEntity.setDesLv(desLv);
/* 132 */       playerEntity.setDesProgress(progress);
/* 133 */       taskComponent.refreshSchedule(TaskType.PartnerReincarnDestiny, playerComponent.getLeaderId(), 0L);
/*     */     } else {
/* 135 */       partnerEntity.setProgress(progress);
/* 136 */       partnerEntity.setDesLv(desLv);
/* 137 */       taskComponent.refreshSchedule(TaskType.PartnerReincarnDestiny, partnerEntity.getTableId(), 0L);
/*     */     } 
/* 139 */     if (levelUp) {
/* 140 */       PartnerAttrUpdate.refreshDestiny(playerSession, request.pid);
/*     */     }
/*     */     
/* 143 */     if (null != taskComponent) {
/* 144 */       taskComponent.refreshSchedule(TaskType.PartnerDenstiny, 0, 0L);
/*     */     }
/* 146 */     ((PartnerDestinyUpResponse)this.response).pid = request.pid;
/* 147 */     ((PartnerDestinyUpResponse)this.response).level = desLv;
/* 148 */     ((PartnerDestinyUpResponse)this.response).exp = progress;
/*     */     
/* 150 */     return 0;
/*     */   }
/*     */   
/*     */   private boolean isEnough(BagComponent bagComponent, List<IntIntValue> itemList) {
/* 154 */     for (IntIntValue intIntValue : itemList) {
/* 155 */       if (!bagComponent.check(intIntValue.key, intIntValue.value)) {
/* 156 */         return false;
/*     */       }
/*     */     } 
/* 159 */     return true;
/*     */   }
/*     */   
/*     */   private void delItem(IPlayerSession playerSession, BagComponent bagComponent, List<IntIntValue> resourceList, ArrayList<RewardBean> costList) {
/* 163 */     CostUtil.cost(costList, playerSession, bagComponent, ResourceEvent.AchieveReward);
/* 164 */     for (IntIntValue intIntValue : resourceList) {
/* 165 */       for (RewardBean rewardBean : costList) {
/* 166 */         if (rewardBean.getId() == intIntValue.key)
/* 167 */           intIntValue.value = (int)(intIntValue.value - rewardBean.getNum()); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\PartnerDestinyUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */