/*     */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*     */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipStarBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MasterForgingBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.EquipParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.equip.EquipUtil;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class GrowthGoalAttrUp extends AttrUpBase {
/*     */   public GrowthGoalAttrUp() {
/*  28 */     super(PlayerAttrUp.AttrUpType.GROWTHGOAL.getIndex(), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void refresh(IPlayer player, Set<Integer> updates) {
/*  33 */     Arrays.fill(this.attrs, 0L);
/*  34 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  35 */     EquipComponent equipComponent = (EquipComponent)player.createIfNotExist(EquipComponent.class);
/*  36 */     EquipParameter parameter = (EquipParameter)ParameterConstant.getParameter(78);
/*  37 */     Map<Integer, Map<Integer, MasterForgingBean>> map = parameter.getGoals();
/*  38 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/*  39 */     if (fighterBean == null) {
/*     */       return;
/*     */     }
/*  42 */     List<Integer> typeList = new ArrayList<>(map.keySet());
/*  43 */     Collections.sort(typeList);
/*     */     
/*  45 */     for (Integer type : typeList) {
/*  46 */       MasterForgingBean formGoalsBean = EquipUtil.getEquipStrengthGoal(equipComponent, playerComponent.getEquips(), type.intValue());
/*  47 */       if (null != formGoalsBean) {
/*  48 */         formGoalsBean.getAttrBonus().forEach(attrBonusBean -> AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBonusBean.getId(), attrBonusBean.getNum(), true));
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  54 */     updateEquipStarGoalth(player, fighterBean, this.attrs, updates, -1L);
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
/*     */   public void refreshPartner(IPlayer player, Set<Integer> updates, long pid) {
/*  68 */     Arrays.fill(this.attrs, 0L);
/*  69 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/*  70 */     PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  71 */     if (partnerEntity == null) {
/*     */       return;
/*     */     }
/*  74 */     EquipComponent equipComponent = (EquipComponent)player.createIfNotExist(EquipComponent.class);
/*  75 */     EquipParameter parameter = (EquipParameter)ParameterConstant.getParameter(78);
/*  76 */     Map<Integer, Map<Integer, MasterForgingBean>> map = parameter.getGoals();
/*  77 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/*  78 */     if (fighterBean == null) {
/*     */       return;
/*     */     }
/*  81 */     List<Integer> typeList = new ArrayList<>(map.keySet());
/*  82 */     Collections.sort(typeList);
/*     */     
/*  84 */     for (Integer type : typeList) {
/*  85 */       MasterForgingBean formGoalsBean = EquipUtil.getEquipStrengthGoal(equipComponent, partnerEntity.getEquips(), type.intValue());
/*  86 */       if (null != formGoalsBean) {
/*  87 */         formGoalsBean.getAttrBonus().forEach(attrBonusBean -> AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBonusBean.getId(), attrBonusBean.getNum(), true));
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  93 */     updateEquipStarGoalth(player, fighterBean, this.attrs, updates, pid);
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
/*     */   public void updateEquipStarGoalth(IPlayer player, FighterBean fighterBean, long[] attrs, Set<Integer> updates, long refreshPid) {
/* 106 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 107 */     EquipComponent equipComponent = (EquipComponent)player.createIfNotExist(EquipComponent.class);
/* 108 */     for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/* 109 */       if (pid == 0L) {
/*     */         continue;
/*     */       }
/* 112 */       if (pid == -1L) {
/*     */         
/* 114 */         EquipStarBean.StarBean starBean1 = EquipUtil.getEquipStarGoalth(equipComponent, playerComponent.getEquips());
/* 115 */         if (null != starBean1)
/* 116 */           starBean1.getStar_one().forEach(attrBean -> {
/*     */                 if (pid == refreshPid) {
/*     */                   AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true);
/*     */                 } else {
/*     */                   AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false);
/*     */                 } 
/*     */               }); 
/*     */         continue;
/*     */       } 
/* 125 */       PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 126 */       PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 127 */       if (partnerEntity == null) {
/*     */         continue;
/*     */       }
/*     */       
/* 131 */       EquipStarBean.StarBean starBean = EquipUtil.getEquipStarGoalth(equipComponent, partnerEntity.getEquips());
/* 132 */       if (null != starBean)
/* 133 */         starBean.getStar_one().forEach(attrBean -> {
/*     */               if (pid == refreshPid) {
/*     */                 AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true);
/*     */               } else {
/*     */                 AttrUpdateUtil.updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false);
/*     */               } 
/*     */             });  }
/*     */   
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\GrowthGoalAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */