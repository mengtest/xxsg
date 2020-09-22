/*     */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ExpLeaderBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBreakBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecordStarBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RelationBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.SoulBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerUtil;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class LeaderAttrUp extends AttrUpBase {
/*     */   public LeaderAttrUp() {
/*  27 */     super(PlayerAttrUp.AttrUpType.LEADER.getIndex(), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void refresh(IPlayer player, Set<Integer> updates) {
/*  32 */     Arrays.fill(this.attrs, 0L);
/*  33 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  34 */     ExpLeaderBean expLeaderBean = (ExpLeaderBean)JsonTableService.getJsonData(playerComponent.getLevel(), ExpLeaderBean.class);
/*  35 */     if (expLeaderBean == null) {
/*     */       return;
/*     */     }
/*  38 */     int leaderId = playerComponent.getLeaderId();
/*  39 */     if (0 == leaderId) {
/*  40 */       LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*  41 */       leaderId = loginParameter.getLeaderBySex(playerComponent.getSex());
/*  42 */       playerComponent.setLeaderId(leaderId);
/*     */     } 
/*     */     
/*  45 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(leaderId, FighterBean.class);
/*  46 */     if (null != fighterBean) {
/*  47 */       fighterBean.getAttr().forEach(attrBean -> AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), attrBean.getNum(), true));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  56 */     FighterBreakBean fighterBreakBean = (FighterBreakBean)JsonTableService.getJsonData(leaderId, FighterBreakBean.class);
/*  57 */     for (AttrBean attrBean : ((FighterBreakBean.LevelBean)fighterBreakBean.getLevel().get(Integer.valueOf(playerComponent.getBreakthroughs()))).getAttr()) {
/*  58 */       AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), attrBean.getNum(), true);
/*     */     }
/*  60 */     for (int i = 0; i <= playerComponent.getBreakthroughs(); i++) {
/*  61 */       for (FighterBreakBean.LevelBean.TalentBean talentBean : ((FighterBreakBean.LevelBean)fighterBreakBean.getLevel().get(Integer.valueOf(i))).getTalent()) {
/*  62 */         AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, talentBean.getId(), talentBean.getNum(), true);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  67 */     SoulBean soulBean = (SoulBean)JsonTableService.getJsonData(playerComponent.getSoulLevel(), SoulBean.class);
/*  68 */     for (SoulBean.AttrBean attrBean : soulBean.getAttr()) {
/*  69 */       AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), attrBean.getNum(), true);
/*     */     }
/*     */     
/*  72 */     SanGuoZhiComponent sanGuoZhiComponent = (SanGuoZhiComponent)player.createIfNotExist(SanGuoZhiComponent.class);
/*  73 */     sanGuoZhiComponent.getEntityMap().values().forEach(iMapEntity -> {
/*     */           SanGuoZhiEntity sanGuoZhiEntity2 = (SanGuoZhiEntity)iMapEntity;
/*     */           
/*     */           if (sanGuoZhiEntity2.isActivity()) {
/*     */             RecordStarBean recordStarBean = (RecordStarBean)JsonTableService.getJsonData(sanGuoZhiEntity2.getRecordStarId(), RecordStarBean.class);
/*     */             
/*     */             for (RecordStarBean.AttrBean attrBean : recordStarBean.getAttr()) {
/*     */               AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), attrBean.getNum(), true);
/*     */             }
/*     */           } 
/*     */         });
/*  84 */     EquipComponent equipComponent = (EquipComponent)player.createIfNotExist(EquipComponent.class);
/*  85 */     ArrayList<Integer> equipList = new ArrayList<>();
/*  86 */     playerComponent.getEquips().values().forEach(equipId -> {
/*     */           if (equipId.longValue() != 0L) {
/*     */             EquipEntity equipEntity = equipComponent.getEquipEntity(equipId.longValue());
/*     */             if (null != equipEntity) {
/*     */               equipList.add(Integer.valueOf(equipEntity.getItemId()));
/*     */             }
/*     */           } 
/*     */         });
/*  94 */     int itemId = 0;
/*  95 */     if (playerComponent.getTalisman() != 0L) {
/*  96 */       EquipEntity equipEntity = equipComponent.getEquipEntity(playerComponent.getTalisman());
/*  97 */       if (null != equipEntity) {
/*  98 */         itemId = equipEntity.getItemId();
/*     */       }
/*     */     } 
/* 101 */     ArrayList<Integer> relationList = PartnerUtil.getLearderRelation(playerComponent.getLeaderId(), equipList, fighterBean, player, itemId);
/* 102 */     for (Integer relation : relationList) {
/* 103 */       RelationBean relationBean = (RelationBean)JsonTableService.getJsonData(relation.intValue(), RelationBean.class);
/* 104 */       relationBean.getAttr().forEach(attrBean -> AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), attrBean.getNum(), true));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\LeaderAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */