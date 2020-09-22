/*    */ package com.linlongyx.sanguo.webgame.processors.warpet;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.PetBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.WarPetStarUpRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.WarPetStarUpResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WarPetStarUpProcessor
/*    */   extends ProcessorBase<WarPetStarUpRequest, WarPetStarUpResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 29 */     this.response = (ResponseBase)new WarPetStarUpResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WarPetStarUpRequest request) {
/* 35 */     WarPetComponent warPetComponent = (WarPetComponent)playerSession.getPlayer().createIfNotExist(WarPetComponent.class);
/* 36 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 37 */     PetBean petBean = (PetBean)JsonTableService.getJsonData(request.warPet, PetBean.class);
/* 38 */     if (null == petBean) {
/* 39 */       return 10006;
/*    */     }
/* 41 */     WarPetEntity warPetEntity = warPetComponent.getEntity(request.warPet);
/* 42 */     if (warPetEntity == null) {
/* 43 */       return 16003;
/*    */     }
/* 45 */     Map<Integer, PetBean.StarBean> starBeanMap = petBean.getStar();
/* 46 */     ArrayList<Reward> rewards = null;
/* 47 */     if (((PetBean.StarBean)starBeanMap.get(Integer.valueOf(warPetEntity.getStar()))).getCard().isEmpty()) {
/* 48 */       return 16005;
/*    */     }
/* 50 */     rewards = FinanceUtil.transformWarPet(((PetBean.StarBean)starBeanMap.get(Integer.valueOf(warPetEntity.getStar()))).getCard());
/* 51 */     short resCode = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/* 52 */     if (resCode != 0) {
/* 53 */       return resCode;
/*    */     }
/* 55 */     CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.WarPetStarUp);
/* 56 */     warPetEntity.setStar(warPetEntity.getStar() + 1);
/* 57 */     ((WarPetStarUpResponse)this.response).star = warPetEntity.getStar();
/* 58 */     ((WarPetStarUpResponse)this.response).warPet = request.warPet;
/* 59 */     AttrUpdateUtil.refreshWarPet(playerSession);
/* 60 */     WarPetUtil.updateWarPetFightValue(warPetEntity, playerSession, warPetComponent, false);
/* 61 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\warpet\WarPetStarUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */