/*     */ package com.linlongyx.sanguo.webgame.processors.equip;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.attribute.PartnerAttrUpdate;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.UpOrDownEquipRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.UpOrDownEquipResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UpOrDownEquipProcessor
/*     */   extends ProcessorBase<UpOrDownEquipRequest, UpOrDownEquipResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  30 */     this.response = (ResponseBase)new UpOrDownEquipResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, UpOrDownEquipRequest request) {
/*  35 */     long pid = request.pid;
/*  36 */     long equip = request.equipId;
/*  37 */     int type = request.type;
/*  38 */     EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*  39 */     EquipEntity equipEntity = equipComponent.getEquipEntity(equip);
/*  40 */     if (null == equipEntity || equip == 0L) {
/*  41 */       return 10801;
/*     */     }
/*     */     
/*  44 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*  45 */     if (null == itemBean) {
/*  46 */       return 10701;
/*     */     }
/*  48 */     if (EquipUtil.isTalisman(equipEntity.getItemId())) {
/*  49 */       return 10804;
/*     */     }
/*  51 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*     */     
/*  53 */     if (playerComponent.getFighters().indexOf(Long.valueOf(pid)) < 0) {
/*  54 */       return 10821;
/*     */     }
/*     */     
/*  57 */     if (type == 0) {
/*  58 */       byte isWear = equipEntity.getIsWear();
/*  59 */       if (isWear == 1) return 10812;
/*     */       
/*  61 */       if (pid == -1L) {
/*     */         
/*  63 */         long equipId = ((Long)playerComponent.getEquips().get(Integer.valueOf(itemBean.getEquipPart()))).longValue();
/*  64 */         if (equipId == equip) {
/*  65 */           return 10812;
/*     */         }
/*  67 */         if (equipId != 0L) {
/*  68 */           EquipEntity equipEntity1 = equipComponent.getEquipEntity(equipId);
/*  69 */           if (equipEntity1 != null) {
/*  70 */             equipEntity1.setIsWear((byte)0);
/*  71 */             equipComponent.updateIsWearToDB(equipEntity1);
/*     */           } 
/*     */         } 
/*  74 */         playerComponent.getEquips().put(Integer.valueOf(itemBean.getEquipPart()), Long.valueOf(equip));
/*  75 */         playerComponent.setEquips(playerComponent.getEquips());
/*  76 */         equipEntity.setIsWear((byte)1);
/*  77 */         equipComponent.updateIsWearToDB(equipEntity);
/*     */         
/*  79 */         ((UpOrDownEquipResponse)this.response).pid = pid;
/*  80 */         ((UpOrDownEquipResponse)this.response).equipId = equip;
/*  81 */         ((UpOrDownEquipResponse)this.response).type = type;
/*     */         
/*  83 */         AttrUpdateUtil.refreshPlayerEquip(playerSession);
/*  84 */         AttrUpdateUtil.refreshLeader(playerSession);
/*  85 */         PartnerUtil.pushPartnerInfo(playerSession, PartnerUtil.tranformPartner2(playerComponent));
/*  86 */         TaskComponent taskComponent2 = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  87 */         taskComponent2.refreshSchedule(TaskType.WearEquip, 0, 1L);
/*  88 */         taskComponent2.refreshSchedule(TaskType.UpQuiatyEquip, itemBean.getItemQuality(), 0L);
/*     */       } else {
/*     */         
/*  91 */         PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  92 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  93 */         if (null == partnerEntity) {
/*  94 */           return 13303;
/*     */         }
/*     */         
/*  97 */         long equipId = ((Long)partnerEntity.getEquips().get(Integer.valueOf(itemBean.getEquipPart()))).longValue();
/*  98 */         if (equipId == equip) {
/*  99 */           return 10812;
/*     */         }
/* 101 */         if (equipId != 0L) {
/* 102 */           EquipEntity equipEntity1 = equipComponent.getEquipEntity(equipId);
/* 103 */           if (equipEntity1 != null) {
/* 104 */             equipEntity1.setIsWear((byte)0);
/* 105 */             equipComponent.updateIsWearToDB(equipEntity1);
/*     */           } 
/*     */         } 
/* 108 */         partnerEntity.getEquips().put(Integer.valueOf(itemBean.getEquipPart()), Long.valueOf(equip));
/* 109 */         partnerEntity.setEquips(partnerEntity.getEquips());
/* 110 */         partnerComponent.updateEquipsDB(partnerEntity.getPid());
/* 111 */         equipEntity.setIsWear((byte)1);
/* 112 */         equipComponent.updateIsWearToDB(equipEntity);
/*     */         
/* 114 */         PartnerUtil.pushPartnerInfo(playerSession, PartnerUtil.tranformPartner(pid, playerComponent, partnerEntity));
/* 115 */         ((UpOrDownEquipResponse)this.response).pid = pid;
/* 116 */         ((UpOrDownEquipResponse)this.response).equipId = equip;
/* 117 */         ((UpOrDownEquipResponse)this.response).type = type;
/*     */         
/* 119 */         PartnerAttrUpdate.refreshPartnerEquip(playerSession, pid);
/* 120 */         PartnerAttrUpdate.refreshPartner(playerSession, pid);
/*     */       } 
/*     */       
/* 123 */       TaskComponent taskComponent1 = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 124 */       taskComponent1.refreshSchedule(TaskType.UpEquip, 0, 1L);
/* 125 */       taskComponent1.refreshSchedule(TaskType.UpQuiatyEquip, 0, itemBean.getItemQuality());
/*     */     }
/*     */     else {
/*     */       
/* 129 */       byte isWear = equipEntity.getIsWear();
/* 130 */       if (isWear == 0) return 10813; 
/* 131 */       if (pid == -1L) {
/*     */         
/* 133 */         long equipId = ((Long)playerComponent.getEquips().get(Integer.valueOf(itemBean.getEquipPart()))).longValue();
/* 134 */         if (equipId == 0L) {
/* 135 */           return 10804;
/*     */         }
/* 137 */         if (!playerComponent.getEquips().values().contains(Long.valueOf(equip))) {
/* 138 */           return 10804;
/*     */         }
/* 140 */         playerComponent.getEquips().put(Integer.valueOf(itemBean.getEquipPart()), Long.valueOf(0L));
/* 141 */         playerComponent.setEquips(playerComponent.getEquips());
/* 142 */         equipEntity.setIsWear((byte)0);
/* 143 */         equipComponent.updateIsWearToDB(equipEntity);
/* 144 */         PartnerUtil.pushPartnerInfo(playerSession, PartnerUtil.tranformPartner2(playerComponent));
/* 145 */         ((UpOrDownEquipResponse)this.response).pid = pid;
/* 146 */         ((UpOrDownEquipResponse)this.response).equipId = equip;
/* 147 */         ((UpOrDownEquipResponse)this.response).type = type;
/*     */         
/* 149 */         AttrUpdateUtil.refreshPlayerEquip(playerSession);
/* 150 */         AttrUpdateUtil.refreshLeader(playerSession);
/*     */       } else {
/*     */         
/* 153 */         PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/* 154 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 155 */         if (null == partnerEntity) {
/* 156 */           return 13303;
/*     */         }
/* 158 */         if (!partnerEntity.getEquips().values().contains(Long.valueOf(equip))) {
/* 159 */           return 10804;
/*     */         }
/* 161 */         long equipId = ((Long)partnerEntity.getEquips().get(Integer.valueOf(itemBean.getEquipPart()))).longValue();
/* 162 */         if (equipId == 0L) {
/* 163 */           return 10804;
/*     */         }
/* 165 */         partnerEntity.getEquips().put(Integer.valueOf(itemBean.getEquipPart()), Long.valueOf(0L));
/* 166 */         partnerEntity.setEquips(partnerEntity.getEquips());
/* 167 */         partnerComponent.updateEquipsDB(partnerEntity.getPid());
/* 168 */         equipEntity.setIsWear((byte)0);
/* 169 */         equipComponent.updateIsWearToDB(equipEntity);
/*     */         
/* 171 */         PartnerUtil.pushPartnerInfo(playerSession, PartnerUtil.tranformPartner(pid, playerComponent, partnerEntity));
/* 172 */         ((UpOrDownEquipResponse)this.response).pid = pid;
/* 173 */         ((UpOrDownEquipResponse)this.response).equipId = equip;
/* 174 */         ((UpOrDownEquipResponse)this.response).type = type;
/*     */         
/* 176 */         PartnerAttrUpdate.refreshPartnerEquip(playerSession, pid);
/* 177 */         PartnerAttrUpdate.refreshPartner(playerSession, pid);
/*     */       } 
/*     */     } 
/* 180 */     LogUtil.errorLog(new Object[] { "UpOrDownEquipProcessor:", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(equipEntity.getItemId()), Long.valueOf(equip), Long.valueOf(pid), Integer.valueOf(type) });
/* 181 */     AttrUpdateUtil.refreshGrowthGoalStar(playerSession);
/* 182 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 183 */     taskComponent.refreshSchedule(TaskType.PartnerEquipNum, 0, 0L);
/* 184 */     taskComponent.refreshSchedule(TaskType.EquipArtifice, 0, 0L);
/* 185 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\equip\UpOrDownEquipProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */