/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BossHomeBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.RankBossSettingRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.RankBossSettingResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankBossSettingProcessor
/*    */   extends ProcessorBase<RankBossSettingRequest, RankBossSettingResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new RankBossSettingResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RankBossSettingRequest request) {
/* 27 */     BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/* 28 */     List<Integer> newInstSetting = new ArrayList<>();
/* 29 */     for (Integer insId : request.settingList) {
/* 30 */       BossHomeBean bossHomeBean = (BossHomeBean)JsonTableService.getJsonData(insId.intValue(), BossHomeBean.class);
/* 31 */       if (bossHomeBean == null) {
/*    */         continue;
/*    */       }
/* 34 */       newInstSetting.add(insId);
/*    */     } 
/* 36 */     bossHomeComponent.setBossNeedNotice(newInstSetting);
/* 37 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\RankBossSettingProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */