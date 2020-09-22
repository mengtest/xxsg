/*     */ package com.linlongyx.sanguo.webgame.processors.equip;
/*     */ 
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
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.attribute.PartnerAttrUpdate;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipStoneBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.equip.comparator.EquipStoneComparator;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipStoneUpRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipStoneUpResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.EquipData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class EquipStoneUpProcessor extends ProcessorBase<EquipStoneUpRequest, EquipStoneUpResponse> {
/*     */   protected void initResponse() {
/*  34 */     this.response = (ResponseBase)new EquipStoneUpResponse();
/*     */   }
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, EquipStoneUpRequest request) {
/*     */     EquipStoneComparator equipStoneComparator;
/*  39 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 803)) {
/*  40 */       return 10061;
/*     */     }
/*  42 */     long pid = request.pid;
/*  43 */     int count = request.count, realCount = 0;
/*  44 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  45 */     EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*  46 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  47 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  48 */     long mid = request.mid;
/*  49 */     if (pid != -1L) {
/*  50 */       PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  51 */       if (null == partnerEntity) {
/*  52 */         return 13303;
/*     */       }
/*     */     } 
/*     */     
/*  56 */     EquipEntity equipEntity = equipComponent.getEquipEntity(mid);
/*  57 */     if (null == equipEntity) {
/*  58 */       return 10805;
/*     */     }
/*  60 */     boolean isTreasure = EquipUtil.isTreasure(equipEntity.getItemId());
/*  61 */     if (isTreasure) {
/*  62 */       return 10814;
/*     */     }
/*  64 */     boolean talisman = EquipUtil.isTalisman(equipEntity.getItemId());
/*  65 */     if (talisman) {
/*  66 */       return 10814;
/*     */     }
/*  68 */     int levelLimit = playerComponent.getLevel();
/*     */     
/*  70 */     List<IntIntValue> stoneList = new ArrayList<>();
/*  71 */     Map<Integer, Integer> stones = equipEntity.getStones();
/*  72 */     Comparator<IntIntValue> comparator = null;
/*  73 */     if (request.index != 0) {
/*  74 */       if (request.index < 1 || request.index > 4) {
/*  75 */         return 10817;
/*     */       }
/*  77 */       if (((Integer)stones.getOrDefault(Integer.valueOf(request.index), (V)Integer.valueOf(0))).intValue() < levelLimit) {
/*  78 */         IntIntValue intIntValue = new IntIntValue();
/*  79 */         intIntValue.key = request.index;
/*  80 */         intIntValue.value = ((Integer)stones.getOrDefault(Integer.valueOf(request.index), Integer.valueOf(0))).intValue();
/*  81 */         stoneList.add(intIntValue);
/*     */       } 
/*     */     } else {
/*  84 */       for (int index = 1; index <= 4; index++) {
/*  85 */         int lv = ((Integer)stones.getOrDefault(Integer.valueOf(index), Integer.valueOf(0))).intValue();
/*  86 */         if (lv < levelLimit) {
/*  87 */           IntIntValue intIntValue = new IntIntValue();
/*  88 */           intIntValue.key = index;
/*  89 */           intIntValue.value = lv;
/*  90 */           stoneList.add(intIntValue);
/*     */         } 
/*     */       } 
/*  93 */       if (stoneList.size() > 1) {
/*  94 */         equipStoneComparator = new EquipStoneComparator();
/*  95 */         stoneList.sort((Comparator<? super IntIntValue>)equipStoneComparator);
/*     */       } 
/*     */     } 
/*     */     
/*  99 */     if (stoneList.isEmpty()) {
/* 100 */       return 10819;
/*     */     }
/* 102 */     int realNum = 0;
/*     */     
/* 104 */     int num = 0, level = 0;
/* 105 */     for (int i = 0; i < count; i++) {
/*     */       
/* 107 */       int stepIndex = 0, sameLvCount = 0;
/* 108 */       boolean isAffect = false;
/* 109 */       while (stepIndex < stoneList.size()) {
/* 110 */         level = ((IntIntValue)stoneList.get(stepIndex)).value;
/* 111 */         if (level >= levelLimit)
/* 112 */           break;  sameLvCount = 0;
/* 113 */         isAffect = false;
/* 114 */         for (int j = stepIndex; j < stoneList.size(); ) {
/* 115 */           IntIntValue intIntValue = stoneList.get(j);
/* 116 */           if (intIntValue.value == level) {
/* 117 */             sameLvCount++;
/* 118 */             EquipStoneBean equipStoneBean = (EquipStoneBean)JsonTableService.getJsonData(intIntValue.key, EquipStoneBean.class);
/* 119 */             num = equipStoneBean.getCostNum() * (level + 1);
/* 120 */             if (bagComponent.check(equipStoneBean.getItemId(), num)) {
/*     */ 
/*     */               
/* 123 */               isAffect = true;
/* 124 */               realNum++;
/* 125 */               bagComponent.deleteItem(equipStoneBean.getItemId(), num, ResourceEvent.EquipStoneUp, true);
/* 126 */               intIntValue.value++;
/*     */             } 
/*     */             j++;
/*     */           } 
/*     */         } 
/* 131 */         if (isAffect)
/* 132 */           break;  stepIndex += sameLvCount;
/*     */       } 
/* 134 */       if (!isAffect)
/* 135 */         break;  stoneList.sort((Comparator<? super IntIntValue>)equipStoneComparator);
/* 136 */       realCount++;
/*     */     } 
/* 138 */     for (IntIntValue intIntValue : stoneList) {
/* 139 */       if (intIntValue.value > 0 && ((Integer)stones.getOrDefault(Integer.valueOf(intIntValue.key), Integer.valueOf(0))).intValue() != intIntValue.value) {
/* 140 */         ((EquipStoneUpResponse)this.response).kvList.add(intIntValue);
/* 141 */         stones.put(Integer.valueOf(intIntValue.key), Integer.valueOf(intIntValue.value));
/*     */       } 
/*     */     } 
/* 144 */     equipEntity.setStones(stones);
/* 145 */     equipComponent.updateStoneToDB(equipEntity);
/* 146 */     if (((EquipStoneUpResponse)this.response).kvList.size() == 0) {
/* 147 */       return (((IntIntValue)stoneList.get(0)).value == levelLimit) ? 10819 : 10050;
/*     */     }
/*     */ 
/*     */     
/* 151 */     AttrUpdateUtil.refreshPartnerEquip(playerSession);
/* 152 */     AttrUpdateUtil.refreshPlayerEquip(playerSession);
/* 153 */     AttrUpdateUtil.refreshGrowthGoal(playerSession);
/* 154 */     if (pid != -1L) {
/* 155 */       PartnerAttrUpdate.refreshPartnerEquip(playerSession, pid);
/* 156 */       PartnerAttrUpdate.refreshGrowthGoal(playerSession, pid);
/*     */     } 
/* 158 */     ((EquipStoneUpResponse)this.response).pid = pid;
/* 159 */     ((EquipStoneUpResponse)this.response).mid = mid;
/* 160 */     ((EquipStoneUpResponse)this.response).count = realCount;
/* 161 */     EquipData equipData = EquipUtil.getEquipData(equipEntity);
/* 162 */     EquipUtil.EquipDataSys(playerSession, equipData);
/* 163 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 164 */     taskComponent.refreshSchedule(TaskType.EquipStone, 0, realNum);
/* 165 */     LogUtils.debugLog(new Object[] { "EquipStone", Long.valueOf(playerSession.getPlayer().getPlayerId()), Long.valueOf(pid), Long.valueOf(mid), Integer.valueOf(count), Integer.valueOf(realCount), Integer.valueOf(realNum) });
/* 166 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\equip\EquipStoneUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */