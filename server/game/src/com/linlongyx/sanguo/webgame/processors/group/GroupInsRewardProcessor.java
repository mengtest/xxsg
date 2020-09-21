/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupInsRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupInsRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupInsRewardProcessor
/*    */   extends ProcessorBase<GroupInsRewardRequest, GroupInsRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new GroupInsRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupInsRewardRequest request) {
/* 27 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 28 */     GroupMemberEntity memberEntity = (GroupMemberEntity)groupMemberComponent.getEntity();
/*    */     
/* 30 */     if (memberEntity == null || memberEntity.getId() == 0L) {
/* 31 */       return 11101;
/*    */     }
/* 33 */     for (Map.Entry<Integer, Integer> kv : (Iterable<Map.Entry<Integer, Integer>>)memberEntity.getPlayerRewards().entrySet()) {
/* 34 */       IntIntValue intIntValue = new IntIntValue();
/* 35 */       intIntValue.key = ((Integer)kv.getKey()).intValue();
/* 36 */       intIntValue.value = ((Integer)kv.getValue()).intValue();
/* 37 */       ((GroupInsRewardResponse)this.response).rewards.add(intIntValue);
/*    */     } 
/* 39 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupInsRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */