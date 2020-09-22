/*     */ package com.linlongyx.sanguo.webgame.app.recruit;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.proxy.IEntityProxy;
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecruitRedlistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RecruitParamter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class RecruitComponent
/*     */   extends AbstractComponent<RecruitEntity>
/*     */ {
/*  20 */   private RecruitParamter parameter = (RecruitParamter)ParameterConstant.getParameter(15);
/*     */   
/*     */   public RecruitComponent(long playerId) {
/*  23 */     super(RecruitEntity.class);
/*  24 */     this.playerId = playerId;
/*  25 */     makeKey();
/*  26 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  32 */     if (time == 0) {
/*  33 */       setCurrFreeCount(this.parameter.getFreeRecruitCount());
/*  34 */       setRefreshNum(0);
/*  35 */       setGoldRefreshNum(0);
/*  36 */       refreshByAct(1);
/*  37 */       refreshByAct(2);
/*  38 */       if (getEndTime() < TimeUtil.currentTime()) {
/*  39 */         setRebateScore(0L);
/*  40 */         setRebateReward(new HashMap<>());
/*     */       } 
/*     */     } 
/*  43 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void refreshByAct(int type) {
/*  50 */     ArrayList<Integer> arrayList = this.parameter.getActId(true, true, type);
/*  51 */     RecruitRedlistBean recruitRedlistBean = null;
/*  52 */     if (!arrayList.isEmpty()) {
/*  53 */       recruitRedlistBean = (RecruitRedlistBean)JsonTableService.getJsonData(((Integer)arrayList.get(0)).intValue(), RecruitRedlistBean.class);
/*     */     }
/*  55 */     if (recruitRedlistBean == null) {
/*  56 */       LogUtil.errorLog(new Object[] { "RecruitComponent::refreshByAct refresh redRecruit error", Long.valueOf(this.playerId), Integer.valueOf(type) });
/*     */       return;
/*     */     } 
/*  59 */     if (type == 1 && recruitRedlistBean.getActivityType() == 1) {
/*  60 */       setToday(getTommorow());
/*  61 */       refreshRedRecruit(type);
/*  62 */     } else if (type == 2 && recruitRedlistBean.getActivityType() == 1) {
/*  63 */       setGoldToday(getGoldTommorow());
/*  64 */       refreshRedRecruit(type);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void refreshRedRecruit(int type) {
/*  72 */     if (type == 1) {
/*  73 */       if (getToday().isEmpty()) {
/*  74 */         setToday(refreshRe(this.parameter, true, type));
/*     */       }
/*     */       
/*  77 */       setTommorow(refreshRe(this.parameter, false, type));
/*  78 */     } else if (type == 2) {
/*  79 */       if (getGoldToday().isEmpty()) {
/*  80 */         setGoldToday(refreshRe(this.parameter, true, type));
/*     */       }
/*  82 */       setGoldTommorow(refreshRe(this.parameter, false, type));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void refreshRedRecruit2(int type) {
/*  90 */     if (type == 1) {
/*  91 */       setToday(refreshRe(this.parameter, true, type));
/*     */       
/*  93 */       setTommorow(refreshRe(this.parameter, false, type));
/*  94 */     } else if (type == 2) {
/*  95 */       setGoldToday(refreshRe(this.parameter, true, type));
/*  96 */       setGoldTommorow(refreshRe(this.parameter, true, type));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> refreshRe(RecruitParamter recruitParamter, boolean isToday, int type) {
/* 107 */     ArrayList<Integer> arrayList = new ArrayList<>();
/* 108 */     ArrayList<Integer> arrayList2 = recruitParamter.getActId(true, isToday, type);
/* 109 */     int actId = 0;
/* 110 */     if (arrayList2.isEmpty()) {
/* 111 */       return arrayList;
/*     */     }
/* 113 */     actId = ((Integer)arrayList2.get(0)).intValue();
/*     */     
/* 115 */     RecruitRedlistBean recruitRedlistBean = (RecruitRedlistBean)JsonTableService.getJsonData(actId, RecruitRedlistBean.class);
/* 116 */     if (recruitRedlistBean == null) {
/* 117 */       LogUtil.errorLog(new Object[] { "RecruitComponent::raffleLottery refresh2 redRecruit2 error", Long.valueOf(this.playerId), Integer.valueOf(actId) });
/* 118 */       return arrayList;
/*     */     } 
/*     */     
/* 121 */     ArrayList<Integer> list = recruitRedlistBean.getActivityList();
/*     */     
/* 123 */     Collections.shuffle(list); int i;
/* 124 */     for (i = 0; i < recruitRedlistBean.getFighterNum(); i++) {
/* 125 */       arrayList.add(list.get(i));
/*     */     }
/*     */     
/* 128 */     if (type == 1 && list.size() > 1) {
/* 129 */       if (isToday) {
/* 130 */         if (getToday().size() > 0 && arrayList.get(0) == getToday().get(0)) {
/* 131 */           arrayList.clear();
/* 132 */           for (i = 0; i < recruitRedlistBean.getFighterNum(); i++) {
/* 133 */             if (list.get(i + 1) != null) {
/* 134 */               arrayList.add(list.get(i + 1));
/*     */             }
/*     */           }
/*     */         
/*     */         } 
/* 139 */       } else if (getTommorow().size() > 0 && arrayList.get(0) == getTommorow().get(0)) {
/* 140 */         arrayList.clear();
/* 141 */         for (i = 0; i < recruitRedlistBean.getFighterNum(); i++) {
/* 142 */           if (list.get(i + 1) != null) {
/* 143 */             arrayList.add(list.get(i + 1));
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/* 148 */     } else if (type == 2 && list.size() > 1) {
/* 149 */       if (isToday) {
/* 150 */         if (getGoldToday().size() > 0 && arrayList.get(0) == getGoldToday().get(0)) {
/* 151 */           arrayList.clear();
/* 152 */           for (i = 0; i < recruitRedlistBean.getFighterNum(); i++) {
/* 153 */             if (list.get(i + 1) != null) {
/* 154 */               arrayList.add(list.get(i + 1));
/*     */             }
/*     */           }
/*     */         
/*     */         } 
/* 159 */       } else if (getGoldTommorow().size() > 0 && arrayList.get(0) == getGoldTommorow().get(0)) {
/* 160 */         arrayList.clear();
/* 161 */         for (i = 0; i < recruitRedlistBean.getFighterNum(); i++) {
/* 162 */           if (list.get(i + 1) != null) {
/* 163 */             arrayList.add(list.get(i + 1));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 169 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 175 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/* 180 */     setPlayerId(playerId);
/* 181 */     setCurrFreeCount(this.parameter.getFreeRecruitCount());
/* 182 */     setNextFreeTime(TimeUtil.currentTime());
/* 183 */     this.proxy.setEntityStatus(IEntityProxy.ENTITY_STATUS.STATUS_ADD);
/*     */   }
/*     */   
/*     */   public int getNextFreeTime() {
/* 187 */     return ((RecruitEntity)getEntity()).getNextFreeTime();
/*     */   }
/*     */   
/*     */   public void setNextFreeTime(int nextFreeTime) {
/* 191 */     ((RecruitEntity)getEntity()).setNextFreeTime(nextFreeTime);
/*     */   }
/*     */   
/*     */   public int getCurrFreeCount() {
/* 195 */     return ((RecruitEntity)getEntity()).getCurrFreeCount();
/*     */   }
/*     */   
/*     */   public void setCurrFreeCount(int currFreeCount) {
/* 199 */     ((RecruitEntity)getEntity()).setCurrFreeCount(currFreeCount);
/*     */   }
/*     */   
/*     */   public int getRecruitTimes() {
/* 203 */     return ((RecruitEntity)getEntity()).getRecruitTimes();
/*     */   }
/*     */   
/*     */   public void setRecruitTimes(int recruitTimes) {
/* 207 */     ((RecruitEntity)getEntity()).setRecruitTimes(recruitTimes);
/*     */   }
/*     */   
/*     */   public int getTime() {
/* 211 */     return ((RecruitEntity)getEntity()).getTime();
/*     */   }
/*     */   
/*     */   public void setTime(int time) {
/* 215 */     ((RecruitEntity)getEntity()).setTime(time);
/*     */   }
/*     */   
/*     */   public int getCcyRecruitTimes() {
/* 219 */     return ((RecruitEntity)getEntity()).getCcyRecruitTimes();
/*     */   }
/*     */   
/*     */   public void setCcyRecruitTimes(int ccyRecruitTimes) {
/* 223 */     ((RecruitEntity)getEntity()).setCcyRecruitTimes(ccyRecruitTimes);
/*     */   }
/*     */   
/*     */   public int getTotalRecruitTimes() {
/* 227 */     return ((RecruitEntity)getEntity()).getTotalRecruitTimes();
/*     */   }
/*     */   
/*     */   public void setTotalRecruitTimes(int totalRecruitTimes) {
/* 231 */     ((RecruitEntity)getEntity()).setTotalRecruitTimes(totalRecruitTimes);
/*     */   }
/*     */   
/*     */   public int getScore() {
/* 235 */     return ((RecruitEntity)getEntity()).getScore();
/*     */   }
/*     */   
/*     */   public void setScore(int score) {
/* 239 */     ((RecruitEntity)getEntity()).setScore(score);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getBoxList() {
/* 243 */     return ((RecruitEntity)getEntity()).getBoxList();
/*     */   }
/*     */   
/*     */   public void setBoxList(ArrayList<Integer> boxList) {
/* 247 */     ((RecruitEntity)getEntity()).setBoxList(boxList);
/*     */   }
/*     */   
/*     */   public int getFree() {
/* 251 */     return ((RecruitEntity)getEntity()).getFree();
/*     */   }
/*     */   
/*     */   public void setFree(int free) {
/* 255 */     ((RecruitEntity)getEntity()).setFree(free);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getToday() {
/* 259 */     return ((RecruitEntity)getEntity()).getToday();
/*     */   }
/*     */   
/*     */   public void setToday(ArrayList<Integer> today) {
/* 263 */     ((RecruitEntity)getEntity()).setToday(today);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getTommorow() {
/* 267 */     return ((RecruitEntity)getEntity()).getTommorow();
/*     */   }
/*     */   
/*     */   public void setTommorow(ArrayList<Integer> tommorow) {
/* 271 */     ((RecruitEntity)getEntity()).setTommorow(tommorow);
/*     */   }
/*     */   
/*     */   public int getRefreshNum() {
/* 275 */     return ((RecruitEntity)getEntity()).getRefreshNum();
/*     */   }
/*     */   
/*     */   public void setRefreshNum(int refreshNum) {
/* 279 */     ((RecruitEntity)getEntity()).setRefreshNum(refreshNum);
/*     */   }
/*     */   
/*     */   public int getTenCCYRecruit() {
/* 283 */     return ((RecruitEntity)getEntity()).getTenCCYRecruit();
/*     */   }
/*     */   
/*     */   public void setTenCCYRecruit(int tenCCYRecruit) {
/* 287 */     ((RecruitEntity)getEntity()).setTenCCYRecruit(tenCCYRecruit);
/*     */   }
/*     */   
/*     */   public ArrayList<KeyValue> getFirstGetList() {
/* 291 */     return ((RecruitEntity)getEntity()).getFirstGetList();
/*     */   }
/*     */   
/*     */   public void setFirstGetList(ArrayList<KeyValue> firstGetList) {
/* 295 */     ((RecruitEntity)getEntity()).setFirstGetList(firstGetList);
/*     */   }
/*     */   
/*     */   public long getRebateScore() {
/* 299 */     return ((RecruitEntity)getEntity()).getRebateScore();
/*     */   }
/*     */   
/*     */   public void setRebateScore(long rebateScore) {
/* 303 */     ((RecruitEntity)getEntity()).setRebateScore(rebateScore);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getRebateReward() {
/* 307 */     return ((RecruitEntity)getEntity()).getRebateReward();
/*     */   }
/*     */   
/*     */   public void setRebateReward(Map<Integer, Integer> rebateReward) {
/* 311 */     ((RecruitEntity)getEntity()).setRebateReward(rebateReward);
/*     */   }
/*     */   
/*     */   public int getEndTime() {
/* 315 */     return ((RecruitEntity)getEntity()).getEndTime();
/*     */   }
/*     */   
/*     */   public void setEndTime(int endTime) {
/* 319 */     ((RecruitEntity)getEntity()).setEndTime(endTime);
/*     */   }
/*     */   
/*     */   public int getGoldScore() {
/* 323 */     return ((RecruitEntity)getEntity()).getGoldScore();
/*     */   }
/*     */   
/*     */   public void setGoldScore(int goldScore) {
/* 327 */     ((RecruitEntity)getEntity()).setGoldScore(goldScore);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getGoldBoxList() {
/* 331 */     return ((RecruitEntity)getEntity()).getGoldBoxList();
/*     */   }
/*     */   
/*     */   public void setGoldBoxList(ArrayList<Integer> goldBoxList) {
/* 335 */     ((RecruitEntity)getEntity()).setGoldBoxList(goldBoxList);
/*     */   }
/*     */   
/*     */   public int getGoldFree() {
/* 339 */     return ((RecruitEntity)getEntity()).getGoldFree();
/*     */   }
/*     */   
/*     */   public void setGoldFree(int goldFree) {
/* 343 */     ((RecruitEntity)getEntity()).setGoldFree(goldFree);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getGoldToday() {
/* 347 */     return ((RecruitEntity)getEntity()).getGoldToday();
/*     */   }
/*     */   
/*     */   public void setGoldToday(ArrayList<Integer> goldToday) {
/* 351 */     ((RecruitEntity)getEntity()).setGoldToday(goldToday);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getGoldTommorow() {
/* 355 */     return ((RecruitEntity)getEntity()).getGoldTommorow();
/*     */   }
/*     */   
/*     */   public void setGoldTommorow(ArrayList<Integer> goldTommorow) {
/* 359 */     ((RecruitEntity)getEntity()).setGoldTommorow(goldTommorow);
/*     */   }
/*     */   
/*     */   public int getGoldRefreshNum() {
/* 363 */     return ((RecruitEntity)getEntity()).getGoldRefreshNum();
/*     */   }
/*     */   
/*     */   public void setGoldRefreshNum(int goldRefreshNum) {
/* 367 */     ((RecruitEntity)getEntity()).setGoldRefreshNum(goldRefreshNum);
/*     */   }
/*     */   
/*     */   public int getGoldRecruitTimes() {
/* 371 */     return ((RecruitEntity)getEntity()).getGoldRecruitTimes();
/*     */   }
/*     */   
/*     */   public void setGoldRecruitTimes(int goldRecruitTimes) {
/* 375 */     ((RecruitEntity)getEntity()).setGoldRecruitTimes(goldRecruitTimes);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\recruit\RecruitComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */