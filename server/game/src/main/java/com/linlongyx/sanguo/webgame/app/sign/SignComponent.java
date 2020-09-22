/*     */ package com.linlongyx.sanguo.webgame.app.sign;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.proxy.IEntityProxy;
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.CheckDeluxeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.CheckFreeBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SignComponent
/*     */   extends AbstractComponent<SignEntity>
/*     */ {
/*     */   public SignComponent(long playerId) {
/*  24 */     super(SignEntity.class, playerId);
/*  25 */     this.playerId = playerId;
/*  26 */     makeKey();
/*  27 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  33 */     if (time == 0) {
/*     */ 
/*     */       
/*  36 */       setTodayReward(new ArrayList<>());
/*  37 */       setSignToday(false);
/*  38 */       setDayCharge(0L);
/*     */       
/*  40 */       Map<Integer, Object> signMap = JsonTableService.getJsonTable(CheckFreeBean.class);
/*  41 */       if (getSignReward().size() >= signMap.size()) {
/*  42 */         setSignCount(0);
/*  43 */         setSignReward(new HashMap<>());
/*     */       } 
/*     */     } 
/*  46 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkDailyMail() {
/*  51 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  52 */     Map<Integer, Object> map = JsonTableService.getJsonTable(CheckDeluxeBean.class);
/*  53 */     for (Object object : map.values()) {
/*  54 */       CheckDeluxeBean checkDeluxeBean = (CheckDeluxeBean)object;
/*  55 */       if (checkDeluxeBean.getConnectortype() == PlayerUtil.getPlatformType() && checkDeluxeBean.getLevel() == getShowLevel() && checkDeluxeBean.getType() == 0 && getTodayReward().indexOf(Integer.valueOf(checkDeluxeBean.getRmb())) < 0 && getDayCharge() >= checkDeluxeBean.getRmb()) {
/*  56 */         rewards.addAll(FinanceUtil.transform(checkDeluxeBean.getReward()));
/*     */       }
/*     */     } 
/*  59 */     if (rewards.size() > 0) {
/*  60 */       String title = LanguageConstant.getLanguage(7601);
/*  61 */       String content = LanguageConstant.getAndReplaceLanguage(7602, new String[0]);
/*  62 */       MailUtil.sendSysMail(this.playerId, rewards, title, content);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkWeekMail() {
/*  68 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  69 */     Map<Integer, Object> map = JsonTableService.getJsonTable(CheckDeluxeBean.class);
/*  70 */     for (Object object : map.values()) {
/*  71 */       CheckDeluxeBean checkDeluxeBean = (CheckDeluxeBean)object;
/*  72 */       if (checkDeluxeBean.getConnectortype() == PlayerUtil.getPlatformType() && checkDeluxeBean.getLevel() == getShowLevel() && checkDeluxeBean.getType() == 1 && getWeekReward().indexOf(Integer.valueOf(checkDeluxeBean.getRmb())) < 0 && getWeekCharge() >= checkDeluxeBean.getRmb()) {
/*  73 */         rewards.addAll(FinanceUtil.transform(checkDeluxeBean.getReward()));
/*     */       }
/*     */     } 
/*  76 */     if (rewards.size() > 0) {
/*  77 */       String title = LanguageConstant.getLanguage(7603);
/*  78 */       String content = LanguageConstant.getAndReplaceLanguage(7604, new String[0]);
/*  79 */       MailUtil.sendSysMail(this.playerId, rewards, title, content);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  86 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  91 */     this.playerId = playerId;
/*  92 */     this.proxy.setEntityStatus(IEntityProxy.ENTITY_STATUS.STATUS_ADD);
/*     */   }
/*     */   
/*     */   public boolean isSignToday() {
/*  96 */     return ((SignEntity)getEntity()).isSignToday();
/*     */   }
/*     */   
/*     */   public void setSignToday(boolean signToday) {
/* 100 */     ((SignEntity)getEntity()).setSignToday(signToday);
/*     */   }
/*     */   
/*     */   public int getSignCount() {
/* 104 */     return ((SignEntity)getEntity()).getSignCount();
/*     */   }
/*     */   
/*     */   public void setSignCount(int signCount) {
/* 108 */     ((SignEntity)getEntity()).setSignCount(signCount);
/*     */   }
/*     */   
/*     */   public long getWeekCharge() {
/* 112 */     return ((SignEntity)getEntity()).getWeekCharge();
/*     */   }
/*     */   
/*     */   public void setWeekCharge(long weekCharge) {
/* 116 */     ((SignEntity)getEntity()).setWeekCharge(weekCharge);
/*     */   }
/*     */   
/*     */   public long getDayCharge() {
/* 120 */     return ((SignEntity)getEntity()).getDayCharge();
/*     */   }
/*     */   
/*     */   public void setDayCharge(long dayCharge) {
/* 124 */     ((SignEntity)getEntity()).setDayCharge(dayCharge);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getTodayReward() {
/* 128 */     return ((SignEntity)getEntity()).getTodayReward();
/*     */   }
/*     */   
/*     */   public void setTodayReward(ArrayList<Integer> todayReward) {
/* 132 */     ((SignEntity)getEntity()).setTodayReward(todayReward);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getWeekReward() {
/* 136 */     return ((SignEntity)getEntity()).getWeekReward();
/*     */   }
/*     */   
/*     */   public void setWeekReward(ArrayList<Integer> weekReward) {
/* 140 */     ((SignEntity)getEntity()).setWeekReward(weekReward);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getSignReward() {
/* 144 */     return ((SignEntity)getEntity()).getSignReward();
/*     */   }
/*     */   
/*     */   public void setSignReward(Map<Integer, Integer> signReward) {
/* 148 */     ((SignEntity)getEntity()).setSignReward(signReward);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getLevelMap() {
/* 152 */     return ((SignEntity)getEntity()).getLevelMap();
/*     */   }
/*     */   
/*     */   public void setLevelMap(Map<Integer, Integer> levelMap) {
/* 156 */     ((SignEntity)getEntity()).setLevelMap(levelMap);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getTimeMap() {
/* 160 */     return ((SignEntity)getEntity()).getTimeMap();
/*     */   }
/*     */   
/*     */   public void setTimeMap(Map<Integer, Integer> timeMap) {
/* 164 */     ((SignEntity)getEntity()).setTimeMap(timeMap);
/*     */   }
/*     */   
/*     */   public int getShowLevel() {
/* 168 */     return ((SignEntity)getEntity()).getShowLevel();
/*     */   }
/*     */   
/*     */   public void setShowLevel(int showLevel) {
/* 172 */     ((SignEntity)getEntity()).setShowLevel(showLevel);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\sign\SignComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */