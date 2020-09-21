/*    */ package com.linlongyx.sanguo.webgame.app.turnplate;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TurnplateBean;
/*    */ import com.linlongyx.sanguo.webgame.processors.limit.LimitUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TurnplateComponent
/*    */   extends AbstractMapComponent<TurnplateEntity>
/*    */ {
/* 21 */   private ArrayList<Reward> rewards = new ArrayList<>();
/* 22 */   private int curActId = 0;
/*    */   
/*    */   public TurnplateComponent(long playerId) {
/* 25 */     super(TurnplateEntity.class, playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getType() {
/* 30 */     return getClass().getSimpleName();
/*    */   }
/*    */ 
/*    */   
/*    */   public void build(long playerId) {
/* 35 */     this.playerId = playerId;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean reset(int time) {
/* 40 */     if (time == 0) {
/* 41 */       for (Integer actId : JsonTableService.getJsonTableKey(TurnplateBean.class)) {
/* 42 */         if (getEntity(actId.intValue()) != null) {
/* 43 */           getEntity(actId.intValue()).setItemDrawCount(new HashMap<>());
/* 44 */           getEntity(actId.intValue()).setTenDrawCount(0);
/* 45 */           getEntity(actId.intValue()).setRareCount(0);
/*    */         } 
/*    */       } 
/* 48 */       saveToDB();
/*    */     } 
/* 50 */     return true;
/*    */   }
/*    */   
/*    */   public TurnplateEntity getEntity(int id) {
/* 54 */     String key = String.valueOf(id);
/* 55 */     if (!getEntityMap().containsKey(key)) {
/* 56 */       TurnplateEntity entity = new TurnplateEntity(this.playerId, id);
/* 57 */       addEntity((IEntity)entity);
/*    */     } 
/* 59 */     return (TurnplateEntity)getEntity(key);
/*    */   }
/*    */   
/*    */   public boolean isActOpen(int actId) {
/* 63 */     TurnplateBean turnplateBean = (TurnplateBean)JsonTableService.getJsonData(actId, TurnplateBean.class);
/* 64 */     if (!LimitUtil.isValid(turnplateBean.getServerType(), turnplateBean.getDay())) {
/* 65 */       return false;
/*    */     }
/* 67 */     return LimitUtil.isActOpen(turnplateBean.getServerType(), turnplateBean.getBeginTime(), turnplateBean.getEndTime());
/*    */   }
/*    */   
/*    */   public ArrayList<Reward> getRewards() {
/* 71 */     return this.rewards;
/*    */   }
/*    */   
/*    */   public void setRewards(ArrayList<Reward> rewards) {
/* 75 */     this.rewards = rewards;
/*    */   }
/*    */   
/*    */   public int getCurActId() {
/* 79 */     if (this.curActId == 0) {
/* 80 */       for (Iterator<Integer> iterator = JsonTableService.getJsonTableKey(TurnplateBean.class).iterator(); iterator.hasNext(); ) { int actId = ((Integer)iterator.next()).intValue();
/* 81 */         if (isActOpen(actId)) {
/* 82 */           this.curActId = actId;
/*    */           break;
/*    */         }  }
/*    */     
/*    */     }
/* 87 */     return this.curActId;
/*    */   }
/*    */   
/*    */   public void setCurActId(int curActId) {
/* 91 */     this.curActId = curActId;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\turnplate\TurnplateComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */