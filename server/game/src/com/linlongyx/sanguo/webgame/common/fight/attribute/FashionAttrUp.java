/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.app.fashion.FashionComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.fashion.FashionEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FashionStrengthenBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Arrays;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FashionAttrUp
/*    */   extends AttrUpBase
/*    */ {
/*    */   public FashionAttrUp() {
/* 30 */     super(PlayerAttrUp.AttrUpType.FASHION.getIndex(), false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refresh(IPlayer player, Set<Integer> updates) {
/* 35 */     Arrays.fill(this.attrs, 0L);
/* 36 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 37 */     FashionComponent fashionComponent = (FashionComponent)player.createIfNotExist(FashionComponent.class);
/* 38 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/*    */     
/* 40 */     for (IMapEntity mapEntity : fashionComponent.getEntityMap().values()) {
/* 41 */       FashionEntity fashionEntity = (FashionEntity)mapEntity;
/* 42 */       FashionStrengthenBean fashionStrengthenBean = (FashionStrengthenBean)JsonTableService.getJsonData(fashionEntity.getFashionId(), FashionStrengthenBean.class);
/* 43 */       if (fashionStrengthenBean != null) {
/* 44 */         FashionStrengthenBean.LevelBean levelBean = (FashionStrengthenBean.LevelBean)fashionStrengthenBean.getLevel().get(Integer.valueOf(fashionEntity.getLevel()));
/* 45 */         levelBean.getAttr().forEach(attrBean -> AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), attrBean.getNum(), true));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void refreshPartner(IPlayer player, Set<Integer> updates, long pid) {
/* 54 */     Arrays.fill(this.attrs, 0L);
/* 55 */     if (pid == -1L) {
/*    */       return;
/*    */     }
/* 58 */     FashionComponent fashionComponent = (FashionComponent)player.createIfNotExist(FashionComponent.class);
/* 59 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 60 */     PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 61 */     if (partnerEntity == null) {
/*    */       return;
/*    */     }
/* 64 */     for (IMapEntity mapEntity : fashionComponent.getEntityMap().values()) {
/* 65 */       FashionEntity fashionEntity = (FashionEntity)mapEntity;
/* 66 */       FashionStrengthenBean fashionStrengthenBean = (FashionStrengthenBean)JsonTableService.getJsonData(fashionEntity.getFashionId(), FashionStrengthenBean.class);
/* 67 */       if (fashionStrengthenBean != null) {
/* 68 */         FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/* 69 */         FashionStrengthenBean.LevelBean levelBean = (FashionStrengthenBean.LevelBean)fashionStrengthenBean.getLevel().get(Integer.valueOf(fashionEntity.getLevel()));
/* 70 */         levelBean.getAttr().forEach(attrBean -> AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), attrBean.getNum(), false));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\FashionAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */