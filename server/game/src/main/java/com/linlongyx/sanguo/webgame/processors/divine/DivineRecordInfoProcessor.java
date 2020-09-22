/*    */ package com.linlongyx.sanguo.webgame.processors.divine;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.divine.DivineComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.divine.DivineEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.divine.DivineRecordInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.divine.DivineRecordInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LongStringValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DivineRecordInfoProcessor
/*    */   extends ProcessorBase<DivineRecordInfoRequest, DivineRecordInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new DivineRecordInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DivineRecordInfoRequest request) {
/* 25 */     int curActId = DivineUtil.getCurDivineActId();
/* 26 */     if (curActId == -1) {
/* 27 */       return 14301;
/*    */     }
/* 29 */     DivineComponent divineComponent = (DivineComponent)playerSession.getPlayer().createIfNotExist(DivineComponent.class);
/* 30 */     DivineEntity divineEntity = divineComponent.getEntity(curActId);
/* 31 */     for (IntIntValue kv : divineEntity.getRecordList()) {
/* 32 */       LongStringValue lsv = new LongStringValue();
/* 33 */       lsv.key = kv.value;
/* 34 */       lsv.value = DivineUtil.fmtDivineNum(kv.key);
/* 35 */       ((DivineRecordInfoResponse)this.response).recordList.add(lsv);
/*    */     } 
/* 37 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\divine\DivineRecordInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */