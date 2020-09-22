/*    */ package com.linlongyx.sanguo.webgame.processors.task;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FindRewardData;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.FindRewardInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.task.FindRewardInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FindRewardInfoProcessor
/*    */   extends ProcessorBase<FindRewardInfoRequest, FindRewardInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 17 */     this.response = (ResponseBase)new FindRewardInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FindRewardInfoRequest request) {
/* 22 */     for (FindRewardType findRewardType : FindRewardType.values()) {
/* 23 */       FindRewardData findRewardData = TaskUtil.getFindRewardData(findRewardType, playerSession, true);
/* 24 */       if (null != findRewardData) {
/* 25 */         ((FindRewardInfoResponse)this.response).list.add(findRewardData);
/*    */       }
/*    */     } 
/* 28 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\FindRewardInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */