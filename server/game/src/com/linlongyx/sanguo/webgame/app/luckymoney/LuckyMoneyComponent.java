/*     */ package com.linlongyx.sanguo.webgame.app.luckymoney;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MoneyPotBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LuckyMoneyActParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LuckyMoneyComponent
/*     */   extends AbstractMapComponent<LuckyMoneyEntity>
/*     */ {
/*     */   public LuckyMoneyComponent(long playerId) {
/*  28 */     super(LuckyMoneyEntity.class, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  33 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  38 */     this.playerId = playerId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  44 */     super.init();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  49 */     if (time == 0) {
/*  50 */       resetActivity();
/*  51 */       resertPointBattle();
/*     */     } 
/*  53 */     return true;
/*     */   }
/*     */   
/*     */   public LuckyMoneyEntity getEntity(int id) {
/*  57 */     LuckyMoneyEntity luckyMoneyEntity = (LuckyMoneyEntity)getEntity(String.valueOf(id));
/*  58 */     if (null == luckyMoneyEntity) {
/*  59 */       luckyMoneyEntity = new LuckyMoneyEntity(this.playerId, id);
/*  60 */       addEntity((IEntity)luckyMoneyEntity);
/*     */     } 
/*     */ 
/*     */     
/*  64 */     return luckyMoneyEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resertPointBattle() {
/*  71 */     LuckyMoneyActParameter luckyMoneyActParameter = (LuckyMoneyActParameter)ParameterConstant.getParameter(61);
/*  72 */     List<Integer> list = luckyMoneyActParameter.getLuckySaveAct(true);
/*  73 */     Map<Integer, FestivalTime> festivalTimes = luckyMoneyActParameter.getActTimes();
/*  74 */     for (Integer actId : list) {
/*  75 */       if (festivalTimes.containsKey(actId) && 
/*  76 */         luckyMoneyActParameter.isActSaveLuckyOpen(actId.intValue())) {
/*  77 */         LuckyMoneyEntity luckyMoneyEntity = getEntity(actId.intValue());
/*  78 */         luckyMoneyEntity.setTaskPoint(0L);
/*  79 */         updateTaskPoint(actId.intValue());
/*  80 */         luckyMoneyEntity.setBattle(0);
/*  81 */         updateBattle(actId.intValue());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetActivity() {
/*  91 */     LuckyMoneyActParameter luckyMoneyActParameter = (LuckyMoneyActParameter)ParameterConstant.getParameter(61);
/*  92 */     List<Integer> list = luckyMoneyActParameter.getLuckySaveAct(false);
/*  93 */     Map<Integer, FestivalTime> festivalTimes = luckyMoneyActParameter.getActTimes();
/*  94 */     for (Integer actId : list) {
/*  95 */       if (festivalTimes.containsKey(actId) && 
/*  96 */         !luckyMoneyActParameter.isActSaveLuckyOpen(actId.intValue())) {
/*  97 */         LuckyMoneyEntity luckyMoneyEntity = getEntity(actId.intValue());
/*  98 */         if (luckyMoneyEntity.getGoldMoneySum() > 0L || luckyMoneyEntity.getSilverMoneySum() > 0L) {
/*  99 */           LogUtil.errorLog(new Object[] { "luckyMoneyEntity", Long.valueOf(this.playerId), Integer.valueOf(luckyMoneyEntity.getId()), Long.valueOf(luckyMoneyEntity.getGoldMoney()), Long.valueOf(luckyMoneyEntity.getGoldMoneySum()), Long.valueOf(luckyMoneyEntity.getSilverMoney()), Long.valueOf(luckyMoneyEntity.getSilverMoneySum()) });
/*     */         }
/* 101 */         MoneyPotBean moneyPotBean = (MoneyPotBean)JsonTableService.getJsonData(actId.intValue(), MoneyPotBean.class);
/* 102 */         double cost_gold = luckyMoneyEntity.getGoldMoney();
/* 103 */         double sum_gold = 0.0D;
/* 104 */         if (cost_gold > 0.0D) {
/* 105 */           sum_gold = luckyMoneyActParameter.getPerCentAge() * ((MoneyPotBean.CostRewardBean)moneyPotBean.getCostReward().get(0)).getNum() * cost_gold * ((MoneyPotBean.RebateRewardBean)moneyPotBean.getRebateReward().get(0)).getNum() / 10000.0D;
/*     */         }
/* 107 */         double cost_silver = luckyMoneyEntity.getSilverMoney();
/* 108 */         double sum_silver = 0.0D;
/* 109 */         if (cost_silver > 0.0D) {
/* 110 */           sum_silver = luckyMoneyActParameter.getPerCentAge() * ((MoneyPotBean.CostRewardBean)moneyPotBean.getCostReward().get(1)).getNum() * cost_silver * ((MoneyPotBean.RebateRewardBean)moneyPotBean.getRebateReward().get(1)).getNum() / 10000.0D;
/*     */         }
/* 112 */         long sum_lucky = (long)Math.floor(sum_gold) + (long)Math.floor(sum_silver);
/* 113 */         if (sum_lucky > 0L) {
/* 114 */           ArrayList<Reward> reward_list = new ArrayList<>();
/* 115 */           Reward reward = new Reward();
/* 116 */           reward.type = 1;
/* 117 */           reward.id = CurrencyType.CCY.getType();
/* 118 */           reward.num = sum_lucky;
/* 119 */           reward_list.add(reward);
/* 120 */           String title = LanguageConstant.getAndReplaceLanguage(5601, new String[0]);
/* 121 */           String content = LanguageConstant.getAndReplaceLanguage(5602, new String[0]);
/* 122 */           MailUtil.sendSysMail(this.playerId, reward_list, title, content);
/*     */         } 
/* 124 */         luckyMoneyEntity.setGoldMoney(0L);
/* 125 */         updateGoldMoneyDB(actId.intValue());
/* 126 */         luckyMoneyEntity.setGoldMoneySum(0L);
/* 127 */         updateGoldMoneySumDB(actId.intValue());
/* 128 */         luckyMoneyEntity.setSilverMoney(0L);
/* 129 */         updateSilverMoneyDB(actId.intValue());
/* 130 */         luckyMoneyEntity.setSilverMoneySum(0L);
/* 131 */         updateSilverMoneySumDB(actId.intValue());
/* 132 */         luckyMoneyEntity.setTaskPoint(0L);
/* 133 */         updateTaskPoint(actId.intValue());
/* 134 */         luckyMoneyEntity.setBattle(0);
/* 135 */         updateBattle(actId.intValue());
/* 136 */         luckyMoneyEntity.setOpen(false);
/* 137 */         updateOpenDB(actId.intValue());
/*     */ 
/*     */         
/* 140 */         List<Integer> openList = luckyMoneyActParameter.getLuckySaveAct(true);
/* 141 */         if (openList.isEmpty()) {
/* 142 */           BagComponent bagComponent = (BagComponent)LookUpService.getComponent(this.playerId, BagComponent.class);
/* 143 */           int GoldItemNum = (int)bagComponent.getItemNum(luckyMoneyActParameter.getCostGoldItem());
/* 144 */           if (GoldItemNum > 0) {
/* 145 */             bagComponent.deleteItem(luckyMoneyActParameter.getCostGoldItem(), GoldItemNum, ResourceEvent.RedundantCard, true);
/*     */           }
/* 147 */           int SilverItemNum = (int)bagComponent.getItemNum(luckyMoneyActParameter.getCostSilverItem());
/* 148 */           if (SilverItemNum > 0) {
/* 149 */             bagComponent.deleteItem(luckyMoneyActParameter.getCostSilverItem(), SilverItemNum, ResourceEvent.RedundantCard, true);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateGoldMoneyDB(int id) {
/* 159 */     getProxy().setUpdate(String.valueOf(id), "goldMoney");
/*     */   }
/*     */   
/*     */   public void updateSilverMoneyDB(int id) {
/* 163 */     getProxy().setUpdate(String.valueOf(id), "silverMoney");
/*     */   }
/*     */   
/*     */   public void updateGoldMoneySumDB(int id) {
/* 167 */     getProxy().setUpdate(String.valueOf(id), "goldMoneySum");
/*     */   }
/*     */   
/*     */   public void updateSilverMoneySumDB(int id) {
/* 171 */     getProxy().setUpdate(String.valueOf(id), "silverMoneySum");
/*     */   }
/*     */   
/*     */   public void updateOpenDB(int id) {
/* 175 */     getProxy().setUpdate(String.valueOf(id), "open");
/*     */   }
/*     */   
/*     */   public void updateTaskPoint(int id) {
/* 179 */     getProxy().setUpdate(String.valueOf(id), "taskPoint");
/*     */   }
/*     */   
/*     */   public void updateBattle(int id) {
/* 183 */     getProxy().setUpdate(String.valueOf(id), "battle");
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\luckymoney\LuckyMoneyComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */