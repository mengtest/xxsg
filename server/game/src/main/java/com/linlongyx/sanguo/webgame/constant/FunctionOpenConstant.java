/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FunctionOpenBean;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
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
/*    */ public class FunctionOpenConstant
/*    */ {
/*    */   public static void checkFunctionOpen(IPlayerSession playerSession) {
/* 27 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 28 */     int level = playerComponent.getLevel();
/* 29 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 30 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 31 */     int taskId = 1;
/* 32 */     if (taskComponent != null) {
/* 33 */       taskId = taskComponent.getId();
/*    */     }
/*    */     
/* 36 */     Map<Integer, Object> maps = JsonTableService.getJsonTable(FunctionOpenBean.class);
/* 37 */     for (Map.Entry<Integer, Object> entry : maps.entrySet()) {
/* 38 */       FunctionOpenBean functionOpenBean = (FunctionOpenBean)entry.getValue();
/* 39 */       if (functionOpenBean.getPlayerLv() <= level && functionOpenBean.getTaskId() <= taskId && !isFunctionOpen(playerSession, functionOpenBean.getFunctionId())) {
/* 40 */         PlayerUtil.updateKeyValueInfo(playerSession, KeyValueConstant.FUNCTION_OPEN.getKey(), functionOpenBean.getFunctionId());
/* 41 */         extendComponent.getFunctionIds().add(Integer.valueOf(functionOpenBean.getFunctionId()));
/* 42 */         extendComponent.getProxy().setUpdateStatus("functionIds");
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isFunctionOpen(IPlayerSession playerSession, int functionId) {
/* 54 */     FunctionOpenBean functionOpenBean = (FunctionOpenBean)JsonTableService.getJsonData(functionId, FunctionOpenBean.class);
/* 55 */     if (null == functionOpenBean) {
/* 56 */       return true;
/*    */     }
/* 58 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 59 */     Set<Integer> functionIds = extendComponent.getFunctionIds();
/* 60 */     return functionIds.contains(Integer.valueOf(functionId));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isFunctionOpen(ExtendComponent extendComponent, int functionId) {
/* 70 */     FunctionOpenBean functionOpenBean = (FunctionOpenBean)JsonTableService.getJsonData(functionId, FunctionOpenBean.class);
/* 71 */     if (null == functionOpenBean) {
/* 72 */       return true;
/*    */     }
/* 74 */     Set<Integer> functionIds = extendComponent.getFunctionIds();
/* 75 */     return functionIds.contains(Integer.valueOf(functionId));
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\FunctionOpenConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */