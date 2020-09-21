/*     */ package com.linlongyx.sanguo.webgame.common.util;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.activitybag.ActivityBagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.reward.DropBag;
/*     */ import com.linlongyx.sanguo.webgame.common.reward.PlayerCurrency;
/*     */ import com.linlongyx.sanguo.webgame.common.reward.RewardStruct;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.HandbookBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.PetBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.VipAccessBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.log.LogType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.divine.DivineUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.recruit.RecruitUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagDropResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.common.RewardNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FinanceUtil
/*     */ {
/*     */   public static ArrayList<Reward> transform(List<RewardBean> rewardBeanList) {
/*  48 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  49 */     if (null == rewardBeanList) {
/*  50 */       return rewards;
/*     */     }
/*  52 */     for (RewardBean rewardBean : rewardBeanList) {
/*  53 */       Reward reward = new Reward();
/*  54 */       reward.type = (byte)rewardBean.getType();
/*  55 */       reward.id = rewardBean.getId();
/*  56 */       reward.num = rewardBean.getNum();
/*  57 */       rewards.add(reward);
/*     */     } 
/*  59 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> transformRatio(List<RewardBean> rewardBeanList, int num) {
/*  70 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  71 */     if (null == rewardBeanList) {
/*  72 */       return rewards;
/*     */     }
/*  74 */     for (RewardBean rewardBean : rewardBeanList) {
/*  75 */       Reward reward = new Reward();
/*  76 */       reward.type = (byte)rewardBean.getType();
/*  77 */       reward.id = rewardBean.getId();
/*  78 */       reward.num = (long)Math.ceil((rewardBean.getNum() * num) / 10000.0D);
/*  79 */       rewards.add(reward);
/*     */     } 
/*  81 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> transformRatioFloor(List<RewardBean> rewardBeanList, int num) {
/*  92 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  93 */     if (null == rewardBeanList) {
/*  94 */       return rewards;
/*     */     }
/*  96 */     for (RewardBean rewardBean : rewardBeanList) {
/*  97 */       Reward reward = new Reward();
/*  98 */       reward.type = (byte)rewardBean.getType();
/*  99 */       reward.id = rewardBean.getId();
/*     */       
/* 101 */       reward.num = (long)Math.floor((rewardBean.getNum() * num) / 10000.0D);
/* 102 */       rewards.add(reward);
/*     */     } 
/* 104 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> transformRewardFloor(List<Reward> rewardBeanList, int num) {
/* 115 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 116 */     if (null == rewardBeanList) {
/* 117 */       return rewards;
/*     */     }
/* 119 */     for (Reward rewardBean : rewardBeanList) {
/* 120 */       Reward reward = new Reward();
/* 121 */       reward.type = rewardBean.type;
/* 122 */       reward.id = rewardBean.id;
/*     */       
/* 124 */       reward.num = (long)Math.floor((rewardBean.num * num) / 10000.0D);
/* 125 */       rewards.add(reward);
/*     */     } 
/* 127 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> transformReward(Reward reward, int count) {
/* 138 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 139 */     if (null == reward) {
/* 140 */       return rewards;
/*     */     }
/* 142 */     Reward reward1 = new Reward();
/* 143 */     reward1.type = reward.type;
/* 144 */     reward1.id = reward.id;
/* 145 */     reward.num *= count;
/* 146 */     rewards.add(reward1);
/* 147 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> transformRewardList(ArrayList<Reward> rewardList, int count) {
/* 158 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 159 */     for (Reward reward1 : rewardList) {
/* 160 */       Reward reward = new Reward();
/* 161 */       reward.type = reward1.type;
/* 162 */       reward.id = reward1.id;
/* 163 */       reward1.num *= count;
/* 164 */       rewards.add(reward);
/*     */     } 
/* 166 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> transformWarPet(List<PetBean.StarBean.CardBean> rewardBeanList) {
/* 177 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 178 */     if (null == rewardBeanList) {
/* 179 */       return rewards;
/*     */     }
/* 181 */     for (PetBean.StarBean.CardBean rewardBean : rewardBeanList) {
/* 182 */       Reward reward = new Reward();
/* 183 */       reward.type = (byte)rewardBean.getType();
/* 184 */       reward.id = rewardBean.getId();
/* 185 */       reward.num = rewardBean.getNum();
/* 186 */       rewards.add(reward);
/*     */     } 
/* 188 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> transformWarLineup(List<ZhenfaBean.StarBean.CardBean> rewardBeanList) {
/* 199 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 200 */     if (null == rewardBeanList) {
/* 201 */       return rewards;
/*     */     }
/* 203 */     for (ZhenfaBean.StarBean.CardBean rewardBean : rewardBeanList) {
/* 204 */       Reward reward = new Reward();
/* 205 */       reward.type = (byte)rewardBean.getType();
/* 206 */       reward.id = rewardBean.getId();
/* 207 */       reward.num = rewardBean.getNum();
/* 208 */       rewards.add(reward);
/*     */     } 
/* 210 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> transformPiceReward(ArrayList<RewardBean> reward, int count) {
/* 222 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 223 */     if (null == reward) {
/* 224 */       return rewards;
/*     */     }
/* 226 */     for (RewardBean rewardBean : reward) {
/* 227 */       Reward reward1 = new Reward();
/* 228 */       reward1.type = (byte)rewardBean.getType();
/* 229 */       reward1.id = rewardBean.getId();
/* 230 */       reward1.num = rewardBean.getNum() * count;
/* 231 */       rewards.add(reward1);
/*     */     } 
/* 233 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Reward transformNewReward(Reward reward, int count) {
/* 245 */     Reward reward1 = new Reward();
/* 246 */     reward1.type = reward.type;
/* 247 */     reward1.id = reward.id;
/* 248 */     reward.num *= count;
/* 249 */     return reward1;
/*     */   }
/*     */   
/*     */   public static ArrayList<Reward> transform2(ArrayList<RewardBean> costBeans, int num) {
/* 253 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 254 */     if (null == costBeans) {
/* 255 */       return rewards;
/*     */     }
/* 257 */     for (RewardBean costBean : costBeans) {
/* 258 */       Reward reward = new Reward();
/* 259 */       reward.type = (byte)costBean.getType();
/* 260 */       reward.id = costBean.getId();
/* 261 */       reward.num = costBean.getNum() * num;
/* 262 */       rewards.add(reward);
/*     */     } 
/* 264 */     return rewards;
/*     */   }
/*     */   
/*     */   public static Reward transform4(RewardBean rewardBean, int param) {
/* 268 */     Reward reward = new Reward();
/* 269 */     reward.type = (byte)rewardBean.getType();
/* 270 */     reward.id = rewardBean.getId();
/* 271 */     reward.num = rewardBean.getNum() * param;
/* 272 */     return reward;
/*     */   }
/*     */   
/*     */   public static void overlyingMap(Map<Long, Long> map, ArrayList<Reward> rewards) {
/* 276 */     long para = 1000000000L;
/* 277 */     for (Reward reward : rewards) {
/* 278 */       long key = reward.type * para + reward.id;
/* 279 */       map.putIfAbsent(Long.valueOf(key), Long.valueOf(0L));
/* 280 */       map.put(Long.valueOf(key), Long.valueOf(((Long)map.get(Long.valueOf(key))).longValue() + reward.num));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ArrayList<Reward> overlyingReward(Map<Long, Long> map) {
/* 285 */     long para = 1000000000L;
/* 286 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 287 */     for (Map.Entry<Long, Long> entry : map.entrySet()) {
/* 288 */       Reward reward = new Reward();
/* 289 */       reward.type = (byte)(int)(((Long)entry.getKey()).longValue() / para);
/* 290 */       reward.id = (int)(((Long)entry.getKey()).longValue() % para);
/* 291 */       reward.num = ((Long)entry.getValue()).longValue();
/* 292 */       rewards.add(reward);
/*     */     } 
/* 294 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> transform(RewardBean rewardBean) {
/* 304 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 305 */     Reward reward = new Reward();
/* 306 */     reward.type = (byte)rewardBean.getType();
/* 307 */     reward.id = rewardBean.getId();
/* 308 */     reward.num = rewardBean.getNum();
/* 309 */     rewards.add(reward);
/* 310 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Reward transform(int type, int id, int num) {
/* 318 */     Reward reward = new Reward();
/* 319 */     reward.type = (byte)type;
/* 320 */     reward.id = id;
/* 321 */     reward.num = num;
/* 322 */     return reward;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> transformHandbook(ArrayList<HandbookBean.StarsBean.RewardBean> moneyBeans) {
/* 332 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 333 */     if (null == moneyBeans) {
/* 334 */       return rewards;
/*     */     }
/* 336 */     for (HandbookBean.StarsBean.RewardBean moneyBean : moneyBeans) {
/* 337 */       Reward reward = new Reward();
/* 338 */       reward.type = (byte)moneyBean.getType();
/* 339 */       reward.id = moneyBean.getId();
/* 340 */       reward.num = moneyBean.getNum();
/* 341 */       rewards.add(reward);
/*     */     } 
/* 343 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getPlayerCurrency(IPlayer player, CurrencyType currencyType) {
/* 350 */     return PlayerCurrency.get(player, currencyType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getPlayerCurrency(PlayerComponent playerComponent, CurrencyType currencyType) {
/* 357 */     return PlayerCurrency.get(playerComponent, currencyType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addCurrency(IPlayer player, CurrencyType currencyType, long num, ResourceEvent resourceEvent, boolean sendFlag, int extend) {
/* 369 */     PlayerCurrency.add(player, currencyType, num, extend);
/* 370 */     if (currencyType.getType() == CurrencyType.TotalCharge.getType()) {
/* 371 */       PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 372 */       VipAccessBean vipAccessBean = (VipAccessBean)JsonTableService.getJsonData(playerComponent.getVip() + 1, VipAccessBean.class);
/* 373 */       if (vipAccessBean != null)
/*     */       {
/* 375 */         while (PlayerCurrency.get(playerComponent, CurrencyType.TotalCharge) >= vipAccessBean.getTotal()) {
/* 376 */           playerComponent.setVip((byte)vipAccessBean.getVipLevel());
/* 377 */           LogUtil.gameLog(LogType.VIP, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(player.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(vipAccessBean.getVipLevel()), TimeUtil.getFormatDate() });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 383 */           vipAccessBean = (VipAccessBean)JsonTableService.getJsonData(playerComponent.getVip() + 1, VipAccessBean.class);
/* 384 */           if (vipAccessBean == null) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 391 */     if (currencyType.getType() == CurrencyType.PlatformCharge.getType()) {
/* 392 */       PlayerUtil.platformRebate(num, player.getSession());
/*     */     }
/*     */     
/* 395 */     if (sendFlag && CurrencyType.EXP.getType() != currencyType.getType()) {
/* 396 */       PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 397 */       if (CurrencyType.COIN == currencyType) {
/* 398 */         if (num >= 400000L) {
/* 399 */           LogUtil.gameLog(LogType.CURRENCY, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(player.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(currencyType.getType()), Long.valueOf(num), Long.valueOf(PlayerCurrency.get(player, currencyType)), TimeUtil.getFormatDate(), Integer.valueOf(resourceEvent.getType()), Integer.valueOf(1) });
/*     */         }
/*     */       } else {
/* 402 */         LogUtil.gameLog(LogType.CURRENCY, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(player.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(currencyType.getType()), Long.valueOf(num), Long.valueOf(PlayerCurrency.get(player, currencyType)), TimeUtil.getFormatDate(), Integer.valueOf(resourceEvent.getType()), Integer.valueOf(1) });
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 408 */     if (sendFlag && CurrencyType.EXP.getType() != currencyType.getType())
/* 409 */       PlayerUtil.updateKeyValueInfo(player.getSession(), currencyType.getType(), PlayerCurrency.get(player, currencyType)); 
/*     */   }
/*     */   
/*     */   public static void addCurrency(IPlayer player, CurrencyType currencyType, long num, ResourceEvent resourceEvent) {
/* 413 */     addCurrency(player, currencyType, num, resourceEvent, true, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void decCurrency(IPlayer player, CurrencyType currencyType, long num, ResourceEvent resourceEvent, boolean sendFlag) {
/* 425 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 426 */     String date = TimeUtil.getFormatDate();
/* 427 */     if (CurrencyType.CCY_B == currencyType) {
/* 428 */       long ccyb = PlayerCurrency.get(player, CurrencyType.CCY_B);
/* 429 */       if (ccyb >= num) {
/* 430 */         PlayerCurrency.dec(player, CurrencyType.CCY_B, num, resourceEvent);
/* 431 */         LogUtil.gameLog(LogType.CURRENCY, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(player.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(currencyType.getType()), Long.valueOf(num), Long.valueOf(PlayerCurrency.get(player, currencyType)), date, Integer.valueOf(resourceEvent.getType()), Integer.valueOf(2) });
/*     */       } else {
/*     */         
/* 434 */         PlayerCurrency.dec(player, CurrencyType.CCY_B, ccyb, resourceEvent);
/* 435 */         PlayerCurrency.dec(player, CurrencyType.CCY, num - ccyb, resourceEvent);
/* 436 */         LogUtil.gameLog(LogType.CURRENCY, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(player.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(CurrencyType.CCY_B.getType()), Long.valueOf(ccyb), Long.valueOf(PlayerCurrency.get(player, CurrencyType.CCY_B)), date, Integer.valueOf(resourceEvent.getType()), Integer.valueOf(2) });
/* 437 */         LogUtil.gameLog(LogType.CURRENCY, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(player.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(CurrencyType.CCY.getType()), Long.valueOf(num - ccyb), Long.valueOf(PlayerCurrency.get(player, CurrencyType.CCY)), date, Integer.valueOf(resourceEvent.getType()), Integer.valueOf(2) });
/*     */ 
/*     */         
/* 440 */         if (sendFlag)
/* 441 */           PlayerUtil.updateKeyValueInfo(player.getSession(), CurrencyType.CCY.getType(), PlayerCurrency.get(player, CurrencyType.CCY)); 
/*     */       } 
/* 443 */       if (sendFlag)
/* 444 */         PlayerUtil.updateKeyValueInfo(player.getSession(), currencyType.getType(), PlayerCurrency.get(player, currencyType)); 
/*     */     } else {
/* 446 */       PlayerCurrency.dec(player, currencyType, num, resourceEvent);
/* 447 */       if (CurrencyType.COIN == currencyType) {
/* 448 */         if (num >= 400000L) {
/* 449 */           LogUtil.gameLog(LogType.CURRENCY, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(player.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(currencyType.getType()), Long.valueOf(num), Long.valueOf(PlayerCurrency.get(player, currencyType)), date, Integer.valueOf(resourceEvent.getType()), Integer.valueOf(2) });
/*     */         }
/*     */       } else {
/* 452 */         LogUtil.gameLog(LogType.CURRENCY, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(player.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(currencyType.getType()), Long.valueOf(num), Long.valueOf(PlayerCurrency.get(player, currencyType)), date, Integer.valueOf(resourceEvent.getType()), Integer.valueOf(2) });
/*     */       } 
/* 454 */       if (currencyType == CurrencyType.CCY)
/*     */       {
/* 456 */         DivineUtil.addTimes(player.getPlayerId(), (int)num);
/*     */       }
/* 458 */       if (sendFlag)
/* 459 */         PlayerUtil.updateKeyValueInfo(player.getSession(), currencyType.getType(), PlayerCurrency.get(player, currencyType)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void decCurrency(IPlayer player, CurrencyType currencyType, long num, ResourceEvent resourceEvent) {
/* 464 */     if (CurrencyType.CCY_B == currencyType) {
/* 465 */       long ccyb = PlayerCurrency.get(player, CurrencyType.CCY_B);
/* 466 */       if (ccyb >= num) {
/* 467 */         decCurrency(player, CurrencyType.CCY_B, num, resourceEvent, true);
/*     */       } else {
/* 469 */         decCurrency(player, CurrencyType.CCY_B, ccyb, resourceEvent, true);
/* 470 */         decCurrency(player, CurrencyType.CCY, num - ccyb, resourceEvent, true);
/*     */       } 
/*     */     } else {
/* 473 */       decCurrency(player, currencyType, num, resourceEvent, true);
/*     */     } 
/*     */   }
/*     */   public static void decCurrencyOffline(PlayerComponent playerComponent, CurrencyType currencyType, long num, ResourceEvent resourceEvent, boolean sendFlag) {
/* 477 */     String date = TimeUtil.getFormatDate();
/* 478 */     if (CurrencyType.CCY_B == currencyType) {
/* 479 */       long ccyb = PlayerCurrency.get(playerComponent, CurrencyType.CCY_B);
/* 480 */       if (ccyb >= num) {
/* 481 */         PlayerCurrency.dec(playerComponent, CurrencyType.CCY_B, num, resourceEvent);
/* 482 */         LogUtil.gameLog(LogType.CURRENCY, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerComponent.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(currencyType.getType()), Long.valueOf(num), Long.valueOf(PlayerCurrency.get(playerComponent, currencyType)), date, Integer.valueOf(resourceEvent.getType()), Integer.valueOf(2) });
/*     */       } else {
/*     */         
/* 485 */         PlayerCurrency.dec(playerComponent, CurrencyType.CCY_B, ccyb, resourceEvent);
/* 486 */         PlayerCurrency.dec(playerComponent, CurrencyType.CCY, num - ccyb, resourceEvent);
/* 487 */         LogUtil.gameLog(LogType.CURRENCY, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerComponent.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(CurrencyType.CCY_B.getType()), Long.valueOf(ccyb), Long.valueOf(PlayerCurrency.get(playerComponent, CurrencyType.CCY_B)), date, Integer.valueOf(resourceEvent.getType()), Integer.valueOf(2) });
/* 488 */         LogUtil.gameLog(LogType.CURRENCY, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerComponent.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(CurrencyType.CCY.getType()), Long.valueOf(num - ccyb), Long.valueOf(PlayerCurrency.get(playerComponent, CurrencyType.CCY)), date, Integer.valueOf(resourceEvent.getType()), Integer.valueOf(2) });
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 493 */       PlayerCurrency.dec(playerComponent, currencyType, num, resourceEvent);
/* 494 */       if (CurrencyType.COIN == currencyType) {
/* 495 */         if (num >= 400000L) {
/* 496 */           LogUtil.gameLog(LogType.CURRENCY, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerComponent.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(currencyType.getType()), Long.valueOf(num), Long.valueOf(PlayerCurrency.get(playerComponent, currencyType)), date, Integer.valueOf(resourceEvent.getType()), Integer.valueOf(2) });
/*     */         }
/*     */       } else {
/* 499 */         LogUtil.gameLog(LogType.CURRENCY, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerComponent.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(currencyType.getType()), Long.valueOf(num), Long.valueOf(PlayerCurrency.get(playerComponent, currencyType)), date, Integer.valueOf(resourceEvent.getType()), Integer.valueOf(2) });
/*     */       } 
/* 501 */       if (currencyType == CurrencyType.CCY)
/*     */       {
/* 503 */         DivineUtil.addTimes(playerComponent.getPlayerId(), (int)num);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addCurrencyOffline(PlayerComponent playerComponent, CurrencyType currencyType, long num, ResourceEvent resourceEvent, boolean sendFlag, int extend) {
/* 517 */     PlayerCurrency.add(playerComponent, currencyType, num, extend);
/* 518 */     if (currencyType.getType() == CurrencyType.TotalCharge.getType()) {
/* 519 */       VipAccessBean vipAccessBean = (VipAccessBean)JsonTableService.getJsonData(playerComponent.getVip() + 1, VipAccessBean.class);
/* 520 */       if (vipAccessBean != null)
/*     */       {
/* 522 */         while (PlayerCurrency.get(playerComponent, CurrencyType.TotalCharge) >= vipAccessBean.getTotal()) {
/* 523 */           playerComponent.setVip((byte)vipAccessBean.getVipLevel());
/* 524 */           LogUtil.gameLog(LogType.VIP, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerComponent.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(vipAccessBean.getVipLevel()), TimeUtil.getFormatDate() });
/* 525 */           vipAccessBean = (VipAccessBean)JsonTableService.getJsonData(playerComponent.getVip() + 1, VipAccessBean.class);
/* 526 */           if (vipAccessBean == null) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 534 */     if (sendFlag && CurrencyType.EXP.getType() != currencyType.getType()) {
/* 535 */       LogUtil.gameLog(LogType.CURRENCY, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerComponent.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(currencyType.getType()), Long.valueOf(num), Long.valueOf(PlayerCurrency.get(playerComponent, currencyType)), TimeUtil.getFormatDate(), Integer.valueOf(resourceEvent.getType()), Integer.valueOf(1) });
/*     */     }
/*     */ 
/*     */     
/* 539 */     playerComponent.saveToDB();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkCurrency(IPlayer player, CurrencyType currencyType, long num) {
/* 551 */     if (num < 0L)
/* 552 */       return false; 
/* 553 */     if (CurrencyType.CCY_B == currencyType) {
/* 554 */       return (PlayerCurrency.get(player, CurrencyType.CCY) + PlayerCurrency.get(player, CurrencyType.CCY_B) >= num);
/*     */     }
/* 556 */     return PlayerCurrency.check(player, currencyType, num);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkCCY2Currency(IPlayer player, long num) {
/* 568 */     if (num < 0L)
/* 569 */       return false; 
/* 570 */     return (PlayerCurrency.get(player, CurrencyType.CCY) + PlayerCurrency.get(player, CurrencyType.CCY_B) >= num);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void decCCY2Currency(IPlayer player, long num, ResourceEvent resourceEvent, boolean sendFlag) {
/* 580 */     long ccyb = PlayerCurrency.get(player, CurrencyType.CCY_B);
/* 581 */     if (ccyb >= num) {
/* 582 */       decCurrency(player, CurrencyType.CCY_B, num, resourceEvent, sendFlag);
/*     */     } else {
/* 584 */       decCurrency(player, CurrencyType.CCY_B, ccyb, resourceEvent, sendFlag);
/* 585 */       decCurrency(player, CurrencyType.CCY, num - ccyb, resourceEvent, sendFlag);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void decCCY2Currency(IPlayer player, long num, ResourceEvent resourceEvent) {
/* 596 */     decCCY2Currency(player, num, resourceEvent, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void reward(RewardStruct rewardStruct, IPlayer player, ResourceEvent resourceEvent, boolean sendFlag) {
/* 601 */     ArrayList<Reward> list = new ArrayList<>();
/* 602 */     rewardStruct.getRewards(list);
/* 603 */     reward(list, player, resourceEvent, sendFlag);
/*     */   }
/*     */   
/*     */   public static void reward(ArrayList<Reward> rewards, IPlayer player, ResourceEvent resourceEvent, boolean sendFlag) {
/* 607 */     rewardGet(rewards, player, resourceEvent, sendFlag);
/*     */   }
/*     */   
/*     */   public static ArrayList<Reward> rewardGet(ArrayList<Reward> rewards, IPlayer player, ResourceEvent resourceEvent, boolean sendFlag) {
/* 611 */     return rewardGet2(rewards, player, player.getPlayerId(), resourceEvent, sendFlag);
/*     */   }
/*     */   public static ArrayList<Reward> rewardGet2(ArrayList<Reward> rewards, IPlayer player, long playerId, ResourceEvent resourceEvent, boolean sendFlag) {
/*     */     int i, j, k, m;
/* 615 */     BagComponent bagComponent = null;
/* 616 */     ActivityBagComponent activityBagComponent = null;
/* 617 */     PartnerComponent partnerComponent = null;
/* 618 */     EquipComponent equipComponent = null;
/* 619 */     RuneBagComponent runeBagComponent = null;
/* 620 */     if (null == player) {
/* 621 */       bagComponent = (BagComponent)LookUpService.createIfNotExist(playerId, BagComponent.class);
/* 622 */       activityBagComponent = (ActivityBagComponent)LookUpService.createIfNotExist(playerId, ActivityBagComponent.class);
/* 623 */       partnerComponent = (PartnerComponent)LookUpService.createIfNotExist(playerId, PartnerComponent.class);
/* 624 */       equipComponent = (EquipComponent)LookUpService.createIfNotExist(playerId, EquipComponent.class);
/* 625 */       runeBagComponent = (RuneBagComponent)LookUpService.createIfNotExist(playerId, RuneBagComponent.class);
/* 626 */       sendFlag = false;
/*     */     } else {
/* 628 */       bagComponent = (BagComponent)player.createIfNotExist(BagComponent.class);
/* 629 */       activityBagComponent = (ActivityBagComponent)player.createIfNotExist(ActivityBagComponent.class);
/* 630 */       partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 631 */       equipComponent = (EquipComponent)player.createIfNotExist(EquipComponent.class);
/* 632 */       runeBagComponent = (RuneBagComponent)player.createIfNotExist(RuneBagComponent.class);
/*     */     } 
/*     */     
/* 635 */     List<Reward> drops = new ArrayList<>();
/* 636 */     ArrayList<Reward> returnReward = new ArrayList<>();
/* 637 */     ArrayList<Reward> noticeReward = new ArrayList<>();
/* 638 */     boolean isExistItem = false;
/* 639 */     boolean isExistPartner = false;
/* 640 */     boolean isExistEquip = false;
/* 641 */     boolean isExistRune = false;
/* 642 */     int nowtime = TimeUtil.currentTime();
/* 643 */     for (Reward reward : rewards) {
/* 644 */       int type = reward.type;
/* 645 */       type = correctType(type, reward.id);
/* 646 */       if (3 != type) {
/* 647 */         returnReward.add(reward);
/* 648 */         addReward(bagComponent, activityBagComponent, partnerComponent, equipComponent, player, resourceEvent, sendFlag, type, reward.id, reward.num, nowtime, runeBagComponent);
/* 649 */         i = isExistItem | ((2 == type || 9 == type) ? 1 : 0);
/* 650 */         j = isExistPartner | ((type == 4) ? 1 : 0);
/* 651 */         k = isExistEquip | ((5 == type) ? 1 : 0);
/* 652 */         m = isExistRune | ((type == 7) ? 1 : 0);
/*     */         
/* 654 */         noticeReward.add(reward);
/*     */         continue;
/*     */       } 
/* 657 */       for (int n = 0; n < reward.num; n++) {
/* 658 */         DropBag.getDropReward(reward.id, drops);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 663 */     for (Reward reward : drops) {
/* 664 */       int type = reward.type;
/* 665 */       type = correctType(type, reward.id);
/* 666 */       addReward(bagComponent, activityBagComponent, partnerComponent, equipComponent, player, resourceEvent, sendFlag, type, reward.id, reward.num, nowtime, runeBagComponent);
/* 667 */       i |= (2 == type) ? 1 : 0;
/* 668 */       j |= (type == 4) ? 1 : 0;
/* 669 */       k |= (type == 5) ? 1 : 0;
/* 670 */       m |= (type == 7) ? 1 : 0;
/*     */       
/* 672 */       returnReward.add(reward);
/*     */       
/* 674 */       noticeReward.add(reward);
/*     */     } 
/*     */     
/* 677 */     if (i != 0)
/* 678 */       bagComponent.notice(); 
/* 679 */     if (sendFlag && !noticeReward.isEmpty())
/* 680 */       rewardNotice(noticeReward, player, resourceEvent); 
/* 681 */     if (j != 0) {
/* 682 */       partnerComponent.notice();
/*     */     }
/* 684 */     if (k != 0) {
/* 685 */       equipComponent.notice();
/*     */     }
/*     */     
/* 688 */     return returnReward;
/*     */   }
/*     */   
/*     */   public static ArrayList<Reward> rewardGet(ArrayList<Reward> rewards) {
/* 692 */     ArrayList<Reward> returnReward = new ArrayList<>();
/* 693 */     List<Reward> drops = new ArrayList<>();
/* 694 */     for (Reward reward : rewards) {
/* 695 */       int type = reward.type;
/* 696 */       type = correctType(type, reward.id);
/* 697 */       if (3 != type) {
/* 698 */         returnReward.add(reward); continue;
/*     */       } 
/* 700 */       for (int i = 0; i < reward.num; i++) {
/* 701 */         DropBag.getDropReward(reward.id, drops);
/*     */       }
/*     */     } 
/*     */     
/* 705 */     returnReward.addAll(drops);
/* 706 */     return returnReward;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addReward(BagComponent bagComponent, ActivityBagComponent activityBagComponent, PartnerComponent partnerComponent, EquipComponent equipComponent, IPlayer player, ResourceEvent resourceEvent, boolean sendFlag, int type, int id, long num, int nowtime, RuneBagComponent runeBagComponent) {
/* 718 */     if (1 == type) {
/*     */       
/* 720 */       addCurrency(player, CurrencyType.values()[id], num, resourceEvent, true, 0);
/* 721 */     } else if (2 == type) {
/* 722 */       bagComponent.addItem(id, (int)num, resourceEvent);
/* 723 */     } else if (4 == type) {
/* 724 */       for (int i = 0; i < num; i++) {
/* 725 */         boolean isHash = partnerComponent.checkPartner(id);
/* 726 */         FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(id, FighterBean.class);
/* 727 */         if (null == fighterBean) {
/*     */           return;
/*     */         }
/* 730 */         if (isHash) {
/* 731 */           ArrayList<Reward> rewardArrayList = new ArrayList<>();
/* 732 */           Reward reward1 = new Reward();
/* 733 */           reward1.type = 2;
/* 734 */           reward1.id = fighterBean.getPiece();
/* 735 */           reward1.num = fighterBean.getPieceNum();
/* 736 */           rewardArrayList.add(reward1);
/* 737 */           reward(rewardArrayList, player, resourceEvent, true);
/*     */         } else {
/* 739 */           partnerComponent.addPartner(player, id, resourceEvent);
/*     */         } 
/* 741 */         bagComponent.addItem(fighterBean.getCardBook(), 1, resourceEvent, true);
/* 742 */         RecruitUtil.sendRecruitNotice(fighterBean, player);
/*     */       } 
/* 744 */     } else if (5 == type) {
/* 745 */       for (int i = 0; i < num; i++) {
/* 746 */         equipComponent.addEquip(player, id, resourceEvent);
/*     */       }
/* 748 */     } else if (7 == type) {
/* 749 */       for (int i = 0; i < num; i++) {
/* 750 */         runeBagComponent.addRune(player, id, resourceEvent);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int correctType(int type, int itemId) {
/* 763 */     if (3 != type) {
/* 764 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(itemId, ItemBean.class);
/* 765 */       if (null == itemBean)
/* 766 */         return type; 
/* 767 */       return itemBean.getItemType();
/*     */     } 
/* 769 */     return type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void rewardBean(ArrayList<RewardBean> rewards, IPlayer player, ResourceEvent resourceEvent, boolean sendFlag) {
/* 781 */     reward(transform(rewards), player, resourceEvent, sendFlag);
/*     */   }
/*     */   
/*     */   public static ArrayList<Reward> rewardBeanGet(ArrayList<RewardBean> rewards, IPlayer player, ResourceEvent resourceEvent, boolean sendFlag) {
/* 785 */     return rewardGet(transform(rewards), player, resourceEvent, sendFlag);
/*     */   }
/*     */   
/*     */   public static ArrayList<Reward> rewardBeanGet(ArrayList<RewardBean> rewards) {
/* 789 */     return rewardGet(transform(rewards));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void rewardNotice(ArrayList<Reward> rewards, IPlayer player, ResourceEvent resourceEvent) {
/* 800 */     RewardNoticeResponse rewardNoticeResponse = new RewardNoticeResponse();
/* 801 */     rewardNoticeResponse.type = resourceEvent.getType();
/* 802 */     rewardNoticeResponse.rewards = rewards;
/* 803 */     player.getSession().sendMessage((ResponseBase)rewardNoticeResponse);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dropNotice(ArrayList<Reward> rewards, IPlayer player, long monsterId) {
/* 814 */     BagDropResponse bagDropResponse = new BagDropResponse();
/* 815 */     bagDropResponse.id = monsterId;
/* 816 */     bagDropResponse.rewards = rewards;
/* 817 */     player.getSession().sendMessage((ResponseBase)bagDropResponse);
/*     */   }
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
/*     */   public static ArrayList<Reward> rewardOverlyingGet(ArrayList<Reward> reward2, IPlayer player, ResourceEvent resourceEvent, boolean sendFlag) {
/* 830 */     Map<Long, Long> mapRewards = new HashMap<>();
/* 831 */     overlyingMap(mapRewards, reward2);
/* 832 */     ArrayList<Reward> reward3 = overlyingReward(mapRewards);
/* 833 */     if (!reward3.isEmpty())
/* 834 */       return rewardGet(reward3, player, resourceEvent, true); 
/* 835 */     return new ArrayList<>();
/*     */   }
/*     */   
/*     */   public static ArrayList<Reward> rewardOverlyingGet(ArrayList<Reward> reward2, IPlayer player, ResourceEvent resourceEvent) {
/* 839 */     return rewardOverlyingGet(reward2, player, resourceEvent, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void mergeRewardList(List<Reward> rewardBeanList) {
/* 848 */     if (null == rewardBeanList) {
/*     */       return;
/*     */     }
/* 851 */     Map<Integer, Reward> map = new HashMap<>();
/*     */     
/* 853 */     for (Reward reward : rewardBeanList) {
/* 854 */       Reward temp = map.getOrDefault(Integer.valueOf(reward.id), new Reward());
/* 855 */       temp.type = reward.type;
/* 856 */       temp.id = reward.id;
/* 857 */       temp.num += reward.num;
/* 858 */       map.put(Integer.valueOf(reward.id), temp);
/*     */     } 
/* 860 */     rewardBeanList.clear();
/* 861 */     rewardBeanList.addAll(map.values());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void clearCurrency(IPlayer player, CurrencyType currencyType, ResourceEvent resourceEvent) {
/* 872 */     long value = getPlayerCurrency(player, currencyType);
/* 873 */     if (value <= 0L) {
/*     */       return;
/*     */     }
/* 876 */     decCurrency(player, currencyType, value, resourceEvent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> handleRepeat(List<Reward> rewards) {
/* 886 */     Map<Integer, Reward> map = new HashMap<>();
/* 887 */     for (Reward reward : rewards) {
/* 888 */       Reward r = map.getOrDefault(Integer.valueOf(reward.id), new Reward());
/* 889 */       r.type = reward.type;
/* 890 */       r.id = reward.id;
/* 891 */       r.num += reward.num;
/* 892 */       map.put(Integer.valueOf(reward.id), r);
/*     */     } 
/* 894 */     return new ArrayList<>(map.values());
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\commo\\util\FinanceUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */