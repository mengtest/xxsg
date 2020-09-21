/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.NeutralBossBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.NeutralBossInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.NeutralBossInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.NeutralBossData;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Iterator;
/*    */ import java.util.TreeSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NeutralBossInfoProcessor
/*    */   extends ProcessorBase<NeutralBossInfoRequest, NeutralBossInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new NeutralBossInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, NeutralBossInfoRequest request) {
/* 29 */     BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/* 30 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 31 */     ((NeutralBossInfoResponse)this.response).buyTimes = bossHomeComponent.getBuyAlienCount();
/* 32 */     ((NeutralBossInfoResponse)this.response).surpRewardCount = bossHomeParameter.getAlienRewardCount() + bossHomeComponent.getBuyAlienCount() - bossHomeComponent.getAlienCount();
/* 33 */     TreeSet<Integer> set = JsonTableService.getJsonTableKey(NeutralBossBean.class);
/* 34 */     for (Iterator<Integer> iterator = set.iterator(); iterator.hasNext(); ) { int insId = ((Integer)iterator.next()).intValue();
/* 35 */       NeutralBossData neutralBossData = BossUtil.getAlienRaceBossData(insId);
/* 36 */       ((NeutralBossInfoResponse)this.response).bossList.add(neutralBossData); }
/*    */     
/* 38 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\NeutralBossInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */