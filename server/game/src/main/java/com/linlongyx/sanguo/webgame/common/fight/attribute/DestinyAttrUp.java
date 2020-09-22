/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterDestinyBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Arrays;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyAttrUp
/*    */   extends AttrUpBase
/*    */ {
/*    */   public DestinyAttrUp() {
/* 22 */     super(PlayerAttrUp.AttrUpType.DESTINY.getIndex(), false);
/*    */   }
/*    */   
/*    */   public void refresh(IPlayer player, Set<Integer> updates) {
/* 26 */     Arrays.fill(this.attrs, 0L);
/* 27 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 28 */     FighterDestinyBean fighterDestinyBean = (FighterDestinyBean)JsonTableService.getJsonData(playerComponent.getDesLv(), FighterDestinyBean.class);
/* 29 */     if (null != fighterDestinyBean) {
/* 30 */       FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/* 31 */       if (null == fighterBean) {
/*    */         return;
/*    */       }
/* 34 */       fighterDestinyBean.getAttr().forEach(attrBean -> AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), attrBean.getNum(), true));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void refreshPartner(IPlayer player, Set<Integer> updates, long pid) {
/* 42 */     Arrays.fill(this.attrs, 0L);
/* 43 */     if (pid == -1L) {
/*    */       return;
/*    */     }
/*    */     
/* 47 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 48 */     PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 49 */     if (partnerEntity == null) {
/*    */       return;
/*    */     }
/* 52 */     FighterDestinyBean fighterDestinyBean = (FighterDestinyBean)JsonTableService.getJsonData(partnerEntity.getDesLv(), FighterDestinyBean.class);
/* 53 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/* 54 */     if (null != fighterDestinyBean && fighterBean != null)
/* 55 */       fighterDestinyBean.getAttr().forEach(attrBean -> AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), attrBean.getNum(), true)); 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\DestinyAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */