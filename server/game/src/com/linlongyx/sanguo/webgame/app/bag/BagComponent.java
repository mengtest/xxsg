/*     */ package com.linlongyx.sanguo.webgame.app.bag;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.log.LogType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.bag.BagUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BagItemInfo;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class BagComponent
/*     */   extends AbstractMapComponent<BagEntity>
/*     */ {
/*  29 */   public ArrayList<BagItemInfo> noticeList = new ArrayList<>();
/*     */   private static final int BAG_ITEM_MAX_SIZE = 2120000000;
/*     */   
/*     */   public BagComponent(long playerId) {
/*  33 */     super(BagEntity.class, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/*  38 */     super.init();
/*  39 */     initComponent();
/*     */   }
/*     */   
/*     */   private void initComponent() {
/*  43 */     List<String> dels = new ArrayList<>();
/*  44 */     int time = TimeUtil.currentTime();
/*  45 */     int openTime = MContext.getOpenTimeInt();
/*     */     
/*  47 */     for (Map.Entry<String, IMapEntity> entry : (Iterable<Map.Entry<String, IMapEntity>>)getEntityMap().entrySet()) {
/*  48 */       BagEntity bagEntity = (BagEntity)entry.getValue();
/*  49 */       if (bagEntity.getNum() == 0) {
/*  50 */         dels.add(entry.getKey());
/*     */       }
/*  52 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(bagEntity.getItemId(), ItemBean.class);
/*     */       
/*  54 */       if (null != itemBean) {
/*  55 */         if (itemBean.getTimeType() == 1) {
/*  56 */           int endTime = TimeUtil.getZeroTimeStamp(openTime) + Integer.parseInt(itemBean.getEndtime()) * 86400;
/*  57 */           if (time > endTime)
/*  58 */             dels.add(entry.getKey());  continue;
/*     */         } 
/*  60 */         if (itemBean.getTimeType() == 2 && 
/*  61 */           time > TimeUtil.getTimeFromDate(itemBean.getEndtime())) {
/*  62 */           dels.add(entry.getKey());
/*     */         }
/*     */         continue;
/*     */       } 
/*  66 */       dels.add(entry.getKey());
/*     */     } 
/*     */     
/*  69 */     for (String key : dels) {
/*  70 */       this.proxy.delEntity(key);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  76 */     if (time == 0) {
/*  77 */       ArrayList<Integer> deleteList = checkBag();
/*  78 */       for (Iterator<Integer> iterator = deleteList.iterator(); iterator.hasNext(); ) { int itemId = ((Integer)iterator.next()).intValue();
/*  79 */         deleteOneAll(itemId, ResourceEvent.OverdueItem); }
/*     */     
/*     */     } 
/*  82 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ArrayList<Integer> checkBag() {
/*  90 */     ArrayList<Integer> dels = new ArrayList<>();
/*  91 */     int time = TimeUtil.currentTime();
/*  92 */     int openTime = MContext.getOpenTimeInt();
/*     */     
/*  94 */     for (Map.Entry<String, IMapEntity> entry : (Iterable<Map.Entry<String, IMapEntity>>)getEntityMap().entrySet()) {
/*  95 */       BagEntity bagEntity = (BagEntity)entry.getValue();
/*  96 */       if (bagEntity.getNum() == 0) {
/*     */         continue;
/*     */       }
/*  99 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(bagEntity.getItemId(), ItemBean.class);
/*     */       
/* 101 */       if (null != itemBean) {
/* 102 */         if (itemBean.getTimeType() == 1) {
/* 103 */           int endTime = TimeUtil.getZeroTimeStamp(openTime) + Integer.parseInt(itemBean.getEndtime()) * 86400;
/* 104 */           if (time > endTime)
/* 105 */             dels.add(Integer.valueOf(bagEntity.getItemId()));  continue;
/*     */         } 
/* 107 */         if (itemBean.getTimeType() == 2 && 
/* 108 */           time > TimeUtil.getTimeFromDate(itemBean.getEndtime())) {
/* 109 */           dels.add(Integer.valueOf(bagEntity.getItemId()));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 114 */     return dels;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(int itemId, int num, ResourceEvent resourceEvent) {
/* 125 */     addItem(itemId, num, resourceEvent, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(int itemId, int num, ResourceEvent resourceEvent, boolean sendFlag) {
/* 134 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(itemId, ItemBean.class);
/* 135 */     if (null == itemBean) {
/*     */       return;
/*     */     }
/* 138 */     addItem(itemBean, num, resourceEvent);
/* 139 */     if (sendFlag) {
/* 140 */       notice();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItem(ItemBean itemBean, int num, ResourceEvent resourceEvent) {
/* 151 */     BagItemInfo itemInfo = new BagItemInfo();
/* 152 */     itemInfo.itemId = itemBean.getId();
/*     */     
/* 154 */     BagEntity bagEntity = getEntity(itemBean.getId());
/* 155 */     if (bagEntity == null) {
/* 156 */       bagEntity = createEntity(Integer.valueOf(itemBean.getId()));
/* 157 */       bagEntity.setNum(num);
/* 158 */       addEntity((IEntity)bagEntity);
/*     */     }
/* 160 */     else if (bagEntity.getNum() + num >= 2147483647L) {
/* 161 */       bagEntity.setNum(2147483647);
/*     */     } else {
/* 163 */       bagEntity.setNum(bagEntity.getNum() + num);
/*     */     } 
/*     */     
/* 166 */     itemInfo.num = bagEntity.getNum();
/* 167 */     itemInfo.status = 1;
/* 168 */     this.noticeList.add(itemInfo);
/* 169 */     PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(getPlayerId(), PlayerComponent.class);
/* 170 */     if (itemBean.getRecord() == 0) {
/* 171 */       LogUtil.gameLog(LogType.BAG, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(itemBean.getId()), Integer.valueOf(num), Integer.valueOf(bagEntity.getNum()), TimeUtil.getFormatDate(), Integer.valueOf(resourceEvent.getType()), Integer.valueOf(1) });
/*     */     }
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
/*     */   
/*     */   public boolean deleteItem(int itemId, int num, ResourceEvent resourceEvent, boolean sendFlag) {
/* 185 */     BagEntity bagEntity = getEntity(itemId);
/* 186 */     if (bagEntity == null || bagEntity.getNum() < num) {
/* 187 */       return false;
/*     */     }
/* 189 */     if (bagEntity.getNum() - num < 0) {
/* 190 */       LogUtil.errorLog(new Object[] { "BagComponent error", Long.valueOf(getPlayerId()), Integer.valueOf(resourceEvent.getType()), Integer.valueOf(bagEntity.getNum()), Integer.valueOf(num) });
/*     */     }
/* 192 */     bagEntity.setNum(bagEntity.getNum() - num);
/* 193 */     BagItemInfo bagItemInfo = new BagItemInfo();
/* 194 */     bagItemInfo.itemId = itemId;
/* 195 */     bagItemInfo.num = bagEntity.getNum();
/* 196 */     bagItemInfo.status = 2;
/* 197 */     this.noticeList.add(bagItemInfo);
/*     */     
/* 199 */     if (sendFlag) {
/* 200 */       notice();
/*     */     }
/* 202 */     PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(this.playerId, PlayerComponent.class);
/* 203 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(itemId, ItemBean.class);
/* 204 */     if (itemBean != null && itemBean.getRecord() == 0) {
/* 205 */       LogUtil.gameLog(LogType.BAG, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(itemId), Integer.valueOf(num), Integer.valueOf(bagEntity.getNum()), TimeUtil.getFormatDate(), Integer.valueOf(resourceEvent.getType()), Integer.valueOf(2) });
/*     */     }
/* 207 */     return true;
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
/*     */   
/*     */   public void deleteItem(int itemId, int num, ResourceEvent resourceEvent) {
/* 220 */     deleteItem(itemId, num, resourceEvent, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void notice() {
/* 225 */     if (this.noticeList.isEmpty()) {
/*     */       return;
/*     */     }
/* 228 */     if (LookUpService.getByPlayerId(this.playerId) != null) {
/* 229 */       IPlayerSession playerSession = LookUpService.getByPlayerId(getPlayerId()).getSession();
/* 230 */       BagNoticeResponse response = new BagNoticeResponse();
/* 231 */       ArrayList<BagItemInfo> tmpInfos = response.bagItemInfos;
/* 232 */       response.bagItemInfos = this.noticeList;
/* 233 */       this.noticeList = tmpInfos;
/* 234 */       playerSession.sendMessage((ResponseBase)response);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 239 */       PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 240 */       BagUtil.petMountsRedNotice(playerSession, playerComponent);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean check(int itemId, long num) {
/* 252 */     if (num < 0L) {
/* 253 */       return false;
/*     */     }
/* 255 */     BagEntity bagEntity = getEntity(itemId);
/* 256 */     if (bagEntity == null || bagEntity.getNum() < num) {
/* 257 */       return false;
/*     */     }
/* 259 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getItemNum(int itemId) {
/* 269 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(itemId, ItemBean.class);
/* 270 */     if (itemBean == null) {
/* 271 */       return 0L;
/*     */     }
/* 273 */     BagEntity bagEntity = getEntity(itemId);
/* 274 */     if (bagEntity == null) return 0L; 
/* 275 */     return bagEntity.getNum();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteOneAll(int itemId, ResourceEvent resourceEvent) {
/* 284 */     BagEntity bagEntity = getEntity(itemId);
/* 285 */     if (bagEntity != null) {
/* 286 */       deleteItem(itemId, bagEntity.getNum(), resourceEvent, false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BagEntity createEntity(Object key) {
/* 293 */     int itemId = ((Integer)key).intValue();
/* 294 */     BagEntity bagEntity = (BagEntity)super.createEntity(Integer.valueOf(itemId));
/* 295 */     return bagEntity;
/*     */   }
/*     */   
/*     */   public BagEntity getEntity(int id) {
/* 299 */     return (BagEntity)getEntity(String.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 305 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/* 310 */     this.playerId = playerId;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\bag\BagComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */