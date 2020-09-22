/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.user.UserComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.ActiveCodeGetRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.ActiveCodeGetResponse;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ActiveCodeGetProcessor
/*    */   extends ProcessorBase<ActiveCodeGetRequest, ActiveCodeGetResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new ActiveCodeGetResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ActiveCodeGetRequest request) {
/* 27 */     String code = request.code.trim();
/* 28 */     if (code.equals("")) {
/* 29 */       return 11907;
/*    */     }
/* 31 */     if (code.length() == 8 || code.length() == 16) {
/* 32 */       return handle2(playerSession, code);
/*    */     }
/* 34 */     return 11908;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private short handle2(IPlayerSession playerSession, String code) {
/* 45 */     UserComponent userComponent = (UserComponent)playerSession.getPlayer().createIfNotExist(UserComponent.class);
/* 46 */     ArrayList<Reward> arrayList = new ArrayList<>();
/* 47 */     short errCode = WelfareUtil.checkCode(Long.valueOf(userComponent.getUserId()), Long.valueOf(playerSession.getPlayer().getPlayerId()), code, arrayList);
/* 48 */     if (0 == errCode) {
/* 49 */       ((ActiveCodeGetResponse)this.response).list.addAll(FinanceUtil.rewardGet(arrayList, playerSession.getPlayer(), ResourceEvent.NewActiveCode, true));
/*    */     }
/* 51 */     return errCode;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\ActiveCodeGetProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */