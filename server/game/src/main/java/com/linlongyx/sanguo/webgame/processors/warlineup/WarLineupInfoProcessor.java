/*    */ package com.linlongyx.sanguo.webgame.processors.warlineup;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WarLineupData;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.WarLineupInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.WarLineupInfoResponse;
/*    */ 
/*    */ 
/*    */ public class WarLineupInfoProcessor
/*    */   extends ProcessorBase<WarLineupInfoRequest, WarLineupInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new WarLineupInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WarLineupInfoRequest request) {
/* 24 */     WarLineupComponent warLineupComponent = (WarLineupComponent)playerSession.getPlayer().createIfNotExist(WarLineupComponent.class);
/* 25 */     warLineupComponent.getEntityMap().values().forEach(iMapEntity -> {
/*    */           WarLineupEntity warLineupEntity = (WarLineupEntity)iMapEntity;
/*    */           
/*    */           if (warLineupEntity != null) {
/*    */             WarLineupData warLineupData = new WarLineupData();
/*    */             
/*    */             warLineupData.battle = warLineupEntity.getBattle();
/*    */             
/*    */             warLineupData.level = warLineupEntity.getLevel();
/*    */             warLineupData.star = warLineupEntity.getStar();
/*    */             warLineupData.warLineup = warLineupEntity.getWarLineupId();
/*    */             warLineupData.exp = warLineupEntity.getExp();
/*    */             warLineupData.fightValue = warLineupEntity.getFightValue();
/*    */             ((WarLineupInfoResponse)this.response).warLineupList.add(warLineupData);
/*    */           } 
/*    */         });
/* 41 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\warlineup\WarLineupInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */