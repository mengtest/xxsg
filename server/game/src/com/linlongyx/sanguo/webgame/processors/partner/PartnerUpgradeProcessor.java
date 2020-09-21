/*     */ package com.linlongyx.sanguo.webgame.processors.partner;
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ExpupBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.PartnerParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerUpgradeRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerUpgradeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ 
/*     */ public class PartnerUpgradeProcessor extends ProcessorBase<PartnerUpgradeRequest, PartnerUpgradeResponse> {
/*     */   protected void initResponse() {
/*  37 */     this.response = (ResponseBase)new PartnerUpgradeResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, PartnerUpgradeRequest request) {
/*  42 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 9802))
/*  43 */       return 10061; 
/*  44 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  45 */     PartnerEntity partnerEntity = partnerComponent.getEntity(request.pid);
/*     */     
/*  47 */     if (null == partnerEntity) {
/*  48 */       return 13303;
/*     */     }
/*  50 */     int level = partnerEntity.getLevel();
/*     */     
/*  52 */     int oldLevel = partnerEntity.getLevel();
/*     */     
/*  54 */     ArrayList<Integer> itemList = request.itemId;
/*  55 */     ArrayList<Integer> itemNums = request.ItemNum;
/*  56 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*  57 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  58 */     PartnerParameter partnerParameter = (PartnerParameter)ParameterConstant.getParameter(33);
/*     */     
/*  60 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  61 */     if (level >= playerComponent.getLevel()) {
/*  62 */       return 13319;
/*     */     }
/*  64 */     Map<Integer, Set<Integer>> levelMap = partnerParameter.getLevelMap();
/*  65 */     if (levelMap.get(Integer.valueOf(oldLevel)) != null && !PartnerUtil.isReincarn(levelMap, oldLevel, partnerEntity)) {
/*  66 */       return 13334;
/*     */     }
/*  68 */     ExpupBean expupBean = (ExpupBean)JsonTableService.getJsonData(level, ExpupBean.class);
/*  69 */     if (null == expupBean) {
/*  70 */       return 13300;
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
/*     */     
/*  84 */     long addExp = 0L;
/*  85 */     for (int i = 0; i < itemList.size(); i++) {
/*  86 */       if (!loginParameter.getExpMap().containsKey(itemList.get(i))) {
/*  87 */         return 10703;
/*     */       }
/*  89 */       if (bagComponent.getItemNum(((Integer)itemList.get(i)).intValue()) < ((Integer)itemNums.get(i)).intValue()) {
/*  90 */         return 10050;
/*     */       }
/*  92 */       if (((Integer)itemNums.get(i)).intValue() <= 0) {
/*  93 */         return 13110;
/*     */       }
/*  95 */       addExp += ((Integer)loginParameter.getExpMap().get(itemList.get(i))).intValue() * 1L * ((Integer)itemNums.get(i)).intValue();
/*     */     } 
/*  97 */     CurrencyType currencyType = CurrencyType.getCurrencyType(partnerParameter.getCostItemId());
/*  98 */     long costCoin = 0L;
/*  99 */     long aExp = partnerEntity.getExp() + addExp;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     for (Iterator<Integer> iterator = loginParameter.getCoinExpMap().keySet().iterator(); iterator.hasNext(); ) { int value = ((Integer)iterator.next()).intValue();
/* 106 */       costCoin = addExp * value / ((Integer)loginParameter.getCoinExpMap().get(Integer.valueOf(value))).intValue(); }
/*     */ 
/*     */     
/* 109 */     if (currencyType.getType() == 0) {
/* 110 */       return 10701;
/*     */     }
/* 112 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), currencyType, costCoin)) {
/* 113 */       return 10087;
/*     */     }
/*     */ 
/*     */     
/* 117 */     int upNum = 0;
/* 118 */     while (aExp >= expupBean.getExp() && expupBean.getExp() != 0L) {
/* 119 */       aExp -= expupBean.getExp();
/* 120 */       level++;
/* 121 */       upNum++;
/*     */       
/* 123 */       if (level == 200) {
/* 124 */         PlayerUtil.sendNotice(6, playerSession.getPlayer(), partnerEntity.getTableId(), null);
/*     */       }
/* 126 */       if (level >= playerComponent.getLevel()) {
/*     */         break;
/*     */       }
/* 129 */       if (levelMap.get(Integer.valueOf(level)) != null && !PartnerUtil.isReincarn(levelMap, level, partnerEntity)) {
/*     */         break;
/*     */       }
/* 132 */       expupBean = (ExpupBean)JsonTableService.getJsonData(level, ExpupBean.class);
/*     */     } 
/*     */     
/* 135 */     FinanceUtil.decCurrency(playerSession.getPlayer(), currencyType, costCoin, ResourceEvent.PartnerAddExp, true);
/* 136 */     for (int j = 0; j < itemList.size(); j++) {
/* 137 */       bagComponent.deleteItem(((Integer)itemList.get(j)).intValue(), ((Integer)itemNums.get(j)).intValue(), ResourceEvent.PartnerAddExp, true);
/*     */     }
/* 139 */     if (upNum > 0) {
/*     */ 
/*     */       
/* 142 */       long coin = 0L;
/* 143 */       for (Iterator<Integer> iterator1 = loginParameter.getCoinExpMap().keySet().iterator(); iterator1.hasNext(); ) { int value = ((Integer)iterator1.next()).intValue();
/* 144 */         coin = aExp * value / ((Integer)loginParameter.getCoinExpMap().get(Integer.valueOf(value))).intValue(); }
/*     */       
/* 146 */       int currId = currencyType.getType();
/*     */       
/* 148 */       HashedMap<Integer, Long> hashedMap = new HashedMap();
/*     */       
/* 150 */       ArrayList<Integer> ids = loginParameter.GetExpList();
/* 151 */       for (int k = ids.size() - 1; k >= 0; k--) {
/* 152 */         int num = ((Integer)loginParameter.getExpMap().get(ids.get(k))).intValue();
/* 153 */         if (aExp >= num) {
/* 154 */           long count2 = aExp / num;
/* 155 */           aExp %= num;
/* 156 */           int itemId = ((Integer)ids.get(k)).intValue();
/* 157 */           long itemNum = count2;
/* 158 */           if (hashedMap.containsKey(Integer.valueOf(itemId))) {
/* 159 */             hashedMap.put(Integer.valueOf(itemId), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(itemId))).longValue() + itemNum));
/*     */           } else {
/* 161 */             hashedMap.put(Integer.valueOf(itemId), Long.valueOf(itemNum));
/*     */           } 
/*     */         } 
/*     */       } 
/* 165 */       aExp = 0L;
/* 166 */       ArrayList<Reward> rewards = new ArrayList<>();
/* 167 */       for (Iterator<Integer> iterator2 = hashedMap.keySet().iterator(); iterator2.hasNext(); ) { int itemId = ((Integer)iterator2.next()).intValue();
/* 168 */         Reward reward1 = new Reward();
/* 169 */         reward1.type = 2;
/* 170 */         reward1.id = itemId;
/* 171 */         reward1.num = ((Long)hashedMap.get(Integer.valueOf(itemId))).longValue();
/* 172 */         rewards.add(reward1); }
/*     */ 
/*     */       
/* 175 */       Reward reward = new Reward();
/* 176 */       reward.type = 1;
/* 177 */       reward.id = currId;
/* 178 */       reward.num = coin;
/* 179 */       rewards.add(reward);
/*     */       
/* 181 */       if (!rewards.isEmpty()) {
/* 182 */         FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.PartnerAddExp, true);
/*     */       }
/*     */     } 
/* 185 */     partnerEntity.setLevel(level);
/* 186 */     partnerEntity.setExp(aExp);
/* 187 */     partnerComponent.updateLevelDB(partnerEntity.getPid());
/* 188 */     partnerComponent.updateExpDB(partnerEntity.getPid());
/* 189 */     if (oldLevel != partnerEntity.getLevel()) {
/* 190 */       AttrUpdateUtil.refreshPartner(playerSession);
/*     */     }
/* 192 */     ((PartnerUpgradeResponse)this.response).pid = partnerEntity.getPid();
/* 193 */     ((PartnerUpgradeResponse)this.response).level = partnerEntity.getLevel();
/* 194 */     ((PartnerUpgradeResponse)this.response).exp = partnerEntity.getExp();
/*     */     
/* 196 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 197 */     taskComponent.refreshSchedule(TaskType.PartnerLevelUp, 0, upNum);
/* 198 */     taskComponent.refreshSchedule(TaskType.PartnerReachLevel, 0, 0L);
/* 199 */     taskComponent.refreshSchedule(TaskType.NPartnerLevel, 0, 0L);
/* 200 */     LogUtil.gameLog(LogType.PARTNER_EXP, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(partnerEntity.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Long.valueOf(partnerEntity.getPid()), Long.valueOf(addExp), Integer.valueOf(partnerEntity.getLevel()), Long.valueOf(partnerEntity.getExp()), TimeUtil.getFormatDate() });
/*     */     
/* 202 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\PartnerUpgradeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */