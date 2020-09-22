/*    */ package com.linlongyx.sanguo.webgame.processors.title;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.title.TitleInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.title.TitleInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TitleInfoProcessor
/*    */   extends ProcessorBase<TitleInfoRequest, TitleInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new TitleInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TitleInfoRequest request) {
/* 26 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 38)) {
/* 27 */       return 10061;
/*    */     }
/* 29 */     TitleUtil.checkTitleValid(playerSession);
/* 30 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 31 */     ((TitleInfoResponse)this.response).title = playerComponent.getWearTitle();
/* 32 */     playerComponent.getActiveTitles().forEach((k, v) -> {
/*    */           KeyValue keyValue = new KeyValue();
/*    */           keyValue.key = k.intValue();
/*    */           keyValue.value = v.intValue();
/*    */           ((TitleInfoResponse)this.response).time.add(keyValue);
/*    */           ((TitleInfoResponse)this.response).list.add(k);
/*    */         });
/* 39 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\title\TitleInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */