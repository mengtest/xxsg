/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BlocBossBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupInsRewardGetRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupInsRewardGetResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupInsRewardGetProcessor
/*    */   extends ProcessorBase<GroupInsRewardGetRequest, GroupInsRewardGetResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new GroupInsRewardGetResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupInsRewardGetRequest request) {
/* 27 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 28 */     if (!groupMemberComponent.getPlayerRewards().containsKey(Integer.valueOf(request.insId))) {
/* 29 */       return 10095;
/*    */     }
/* 31 */     if (((Integer)groupMemberComponent.getPlayerRewards().get(Integer.valueOf(request.insId))).intValue() != 1) {
/* 32 */       return 10091;
/*    */     }
/* 34 */     groupMemberComponent.getPlayerRewards().put(Integer.valueOf(request.insId), Integer.valueOf(2));
/* 35 */     BlocBossBean blocBossBean = (BlocBossBean)JsonTableService.getJsonData(request.insId, BlocBossBean.class);
/* 36 */     FinanceUtil.reward(FinanceUtil.transform(blocBossBean.getReward()), playerSession.getPlayer(), ResourceEvent.GroupBossFight, true);
/*    */     
/* 38 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupInsRewardGetProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */