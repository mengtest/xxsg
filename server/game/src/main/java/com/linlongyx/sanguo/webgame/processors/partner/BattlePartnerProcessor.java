/*     */ package com.linlongyx.sanguo.webgame.processors.partner;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.concurrent.Fibers;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.souls.SoulsComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.souls.SoulsEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.attribute.PartnerAttrUpdate;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.PartnerBattleParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.SoulsParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.EquipPart;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.BattlePartnerRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.BattlePartnerResponse;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class BattlePartnerProcessor extends ProcessorBase<BattlePartnerRequest, BattlePartnerResponse> {
/*     */   protected void initResponse() {
/*  39 */     this.response = (ResponseBase)new BattlePartnerResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, BattlePartnerRequest request) {
/*  44 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 34))
/*  45 */       return 10061; 
/*  46 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  47 */     PartnerBattleParameter partnerBattleParameter = (PartnerBattleParameter)ParameterConstant.getParameter(34);
/*  48 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/*  49 */     PartnerUtil.updateMaxSize(playerSession.getPlayer());
/*  50 */     EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*  51 */     if (request.pids.size() != extendComponent.getBattleMaxCount()) {
/*  52 */       return 13304;
/*     */     }
/*     */     
/*  55 */     int num2 = 0;
/*  56 */     for (Iterator<Integer> iterator = partnerBattleParameter.getMap().keySet().iterator(); iterator.hasNext(); ) { int pos = ((Integer)iterator.next()).intValue();
/*  57 */       int level = ((Integer)partnerBattleParameter.getMap().get(Integer.valueOf(pos))).intValue();
/*  58 */       int vipLevel = ((Integer)partnerBattleParameter.getVipMap().get(Integer.valueOf(pos))).intValue();
/*  59 */       if (playerComponent.getLevel() >= level || playerComponent.getVip() >= vipLevel) {
/*  60 */         num2++;
/*     */       } }
/*     */ 
/*     */     
/*  64 */     if (request.type == 1) {
/*  65 */       int oldBattleNum = PlayerUtil.getBattleNum(playerComponent);
/*     */       
/*  67 */       int num = 0;
/*  68 */       boolean isHash = false;
/*  69 */       for (Iterator<Long> iterator1 = request.pids.iterator(); iterator1.hasNext(); ) { long pid = ((Long)iterator1.next()).longValue();
/*  70 */         if (pid == 0L) {
/*     */           continue;
/*     */         }
/*  73 */         if (pid == -1L) {
/*  74 */           isHash = true;
/*     */         }
/*  76 */         num++; }
/*     */       
/*  78 */       GeneralComponent generalComponent = (GeneralComponent)playerSession.getPlayer().createIfNotExist(GeneralComponent.class);
/*  79 */       for (Iterator<Long> iterator2 = generalComponent.getAssistInBattle().iterator(); iterator2.hasNext(); ) { long pid = ((Long)iterator2.next()).longValue();
/*  80 */         if (pid == 0L) {
/*     */           continue;
/*     */         }
/*  83 */         if (request.pids.indexOf(Long.valueOf(pid)) >= 0) {
/*  84 */           return 13332;
/*     */         } }
/*     */       
/*  87 */       if (!isHash) {
/*  88 */         return 13325;
/*     */       }
/*  90 */       if (num > num2) {
/*  91 */         return 13304;
/*     */       }
/*  93 */       if (oldBattleNum > num) {
/*  94 */         return 13330;
/*     */       }
/*     */       
/*  97 */       int reNum = 0;
/*  98 */       PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  99 */       ArrayList<Long> batttleList2 = new ArrayList<>();
/* 100 */       boolean isChange = true;
/* 101 */       for (Iterator<Long> iterator3 = request.pids.iterator(); iterator3.hasNext(); ) { long pid = ((Long)iterator3.next()).longValue();
/* 102 */         if (pid != 0L && batttleList2.indexOf(Long.valueOf(pid)) >= 0) {
/* 103 */           return 13301;
/*     */         }
/*     */         
/* 106 */         if (playerComponent.getFighters().indexOf(Long.valueOf(pid)) < 0) {
/* 107 */           isChange = false;
/*     */         }
/* 109 */         if (pid != 0L) {
/* 110 */           reNum++;
/*     */         }
/* 112 */         if (pid == -1L || pid == 0L) {
/* 113 */           batttleList2.add(Long.valueOf(pid));
/*     */           continue;
/*     */         } 
/* 116 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 117 */         if (null == partnerEntity) {
/* 118 */           return 13303;
/*     */         }
/* 120 */         batttleList2.add(Long.valueOf(pid)); }
/*     */       
/* 122 */       boolean upPartner = false;
/*     */       
/* 124 */       if (oldBattleNum != reNum) {
/* 125 */         upPartner = true;
/*     */       }
/*     */       
/* 128 */       ArrayList<Integer> indexList = new ArrayList<>();
/* 129 */       if (!upPartner && !isChange)
/*     */       {
/*     */         
/* 132 */         for (int j = 0; j < playerComponent.getFighters().size(); j++) {
/* 133 */           long oldPid = ((Long)playerComponent.getFighters().get(j)).longValue();
/* 134 */           long newPid = ((Long)request.pids.get(j)).longValue();
/* 135 */           if (oldPid != newPid) {
/* 136 */             indexList.add(Integer.valueOf(j));
/*     */           }
/*     */         } 
/*     */       }
/* 140 */       if (!indexList.isEmpty()) {
/* 141 */         if (indexList.size() > 1) {
/* 142 */           return 13322;
/*     */         }
/* 144 */         for (Iterator<Integer> iterator5 = indexList.iterator(); iterator5.hasNext(); ) { int index = ((Integer)iterator5.next()).intValue();
/* 145 */           long oldPid = ((Long)playerComponent.getFighters().get(index)).longValue();
/* 146 */           long newPid = ((Long)request.pids.get(index)).longValue();
/* 147 */           if (oldPid == 0L || newPid == 0L || oldPid == newPid) {
/*     */             continue;
/*     */           }
/* 150 */           if (playerComponent.getFighters().indexOf(Long.valueOf(newPid)) >= 0) {
/* 151 */             return 13301;
/*     */           }
/* 153 */           PartnerEntity oldPartnerEntity = partnerComponent.getEntity(oldPid);
/* 154 */           PartnerEntity newPartnerEntity = partnerComponent.getEntity(newPid);
/* 155 */           Map<Integer, Long> equipMap = new HashMap<>(oldPartnerEntity.getEquips());
/* 156 */           Map<Integer, Long> runeMap = new HashMap<>(oldPartnerEntity.getRuneMap());
/* 157 */           for (Iterator<Integer> iterator6 = equipMap.keySet().iterator(); iterator6.hasNext(); ) { int part = ((Integer)iterator6.next()).intValue();
/* 158 */             newPartnerEntity.getEquips().put(Integer.valueOf(part), equipMap.get(Integer.valueOf(part))); }
/*     */           
/* 160 */           newPartnerEntity.setEquips(newPartnerEntity.getEquips());
/* 161 */           newPartnerEntity.setTalisman(oldPartnerEntity.getTalisman());
/*     */           
/* 163 */           EquipEntity equipEntity = equipComponent.getEquipEntity(newPartnerEntity.getTalisman());
/* 164 */           if (null != equipEntity) {
/* 165 */             equipEntity.setBelondTo(newPartnerEntity.getPid());
/*     */           }
/* 167 */           for (Iterator<Integer> iterator7 = runeMap.keySet().iterator(); iterator7.hasNext(); ) { int hole = ((Integer)iterator7.next()).intValue();
/* 168 */             newPartnerEntity.getRuneMap().put(Integer.valueOf(hole), runeMap.get(Integer.valueOf(hole)));
/* 169 */             newPartnerEntity.setRuneMap(newPartnerEntity.getRuneMap()); }
/*     */           
/* 171 */           partnerComponent.updateRuneMapDB(newPid);
/* 172 */           partnerComponent.updateEquipsDB(newPid);
/* 173 */           PartnerUtil.pushPartnerInfo(playerSession, PartnerUtil.tranformPartner(newPid, playerComponent, newPartnerEntity));
/* 174 */           PartnerAttrUpdate.refreshPartnerEquip(playerSession, newPid);
/* 175 */           PartnerAttrUpdate.refreshGrowthGoal(playerSession, newPid);
/*     */           
/* 177 */           oldPartnerEntity.setEquips(new HashMap<>());
/* 178 */           for (EquipPart equipPart : EquipPart.values()) {
/* 179 */             oldPartnerEntity.getEquips().put(Integer.valueOf(equipPart.getPart()), Long.valueOf(0L));
/*     */           }
/*     */           
/* 182 */           oldPartnerEntity.setEquips(oldPartnerEntity.getEquips());
/* 183 */           oldPartnerEntity.setTalisman(0L);
/*     */           
/* 185 */           oldPartnerEntity.setRuneMap(new HashMap<>());
/* 186 */           partnerComponent.updateRuneMapDB(oldPid);
/* 187 */           partnerComponent.updateEquipsDB(oldPid);
/* 188 */           PartnerUtil.pushPartnerInfo(playerSession, PartnerUtil.tranformPartner(oldPid, playerComponent, oldPartnerEntity));
/* 189 */           PartnerAttrUpdate.refreshPartnerEquip(playerSession, oldPid);
/* 190 */           PartnerAttrUpdate.refreshGrowthGoal(playerSession, oldPid); }
/*     */       
/*     */       } 
/*     */       Iterator<Long> iterator4;
/* 194 */       for (iterator4 = playerComponent.getFighters().iterator(); iterator4.hasNext(); ) { long pid = ((Long)iterator4.next()).longValue();
/* 195 */         if (pid == -1L || pid == 0L) {
/*     */           continue;
/*     */         }
/* 198 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 199 */         partnerEntity.setStatus(0);
/* 200 */         partnerComponent.updateStatusDB(pid); }
/*     */       
/* 202 */       playerComponent.setFighters(new ArrayList());
/*     */       
/* 204 */       for (iterator4 = batttleList2.iterator(); iterator4.hasNext(); ) { long pid = ((Long)iterator4.next()).longValue();
/* 205 */         if (pid == -1L || pid == 0L) {
/*     */           continue;
/*     */         }
/* 208 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 209 */         partnerEntity.setStatus(1);
/* 210 */         partnerComponent.updateStatusDB(pid); }
/*     */ 
/*     */       
/* 213 */       playerComponent.setFighters(batttleList2);
/* 214 */       playerComponent.setUpdate("fighters");
/* 215 */       playerComponent.saveToDB();
/* 216 */       AttrUpdateUtil.refreshPartner(playerSession);
/* 217 */       AttrUpdateUtil.refreshPartnerEquip(playerSession);
/* 218 */       for (int i = 0; i < playerComponent.getFighters().size(); i++) {
/* 219 */         long id = ((Long)playerComponent.getFighters().get(i)).longValue();
/* 220 */         if (id != 0L && id != -1L)
/*     */         {
/*     */           
/* 223 */           PartnerAttrUpdate.refreshPartnerEquip(playerSession, id); } 
/*     */       } 
/* 225 */       ((BattlePartnerResponse)this.response).pids.addAll(playerComponent.getFighters());
/* 226 */       AttrUpdateUtil.refreshRune(playerSession);
/*     */     }
/* 228 */     else if (request.type == 5) {
/*     */       
/* 230 */       int num = 0;
/* 231 */       for (Iterator<Long> iterator1 = request.pids.iterator(); iterator1.hasNext(); ) { long pid = ((Long)iterator1.next()).longValue();
/* 232 */         if (pid == 0L) {
/*     */           continue;
/*     */         }
/* 235 */         num++; }
/*     */       
/* 237 */       if (num > num2) {
/* 238 */         return 13304;
/*     */       }
/*     */       
/* 241 */       PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/* 242 */       UnparalleledComponent unparalleledComponent = (UnparalleledComponent)playerSession.getPlayer().createIfNotExist(UnparalleledComponent.class);
/*     */       
/* 244 */       ArrayList<Long> batttleList2 = new ArrayList<>();
/* 245 */       for (Iterator<Long> iterator2 = request.pids.iterator(); iterator2.hasNext(); ) { long pid = ((Long)iterator2.next()).longValue();
/* 246 */         if (pid != 0L && batttleList2.indexOf(Long.valueOf(pid)) >= 0) {
/* 247 */           return 13301;
/*     */         }
/* 249 */         if (pid == -1L || pid == 0L) {
/* 250 */           batttleList2.add(Long.valueOf(pid));
/*     */           continue;
/*     */         } 
/* 253 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 254 */         if (null == partnerEntity) {
/* 255 */           return 13303;
/*     */         }
/* 257 */         if (!unparalleledComponent.getPartneredHpMap().isEmpty() && 
/* 258 */           !unparalleledComponent.getPartneredHpMap().containsKey(Long.valueOf(pid))) {
/* 259 */           return 13329;
/*     */         }
/*     */         
/* 262 */         batttleList2.add(Long.valueOf(pid)); }
/*     */ 
/*     */       
/* 265 */       unparalleledComponent.setBattlePartner(batttleList2);
/* 266 */       unparalleledComponent.setUpdate("battlePartner");
/* 267 */       unparalleledComponent.saveToDB();
/* 268 */       ((BattlePartnerResponse)this.response).pids.addAll(unparalleledComponent.getBattlePartner());
/* 269 */     } else if (request.type == 6) {
/* 270 */       SoulsParameter soulsParameter = (SoulsParameter)ParameterConstant.getParameter(55);
/* 271 */       int canBattleNum = 0;
/* 272 */       int reallyCount = 0;
/* 273 */       ArrayList<Integer> souls = new ArrayList<>();
/* 274 */       for (Iterator<Integer> iterator1 = soulsParameter.getLevelOpen().values().iterator(); iterator1.hasNext(); ) { int level = ((Integer)iterator1.next()).intValue();
/* 275 */         if (playerComponent.getLevel() >= level) {
/* 276 */           canBattleNum++;
/*     */         } }
/*     */       
/* 279 */       SoulsComponent soulsComponent = (SoulsComponent)playerSession.getPlayer().createIfNotExist(SoulsComponent.class);
/* 280 */       boolean change = false;
/* 281 */       for (int i = 0; i < 6; i++) {
/* 282 */         long sId = ((Long)request.pids.get(i)).longValue();
/* 283 */         int newSid = ((Integer)playerComponent.getSoulsFighter().get(i)).intValue();
/* 284 */         if ((int)sId != newSid) {
/* 285 */           change = true;
/*     */         }
/* 287 */         if (sId != 0L) {
/*     */ 
/*     */           
/* 290 */           SoulsEntity soulsEntity = soulsComponent.getEntity((int)sId);
/* 291 */           if (null == soulsEntity) {
/* 292 */             return 17309;
/*     */           }
/* 294 */           if (souls.indexOf(Integer.valueOf((int)sId)) >= 0) {
/* 295 */             return 11808;
/*     */           }
/* 297 */           souls.add(Integer.valueOf((int)sId));
/* 298 */           reallyCount++;
/*     */         } 
/* 300 */       }  if (reallyCount > canBattleNum) {
/* 301 */         return 13304;
/*     */       }
/* 303 */       playerComponent.getSoulsFighter().clear();
/* 304 */       for (Iterator<Long> iterator2 = request.pids.iterator(); iterator2.hasNext(); ) { long sId = ((Long)iterator2.next()).longValue();
/* 305 */         playerComponent.getSoulsFighter().add(Integer.valueOf((int)sId)); }
/*     */       
/* 307 */       playerComponent.setSoulsFighter(playerComponent.getSoulsFighter());
/*     */       
/* 309 */       ArrayList<Pair<Integer, Integer>> candidateFighters = new ArrayList<>();
/* 310 */       for (Iterator<Integer> iterator3 = playerComponent.getSoulsFighter().iterator(); iterator3.hasNext(); ) { int id = ((Integer)iterator3.next()).intValue();
/* 311 */         if (id > 0) {
/* 312 */           candidateFighters.add(new Pair(Integer.valueOf(id), Integer.valueOf(soulsComponent.getEntity(id).getStar())));
/*     */         } }
/*     */       
/* 315 */       if (change) {
/* 316 */         Fibers.createExecutorService().submit(() -> CrossUtil.updateCandidateFighters("BattlePartnerProcessor", playerComponent.getPlayerId(), candidateFighters));
/*     */       }
/* 318 */       TaskComponent taskComponent1 = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 319 */       taskComponent1.refreshSchedule(TaskType.TotalBattleSoul, 0, 0L);
/* 320 */       ((BattlePartnerResponse)this.response).pids = request.pids;
/*     */     } 
/* 322 */     ((BattlePartnerResponse)this.response).type = request.type;
/* 323 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 324 */     if (taskComponent != null) {
/* 325 */       taskComponent.refreshSchedule(TaskType.BattleFighter, 0, 0L);
/*     */     }
/* 327 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\BattlePartnerProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */