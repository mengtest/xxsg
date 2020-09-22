/*     */ package com.linlongyx.sanguo.webgame.processors.equip;
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
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
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipSuitBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipZhuhunBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.equip.comparator.EquipZhuLvComparator;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipZhuUpRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipZhuUpResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.EquipData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LongIntValue;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class EquipZhuUpProcessor extends ProcessorBase<EquipZhuUpRequest, EquipZhuUpResponse> {
/*     */   protected void initResponse() {
/*  37 */     this.response = (ResponseBase)new EquipZhuUpResponse();
/*     */   }
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, EquipZhuUpRequest request) {
/*     */     EquipZhuLvComparator equipZhuLvComparator;
/*  42 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 804)) {
/*  43 */       return 10061;
/*     */     }
/*  45 */     long pid = request.pid;
/*  46 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  47 */     EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*  48 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  49 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  50 */     Set<Long> equipIdSet = new HashSet<>();
/*  51 */     int levelLimit = (int)Math.ceil(playerComponent.getLevel() / 3.0D), realCount = 0;
/*     */     
/*  53 */     List<EquipEntity> equipList = new ArrayList<>();
/*  54 */     Comparator<EquipEntity> comparator = null;
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
/*  70 */       if (null == equipEntity) {
/*  71 */         return 10804;
/*     */       }
/*  73 */       boolean talisman = EquipUtil.isTalisman(equipEntity.getItemId());
/*  74 */       if (talisman) {
/*  75 */         return 10814;
/*     */       }
/*  77 */       if (equipComponent.getEquipEntity(request.mid).getZhuLv() < levelLimit) {
/*  78 */         equipList.add(equipComponent.getEquipEntity(request.mid));
/*     */       }
/*     */     } else {
/*  81 */       for (Long mid : equipIdSet) {
/*  82 */         EquipEntity equipEntity = equipComponent.getEquipEntity(mid.longValue());
/*  83 */         if (null == equipEntity || equipEntity.getZhuLv() >= levelLimit) {
/*     */           continue;
/*     */         }
/*  86 */         if (!EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  87 */           equipList.add(equipEntity);
/*     */         }
/*     */       } 
/*  90 */       if (equipList.size() > 1) {
/*  91 */         equipZhuLvComparator = new EquipZhuLvComparator();
/*  92 */         equipList.sort((Comparator<? super EquipEntity>)equipZhuLvComparator);
/*     */       } 
/*     */     } 
/*  95 */     if (equipList.isEmpty()) {
/*  96 */       return 10816;
/*     */     }
/*  98 */     int realNum = 0;
/*  99 */     equipIdSet.clear();
/*     */ 
/*     */ 
/*     */     
/* 103 */     for (int i = 0; i < request.count; i++) {
/*     */       
/* 105 */       int stepIndex = 0, sameLvCount = 0;
/* 106 */       boolean isAffect = false;
/* 107 */       while (stepIndex < equipList.size()) {
/* 108 */         int zhuLv = ((EquipEntity)equipList.get(stepIndex)).getZhuLv();
/* 109 */         if (zhuLv >= levelLimit) {
/*     */           break;
/*     */         }
/* 112 */         EquipZhuhunBean equipZhuhunBean = (EquipZhuhunBean)JsonTableService.getJsonData(zhuLv + 1, EquipZhuhunBean.class);
/* 113 */         if (null == equipZhuhunBean) {
/*     */           break;
/*     */         }
/*     */         
/* 117 */         equipZhuhunBean = (EquipZhuhunBean)JsonTableService.getJsonData(zhuLv, EquipZhuhunBean.class);
/* 118 */         sameLvCount = 0;
/* 119 */         isAffect = false;
/* 120 */         for (int j = stepIndex; j < equipList.size(); ) {
/* 121 */           EquipEntity equipEntity = equipList.get(j);
/* 122 */           if (equipEntity.getZhuLv() == zhuLv) {
/* 123 */             sameLvCount++;
/* 124 */             ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 125 */             EquipSuitBean equipSuitBean = (EquipSuitBean)JsonTableService.getJsonData(itemBean.getSuitType(), EquipSuitBean.class);
/* 126 */             EquipSuitBean.EquipPartBean equipPartBean = (EquipSuitBean.EquipPartBean)equipSuitBean.getEquipPart().get(Integer.valueOf(itemBean.getEquipPart()));
/*     */             
/* 128 */             int num = (int)Math.ceil(equipZhuhunBean.getCostNum() * equipPartBean.getCostZhstone() / 10000.0D);
/* 129 */             if (bagComponent.getItemNum(equipZhuhunBean.getCostItem()) >= num) {
/*     */ 
/*     */               
/* 132 */               isAffect = true;
/* 133 */               realNum++;
/* 134 */               bagComponent.deleteItem(equipZhuhunBean.getCostItem(), num, ResourceEvent.EquipZhuUp, true);
/* 135 */               equipEntity.setZhuLv(zhuLv + 1);
/* 136 */               equipIdSet.add(Long.valueOf(equipEntity.getMid()));
/*     */             } 
/*     */             j++;
/*     */           } 
/*     */         } 
/* 141 */         if (isAffect)
/* 142 */           break;  stepIndex += sameLvCount;
/*     */       } 
/* 144 */       if (!isAffect)
/* 145 */         break;  equipList.sort((Comparator<? super EquipEntity>)equipZhuLvComparator);
/* 146 */       realCount++;
/*     */     } 
/* 148 */     for (Long mid : equipIdSet) {
/* 149 */       EquipEntity equipEntity = equipComponent.getEquipEntity(mid.longValue());
/* 150 */       equipComponent.updateZhuLvToDB(equipEntity);
/*     */       
/* 152 */       EquipData equipData = EquipUtil.getEquipData(equipEntity);
/* 153 */       EquipUtil.EquipDataSys(playerSession, equipData);
/* 154 */       LongIntValue longIntValue = new LongIntValue();
/* 155 */       longIntValue.key = mid.longValue();
/* 156 */       longIntValue.value = equipEntity.getRefineLv();
/* 157 */       ((EquipZhuUpResponse)this.response).kvList.add(longIntValue);
/*     */     } 
/*     */ 
/*     */     
/* 161 */     if (equipIdSet.size() > 0) {
/* 162 */       AttrUpdateUtil.refreshPartnerEquip(playerSession);
/* 163 */       AttrUpdateUtil.refreshPlayerEquip(playerSession);
/* 164 */       AttrUpdateUtil.refreshGrowthGoal(playerSession);
/* 165 */       if (pid != -1L) {
/* 166 */         PartnerAttrUpdate.refreshPartnerEquip(playerSession, pid);
/* 167 */         PartnerAttrUpdate.refreshGrowthGoal(playerSession, pid);
/*     */       } 
/*     */     } else {
/* 170 */       return (((EquipEntity)equipList.get(0)).getZhuLv() == levelLimit) ? 10816 : 10050;
/*     */     } 
/* 172 */     RuneComponent runeComponent = (RuneComponent)playerSession.getPlayer().createIfNotExist(RuneComponent.class);
/* 173 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 174 */     runeComponent.setTotalZhuHun(runeComponent.getTotalZhuHun() + realCount);
/* 175 */     if (null != taskComponent) {
/* 176 */       taskComponent.refreshSchedule(TaskType.EquipZhuhun, 0, realCount);
/* 177 */       taskComponent.refreshSchedule(TaskType.TotalEquipZhuhun, 0, 0L);
/*     */     } 
/* 179 */     ((EquipZhuUpResponse)this.response).pid = pid;
/* 180 */     ((EquipZhuUpResponse)this.response).count = realCount;
/* 181 */     LogUtils.debugLog(new Object[] { "EquipZhuUp", Long.valueOf(playerSession.getPlayer().getPlayerId()), Long.valueOf(pid), Integer.valueOf(request.count), Integer.valueOf(realCount), Integer.valueOf(realNum) });
/* 182 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\equip\EquipZhuUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */