/*    */ package com.linlongyx.sanguo.webgame.processors.fight;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResultUtil
/*    */ {
/*    */   public static void saveResult(long playerId, byte type, int insId, int star, ArrayList<Reward> list, int percent) {
/* 24 */     ExtendComponent extendComponent = (ExtendComponent)LookUpService.getComponent(playerId, ExtendComponent.class);
/* 25 */     if (null != extendComponent)
/* 26 */       extendComponent.setInsRewardResponse(type, insId, star, list, percent); 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\fight\ResultUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */