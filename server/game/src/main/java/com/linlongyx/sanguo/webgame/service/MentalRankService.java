/*     */ package com.linlongyx.sanguo.webgame.service;
/*     */ 
/*     */ import com.linlongyx.core.framework.service.IBussinessService;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.mental.MentalRankComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.mental.MentalUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaTempleRankBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaTempleWorldBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MentalRankStruct;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MentalShowStruct;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class MentalRankService
/*     */   implements IBussinessService
/*     */ {
/*  28 */   private Map<Long, MentalRankStruct> ranks = new HashMap<>();
/*     */ 
/*     */   
/*  31 */   public MentalRankComponent component = (MentalRankComponent)LookUpService.getComponent(1L, (Class)MentalRankComponent.class);
/*     */ 
/*     */   
/*     */   public MentalRankService() {
/*  35 */     if (null == this.component) {
/*  36 */       this.component = new MentalRankComponent(1L);
/*  37 */       this.component.saveAllToDB();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addShowRecord(String playerName, ArrayList<Reward> rewards) {
/*  48 */     this.component.addShowRecord(playerName, rewards);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getShowRecord(List<MentalShowStruct> list) {
/*  55 */     this.component.getRevertShowList(list);
/*     */   }
/*     */ 
/*     */   
/*     */   public void getRanks(List<MentalRankStruct> list) {
/*  60 */     this.component.getRankList().forEach(mentalRankInfo -> list.add(mentalRankInfo));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized int getLevel(int actId) {
/*  69 */     return this.component.getLevel(actId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void setLevel(int actId) {
/*  76 */     this.component.setLevels(actId);
/*     */   }
/*     */   
/*     */   public synchronized int getWorldLevel(int actId) {
/*  80 */     return this.component.getWorldLevel(actId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void save() {
/*  86 */     this.component.saveToDB();
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
/*     */   public synchronized void updatePlayerPoint(long playerId, String playerName, int point) {
/*  98 */     if (point <= this.component.getMinPoint()) {
/*     */       return;
/*     */     }
/* 101 */     int level = MentalUtil.getShowLevel();
/* 102 */     ZhenfaTempleWorldBean zhenfaTempleWorldBean = JsonTableService.<ZhenfaTempleWorldBean>getJsonData(level, ZhenfaTempleWorldBean.class);
/* 103 */     if (point < zhenfaTempleWorldBean.getEntryScore()) {
/*     */       return;
/*     */     }
/* 106 */     for (MentalRankStruct rankInfo : this.component.getRankList()) {
/* 107 */       if (playerId == rankInfo.playerId) {
/* 108 */         rankInfo.point = point;
/* 109 */         this.component.setUpdate("rankList");
/* 110 */         this.component.saveToDB();
/* 111 */         this.component.sortRank();
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 116 */     MentalRankStruct struct = new MentalRankStruct();
/* 117 */     struct.playerId = playerId;
/* 118 */     struct.playerName = playerName;
/* 119 */     struct.point = point;
/*     */     
/* 121 */     this.component.getRankList().add(struct);
/* 122 */     this.component.setUpdate("rankList");
/* 123 */     this.component.saveToDB();
/*     */     
/* 125 */     this.component.sortRank();
/*     */   }
/*     */ 
/*     */   
/*     */   public void sort() {
/* 130 */     this.component.sortRank();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 138 */     if (TimeUtil.getWeek() == 1) {
/* 139 */       LogUtil.errorLog(new Object[] { "MentalRankService::reset begin", Long.valueOf(TimeUtil.currentTimeMillis()) });
/* 140 */       sendRankReward();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendRankReward() {
/* 146 */     int rank = 1;
/* 147 */     String title = LanguageConstant.getLanguage(5802);
/*     */     
/* 149 */     List<RewardBean> rewards = new ArrayList<>();
/* 150 */     int level = MentalUtil.getShowLevel();
/* 151 */     ZhenfaTempleWorldBean zhenfaTempleWorldBean = JsonTableService.<ZhenfaTempleWorldBean>getJsonData(level, ZhenfaTempleWorldBean.class);
/* 152 */     ArrayList<Integer> ranklist = zhenfaTempleWorldBean.getRankReward();
/* 153 */     Collections.sort(ranklist);
/* 154 */     for (MentalRankStruct rankInfo : this.component.getRankList()) {
/* 155 */       rewards.clear();
/* 156 */       ZhenfaTempleRankBean zhenfaTempleRankBean2 = null;
/* 157 */       for (Iterator<Integer> iterator = ranklist.iterator(); iterator.hasNext(); ) { int rankid = ((Integer)iterator.next()).intValue();
/* 158 */         ZhenfaTempleRankBean zhenfaTempleRankBean = JsonTableService.<ZhenfaTempleRankBean>getJsonData(rankid, ZhenfaTempleRankBean.class);
/* 159 */         if (rank <= zhenfaTempleRankBean.getTarget()) {
/* 160 */           zhenfaTempleRankBean2 = zhenfaTempleRankBean;
/*     */           break;
/*     */         }  }
/*     */       
/* 164 */       if (zhenfaTempleRankBean2 == null) {
/*     */         continue;
/*     */       }
/* 167 */       rewards.addAll(zhenfaTempleRankBean2.getRankReward());
/* 168 */       String content = LanguageConstant.getAndReplaceLanguage(5803, new String[] { rank + "" });
/* 169 */       MailUtil.sendSysMail(rankInfo.playerId, FinanceUtil.transform(rewards), title, content);
/* 170 */       LogUtil.errorLog(new Object[] { "MentalRankService::reset", Long.valueOf(rankInfo.playerId), rankInfo, Integer.valueOf(rankInfo.point), Integer.valueOf(rank) });
/* 171 */       rank++;
/*     */     } 
/* 173 */     this.ranks.clear();
/* 174 */     this.component.getRankList().clear();
/* 175 */     this.component.setUpdate("rankList");
/* 176 */     this.component.saveToDB();
/* 177 */     LogUtil.errorLog(new Object[] { "MentalRankService::reset end", Long.valueOf(TimeUtil.currentTimeMillis()) });
/*     */   }
/*     */ 
/*     */   
/*     */   public void initFromDB() {
/* 182 */     this.component.getFromDB();
/* 183 */     this.component.getRankList().forEach(rankStruct -> (MentalRankStruct)this.ranks.put(Long.valueOf(rankStruct.playerId), rankStruct));
/* 184 */     this.component.sortRank();
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveToDB() {
/* 189 */     this.component.saveAllToDB();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\MentalRankService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */