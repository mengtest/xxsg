/*    */ package com.linlongyx.sanguo.webgame.processors.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.Rijndael;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossTokenRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossTokenResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossTokenProcessor
/*    */   extends ProcessorBase<CrossTokenRequest, CrossTokenResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new CrossTokenResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossTokenRequest request) {
/* 27 */     IPlayer player = playerSession.getPlayer();
/* 28 */     if (!CrossUtil.playerTokenMap.containsKey(Long.valueOf(player.getPlayerId()))) {
/* 29 */       int expire = TimeUtil.currentTime() + 7200;
/* 30 */       String token_src = MContext.getServerIdInt() + "_" + player.getUserId() + "_" + player.getPlayerId() + "_" + expire;
/* 31 */       Rijndael ecb_aes = Rijndael.getInstance(MContext.getSecretKey().getBytes());
/* 32 */       String token = ecb_aes.encryptStringToBase64(token_src, "UTF-8");
/* 33 */       Pair<String, Integer> tokenPair = new Pair(token, Integer.valueOf(expire));
/* 34 */       CrossUtil.playerTokenMap.put(Long.valueOf(player.getPlayerId()), tokenPair);
/*    */     } 
/* 36 */     CrossUtil.needInit("CrossTokenProcessor::needInit");
/*    */     
/* 38 */     ((CrossTokenResponse)this.response).token = (String)((Pair)CrossUtil.playerTokenMap.get(Long.valueOf(player.getPlayerId()))).getKey();
/* 39 */     ((CrossTokenResponse)this.response).crossUrl = MContext.getCrossUrl();
/* 40 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\cross\CrossTokenProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */