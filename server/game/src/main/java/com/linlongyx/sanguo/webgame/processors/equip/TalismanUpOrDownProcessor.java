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
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.TalismanUpOrDownRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.TalismanUpOrDownResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TalismanUpOrDownProcessor
/*     */   extends ProcessorBase<TalismanUpOrDownRequest, TalismanUpOrDownResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  31 */     this.response = (ResponseBase)new TalismanUpOrDownResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, TalismanUpOrDownRequest request) {
/*  36 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 2901)) {
/*  37 */       return 10061;
/*     */     }
/*  39 */     long pid = request.pid;
/*  40 */     long equip = request.equipId;
/*  41 */     int type = request.type;
/*     */     
/*  43 */     EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*  44 */     EquipEntity equipEntity = equipComponent.getEquipEntity(equip);
/*  45 */     if (null == equipEntity || equip == 0L) {
/*  46 */       return 10801;
/*     */     }
/*  48 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*  49 */     if (null == itemBean) {
/*  50 */       return 10701;
/*     */     }
/*  52 */     if (!EquipUtil.isTalisman(equipEntity.getItemId())) {
/*  53 */       return 10823;
/*     */     }
/*     */     
/*  56 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*     */     
/*  58 */     if (playerComponent.getFighters().indexOf(Long.valueOf(pid)) < 0) {
/*  59 */       return 10821;
/*     */     }
/*  61 */     if (type == 0) {
/*  62 */       byte isWear = equipEntity.getIsWear();
/*  63 */       if (isWear == 1) return 10812;
/*     */       
/*  65 */       if (pid == -1L) {
/*     */         
/*  67 */         long equipId = playerComponent.getTalisman();
/*  68 */         if (equipId == equip) {
/*  69 */           return 10812;
/*     */         }
/*  71 */         if (equipId != 0L) {
/*  72 */           EquipEntity equipEntity1 = equipComponent.getEquipEntity(equipId);
/*  73 */           if (equipEntity1 != null) {
/*  74 */             equipEntity1.setIsWear((byte)0);
/*  75 */             equipComponent.updateIsWearToDB(equipEntity1);
/*  76 */             equipEntity1.setBelondTo(0L);
/*  77 */             equipComponent.updateBelondToToDB(equipEntity1);
/*     */           } 
/*     */         } 
/*  80 */         playerComponent.setTalisman(equip);
/*  81 */         equipEntity.setIsWear((byte)1);
/*  82 */         equipComponent.updateIsWearToDB(equipEntity);
/*  83 */         equipEntity.setBelondTo(pid);
/*  84 */         equipComponent.updateBelondToToDB(equipEntity);
/*     */         
/*  86 */         ((TalismanUpOrDownResponse)this.response).pid = pid;
/*  87 */         ((TalismanUpOrDownResponse)this.response).equipId = equip;
/*  88 */         ((TalismanUpOrDownResponse)this.response).type = type;
/*     */         
/*  90 */         AttrUpdateUtil.refreshLeader(playerSession);
/*  91 */         AttrUpdateUtil.refreshAllEquip(playerSession);
/*  92 */         PartnerUtil.pushPartnerInfo(playerSession, PartnerUtil.tranformPartner2(playerComponent));
/*     */       } else {
/*     */         
/*  95 */         PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  96 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  97 */         if (null == partnerEntity) {
/*  98 */           return 13303;
/*     */         }
/*     */         
/* 101 */         long equipId = partnerEntity.getTalisman();
/* 102 */         if (equipId == equip) {
/* 103 */           return 10812;
/*     */         }
/* 105 */         if (equipId != 0L) {
/* 106 */           EquipEntity equipEntity1 = equipComponent.getEquipEntity(equipId);
/* 107 */           if (equipEntity1 != null) {
/* 108 */             equipEntity1.setIsWear((byte)0);
/* 109 */             equipComponent.updateIsWearToDB(equipEntity1);
/* 110 */             equipEntity1.setBelondTo(0L);
/* 111 */             equipComponent.updateBelondToToDB(equipEntity1);
/*     */           } 
/*     */         } 
/* 114 */         partnerEntity.setTalisman(equip);
/* 115 */         partnerComponent.updateTalismanDB(partnerEntity.getPid());
/* 116 */         equipEntity.setIsWear((byte)1);
/* 117 */         equipComponent.updateIsWearToDB(equipEntity);
/* 118 */         equipEntity.setBelondTo(pid);
/* 119 */         equipComponent.updateBelondToToDB(equipEntity);
/* 120 */         PartnerUtil.pushPartnerInfo(playerSession, PartnerUtil.tranformPartner(pid, playerComponent, partnerEntity));
/*     */       } 
/*     */       
/* 123 */       ((TalismanUpOrDownResponse)this.response).pid = pid;
/* 124 */       ((TalismanUpOrDownResponse)this.response).equipId = equip;
/* 125 */       ((TalismanUpOrDownResponse)this.response).type = type;
/*     */       
/* 127 */       AttrUpdateUtil.refreshAllEquip(playerSession);
/* 128 */       PartnerAttrUpdate.refreshPartner(playerSession, pid);
/*     */     
/*     */     }
/* 131 */     else if (type == 1) {
/*     */       
/* 133 */       byte isWear = equipEntity.getIsWear();
/* 134 */       if (isWear == 0) return 10813;
/*     */       
/* 136 */       if (pid == -1L) {
/*     */         
/* 138 */         long equipId = playerComponent.getTalisman();
/* 139 */         if (equipId == 0L) {
/* 140 */           return 10804;
/*     */         }
/* 142 */         if (equipId != equip) {
/* 143 */           return 10804;
/*     */         }
/* 145 */         playerComponent.setTalisman(0L);
/* 146 */         equipEntity.setIsWear((byte)0);
/* 147 */         equipComponent.updateIsWearToDB(equipEntity);
/* 148 */         equipEntity.setBelondTo(0L);
/* 149 */         equipComponent.updateBelondToToDB(equipEntity);
/* 150 */         PartnerUtil.pushPartnerInfo(playerSession, PartnerUtil.tranformPartner2(playerComponent));
/* 151 */         ((TalismanUpOrDownResponse)this.response).pid = pid;
/* 152 */         ((TalismanUpOrDownResponse)this.response).equipId = equip;
/* 153 */         ((TalismanUpOrDownResponse)this.response).type = type;
/*     */         
/* 155 */         AttrUpdateUtil.refreshAllEquip(playerSession);
/* 156 */         AttrUpdateUtil.refreshLeader(playerSession);
/*     */       } else {
/*     */         
/* 159 */         PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/* 160 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 161 */         if (null == partnerEntity) {
/* 162 */           return 13303;
/*     */         }
/* 164 */         long equipId = partnerEntity.getTalisman();
/* 165 */         if (equipId == 0L) {
/* 166 */           return 10804;
/*     */         }
/* 168 */         if (equipId != equip) {
/* 169 */           return 10804;
/*     */         }
/* 171 */         partnerEntity.setTalisman(0L);
/* 172 */         partnerComponent.updateEquipsDB(pid);
/* 173 */         equipEntity.setIsWear((byte)0);
/* 174 */         equipComponent.updateIsWearToDB(equipEntity);
/* 175 */         equipEntity.setBelondTo(0L);
/* 176 */         equipComponent.updateBelondToToDB(equipEntity);
/*     */         
/* 178 */         PartnerUtil.pushPartnerInfo(playerSession, PartnerUtil.tranformPartner(pid, playerComponent, partnerEntity));
/* 179 */         ((TalismanUpOrDownResponse)this.response).pid = pid;
/* 180 */         ((TalismanUpOrDownResponse)this.response).equipId = equip;
/* 181 */         ((TalismanUpOrDownResponse)this.response).type = type;
/*     */         
/* 183 */         AttrUpdateUtil.refreshAllEquip(playerSession);
/* 184 */         PartnerAttrUpdate.refreshPartner(playerSession, pid);
/*     */       } 
/*     */     } 
/*     */     
/* 188 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 189 */     taskComponent.refreshSchedule(TaskType.TalimanUp, 0, 0L);
/* 190 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\equip\TalismanUpOrDownProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */