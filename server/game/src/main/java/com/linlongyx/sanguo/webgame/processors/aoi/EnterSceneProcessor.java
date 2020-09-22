/*    */ package com.linlongyx.sanguo.webgame.processors.aoi;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BornPointBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MapTableBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.EnterSceneRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.EnterSceneResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class EnterSceneProcessor
/*    */   extends ProcessorBase<EnterSceneRequest, EnterSceneResponse> {
/*    */   private static final int MAX_PLAYER = 50;
/*    */   
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new EnterSceneResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, EnterSceneRequest request) {
/* 32 */     IPlayer iPlayer = playerSession.getPlayer();
/* 33 */     MapTableBean newMapTableBean = (MapTableBean)JsonTableService.getJsonData(request.sceneId, MapTableBean.class);
/*    */     
/* 35 */     if (null == newMapTableBean) {
/* 36 */       return 10101;
/*    */     }
/*    */     
/* 39 */     PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/*    */     
/* 41 */     if (newMapTableBean.getLevelNeeded() > 0 && playerComponent.getLevel() < newMapTableBean.getLevelNeeded()) {
/* 42 */       return 10084;
/*    */     }
/* 44 */     int index = RandUtil.randNum(newMapTableBean.getBornPoint().size() - 1);
/* 45 */     int size = newMapTableBean.getBornPoint().size();
/* 46 */     BornPointBean bornPointBean = (size > index) ? newMapTableBean.getBornPoint().get(index) : newMapTableBean.getBornPoint().get(size - 1);
/* 47 */     if (null != newMapTableBean.getBornPoint()) {
/* 48 */       ((EnterSceneResponse)this.response).x = (short)bornPointBean.getX();
/* 49 */       ((EnterSceneResponse)this.response).y = (short)bornPointBean.getY();
/* 50 */       ((EnterSceneResponse)this.response).sceneId = request.sceneId;
/*    */     } 
/* 52 */     playerComponent.setMainSceneId(request.sceneId);
/*    */     
/* 54 */     for (Iterator<Long> iterator = LookUpService.getOnlinePlayer().iterator(); iterator.hasNext(); ) { long playerId = ((Long)iterator.next()).longValue();
/* 55 */       if (playerId == playerComponent.getPlayerId()) {
/*    */         continue;
/*    */       }
/* 58 */       if (((EnterSceneResponse)this.response).playerList.size() < 50) {
/* 59 */         ((EnterSceneResponse)this.response).playerList.add(PlayerUtil.tramformPlayerData(playerId));
/*    */       } }
/*    */     
/* 62 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 63 */     if (taskComponent != null) {
/* 64 */       taskComponent.refreshSchedule(TaskType.EnterScene, 0, request.sceneId);
/*    */     }
/* 66 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\aoi\EnterSceneProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */