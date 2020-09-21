/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.GroupBossFightSide;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BlocBossBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupInsResetRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupInsResetResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupInsResetProcessor
/*    */   extends ProcessorBase<GroupInsResetRequest, GroupInsResetResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new GroupInsResetResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupInsResetRequest request) {
/* 28 */     GroupEntity groupEntity = GroupUtil.getCurGroup(playerSession.getPlayer());
/* 29 */     if (groupEntity == null) {
/* 30 */       return 11101;
/*    */     }
/* 32 */     if (groupEntity.getReset() == 1) {
/* 33 */       return 11128;
/*    */     }
/* 35 */     if (groupEntity.getLeader() == playerSession.getPlayer().getPlayerId() || groupEntity.getVices().contains(Long.valueOf(playerSession.getPlayer().getPlayerId()))) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 44 */       List<Integer> resetInsList = new ArrayList<>(JsonTableService.getJsonTableKey(BlocBossBean.class));
/* 45 */       groupEntity.setInsId(((Integer)resetInsList.get(0)).intValue());
/* 46 */       GroupBossFightSide groupBossFightSide = new GroupBossFightSide();
/* 47 */       groupBossFightSide.initGroupBoss(groupEntity.getId(), true);
/* 48 */       GroupBossUtil.resetGroupBossFightSide(groupEntity, groupBossFightSide, resetInsList);
/* 49 */       groupBossFightSide.broadcast(0L, playerSession.getPlayer().getPlayerName());
/* 50 */       groupEntity.setReset((byte)1);
/* 51 */       ((GroupInsResetResponse)this.response).insId = groupEntity.getInsId();
/*    */     } else {
/* 53 */       return 11131;
/*    */     } 
/*    */     
/* 56 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupInsResetProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */