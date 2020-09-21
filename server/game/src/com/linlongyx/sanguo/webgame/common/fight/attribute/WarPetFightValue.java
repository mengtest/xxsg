/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Arrays;
/*    */ import java.util.HashSet;
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
/*    */ 
/*    */ 
/*    */ public class WarPetFightValue
/*    */   extends AttrUpBase
/*    */ {
/*    */   public WarPetFightValue(int type, boolean isBaseAffect) {
/* 30 */     super(type, isBaseAffect);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long getWarPetFightValue(WarPetEntity warPetEntity, IPlayerSession iPlayerSession) {
/* 38 */     Arrays.fill(this.attrs, 0L);
/* 39 */     PlayerComponent playerComponent = (PlayerComponent)iPlayerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 40 */     FighterBean fighterBean1 = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/* 41 */     AttrUpdateUtil.oneWarPetAttr(fighterBean1, warPetEntity, this.attrs, new HashSet<>(), -1L);
/* 42 */     PartnerComponent partnerComponent = (PartnerComponent)iPlayerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/* 43 */     partnerComponent.getEntityMap().values().forEach(iMapEntity -> {
/*    */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*    */           FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/*    */           if (null != fighterBean) {
/*    */             AttrUpdateUtil.oneWarPetAttr(fighterBean, warPetEntity, this.attrs, new HashSet<>(), partnerEntity.getPid());
/*    */           }
/*    */         });
/* 50 */     return getFightValue() / 10000L;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\WarPetFightValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */