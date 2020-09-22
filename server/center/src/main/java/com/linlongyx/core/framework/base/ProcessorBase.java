/*    */ package com.linlongyx.core.framework.base;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ProcessorBase<REQ extends RequestBase, RESP extends ResponseBase>
/*    */   implements IProcessor
/*    */ {
/* 14 */   private static final Logger logger = LoggerFactory.getLogger(ProcessorBase.class);
/*    */   
/*    */   protected RESP response;
/*    */   private Entrance entrance;
/*    */   
/*    */   public ProcessorBase() {
/* 20 */     initResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected abstract void initResponse();
/*    */   
/*    */   public void handle(IPlayerSession playerSession, RequestBase request) {
/*    */     try {
/* 28 */       initResponse();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 38 */       process(playerSession, (REQ)request);
/* 39 */     } catch (Exception e) {
/*    */       
/* 41 */       logger.error("消息处理出错，code:" + request.getEventId() + ", msg:" + request.toString());
/* 42 */       logger.error(e.getMessage(), e);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void process(IPlayerSession playerSession, REQ request) throws Exception {
/* 47 */     short retCode = handleRequest(playerSession, request);
/* 48 */     this.response.setRetCode(retCode);
/* 49 */     playerSession.sendMessage((ResponseBase)this.response);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected abstract short handleRequest(IPlayerSession paramIPlayerSession, REQ paramREQ);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected RESP getResponse() {
/* 61 */     return this.response;
/*    */   }
/*    */ 
/*    */   
/*    */   public void handleException(IPlayerSession playerSession, short errorCode) {
/* 66 */     this.response.setRetCode(errorCode);
/* 67 */     playerSession.sendMessage((ResponseBase)this.response);
/*    */   }
/*    */   
/*    */   public void setEntrance(Entrance entrance) {
/* 71 */     this.entrance = entrance;
/*    */   }
/*    */   
/*    */   public boolean isOpen() {
/* 75 */     if (null == this.entrance)
/* 76 */       return true; 
/* 77 */     return this.entrance.isOpenFlag();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\base\ProcessorBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */