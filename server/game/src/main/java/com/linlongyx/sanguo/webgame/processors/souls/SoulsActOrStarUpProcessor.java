/*    */ package com.linlongyx.sanguo.webgame.processors.souls;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.souls.SoulsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.souls.SoulsEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SoulsStarBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.souls.SoulsActOrStarUpRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.souls.SoulsActOrStarUpResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SoulsActOrStarUpProcessor
/*    */   extends ProcessorBase<SoulsActOrStarUpRequest, SoulsActOrStarUpResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 31 */     this.response = (ResponseBase)new SoulsActOrStarUpResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SoulsActOrStarUpRequest request) {
/* 36 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 55))
/* 37 */       return 10061; 
/* 38 */     SoulsComponent soulsComponent = (SoulsComponent)playerSession.getPlayer().createIfNotExist(SoulsComponent.class);
/* 39 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 40 */     SoulsStarBean soulsStarBean = (SoulsStarBean)JsonTableService.getJsonData(request.soulId, SoulsStarBean.class);
/* 41 */     if (soulsStarBean == null) {
/* 42 */       return 10006;
/*    */     }
/* 44 */     if (request.type == 0) {
/* 45 */       SoulsEntity soulsEntity = soulsComponent.getEntity(request.soulId);
/* 46 */       if (soulsEntity != null) {
/* 47 */         return 15005;
/*    */       }
/* 49 */       Map<Integer, SoulsStarBean.StarBean> starBeanMap = soulsStarBean.getStar();
/* 50 */       ArrayList<Reward> rewards = null;
/* 51 */       if (starBeanMap.get(Integer.valueOf(-1)) == null) {
/* 52 */         return 10006;
/*    */       }
/* 54 */       rewards = FinanceUtil.transform(((SoulsStarBean.StarBean)starBeanMap.get(Integer.valueOf(-1))).getCard());
/* 55 */       short resCode = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/* 56 */       if (resCode != 0) {
/* 57 */         return resCode;
/*    */       }
/* 59 */       CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.ActivityStage);
/* 60 */       soulsComponent.addSouls(request.soulId);
/* 61 */       soulsEntity = soulsComponent.getEntity(request.soulId);
/* 62 */       ((SoulsActOrStarUpResponse)this.response).data.soulsId = request.soulId;
/* 63 */       ((SoulsActOrStarUpResponse)this.response).data.star = soulsEntity.getStar();
/* 64 */       ((SoulsActOrStarUpResponse)this.response).data.level = soulsEntity.getLevel();
/* 65 */       ((SoulsActOrStarUpResponse)this.response).data.exp = soulsEntity.getExp();
/* 66 */       ((SoulsActOrStarUpResponse)this.response).data.fightValue = soulsEntity.getFightValue();
/* 67 */     } else if (request.type == 1) {
/* 68 */       SoulsEntity soulsEntity = soulsComponent.getEntity(request.soulId);
/* 69 */       if (soulsEntity == null) {
/* 70 */         return 16009;
/*    */       }
/* 72 */       Map<Integer, SoulsStarBean.StarBean> starBeanMap = soulsStarBean.getStar();
/* 73 */       ArrayList<Reward> rewards = null;
/* 74 */       if (starBeanMap.get(Integer.valueOf(soulsEntity.getStar())) == null) {
/* 75 */         return 10006;
/*    */       }
/* 77 */       if (((SoulsStarBean.StarBean)starBeanMap.get(Integer.valueOf(soulsEntity.getStar()))).getCard().isEmpty()) {
/* 78 */         return 16005;
/*    */       }
/* 80 */       rewards = FinanceUtil.transform(((SoulsStarBean.StarBean)starBeanMap.get(Integer.valueOf(soulsEntity.getStar()))).getCard());
/* 81 */       short resCode = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/* 82 */       if (resCode != 0) {
/* 83 */         return resCode;
/*    */       }
/* 85 */       CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.KungFuStarUp);
/* 86 */       soulsEntity.setStar(soulsEntity.getStar() + 1);
/* 87 */       soulsComponent.updateStarDB(soulsEntity.getId());
/* 88 */       ((SoulsActOrStarUpResponse)this.response).data.soulsId = request.soulId;
/* 89 */       ((SoulsActOrStarUpResponse)this.response).data.star = soulsEntity.getStar();
/* 90 */       ((SoulsActOrStarUpResponse)this.response).data.level = soulsEntity.getLevel();
/* 91 */       ((SoulsActOrStarUpResponse)this.response).data.exp = soulsEntity.getExp();
/* 92 */       ((SoulsActOrStarUpResponse)this.response).data.fightValue = soulsEntity.getFightValue();
/*    */     } 
/*    */     
/* 95 */     ArrayList<Integer> handBookList = SoulsUtil.getSoulsHandBook(soulsComponent);
/* 96 */     ((SoulsActOrStarUpResponse)this.response).combinationList = new ArrayList<>(handBookList);
/* 97 */     AttrUpdateUtil.refreshSouls(playerSession);
/* 98 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\souls\SoulsActOrStarUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */