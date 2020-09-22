/*     */ package com.linlongyx.sanguo.webgame.processors.equip;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.attribute.PartnerAttrUpdate;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipStarBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipStarUpRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipStarUpResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.EquipData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ public class EquipStarUpProcessor
/*     */   extends ProcessorBase<EquipStarUpRequest, EquipStarUpResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  35 */     this.response = (ResponseBase)new EquipStarUpResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, EquipStarUpRequest request) {
/*  40 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 806)) {
/*  41 */       return 10061;
/*     */     }
/*  43 */     long pid = request.pid;
/*  44 */     long mid = request.equipId;
/*  45 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  46 */     if (playerComponent.getFighters().indexOf(Long.valueOf(pid)) < 0) {
/*  47 */       return 13302;
/*     */     }
/*  49 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  50 */     Set<Long> equipIdSet = new HashSet<>();
/*  51 */     if (pid != -1L) {
/*  52 */       PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  53 */       if (null == partnerEntity) {
/*  54 */         return 13303;
/*     */       }
/*  56 */       equipIdSet.addAll(partnerEntity.getEquips().values());
/*     */     } else {
/*  58 */       equipIdSet.addAll(playerComponent.getEquips().values());
/*     */     } 
/*  60 */     if (!equipIdSet.contains(Long.valueOf(mid))) {
/*  61 */       return 10805;
/*     */     }
/*  63 */     EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*  64 */     EquipEntity equipEntity = equipComponent.getEquipEntity(mid);
/*  65 */     if (null == equipEntity) {
/*  66 */       return 10804;
/*     */     }
/*  68 */     int itemId = equipEntity.getItemId();
/*  69 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(itemId, ItemBean.class);
/*  70 */     if (null == itemBean) {
/*  71 */       return 10050;
/*     */     }
/*  73 */     EquipStarBean equipStarBean = (EquipStarBean)JsonTableService.getJsonData(itemBean.getSuitType(), EquipStarBean.class);
/*  74 */     if (null == equipStarBean) {
/*  75 */       return 10006;
/*     */     }
/*  77 */     EquipStarBean.StarBean starBean = (EquipStarBean.StarBean)equipStarBean.getStar().get(Integer.valueOf(equipEntity.getStar()));
/*  78 */     if (starBean == null || starBean.getCost() == 0) {
/*  79 */       return 13321;
/*     */     }
/*     */     
/*  82 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*     */     
/*  84 */     Reward reward = new Reward();
/*  85 */     reward.type = 5;
/*  86 */     reward.id = itemId;
/*  87 */     reward.num = starBean.getCost();
/*  88 */     short code = CostUtil.check(reward, playerSession, bagComponent);
/*  89 */     if (code != 0) {
/*  90 */       return code;
/*     */     }
/*  92 */     CostUtil.cost(reward, playerSession, bagComponent, ResourceEvent.EquipStarUp);
/*  93 */     equipEntity.setStar(equipEntity.getStar() + 1);
/*  94 */     equipComponent.updateStarToDB(equipEntity);
/*     */     
/*  96 */     if (pid != -1L) {
/*  97 */       PartnerAttrUpdate.refreshPartnerEquip(playerSession, pid);
/*     */     } else {
/*  99 */       AttrUpdateUtil.refreshPlayerEquip(playerSession);
/*     */     } 
/* 101 */     AttrUpdateUtil.refreshGrowthGoalStar(playerSession);
/* 102 */     EquipData equipData = EquipUtil.getEquipData(equipEntity);
/* 103 */     EquipUtil.EquipDataSys(playerSession, equipData);
/* 104 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 105 */     taskComponent.refreshSchedule(TaskType.EquipStarTarget, 0, 0L);
/* 106 */     ((EquipStarUpResponse)this.response).equipId = mid;
/* 107 */     ((EquipStarUpResponse)this.response).pid = pid;
/* 108 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\equip\EquipStarUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */