/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.SingleInsParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.fight.FightRecordProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordRequest;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SingleInsGm
/*    */   implements IGm
/*    */ {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 20 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/*    */     
/* 22 */     if (strings[2].equals("clear")) {
/* 23 */       int intType = Integer.valueOf(strings[3]).intValue();
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 28 */       Map<Integer, Integer> maxPoints = singleInsComponent.getMaxPoints();
/* 29 */       Set<Integer> clears = singleInsComponent.getClears();
/* 30 */       SingleInsParameter singleInsParameter = (SingleInsParameter)ParameterConstant.getParameter(13);
/* 31 */       Map<Integer, Integer> list = singleInsParameter.getInsListByType(intType);
/* 32 */       int max = 0;
/* 33 */       for (Map.Entry<Integer, Integer> entry : list.entrySet()) {
/* 34 */         clears.add(entry.getKey());
/* 35 */         if (((Integer)entry.getKey()).intValue() > max) {
/* 36 */           max = ((Integer)entry.getKey()).intValue();
/*    */         }
/*    */       } 
/* 39 */       maxPoints.put(Integer.valueOf(intType), Integer.valueOf(max));
/* 40 */       singleInsComponent.setMaxPoints(maxPoints);
/* 41 */     } else if (strings[2].equals("reset")) {
/* 42 */       singleInsComponent.reset(0);
/* 43 */     } else if (strings[2].equals("fight")) {
/* 44 */       FightRecordRequest recordRequest = new FightRecordRequest();
/* 45 */       recordRequest.type = Byte.valueOf(strings[3]).byteValue();
/* 46 */       recordRequest.id = strings[4];
/* 47 */       (new FightRecordProcessor()).handle(playerSession, (RequestBase)recordRequest);
/* 48 */     } else if (strings[2].equals("t4")) {
/*    */     
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\SingleInsGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */