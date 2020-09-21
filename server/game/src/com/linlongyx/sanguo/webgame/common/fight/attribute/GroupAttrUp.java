/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BlocSkillBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*    */ import com.linlongyx.sanguo.webgame.processors.group.GroupUtil;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Arrays;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupAttrUp
/*    */   extends AttrUpBase
/*    */ {
/*    */   public GroupAttrUp() {
/* 25 */     super(PlayerAttrUp.AttrUpType.GROUP.getIndex(), false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refresh(IPlayer player, Set<Integer> updates) {
/* 30 */     Arrays.fill(this.attrs, 0L);
/* 31 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)player.createIfNotExist(GroupMemberComponent.class);
/* 32 */     if (0L == groupMemberComponent.getId()) {
/*    */       return;
/*    */     }
/*    */     
/* 36 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 37 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/* 38 */     if (null == fighterBean) {
/*    */       return;
/*    */     }
/* 41 */     Map<Integer, Integer> skills = groupMemberComponent.getSkills();
/* 42 */     for (Map.Entry<Integer, Integer> entry : skills.entrySet()) {
/* 43 */       BlocSkillBean blocSkillBean = GroupUtil.getBlocSkillBean(((Integer)entry.getKey()).intValue(), ((Integer)entry.getValue()).intValue());
/* 44 */       if (null == blocSkillBean) {
/*    */         continue;
/*    */       }
/* 47 */       blocSkillBean.getAttr().forEach(attrBean -> AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), attrBean.getNum(), true));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void refreshPartner(IPlayer player, Set<Integer> updates, long pid) {
/* 54 */     if (pid == -1L) {
/*    */       return;
/*    */     }
/*    */     
/* 58 */     Arrays.fill(this.attrs, 0L);
/* 59 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)player.createIfNotExist(GroupMemberComponent.class);
/* 60 */     if (0L == groupMemberComponent.getId()) {
/*    */       return;
/*    */     }
/*    */     
/* 64 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 65 */     PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 66 */     if (partnerEntity == null) {
/*    */       return;
/*    */     }
/* 69 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/* 70 */     if (null == fighterBean) {
/*    */       return;
/*    */     }
/* 73 */     Map<Integer, Integer> skills = groupMemberComponent.getSkills();
/* 74 */     for (Map.Entry<Integer, Integer> entry : skills.entrySet()) {
/* 75 */       BlocSkillBean blocSkillBean = GroupUtil.getBlocSkillBean(((Integer)entry.getKey()).intValue(), ((Integer)entry.getValue()).intValue());
/* 76 */       if (null == blocSkillBean) {
/*    */         continue;
/*    */       }
/* 79 */       blocSkillBean.getAttr().forEach(attrBean -> AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), attrBean.getNum(), false));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\GroupAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */