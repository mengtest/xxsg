/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.GroupBossFightSide;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GroupParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupInsInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupInsInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupInsInfoProcessor
/*    */   extends ProcessorBase<GroupInsInfoRequest, GroupInsInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new GroupInsInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupInsInfoRequest request) {
/* 31 */     GroupParameter groupParameter = (GroupParameter)ParameterConstant.getParameter(11);
/* 32 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 33 */     GroupMemberEntity memberEntity = (GroupMemberEntity)groupMemberComponent.getEntity();
/* 34 */     GroupEntity groupEntity = GroupUtil.getCurGroup(playerSession.getPlayer());
/*    */ 
/*    */     
/* 37 */     if (memberEntity == null || memberEntity.getId() == 0L || groupEntity == null) {
/* 38 */       return 11101;
/*    */     }
/* 40 */     int curTime = TimeUtil.currentTime();
/* 41 */     int curHour = TimeUtil.getTimestampHour(curTime);
/* 42 */     int lastHour = (groupMemberComponent.getLastResettime() == 0) ? 0 : TimeUtil.getTimestampHour(groupMemberComponent.getLastResettime());
/* 43 */     if (0 <= lastHour && lastHour < 12 && curHour >= 12) {
/* 44 */       groupMemberComponent.setLastResettime(curTime);
/* 45 */       groupMemberComponent.setFightTimes(0);
/*    */     } 
/*    */     
/* 48 */     if (groupEntity.getLevel() < groupParameter.getOpenLevel()) {
/* 49 */       return 11126;
/*    */     }
/* 51 */     GroupBossFightSide groupBossFightSide = GroupBossUtil.getGroupBossFightSide(groupEntity.getId());
/* 52 */     ((GroupInsInfoResponse)this.response).insId = groupEntity.getInsId();
/* 53 */     ((GroupInsInfoResponse)this.response).times = groupParameter.getTimeLimit() - memberEntity.getFightTimes();
/* 54 */     ((GroupInsInfoResponse)this.response).reset = groupEntity.getReset();
/* 55 */     ((GroupInsInfoResponse)this.response).hp = groupBossFightSide.getCurTotalHp();
/* 56 */     ((GroupInsInfoResponse)this.response).maxHp = groupBossFightSide.getMaxHp();
/* 57 */     ((GroupInsInfoResponse)this.response)
/* 58 */       .isLeader = (byte)((groupEntity.getLeader() == playerSession.getPlayer().getPlayerId() || groupEntity.getVices().contains(Long.valueOf(playerSession.getPlayer().getPlayerId()))) ? 1 : 0);
/*    */     
/* 60 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupInsInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */