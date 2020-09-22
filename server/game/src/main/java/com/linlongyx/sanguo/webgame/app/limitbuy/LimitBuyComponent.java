/*     */ package com.linlongyx.sanguo.webgame.app.limitbuy;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.SpecialOfferBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LimitBuyActParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LimitBuyComponent
/*     */   extends AbstractMapComponent<LimitBuyEntity>
/*     */ {
/*     */   public LimitBuyComponent(long playerId) {
/*  26 */     super(LimitBuyEntity.class, playerId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  32 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  37 */     this.playerId = playerId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  43 */     super.init();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  48 */     if (time == 0)
/*     */     {
/*  50 */       resertLimitBuyGoods();
/*     */     }
/*  52 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public LimitBuyEntity getEntity(int id) {
/*  57 */     LimitBuyEntity limitBuyEntity = (LimitBuyEntity)getEntity(String.valueOf(id));
/*  58 */     if (null == limitBuyEntity) {
/*  59 */       limitBuyEntity = new LimitBuyEntity(this.playerId, id);
/*  60 */       limitBuyEntity.setOpen(true);
/*  61 */       addEntity((IEntity)limitBuyEntity);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  66 */     return limitBuyEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resertLimitBuyGoods() {
/*  74 */     LimitBuyActParameter limitBuyActParameter = (LimitBuyActParameter)ParameterConstant.getParameter(68);
/*  75 */     List<Integer> list = limitBuyActParameter.getLimitBuyAct(true);
/*  76 */     if (!list.isEmpty()) {
/*  77 */       LimitBuyComponent limitBuyComponent = (LimitBuyComponent)LookUpService.getComponent(this.playerId, LimitBuyComponent.class);
/*  78 */       Map<Integer, FestivalTime> festivalTimes = limitBuyActParameter.getActTimes();
/*  79 */       for (Integer actId : list) {
/*  80 */         if (festivalTimes.containsKey(actId) && 
/*  81 */           limitBuyActParameter.isLimitBuyAct(actId.intValue())) {
/*  82 */           SpecialOfferBean specialOfferBean = (SpecialOfferBean)JsonTableService.getJsonData(actId.intValue(), SpecialOfferBean.class);
/*  83 */           if (specialOfferBean.getActivityType() == 1) {
/*  84 */             limitBuyComponent.setLimitBuyGoods(actId.intValue(), new HashMap<>());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Integer, Integer> getLimitBuyGoods(int id) {
/*  93 */     return getEntity(id).getLimitBuyGoods();
/*     */   }
/*     */   
/*     */   public void setLimitBuyGoods(int id, Map<Integer, Integer> limitBuyGoods) {
/*  97 */     getEntity(id).setLimitBuyGoods(limitBuyGoods);
/*     */   }
/*     */   
/*     */   public void updateOpenDB(int id) {
/* 101 */     getProxy().setUpdate(String.valueOf(id), "open");
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\limitbuy\LimitBuyComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */