/*     */ package com.linlongyx.sanguo.webgame.common.reward;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.VipAccessBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.Map;
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
/*     */ public class PlayerCurrency
/*     */ {
/*     */   public static long get(IPlayer player, CurrencyType type) {
/*  36 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  37 */     return get(playerComponent, type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long get(PlayerComponent playerComponent, CurrencyType type) {
/*  48 */     return playerComponent.getCurrency(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void set(PlayerComponent component, CurrencyType type, long value) {
/*  59 */     component.setCurrency(type, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void add(IPlayer player, CurrencyType type, long value, int extend) {
/*  64 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  65 */     add(playerComponent, type, value, extend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void add(PlayerComponent playerComponent, CurrencyType type, long value, int extend) {
/*  76 */     if (value < 0L)
/*     */       return; 
/*  78 */     if (type.getType() == CurrencyType.EXP.getType()) {
/*  79 */       IPlayer player = LookUpService.getByPlayerId(playerComponent.getPlayerId());
/*  80 */       if (player != null) {
/*  81 */         if (playerComponent.getLevel() <= extend) {
/*  82 */           PlayerUtil.addLevel(player);
/*     */         } else {
/*  84 */           PlayerUtil.addExp(player, value);
/*     */         } 
/*     */         return;
/*     */       } 
/*  88 */     } else if (type.getType() == CurrencyType.Contribute.getType()) {
/*  89 */       GroupMemberComponent groupMemberComponent = (GroupMemberComponent)LookUpService.getComponent(playerComponent.getPlayerId(), GroupMemberComponent.class);
/*  90 */       if (null != groupMemberComponent && groupMemberComponent.getId() != 0L) {
/*  91 */         groupMemberComponent.setTotalOffer(groupMemberComponent.getTotalOffer() + value);
/*  92 */         groupMemberComponent.saveToDB();
/*     */       } 
/*     */     } 
/*  95 */     long v = get(playerComponent, type);
/*  96 */     set(playerComponent, type, value + v);
/*  97 */     ExtendComponent extendComponent = (ExtendComponent)LookUpService.getComponent(playerComponent.getPlayerId(), ExtendComponent.class);
/*  98 */     if (null != extendComponent) {
/*  99 */       Map<Integer, Long> totalCurrency = extendComponent.getTotalCurrency();
/* 100 */       totalCurrency.put(Integer.valueOf(type.getType()), Long.valueOf(((Long)totalCurrency.getOrDefault(Integer.valueOf(type.getType()), Long.valueOf(0L))).longValue() + value));
/* 101 */       extendComponent.setTotalCurrency(totalCurrency);
/* 102 */       extendComponent.saveToDB();
/*     */     } 
/* 104 */     assert checkOverFlow(value + v);
/* 105 */     if (checkOverFlow(value + v))
/* 106 */     { set(playerComponent, type, value + v); }
/* 107 */     else { set(playerComponent, type, Long.MAX_VALUE); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dec(IPlayer player, CurrencyType type, long value, ResourceEvent resourceEvent) {
/* 118 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 119 */     dec(playerComponent, type, value, resourceEvent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dec(PlayerComponent playerComponent, CurrencyType type, long value, ResourceEvent resourceEvent) {
/* 130 */     if (value < 0L)
/*     */       return; 
/* 132 */     long v = get(playerComponent, type);
/*     */     
/* 134 */     if (check(playerComponent, type, value)) {
/* 135 */       set(playerComponent, type, v - value);
/*     */     } else {
/* 137 */       set(playerComponent, type, 0L);
/*     */     } 
/*     */     
/* 140 */     ExtendComponent extendComponent = (ExtendComponent)LookUpService.getComponent(playerComponent.getPlayerId(), ExtendComponent.class);
/* 141 */     if (null != extendComponent && resourceEvent.getType() != ResourceEvent.FortuneCatCost.getType() && resourceEvent.getType() != ResourceEvent.FortunePigCost.getType()) {
/* 142 */       Map<Integer, Long> consumeCurrency = extendComponent.getConsumeCurrency();
/* 143 */       consumeCurrency.put(Integer.valueOf(type.getType()), Long.valueOf(((Long)consumeCurrency.getOrDefault(Integer.valueOf(type.getType()), Long.valueOf(0L))).longValue() + value));
/* 144 */       extendComponent.setConsumeCurrency(consumeCurrency);
/*     */     } 
/*     */     
/* 147 */     if (type.getType() == CurrencyType.CCY.getType() && resourceEvent.getType() != ResourceEvent.FortuneCatCost.getType() && resourceEvent.getType() != ResourceEvent.FortunePigCost.getType()) {
/* 148 */       TaskComponent taskComponent = (TaskComponent)LookUpService.getComponent(playerComponent.getPlayerId(), TaskComponent.class);
/* 149 */       if (taskComponent != null) {
/* 150 */         taskComponent.refreshSchedule(TaskType.ConsumeCCY, 0, (int)value);
/* 151 */         taskComponent.refreshSchedule(TaskType.ConsumeTotalCCY, 0, 0L);
/*     */       } 
/*     */     } 
/*     */     
/* 155 */     if (type.getType() == CurrencyType.TotalCharge.getType()) {
/* 156 */       Map<Integer, Object> map = JsonTableService.getJsonTable(VipAccessBean.class);
/* 157 */       for (Object o : map.values()) {
/* 158 */         VipAccessBean vipAccessBean = (VipAccessBean)o;
/* 159 */         if (vipAccessBean != null)
/*     */         {
/* 161 */           while (get(playerComponent, CurrencyType.TotalCharge) >= vipAccessBean.getTotal()) {
/* 162 */             playerComponent.setVip((byte)vipAccessBean.getVipLevel());
/* 163 */             vipAccessBean = (VipAccessBean)JsonTableService.getJsonData(playerComponent.getVip() + 1, VipAccessBean.class);
/* 164 */             if (vipAccessBean == null) {
/*     */               break;
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/* 170 */       LogUtil.gameLog(LogType.VIP, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerComponent.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Byte.valueOf(playerComponent.getVip()), TimeUtil.getFormatDate() });
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
/*     */   public static boolean check(IPlayer player, CurrencyType type, long value) {
/* 183 */     if (value < 0L)
/* 184 */       return false; 
/* 185 */     long v = get(player, type);
/* 186 */     return (v >= value);
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
/*     */   public static boolean check(PlayerComponent component, CurrencyType type, long value) {
/* 198 */     if (value < 0L)
/* 199 */       return false; 
/* 200 */     long v = get(component, type);
/* 201 */     return (v >= value);
/*     */   }
/*     */   
/*     */   public static boolean checkOverFlow(long value) {
/* 205 */     return (value < Long.MAX_VALUE);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\reward\PlayerCurrency.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */