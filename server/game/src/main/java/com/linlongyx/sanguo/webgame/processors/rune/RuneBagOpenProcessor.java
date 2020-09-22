/*    */ package com.linlongyx.sanguo.webgame.processors.rune;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagEntity;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rune.RuneBagOpenRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rune.RuneBagOpenResponse;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RuneBagOpenProcessor
/*    */   extends ProcessorBase<RuneBagOpenRequest, RuneBagOpenResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new RuneBagOpenResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RuneBagOpenRequest request) {
/* 29 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 69)) {
/* 30 */       return 0;
/*    */     }
/*    */     
/* 33 */     RuneBagComponent runeBagComponent = (RuneBagComponent)playerSession.getPlayer().createIfNotExist(RuneBagComponent.class);
/* 34 */     for (Map.Entry<String, IMapEntity> entry : (Iterable<Map.Entry<String, IMapEntity>>)runeBagComponent.getEntityMap().entrySet()) {
/* 35 */       RuneBagEntity runeBagEntity = (RuneBagEntity)entry.getValue();
/* 36 */       ((RuneBagOpenResponse)this.response).infos.add(RuneUtil.tranform(runeBagEntity));
/*    */     } 
/* 38 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rune\RuneBagOpenProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */