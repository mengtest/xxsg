/*     */ package com.linlongyx.sanguo.webgame.processors.bag;
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RecoveryParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.KeyValueConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.equip.EquipUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagRecoveryRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagRecoveryResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ 
/*     */ public class BagRecoveryProcessor extends ProcessorBase<BagRecoveryRequest, BagRecoveryResponse> {
/*     */   protected void initResponse() {
/*  37 */     this.response = (ResponseBase)new BagRecoveryResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, BagRecoveryRequest request) {
/*  42 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 8)) {
/*  43 */       return 10061;
/*     */     }
/*     */     
/*  46 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  47 */     ArrayList<Long> ids = new ArrayList<>();
/*     */     
/*  49 */     if (request.type == 0) {
/*     */       
/*  51 */       EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*  52 */       boolean hash = false;
/*  53 */       for (Iterator<Long> iterator = request.ids.iterator(); iterator.hasNext(); ) { long equipId = ((Long)iterator.next()).longValue();
/*  54 */         ArrayList<Reward> temp = new ArrayList<>();
/*  55 */         EquipEntity equipEntity = equipComponent.checkEquip(equipId);
/*  56 */         if (null == equipEntity) {
/*     */           continue;
/*     */         }
/*  59 */         ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*  60 */         if (null == itemBean || itemBean.getFurnace().isEmpty()) {
/*     */           continue;
/*     */         }
/*  63 */         if (EquipUtil.isTreasure(equipEntity.getItemId())) {
/*     */           continue;
/*     */         }
/*  66 */         if (equipEntity.getIsWear() == 1) {
/*     */           continue;
/*     */         }
/*  69 */         if (EquipUtil.isTalisman(equipEntity.getItemId())) {
/*     */           continue;
/*     */         }
/*  72 */         ids.add(Long.valueOf(equipId));
/*     */         
/*  74 */         temp.addAll(BagUtil.equipBack(equipComponent, equipEntity));
/*  75 */         equipComponent.deleteEquip(equipEntity, ResourceEvent.EquipSell);
/*     */         
/*  77 */         itemBean.getFurnace().forEach(rewardBean -> {
/*     */               Reward reward = new Reward();
/*     */               reward.type = (byte)rewardBean.getType();
/*     */               reward.id = rewardBean.getId();
/*     */               reward.num = rewardBean.getNum();
/*     */               temp.add(reward);
/*     */             });
/*  84 */         rewards.addAll(temp);
/*  85 */         if (rewards.isEmpty()) {
/*     */           continue;
/*     */         }
/*  88 */         hash = true; }
/*     */ 
/*     */       
/*  91 */       if (hash) {
/*  92 */         equipComponent.saveAllToDB();
/*     */       }
/*     */     }
/*  95 */     else if (request.type == 1) {
/*  96 */       EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*  97 */       int count = ((AtomicInteger)equipComponent.getStatisticRequest().getValue()).incrementAndGet();
/*  98 */       int curTime = TimeUtil.currentTime();
/*  99 */       if (curTime - ((Integer)equipComponent.getStatisticRequest().getKey()).intValue() > 10) {
/* 100 */         equipComponent.getStatisticRequest().setKey(Integer.valueOf(curTime));
/* 101 */         ((AtomicInteger)equipComponent.getStatisticRequest().getValue()).set(0);
/* 102 */         if (count / 10 > 15) {
/* 103 */           PlayerUtil.updateKeyValueInfo(playerSession, KeyValueConstant.DISCONNECTION.getKey(), 0L);
/* 104 */           LookUpService.logout(LookUpService.getByPlayerId(playerSession.getPlayer().getPlayerId()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 161 */     else if (request.type == 2) {
/*     */       
/* 163 */       EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/* 164 */       for (Iterator<Long> iterator = request.ids.iterator(); iterator.hasNext(); ) { long id = ((Long)iterator.next()).longValue();
/* 165 */         ArrayList<Reward> temp = new ArrayList<>();
/* 166 */         EquipEntity equipEntity = equipComponent.checkEquip(id);
/* 167 */         if (null == equipEntity) {
/*     */           continue;
/*     */         }
/* 170 */         ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 171 */         if (null == itemBean || itemBean.getFurnace().isEmpty()) {
/*     */           continue;
/*     */         }
/*     */         
/* 175 */         if (equipEntity.getIsWear() == 1) {
/*     */           continue;
/*     */         }
/* 178 */         if (!EquipUtil.isTreasure(equipEntity.getItemId())) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 183 */         rewards.addAll(BagUtil.magicWeapon(equipComponent, equipEntity));
/* 184 */         equipComponent.deleteEquip(equipEntity, ResourceEvent.EquipSell);
/* 185 */         itemBean.getFurnace().forEach(rewardBean -> {
/*     */               Reward reward = new Reward();
/*     */               reward.type = (byte)rewardBean.getType();
/*     */               reward.id = rewardBean.getId();
/*     */               reward.num = rewardBean.getNum();
/*     */               temp.add(reward);
/*     */             });
/* 192 */         ids.add(Long.valueOf(id));
/* 193 */         rewards.addAll(temp); }
/*     */     
/* 195 */     } else if (request.type == 3) {
/*     */       
/* 197 */       for (int i = 0; i < request.ids.size(); i++) {
/* 198 */         ArrayList<Reward> temp = new ArrayList<>();
/* 199 */         long itemId = ((Long)request.ids.get(i)).longValue();
/* 200 */         int num = ((Integer)request.itemNum.get(i)).intValue();
/* 201 */         if (num > 0)
/*     */         
/*     */         { 
/* 204 */           ItemBean itemBean = (ItemBean)JsonTableService.getJsonData((int)itemId, ItemBean.class);
/* 205 */           if (null != itemBean)
/*     */           {
/*     */             
/* 208 */             if (!itemBean.getFurnace().isEmpty())
/*     */             {
/*     */               
/* 211 */               if (itemBean.getItemClass2() == 1)
/*     */               
/*     */               { 
/* 214 */                 BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 215 */                 if (bagComponent.check((int)itemId, num))
/*     */                 
/*     */                 { 
/* 218 */                   for (RewardBean rewardBean : itemBean.getFurnace()) {
/* 219 */                     Reward reward = new Reward();
/* 220 */                     reward.type = (byte)rewardBean.getType();
/* 221 */                     reward.id = rewardBean.getId();
/* 222 */                     reward.num = rewardBean.getNum() * num;
/* 223 */                     temp.add(reward);
/*     */                   } 
/* 225 */                   bagComponent.deleteItem((int)itemId, num, ResourceEvent.PiceRecovery, true);
/* 226 */                   ids.add(Long.valueOf(itemId));
/* 227 */                   rewards.addAll(temp); }  }  }  }  } 
/*     */       } 
/* 229 */     } else if (request.type == 4) {
/*     */       
/* 231 */       boolean hash = false;
/* 232 */       EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/* 233 */       for (Iterator<Long> iterator = request.ids.iterator(); iterator.hasNext(); ) { long equipId = ((Long)iterator.next()).longValue();
/* 234 */         ArrayList<Reward> temp = new ArrayList<>();
/* 235 */         EquipEntity equipEntity = equipComponent.checkEquip(equipId);
/* 236 */         if (null == equipEntity) {
/*     */           continue;
/*     */         }
/* 239 */         ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 240 */         if (null == itemBean || itemBean.getFurnace().isEmpty()) {
/*     */           continue;
/*     */         }
/* 243 */         if (!EquipUtil.isTalisman(equipEntity.getItemId())) {
/*     */           continue;
/*     */         }
/* 246 */         if (equipEntity.getIsWear() == 1) {
/*     */           continue;
/*     */         }
/* 249 */         ids.add(Long.valueOf(equipId));
/*     */         
/* 251 */         temp.addAll(BagUtil.talismanRecovery(equipComponent, equipEntity));
/* 252 */         equipComponent.deleteEquip(equipEntity, ResourceEvent.EquipSell);
/* 253 */         itemBean.getFurnace().forEach(rewardBean -> {
/*     */               Reward reward = new Reward();
/*     */               reward.type = (byte)rewardBean.getType();
/*     */               reward.id = rewardBean.getId();
/*     */               reward.num = rewardBean.getNum();
/*     */               temp.add(reward);
/*     */             });
/* 260 */         rewards.addAll(temp);
/* 261 */         if (rewards.isEmpty()) {
/*     */           continue;
/*     */         }
/* 264 */         hash = true; }
/*     */       
/* 266 */       if (hash) {
/* 267 */         equipComponent.saveAllToDB();
/*     */       }
/* 269 */     } else if (request.type == 5) {
/*     */ 
/*     */       
/* 272 */       RuneBagComponent runeBagComponent = (RuneBagComponent)playerSession.getPlayer().createIfNotExist(RuneBagComponent.class);
/* 273 */       for (Iterator<Long> iterator = request.ids.iterator(); iterator.hasNext(); ) { long mid = ((Long)iterator.next()).longValue();
/* 274 */         ArrayList<Reward> temp = new ArrayList<>();
/* 275 */         RuneBagEntity runeBagEntity = runeBagComponent.getEntity(mid);
/* 276 */         if (null == runeBagEntity) {
/*     */           continue;
/*     */         }
/* 279 */         ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(runeBagEntity.getItemId(), ItemBean.class);
/* 280 */         if (null == itemBean || itemBean.getFurnace().isEmpty()) {
/*     */           continue;
/*     */         }
/* 283 */         if (runeBagEntity.getHole() > 0) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 288 */         rewards.addAll(BagUtil.runeRecovery(runeBagComponent, runeBagEntity));
/* 289 */         runeBagComponent.deleteItem(runeBagEntity, ResourceEvent.RuneRecovery);
/* 290 */         itemBean.getFurnace().forEach(rewardBean -> {
/*     */               Reward reward = new Reward();
/*     */               reward.type = (byte)rewardBean.getType();
/*     */               reward.id = rewardBean.getId();
/*     */               reward.num = rewardBean.getNum();
/*     */               temp.add(reward);
/*     */             });
/* 297 */         rewards.addAll(temp); }
/*     */     
/*     */     } 
/* 300 */     Map<Long, Long> mapRewards = new HashMap<>();
/* 301 */     FinanceUtil.overlyingMap(mapRewards, rewards);
/* 302 */     ArrayList<Reward> reward2 = FinanceUtil.overlyingReward(mapRewards);
/* 303 */     RecoveryParameter recoveryParameter = (RecoveryParameter)ParameterConstant.getParameter(8);
/* 304 */     for (Reward reward : reward2) {
/* 305 */       if (reward.id == CurrencyType.COIN.getType()) {
/* 306 */         reward.num = reward.num * recoveryParameter.getRatio() / 10000L;
/*     */         break;
/*     */       } 
/*     */     } 
/* 310 */     if (!reward2.isEmpty()) {
/* 311 */       FinanceUtil.reward(reward2, playerSession.getPlayer(), ResourceEvent.EquipSell, true);
/*     */     }
/* 313 */     LogUtil.errorLog(new Object[] { "BagRecoveryProcessor", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(request.type), request.ids });
/* 314 */     ((BagRecoveryResponse)this.response).type = request.type;
/* 315 */     ((BagRecoveryResponse)this.response).ids.addAll(ids);
/* 316 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bag\BagRecoveryProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */