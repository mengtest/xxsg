/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterReincarnBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Arrays;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReincarnAttrUp
/*    */   extends AttrUpBase
/*    */ {
/*    */   public ReincarnAttrUp() {
/* 24 */     super(PlayerAttrUp.AttrUpType.REINCARN.getIndex(), false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refresh(IPlayer player, Set<Integer> updates) {
/* 29 */     Arrays.fill(this.attrs, 0L);
/* 30 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 31 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/* 32 */     if (null == fighterBean) {
/*    */       return;
/*    */     }
/* 35 */     for (Iterator<Integer> iterator = playerComponent.getReincarnationIds().iterator(); iterator.hasNext(); ) { int reinId = ((Integer)iterator.next()).intValue();
/* 36 */       FighterReincarnBean fighterReincarnBean = (FighterReincarnBean)JsonTableService.getJsonData(reinId, FighterReincarnBean.class);
/* 37 */       if (null != fighterReincarnBean) {
/* 38 */         for (AttrBean attrBean : fighterReincarnBean.getAttr()) {
/* 39 */           AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), attrBean.getNum(), true);
/*    */         }
/*    */       } }
/*    */   
/*    */   }
/*    */ 
/*    */   
/*    */   public void refreshPartner(IPlayer player, Set<Integer> updates, long pid) {
/* 47 */     if (pid == -1L) {
/*    */       return;
/*    */     }
/* 50 */     Arrays.fill(this.attrs, 0L);
/* 51 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 52 */     PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 53 */     if (null == partnerEntity) {
/*    */       return;
/*    */     }
/* 56 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/* 57 */     if (null == fighterBean) {
/*    */       return;
/*    */     }
/* 60 */     for (Iterator<Integer> iterator = partnerEntity.getReincarnationIds().iterator(); iterator.hasNext(); ) { int reinId = ((Integer)iterator.next()).intValue();
/* 61 */       FighterReincarnBean fighterReincarnBean = (FighterReincarnBean)JsonTableService.getJsonData(reinId, FighterReincarnBean.class);
/* 62 */       if (null != fighterReincarnBean)
/* 63 */         for (AttrBean attrBean : fighterReincarnBean.getAttr())
/* 64 */           AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), attrBean.getNum(), true);   }
/*    */   
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\ReincarnAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */