/*     */ package com.linlongyx.sanguo.webgame.processors.shop;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.secreti.SecretiComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.shop.ShopComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.shop.ShopEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.SecretiBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ShopNormalBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ShopSecretiBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ShopParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.log.LogType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.recruit.RecruitUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GoodsData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class ShopUtil {
/*     */   public static final int LIMIT_BUY_TYPE_NOT_LIMIT = 0;
/*     */   public static final int LIMIT_BUY_TYPE_DAILY = 1;
/*     */   public static final int LIMIT_BUY_TYPE_TOTAL = 2;
/*     */   public static final int LIMIT_BUY_TYPE_WEEK = 3;
/*     */   public static final int LIMIT_BUY_TYPE_ACT = 4;
/*     */   
/*     */   public enum ShopType {
/*  44 */     ItemShop(1, "道具商店"),
/*  45 */     PartnerShop(2, "武将商店"),
/*  46 */     EquipShop(3, "装备商店"),
/*  47 */     ArenaShop(4, "竞技商店"),
/*  48 */     ArenaRewardShop(5, "竞技奖励商店"),
/*  49 */     FriendShop(6, "好友商店"),
/*  50 */     UnparalleledShop(7, "无双商店"),
/*  51 */     UnparalleledRewardShop(8, "无双奖励商店"),
/*  52 */     GroupShop(9, "军团商店"),
/*  53 */     GroupRewardShop(10, "军团奖励商店"),
/*  54 */     SoulShop(11, "将魂商店"),
/*  55 */     BossShop(12, "boss奖励商店"),
/*  56 */     TurnplateShop(13, "天金转盘商店"),
/*  57 */     RedEquipShop(14, "红金装备商店"),
/*  58 */     GuessShop(15, "竞猜商店"),
/*  59 */     InviteShop(16, "求助商店"),
/*  60 */     SoulsShop(17, "英魂商店"),
/*  61 */     ExaminationShop(18, "科举商店"),
/*     */     
/*  63 */     SecretShop(96, "秘境商店"),
/*  64 */     BloodCrystalsShop(97, "血晶商店"),
/*  65 */     CrystalSoulShop(98, "晶魂商店"),
/*  66 */     QQVIPShop(99, "玩吧特价礼包商店"),
/*  67 */     MysteryShop(100, "神秘商店");
/*     */     
/*     */     private int type;
/*     */     private String name;
/*     */     
/*     */     ShopType(int type, String name) {
/*  73 */       setType(type);
/*  74 */       setName(name);
/*     */     }
/*     */     
/*     */     public int getType() {
/*  78 */       return this.type;
/*     */     }
/*     */     
/*     */     public void setType(int type) {
/*  82 */       this.type = type;
/*     */     }
/*     */     
/*     */     public String getName() {
/*  86 */       return this.name;
/*     */     }
/*     */     
/*     */     public void setName(String name) {
/*  90 */       this.name = name;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void openShop(IPlayer iPlayer, PlayerComponent playerComponent) {
/*  98 */     ShopComponent shopComponent = (ShopComponent)iPlayer.createIfNotExist(ShopComponent.class);
/*  99 */     if (null == shopComponent)
/* 100 */       return;  ShopParameter parameter = (ShopParameter)ParameterConstant.getParameter(31);
/* 101 */     boolean isOpen = false;
/* 102 */     for (Iterator<Integer> iterator = parameter.getNormalConfig().keySet().iterator(); iterator.hasNext(); ) { int list = ((Integer)iterator.next()).intValue();
/* 103 */       ShopEntity shopEntity = shopComponent.getShopEntity(list);
/* 104 */       if (null != shopEntity)
/* 105 */         continue;  shopComponent.addShop(playerComponent.getPlayerId(), list);
/* 106 */       shopEntity = shopComponent.getShopEntity(list);
/* 107 */       shopEntity.setRefreshTime(TimeUtil.currentTime());
/* 108 */       isOpen = true; }
/*     */     
/* 110 */     if (isOpen) {
/* 111 */       shopComponent.saveAllToDB();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static GoodsData transform(int goodsId, int sells, int type, int totalsSells, int weekSells, int actSells, int stock) {
/* 118 */     GoodsData goodsData = new GoodsData();
/* 119 */     goodsData.goodsId = goodsId;
/* 120 */     goodsData.sells = sells;
/* 121 */     goodsData.type = type;
/* 122 */     goodsData.totalsSells = totalsSells;
/* 123 */     goodsData.weekSells = weekSells;
/* 124 */     goodsData.actSells = actSells;
/* 125 */     goodsData.stock = stock;
/* 126 */     return goodsData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getDay() {
/* 135 */     int day = TimeUtil.getDayDisTime(MContext.getOpenTimeInt());
/* 136 */     day %= 7;
/* 137 */     if (0 == day) {
/* 138 */       return 7;
/*     */     }
/* 140 */     return day;
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
/*     */   public static void grantReward(ArrayList<RewardBean> rewardBeans, IPlayerSession playerSession, int num, ResourceEvent resourceEvent, int shopType, int currencyType, int currencyNum) {
/* 164 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 165 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().getComponent(BagComponent.class);
/* 166 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().getComponent(PartnerComponent.class);
/* 167 */     EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().getComponent(EquipComponent.class);
/* 168 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 169 */     RuneBagComponent runeBagComponent = (RuneBagComponent)playerSession.getPlayer().createIfNotExist(RuneBagComponent.class);
/* 170 */     int now = TimeUtil.currentTime();
/* 171 */     for (RewardBean rewardBean : rewardBeans) {
/* 172 */       int i; Reward reward = new Reward();
/* 173 */       int type = rewardBean.getType();
/* 174 */       type = FinanceUtil.correctType(type, rewardBean.getId());
/* 175 */       reward.type = (byte)type;
/* 176 */       reward.num = rewardBean.getNum() * num;
/* 177 */       reward.id = rewardBean.getId();
/* 178 */       rewards.add(reward);
/*     */       
/* 180 */       LogUtil.gameLog(LogType.MALL, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Integer.valueOf(playerComponent.getChannel()), Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(rewardBean.getId()), TimeUtil.getFormatDate(), Integer.valueOf(shopType), Long.valueOf(reward.num), Integer.valueOf(currencyType), Integer.valueOf(currencyNum) });
/* 181 */       switch (type) {
/*     */         case 2:
/* 183 */           bagComponent.addItem(rewardBean.getId(), (int)(rewardBean.getNum() * num), resourceEvent, true);
/*     */         
/*     */         case 1:
/* 186 */           FinanceUtil.addCurrency(playerSession.getPlayer(), CurrencyType.values()[rewardBean.getId()], rewardBean.getNum() * num, resourceEvent, true, 0);
/*     */         
/*     */         case 4:
/* 189 */           for (i = 0; i < num; i++) {
/* 190 */             boolean isHash = partnerComponent.checkPartner(rewardBean.getId());
/* 191 */             FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(rewardBean.getId(), FighterBean.class);
/* 192 */             if (null == fighterBean) {
/*     */               return;
/*     */             }
/* 195 */             if (isHash) {
/* 196 */               ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(fighterBean.getPiece(), ItemBean.class);
/* 197 */               if (null == itemBean) {
/*     */                 return;
/*     */               }
/*     */               
/* 201 */               ArrayList<Reward> rewardList = FinanceUtil.transformPiceReward(itemBean.getReward(), fighterBean.getPieceNum());
/* 202 */               FinanceUtil.reward(rewardList, playerSession.getPlayer(), resourceEvent, true);
/*     */             } else {
/* 204 */               partnerComponent.addPartner(playerSession.getPlayer(), rewardBean.getId(), resourceEvent);
/*     */             } 
/* 206 */             bagComponent.addItem(fighterBean.getCardBook(), 1, resourceEvent, true);
/* 207 */             RecruitUtil.sendRecruitNotice(fighterBean, playerSession.getPlayer());
/*     */           } 
/*     */         
/*     */         case 5:
/* 211 */           for (i = 0; i < reward.num; i++) {
/* 212 */             equipComponent.addEquip(playerSession.getPlayer(), rewardBean.getId(), resourceEvent);
/*     */           }
/*     */         
/*     */         case 7:
/* 216 */           for (i = 0; i < reward.num; i++) {
/* 217 */             runeBagComponent.addRune(playerSession.getPlayer(), reward.id, resourceEvent);
/*     */           }
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 224 */     bagComponent.notice();
/* 225 */     partnerComponent.notice();
/* 226 */     equipComponent.notice();
/* 227 */     FinanceUtil.rewardNotice(rewards, playerSession.getPlayer(), resourceEvent);
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
/*     */   public static ArrayList<Integer> getShopList(ArrayList<Integer> shopList, PlayerComponent playerComponent) {
/* 310 */     ArrayList<Integer> newShopList = new ArrayList<>();
/* 311 */     for (Iterator<Integer> iterator = shopList.iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 312 */       boolean enough = false;
/* 313 */       ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id, ShopNormalBean.class);
/* 314 */       if (null != shopNormalBean) {
/* 315 */         for (ShopNormalBean.BuyConditionBean conditionBean : shopNormalBean.getBuyCondition()) {
/* 316 */           if (conditionBean.getType() == 1 && playerComponent.getLevel() < conditionBean.getValue()) {
/* 317 */             enough = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 321 */         if (enough) {
/*     */           continue;
/*     */         }
/* 324 */         newShopList.add(Integer.valueOf(id));
/*     */       }  }
/*     */     
/* 327 */     return newShopList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Integer> getSecritShopList(ArrayList<Integer> shopList, PlayerComponent playerComponent, ShopSecretiBean shopSecretiBean) {
/* 338 */     ArrayList<Integer> newShopList = new ArrayList<>();
/* 339 */     for (Iterator<Integer> iterator = shopList.iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 340 */       boolean enough = false;
/* 341 */       ShopSecretiBean.GoodsIdBean goodsIdBean = (ShopSecretiBean.GoodsIdBean)shopSecretiBean.getGoodsId().get(Integer.valueOf(id));
/* 342 */       if (null != goodsIdBean) {
/* 343 */         for (ShopSecretiBean.GoodsIdBean.BuyConditionBean conditionBean : goodsIdBean.getBuyCondition()) {
/* 344 */           if (conditionBean.getType() == 1 && playerComponent.getLevel() < conditionBean.getValue()) {
/* 345 */             enough = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 349 */         if (enough) {
/*     */           continue;
/*     */         }
/* 352 */         newShopList.add(Integer.valueOf(id));
/*     */       }  }
/*     */     
/* 355 */     return newShopList;
/*     */   }
/*     */   
/* 358 */   public static int shopLevel = 1;
/*     */   
/*     */   public static void setShopLevel(int shopLevel) {
/* 361 */     ShopUtil.shopLevel = shopLevel;
/*     */   }
/*     */   
/*     */   public static int updateShopLevel(long playerId) {
/* 365 */     SecretiComponent secretiComponent = (SecretiComponent)LookUpService.getComponent(playerId, SecretiComponent.class);
/* 366 */     int currLayer = secretiComponent.getMaxLayer();
/* 367 */     if (null == secretiComponent) {
/* 368 */       return shopLevel;
/*     */     }
/* 370 */     SecretiBean secretiBean = (SecretiBean)JsonTableService.getJsonData(currLayer, SecretiBean.class);
/* 371 */     if (null == secretiBean) {
/* 372 */       LogUtil.errorLog(new Object[] { "ShopUtil:updateShopLevel", Long.valueOf(playerId), Integer.valueOf(currLayer) });
/* 373 */       return shopLevel;
/*     */     } 
/* 375 */     setShopLevel(secretiBean.getShop());
/* 376 */     return shopLevel;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\shop\ShopUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */