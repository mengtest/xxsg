/*     */ package com.linlongyx.sanguo.webgame.processors.partner;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterStarBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerStarUpRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerStarUpResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class PartnerStarUpProcessor
/*     */   extends ProcessorBase<PartnerStarUpRequest, PartnerStarUpResponse> {
/*     */   protected void initResponse() {
/*  33 */     this.response = (ResponseBase)new PartnerStarUpResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, PartnerStarUpRequest request) {
/*  38 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 9804)) {
/*  39 */       return 10061;
/*     */     }
/*  41 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  42 */     PartnerEntity partnerEntity = partnerComponent.getEntity(request.pid);
/*  43 */     if (null == partnerEntity) {
/*  44 */       return 13303;
/*     */     }
/*  46 */     FighterStarBean fighterStarBean = (FighterStarBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterStarBean.class);
/*  47 */     if (null == fighterStarBean) {
/*  48 */       return 13303;
/*     */     }
/*  50 */     FighterStarBean.LevelBean levelBean = (FighterStarBean.LevelBean)fighterStarBean.getLevel().get(Integer.valueOf(partnerEntity.getStars()));
/*  51 */     if (null == levelBean) {
/*  52 */       return 13317;
/*     */     }
/*  54 */     if (levelBean.getCost() == null || levelBean.getCost().isEmpty()) {
/*  55 */       return 13321;
/*     */     }
/*  57 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*     */     
/*  59 */     ArrayList<Reward> costRward = FinanceUtil.transform(levelBean.getCost());
/*     */     
/*  61 */     ArrayList<Reward> moneyRward = FinanceUtil.transform(levelBean.getMoney());
/*  62 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  63 */     rewards.addAll(costRward);
/*  64 */     rewards.addAll(moneyRward);
/*     */     
/*  66 */     ArrayList<Reward> rewards2 = new ArrayList<>();
/*  67 */     rewards2.addAll(moneyRward);
/*  68 */     short result = CostUtil.checkRewards(moneyRward, playerSession, bagComponent);
/*  69 */     if (result != 0) {
/*  70 */       return result;
/*     */     }
/*  72 */     long universalNum = 0L;
/*  73 */     long nowNum = 0L;
/*  74 */     int item = 0;
/*  75 */     int itemId = 0;
/*  76 */     boolean enough = true;
/*  77 */     short res = CostUtil.checkRewards(costRward, playerSession, bagComponent);
/*  78 */     if (res == 10062) {
/*  79 */       enough = false;
/*  80 */       if (request.isChoose == 0) {
/*  81 */         return result;
/*     */       }
/*  83 */       long needNum = 99999999L;
/*  84 */       for (Reward reward : costRward) {
/*  85 */         needNum = reward.num;
/*  86 */         itemId = reward.id;
/*     */       } 
/*  88 */       nowNum = bagComponent.getItemNum(itemId);
/*  89 */       universalNum = needNum - nowNum;
/*  90 */       if (universalNum <= 0L) {
/*  91 */         return 10006;
/*     */       }
/*  93 */       FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/*  94 */       if (null != fighterBean) {
/*  95 */         LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*  96 */         item = ((Integer)loginParameter.getUniversalMap().getOrDefault(Integer.valueOf(fighterBean.getQuality()), Integer.valueOf(0))).intValue();
/*  97 */         if (item != 0) {
/*  98 */           if (!bagComponent.check(item, universalNum)) {
/*  99 */             return 10050;
/*     */           }
/*     */         } else {
/* 102 */           return 10006;
/*     */         } 
/* 104 */         Reward reward = new Reward();
/* 105 */         reward.id = itemId;
/* 106 */         reward.num = nowNum;
/* 107 */         reward.type = 2;
/* 108 */         rewards2.add(reward);
/* 109 */         Reward reward2 = new Reward();
/* 110 */         reward2.id = item;
/* 111 */         reward2.num = universalNum;
/* 112 */         reward2.type = 2;
/* 113 */         rewards2.add(reward2);
/*     */       } 
/*     */     } 
/*     */     
/* 117 */     if (enough) {
/* 118 */       CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.PartnerStarUp);
/*     */     } else {
/* 120 */       CostUtil.costs(rewards2, playerSession, bagComponent, ResourceEvent.PartnerStarUp);
/*     */     } 
/* 122 */     long beforeUp = partnerComponent.getPartnerAttrUp().getAttrByType(AttributeType.FIRST_HAND.getType(), partnerEntity.getPid());
/* 123 */     partnerEntity.setStars(partnerEntity.getStars() + 1);
/* 124 */     partnerComponent.updateStarsDB(request.pid);
/*     */ 
/*     */     
/* 127 */     AttrUpdateUtil.refreshPartner(playerSession);
/* 128 */     AttrUpdateUtil.refreshAssist(playerSession);
/* 129 */     ((PartnerStarUpResponse)this.response).star = partnerEntity.getStars();
/* 130 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 131 */     if (taskComponent != null) {
/* 132 */       taskComponent.refreshSchedule(TaskType.StarFighter, partnerEntity.getStars(), 0L);
/* 133 */       taskComponent.refreshSchedule(TaskType.PartnerReincarnStar, partnerEntity.getTableId(), 0L);
/*     */     } 
/*     */     
/* 136 */     if (partnerEntity.getStars() == 6) {
/* 137 */       PlayerUtil.sendNotice(7, playerSession.getPlayer(), partnerEntity.getTableId(), null);
/*     */     } else {
/* 139 */       PlayerUtil.sendNotice(11, playerSession.getPlayer(), partnerEntity.getTableId(), partnerEntity.getStars() + "");
/*     */     } 
/* 141 */     long afterUp = partnerComponent.getPartnerAttrUp().getAttrByType(AttributeType.FIRST_HAND.getType(), partnerEntity.getPid());
/*     */     
/* 143 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 144 */     playerComponent.setFirstHand(playerComponent.getFirstHand() - beforeUp + afterUp);
/*     */     
/* 146 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\PartnerStarUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */