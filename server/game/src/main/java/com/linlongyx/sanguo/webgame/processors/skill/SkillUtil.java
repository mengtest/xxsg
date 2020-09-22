/*    */ package com.linlongyx.sanguo.webgame.processors.skill;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.skill.SkillComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.skill.SkillEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.skill.SkillNewResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.SkillData;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillUtil
/*    */ {
/*    */   public static boolean openSkill(IPlayer iPlayer, PlayerComponent playerComponent) {
/* 26 */     SkillComponent skillComponent = (SkillComponent)iPlayer.createIfNotExist(SkillComponent.class);
/* 27 */     if (null == skillComponent) return false;
/*    */     
/* 29 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/* 30 */     if (null == fighterBean) {
/* 31 */       return false;
/*    */     }
/* 33 */     SkillEntity skillEntity = skillComponent.getSkillEntity(fighterBean.getSkill());
/* 34 */     if (null != skillEntity) {
/* 35 */       return false;
/*    */     }
/*    */     
/* 38 */     skillComponent.addSkillEntity(playerComponent.getPlayerId(), fighterBean.getSkill());
/* 39 */     skillEntity = skillComponent.getSkillEntity(fighterBean.getSkill());
/* 40 */     skillEntity.setLevel(1);
/* 41 */     sendNewSkill(iPlayer, skillEntity);
/* 42 */     skillComponent.saveAllToDB();
/*    */     
/* 44 */     return true;
/*    */   }
/*    */   
/*    */   public static void sendNewSkill(IPlayer iPlayer, SkillEntity skillEntity) {
/* 48 */     if (null == iPlayer || null == iPlayer.getSession() || null == skillEntity)
/*    */       return; 
/* 50 */     SkillNewResponse skillNewResponse = new SkillNewResponse();
/* 51 */     skillNewResponse.skill = transform(skillEntity);
/* 52 */     iPlayer.getSession().sendMessage((ResponseBase)skillNewResponse);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static SkillData transform(SkillEntity skillEntity) {
/* 60 */     SkillData skillData = new SkillData();
/* 61 */     skillData.skillId = skillEntity.getSkillId();
/* 62 */     skillData.level = (short)skillEntity.getLevel();
/* 63 */     return skillData;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isSkillActivated(IPlayerSession playerSession, int skillId) {
/* 74 */     SkillComponent skillComponent = (SkillComponent)playerSession.getPlayer().createIfNotExist(SkillComponent.class);
/* 75 */     SkillEntity skillEntity = skillComponent.getSkillEntity(skillId);
/* 76 */     return (null != skillEntity);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\skill\SkillUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */