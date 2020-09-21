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
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.attribute.PartnerAttrUpdate;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipPurifyBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipSuitBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TreasurePurifyBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TreasureSuitBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.equip.comparator.EquipRefineLvComparator;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipPurifyRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipPurifyResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.EquipData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LongIntValue;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class EquipPurifyProcessor extends ProcessorBase<EquipPurifyRequest, EquipPurifyResponse> {
/*     */   protected void initResponse() {
/*  35 */     this.response = (ResponseBase)new EquipPurifyResponse();
/*     */   }
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, EquipPurifyRequest request) {
/*     */     EquipRefineLvComparator equipRefineLvComparator;
/*  40 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 802)) {
/*  41 */       return 10061;
/*     */     }
/*  43 */     long pid = request.pid;
/*  44 */     int realCount = 0, lv = 0;
/*  45 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  46 */     EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*  47 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  48 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  49 */     Set<Long> equipIdSet = new HashSet<>();
/*     */     
/*  51 */     int levelLimit = playerComponent.getLevel();
/*  52 */     List<EquipEntity> equipList = new ArrayList<>();
/*  53 */     Comparator<EquipEntity> comparator = null;
/*     */     
/*  55 */     if (pid != -1L) {
/*  56 */       PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  57 */       if (null == partnerEntity) {
/*  58 */         return 13303;
/*     */       }
/*  60 */       equipIdSet.addAll(partnerEntity.getEquips().values());
/*     */     } else {
/*  62 */       equipIdSet.addAll(playerComponent.getEquips().values());
/*     */     } 
/*  64 */     if (request.mid != 0L) {
/*  65 */       if (!equipIdSet.contains(Long.valueOf(request.mid))) {
/*  66 */         return 10805;
/*     */       }
/*  68 */       EquipEntity equipEntity = equipComponent.getEquipEntity(request.mid);
/*  69 */       if (null == equipEntity) {
/*  70 */         return 10804;
/*     */       }
/*  72 */       boolean talisman = EquipUtil.isTalisman(equipEntity.getItemId());
/*  73 */       if (talisman) {
/*  74 */         return 10814;
/*     */       }
/*  76 */       if (equipComponent.getEquipEntity(request.mid).getRefineLv() < levelLimit) {
/*  77 */         equipList.add(equipComponent.getEquipEntity(request.mid));
/*     */       }
/*     */     } else {
/*  80 */       if (request.type == 0) {
/*  81 */         for (Long mid : equipIdSet) {
/*  82 */           EquipEntity equipEntity = equipComponent.getEquipEntity(mid.longValue());
/*  83 */           if (null == equipEntity || equipEntity.getRefineLv() >= levelLimit) {
/*     */             continue;
/*     */           }
/*  86 */           if (!EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  87 */             equipList.add(equipEntity);
/*     */           }
/*     */         } 
/*     */       } else {
/*  91 */         for (Long mid : equipIdSet) {
/*  92 */           EquipEntity equipEntity = equipComponent.getEquipEntity(mid.longValue());
/*  93 */           if (null == equipEntity || equipEntity.getRefineLv() >= levelLimit) {
/*     */             continue;
/*     */           }
/*  96 */           if (EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  97 */             equipList.add(equipEntity);
/*     */           }
/*     */         } 
/*     */       } 
/* 101 */       if (equipList.size() > 1) {
/* 102 */         equipRefineLvComparator = new EquipRefineLvComparator();
/* 103 */         equipList.sort((Comparator<? super EquipEntity>)equipRefineLvComparator);
/*     */       } 
/*     */     } 
/* 106 */     if (equipList.isEmpty()) {
/* 107 */       return 10809;
/*     */     }
/* 109 */     int realNum = 0;
/* 110 */     equipIdSet.clear();
/* 111 */     for (int i = 0; i < request.count; i++) {
/*     */       
/* 113 */       int stepIndex = 0, sameLvCount = 0;
/* 114 */       boolean isAffect = false;
/* 115 */       while (stepIndex < equipList.size()) {
/* 116 */         lv = ((EquipEntity)equipList.get(stepIndex)).getRefineLv();
/* 117 */         if (lv >= levelLimit) {
/*     */           break;
/*     */         }
/*     */         
/* 121 */         sameLvCount = 0;
/* 122 */         isAffect = false;
/* 123 */         for (int j = stepIndex; j < equipList.size(); ) {
/* 124 */           EquipEntity equipEntity = equipList.get(j);
/* 125 */           if (equipEntity.getRefineLv() == lv) {
/* 126 */             short code; sameLvCount++;
/* 127 */             if (EquipUtil.isTreasure(equipEntity.getItemId())) {
/* 128 */               code = purifyTreasure(levelLimit, equipEntity, bagComponent);
/*     */             } else {
/* 130 */               code = purifyEquip(levelLimit, equipEntity, bagComponent);
/*     */             } 
/* 132 */             if (code != 0) {
/* 133 */               LogUtil.errorLog(new Object[] { "EquipPurify error mid: " + equipEntity.getMid() + ", errCode: " + code });
/*     */             } else {
/*     */               
/* 136 */               isAffect = true;
/* 137 */               realNum++;
/* 138 */               equipIdSet.add(Long.valueOf(equipEntity.getMid()));
/*     */             } 
/*     */             j++;
/*     */           } 
/*     */         } 
/* 143 */         if (isAffect)
/* 144 */           break;  stepIndex += sameLvCount;
/*     */       } 
/* 146 */       if (!isAffect)
/* 147 */         break;  equipList.sort((Comparator<? super EquipEntity>)equipRefineLvComparator);
/* 148 */       realCount++;
/*     */     } 
/* 150 */     for (Long mid : equipIdSet) {
/* 151 */       EquipEntity equipEntity = equipComponent.getEquipEntity(mid.longValue());
/* 152 */       equipComponent.updateRefineToDB(equipEntity);
/*     */       
/* 154 */       EquipData equipData = EquipUtil.getEquipData(equipEntity);
/* 155 */       EquipUtil.EquipDataSys(playerSession, equipData);
/* 156 */       LongIntValue longIntValue = new LongIntValue();
/* 157 */       longIntValue.key = mid.longValue();
/* 158 */       longIntValue.value = equipEntity.getRefineLv();
/* 159 */       ((EquipPurifyResponse)this.response).kvList.add(longIntValue);
/*     */     } 
/* 161 */     ((EquipPurifyResponse)this.response).pid = pid;
/* 162 */     ((EquipPurifyResponse)this.response).count = realCount;
/*     */     
/* 164 */     if (equipIdSet.size() > 0) {
/*     */       
/* 166 */       bagComponent.notice();
/*     */       
/* 168 */       AttrUpdateUtil.refreshPartnerEquip(playerSession);
/* 169 */       AttrUpdateUtil.refreshPlayerEquip(playerSession);
/* 170 */       AttrUpdateUtil.refreshGrowthGoal(playerSession);
/* 171 */       if (pid != -1L) {
/* 172 */         PartnerAttrUpdate.refreshPartnerEquip(playerSession, pid);
/* 173 */         PartnerAttrUpdate.refreshGrowthGoal(playerSession, pid);
/*     */       } 
/* 175 */       TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 176 */       taskComponent.refreshSchedule(TaskType.PurifyFighter, 0, 0L);
/* 177 */       taskComponent.refreshSchedule(TaskType.PurifyEquip, 0, realNum);
/* 178 */       taskComponent.refreshSchedule(TaskType.EquipPurify, 0, realNum);
/* 179 */       taskComponent.refreshSchedule(TaskType.Weapon, 0, realNum);
/* 180 */       taskComponent.refreshSchedule(TaskType.XWeapon, 0, realNum);
/*     */     } else {
/* 182 */       return (((EquipEntity)equipList.get(0)).getRefineLv() == levelLimit) ? 10809 : 10050;
/*     */     } 
/* 184 */     LogUtils.debugLog(new Object[] { "EquipPurify", Long.valueOf(playerSession.getPlayer().getPlayerId()), Long.valueOf(pid), Integer.valueOf(request.count), Integer.valueOf(realNum), Integer.valueOf(realCount) });
/*     */     
/* 186 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private short purifyEquip(int levelLimit, EquipEntity equipEntity, BagComponent bagComponent) {
/* 197 */     int refineLv = equipEntity.getRefineLv();
/* 198 */     if (refineLv >= levelLimit) {
/* 199 */       return 10807;
/*     */     }
/* 201 */     EquipPurifyBean equipPurifyBean = (EquipPurifyBean)JsonTableService.getJsonData(refineLv + 1, EquipPurifyBean.class);
/* 202 */     if (null == equipPurifyBean) {
/* 203 */       return 10809;
/*     */     }
/* 205 */     equipPurifyBean = (EquipPurifyBean)JsonTableService.getJsonData(refineLv, EquipPurifyBean.class);
/* 206 */     if (null == equipPurifyBean) {
/* 207 */       return 10810;
/*     */     }
/* 209 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 210 */     if (null == itemBean) {
/* 211 */       return 10801;
/*     */     }
/* 213 */     EquipSuitBean equipSuitBean = (EquipSuitBean)JsonTableService.getJsonData(itemBean.getSuitType(), EquipSuitBean.class);
/* 214 */     if (null == equipSuitBean) {
/* 215 */       return 10811;
/*     */     }
/* 217 */     Map<Integer, EquipSuitBean.EquipPartBean> equipPart = equipSuitBean.getEquipPart();
/* 218 */     EquipSuitBean.EquipPartBean equipPartBean = equipPart.get(Integer.valueOf(itemBean.getEquipPart()));
/* 219 */     if (null == equipPartBean) {
/* 220 */       return 10811;
/*     */     }
/* 222 */     int num = (int)Math.ceil(equipPurifyBean.getCostNum() * equipPartBean.getCostExp() / 10000.0D);
/* 223 */     if (bagComponent.getItemNum(equipPurifyBean.getCostItem()) < num) {
/* 224 */       return 10050;
/*     */     }
/* 226 */     bagComponent.deleteItem(equipPurifyBean.getCostItem(), num, ResourceEvent.EquipPurify);
/* 227 */     refineLv++;
/* 228 */     equipEntity.setRefineLv(refineLv);
/* 229 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private short purifyTreasure(int levelLimit, EquipEntity equipEntity, BagComponent bagComponent) {
/* 240 */     int refineLv = equipEntity.getRefineLv();
/* 241 */     if (refineLv >= levelLimit) {
/* 242 */       return 10807;
/*     */     }
/* 244 */     TreasurePurifyBean treasurePurifyBean = (TreasurePurifyBean)JsonTableService.getJsonData(refineLv + 1, TreasurePurifyBean.class);
/* 245 */     if (null == treasurePurifyBean) {
/* 246 */       return 10809;
/*     */     }
/* 248 */     treasurePurifyBean = (TreasurePurifyBean)JsonTableService.getJsonData(refineLv, TreasurePurifyBean.class);
/* 249 */     if (null == treasurePurifyBean) {
/* 250 */       return 10810;
/*     */     }
/* 252 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 253 */     if (null == itemBean) {
/* 254 */       return 10801;
/*     */     }
/* 256 */     TreasureSuitBean treasureSuitBean = (TreasureSuitBean)JsonTableService.getJsonData(itemBean.getSuitType(), TreasureSuitBean.class);
/* 257 */     if (null == treasureSuitBean) {
/* 258 */       return 10811;
/*     */     }
/* 260 */     Map<Integer, TreasureSuitBean.TreasurePartBean> equipPart = treasureSuitBean.getTreasurePart();
/* 261 */     TreasureSuitBean.TreasurePartBean treasurePartBean = equipPart.get(Integer.valueOf(itemBean.getEquipPart()));
/* 262 */     if (null == treasurePartBean) {
/* 263 */       return 10811;
/*     */     }
/* 265 */     int itemNum = (int)Math.ceil(treasurePurifyBean.getCostNum() * treasurePartBean.getCostExp() / 10000.0D);
/* 266 */     if (bagComponent.getItemNum(treasurePurifyBean.getCostItem()) < itemNum) {
/* 267 */       return 10050;
/*     */     }
/* 269 */     int equipNum = treasurePurifyBean.getExtraNum();
/* 270 */     if (bagComponent.getItemNum(treasurePurifyBean.getExtraCost()) < equipNum) {
/* 271 */       return 10050;
/*     */     }
/* 273 */     bagComponent.deleteItem(treasurePurifyBean.getCostItem(), itemNum, ResourceEvent.EquipPurify);
/* 274 */     bagComponent.deleteItem(treasurePurifyBean.getExtraCost(), equipNum, ResourceEvent.EquipPurify);
/* 275 */     refineLv++;
/* 276 */     equipEntity.setRefineLv(refineLv);
/* 277 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\equip\EquipPurifyProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */