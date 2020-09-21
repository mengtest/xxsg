/*     */ package com.linlongyx.sanguo.webgame.common.fight.side;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.MonsterFighter;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ArenaRobotBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.BaguaInsBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.BlocBossBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.BossHomeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.GeneralInsBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MainInsBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MultiInsBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.NeutralBossBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.PersonalInsBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.SecretiInsBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TowerBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.WushuangInsBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.YearBeastListBean;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class MonsterFightSide extends AbstractFightSide implements IFightSide {
/*     */   public MonsterFightSide(MainInsBean mainInsBean, byte side) {
/*  22 */     this.isAuto = true;
/*  23 */     this.side = side;
/*  24 */     for (MainInsBean.WaveBean waveBean : mainInsBean.getWave().values()) {
/*  25 */       FightGroup group = new FightGroup();
/*  26 */       List<IFighter> fighters = new ArrayList<>();
/*  27 */       for (MainInsBean.WaveBean.MonsterBean monsterBean : waveBean.getMonster()) {
/*     */         
/*  29 */         MonsterFighter monsterFighter = new MonsterFighter(monsterBean.getId(), (byte)monsterBean.getPosi());
/*  30 */         fighters.add(monsterFighter);
/*     */       } 
/*  32 */       group.setFighters(fighters);
/*  33 */       group.setMaxRound(waveBean.getRound());
/*  34 */       this.groupList.add(group);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterFightSide(PersonalInsBean personalInsBean, byte side) {
/*  44 */     this.isAuto = true;
/*  45 */     this.side = side;
/*  46 */     for (PersonalInsBean.WaveBean waveBean : personalInsBean.getWave().values()) {
/*  47 */       FightGroup group = new FightGroup();
/*  48 */       List<IFighter> fighters = new ArrayList<>();
/*  49 */       for (PersonalInsBean.WaveBean.MonsterBean monsterBean : waveBean.getMonster()) {
/*  50 */         MonsterFighter monsterFighter = new MonsterFighter(monsterBean.getId(), (byte)monsterBean.getPosi());
/*  51 */         fighters.add(monsterFighter);
/*     */       } 
/*  53 */       group.setFighters(fighters);
/*  54 */       group.setMaxRound(waveBean.getRound());
/*  55 */       this.groupList.add(group);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterFightSide(BossHomeBean bossHomeBean, byte side) {
/*  65 */     this.isAuto = true;
/*  66 */     this.side = side;
/*  67 */     for (BossHomeBean.WaveBean waveBean : bossHomeBean.getWave().values()) {
/*  68 */       FightGroup group = new FightGroup();
/*  69 */       List<IFighter> fighters = new ArrayList<>();
/*  70 */       for (BossHomeBean.WaveBean.MonsterBean monsterBean : waveBean.getMonster()) {
/*  71 */         if (monsterBean.getId() == 0)
/*     */           continue; 
/*  73 */         MonsterFighter monsterFighter = new MonsterFighter(monsterBean.getId(), (byte)monsterBean.getPosi());
/*  74 */         fighters.add(monsterFighter);
/*     */       } 
/*  76 */       group.setFighters(fighters);
/*  77 */       group.setMaxRound(waveBean.getRound());
/*  78 */       this.groupList.add(group);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterFightSide(ArenaRobotBean arenaRobotBean, byte side) {
/*  88 */     this.isAuto = true;
/*  89 */     this.side = side;
/*  90 */     for (ArenaRobotBean.WaveBean waveBean : arenaRobotBean.getWave().values()) {
/*  91 */       FightGroup group = new FightGroup();
/*  92 */       List<IFighter> fighters = new ArrayList<>();
/*  93 */       for (ArenaRobotBean.WaveBean.MonsterBean monsterBean : waveBean.getMonster()) {
/*  94 */         if (monsterBean.getId() == 0)
/*     */           continue; 
/*  96 */         MonsterFighter monsterFighter = new MonsterFighter(monsterBean.getId(), (byte)monsterBean.getPosi());
/*  97 */         fighters.add(monsterFighter);
/*     */       } 
/*  99 */       group.setFighters(fighters);
/* 100 */       group.setMaxRound(waveBean.getRound());
/* 101 */       this.groupList.add(group);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterFightSide(WushuangInsBean wushuangInsBean, byte side) {
/* 111 */     this.isAuto = true;
/* 112 */     this.side = side;
/* 113 */     for (WushuangInsBean.WaveBean waveBean : wushuangInsBean.getWave().values()) {
/* 114 */       FightGroup group = new FightGroup();
/* 115 */       List<IFighter> fighters = new ArrayList<>();
/* 116 */       for (WushuangInsBean.WaveBean.MonsterBean monsterBean : waveBean.getMonster()) {
/* 117 */         if (monsterBean.getId() == 0)
/*     */           continue; 
/* 119 */         MonsterFighter monsterFighter = new MonsterFighter(monsterBean.getId(), (byte)monsterBean.getPosi());
/* 120 */         fighters.add(monsterFighter);
/*     */       } 
/* 122 */       group.setFighters(fighters);
/* 123 */       group.setMaxRound(waveBean.getRound());
/* 124 */       this.groupList.add(group);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterFightSide(GeneralInsBean generalInsBean, byte side) {
/* 134 */     this.isAuto = true;
/* 135 */     this.side = side;
/* 136 */     for (GeneralInsBean.WaveBean waveBean : generalInsBean.getWave().values()) {
/* 137 */       FightGroup group = new FightGroup();
/* 138 */       List<IFighter> fighters = new ArrayList<>();
/* 139 */       for (GeneralInsBean.WaveBean.MonsterBean monsterBean : waveBean.getMonster()) {
/* 140 */         if (monsterBean.getId() == 0)
/*     */           continue; 
/* 142 */         MonsterFighter monsterFighter = new MonsterFighter(monsterBean.getId(), (byte)monsterBean.getPosi());
/* 143 */         fighters.add(monsterFighter);
/*     */       } 
/* 145 */       group.setFighters(fighters);
/* 146 */       group.setMaxRound(waveBean.getRound());
/* 147 */       this.groupList.add(group);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterFightSide(TowerBean towerBean, byte side) {
/* 157 */     this.isAuto = true;
/* 158 */     this.side = side;
/* 159 */     for (TowerBean.WaveBean waveBean : towerBean.getWave().values()) {
/* 160 */       FightGroup group = new FightGroup();
/* 161 */       List<IFighter> fighters = new ArrayList<>();
/* 162 */       for (TowerBean.WaveBean.MonsterIDBean monsterBean : waveBean.getMonsterID()) {
/* 163 */         if (monsterBean.getId() == 0)
/*     */           continue; 
/* 165 */         MonsterFighter monsterFighter = new MonsterFighter(monsterBean.getId(), (byte)monsterBean.getPosi());
/* 166 */         fighters.add(monsterFighter);
/*     */       } 
/* 168 */       group.setFighters(fighters);
/* 169 */       group.setMaxRound(waveBean.getRound());
/* 170 */       this.groupList.add(group);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterFightSide(MultiInsBean multiInsBean, byte side) {
/* 180 */     this.isAuto = true;
/* 181 */     this.side = side;
/* 182 */     for (MultiInsBean.WaveBean waveBean : multiInsBean.getWave().values()) {
/* 183 */       FightGroup group = new FightGroup();
/* 184 */       List<IFighter> fighters = new ArrayList<>();
/* 185 */       for (MultiInsBean.WaveBean.MonsterBean monsterBean : waveBean.getMonster()) {
/* 186 */         if (monsterBean.getId() == 0)
/*     */           continue; 
/* 188 */         MonsterFighter monsterFighter = new MonsterFighter(monsterBean.getId(), (byte)monsterBean.getPosi());
/* 189 */         fighters.add(monsterFighter);
/*     */       } 
/* 191 */       group.setFighters(fighters);
/* 192 */       group.setMaxRound(waveBean.getRound());
/* 193 */       this.groupList.add(group);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterFightSide(BaguaInsBean baguaInsBean, byte side) {
/* 203 */     this.isAuto = true;
/* 204 */     this.side = side;
/* 205 */     for (BaguaInsBean.WaveBean waveBean : baguaInsBean.getWave().values()) {
/* 206 */       FightGroup group = new FightGroup();
/* 207 */       List<IFighter> fighters = new ArrayList<>();
/* 208 */       for (BaguaInsBean.WaveBean.MonsterBean monsterBean : waveBean.getMonster()) {
/* 209 */         if (monsterBean.getId() == 0)
/*     */           continue; 
/* 211 */         MonsterFighter monsterFighter = new MonsterFighter(monsterBean.getId(), (byte)monsterBean.getPosi());
/* 212 */         fighters.add(monsterFighter);
/*     */       } 
/* 214 */       group.setFighters(fighters);
/* 215 */       group.setMaxRound(waveBean.getRound());
/* 216 */       this.groupList.add(group);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterFightSide(BlocBossBean blocBossBean, byte side) {
/* 226 */     this.isAuto = true;
/* 227 */     this.side = side;
/* 228 */     for (BlocBossBean.WaveBean waveBean : blocBossBean.getWave().values()) {
/* 229 */       FightGroup group = new FightGroup();
/* 230 */       List<IFighter> fighters = new ArrayList<>();
/* 231 */       for (BlocBossBean.WaveBean.MonsterBean monsterBean : waveBean.getMonster()) {
/* 232 */         if (monsterBean.getId() == 0)
/*     */           continue; 
/* 234 */         MonsterFighter monsterFighter = new MonsterFighter(monsterBean.getId(), (byte)monsterBean.getPosi());
/* 235 */         fighters.add(monsterFighter);
/*     */       } 
/* 237 */       group.setFighters(fighters);
/* 238 */       group.setMaxRound(waveBean.getRound());
/* 239 */       this.groupList.add(group);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterFightSide(NeutralBossBean neutralBossBean, byte side) {
/* 249 */     this.isAuto = true;
/* 250 */     this.side = side;
/* 251 */     for (NeutralBossBean.WaveBean waveBean : neutralBossBean.getWave().values()) {
/* 252 */       FightGroup group = new FightGroup();
/* 253 */       List<IFighter> fighters = new ArrayList<>();
/* 254 */       for (NeutralBossBean.WaveBean.MonsterBean monsterBean : waveBean.getMonster()) {
/* 255 */         if (monsterBean.getId() == 0)
/*     */           continue; 
/* 257 */         MonsterFighter monsterFighter = new MonsterFighter(monsterBean.getId(), (byte)monsterBean.getPosi());
/* 258 */         fighters.add(monsterFighter);
/*     */       } 
/* 260 */       group.setFighters(fighters);
/* 261 */       group.setMaxRound(waveBean.getRound());
/* 262 */       this.groupList.add(group);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterFightSide(YearBeastListBean yearBeastListBean, byte side) {
/* 272 */     this.isAuto = true;
/* 273 */     this.side = side;
/* 274 */     for (YearBeastListBean.WaveBean waveBean : yearBeastListBean.getWave().values()) {
/* 275 */       FightGroup group = new FightGroup();
/* 276 */       List<IFighter> fighters = new ArrayList<>();
/* 277 */       for (YearBeastListBean.WaveBean.MonsterBean monsterBean : waveBean.getMonster()) {
/* 278 */         if (monsterBean.getId() == 0)
/*     */           continue; 
/* 280 */         MonsterFighter monsterFighter = new MonsterFighter(monsterBean.getId(), (byte)monsterBean.getPosi());
/* 281 */         fighters.add(monsterFighter);
/*     */       } 
/* 283 */       group.setFighters(fighters);
/* 284 */       group.setMaxRound(waveBean.getRound());
/* 285 */       this.groupList.add(group);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterFightSide(SecretiInsBean secretiInsBean, byte side) {
/* 295 */     this.isAuto = true;
/* 296 */     this.side = side;
/* 297 */     for (SecretiInsBean.WaveBean waveBean : secretiInsBean.getWave().values()) {
/* 298 */       FightGroup group = new FightGroup();
/* 299 */       List<IFighter> fighters = new ArrayList<>();
/* 300 */       for (SecretiInsBean.WaveBean.MonsterBean monsterBean : waveBean.getMonster()) {
/* 301 */         if (monsterBean.getId() == 0)
/*     */           continue; 
/* 303 */         MonsterFighter monsterFighter = new MonsterFighter(monsterBean.getId(), (byte)monsterBean.getPosi());
/* 304 */         fighters.add(monsterFighter);
/*     */       } 
/* 306 */       group.setFighters(fighters);
/* 307 */       group.setMaxRound(waveBean.getRound());
/* 308 */       this.groupList.add(group);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 313 */     if (!secretiInsBean.getZhenfa().isEmpty()) {
/* 314 */       this.zhenfa = new Pair(Integer.valueOf(((SecretiInsBean.ZhenfaBean)secretiInsBean.getZhenfa().get(0)).getId()), Integer.valueOf(((SecretiInsBean.ZhenfaBean)secretiInsBean.getZhenfa().get(0)).getStar()));
/*     */     }
/* 316 */     if (secretiInsBean.getPet() > 0) {
/* 317 */       this.petFighter = (IFighter)new PetFighter(secretiInsBean.getPet());
/*     */     }
/* 319 */     if (secretiInsBean.getStage() > 0) {
/* 320 */       this.stageFighter = (IFighter)new StageFighter(secretiInsBean.getStage());
/*     */     }
/* 322 */     if (!secretiInsBean.getSouls().isEmpty()) {
/* 323 */       initCandidateFighters(secretiInsBean.getSouls());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void initCandidateFighters(List<Integer> candidateFighters) {
/* 329 */     if (!candidateFighters.isEmpty())
/* 330 */       for (Iterator<Integer> iterator = candidateFighters.iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 331 */         MonsterCandidateFighter candidateFighter = new MonsterCandidateFighter(id);
/* 332 */         addCandidateFigher((IFighter)candidateFighter); }
/*     */        
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\side\MonsterFightSide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */