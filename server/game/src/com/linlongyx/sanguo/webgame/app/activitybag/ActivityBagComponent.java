/*     */ package com.linlongyx.sanguo.webgame.app.activitybag;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemExchangeBean;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BagItemInfo;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class ActivityBagComponent
/*     */   extends AbstractMapComponent<ActivityBagEntity>
/*     */ {
/*     */   private ArrayList<BagItemInfo> noticeList;
/*     */   
/*     */   public ActivityBagComponent(long playerId) {
/*  26 */     super(ActivityBagEntity.class, playerId);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  35 */     this.noticeList = new ArrayList<>();
/*     */   }
/*     */   
/*     */   private void initComponent() {
/*  39 */     int time = TimeUtil.currentTime();
/*  40 */     List<String> dels = new ArrayList<>();
/*  41 */     for (Map.Entry<String, IMapEntity> entry : (Iterable<Map.Entry<String, IMapEntity>>)getEntityMap().entrySet()) {
/*  42 */       ActivityBagEntity activityBagEntity = (ActivityBagEntity)entry.getValue();
/*  43 */       if (activityBagEntity.getNum() == 0) {
/*  44 */         dels.add(entry.getKey());
/*     */       }
/*  46 */       ItemExchangeBean itemExchangeBean = (ItemExchangeBean)JsonTableService.getJsonData(activityBagEntity.getItemId(), ItemExchangeBean.class);
/*     */       
/*  48 */       if (null != itemExchangeBean) {
/*  49 */         if (itemExchangeBean.getDelete() == 1) {
/*  50 */           if (time > activityBagEntity.getEnd())
/*  51 */             dels.add(entry.getKey()); 
/*     */           continue;
/*     */         } 
/*  54 */         if (time > TimeUtil.getTimeFromDate(itemExchangeBean.getEndTime())) {
/*  55 */           dels.add(entry.getKey());
/*     */         }
/*     */         continue;
/*     */       } 
/*  59 */       if (time > activityBagEntity.getEnd()) {
/*  60 */         dels.add(entry.getKey());
/*     */       }
/*     */     } 
/*     */     
/*  64 */     for (String key : dels) {
/*  65 */       this.proxy.delEntity(key);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ActivityBagEntity createEntity(Object key) {
/*  71 */     int itemId = ((Integer)key).intValue();
/*  72 */     ActivityBagEntity activityBagEntity = (ActivityBagEntity)super.createEntity(Integer.valueOf(itemId));
/*  73 */     return activityBagEntity; } public void init() {
/*     */     super.init();
/*     */     initComponent();
/*     */   } public ActivityBagEntity getEntity(int id) {
/*  77 */     return (ActivityBagEntity)getEntity(String.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(int nowtime, int itemId, int num) {
/*  86 */     ItemExchangeBean itemExchangeBean = (ItemExchangeBean)JsonTableService.getJsonData(itemId, ItemExchangeBean.class);
/*  87 */     if (null == itemExchangeBean) {
/*     */       return;
/*     */     }
/*  90 */     if (nowtime > TimeUtil.getTimeFromDate(itemExchangeBean.getEndTime()))
/*     */       return; 
/*  92 */     addItem(nowtime, itemExchangeBean, num);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addItem(int nowtime, ItemExchangeBean itemExchangeBean, int num) {
/* 102 */     ActivityBagEntity activityBagEntity = getEntity(itemExchangeBean.getId());
/* 103 */     if (activityBagEntity == null) {
/* 104 */       activityBagEntity = createEntity(Integer.valueOf(itemExchangeBean.getId()));
/* 105 */       activityBagEntity.setNum(num);
/* 106 */       activityBagEntity.setEnd(TimeUtil.getTimeFromDate(itemExchangeBean.getEndTime()));
/* 107 */       addEntity((IEntity)activityBagEntity);
/*     */     } else {
/* 109 */       if (nowtime > activityBagEntity.getEnd()) {
/* 110 */         deleteItem(activityBagEntity, activityBagEntity.getNum());
/*     */       }
/* 112 */       activityBagEntity.setEnd(TimeUtil.getTimeFromDate(itemExchangeBean.getEndTime()));
/* 113 */       activityBagEntity.setNum(activityBagEntity.getNum() + num);
/*     */     } 
/* 115 */     activityBagLog(activityBagEntity.getItemId(), num, activityBagEntity.getNum(), activityBagEntity.getEnd(), 2);
/*     */ 
/*     */     
/* 118 */     BagItemInfo bagItemInfo = new BagItemInfo();
/* 119 */     bagItemInfo.itemId = itemExchangeBean.getId();
/* 120 */     bagItemInfo.num = num;
/* 121 */     bagItemInfo.status = 1;
/* 122 */     this.noticeList.add(bagItemInfo);
/* 123 */     notice();
/*     */   }
/*     */   
/*     */   public void deleteItem(ActivityBagEntity activityBagEntity, long num) {
/* 127 */     activityBagLog(activityBagEntity.getItemId(), activityBagEntity.getNum() + num, activityBagEntity.getNum(), activityBagEntity.getEnd(), 1);
/* 128 */     BagItemInfo bagItemInfo = new BagItemInfo();
/* 129 */     bagItemInfo.itemId = activityBagEntity.getItemId();
/* 130 */     bagItemInfo.num = activityBagEntity.getNum();
/* 131 */     bagItemInfo.status = 2;
/* 132 */     this.noticeList.add(bagItemInfo);
/* 133 */     notice();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deleteItem(int nowtime, int itemId, int num) {
/* 144 */     ActivityBagEntity activityBagEntity = getEntity(itemId);
/* 145 */     if (activityBagEntity == null) return false; 
/* 146 */     if (nowtime > activityBagEntity.getEnd()) {
/* 147 */       deleteItem(activityBagEntity, activityBagEntity.getNum());
/*     */     }
/* 149 */     if (activityBagEntity.getNum() < num) {
/* 150 */       return false;
/*     */     }
/* 152 */     activityBagEntity.setNum(activityBagEntity.getNum() - num);
/* 153 */     deleteItem(activityBagEntity, num);
/* 154 */     return true;
/*     */   }
/*     */   
/*     */   public void activityBagLog(int itemId, long num, long after, int endTime, int type) {
/* 158 */     PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(getPlayerId(), PlayerComponent.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean check(int nowtime, int itemId, long num) {
/* 170 */     return (getItemNum(nowtime, itemId) >= num);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getItemNum(int nowtime, int itemId) {
/* 180 */     ItemExchangeBean itemExchangeBean = (ItemExchangeBean)JsonTableService.getJsonData(itemId, ItemExchangeBean.class);
/* 181 */     if (itemExchangeBean == null) {
/* 182 */       return 0L;
/*     */     }
/* 184 */     ActivityBagEntity activityBagEntity = getEntity(itemId);
/* 185 */     if (activityBagEntity == null) return 0L;
/*     */     
/* 187 */     boolean del = false;
/* 188 */     if (itemExchangeBean.getDelete() == 1) {
/* 189 */       if (nowtime > activityBagEntity.getEnd()) {
/* 190 */         del = true;
/*     */       }
/*     */     }
/* 193 */     else if (nowtime > TimeUtil.getTimeFromDate(itemExchangeBean.getEndTime())) {
/* 194 */       del = true;
/*     */     } 
/*     */ 
/*     */     
/* 198 */     if (del) {
/* 199 */       deleteItem(activityBagEntity, activityBagEntity.getNum());
/* 200 */       return 0L;
/*     */     } 
/*     */     
/* 203 */     return activityBagEntity.getNum();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getItemNum(int itemId) {
/* 212 */     ActivityBagEntity activityBagEntity = getEntity(itemId);
/* 213 */     if (activityBagEntity == null) return 0L; 
/* 214 */     return activityBagEntity.getNum();
/*     */   }
/*     */   
/*     */   public void notice() {
/* 218 */     if (this.noticeList.isEmpty())
/*     */       return; 
/* 220 */     if (LookUpService.getByPlayerId(getPlayerId()) != null) {
/* 221 */       IPlayerSession playerSession = LookUpService.getByPlayerId(getPlayerId()).getSession();
/* 222 */       BagNoticeResponse response = new BagNoticeResponse();
/* 223 */       response.bagItemInfos = this.noticeList;
/* 224 */       playerSession.sendMessage((ResponseBase)response);
/* 225 */       this.noticeList.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/* 231 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/* 236 */     this.playerId = playerId;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\activitybag\ActivityBagComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */