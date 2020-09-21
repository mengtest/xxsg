/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.app.rankAct.RankActComponent;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.charge.ChargeUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.limit.LimitActInfoProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankAct;
/*    */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.limit.LimitActInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.jdbc.core.JdbcTemplate;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ActivityGm
/*    */   implements IGm
/*    */ {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 27 */     if (strings[2].equals("info")) {
/* 28 */       LimitActInfoRequest request = new LimitActInfoRequest();
/* 29 */       request.actId = Integer.parseInt(strings[3]);
/* 30 */       (new LimitActInfoProcessor()).handle(playerSession, (RequestBase)request);
/*    */     } 
/*    */     
/* 33 */     if (strings[2].equals("rankClear")) {
/* 34 */       clearPlayerRankData();
/* 35 */     } else if (strings[2].equals("rankRefresh")) {
/* 36 */       RankActUtil.forceRefreshRanks();
/* 37 */     } else if (strings[2].equals("rankReset")) {
/* 38 */       clearPlayerRankData();
/* 39 */       RankActUtil.resetAllRankAct();
/* 40 */     } else if (strings[2].equals("rankSet")) {
/* 41 */       int rankId = Integer.parseInt(strings[3]);
/* 42 */       int stateNum = Integer.parseInt(strings[4]);
/* 43 */       RankAct.RankActState state = (stateNum == 0) ? RankAct.RankActState.NotOpen : ((stateNum == 1) ? RankAct.RankActState.Opening : RankAct.RankActState.Expire);
/* 44 */       if (stateNum == 1) {
/* 45 */         RankActComponent rankActComponent = (RankActComponent)playerSession.getPlayer().getComponent(RankActComponent.class);
/* 46 */         rankActComponent.getEntity(rankId).setOpen(true);
/*    */       } 
/* 48 */       RankActUtil.setRankActState(rankId, state);
/* 49 */     } else if (strings[2].equals("balance")) {
/* 50 */       RankActUtil.balanceExpired(playerSession);
/* 51 */     } else if (strings[2].equals("start")) {
/* 52 */       ChargeUtil.setIsDebug(true);
/* 53 */     } else if (strings[2].equals("stop")) {
/* 54 */       ChargeUtil.setIsDebug(false);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void clearPlayerRankData() {
/*    */     List<Map<String, Object>> info;
/*    */     try {
/* 61 */       MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/* 62 */       JdbcTemplate template = mysql.getTemplate();
/* 63 */       String selectSql = "SELECT DISTINCT p.playerId FROM tb_player p";
/* 64 */       info = template.queryForList(selectSql);
/* 65 */     } catch (Exception e) {
/* 66 */       LogUtil.errorLog(new Object[] { "rankClear", Arrays.toString((Object[])e.getStackTrace()) });
/*    */       return;
/*    */     } 
/* 69 */     List<Long> playerIds = new ArrayList<>();
/* 70 */     for (Map<String, Object> map : info) {
/* 71 */       Long playerId = Long.valueOf(((Long)map.get("playerId")).longValue());
/* 72 */       playerIds.add(playerId);
/*    */     } 
/* 74 */     for (Long playerId : playerIds) {
/* 75 */       RankActComponent rankActComponent = (RankActComponent)LookUpService.getComponent(playerId.longValue(), RankActComponent.class);
/* 76 */       if (null != rankActComponent) {
/* 77 */         rankActComponent.deleteAll();
/* 78 */         rankActComponent.saveToDB();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\ActivityGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */