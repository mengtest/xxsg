/*    */ package com.linlongyx.sanguo.webgame.processors.title;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TitleBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.title.TitleWearRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.title.TitleWearResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TitleWearProcessor
/*    */   extends ProcessorBase<TitleWearRequest, TitleWearResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new TitleWearResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TitleWearRequest request) {
/* 26 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 38)) {
/* 27 */       return 10061;
/*    */     }
/* 29 */     TitleBean titleBean = (TitleBean)JsonTableService.getJsonData(request.title, TitleBean.class);
/* 30 */     if (null == titleBean) {
/* 31 */       return 13801;
/*    */     }
/* 33 */     TitleUtil.checkTitleValid(playerSession);
/* 34 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 35 */     if (request.title == playerComponent.getWearTitle()) {
/* 36 */       return 13803;
/*    */     }
/* 38 */     if (!playerComponent.getActiveTitles().containsKey(Integer.valueOf(request.title))) {
/* 39 */       return 13802;
/*    */     }
/*    */     
/* 42 */     playerComponent.setWearTitle(request.title);
/* 43 */     ((TitleWearResponse)this.response).title = request.title;
/*    */     
/* 45 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\title\TitleWearProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */