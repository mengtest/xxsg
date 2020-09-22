/*    */ package com.linlongyx.sanguo.webgame.processors.bagua;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bagua.BaguaRecordInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bagua.BaguaRecordInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BaguaRecord;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BaguaRecordInfoProcessor
/*    */   extends ProcessorBase<BaguaRecordInfoRequest, BaguaRecordInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new BaguaRecordInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BaguaRecordInfoRequest request) {
/* 24 */     Map<Integer, Map<Integer, BaguaRecord>> recordMap = BaguaUtil.getRecordMap();
/* 25 */     if (recordMap.containsKey(Integer.valueOf(request.chapterId))) {
/* 26 */       ((BaguaRecordInfoResponse)this.response).chapterRecordList.addAll(((Map)recordMap.get(Integer.valueOf(request.chapterId))).values());
/*    */     }
/* 28 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bagua\BaguaRecordInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */