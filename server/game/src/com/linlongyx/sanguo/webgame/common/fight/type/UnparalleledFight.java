/*     */ package com.linlongyx.sanguo.webgame.common.fight.type;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.AbstractFight;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.MonsterFightSide;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.WushuangInsBean;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FightGroupData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FighterData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UnparalleledFight
/*     */   extends AbstractFight
/*     */   implements IFight
/*     */ {
/*     */   private int instanceId;
/*     */   private WushuangInsBean wushuangInsBean;
/*     */   private IPlayer player;
/*     */   private UnparalleledComponent unparalleledComponent;
/*     */   
/*     */   public UnparalleledFight(int instanceId, IPlayer player) {
/*  39 */     this.instanceId = instanceId;
/*  40 */     this.type = 5;
/*  41 */     this.player = player;
/*  42 */     this.wushuangInsBean = (WushuangInsBean)JsonTableService.getJsonData(instanceId, WushuangInsBean.class);
/*  43 */     this.unparalleledComponent = (UnparalleledComponent)player.createIfNotExist(UnparalleledComponent.class);
/*     */   }
/*     */   
/*     */   public short initFight() {
/*  47 */     if (null == this.wushuangInsBean) {
/*  48 */       return 13002;
/*     */     }
/*  50 */     PlayerFightSide playerFightSide = FightUtil.initUnParalleledSide(this.player);
/*  51 */     initSide(0, (IFightSide)playerFightSide);
/*     */     
/*  53 */     MonsterFightSide monsterFightSide = new MonsterFightSide(this.wushuangInsBean, (byte)1);
/*  54 */     initSide(1, (IFightSide)monsterFightSide);
/*  55 */     return 0;
/*     */   }
/*     */   
/*     */   public byte start(FightRecordResponse response) {
/*  59 */     init();
/*  60 */     response.type = this.type;
/*  61 */     response.id = this.instanceId + "";
/*  62 */     getGroupData(response);
/*     */ 
/*     */     
/*  65 */     response.lGroups.forEach(fightGroupData -> fightGroupData.fighters.toArray());
/*     */ 
/*     */ 
/*     */     
/*  69 */     Set<Long> srcFighters = new HashSet<>();
/*  70 */     for (IFighter f1 : getSide((byte)0).getCurGroup().getFighters(true)) {
/*  71 */       srcFighters.add(Long.valueOf(f1.getPid()));
/*     */     }
/*     */ 
/*     */     
/*  75 */     action();
/*  76 */     getCandidateFighterData(response);
/*     */     
/*  78 */     Map<Long, Long> finalFighters = new HashMap<>();
/*  79 */     for (IFighter f1 : getSide((byte)0).getCurGroup().getFighters(false)) {
/*  80 */       if (f1.getType() != 5) {
/*  81 */         finalFighters.put(Long.valueOf(f1.getPid()), Long.valueOf(f1.getHP()));
/*     */       }
/*     */     } 
/*     */     
/*  85 */     for (Iterator<Long> iterator = srcFighters.iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  86 */       this.unparalleledComponent.getPartneredHpMap().put(Long.valueOf(pid), finalFighters.getOrDefault(Long.valueOf(pid), Long.valueOf(0L))); }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     this.unparalleledComponent.setPartneredHpMap(this.unparalleledComponent.getPartneredHpMap());
/* 106 */     getRecord(response);
/*     */     
/* 108 */     TaskComponent taskComponent = (TaskComponent)LookUpService.getComponent(this.player.getPlayerId(), TaskComponent.class);
/*     */ 
/*     */ 
/*     */     
/* 112 */     return this.result;
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
/*     */ 
/*     */   
/*     */   protected void end() {
/* 127 */     if (this.result == 2)
/* 128 */       this.result = 0; 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\UnparalleledFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */