/*    */ package com.linlongyx.sanguo.webgame.app.rechargeActivity;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TiringrebateBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TiringrebatelistBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.limit.LimitUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
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
/*    */ public class RechargeActivityUtil
/*    */ {
/*    */   public static void refreshOpeningActivity(IPlayer player, int rmb) {
/* 34 */     Map<Integer, Object> tiringrebateMap = JsonTableService.getJsonTable(TiringrebateBean.class);
/* 35 */     RechargeActivityComponent rechargeActivityComponent = (RechargeActivityComponent)player.createIfNotExist(RechargeActivityComponent.class);
/* 36 */     for (Map.Entry<Integer, Object> kv : tiringrebateMap.entrySet()) {
/* 37 */       TiringrebateBean bean = (TiringrebateBean)kv.getValue();
/* 38 */       if (LimitUtil.isValid(bean.getServerType(), 0) && LimitUtil.isActOpen(bean.getServerType(), bean.getBeginTime(), bean.getEndTime())) {
/* 39 */         RechargeActivityEntity entity = (RechargeActivityEntity)rechargeActivityComponent.getEntity(String.valueOf(kv.getKey()));
/* 40 */         if (entity == null) {
/* 41 */           entity = new RechargeActivityEntity(player.getPlayerId(), ((Integer)kv.getKey()).intValue());
/* 42 */           rechargeActivityComponent.addEntity((IEntity)entity);
/*    */         } 
/* 44 */         entity.setRecharge(entity.getRecharge() + rmb);
/*    */         
/* 46 */         for (Iterator<Integer> iterator = bean.getActivityList().iterator(); iterator.hasNext(); ) { int actId = ((Integer)iterator.next()).intValue();
/* 47 */           TiringrebatelistBean tiringrebatelistBean = (TiringrebatelistBean)JsonTableService.getJsonData(actId, TiringrebatelistBean.class);
/* 48 */           if (entity.getRecharge() >= tiringrebatelistBean.getRmb() && 
/* 49 */             !entity.getStates().containsKey(Integer.valueOf(actId))) {
/* 50 */             entity.getStates().put(Integer.valueOf(actId), Integer.valueOf(1));
/* 51 */             PlayerUtil.sendRedNotice(Long.valueOf(player.getPlayerId()), RedNoticeConstant.RechargeAct.getSys(), ((Integer)kv.getKey()).intValue());
/*    */           }  }
/*    */       
/*    */       } 
/*    */     } 
/*    */     
/* 57 */     rechargeActivityComponent.maybeSaveToDB();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isOpen(int id) {
/* 67 */     TiringrebateBean bean = (TiringrebateBean)JsonTableService.getJsonData(id, TiringrebateBean.class);
/* 68 */     if (bean == null) {
/* 69 */       return false;
/*    */     }
/* 71 */     int days = TimeUtil.getDayDiffToOpen(MContext.getOpenTime()), openTime2Seconds = TimeUtil.currentTime();
/* 72 */     boolean isOpen = false;
/* 73 */     isOpen = LimitUtil.isValid(bean.getServerType(), 0);
/* 74 */     if (!isOpen) {
/* 75 */       return isOpen;
/*    */     }
/* 77 */     if (bean.getServerType() == 0) {
/* 78 */       int startTime = TimeUtil.getTimeFromDate(bean.getBeginTime());
/* 79 */       int endTime = TimeUtil.getTimeFromDate(bean.getEndTime());
/* 80 */       isOpen = (startTime <= openTime2Seconds && openTime2Seconds <= endTime);
/*    */     } else {
/* 82 */       int startTime = Integer.parseInt(bean.getBeginTime());
/* 83 */       int endTime = Integer.parseInt(bean.getEndTime());
/* 84 */       isOpen = (startTime <= days && days <= endTime);
/*    */     } 
/* 86 */     return isOpen;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void balanceExpiredActivity(IPlayer player) {
/* 93 */     RechargeActivityComponent rechargeActivityComponent = (RechargeActivityComponent)player.createIfNotExist(RechargeActivityComponent.class);
/* 94 */     rechargeActivityComponent.getEntityMap().forEach((actId, obj) -> {
/*    */           RechargeActivityEntity entity = (RechargeActivityEntity)obj;
/*    */           if (!isOpen(entity.getId())) {
/*    */             TiringrebateBean bean = (TiringrebateBean)JsonTableService.getJsonData(entity.getId(), TiringrebateBean.class);
/*    */             entity.getStates();
/*    */             entity.setStates(entity.getStates());
/*    */           } 
/*    */         });
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\rechargeActivity\RechargeActivityUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */