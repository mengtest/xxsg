/*     */ package com.linlongyx.sanguo.webgame.app.zodiac;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZodiacBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZodiacShopBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ZodiacParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ZodiacComponent
/*     */   extends AbstractMapComponent<ZodiacEntity> {
/*     */   public ZodiacComponent(long playerId) {
/*  22 */     super(ZodiacEntity.class, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  27 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  32 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public void init() {
/*  36 */     super.init();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  41 */     if (time == 0) {
/*  42 */       resertDaily();
/*  43 */       resertEndAct();
/*     */     } 
/*     */     
/*  46 */     return true;
/*     */   }
/*     */   public ZodiacEntity getEntity(int id) {
/*  49 */     ZodiacEntity zodiacEntity = (ZodiacEntity)getEntity(String.valueOf(id));
/*  50 */     if (null == zodiacEntity) {
/*  51 */       zodiacEntity = new ZodiacEntity(this.playerId, id);
/*     */       
/*  53 */       PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(this.playerId, PlayerComponent.class);
/*  54 */       zodiacEntity.setFirstLever(playerComponent.getLevel());
/*  55 */       addEntity((IEntity)zodiacEntity);
/*     */     } 
/*  57 */     return zodiacEntity;
/*     */   }
/*     */   public Map<Integer, Integer> getZodiacGoods(int id) {
/*  60 */     return getEntity(id).getZodiacGoods();
/*     */   }
/*     */   
/*     */   public void setZodiacGoods(int id, Map<Integer, Integer> zodiacGoods) {
/*  64 */     getEntity(id).setZodiacGoods(zodiacGoods);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getZodiaState(int id) {
/*  68 */     return getEntity(id).getZodiaState();
/*     */   }
/*     */   
/*     */   public void setZodiaState(int id, Map<Integer, Integer> zodiaState) {
/*  72 */     getEntity(id).setZodiaState(zodiaState);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getZodiaTasks(int id) {
/*  76 */     return getEntity(id).getZodiaTasks();
/*     */   }
/*     */   
/*     */   public void setZodiaTasks(int id, Map<Integer, Integer> zodiaTasks) {
/*  80 */     getEntity(id).setZodiaTasks(zodiaTasks);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getZodiacShop(int id) {
/*  84 */     return getEntity(id).getZodiacShop();
/*     */   }
/*     */   
/*     */   public void setZodiacShop(int id, Map<Integer, Integer> zodiacShop) {
/*  88 */     getEntity(id).setZodiacShop(zodiacShop);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFirstLever(int id, int firstLever) {
/*  93 */     getEntity(id).setFirstLever(firstLever);
/*     */   }
/*     */   
/*     */   public int getFirstLever(int id) {
/*  97 */     return getEntity(id).getFirstLever();
/*     */   }
/*     */   
/*     */   public void setOpen(int id, boolean state) {
/* 101 */     getEntity(id).setOpen(state);
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
/*     */   public void resertDaily() {
/* 113 */     ZodiacParameter zodiacParameter = (ZodiacParameter)ParameterConstant.getParameter(73);
/* 114 */     List<Integer> list = zodiacParameter.getZodiacAct(true);
/* 115 */     if (!list.isEmpty()) {
/*     */       
/* 117 */       Map<Integer, FestivalTime> festivalTimes = zodiacParameter.getActTimes();
/* 118 */       PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(this.playerId, PlayerComponent.class);
/* 119 */       for (Integer actId : list) {
/* 120 */         if (festivalTimes.containsKey(actId)) {
/* 121 */           ZodiacEntity zodiacEntity = getEntity(actId.intValue());
/* 122 */           zodiacEntity.setFirstLever(playerComponent.getLevel());
/* 123 */           setZodiaState(actId.intValue(), new HashMap<>());
/* 124 */           setZodiaTasks(actId.intValue(), new HashMap<>());
/* 125 */           ZodiacBean zodiacBean = (ZodiacBean)JsonTableService.getJsonData(actId.intValue(), ZodiacBean.class);
/* 126 */           if (zodiacBean == null) {
/*     */             continue;
/*     */           }
/* 129 */           Map<Integer, Integer> zodiacShop = getZodiacShop(actId.intValue());
/* 130 */           for (Integer shop : zodiacBean.getShopList()) {
/* 131 */             ZodiacShopBean zodiacShopBean = (ZodiacShopBean)JsonTableService.getJsonData(shop.intValue(), ZodiacShopBean.class);
/* 132 */             if (zodiacShopBean == null) {
/*     */               continue;
/*     */             }
/* 135 */             if (zodiacShopBean.getLimitType() == 1) {
/* 136 */               zodiacShop.put(Integer.valueOf(zodiacShopBean.getId()), Integer.valueOf(0));
/*     */             }
/*     */           } 
/* 139 */           setZodiacShop(actId.intValue(), zodiacShop);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resertEndAct() {
/* 149 */     ZodiacParameter zodiacParameter = (ZodiacParameter)ParameterConstant.getParameter(73);
/* 150 */     List<Integer> list = zodiacParameter.getZodiacAct(false);
/* 151 */     if (!list.isEmpty()) {
/* 152 */       Map<Integer, FestivalTime> festivalTimes = zodiacParameter.getActTimes();
/* 153 */       for (Integer actId : list) {
/* 154 */         if (festivalTimes.containsKey(actId)) {
/* 155 */           setZodiacGoods(actId.intValue(), new HashMap<>());
/* 156 */           setZodiacShop(actId.intValue(), new HashMap<>());
/* 157 */           setOpen(actId.intValue(), false);
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
/* 173 */           zodiacParameter.getZodiacAct(true);
/* 174 */           List<Integer> openList = zodiacParameter.getZodiacAct(true);
/* 175 */           if (openList.isEmpty()) {
/* 176 */             BagComponent bagComponent = (BagComponent)LookUpService.getComponent(this.playerId, BagComponent.class);
/* 177 */             int num = (int)bagComponent.getItemNum(zodiacParameter.getItemId());
/* 178 */             if (num > 0)
/* 179 */               bagComponent.deleteItem(zodiacParameter.getItemId(), num, ResourceEvent.ZodiacReward, true); 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\zodiac\ZodiacComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */