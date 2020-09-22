/*    */ package com.linlongyx.sanguo.webgame.processors.warpet;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.PetBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.ActivityWarPetRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.ActivityWarPetResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ActivityWarPetProcessor
/*    */   extends ProcessorBase<ActivityWarPetRequest, ActivityWarPetResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 30 */     this.response = (ResponseBase)new ActivityWarPetResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ActivityWarPetRequest request) {
/* 35 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 30))
/* 36 */       return 10061; 
/* 37 */     WarPetComponent warPetComponent = (WarPetComponent)playerSession.getPlayer().createIfNotExist(WarPetComponent.class);
/* 38 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 39 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 40 */     PetBean petBean = (PetBean)JsonTableService.getJsonData(request.warpet, PetBean.class);
/* 41 */     if (null == petBean) {
/* 42 */       return 10006;
/*    */     }
/* 44 */     WarPetEntity warPetEntity = warPetComponent.getEntity(request.warpet);
/* 45 */     if (warPetEntity != null) {
/* 46 */       return 16001;
/*    */     }
/* 48 */     Map<Integer, PetBean.StarBean> starBeanMap = petBean.getStar();
/* 49 */     ArrayList<Reward> rewards = null;
/* 50 */     if (starBeanMap.get(Integer.valueOf(-1)) == null) {
/* 51 */       return 10006;
/*    */     }
/* 53 */     rewards = FinanceUtil.transformWarPet(((PetBean.StarBean)starBeanMap.get(Integer.valueOf(-1))).getCard());
/* 54 */     short resCode = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/* 55 */     if (resCode != 0) {
/* 56 */       return resCode;
/*    */     }
/* 58 */     CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.ActWarPet);
/* 59 */     warPetComponent.addWarPet(request.warpet);
/* 60 */     warPetEntity = warPetComponent.getEntity(request.warpet);
/* 61 */     ((ActivityWarPetResponse)this.response).data.warPet = request.warpet;
/* 62 */     ((ActivityWarPetResponse)this.response).data.star = warPetEntity.getStar();
/* 63 */     ((ActivityWarPetResponse)this.response).data.level = warPetEntity.getLevel();
/* 64 */     ((ActivityWarPetResponse)this.response).data.battle = warPetEntity.getBattle();
/* 65 */     WarPetUtil.updateWarPetFightValue(warPetEntity, playerSession, warPetComponent, true);
/* 66 */     ((ActivityWarPetResponse)this.response).data.fightValue = warPetEntity.getFightValue();
/* 67 */     AttrUpdateUtil.refreshWarPet(playerSession);
/* 68 */     taskComponent.refreshSchedule(TaskType.ActivityWarPet, 0, 0L);
/*    */     
/* 70 */     PlayerUtil.sendNotice(3, playerSession.getPlayer(), warPetEntity.getWarPetId(), null);
/*    */     
/* 72 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\warpet\ActivityWarPetProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */