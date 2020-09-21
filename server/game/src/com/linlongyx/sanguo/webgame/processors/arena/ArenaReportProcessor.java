/*    */ package com.linlongyx.sanguo.webgame.processors.arena;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.arena.ArenaComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.arena.ArenaReportRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.arena.ArenaReportResponse;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArenaReportProcessor
/*    */   extends ProcessorBase<ArenaReportRequest, ArenaReportResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new ArenaReportResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ArenaReportRequest request) {
/* 24 */     ArenaComponent arenaComponent = (ArenaComponent)playerSession.getPlayer().createIfNotExist(ArenaComponent.class);
/* 25 */     ((ArenaReportResponse)this.response).arenaReports = new ArrayList(arenaComponent.getReports());
/* 26 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\arena\ArenaReportProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */