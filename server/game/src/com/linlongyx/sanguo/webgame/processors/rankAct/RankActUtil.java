/*     */ package com.linlongyx.sanguo.webgame.processors.rankAct;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.rankAct.RankActComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rankAct.RankActEntity;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RankingActivityBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RankingActivitylistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RankActParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.FestivalCountType;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.recruit.RecruitUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class RankActUtil {
/*  28 */   public static Map<Integer, RankAct> rankActMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  33 */     initRankActMap();
/*     */   }
/*     */   
/*     */   public static void initRankActMap() {
/*  37 */     rankActMap.clear();
/*  38 */     int days = TimeUtil.getDayDisTime(MContext.getOpenTimeInt());
/*  39 */     Map<Integer, Object> map = JsonTableService.getJsonTable(RankingActivityBean.class);
/*     */     
/*  41 */     for (Object o : map.values()) {
/*  42 */       RankingActivityBean rankingActivityBean = (RankingActivityBean)o;
/*  43 */       if (isOldServer() ? (
/*  44 */         rankingActivityBean.getDistinguish() == 1) : (
/*     */ 
/*     */ 
/*     */         
/*  48 */         rankingActivityBean.getDistinguish() == 0)) {
/*     */         continue;
/*     */       }
/*     */       
/*  52 */       if (MContext.isHeFu() && !LimitUtil.isValid(rankingActivityBean.getServerType(), 0)) {
/*     */         continue;
/*     */       }
/*     */       
/*  56 */       RankAct.RankActState state = (rankingActivityBean.getBeginTime() <= days && days < rankingActivityBean.getEndTime()) ? RankAct.RankActState.Opening : ((days < rankingActivityBean.getBeginTime()) ? RankAct.RankActState.NotOpen : RankAct.RankActState.Expire);
/*  57 */       rankActMap.put(Integer.valueOf(rankingActivityBean.getId()), new RankAct(state, rankingActivityBean.getId(), rankingActivityBean.getType()));
/*  58 */       LogUtil.debugLog(new Object[] { "server open days: " + days + ", rankAct id: " + rankingActivityBean.getId() + ", state: " + state.name() });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isOldServer() {
/*  68 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*  69 */     return (loginParameter.getOldOpenTime() > MContext.getOpenTimeInt());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<RankingData> getRankDataList(int rankActId) {
/*  79 */     return ((RankAct)rankActMap.get(Integer.valueOf(rankActId))).getRankList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void refreshRanks() {
/*  86 */     rankActMap.values().forEach(rankAct -> {
/*     */           int days = TimeUtil.getDayDisTime(MContext.getOpenTimeInt());
/*     */           RankingActivityBean rankingActivityBean = (RankingActivityBean)JsonTableService.getJsonData(rankAct.getId(), RankingActivityBean.class);
/*     */           if (rankAct.getState() == RankAct.RankActState.Opening) {
/*     */             rankAct.getRankList();
/*     */             if (rankingActivityBean.getEndTime() <= days) {
/*     */               rankAct.setState(RankAct.RankActState.Expire);
/*     */               rankAct.balance();
/*     */             } 
/*     */           } else if (rankAct.getState() == RankAct.RankActState.NotOpen && rankingActivityBean.getBeginTime() <= days && days < rankingActivityBean.getEndTime()) {
/*     */             rankAct.setState(RankAct.RankActState.Opening);
/*     */             rankAct.getRankList();
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void forceRefreshRanks() {
/* 108 */     rankActMap.values().forEach(RankAct::getRankList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getPlayerRank(int id, long playerId) {
/* 119 */     return ((RankAct)rankActMap.get(Integer.valueOf(id))).getRankByPlayerId(playerId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Integer> getRankActList(boolean isOpen) {
/* 129 */     ArrayList<Integer> rankActList = new ArrayList<>();
/* 130 */     RankAct.RankActState state = isOpen ? RankAct.RankActState.Opening : RankAct.RankActState.Expire;
/* 131 */     rankActMap.forEach((k, v) -> {
/*     */           RankingActivityBean rankingActivityBean = (RankingActivityBean)JsonTableService.getJsonData(k.intValue(), RankingActivityBean.class);
/*     */           boolean valid = RecruitUtil.isValid(rankingActivityBean.getServerType());
/*     */           if (valid && v.getState() == state) {
/*     */             rankActList.add(k);
/*     */           }
/*     */         });
/* 138 */     return rankActList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isActOpen(int rankActId) {
/* 148 */     return (((RankAct)rankActMap.get(Integer.valueOf(rankActId))).getState() == RankAct.RankActState.Opening);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void resetRankAct(int rankActId) {
/* 157 */     ((RankAct)rankActMap.get(Integer.valueOf(rankActId))).setState(RankAct.RankActState.NotOpen);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setRankActState(int rankActId, RankAct.RankActState state) {
/* 166 */     ((RankAct)rankActMap.get(Integer.valueOf(rankActId))).setState(state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void resetAllRankAct() {
/* 173 */     rankActMap.values().forEach(rankAct -> {
/*     */           if (rankAct.getState() != RankAct.RankActState.NotOpen) {
/*     */             rankAct.setState(RankAct.RankActState.NotOpen);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void refreshRankValue(int type, long newValue, long playerId) {
/* 188 */     if (newValue <= 0L) {
/*     */       return;
/*     */     }
/*     */     try {
/* 192 */       RankActParameter rankActParameter = (RankActParameter)ParameterConstant.getParameter(20);
/* 193 */       List<Integer> rankActIdList = rankActParameter.getIdsByType(type);
/* 194 */       if (null == rankActIdList || rankActIdList.isEmpty()) {
/*     */         return;
/*     */       }
/* 197 */       LogUtils.errorLog(new Object[] { "RankActUtil addRankValue", Long.valueOf(playerId), Integer.valueOf(type), Long.valueOf(newValue) });
/* 198 */       RankActComponent rankActComponent = (RankActComponent)LookUpService.getComponent(playerId, RankActComponent.class);
/* 199 */       if (null == rankActComponent) {
/*     */         return;
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
/* 215 */       for (Integer id : rankActIdList) {
/* 216 */         if (null == rankActMap.get(id)) {
/*     */           return;
/*     */         }
/* 219 */         if (((RankAct)rankActMap.get(id)).getState() == RankAct.RankActState.Expire) {
/*     */           continue;
/*     */         }
/*     */         
/* 223 */         RankingActivityBean rankingActivityBean = (RankingActivityBean)JsonTableService.getJsonData(id.intValue(), RankingActivityBean.class);
/* 224 */         if (null == rankingActivityBean) {
/*     */           continue;
/*     */         }
/* 227 */         if (rankingActivityBean.getOpenType() == 1 && !isActOpen(id.intValue()))
/*     */           continue; 
/* 229 */         RankActEntity rankActEntity = rankActComponent.getEntity(id.intValue());
/* 230 */         if (!rankActEntity.isOpen()) {
/*     */           continue;
/*     */         }
/* 233 */         int countType = rankingActivityBean.getCountType();
/*     */         
/* 235 */         long value = rankActEntity.getValue();
/* 236 */         if (countType == FestivalCountType.cumDone.getType()) {
/* 237 */           value += newValue;
/*     */         } else {
/* 239 */           if (value == newValue) {
/*     */             continue;
/*     */           }
/* 242 */           if (countType == FestivalCountType.singleDone.getType()) {
/* 243 */             if (newValue > value) {
/* 244 */               value = newValue;
/*     */             }
/*     */           } else {
/* 247 */             value = newValue;
/*     */           } 
/*     */         } 
/* 250 */         Map<Integer, Integer> states = rankActEntity.getStates();
/*     */         
/* 252 */         for (Iterator<Integer> iterator = rankingActivityBean.getPersonalReward().iterator(); iterator.hasNext(); ) { int itemId = ((Integer)iterator.next()).intValue();
/* 253 */           RankingActivitylistBean rankingActivitylistBean = (RankingActivitylistBean)JsonTableService.getJsonData(itemId, RankingActivitylistBean.class);
/* 254 */           if (null != rankingActivitylistBean) {
/* 255 */             int status = ((Integer)states.getOrDefault(Integer.valueOf(itemId), Integer.valueOf(0))).intValue();
/* 256 */             if (status == 1 || status == 2) {
/*     */               continue;
/*     */             }
/* 259 */             if (countType == FestivalCountType.less.getType()) {
/* 260 */               if (value <= Integer.parseInt(rankingActivitylistBean.getTarget())) {
/* 261 */                 states.put(Integer.valueOf(itemId), Integer.valueOf(1));
/* 262 */                 rankActEntity.setStates(states);
/* 263 */                 PlayerUtil.sendRedNotice(Long.valueOf(playerId), RedNoticeConstant.RankAct.getSys(), id.intValue());
/*     */               }  continue;
/*     */             } 
/* 266 */             if (value >= Integer.parseInt(rankingActivitylistBean.getTarget())) {
/* 267 */               states.put(Integer.valueOf(itemId), Integer.valueOf(1));
/* 268 */               rankActEntity.setStates(states);
/* 269 */               PlayerUtil.sendRedNotice(Long.valueOf(playerId), RedNoticeConstant.RankAct.getSys(), id.intValue());
/*     */             } 
/*     */           }  }
/*     */ 
/*     */         
/* 274 */         rankActEntity.setValue(value);
/* 275 */         rankActComponent.updateValueDB(rankActEntity.getId());
/* 276 */         rankActComponent.updateStatesDB(rankActEntity.getId());
/* 277 */         LogUtils.errorLog(new Object[] { "RankActUtil value change", Long.valueOf(playerId), Integer.valueOf(type), Long.valueOf(newValue), Long.valueOf(value) });
/*     */       } 
/* 279 */       rankActComponent.saveToDB();
/* 280 */       LogUtils.errorLog(new Object[] { "RankActUtil end", Long.valueOf(playerId), Integer.valueOf(type), Long.valueOf(newValue) });
/* 281 */     } catch (Exception e) {
/* 282 */       LogUtils.errorLog(new Object[] { "RankActError", Arrays.toString((Object[])e.getStackTrace()) });
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
/*     */   public static void balanceExpired(IPlayerSession playerSession) {
/* 294 */     RankActComponent rankActComponent = (RankActComponent)playerSession.getPlayer().createIfNotExist(RankActComponent.class);
/* 295 */     rankActComponent.balanceExpired();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rankAct\RankActUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */