/*     */ package com.linlongyx.sanguo.webgame.app.shop;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ShopNormalBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ShopSecretiBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ShopParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.shop.ShopUtil;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShopComponent
/*     */   extends AbstractMapComponent<ShopEntity>
/*     */ {
/*     */   public ShopComponent(long playerId) {
/*  24 */     super(ShopEntity.class, playerId);
/*     */   }
/*     */   
/*     */   public ShopEntity getShopEntity(int goodsType) {
/*  28 */     return (ShopEntity)getEntity(String.valueOf(goodsType));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addShop(long playerId, int goodsType) {
/*  37 */     if (getShopEntity(goodsType) != null)
/*     */       return; 
/*  39 */     ShopEntity shopEntity = new ShopEntity(playerId, goodsType);
/*  40 */     addEntity((IEntity)shopEntity);
/*     */   }
/*     */   
/*     */   public void addShop(long playerId, int goodsType, int actId) {
/*  44 */     ShopEntity shopEntity = new ShopEntity(playerId, goodsType);
/*  45 */     shopEntity.setActId(actId);
/*  46 */     addEntity((IEntity)shopEntity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  51 */     if (time == 0) {
/*     */       
/*  53 */       ShopParameter shopParameter = (ShopParameter)ParameterConstant.getParameter(31);
/*  54 */       resetNormalShop(shopParameter);
/*  55 */       resetUnNormalShop(shopParameter);
/*  56 */     } else if (time == 18000) {
/*     */     
/*     */     } 
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  64 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  69 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public void updateSellsToDB(ShopEntity shopEntity) {
/*  73 */     getProxy().setUpdate(String.valueOf(shopEntity.getGoodsType()), "sells");
/*  74 */     getProxy().setUpdate(String.valueOf(shopEntity.getGoodsType()), "totalSells");
/*  75 */     getProxy().setUpdate(String.valueOf(shopEntity.getGoodsType()), "weekSells");
/*  76 */     getProxy().setUpdate(String.valueOf(shopEntity.getGoodsType()), "activitySells");
/*  77 */     getProxy().setUpdate(String.valueOf(shopEntity.getGoodsType()), "currStock");
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetToWeek(ShopEntity shopEntity) {
/*  82 */     shopEntity.setWeekSells(new HashMap<>());
/*  83 */     updateSellsToDB(shopEntity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetToAct(ShopEntity shopEntity) {
/*  88 */     shopEntity.setActivitySells(new HashMap<>());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetUnNormalShop(ShopParameter shopParameter) {
/*  97 */     ShopEntity shopEntity = getShopEntity(ShopUtil.ShopType.MysteryShop.getType());
/*  98 */     if (null != shopEntity) {
/*  99 */       shopEntity.setRefreshNum(0);
/* 100 */       shopEntity.setSells(new HashMap<>());
/*     */     } 
/* 102 */     ShopEntity cryShopEntity = getShopEntity(ShopUtil.ShopType.CrystalSoulShop.getType());
/* 103 */     if (null != cryShopEntity) {
/* 104 */       cryShopEntity.setRefreshNum(0);
/* 105 */       cryShopEntity.setSells(new HashMap<>());
/*     */     } 
/* 107 */     ShopEntity bloodShopEntity = getShopEntity(ShopUtil.ShopType.BloodCrystalsShop.getType());
/* 108 */     if (null != bloodShopEntity) {
/* 109 */       bloodShopEntity.setRefreshNum(0);
/* 110 */       bloodShopEntity.setSells(new HashMap<>());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     shopEntity = getShopEntity(ShopUtil.ShopType.SecretShop.getType());
/* 117 */     if (null != shopEntity) {
/* 118 */       shopEntity.setSells(new HashMap<>());
/* 119 */       updateSellsToDB(shopEntity);
/* 120 */       int shopLevel = ShopUtil.updateShopLevel(this.playerId);
/* 121 */       List<Integer> arrayList = (List<Integer>)shopParameter.getSecretShopMap().get(Integer.valueOf(shopLevel));
/* 122 */       if (null != arrayList) {
/* 123 */         for (Iterator<Integer> iterator = arrayList.iterator(); iterator.hasNext(); ) { int goodId = ((Integer)iterator.next()).intValue();
/* 124 */           ShopSecretiBean shopSecretiBean = (ShopSecretiBean)JsonTableService.getJsonData(shopLevel, ShopSecretiBean.class);
/* 125 */           ShopSecretiBean.GoodsIdBean goodsIdBean = (ShopSecretiBean.GoodsIdBean)shopSecretiBean.getGoodsId().get(Integer.valueOf(goodId));
/* 126 */           if (null != shopEntity && goodsIdBean.getStock() != 0 && goodsIdBean.getLimitType() == 0) {
/* 127 */             int currStock = ((Integer)shopEntity.getCurrStock().getOrDefault(Integer.valueOf(goodId), Integer.valueOf(0))).intValue();
/* 128 */             if (currStock >= goodsIdBean.getMidStock()) {
/*     */               continue;
/*     */             }
/* 131 */             currStock += goodsIdBean.getAddStock();
/* 132 */             if (currStock >= goodsIdBean.getMidStock()) {
/* 133 */               currStock = goodsIdBean.getMidStock();
/*     */             }
/*     */             
/* 136 */             shopEntity.getCurrStock().put(Integer.valueOf(goodId), Integer.valueOf(currStock));
/* 137 */             shopEntity.setCurrStock(shopEntity.getCurrStock());
/*     */           }  }
/*     */       
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void resetNormalShop(ShopParameter shopParameter) {
/* 145 */     ShopEntity shopEntity = null;
/*     */     
/* 147 */     for (ShopUtil.ShopType shopType : ShopUtil.ShopType.values()) {
/* 148 */       int type = shopType.getType();
/* 149 */       shopEntity = getShopEntity(type);
/* 150 */       List<Integer> arrayList = shopParameter.getGoodsData(type);
/* 151 */       if (null != arrayList) {
/*     */ 
/*     */         
/* 154 */         for (Iterator<Integer> iterator = arrayList.iterator(); iterator.hasNext(); ) { int goodId = ((Integer)iterator.next()).intValue();
/* 155 */           ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(goodId, ShopNormalBean.class);
/* 156 */           if (null != shopEntity && shopNormalBean.getStock() != 0 && shopNormalBean.getLimitType() == 0) {
/* 157 */             int currStock = ((Integer)shopEntity.getCurrStock().getOrDefault(Integer.valueOf(goodId), Integer.valueOf(0))).intValue();
/* 158 */             if (currStock >= shopNormalBean.getMidStock()) {
/*     */               continue;
/*     */             }
/* 161 */             currStock += shopNormalBean.getAddStock();
/* 162 */             if (currStock >= shopNormalBean.getMidStock()) {
/* 163 */               currStock = shopNormalBean.getMidStock();
/*     */             }
/*     */             
/* 166 */             shopEntity.getCurrStock().put(Integer.valueOf(goodId), Integer.valueOf(currStock));
/* 167 */             shopEntity.setCurrStock(shopEntity.getCurrStock());
/*     */           }  }
/*     */ 
/*     */         
/* 171 */         if (null != shopEntity) {
/* 172 */           shopEntity.setSells(new HashMap<>());
/* 173 */           updateSellsToDB(shopEntity);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\shop\ShopComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */