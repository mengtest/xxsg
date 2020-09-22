/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.app.artifact.ArtifactComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.artifact.ArtifactEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ArtifactBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Arrays;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArtifactAttrUp
/*    */   extends AttrUpBase
/*    */ {
/*    */   public ArtifactAttrUp() {
/* 27 */     super(PlayerAttrUp.AttrUpType.ARTIFACT.getIndex(), false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refresh(IPlayer player, Set<Integer> updates) {
/* 32 */     Arrays.fill(this.attrs, 0L);
/* 33 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 34 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/* 35 */     if (null == fighterBean) {
/*    */       return;
/*    */     }
/* 38 */     ArtifactComponent artifactComponent = (ArtifactComponent)player.createIfNotExist(ArtifactComponent.class);
/* 39 */     Map<Integer, Object> map = JsonTableService.getJsonTable(ArtifactBean.class);
/* 40 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 41 */       ArtifactEntity artifactEntity = artifactComponent.getArtifactEntity(((Integer)entry.getKey()).intValue());
/* 42 */       if (null != artifactEntity) {
/* 43 */         ArtifactBean artifactBean = (ArtifactBean)entry.getValue();
/* 44 */         Map<Integer, Integer> artifactEntityAttrs = artifactEntity.getAttrs();
/* 45 */         for (AttrBean attrBean : artifactBean.getMaxAttr()) {
/* 46 */           AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), ((Integer)artifactEntityAttrs.getOrDefault(Integer.valueOf(attrBean.getId()), Integer.valueOf(0))).intValue(), true);
/*    */         }
/* 48 */         for (AttrBean attrBean : artifactBean.getSensitizeAttr()) {
/* 49 */           AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), attrBean.getNum(), true);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void refreshPartner(IPlayer player, Set<Integer> updates, long pid) {
/* 57 */     if (pid == -1L) {
/*    */       return;
/*    */     }
/*    */     
/* 61 */     Arrays.fill(this.attrs, 0L);
/* 62 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 63 */     PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 64 */     if (partnerEntity == null) {
/*    */       return;
/*    */     }
/* 67 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/* 68 */     if (null == fighterBean) {
/*    */       return;
/*    */     }
/* 71 */     ArtifactComponent artifactComponent = (ArtifactComponent)player.createIfNotExist(ArtifactComponent.class);
/* 72 */     Map<Integer, Object> map = JsonTableService.getJsonTable(ArtifactBean.class);
/* 73 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 74 */       ArtifactEntity artifactEntity = artifactComponent.getArtifactEntity(((Integer)entry.getKey()).intValue());
/* 75 */       if (null != artifactEntity) {
/* 76 */         ArtifactBean artifactBean = (ArtifactBean)entry.getValue();
/* 77 */         Map<Integer, Integer> artifactEntityAttrs = artifactEntity.getAttrs();
/* 78 */         for (AttrBean attrBean : artifactBean.getMaxAttr()) {
/* 79 */           AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), ((Integer)artifactEntityAttrs.getOrDefault(Integer.valueOf(attrBean.getId()), Integer.valueOf(0))).intValue(), true);
/*    */         }
/* 81 */         for (AttrBean attrBean : artifactBean.getSensitizeAttr())
/* 82 */           AttrUpdateUtil.updateAttr(fighterBean, this.attrs, updates, attrBean.getId(), attrBean.getNum(), true); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\ArtifactAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */