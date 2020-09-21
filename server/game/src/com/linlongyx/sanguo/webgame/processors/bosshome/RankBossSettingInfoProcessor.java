/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.RankBossSettingInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.RankBossSettingInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankBossSettingInfoProcessor
/*    */   extends ProcessorBase<RankBossSettingInfoRequest, RankBossSettingInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 17 */     this.response = (ResponseBase)new RankBossSettingInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RankBossSettingInfoRequest request) {
/* 22 */     BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/* 23 */     ((RankBossSettingInfoResponse)this.response).settingList.addAll(bossHomeComponent.getBossNeedNotice());
/* 24 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\RankBossSettingInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */