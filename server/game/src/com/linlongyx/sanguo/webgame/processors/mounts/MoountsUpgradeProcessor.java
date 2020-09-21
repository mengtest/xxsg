/*     */ package com.linlongyx.sanguo.webgame.processors.mounts;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.mounts.MountsComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.mounts.MountsEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MountLevelBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.MountsParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.MoountsUpgradeRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.MoountsUpgradeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ 
/*     */ public class MoountsUpgradeProcessor
/*     */   extends ProcessorBase<MoountsUpgradeRequest, MoountsUpgradeResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  31 */     this.response = (ResponseBase)new MoountsUpgradeResponse();
/*     */   }
/*     */   
/*  34 */   int oldLevel = 0;
/*  35 */   int newLevel = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, MoountsUpgradeRequest request) {
/*  40 */     MountsComponent mountsComponent = (MountsComponent)playerSession.getPlayer().createIfNotExist(MountsComponent.class);
/*  41 */     MountsEntity mountsEntity = mountsComponent.getEntity(request.mounts);
/*  42 */     if (null == mountsEntity) {
/*  43 */       return 19002;
/*     */     }
/*  45 */     int level = mountsEntity.getLevel();
/*  46 */     this.oldLevel = mountsEntity.getLevel();
/*  47 */     ArrayList<Integer> itemList = request.itemId;
/*  48 */     ArrayList<Integer> itemNums = request.ItemNum;
/*  49 */     MountsParameter mountsParameter = (MountsParameter)ParameterConstant.getParameter(42);
/*  50 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*     */     
/*  52 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  53 */     if (level >= playerComponent.getLevel()) {
/*  54 */       return 10086;
/*     */     }
/*  56 */     MountLevelBean mountLevelBean = (MountLevelBean)JsonTableService.getJsonData(level, MountLevelBean.class);
/*  57 */     if (null == mountLevelBean) {
/*  58 */       return 10006;
/*     */     }
/*  60 */     if (!mountLevelBean.getBreakC().isEmpty() && !mountsEntity.getBreaksLevel().containsKey(Integer.valueOf(level))) {
/*  61 */       return 19004;
/*     */     }
/*  63 */     if (mountLevelBean.getExp() == 0) {
/*  64 */       return 19004;
/*     */     }
/*  66 */     if (itemNums.isEmpty()) {
/*  67 */       return 11808;
/*     */     }
/*  69 */     long addExp = 0L;
/*  70 */     for (int i = 0; i < itemList.size(); i++) {
/*  71 */       if (!mountsParameter.getExpMap().containsKey(itemList.get(i))) {
/*  72 */         return 10703;
/*     */       }
/*  74 */       if (bagComponent.getItemNum(((Integer)itemList.get(i)).intValue()) < ((Integer)itemNums.get(i)).intValue()) {
/*  75 */         return 10050;
/*     */       }
/*  77 */       if (((Integer)itemNums.get(i)).intValue() <= 0) {
/*  78 */         return 13110;
/*     */       }
/*  80 */       addExp += (((Integer)mountsParameter.getExpMap().get(itemList.get(i))).intValue() * ((Integer)itemNums.get(i)).intValue());
/*     */     } 
/*     */     
/*  83 */     long aExp = mountsEntity.getExp() + addExp;
/*  84 */     boolean upLevel = false;
/*  85 */     while (aExp >= mountLevelBean.getExp() && mountLevelBean.getExp() != 0) {
/*  86 */       aExp -= mountLevelBean.getExp();
/*  87 */       level++;
/*  88 */       upLevel = true;
/*  89 */       mountLevelBean = (MountLevelBean)JsonTableService.getJsonData(level, MountLevelBean.class);
/*  90 */       if (!mountLevelBean.getBreakC().isEmpty() || level >= playerComponent.getLevel()) {
/*     */         break;
/*     */       }
/*     */     } 
/*  94 */     if (level > playerComponent.getLevel()) {
/*  95 */       return 13319;
/*     */     }
/*  97 */     for (int j = 0; j < itemList.size(); j++) {
/*  98 */       bagComponent.deleteItem(((Integer)itemList.get(j)).intValue(), ((Integer)itemNums.get(j)).intValue(), ResourceEvent.MountsUpgrade, true);
/*     */     }
/*     */ 
/*     */     
/* 102 */     if (upLevel) {
/* 103 */       HashedMap<Integer, Long> hashedMap = new HashedMap();
/*     */       
/* 105 */       ArrayList<Integer> ids = mountsParameter.GetExpList();
/* 106 */       for (int k = ids.size() - 1; k >= 0; k--) {
/* 107 */         int num = ((Integer)mountsParameter.getExpMap().get(ids.get(k))).intValue();
/* 108 */         if (aExp >= num) {
/* 109 */           long count2 = aExp / num;
/* 110 */           aExp %= num;
/* 111 */           int itemId = ((Integer)ids.get(k)).intValue();
/* 112 */           long itemNum = count2;
/* 113 */           if (hashedMap.containsKey(Integer.valueOf(itemId))) {
/* 114 */             hashedMap.put(Integer.valueOf(itemId), Long.valueOf(((Long)hashedMap.get(Integer.valueOf(itemId))).longValue() + itemNum));
/*     */           } else {
/* 116 */             hashedMap.put(Integer.valueOf(itemId), Long.valueOf(itemNum));
/*     */           } 
/*     */         } 
/*     */       } 
/* 120 */       ArrayList<Reward> rewards = new ArrayList<>();
/* 121 */       for (Iterator<Integer> iterator = hashedMap.keySet().iterator(); iterator.hasNext(); ) { int itemId = ((Integer)iterator.next()).intValue();
/* 122 */         Reward reward = new Reward();
/* 123 */         reward.type = 2;
/* 124 */         reward.id = itemId;
/* 125 */         reward.num = ((Long)hashedMap.get(Integer.valueOf(itemId))).longValue();
/* 126 */         rewards.add(reward); }
/*     */       
/* 128 */       if (!rewards.isEmpty()) {
/* 129 */         FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.MountsUpgrade, true);
/*     */       }
/*     */     } 
/* 132 */     mountsEntity.setLevel(level);
/* 133 */     mountsComponent.updateLevelDB(mountsEntity.getMountsId());
/* 134 */     mountsEntity.setExp((int)aExp);
/* 135 */     mountsComponent.updateExpDB(mountsEntity.getMountsId());
/* 136 */     this.newLevel = mountsEntity.getLevel();
/* 137 */     if (this.oldLevel != this.newLevel) {
/* 138 */       AttrUpdateUtil.refreshMounts(playerSession);
/* 139 */       MountsUtil.updateMountsFightValue(mountsEntity, playerSession, mountsComponent, false);
/*     */     } 
/* 141 */     ((MoountsUpgradeResponse)this.response).mounts = mountsEntity.getMountsId();
/* 142 */     ((MoountsUpgradeResponse)this.response).level = mountsEntity.getLevel();
/* 143 */     ((MoountsUpgradeResponse)this.response).exp = mountsEntity.getExp();
/* 144 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 145 */     if (null != taskComponent) {
/* 146 */       taskComponent.refreshSchedule(TaskType.MountsFeed, 0, 1L);
/*     */     }
/* 148 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mounts\MoountsUpgradeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */