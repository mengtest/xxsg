/*    */ package com.linlongyx.sanguo.webgame.processors.runewar;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.runewar.RunewarRankInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.runewar.RunewarRankInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RunewarRankInfoProcessor
/*    */   extends ProcessorBase<RunewarRankInfoRequest, RunewarRankInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new RunewarRankInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RunewarRankInfoRequest request) {
/* 27 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 45))
/* 28 */       return 10061; 
/* 29 */     ((RunewarRankInfoResponse)this.response).rankList.addAll(CrossUtil.getRunewarRankList("RunewarRankInfoProcessor"));
/* 30 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\runewar\RunewarRankInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */