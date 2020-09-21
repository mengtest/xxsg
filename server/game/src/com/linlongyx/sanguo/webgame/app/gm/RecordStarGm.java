/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.sanguozhi.ActivateRecordStarProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.sanguozhi.SanGuoZhiInfoProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.sanguozhi.ActivateRecordStarRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.sanguozhi.SanGuoZhiInfoRequest;
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
/*    */ public class RecordStarGm
/*    */   implements IGm
/*    */ {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 29 */     if (strings[2].equals("info")) {
/* 30 */       SanGuoZhiInfoRequest request = new SanGuoZhiInfoRequest();
/* 31 */       (new SanGuoZhiInfoProcessor()).handle(playerSession, (RequestBase)request);
/* 32 */     } else if (strings[2].equals("act")) {
/* 33 */       ActivateRecordStarRequest request = new ActivateRecordStarRequest();
/* 34 */       request.recordStar = Integer.valueOf(strings[3]).intValue();
/* 35 */       (new ActivateRecordStarProcessor()).handle(playerSession, (RequestBase)request);
/* 36 */     } else if (strings[2].equals("t1")) {
/* 37 */       SanGuoZhiComponent sanGuoZhiComponent = (SanGuoZhiComponent)playerSession.getPlayer().createIfNotExist(SanGuoZhiComponent.class);
/* 38 */       int recordStar = Integer.valueOf(strings[3]).intValue();
/* 39 */       int task = Integer.valueOf(strings[4]).intValue();
/* 40 */       SanGuoZhiEntity sanGuoZhiEntity = sanGuoZhiComponent.getEntity(recordStar);
/* 41 */       sanGuoZhiEntity.getRewarded().add(Integer.valueOf(task));
/* 42 */       sanGuoZhiEntity.getFinishes().add(Integer.valueOf(task));
/* 43 */       sanGuoZhiComponent.updateFinishesDB(recordStar);
/* 44 */       sanGuoZhiComponent.updateRewardedDB(recordStar);
/* 45 */     } else if (strings[2].equals("update")) {
/* 46 */       AttrUpdateUtil.refreshLeader(playerSession);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\RecordStarGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */