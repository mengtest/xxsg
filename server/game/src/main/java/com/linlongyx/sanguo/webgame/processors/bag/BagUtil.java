/*     */ package com.linlongyx.sanguo.webgame.processors.bag;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.mounts.MountsComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.mounts.MountsEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.souls.SoulsComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.souls.SoulsEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.stage.StageComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.stage.StageEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipArtificeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipStarBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipSuitBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipZhuhunBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBreakBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterReincarnBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterStarBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.KungfuBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MountBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MountLevelBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.PetBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.SoulsStarBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.StageBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TalismanLevelBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TreasurePurifyBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TreasureStrengthBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TreasureSuitBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ 
/*     */ public class BagUtil {
/*     */   public static ArrayList<Reward> equipBack(EquipComponent equipComponent, EquipEntity equipEntity) {
/*  58 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  59 */     HashedMap<Integer, Long> hashedMap = new HashedMap();
/*     */     
/*  61 */     EquipSuitBean.EquipPartBean equipPartBean = equipPartBean(equipEntity);
/*  62 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*  63 */     if (equipPartBean != null) {
/*  64 */       if (equipEntity.getStrengthLv() > 0) {
/*  65 */         EquipStrengthBean equipStrengthBean = (EquipStrengthBean)JsonTableService.getJsonData(equipEntity.getStrengthLv(), EquipStrengthBean.class);
/*  66 */         long count = equipStrengthBean.getTotalCost();
/*  67 */         Reward reward1 = new Reward();
/*  68 */         reward1.type = 1;
/*  69 */         reward1.id = CurrencyType.COIN.getType();
/*  70 */         reward1.num = (long)(count * equipPartBean.getCostCoin() / 10000.0D);
/*  71 */         rewards.add(reward1);
/*  72 */         equipEntity.setStrengthLv(0);
/*  73 */         equipComponent.updateStrengthToDB(equipEntity);
/*     */       } 
/*     */       
/*  76 */       if (equipEntity.getRefineLv() > 0) {
/*  77 */         EquipPurifyBean equipPurifyBean = (EquipPurifyBean)JsonTableService.getJsonData(equipEntity.getRefineLv(), EquipPurifyBean.class);
/*  78 */         for (RewardBean totalCostBean : equipPurifyBean.getTotalCost()) {
/*  79 */           int itemId = totalCostBean.getId();
/*  80 */           if (hashedMap.containsKey(Integer.valueOf(itemId))) {
/*  81 */             hashedMap.put(Integer.valueOf(itemId), Long.valueOf((long)(((Long)hashedMap.get(Integer.valueOf(itemId))).longValue() + totalCostBean.getNum() * equipPartBean.getCostExp() / 10000.0D))); continue;
/*     */           } 
/*  83 */           hashedMap.put(Integer.valueOf(itemId), Long.valueOf((long)(totalCostBean.getNum() * equipPartBean.getCostExp() / 10000.0D)));
/*     */         } 
/*     */         
/*  86 */         equipEntity.setRefineLv(0);
/*  87 */         equipComponent.updateRefineToDB(equipEntity);
/*     */       } 
/*     */       
/*  90 */       if (equipEntity.getZhuLv() > 0) {
/*  91 */         EquipZhuhunBean equipZhuhunBean = (EquipZhuhunBean)JsonTableService.getJsonData(equipEntity.getZhuLv(), EquipZhuhunBean.class);
/*  92 */         int itemId = equipZhuhunBean.getCostItem();
/*  93 */         long itemNum = (long)(equipZhuhunBean.getTotalCost() * equipPartBean.getCostZhstone() / 10000.0D);
/*  94 */         if (hashedMap.containsKey(Integer.valueOf(itemId))) {
/*  95 */           hashedMap.put(Integer.valueOf(itemId), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(itemId))).longValue() + itemNum));
/*     */         } else {
/*  97 */           hashedMap.put(Integer.valueOf(itemId), Long.valueOf(itemNum));
/*     */         } 
/*  99 */         equipEntity.setZhuLv(0);
/* 100 */         equipComponent.updateZhuLvToDB(equipEntity);
/*     */       } 
/*     */       
/* 103 */       if (equipEntity.getArtificeLevel() > 0) {
/* 104 */         EquipArtificeBean equipArtificeBean = (EquipArtificeBean)JsonTableService.getJsonData(itemBean.getSuitType(), EquipArtificeBean.class);
/* 105 */         if (null != equipArtificeBean) {
/* 106 */           EquipArtificeBean.LvBean lvBean = (EquipArtificeBean.LvBean)equipArtificeBean.getLv().get(Integer.valueOf(equipEntity.getArtificeLevel()));
/* 107 */           for (RewardBean rewardBean : lvBean.getRecover()) {
/* 108 */             if (hashedMap.containsKey(Integer.valueOf(rewardBean.getId()))) {
/* 109 */               hashedMap.put(Integer.valueOf(rewardBean.getId()), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(rewardBean.getId()))).longValue() + rewardBean.getNum())); continue;
/*     */             } 
/* 111 */             hashedMap.put(Integer.valueOf(rewardBean.getId()), Long.valueOf(rewardBean.getNum() * 1L));
/*     */           } 
/*     */         } 
/*     */         
/* 115 */         equipEntity.setArtificeLucky(0);
/* 116 */         equipEntity.setArtificeLevel(0);
/* 117 */         equipEntity.setArtificeProcess(0);
/* 118 */         equipComponent.updateArtificeLuckyToDB(equipEntity);
/* 119 */         equipComponent.updateArtificeProcessToDB(equipEntity);
/* 120 */         equipComponent.updateArtificeLevelToDB(equipEntity);
/*     */       } 
/* 122 */       if (equipEntity.getStar() > 0) {
/* 123 */         int star = equipEntity.getStar();
/* 124 */         EquipStarBean equipStarBean = (EquipStarBean)JsonTableService.getJsonData(itemBean.getSuitType(), EquipStarBean.class);
/* 125 */         EquipStarBean.StarBean starBean = (EquipStarBean.StarBean)equipStarBean.getStar().get(Integer.valueOf(star));
/* 126 */         if (null != starBean) {
/* 127 */           int itemNum = starBean.getRecover();
/* 128 */           Reward reward = new Reward();
/* 129 */           reward.type = 5;
/* 130 */           reward.id = equipEntity.getItemId();
/* 131 */           reward.num = itemNum;
/* 132 */           rewards.add(reward);
/*     */         } 
/* 134 */         equipEntity.setStar(0);
/* 135 */         equipComponent.updateStarToDB(equipEntity);
/*     */       } 
/*     */     } 
/*     */     
/* 139 */     boolean isZero = true; Iterator<Integer> iterator;
/* 140 */     for (iterator = equipEntity.getStones().values().iterator(); iterator.hasNext(); ) { int level = ((Integer)iterator.next()).intValue();
/* 141 */       if (level > 0) {
/* 142 */         isZero = false;
/*     */         break;
/*     */       }  }
/*     */     
/* 146 */     if (!isZero) {
/* 147 */       for (iterator = equipEntity.getStones().keySet().iterator(); iterator.hasNext(); ) { int type = ((Integer)iterator.next()).intValue();
/* 148 */         int level = ((Integer)equipEntity.getStones().get(Integer.valueOf(type))).intValue();
/* 149 */         if (level == 0) {
/*     */           continue;
/*     */         }
/* 152 */         EquipStoneBean equipStoneBean = (EquipStoneBean)JsonTableService.getJsonData(type, EquipStoneBean.class);
/* 153 */         int itemId = equipStoneBean.getItemId();
/* 154 */         long itemNum = ((level + 1) * level / 2 * equipStoneBean.getCostNum());
/* 155 */         if (hashedMap.containsKey(Integer.valueOf(itemId))) {
/* 156 */           hashedMap.put(Integer.valueOf(itemId), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(itemId))).longValue() + itemNum));
/*     */         } else {
/* 158 */           hashedMap.put(Integer.valueOf(itemId), Long.valueOf(itemNum));
/*     */         } 
/* 160 */         equipEntity.getStones().put(Integer.valueOf(type), Integer.valueOf(0)); }
/*     */       
/* 162 */       equipComponent.updateStoneToDB(equipEntity);
/*     */     } 
/*     */     
/* 165 */     for (iterator = hashedMap.keySet().iterator(); iterator.hasNext(); ) { int itemId = ((Integer)iterator.next()).intValue();
/* 166 */       Reward reward = new Reward();
/* 167 */       reward.type = 2;
/* 168 */       reward.id = itemId;
/* 169 */       reward.num = ((Long)hashedMap.get(Integer.valueOf(itemId))).longValue();
/* 170 */       rewards.add(reward); }
/*     */     
/* 172 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int ITEM_CLASS2_TYPE = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> rebornBack(PartnerComponent partnerComponent, PartnerEntity partnerEntity, boolean isReboorn) {
/* 182 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 183 */     HashedMap<Integer, Long> hashedMap = new HashedMap();
/* 184 */     long pid = partnerEntity.getPid();
/*     */     
/* 186 */     if (partnerEntity.getLevel() > 1) {
/* 187 */       ExpupBean expupBean = (ExpupBean)JsonTableService.getJsonData(partnerEntity.getLevel(), ExpupBean.class);
/* 188 */       long count = expupBean.getFExp() + partnerEntity.getExp();
/* 189 */       long money = expupBean.getFExp() + partnerEntity.getExp();
/* 190 */       LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/* 191 */       PartnerParameter partnerParameter = (PartnerParameter)ParameterConstant.getParameter(33);
/* 192 */       ArrayList<Integer> ids = loginParameter.GetExpList();
/* 193 */       int expBookId = 0;
/* 194 */       for (int i = ids.size() - 1; i >= 0; i--) {
/* 195 */         int num = ((Integer)loginParameter.getExpMap().get(ids.get(i))).intValue();
/* 196 */         if (count >= num) {
/* 197 */           long count2 = count / num;
/* 198 */           count %= num;
/* 199 */           int itemId = ((Integer)ids.get(i)).intValue();
/* 200 */           long itemNum = count2;
/* 201 */           if (hashedMap.containsKey(Integer.valueOf(itemId))) {
/* 202 */             hashedMap.put(Integer.valueOf(itemId), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(itemId))).longValue() + itemNum));
/*     */           } else {
/* 204 */             hashedMap.put(Integer.valueOf(itemId), Long.valueOf(itemNum));
/*     */           } 
/* 206 */           expBookId = itemId;
/*     */         } 
/*     */       } 
/* 209 */       if (count != 0L) {
/* 210 */         hashedMap.put(Integer.valueOf(expBookId), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(expBookId))).longValue() + 1L));
/*     */       }
/* 212 */       int key = 0;
/* 213 */       int value = 0;
/* 214 */       for (Iterator<Integer> iterator1 = loginParameter.getCoinExpMap().keySet().iterator(); iterator1.hasNext(); ) { int key2 = ((Integer)iterator1.next()).intValue();
/* 215 */         key = key2;
/* 216 */         value = ((Integer)loginParameter.getCoinExpMap().get(Integer.valueOf(key2))).intValue(); }
/*     */ 
/*     */       
/* 219 */       Reward reward2 = new Reward();
/* 220 */       reward2.type = 1;
/* 221 */       reward2.id = partnerParameter.getCostItemId();
/* 222 */       reward2.num = money * key / value;
/* 223 */       rewards.add(reward2);
/* 224 */       partnerEntity.setLevel(1);
/* 225 */       partnerComponent.updateLevelDB(pid);
/* 226 */       partnerEntity.setExp(0L);
/* 227 */       partnerComponent.updateExpDB(pid);
/*     */     } 
/*     */     
/* 230 */     if (partnerEntity.getBreakthroughs() > 0) {
/* 231 */       FighterBreakBean fighterBreakBean = (FighterBreakBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBreakBean.class);
/* 232 */       for (RewardBean costBean : ((FighterBreakBean.LevelBean)fighterBreakBean.getLevel().get(Integer.valueOf(partnerEntity.getBreakthroughs()))).getFCost()) {
/* 233 */         int itemId = costBean.getId();
/* 234 */         long itemNum = costBean.getNum();
/* 235 */         if (hashedMap.containsKey(Integer.valueOf(itemId))) {
/* 236 */           hashedMap.put(Integer.valueOf(itemId), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(itemId))).longValue() + itemNum)); continue;
/*     */         } 
/* 238 */         hashedMap.put(Integer.valueOf(itemId), Long.valueOf(itemNum));
/*     */       } 
/*     */       
/* 241 */       partnerEntity.setBreakthroughs(0);
/* 242 */       partnerComponent.updateBreakthroughsDB(pid);
/*     */     } 
/*     */     
/* 245 */     if (partnerEntity.getStars() > 0 && !isReboorn) {
/* 246 */       FighterStarBean fighterStarBean = (FighterStarBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterStarBean.class);
/* 247 */       for (RewardBean costBean : ((FighterStarBean.LevelBean)fighterStarBean.getLevel().get(Integer.valueOf(partnerEntity.getStars()))).getFCost()) {
/* 248 */         int itemId = costBean.getId();
/* 249 */         long itemNum = costBean.getNum();
/* 250 */         if (hashedMap.containsKey(Integer.valueOf(itemId))) {
/* 251 */           hashedMap.put(Integer.valueOf(itemId), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(itemId))).longValue() + itemNum)); continue;
/*     */         } 
/* 253 */         hashedMap.put(Integer.valueOf(itemId), Long.valueOf(itemNum));
/*     */       } 
/*     */       
/* 256 */       partnerEntity.setStars(0);
/* 257 */       partnerComponent.updateStarsDB(pid);
/*     */     } 
/* 259 */     if (partnerEntity.getDesLv() > 0) {
/* 260 */       FighterDestinyBean fighterDestinyBean = (FighterDestinyBean)JsonTableService.getJsonData(partnerEntity.getDesLv(), FighterDestinyBean.class);
/* 261 */       for (RewardBean rewardBean : fighterDestinyBean.getRecover()) {
/* 262 */         int itemId = rewardBean.getId();
/* 263 */         long itemNum = rewardBean.getNum();
/* 264 */         if (hashedMap.containsKey(Integer.valueOf(itemId))) {
/* 265 */           hashedMap.put(Integer.valueOf(itemId), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(itemId))).longValue() + itemNum)); continue;
/*     */         } 
/* 267 */         hashedMap.put(Integer.valueOf(itemId), Long.valueOf(itemNum));
/*     */       } 
/*     */       
/* 270 */       partnerEntity.setDesLv(0);
/* 271 */       partnerEntity.setProgress(0);
/* 272 */       partnerComponent.updateDesLvDB(pid);
/* 273 */       partnerComponent.updateProgressDB(pid);
/*     */     } 
/*     */     
/* 276 */     if (partnerEntity.getSoulLevel() > 0) {
/* 277 */       SoulBean soulBean = (SoulBean)JsonTableService.getJsonData(partnerEntity.getSoulLevel(), SoulBean.class);
/* 278 */       for (RewardBean rewardBean : soulBean.getRecover()) {
/* 279 */         int itemId = rewardBean.getId();
/* 280 */         long itemNum = rewardBean.getNum();
/* 281 */         if (hashedMap.containsKey(Integer.valueOf(itemId))) {
/* 282 */           hashedMap.put(Integer.valueOf(itemId), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(itemId))).longValue() + itemNum)); continue;
/*     */         } 
/* 284 */         hashedMap.put(Integer.valueOf(itemId), Long.valueOf(itemNum));
/*     */       } 
/*     */       
/* 287 */       partnerEntity.setSoulLevel(0);
/* 288 */       partnerComponent.updateSoulLevelDB(pid);
/*     */     } 
/*     */     
/* 291 */     if (!partnerEntity.getReincarnationIds().isEmpty()) {
/* 292 */       for (Iterator<Integer> iterator1 = partnerEntity.getReincarnationIds().iterator(); iterator1.hasNext(); ) { int id = ((Integer)iterator1.next()).intValue();
/* 293 */         FighterReincarnBean fighterReincarnBean = (FighterReincarnBean)JsonTableService.getJsonData(id, FighterReincarnBean.class);
/* 294 */         if (null != fighterReincarnBean) {
/* 295 */           for (RewardBean reward : fighterReincarnBean.getRecover()) {
/* 296 */             if (hashedMap.containsKey(Integer.valueOf(reward.getId()))) {
/* 297 */               hashedMap.put(Integer.valueOf(reward.getId()), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(reward.getId()))).longValue() + reward.getNum())); continue;
/*     */             } 
/* 299 */             hashedMap.put(Integer.valueOf(reward.getId()), Long.valueOf(reward.getNum()));
/*     */           } 
/*     */         } }
/*     */ 
/*     */       
/* 304 */       partnerEntity.setReincarnationIds(new HashSet());
/* 305 */       partnerComponent.updateReincarnationIdsDB(partnerEntity.getPid());
/* 306 */       partnerEntity.setReincarnationMap(new HashMap<>());
/* 307 */       partnerComponent.updateReincarnationMapDB(partnerEntity.getPid());
/*     */     } 
/*     */     
/* 310 */     for (Iterator<Integer> iterator = hashedMap.keySet().iterator(); iterator.hasNext(); ) { int itemId = ((Integer)iterator.next()).intValue();
/* 311 */       Reward reward = new Reward();
/* 312 */       reward.type = 2;
/* 313 */       reward.id = itemId;
/* 314 */       reward.num = ((Long)hashedMap.get(Integer.valueOf(itemId))).longValue();
/* 315 */       rewards.add(reward); }
/*     */ 
/*     */     
/* 318 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> magicWeapon(EquipComponent equipComponent, EquipEntity equipEntity) {
/* 329 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 330 */     HashedMap<Integer, Long> hashedMap = new HashedMap();
/*     */     
/* 332 */     RecoveryParameter recoveryParameter = (RecoveryParameter)ParameterConstant.getParameter(8);
/* 333 */     TreasureSuitBean.TreasurePartBean treasurePartBean = treasurePartBean(equipEntity);
/* 334 */     if (null != treasurePartBean) {
/* 335 */       if (equipEntity.getStrengthLv() > 0) {
/* 336 */         TreasureStrengthBean treasureStrengthBean = (TreasureStrengthBean)JsonTableService.getJsonData(equipEntity.getStrengthLv(), TreasureStrengthBean.class);
/* 337 */         int itemId = treasureStrengthBean.getItemId();
/* 338 */         long count = (long)(treasureStrengthBean.getTotalCost() * recoveryParameter.getStoneRatio() / 10000.0D * treasurePartBean.getCostCoin() / 10000.0D);
/* 339 */         hashedMap.put(Integer.valueOf(itemId), Long.valueOf(count));
/* 340 */         equipEntity.setStrengthLv(0);
/* 341 */         equipComponent.updateStrengthToDB(equipEntity);
/*     */       } 
/*     */       
/* 344 */       if (equipEntity.getRefineLv() > 0) {
/* 345 */         TreasurePurifyBean treasurePurifyBean = (TreasurePurifyBean)JsonTableService.getJsonData(equipEntity.getRefineLv(), TreasurePurifyBean.class);
/* 346 */         int itemId = treasurePurifyBean.getCostItem();
/* 347 */         long itemNum = (long)(treasurePurifyBean.getTotalCost() * treasurePartBean.getCostExp() / 10000.0D);
/* 348 */         if (hashedMap.containsKey(Integer.valueOf(itemId))) {
/* 349 */           hashedMap.put(Integer.valueOf(itemId), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(itemId))).longValue() + itemNum));
/*     */         } else {
/* 351 */           hashedMap.put(Integer.valueOf(itemId), Long.valueOf(itemNum));
/*     */         } 
/* 353 */         int treasureCost2 = treasurePurifyBean.getExtraCost();
/* 354 */         long treasureNum2 = treasurePurifyBean.getTotalTreasure();
/* 355 */         if (hashedMap.containsKey(Integer.valueOf(treasureCost2))) {
/* 356 */           hashedMap.put(Integer.valueOf(treasureCost2), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(treasureCost2))).longValue() + treasureNum2));
/*     */         } else {
/* 358 */           hashedMap.put(Integer.valueOf(treasureCost2), Long.valueOf(treasureNum2));
/*     */         } 
/* 360 */         equipEntity.setRefineLv(0);
/* 361 */         equipComponent.updateRefineToDB(equipEntity);
/*     */       } 
/*     */ 
/*     */       
/* 365 */       for (Iterator<Integer> iterator = hashedMap.keySet().iterator(); iterator.hasNext(); ) { int itemId = ((Integer)iterator.next()).intValue();
/* 366 */         Reward reward = new Reward();
/* 367 */         reward.type = 2;
/* 368 */         reward.id = itemId;
/* 369 */         reward.num = ((Long)hashedMap.get(Integer.valueOf(itemId))).longValue();
/* 370 */         rewards.add(reward); }
/*     */       
/* 372 */       equipEntity.setRefineLv(0);
/*     */     } 
/* 374 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static EquipSuitBean.EquipPartBean equipPartBean(EquipEntity equipEntity) {
/* 384 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 385 */     EquipSuitBean equipSuitBean = (EquipSuitBean)JsonTableService.getJsonData(itemBean.getSuitType(), EquipSuitBean.class);
/* 386 */     if (equipSuitBean != null) {
/* 387 */       Map<Integer, EquipSuitBean.EquipPartBean> equipPart = equipSuitBean.getEquipPart();
/* 388 */       EquipSuitBean.EquipPartBean equipPartBean = equipPart.get(Integer.valueOf(itemBean.getEquipPart()));
/* 389 */       return equipPartBean;
/*     */     } 
/* 391 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TreasureSuitBean.TreasurePartBean treasurePartBean(EquipEntity equipEntity) {
/* 401 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 402 */     TreasureSuitBean treasureSuitBean = (TreasureSuitBean)JsonTableService.getJsonData(itemBean.getSuitType(), TreasureSuitBean.class);
/* 403 */     if (treasureSuitBean != null) {
/* 404 */       Map<Integer, TreasureSuitBean.TreasurePartBean> treasurePart = treasureSuitBean.getTreasurePart();
/* 405 */       TreasureSuitBean.TreasurePartBean treasurePartBean = treasurePart.get(Integer.valueOf(itemBean.getEquipPart()));
/* 406 */       return treasurePartBean;
/*     */     } 
/* 408 */     return null;
/*     */   }
/*     */   
/*     */   public static boolean isUpEquip(PartnerEntity partnerEntity) {
/* 412 */     boolean isUp = false;
/* 413 */     for (Iterator<Long> iterator = partnerEntity.getEquips().values().iterator(); iterator.hasNext(); ) { long mid = ((Long)iterator.next()).longValue();
/* 414 */       if (mid != 0L) {
/* 415 */         isUp = true;
/*     */         break;
/*     */       }  }
/*     */     
/* 419 */     return isUp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean warPetRedNotice(IPlayerSession playerSession, PlayerComponent playerComponent) {
/* 429 */     boolean red = false;
/*     */     
/* 431 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 432 */     WarPetComponent warPetComponent = (WarPetComponent)playerSession.getPlayer().createIfNotExist(WarPetComponent.class);
/* 433 */     if (warPetComponent.getEntityMap().isEmpty()) {
/* 434 */       return red;
/*     */     }
/* 436 */     WarPetParameter warPetParameter = (WarPetParameter)ParameterConstant.getParameter(30);
/*     */     
/* 438 */     for (null = warPetParameter.getExpMap().keySet().iterator(); null.hasNext(); ) { int itemId = ((Integer)null.next()).intValue();
/* 439 */       if (bagComponent.check(itemId, 1L)) {
/* 440 */         red = true;
/*     */         break;
/*     */       }  }
/*     */     
/* 444 */     if (red) {
/* 445 */       return red;
/*     */     }
/*     */     
/* 448 */     for (IMapEntity iMapEntity : warPetComponent.getEntityMap().values()) {
/* 449 */       WarPetEntity warPetEntity = (WarPetEntity)iMapEntity;
/* 450 */       PetBean petBean = (PetBean)JsonTableService.getJsonData(warPetEntity.getWarPetId(), PetBean.class);
/* 451 */       ArrayList<Reward> rewards = FinanceUtil.transformWarPet(((PetBean.StarBean)petBean.getStar().get(Integer.valueOf(warPetEntity.getStar()))).getCard());
/* 452 */       if (!rewards.isEmpty() && CostUtil.checkRewards(rewards, playerSession, bagComponent) == 0) {
/* 453 */         red = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 458 */     Map<Integer, Object> map = JsonTableService.getJsonTable(PetBean.class);
/* 459 */     for (Object object : map.values()) {
/* 460 */       PetBean petBean = (PetBean)object;
/* 461 */       WarPetEntity warPetEntity = warPetComponent.getEntity(petBean.getId());
/* 462 */       if (null == warPetEntity && 
/* 463 */         CostUtil.checkRewards(FinanceUtil.transformWarPet(((PetBean.StarBean)petBean.getStar().get(Integer.valueOf(-1))).getCard()), playerSession, bagComponent) == 0) {
/* 464 */         red = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 470 */     return red;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean warZhenfaRedNotice(IPlayerSession playerSession, PlayerComponent playerComponent) {
/* 480 */     boolean red = false;
/*     */     
/* 482 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 483 */     WarLineupComponent warLineupComponent = (WarLineupComponent)playerSession.getPlayer().createIfNotExist(WarLineupComponent.class);
/* 484 */     WarZhenfaParameter warZhenfaParameter = (WarZhenfaParameter)ParameterConstant.getParameter(60);
/* 485 */     if (warLineupComponent.getEntityMap().isEmpty()) {
/* 486 */       return red;
/*     */     }
/*     */     
/* 489 */     for (null = warZhenfaParameter.getExpMap().keySet().iterator(); null.hasNext(); ) { int itemId = ((Integer)null.next()).intValue();
/* 490 */       if (bagComponent.check(itemId, 1L)) {
/* 491 */         red = true;
/*     */         break;
/*     */       }  }
/*     */     
/* 495 */     if (red) {
/* 496 */       return red;
/*     */     }
/*     */     
/* 499 */     for (IMapEntity iMapEntity : warLineupComponent.getEntityMap().values()) {
/* 500 */       WarLineupEntity warLineupEntity = (WarLineupEntity)iMapEntity;
/* 501 */       ZhenfaBean zhenfaBean = (ZhenfaBean)JsonTableService.getJsonData(warLineupEntity.getWarLineupId(), ZhenfaBean.class);
/* 502 */       ArrayList<Reward> rewards = FinanceUtil.transformWarLineup(((ZhenfaBean.StarBean)zhenfaBean.getStar().get(Integer.valueOf(warLineupEntity.getStar()))).getCard());
/* 503 */       if (!rewards.isEmpty() && CostUtil.checkRewards(rewards, playerSession, bagComponent) == 0) {
/* 504 */         red = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 509 */     Map<Integer, Object> map = JsonTableService.getJsonTable(ZhenfaBean.class);
/* 510 */     for (Object object : map.values()) {
/* 511 */       ZhenfaBean zhenfaBean = (ZhenfaBean)object;
/* 512 */       WarLineupEntity warLineupEntity = warLineupComponent.getEntity(zhenfaBean.getId());
/* 513 */       if (null == warLineupEntity && 
/* 514 */         CostUtil.checkRewards(FinanceUtil.transformWarLineup(((ZhenfaBean.StarBean)zhenfaBean.getStar().get(Integer.valueOf(-1))).getCard()), playerSession, bagComponent) == 0) {
/* 515 */         red = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 521 */     return red;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean mountsRedNotice(IPlayerSession playerSession, PlayerComponent playerComponent) {
/* 531 */     boolean red = false;
/*     */     
/* 533 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 534 */     MountsComponent mountsComponent = (MountsComponent)playerSession.getPlayer().createIfNotExist(MountsComponent.class);
/*     */     
/* 536 */     if (mountsComponent.getEntityMap().isEmpty()) {
/* 537 */       return red;
/*     */     }
/* 539 */     MountsParameter mountsParameter = (MountsParameter)ParameterConstant.getParameter(42);
/* 540 */     for (null = mountsParameter.getExpMap().keySet().iterator(); null.hasNext(); ) { int itemId = ((Integer)null.next()).intValue();
/* 541 */       if (bagComponent.check(itemId, 1L)) {
/* 542 */         red = true;
/*     */         break;
/*     */       }  }
/*     */     
/* 546 */     if (red) {
/* 547 */       return red;
/*     */     }
/*     */     
/* 550 */     for (IMapEntity iMapEntity : mountsComponent.getEntityMap().values()) {
/* 551 */       MountsEntity mountsEntity = (MountsEntity)iMapEntity;
/* 552 */       MountBean mountBean = (MountBean)JsonTableService.getJsonData(mountsEntity.getMountsId(), MountBean.class);
/* 553 */       ArrayList<Reward> rewards = FinanceUtil.transform(((MountBean.StarBean)mountBean.getStar().get(Integer.valueOf(mountsEntity.getStar()))).getCard());
/* 554 */       if (!rewards.isEmpty() && CostUtil.checkRewards(rewards, playerSession, bagComponent) == 0) {
/* 555 */         red = true;
/*     */         break;
/*     */       } 
/* 558 */       MountLevelBean mountLevelBean = (MountLevelBean)JsonTableService.getJsonData(mountsEntity.getLevel(), MountLevelBean.class);
/* 559 */       if (null != mountLevelBean && !mountLevelBean.getBreakC().isEmpty()) {
/* 560 */         ArrayList<Reward> breakre = FinanceUtil.transform(mountLevelBean.getBreakC());
/* 561 */         if (CostUtil.checkRewards(breakre, playerSession, bagComponent) == 0) {
/* 562 */           red = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 568 */     Map<Integer, Object> map = JsonTableService.getJsonTable(MountBean.class);
/* 569 */     for (Object object : map.values()) {
/* 570 */       MountBean mountBean = (MountBean)object;
/* 571 */       MountsEntity mountsEntity = mountsComponent.getEntity(mountBean.getId());
/* 572 */       if (null == mountsEntity && 
/* 573 */         CostUtil.checkRewards(FinanceUtil.transform(((MountBean.StarBean)mountBean.getStar().get(Integer.valueOf(-1))).getCard()), playerSession, bagComponent) == 0) {
/* 574 */         red = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 579 */     return red;
/*     */   }
/*     */   
/*     */   public static void petMountsRedNotice(IPlayerSession playerSession, PlayerComponent playerComponent) {
/* 583 */     boolean mountsNotice = mountsRedNotice(playerSession, playerComponent);
/* 584 */     if (mountsNotice) {
/* 585 */       PlayerUtil.sendRedNotice(Long.valueOf(playerSession.getPlayer().getPlayerId()), RedNoticeConstant.Mounts.getSys(), 0);
/*     */     }
/* 587 */     boolean zhenfaNotice = warZhenfaRedNotice(playerSession, playerComponent);
/* 588 */     if (zhenfaNotice) {
/* 589 */       PlayerUtil.sendRedNotice(Long.valueOf(playerSession.getPlayer().getPlayerId()), RedNoticeConstant.WarZhenfa.getSys(), 0);
/*     */     }
/* 591 */     boolean petNotice = warPetRedNotice(playerSession, playerComponent);
/* 592 */     if (petNotice) {
/* 593 */       PlayerUtil.sendRedNotice(Long.valueOf(playerSession.getPlayer().getPlayerId()), RedNoticeConstant.WarPet.getSys(), 0);
/*     */     }
/* 595 */     boolean kungFuNotice = kungFuRedNotice(playerSession, playerComponent);
/* 596 */     if (kungFuNotice) {
/* 597 */       PlayerUtil.sendRedNotice(Long.valueOf(playerSession.getPlayer().getPlayerId()), RedNoticeConstant.KungFu.getSys(), 0);
/*     */     }
/* 599 */     boolean stageRedNotice = StageRedNotice(playerSession, playerComponent);
/* 600 */     if (stageRedNotice) {
/* 601 */       PlayerUtil.sendRedNotice(Long.valueOf(playerSession.getPlayer().getPlayerId()), RedNoticeConstant.Stage.getSys(), 0);
/*     */     }
/* 603 */     boolean soulsRedNotice = checkSoulsRedNotice(playerSession);
/* 604 */     if (soulsRedNotice) {
/* 605 */       PlayerUtil.sendRedNotice(Long.valueOf(playerSession.getPlayer().getPlayerId()), RedNoticeConstant.Souls.getSys(), 0);
/*     */     }
/* 607 */     RuneUtil.sysList(playerSession);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean kungFuRedNotice(IPlayerSession playerSession, PlayerComponent playerComponent) {
/* 617 */     boolean red = false;
/* 618 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 619 */     KungFuComponent kungFuComponent = (KungFuComponent)playerSession.getPlayer().createIfNotExist(KungFuComponent.class);
/* 620 */     KungFuParameter kungFuParameter = (KungFuParameter)ParameterConstant.getParameter(53);
/*     */     
/* 622 */     if (kungFuComponent.getEntityMap().isEmpty()) {
/* 623 */       return red;
/*     */     }
/* 625 */     for (null = kungFuParameter.getExpMap().keySet().iterator(); null.hasNext(); ) { int itemId = ((Integer)null.next()).intValue();
/* 626 */       if (bagComponent.check(itemId, 1L)) {
/* 627 */         red = true;
/*     */         break;
/*     */       }  }
/*     */     
/* 631 */     if (red) {
/* 632 */       return red;
/*     */     }
/*     */     
/* 635 */     for (IMapEntity iMapEntity : kungFuComponent.getEntityMap().values()) {
/* 636 */       KungFuEntity kungFuEntity = (KungFuEntity)iMapEntity;
/* 637 */       KungfuBean kungfuBean = (KungfuBean)JsonTableService.getJsonData(kungFuEntity.getKungFuId(), KungfuBean.class);
/* 638 */       if (kungfuBean != null) {
/* 639 */         ArrayList<Reward> rewards = FinanceUtil.transform(((KungfuBean.StarBean)kungfuBean.getStar().get(Integer.valueOf(kungFuEntity.getStar()))).getCard());
/* 640 */         if (!rewards.isEmpty() && CostUtil.checkRewards(rewards, playerSession, bagComponent) == 0) {
/* 641 */           red = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 647 */     Map<Integer, Object> map = JsonTableService.getJsonTable(KungfuBean.class);
/* 648 */     for (Object object : map.values()) {
/* 649 */       KungfuBean kungfuBean = (KungfuBean)object;
/* 650 */       KungFuEntity kungFuEntity = kungFuComponent.getEntity(kungfuBean.getId());
/* 651 */       if (null == kungFuEntity && 
/* 652 */         CostUtil.checkRewards(FinanceUtil.transform(((KungfuBean.StarBean)kungfuBean.getStar().get(Integer.valueOf(-1))).getCard()), playerSession, bagComponent) == 0) {
/* 653 */         red = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 658 */     return red;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean StageRedNotice(IPlayerSession playerSession, PlayerComponent playerComponent) {
/* 668 */     boolean red = false;
/*     */     
/* 670 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 671 */     StageComponent stageComponent = (StageComponent)playerSession.getPlayer().createIfNotExist(StageComponent.class);
/* 672 */     StageParameter stageParameter = (StageParameter)ParameterConstant.getParameter(54);
/* 673 */     if (stageComponent.getEntityMap().isEmpty()) {
/* 674 */       return red;
/*     */     }
/*     */     
/* 677 */     for (null = stageParameter.getExpMap().keySet().iterator(); null.hasNext(); ) { int itemId = ((Integer)null.next()).intValue();
/* 678 */       if (bagComponent.check(itemId, 1L)) {
/* 679 */         red = true;
/*     */         break;
/*     */       }  }
/*     */     
/* 683 */     if (red) {
/* 684 */       return red;
/*     */     }
/*     */     
/* 687 */     for (IMapEntity iMapEntity : stageComponent.getEntityMap().values()) {
/* 688 */       StageEntity stageEntity = (StageEntity)iMapEntity;
/* 689 */       StageBean stageBean = (StageBean)JsonTableService.getJsonData(stageEntity.getId(), StageBean.class);
/* 690 */       if (stageBean != null) {
/* 691 */         ArrayList<Reward> rewards = FinanceUtil.transform(((StageBean.StarBean)stageBean.getStar().get(Integer.valueOf(stageEntity.getStar()))).getCard());
/* 692 */         if (!rewards.isEmpty() && CostUtil.checkRewards(rewards, playerSession, bagComponent) == 0) {
/* 693 */           red = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 699 */     Map<Integer, Object> map = JsonTableService.getJsonTable(StageBean.class);
/* 700 */     for (Object object : map.values()) {
/* 701 */       StageBean stageBean = (StageBean)object;
/* 702 */       StageEntity stageEntity = stageComponent.getEntity(stageBean.getId());
/* 703 */       if (null == stageEntity && 
/* 704 */         CostUtil.checkRewards(FinanceUtil.transform(((StageBean.StarBean)stageBean.getStar().get(Integer.valueOf(-1))).getCard()), playerSession, bagComponent) == 0) {
/* 705 */         red = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 710 */     return red;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkSoulsRedNotice(IPlayerSession playerSession) {
/* 720 */     boolean red = false;
/*     */     
/* 722 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 723 */     SoulsComponent soulsComponent = (SoulsComponent)playerSession.getPlayer().createIfNotExist(SoulsComponent.class);
/* 724 */     SoulsParameter soulsParameter = (SoulsParameter)ParameterConstant.getParameter(55);
/* 725 */     if (soulsComponent.getEntityMap().isEmpty()) {
/* 726 */       return red;
/*     */     }
/*     */     
/* 729 */     for (null = soulsParameter.getExpMap().keySet().iterator(); null.hasNext(); ) { int itemId = ((Integer)null.next()).intValue();
/* 730 */       if (bagComponent.check(itemId, 1L)) {
/* 731 */         red = true;
/*     */         break;
/*     */       }  }
/*     */     
/* 735 */     if (red) {
/* 736 */       return red;
/*     */     }
/*     */     
/* 739 */     for (IMapEntity iMapEntity : soulsComponent.getEntityMap().values()) {
/* 740 */       SoulsEntity soulsEntity = (SoulsEntity)iMapEntity;
/* 741 */       SoulsStarBean soulsStarBean = (SoulsStarBean)JsonTableService.getJsonData(soulsEntity.getId(), SoulsStarBean.class);
/* 742 */       if (soulsStarBean != null) {
/* 743 */         ArrayList<Reward> rewards = FinanceUtil.transform(((SoulsStarBean.StarBean)soulsStarBean.getStar().get(Integer.valueOf(soulsEntity.getStar()))).getCard());
/* 744 */         if (!rewards.isEmpty() && CostUtil.checkRewards(rewards, playerSession, bagComponent) == 0) {
/* 745 */           red = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 751 */     Map<Integer, Object> map = JsonTableService.getJsonTable(SoulsStarBean.class);
/* 752 */     for (Object object : map.values()) {
/* 753 */       SoulsStarBean soulsStarBean = (SoulsStarBean)object;
/* 754 */       SoulsEntity soulsEntity = soulsComponent.getEntity(soulsStarBean.getId());
/* 755 */       if (null == soulsEntity && 
/* 756 */         CostUtil.checkRewards(FinanceUtil.transform(((SoulsStarBean.StarBean)soulsStarBean.getStar().get(Integer.valueOf(-1))).getCard()), playerSession, bagComponent) == 0) {
/* 757 */         red = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 762 */     return red;
/*     */   }
/*     */   
/*     */   public static ArrayList<Reward> talismanRecovery(EquipComponent equipComponent, EquipEntity equipEntity) {
/* 766 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 767 */     TalismanLevelBean talismanLevelBean = (TalismanLevelBean)JsonTableService.getJsonData(equipEntity.getItemId(), TalismanLevelBean.class);
/* 768 */     if (equipEntity.getTalismanRank() > 1 && null != talismanLevelBean) {
/* 769 */       TalismanLevelBean.LvBean lvBean = (TalismanLevelBean.LvBean)talismanLevelBean.getLv().get(Integer.valueOf(equipEntity.getTalismanRank()));
/* 770 */       if (null != lvBean) {
/* 771 */         rewards.addAll(FinanceUtil.transform(lvBean.getFurnace()));
/* 772 */         equipEntity.setTalismanRank(1);
/* 773 */         equipComponent.updateTalismanRankToDB(equipEntity);
/*     */       } 
/*     */     } 
/* 776 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> runeRecovery(RuneBagComponent runeBagComponent, RuneBagEntity runeBagEntity) {
/* 786 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 787 */     RuneLevelBean runeLevelBean = (RuneLevelBean)JsonTableService.getJsonData(runeBagEntity.getLevel(), RuneLevelBean.class);
/* 788 */     RuneBean runeBean = (RuneBean)JsonTableService.getJsonData(runeBagEntity.getItemId(), RuneBean.class);
/* 789 */     if (null != runeLevelBean && null != runeBean) {
/* 790 */       rewards.addAll(FinanceUtil.transformRatioFloor(runeLevelBean.getSellReward(), runeBean.getCostCoin()));
/* 791 */       runeBagEntity.setLevel(1);
/* 792 */       runeBagComponent.updateLevelToDB(runeBagEntity);
/*     */     } 
/* 794 */     return rewards;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bag\BagUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */