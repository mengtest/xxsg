/*     */ package com.linlongyx.sanguo.webgame.app.unparalleled;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.proxy.IEntityProxy;
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.WushuangBuffBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.UnparalleledParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UnparalleledComponent
/*     */   extends AbstractComponent<UnparalleledEntity>
/*     */ {
/*  21 */   private LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*     */   
/*     */   public UnparalleledComponent(long playerId) {
/*  24 */     super(UnparalleledEntity.class, playerId);
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
/*  45 */     this.attrbuteList = new ArrayList<>();
/*     */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */   
/*     */   private ArrayList<Integer> attrbuteList;
/*     */   
/*     */   public ArrayList<Integer> getAttrbuteList() {
/*  53 */     return this.attrbuteList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createAttrbuteList() {
/*  60 */     UnparalleledParameter unparalleledParameter = (UnparalleledParameter)ParameterConstant.getParameter(18);
/*  61 */     for (Integer star : unparalleledParameter.getStarMap().keySet()) {
/*  62 */       ArrayList<Integer> costStar = new ArrayList<>();
/*  63 */       costStar = (ArrayList<Integer>)unparalleledParameter.getStarMap().get(star);
/*  64 */       int total = 0;
/*  65 */       for (Iterator<Integer> iterator1 = costStar.iterator(); iterator1.hasNext(); ) { int insId = ((Integer)iterator1.next()).intValue();
/*  66 */         WushuangBuffBean wushuangBuffBean = (WushuangBuffBean)JsonTableService.getJsonData(insId, WushuangBuffBean.class);
/*  67 */         total += wushuangBuffBean.getProb(); }
/*     */       
/*  69 */       int rand = RandUtil.randNum(0, total);
/*  70 */       total = 0;
/*  71 */       for (Iterator<Integer> iterator2 = costStar.iterator(); iterator2.hasNext(); ) { int insIds = ((Integer)iterator2.next()).intValue();
/*  72 */         WushuangBuffBean wushuangBuffBean1 = (WushuangBuffBean)JsonTableService.getJsonData(insIds, WushuangBuffBean.class);
/*  73 */         total += wushuangBuffBean1.getProb();
/*  74 */         if (total >= rand) {
/*  75 */           this.attrbuteList.add(Integer.valueOf(wushuangBuffBean1.getBuffId()));
/*     */         } }
/*     */     
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
/*     */   
/*     */   public boolean reset(int time) {
/*  91 */     if (time == 0)
/*     */     {
/*     */ 
/*     */       
/*  95 */       setResetTimes(0);
/*     */     }
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
/* 108 */     return true;
/*     */   }
/*     */   
/*     */   public Map<Long, Long> getPartneredHpMap() {
/* 112 */     return ((UnparalleledEntity)getEntity()).getPartneredHpMap();
/*     */   }
/*     */   
/*     */   public void setPartneredHpMap(Map<Long, Long> partneredHpMap) {
/* 116 */     ((UnparalleledEntity)getEntity()).setPartneredHpMap(partneredHpMap);
/*     */   }
/*     */   
/*     */   public int getResetTimes() {
/* 120 */     return ((UnparalleledEntity)getEntity()).getResetTimes();
/*     */   }
/*     */   
/*     */   public void setResetTimes(int resetTimes) {
/* 124 */     ((UnparalleledEntity)getEntity()).setResetTimes(resetTimes);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getBuffs() {
/* 128 */     return ((UnparalleledEntity)getEntity()).getBuffs();
/*     */   }
/*     */   
/*     */   public void setBuffs(ArrayList<Integer> buffs) {
/* 132 */     ((UnparalleledEntity)getEntity()).setBuffs(buffs);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getLayerAddition() {
/* 136 */     return ((UnparalleledEntity)getEntity()).getLayerAddition();
/*     */   }
/*     */   
/*     */   public void setLayerAddition(ArrayList<Integer> layerAddition) {
/* 140 */     ((UnparalleledEntity)getEntity()).setLayerAddition(layerAddition);
/*     */   }
/*     */   
/*     */   public int getCurPoint() {
/* 144 */     return ((UnparalleledEntity)getEntity()).getCurPoint();
/*     */   }
/*     */   
/*     */   public void setCurPoint(int curPoint) {
/* 148 */     ((UnparalleledEntity)getEntity()).setCurPoint(curPoint);
/*     */   }
/*     */   
/*     */   public int getCurrMaxStar() {
/* 152 */     return ((UnparalleledEntity)getEntity()).getCurrMaxStar();
/*     */   }
/*     */   
/*     */   public void setCurrMaxStar(int currMaxStar) {
/* 156 */     ((UnparalleledEntity)getEntity()).setCurrMaxStar(currMaxStar);
/*     */   }
/*     */   
/*     */   public int getSurpStar() {
/* 160 */     return ((UnparalleledEntity)getEntity()).getSurpStar();
/*     */   }
/*     */   
/*     */   public void setSurpStar(int surpStar) {
/* 164 */     ((UnparalleledEntity)getEntity()).setSurpStar(surpStar);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getPlayedPoints() {
/* 168 */     return ((UnparalleledEntity)getEntity()).getPlayedPoints();
/*     */   }
/*     */   
/*     */   public void setPlayedPoints(Map<Integer, Integer> playedPoints) {
/* 172 */     ((UnparalleledEntity)getEntity()).setPlayedPoints(playedPoints);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getLayerBox() {
/* 176 */     return ((UnparalleledEntity)getEntity()).getLayerBox();
/*     */   }
/*     */   
/*     */   public void setLayerBox(ArrayList<Integer> layerBox) {
/* 180 */     ((UnparalleledEntity)getEntity()).setLayerBox(layerBox);
/*     */   }
/*     */   
/*     */   public int getLastMaxStar() {
/* 184 */     return ((UnparalleledEntity)getEntity()).getLastMaxStar();
/*     */   }
/*     */   
/*     */   public void setLastMaxStar(int lastMaxStar) {
/* 188 */     ((UnparalleledEntity)getEntity()).setLastMaxStar(lastMaxStar);
/*     */   }
/*     */   
/*     */   public void setAttrbuteList(ArrayList<Integer> attrbuteList) {
/* 192 */     this.attrbuteList = attrbuteList;
/*     */   }
/*     */   
/*     */   public ArrayList<Long> getBattlePartner() {
/* 196 */     return ((UnparalleledEntity)getEntity()).getBattlePartner();
/*     */   }
/*     */   
/*     */   public void setBattlePartner(ArrayList<Long> battlePartner) {
/* 200 */     ((UnparalleledEntity)getEntity()).setBattlePartner(battlePartner);
/*     */   }
/*     */   
/*     */   public boolean isSweep() {
/* 204 */     return ((UnparalleledEntity)getEntity()).isSweep();
/*     */   }
/*     */   
/*     */   public void setSweep(boolean sweep) {
/* 208 */     ((UnparalleledEntity)getEntity()).setSweep(sweep);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getInsMap() {
/* 212 */     return ((UnparalleledEntity)getEntity()).getInsMap();
/*     */   }
/*     */   
/*     */   public void setInsMap(Map<Integer, Integer> insMap) {
/* 216 */     ((UnparalleledEntity)getEntity()).setInsMap(insMap);
/*     */   }
/*     */   
/*     */   public long getLastPassTime() {
/* 220 */     return ((UnparalleledEntity)getEntity()).getLastPassTime();
/*     */   }
/*     */   
/*     */   public void setLastPassTime(long lastPassTime) {
/* 224 */     ((UnparalleledEntity)getEntity()).setLastPassTime(lastPassTime);
/*     */   }
/*     */   
/*     */   public int getLastMaxRank() {
/* 228 */     return ((UnparalleledEntity)getEntity()).getLastMaxRank();
/*     */   }
/*     */   
/*     */   public void setLastMaxRank(int lastMaxRank) {
/* 232 */     ((UnparalleledEntity)getEntity()).setLastMaxRank(lastMaxRank);
/*     */   }
/*     */   
/*     */   public int getMaxPoint() {
/* 236 */     return ((UnparalleledEntity)getEntity()).getMaxPoint();
/*     */   }
/*     */   
/*     */   public void setMaxPoint(int maxPoint) {
/* 240 */     ((UnparalleledEntity)getEntity()).setMaxPoint(maxPoint);
/*     */   }
/*     */   
/*     */   public int getChallengeNum() {
/* 244 */     return ((UnparalleledEntity)getEntity()).getChallengeNum();
/*     */   }
/*     */   
/*     */   public void setChallengeNum(int challengeNum) {
/* 248 */     ((UnparalleledEntity)getEntity()).setChallengeNum(challengeNum);
/*     */   }
/*     */   
/*     */   public LoginParameter getLoginParameter() {
/* 252 */     return this.loginParameter;
/*     */   }
/*     */   
/*     */   public void setLoginParameter(LoginParameter loginParameter) {
/* 256 */     this.loginParameter = loginParameter;
/*     */   }
/*     */   
/*     */   public int getTotalResetTimes() {
/* 260 */     return ((UnparalleledEntity)getEntity()).getTotalResetTimes();
/*     */   }
/*     */   
/*     */   public void setTotalResetTimes(int totalResetTimes) {
/* 264 */     ((UnparalleledEntity)getEntity()).setTotalResetTimes(totalResetTimes);
/*     */   }
/*     */   
/*     */   public Map<Long, ArrayList<Long>> getAttrsList() {
/* 268 */     return ((UnparalleledEntity)getEntity()).getAttrsList();
/*     */   }
/*     */   
/*     */   public void setAttrsList(Map<Long, ArrayList<Long>> attrsList) {
/* 272 */     ((UnparalleledEntity)getEntity()).setAttrsList(attrsList);
/*     */   }
/*     */   
/*     */   public Map<Long, Integer> getLevelMap() {
/* 276 */     return ((UnparalleledEntity)getEntity()).getLevelMap();
/*     */   }
/*     */   
/*     */   public void setLevelMap(Map<Long, Integer> levelMap) {
/* 280 */     ((UnparalleledEntity)getEntity()).setLevelMap(levelMap);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/* 285 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/* 290 */     this.proxy.setEntityStatus(IEntityProxy.ENTITY_STATUS.STATUS_ADD);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\ap\\unparalleled\UnparalleledComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */