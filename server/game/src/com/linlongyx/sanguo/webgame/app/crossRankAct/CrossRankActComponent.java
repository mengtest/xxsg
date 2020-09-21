/*     */ package com.linlongyx.sanguo.webgame.app.crossRankAct;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RankingKuafuBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RankingKuafulistBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.crossRankAct.CrossRankActUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
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
/*     */ public class CrossRankActComponent
/*     */   extends AbstractMapComponent<CrossRankActEntity>
/*     */ {
/*  29 */   private long maxPower = 0L;
/*     */   
/*     */   public CrossRankActComponent(long playerId) {
/*  32 */     super(CrossRankActEntity.class, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  37 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  42 */     this.playerId = playerId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  47 */     if (time == 0);
/*     */     
/*  49 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void balanceExpired() {
/*  56 */     ArrayList<Integer> rankActList = RankActUtil.getRankActList(false);
/*  57 */     for (Integer rankActId : rankActList) {
/*  58 */       balancePersonalReward(rankActId.intValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void balancePersonalReward(int rankActId) {
/*  63 */     CrossRankActEntity rankActEntity = getEntity(rankActId);
/*  64 */     if (rankActEntity == null)
/*     */       return; 
/*  66 */     if (!rankActEntity.isOpen() || rankActEntity.getValue() == 0L)
/*     */       return; 
/*  68 */     RankingKuafuBean rankingKuafuBean = (RankingKuafuBean)JsonTableService.getJsonData(rankActId, RankingKuafuBean.class);
/*  69 */     if (rankingKuafuBean == null)
/*     */       return; 
/*  71 */     LogUtils.errorLog(new Object[] { "CrossRankBalance personal reward", Long.valueOf(rankActEntity.getPlayerId()), rankActEntity.toString() });
/*     */     
/*  73 */     ArrayList<Reward> rewardList = new ArrayList<>();
/*  74 */     Map<Integer, Integer> map = rankActEntity.getStates();
/*  75 */     for (Integer id : map.keySet()) {
/*  76 */       RankingKuafulistBean bean = (RankingKuafulistBean)JsonTableService.getJsonData(id.intValue(), RankingKuafulistBean.class);
/*  77 */       if (null != bean && 1 == ((Integer)map.get(id)).intValue()) {
/*  78 */         rewardList.addAll(FinanceUtil.rewardGet(FinanceUtil.transform(bean.getReward())));
/*  79 */         map.put(id, Integer.valueOf(2));
/*  80 */         updateStatesDB(rankActId);
/*     */       } 
/*     */     } 
/*  83 */     if (!rewardList.isEmpty()) {
/*  84 */       String title = LanguageConstant.getAndReplaceLanguage(5903, new String[] { rankingKuafuBean.getTagName() });
/*  85 */       String content = LanguageConstant.getAndReplaceLanguage(5904, new String[] { rankingKuafuBean.getTagName() });
/*  86 */       MailUtil.sendSysMail(this.playerId, rewardList, title, content);
/*     */     } 
/*  88 */     rankActEntity.setOpen(false);
/*  89 */     updateOpenDB(rankActId);
/*     */   }
/*     */   
/*     */   public void updateValueDB(int id) {
/*  93 */     getProxy().setUpdate(String.valueOf(id), "value");
/*     */   }
/*     */   
/*     */   public void updateStatesDB(int id) {
/*  97 */     getProxy().setUpdate(String.valueOf(id), "states");
/*     */   }
/*     */   
/*     */   public void updateOpenDB(int id) {
/* 101 */     getProxy().setUpdate(String.valueOf(id), "open");
/*     */   }
/*     */   
/*     */   public CrossRankActEntity getEntity(int id) {
/* 105 */     CrossRankActEntity crossRankActEntity = (CrossRankActEntity)getEntity(String.valueOf(id));
/* 106 */     RankingKuafuBean bean = (RankingKuafuBean)JsonTableService.getJsonData(id, RankingKuafuBean.class);
/* 107 */     if (null == crossRankActEntity && CrossRankActUtil.isActOpen(bean)) {
/* 108 */       crossRankActEntity = new CrossRankActEntity(this.playerId, id);
/* 109 */       crossRankActEntity.setOpen(true);
/* 110 */       addEntity((IEntity)crossRankActEntity);
/* 111 */       saveToDB();
/*     */     } 
/* 113 */     return crossRankActEntity;
/*     */   }
/*     */   
/*     */   public long getMaxPower() {
/* 117 */     return this.maxPower;
/*     */   }
/*     */   
/*     */   public void setMaxPower(long maxPower) {
/* 121 */     this.maxPower = maxPower;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\crossRankAct\CrossRankActComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */