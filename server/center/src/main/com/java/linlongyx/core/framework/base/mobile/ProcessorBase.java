/*    */ package linlongyx.core.framework.base.mobile;
/*    */ 
/*    */ import linlongyx.core.framework.base.Entrance;
/*    */ import linlongyx.core.framework.logic.IPlayerSession;
/*    */ import linlongyx.core.framework.protocol.mobile.RequestBase;
/*    */ import linlongyx.core.framework.protocol.mobile.ResponseBase;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ProcessorBase<REQ extends RequestBase, RESP extends ResponseBase>
/*    */   implements IProcessor
/*    */ {
/* 15 */   private static final Logger logger = LoggerFactory.getLogger(ProcessorBase.class);
/*    */   
/*    */   protected RESP response;
/*    */   private Entrance entrance;
/*    */   protected boolean notNeedPlayer;
/*    */   
/*    */   public ProcessorBase() {
/* 22 */     initResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected abstract void initResponse();
/*    */   
/*    */   public void handle(IPlayerSession playerSession, RequestBase request) {
/*    */     try {
/* 30 */       if (this.response != null) {
/* 31 */         this.response.setRetCode((short)0);
/* 32 */         this.response.reset();
/*    */       } 
/* 34 */       process(playerSession, (REQ)request);
/* 35 */       request.reset();
/* 36 */     } catch (Exception e) {
/* 37 */       logger.error("消息处理出错，msg code:" + request.getEventId(), e);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void process(IPlayerSession playerSession, REQ request) throws Exception {
/* 42 */     if (this.response != null) this.response.setCorCode(request.getCorCode()); 
/* 43 */     short retCode = handleRequest(playerSession, request);
/* 44 */     if (retCode < 0 || this.response == null)
/* 45 */       return;  this.response.setRetCode(retCode);
/* 46 */     playerSession.sendMessage((ResponseBase)this.response);
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
/* 58 */     return this.response;
/*    */   }
/*    */ 
/*    */   
/*    */   public void handleException(IPlayerSession playerSession, short errorCode) {
/* 63 */     if (this.response == null)
/* 64 */       return;  this.response.setRetCode(errorCode);
/* 65 */     playerSession.sendMessage((ResponseBase)this.response);
/*    */   }
/*    */   
/*    */   public void setEntrance(Entrance entrance) {
/* 69 */     this.entrance = entrance;
/*    */   }
/*    */   
/*    */   public boolean isOpen() {
/* 73 */     if (null == this.entrance)
/* 74 */       return true; 
/* 75 */     return this.entrance.isOpenFlag();
/*    */   }
/*    */   
/*    */   public boolean isNeedPlayer() {
/* 79 */     return !this.notNeedPlayer;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\base\mobile\ProcessorBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */