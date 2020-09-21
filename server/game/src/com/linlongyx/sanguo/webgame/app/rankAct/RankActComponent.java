/*     */ package com.linlongyx.sanguo.webgame.app.rankAct;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RankingActivityBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RankingActivitylistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RankActParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.limit.LimitUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankAct;
/*     */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RankActComponent
/*     */   extends AbstractMapComponent<RankActEntity>
/*     */ {
/*  35 */   private long maxPower = 0L;
/*     */   
/*     */   public RankActComponent(long playerId) {
/*  38 */     super(RankActEntity.class, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  43 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  48 */     this.playerId = playerId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  53 */     if (time == 0);
/*     */     
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void balanceExpired() {
/*  62 */     ArrayList<Integer> rankActList = RankActUtil.getRankActList(false);
/*  63 */     for (Integer rankActId : rankActList) {
/*  64 */       balanceRankAct(rankActId.intValue());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void balanceRankAct(int rankActId) {
/*  70 */     RankActEntity rankActEntity = getEntity(rankActId);
/*  71 */     if (null == rankActEntity) {
/*     */       return;
/*     */     }
/*  74 */     if (!rankActEntity.isOpen()) {
/*     */       return;
/*     */     }
/*  77 */     LogUtils.errorLog(new Object[] { "RankBalance", Long.valueOf(rankActEntity.getPlayerId()), rankActEntity.toString() });
/*  78 */     RankingActivityBean rankingActivityBean = (RankingActivityBean)JsonTableService.getJsonData(rankActEntity.getId(), RankingActivityBean.class);
/*  79 */     if (rankActEntity.getValue() == 0L) {
/*     */       return;
/*     */     }
/*  82 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*  83 */     if (TimeUtil.getDayDisTime(MContext.getOpenTimeInt()) > loginParameter.getRankActClose()) {
/*  84 */       rankActEntity.setOpen(false);
/*  85 */       updateOpenDB(rankActId);
/*     */       return;
/*     */     } 
/*  88 */     if (MContext.isHeFu() && !LimitUtil.isValid(rankingActivityBean.getServerType(), 0)) {
/*  89 */       rankActEntity.setOpen(false);
/*  90 */       updateOpenDB(rankActId);
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  96 */     int rank = RankActUtil.getPlayerRank(rankingActivityBean.getId(), this.playerId);
/*  97 */     if (rank > 0) {
/*  98 */       int priceId = 0;
/*  99 */       RankActParameter rankActParameter = (RankActParameter)ParameterConstant.getParameter(20);
/* 100 */       for (Integer itemId : rankingActivityBean.getRankReward()) {
/* 101 */         List<Integer> range = rankActParameter.getRange(itemId.intValue());
/* 102 */         if (range != null && range.size() >= 2 && ((Integer)range.get(0)).intValue() <= rank && rank <= ((Integer)range.get(1)).intValue()) {
/* 103 */           priceId = itemId.intValue();
/*     */           break;
/*     */         } 
/*     */       } 
/* 107 */       RankingActivitylistBean rankingActivitylistBean = (RankingActivitylistBean)JsonTableService.getJsonData(priceId, RankingActivitylistBean.class);
/* 108 */       if (null != rankingActivitylistBean) {
/* 109 */         String title = LanguageConstant.getAndReplaceLanguage(1201, new String[] { rankingActivityBean.getTagName() });
/* 110 */         String content = LanguageConstant.getAndReplaceLanguage(1202, new String[] { rankingActivityBean.getTagName(), String.valueOf(rank) });
/* 111 */         MailUtil.sendSysMail(this.playerId, FinanceUtil.rewardGet(FinanceUtil.transform(rankingActivitylistBean.getReward())), title, content.replace("{rank}", String.valueOf(rank)));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 116 */     ArrayList<Reward> rewardList = new ArrayList<>();
/* 117 */     Map<Integer, Integer> map = rankActEntity.getStates();
/* 118 */     for (Integer id : map.keySet()) {
/* 119 */       RankingActivitylistBean rankingActivitylistBean = (RankingActivitylistBean)JsonTableService.getJsonData(id.intValue(), RankingActivitylistBean.class);
/* 120 */       if (null != rankingActivitylistBean && 1 == ((Integer)map.get(id)).intValue()) {
/* 121 */         rewardList.addAll(FinanceUtil.rewardGet(FinanceUtil.transform(rankingActivitylistBean.getReward())));
/* 122 */         map.put(id, Integer.valueOf(2));
/* 123 */         updateStatesDB(rankActId);
/*     */       } 
/*     */     } 
/* 126 */     if (!rewardList.isEmpty()) {
/* 127 */       String title = LanguageConstant.getAndReplaceLanguage(1203, new String[] { rankingActivityBean.getTagName() });
/* 128 */       String content = LanguageConstant.getAndReplaceLanguage(1204, new String[] { rankingActivityBean.getTagName() });
/* 129 */       MailUtil.sendSysMail(this.playerId, rewardList, title, content);
/*     */     } 
/* 131 */     rankActEntity.setOpen(false);
/* 132 */     updateOpenDB(rankActId);
/*     */   }
/*     */   
/*     */   public void updateValueDB(int id) {
/* 136 */     getProxy().setUpdate(String.valueOf(id), "value");
/*     */   }
/*     */   
/*     */   public void updateStatesDB(int id) {
/* 140 */     getProxy().setUpdate(String.valueOf(id), "states");
/*     */   }
/*     */   
/*     */   public void updateOpenDB(int id) {
/* 144 */     getProxy().setUpdate(String.valueOf(id), "open");
/*     */   }
/*     */   
/*     */   public RankActEntity getEntity(int id) {
/* 148 */     RankActEntity rankActEntity = (RankActEntity)getEntity(String.valueOf(id));
/*     */     
/* 150 */     if (null == rankActEntity) {
/* 151 */       rankActEntity = new RankActEntity(this.playerId, id);
/* 152 */       if (RankActUtil.rankActMap.get(Integer.valueOf(id)) != null && ((RankAct)RankActUtil.rankActMap.get(Integer.valueOf(id))).getState() != RankAct.RankActState.Expire) {
/* 153 */         rankActEntity.setOpen(true);
/*     */       }
/* 155 */       addEntity((IEntity)rankActEntity);
/* 156 */       saveToDB();
/*     */     } 
/* 158 */     return rankActEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteAll() {
/* 165 */     for (Map.Entry<String, IMapEntity> entry : (Iterable<Map.Entry<String, IMapEntity>>)getEntityMap().entrySet()) {
/* 166 */       RankActEntity rankActEntity = (RankActEntity)entry.getValue();
/* 167 */       getProxy().delEntity(rankActEntity);
/*     */     } 
/*     */   }
/*     */   
/*     */   public long getMaxPower() {
/* 172 */     return this.maxPower;
/*     */   }
/*     */   
/*     */   public void setMaxPower(long maxPower) {
/* 176 */     this.maxPower = maxPower;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\rankAct\RankActComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */