/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupDamageListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupDamageListResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GroupDamageData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupDamageListProcessor
/*    */   extends ProcessorBase<GroupDamageListRequest, GroupDamageListResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new GroupDamageListResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupDamageListRequest request) {
/* 25 */     GroupEntity groupEntity = GroupUtil.getCurGroup(playerSession.getPlayer());
/* 26 */     if (groupEntity == null) {
/* 27 */       return 11101;
/*    */     }
/* 29 */     ((GroupDamageListResponse)this.response).datas.addAll(groupEntity.getDamageRecords().values());
/* 30 */     ((GroupDamageListResponse)this.response).datas.sort((o1, o2) -> (o1.maxDamage == o2.maxDamage) ? 0 : ((o1.maxDamage > o2.maxDamage) ? -1 : 1));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 39 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupDamageListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */