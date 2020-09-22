/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*    */ import java.util.Arrays;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PartnerAttrUp
/*    */   extends AttrUpBase
/*    */ {
/*    */   public PartnerAttrUp() {
/* 23 */     super(PlayerAttrUp.AttrUpType.PARTNER.getIndex(), false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refresh(IPlayer player, Set<Integer> updates) {
/* 28 */     Arrays.fill(this.attrs, 0L);
/* 29 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 30 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 31 */     GeneralComponent generalComponent = (GeneralComponent)player.createIfNotExist(GeneralComponent.class);
/* 32 */     for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/* 33 */       PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/* 34 */       if (playerComponent.getFighters().indexOf(Long.valueOf(partnerEntity.getPid())) < 0 && generalComponent.getAssistInBattle().indexOf(Long.valueOf(partnerEntity.getPid())) < 0) {
/*    */         continue;
/*    */       }
/* 37 */       AttrUpdateUtil.partnerAttribute(player, this.attrs, updates, partnerEntity.getPid(), true);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void refreshPartner(IPlayer player, Set<Integer> updates, long pid) {
/* 44 */     if (pid == -1L) {
/*    */       return;
/*    */     }
/*    */     
/* 48 */     Arrays.fill(this.attrs, 0L);
/* 49 */     AttrUpdateUtil.partnerAttribute(player, this.attrs, updates, pid, false);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\PartnerAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */