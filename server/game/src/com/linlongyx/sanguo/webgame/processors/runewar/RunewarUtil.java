/*    */ package com.linlongyx.sanguo.webgame.processors.runewar;
/*    */ 
/*    */ import com.linlongyx.core.framework.concurrent.Fibers;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.RunewarParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RunewarUtil
/*    */ {
/*    */   public static void uploadRunewarData(IPlayer player) {
/* 22 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 23 */     RunewarParameter runewarParameter = (RunewarParameter)ParameterConstant.getParameter(45);
/* 24 */     if (playerComponent.getLevel() >= runewarParameter.getLevelLimit()) {
/*    */       
/* 26 */       PlayerData playerData = CrossUtil.buildLocalPlayerData(playerComponent.getPlayerId());
/* 27 */       Fibers.createExecutorService().execute(() -> CrossUtil.runewarPlayerJoin("RunewarUtil::uploadRunewarData ", playerData));
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void addRunewarDefRecord(long playerId, BattleRecordData record) {
/* 32 */     RuneComponent runeComponent = (RuneComponent)LookUpService.getComponent(playerId, RuneComponent.class);
/* 33 */     if (runeComponent != null) {
/* 34 */       runeComponent.addRecord(record);
/* 35 */       runeComponent.addPoint(record.robNum);
/* 36 */       if (!LookUpService.isOnline(playerId))
/* 37 */         runeComponent.saveToDB(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\runewar\RunewarUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */