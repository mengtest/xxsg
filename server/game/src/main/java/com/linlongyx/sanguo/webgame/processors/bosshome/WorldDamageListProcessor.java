/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.WorldBossFightSide;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldDamageListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldDamageListResponse;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldDamageListProcessor
/*    */   extends ProcessorBase<WorldDamageListRequest, WorldDamageListResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new WorldDamageListResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WorldDamageListRequest request) {
/* 26 */     WorldBossFightSide worldBossFightSide = WorldBossUtil.getWorldBossFightSide();
/* 27 */     if (worldBossFightSide != null) {
/* 28 */       ((WorldDamageListResponse)this.response).list = new ArrayList(worldBossFightSide.getDamageRankList());
/*    */     }
/* 30 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\WorldDamageListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */