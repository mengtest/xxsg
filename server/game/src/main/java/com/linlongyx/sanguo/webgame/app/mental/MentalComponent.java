/*     */ package com.linlongyx.sanguo.webgame.app.mental;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.proxy.IEntityProxy;
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.mental.MentalLotteryInfoResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.mental.MyMentalInfoResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MyMentalStruct;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.MentalRankService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MentalComponent
/*     */   extends AbstractComponent<MentalEntity>
/*     */ {
/*     */   public MentalComponent(long playerId) {
/*  23 */     super(MentalEntity.class);
/*  24 */     this.playerId = playerId;
/*  25 */     makeKey();
/*  26 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  31 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  36 */     setPlayerId(playerId);
/*  37 */     this.proxy.setEntityStatus(IEntityProxy.ENTITY_STATUS.STATUS_ADD);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  42 */     if (time == 0);
/*     */ 
/*     */     
/*  45 */     return true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void pointReward() {}
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
/*     */   public boolean incMyReward(ArrayList<Reward> rewardList, int point) {
/*  86 */     MyMentalStruct struct = new MyMentalStruct();
/*  87 */     struct.rewards = rewardList;
/*  88 */     struct.createTime = TimeUtil.currentTime();
/*  89 */     struct.point = point;
/*     */     
/*  91 */     List<MyMentalStruct> records = getRecords();
/*  92 */     if (records.size() >= 10) {
/*  93 */       records.remove(0);
/*     */     }
/*  95 */     records.add(struct);
/*  96 */     setUpdate("records");
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getLotteryInfo(MentalLotteryInfoResponse response) {
/* 106 */     response.point = getPoint();
/* 107 */     response.rewardList = new ArrayList<>(getRewardIds());
/* 108 */     response.freeTime = getFreeTime();
/* 109 */     response.showLevel = MentalUtil.getShowLevel();
/* 110 */     MentalRankService rankService = (MentalRankService)MContext.getBean("mentalRankService");
/* 111 */     rankService.getShowRecord(response.items);
/* 112 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getMyMentalInfo(MyMentalInfoResponse response) {
/* 122 */     List<MyMentalStruct> list = getRecords();
/* 123 */     int size = list.size();
/* 124 */     for (int index = size - 1; index >= 0; index--) {
/* 125 */       response.rewards.add(list.get(index));
/*     */     }
/*     */     
/* 128 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTimes() {
/* 135 */     return ((MentalEntity)getEntity()).getTimes();
/*     */   }
/*     */   
/*     */   public void setTimes(int times) {
/* 139 */     ((MentalEntity)getEntity()).setTimes(times);
/*     */   }
/*     */   
/*     */   public int getFreeTime() {
/* 143 */     return ((MentalEntity)getEntity()).getFreeTime();
/*     */   }
/*     */   
/*     */   public void setFreeTime(int freeTime) {
/* 147 */     ((MentalEntity)getEntity()).setFreeTime(freeTime);
/*     */   }
/*     */   
/*     */   public int getTotalTimes() {
/* 151 */     return ((MentalEntity)getEntity()).getTotalTimes();
/*     */   }
/*     */   
/*     */   public void setTotalTimes(int totalTimes) {
/* 155 */     ((MentalEntity)getEntity()).setTotalTimes(totalTimes);
/*     */   }
/*     */   
/*     */   public Set<Integer> getRewardIds() {
/* 159 */     return ((MentalEntity)getEntity()).getRewardIds();
/*     */   }
/*     */   
/*     */   public void setRewardIds(Set<Integer> rewardIds) {
/* 163 */     ((MentalEntity)getEntity()).setRewardIds(rewardIds);
/*     */   }
/*     */   
/*     */   public int getPoint() {
/* 167 */     return ((MentalEntity)getEntity()).getPoint();
/*     */   }
/*     */   
/*     */   public void setPoint(int point) {
/* 171 */     ((MentalEntity)getEntity()).setPoint(point);
/*     */   }
/*     */   
/*     */   public List<MyMentalStruct> getRecords() {
/* 175 */     return ((MentalEntity)getEntity()).getRecords();
/*     */   }
/*     */   
/*     */   public void setRecords(List<MyMentalStruct> records) {
/* 179 */     ((MentalEntity)getEntity()).setRecords(records);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\mental\MentalComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */