/*     */ package com.linlongyx.sanguo.webgame.processors.equip;
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.attribute.PartnerAttrUpdate;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipArtificeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipSuitBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipArtificeUpRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipArtificeUpResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.EquipData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class EquipArtificeUpProcessor extends ProcessorBase<EquipArtificeUpRequest, EquipArtificeUpResponse> {
/*     */   protected void initResponse() {
/*  39 */     this.response = (ResponseBase)new EquipArtificeUpResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, EquipArtificeUpRequest request) {
/*  44 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 805)) {
/*  45 */       return 10061;
/*     */     }
/*  47 */     long pid = request.pid;
/*  48 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  49 */     EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*  50 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  51 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  52 */     Set<Long> equipIdSet = new HashSet<>();
/*  53 */     int levelLimit = playerComponent.getLevel();
/*  54 */     List<EquipEntity> equipList = new ArrayList<>();
/*     */     
/*  56 */     if (pid != -1L) {
/*  57 */       PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  58 */       if (null == partnerEntity) {
/*  59 */         return 13303;
/*     */       }
/*  61 */       equipIdSet.addAll(partnerEntity.getEquips().values());
/*     */     } else {
/*  63 */       equipIdSet.addAll(playerComponent.getEquips().values());
/*     */     } 
/*  65 */     if (request.mid != 0L) {
/*  66 */       if (!equipIdSet.contains(Long.valueOf(request.mid))) {
/*  67 */         return 10805;
/*     */       }
/*  69 */       EquipEntity equipEntity = equipComponent.getEquipEntity(request.mid);
/*  70 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*  71 */       if (null != equipEntity && EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  72 */         return 10822;
/*     */       }
/*  74 */       if (null != equipEntity && EquipUtil.isTalisman(equipEntity.getItemId())) {
/*  75 */         return 10822;
/*     */       }
/*  77 */       if (null != equipEntity && equipEntity.getArtificeLevel() < levelLimit) {
/*  78 */         equipList.add(equipEntity);
/*     */       }
/*  80 */       if (itemBean == null) {
/*  81 */         return 10006;
/*     */       }
/*  83 */       int suitType = itemBean.getSuitType();
/*  84 */       EquipArtificeBean equipArtificeBean = (EquipArtificeBean)JsonTableService.getJsonData(suitType, EquipArtificeBean.class);
/*  85 */       if (null == equipArtificeBean) {
/*  86 */         return 10006;
/*     */       }
/*  88 */       EquipArtificeBean.LvBean lvBean = (EquipArtificeBean.LvBean)equipArtificeBean.getLv().get(Integer.valueOf(equipEntity.getArtificeLevel()));
/*  89 */       if (null == lvBean || lvBean.getMaxVal() == 0) {
/*  90 */         return 10011;
/*     */       }
/*  92 */       int index = 0;
/*  93 */       int artLuck = equipEntity.getArtificeLucky();
/*  94 */       for (EquipArtificeBean.LvBean.SucessProbBean sucessProbBean : lvBean.getSucessProb()) {
/*  95 */         if (sucessProbBean.getBlessVal() <= artLuck && artLuck <= sucessProbBean.getSucessProb()) {
/*     */           break;
/*     */         }
/*  98 */         index++;
/*     */       } 
/* 100 */       int minLuck = 0;
/* 101 */       int maxLuck = 0;
/* 102 */       int minVal = 0;
/* 103 */       int maxVal = 0;
/* 104 */       EquipSuitBean equipSuitBean = (EquipSuitBean)JsonTableService.getJsonData(suitType, EquipSuitBean.class);
/* 105 */       if (null == equipSuitBean) {
/* 106 */         return 10006;
/*     */       }
/* 108 */       Map<Integer, EquipSuitBean.EquipPartBean> equipPart = equipSuitBean.getEquipPart();
/* 109 */       EquipSuitBean.EquipPartBean equipPartBean = equipPart.get(Integer.valueOf(itemBean.getEquipPart()));
/*     */       
/* 111 */       if (request.type == 0) {
/* 112 */         long costCCY = (long)Math.ceil(lvBean.getCostMoney() * equipPartBean.getArtificeStone() / 10000.0D);
/* 113 */         if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, costCCY)) {
/* 114 */           return 10052;
/*     */         }
/* 116 */         for (EquipArtificeBean.LvBean.CostMLuckBean mLuckBean : lvBean.getCostMLuck()) {
/* 117 */           minLuck = mLuckBean.getLow();
/* 118 */           maxLuck = mLuckBean.getHigh();
/*     */         } 
/* 120 */         int randLuck = RandUtil.randNum(minLuck, maxLuck);
/*     */         
/* 122 */         EquipArtificeBean.LvBean.CostMValBean costMValBean = lvBean.getCostMVal().get(index);
/* 123 */         minVal = costMValBean.getLow();
/* 124 */         maxVal = costMValBean.getHigh();
/* 125 */         int randMVal = RandUtil.randNum(minVal, maxVal);
/* 126 */         equipEntity.setArtificeLucky(equipEntity.getArtificeLucky() + randLuck);
/* 127 */         equipEntity.setArtificeProcess(equipEntity.getArtificeProcess() + randMVal);
/* 128 */         FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, costCCY, ResourceEvent.EquipArtificeUp);
/* 129 */       } else if (request.type == 1) {
/*     */         
/* 131 */         ArrayList<Reward> rewards = FinanceUtil.transformRatio(lvBean.getCost(), equipPartBean.getArtificeStone());
/* 132 */         short res = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/* 133 */         if (res != 0) {
/* 134 */           return res;
/*     */         }
/* 136 */         for (EquipArtificeBean.LvBean.CostLuckBean mLuckBean : lvBean.getCostLuck()) {
/* 137 */           minLuck = mLuckBean.getLow();
/* 138 */           maxLuck = mLuckBean.getHigh();
/*     */         } 
/* 140 */         int randLuck = RandUtil.randNum(minLuck, maxLuck);
/*     */         
/* 142 */         EquipArtificeBean.LvBean.CostValBean costValBean = lvBean.getCostVal().get(index);
/* 143 */         minVal = costValBean.getLow();
/* 144 */         maxVal = costValBean.getHigh();
/* 145 */         int randMVal = RandUtil.randNum(minVal, maxVal);
/* 146 */         equipEntity.setArtificeLucky(equipEntity.getArtificeLucky() + randLuck);
/* 147 */         equipEntity.setArtificeProcess(equipEntity.getArtificeProcess() + randMVal);
/* 148 */         equipComponent.updateArtificeLuckyToDB(equipEntity);
/* 149 */         equipComponent.updateArtificeProcessToDB(equipEntity);
/* 150 */         CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.EquipArtificeUp);
/*     */       } else {
/* 152 */         return 11808;
/*     */       } 
/* 154 */       if (equipEntity.getArtificeProcess() >= lvBean.getMaxVal()) {
/* 155 */         equipEntity.setArtificeProcess(0);
/* 156 */         equipEntity.setArtificeLevel(equipEntity.getArtificeLevel() + 1);
/* 157 */         equipEntity.setArtificeLucky(0);
/* 158 */         equipComponent.updateArtificeLuckyToDB(equipEntity);
/* 159 */         equipComponent.updateArtificeProcessToDB(equipEntity);
/* 160 */         equipComponent.updateArtificeLevelToDB(equipEntity);
/* 161 */         ((EquipArtificeUpResponse)this.response).isSuccess = 1;
/*     */       } 
/* 163 */       ((EquipArtificeUpResponse)this.response).level = equipEntity.getArtificeLevel();
/* 164 */       ((EquipArtificeUpResponse)this.response).lucky = equipEntity.getArtificeLucky();
/* 165 */       ((EquipArtificeUpResponse)this.response).mid = request.mid;
/* 166 */       ((EquipArtificeUpResponse)this.response).pid = request.pid;
/* 167 */       ((EquipArtificeUpResponse)this.response).type = request.type;
/* 168 */       ((EquipArtificeUpResponse)this.response).process = equipEntity.getArtificeProcess();
/*     */       
/* 170 */       AttrUpdateUtil.refreshPartnerEquip(playerSession);
/* 171 */       AttrUpdateUtil.refreshPlayerEquip(playerSession);
/* 172 */       AttrUpdateUtil.refreshGrowthGoal(playerSession);
/* 173 */       if (pid != -1L) {
/* 174 */         PartnerAttrUpdate.refreshPartnerEquip(playerSession, pid);
/* 175 */         PartnerAttrUpdate.refreshGrowthGoal(playerSession, pid);
/*     */       } 
/* 177 */       TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 178 */       RuneComponent runeComponent = (RuneComponent)playerSession.getPlayer().createIfNotExist(RuneComponent.class);
/* 179 */       runeComponent.setTatalArtificeTimes(runeComponent.getTatalArtificeTimes() + 1L);
/* 180 */       if (null != taskComponent) {
/* 181 */         taskComponent.refreshSchedule(TaskType.EquipArtifice, 0, 0L);
/* 182 */         taskComponent.refreshSchedule(TaskType.ArtificeTimes, 0, 1L);
/* 183 */         taskComponent.refreshSchedule(TaskType.TatalArtificeTimes, 0, 0L);
/*     */       } 
/*     */       
/* 186 */       EquipData equipData = EquipUtil.getEquipData(equipEntity);
/* 187 */       EquipUtil.EquipDataSys(playerSession, equipData);
/* 188 */       LogUtil.errorLog(new Object[] { "EquipArtificeUp", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(request.type), Long.valueOf(request.pid), Long.valueOf(request.mid), Integer.valueOf(equipEntity.getArtificeLevel()), Integer.valueOf(equipEntity.getArtificeProcess()) });
/*     */     } else {
/* 190 */       return 10804;
/*     */     } 
/* 192 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\equip\EquipArtificeUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */