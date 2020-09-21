/*     */ package com.linlongyx.sanguo.webgame.app.gm;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.bag.BagRebornProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.bag.BagRecoveryProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.bag.BagUseItemProcessor;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagRebornRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagRecoveryRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagUseItemRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemGm
/*     */   implements IGm
/*     */ {
/*     */   public void gm(IPlayerSession playerSession, String[] strings) {
/*  32 */     BagComponent component = (BagComponent)playerSession.getPlayer().getComponent(BagComponent.class);
/*  33 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/*  34 */     if (component == null || playerComponent == null)
/*  35 */       return;  if (strings[2].equals("add")) {
/*  36 */       int itemId = Integer.parseInt(strings[3]);
/*  37 */       int num = Integer.parseInt(strings[4]);
/*  38 */       Reward reward = new Reward();
/*  39 */       reward.type = 2;
/*  40 */       reward.id = itemId;
/*  41 */       reward.num = num;
/*  42 */       ArrayList<Reward> rewards = new ArrayList<>();
/*  43 */       rewards.add(reward);
/*  44 */       FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.gmAdd, true);
/*  45 */     } else if (strings[2].equals("use")) {
/*  46 */       int itemId = Integer.parseInt(strings[3]);
/*  47 */       int num = Integer.parseInt(strings[4]);
/*  48 */       BagUseItemRequest request = new BagUseItemRequest();
/*  49 */       request.itemId = itemId;
/*  50 */       request.num = num;
/*  51 */       (new BagUseItemProcessor()).handle(playerSession, (RequestBase)request);
/*  52 */     } else if (strings[2].equals("clear")) {
/*  53 */       ArrayList<IMapEntity> bagEntities = new ArrayList<>(component.getEntityMap().values());
/*  54 */       for (IMapEntity iMapEntity : bagEntities) {
/*  55 */         BagEntity bagEntity = (BagEntity)iMapEntity;
/*  56 */         component.deleteItem(bagEntity.getItemId(), bagEntity.getNum(), ResourceEvent.gmUse);
/*     */       } 
/*  58 */       component.notice();
/*  59 */     } else if (strings[2].equals("delete")) {
/*  60 */       int itemId = Integer.parseInt(strings[3]);
/*  61 */       int num = Integer.parseInt(strings[4]);
/*  62 */       ArrayList<IMapEntity> bagEntities = new ArrayList<>(component.getEntityMap().values());
/*  63 */       component.deleteItem(itemId, num, ResourceEvent.gmUse);
/*  64 */       component.notice();
/*  65 */     } else if (strings[2].equals("addd")) {
/*  66 */       String itemName = strings[3];
/*  67 */       int itemId = 0;
/*  68 */       for (Map.Entry<Integer, Object> entry : (Iterable<Map.Entry<Integer, Object>>)JsonTableService.getJsonTable(ItemBean.class).entrySet()) {
/*  69 */         ItemBean itemBean = (ItemBean)entry.getValue();
/*  70 */         if (itemBean.getName().equals(itemName)) {
/*  71 */           itemId = ((Integer)entry.getKey()).intValue();
/*     */           break;
/*     */         } 
/*     */       } 
/*  75 */       if (itemId != 0) {
/*  76 */         Reward reward = new Reward();
/*  77 */         reward.type = 2;
/*  78 */         reward.id = itemId;
/*  79 */         reward.num = Integer.parseInt(strings[4]);
/*  80 */         ArrayList<Reward> rewards = new ArrayList<>();
/*  81 */         rewards.add(reward);
/*  82 */         FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.gmAdd, true);
/*     */       } 
/*  84 */     } else if (strings[2].equals("addMoney")) {
/*  85 */       int itemId = Integer.parseInt(strings[3]);
/*  86 */       int num = Integer.parseInt(strings[4]);
/*  87 */       Reward reward = new Reward();
/*  88 */       reward.type = 1;
/*  89 */       reward.id = itemId;
/*  90 */       reward.num = num;
/*  91 */       ArrayList<Reward> rewards = new ArrayList<>();
/*  92 */       rewards.add(reward);
/*  93 */       FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.gmAdd, true);
/*  94 */     } else if (strings[2].equals("addEquip")) {
/*  95 */       int itemId = Integer.parseInt(strings[3]);
/*  96 */       EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*  97 */       equipComponent.addEquip(playerSession.getPlayer(), itemId, ResourceEvent.gmAdd, true);
/*  98 */     } else if (strings[2].equals("recovery")) {
/*  99 */       int type = Integer.parseInt(strings[3]);
/* 100 */       long id = Long.parseLong(strings[4]);
/* 101 */       long id2 = Long.parseLong(strings[5]);
/* 102 */       long id3 = Long.parseLong(strings[6]);
/* 103 */       long id4 = Long.parseLong(strings[7]);
/* 104 */       long id5 = Long.parseLong(strings[8]);
/* 105 */       long id6 = Long.parseLong(strings[9]);
/* 106 */       long id7 = Long.parseLong(strings[10]);
/* 107 */       long id8 = Long.parseLong(strings[11]);
/* 108 */       BagRecoveryRequest request = new BagRecoveryRequest();
/* 109 */       request.type = type;
/* 110 */       request.ids.add(Long.valueOf(id));
/* 111 */       request.ids.add(Long.valueOf(id2));
/* 112 */       request.ids.add(Long.valueOf(id3));
/* 113 */       request.ids.add(Long.valueOf(id4));
/* 114 */       request.ids.add(Long.valueOf(id5));
/* 115 */       request.ids.add(Long.valueOf(id6));
/* 116 */       request.ids.add(Long.valueOf(id7));
/* 117 */       request.ids.add(Long.valueOf(id8));
/* 118 */       (new BagRecoveryProcessor()).handle(playerSession, (RequestBase)request);
/* 119 */     } else if (strings[2].equals("reborn")) {
/* 120 */       int type = Integer.parseInt(strings[3]);
/* 121 */       long id = Long.parseLong(strings[4]);
/* 122 */       BagRebornRequest request = new BagRebornRequest();
/* 123 */       request.type = type;
/* 124 */       request.pid = id;
/* 125 */       (new BagRebornProcessor()).handle(playerSession, (RequestBase)request);
/* 126 */     } else if (strings[2].equals("addReCharge")) {
/* 127 */       int itemId = Integer.parseInt(strings[3]);
/* 128 */       int num = Integer.parseInt(strings[4]);
/* 129 */       Reward reward = new Reward();
/* 130 */       reward.type = 6;
/* 131 */       reward.id = itemId;
/* 132 */       reward.num = num;
/* 133 */       ArrayList<Reward> rewards = new ArrayList<>();
/* 134 */       rewards.add(reward);
/* 135 */       FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.gmAdd, true);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\ItemGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */