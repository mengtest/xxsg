/*     */ package com.linlongyx.sanguo.webgame.processors.offices;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.offices.MilitaryOfficeComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MilitaryBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.MilitaryInsType;
/*     */ import com.linlongyx.sanguo.webgame.constant.MilitaryInteriorType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.offices.PushMilitaryHelpResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.HelpInfoData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MilitaryUtil
/*     */ {
/*     */   public static final int TYPE_PRACTICE = 1;
/*     */   public static final int TYPE_INTERIOR = 2;
/*     */   public static final int TYPE_CRUSADE = 3;
/*     */   public static final int TYPE_GOLD = 1;
/*     */   public static final int TYPE_REPHINE = 2;
/*     */   public static final int TYPE_EXP = 3;
/*     */   public static final int TYPE_BREAK = 4;
/*     */   public static final int TYPE_STRENGH = 5;
/*     */   public static final int TYPE_WARPET = 6;
/*     */   
/*     */   public static void getPlayerInfos(ArrayList<HelpInfoData> list, ArrayList<Long> playerIds) {
/*  47 */     int size = playerIds.size();
/*  48 */     if (0 == size) {
/*     */       return;
/*     */     }
/*  51 */     String add = "(";
/*  52 */     int i = 0;
/*  53 */     for (Long id : playerIds) {
/*  54 */       add = add + id + ((i == size - 1) ? "" : ",");
/*  55 */       i++;
/*     */     } 
/*  57 */     add = add + ")";
/*     */ 
/*     */     
/*     */     try {
/*  61 */       MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/*  62 */       JdbcTemplate template = mysql.getTemplate();
/*  63 */       String selectSql = "SELECT distinct p.playerId,p.playerName,p.head,p.sex,p.level,p.totalValue,p.vip,p.quality  FROM tb_player p where p.playerId IN " + add;
/*     */ 
/*     */       
/*  66 */       List<Map<String, Object>> info = template.queryForList(selectSql);
/*  67 */       int j = 0;
/*  68 */       for (Map<String, Object> map : info) {
/*  69 */         HelpInfoData helpInfoData = new HelpInfoData();
/*  70 */         long playerId = ((Long)map.get("playerId")).longValue();
/*  71 */         helpInfoData.playerId = playerId;
/*  72 */         helpInfoData.playerName = (String)map.get("playerName");
/*     */         
/*  74 */         String head = (String)map.get("head");
/*  75 */         helpInfoData.quality = ((Integer)map.get("quality")).intValue();
/*  76 */         helpInfoData.head = head;
/*  77 */         helpInfoData.playerLevel = ((Integer)map.get("level")).intValue();
/*  78 */         list.add(helpInfoData);
/*     */       } 
/*  80 */     } catch (Exception e) {
/*  81 */       LogUtil.errorLog(new Object[] { "MilitaryUtil::getPlayerInfos", Arrays.toString((Object[])e.getStackTrace()) });
/*  82 */       e.printStackTrace();
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
/*     */   public static int[] getRewardResult(ArrayList<Reward> rewards, int insType, long playerId, int rabateValue) {
/*  94 */     int[] value = new int[MilitaryInteriorType.AddiTionEnd.ordinal()];
/*  95 */     Map<Integer, Integer> map = getMilitaryByType(playerId, 2);
/*  96 */     for (Reward reward : rewards) {
/*  97 */       for (Iterator<Integer> iterator = map.keySet().iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/*  98 */         int level = ((Integer)map.get(Integer.valueOf(id))).intValue();
/*  99 */         MilitaryBean militaryBean = (MilitaryBean)JsonTableService.getJsonData(id, MilitaryBean.class);
/* 100 */         if (militaryBean != null) {
/* 101 */           MilitaryBean.LevelBean militaryBeanl = (MilitaryBean.LevelBean)militaryBean.getLevel().get(Integer.valueOf(level));
/* 102 */           if (insType == MilitaryInsType.ThreadIns.getType()) {
/* 103 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 104 */               if (interiorSpecialBean.getType() == insType)
/* 105 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 107 */           }  if (insType == MilitaryInsType.ArenaIns.getType()) {
/* 108 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 109 */               if (interiorSpecialBean.getType() == insType)
/* 110 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 112 */           }  if (insType == MilitaryInsType.TeamIns.getType()) {
/* 113 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 114 */               if (interiorSpecialBean.getType() == insType)
/* 115 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 117 */           }  if (insType == MilitaryInsType.DestinyIns.getType()) {
/* 118 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 119 */               if (interiorSpecialBean.getType() == insType)
/* 120 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 122 */           }  if (insType == MilitaryInsType.SingleBoss.getType()) {
/* 123 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 124 */               if (interiorSpecialBean.getType() == insType)
/* 125 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 127 */           }  if (insType == MilitaryInsType.RankBoss.getType()) {
/* 128 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 129 */               if (interiorSpecialBean.getType() == insType)
/* 130 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 132 */           }  if (insType == MilitaryInsType.WorldBoss.getType()) {
/* 133 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 134 */               if (interiorSpecialBean.getType() == insType)
/* 135 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 137 */           }  if (insType == MilitaryInsType.GroupBoss.getType()) {
/* 138 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 139 */               if (interiorSpecialBean.getType() == insType)
/* 140 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 142 */           }  if (insType == MilitaryInsType.Rabit.getType()) {
/* 143 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 144 */               if (interiorSpecialBean.getType() == insType)
/* 145 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 147 */           }  if (insType == MilitaryInsType.TowerIns.getType()) {
/* 148 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 149 */               if (interiorSpecialBean.getType() == insType)
/* 150 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 152 */           }  if (insType == MilitaryInsType.GoldIns.getType()) {
/* 153 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 154 */               if (interiorSpecialBean.getType() == insType)
/* 155 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 157 */           }  if (insType == MilitaryInsType.RefineStoneIns.getType()) {
/* 158 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 159 */               if (interiorSpecialBean.getType() == insType)
/* 160 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 162 */           }  if (insType == MilitaryInsType.ExpBookIns.getType()) {
/* 163 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 164 */               if (interiorSpecialBean.getType() == insType)
/* 165 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 167 */           }  if (insType == MilitaryInsType.BreakStoneIns.getType()) {
/* 168 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 169 */               if (interiorSpecialBean.getType() == insType)
/* 170 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 172 */           }  if (insType == MilitaryInsType.TetureIns.getType()) {
/* 173 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 174 */               if (interiorSpecialBean.getType() == insType)
/* 175 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 177 */           }  if (insType == MilitaryInsType.PetFoodIns.getType()) {
/* 178 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 179 */               if (interiorSpecialBean.getType() == insType)
/* 180 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 182 */           }  if (insType == MilitaryInsType.GeneralIns.getType()) {
/* 183 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 184 */               if (interiorSpecialBean.getType() == insType)
/* 185 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 187 */           }  if (insType == MilitaryInsType.UnparaledIns.getType()) {
/* 188 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 189 */               if (interiorSpecialBean.getType() == insType)
/* 190 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue); 
/*     */             }  continue;
/* 192 */           }  if (insType == MilitaryInsType.SLG.getType())
/* 193 */             for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : militaryBeanl.getInteriorSpecial()) {
/* 194 */               if (interiorSpecialBean.getType() == insType) {
/* 195 */                 calculationReward(interiorSpecialBean, reward, value, rabateValue);
/*     */               }
/*     */             }  
/*     */         }  }
/*     */     
/*     */     } 
/* 201 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void calculationReward(MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean, Reward reward, int[] value, int rate) {
/* 212 */     int itemId = reward.id;
/* 213 */     if (interiorSpecialBean.getAttrId() == MilitaryInteriorType.Gold.getType() && itemId == MilitaryInteriorType.Gold.getItemId()) {
/* 214 */       rewardResults(reward, MilitaryInteriorType.Gold, interiorSpecialBean, value, rate);
/* 215 */     } else if (interiorSpecialBean.getAttrId() == MilitaryInteriorType.Exp.getType() && itemId == MilitaryInteriorType.Exp.getItemId()) {
/* 216 */       rewardResults(reward, MilitaryInteriorType.Exp, interiorSpecialBean, value, rate);
/* 217 */     } else if (interiorSpecialBean.getAttrId() == MilitaryInteriorType.ArenaPoint.getType() && itemId == MilitaryInteriorType.ArenaPoint.getItemId()) {
/* 218 */       rewardResults(reward, MilitaryInteriorType.ArenaPoint, interiorSpecialBean, value, rate);
/* 219 */     } else if (interiorSpecialBean.getAttrId() == MilitaryInteriorType.Prestige.getType() && itemId == MilitaryInteriorType.Prestige.getItemId()) {
/* 220 */       rewardResults(reward, MilitaryInteriorType.Prestige, interiorSpecialBean, value, rate);
/* 221 */     } else if (interiorSpecialBean.getAttrId() == MilitaryInteriorType.Soul.getType() && itemId == MilitaryInteriorType.Soul.getItemId()) {
/* 222 */       rewardResults(reward, MilitaryInteriorType.Soul, interiorSpecialBean, value, rate);
/* 223 */     } else if (interiorSpecialBean.getAttrId() == MilitaryInteriorType.FirstExpBook.getType() && itemId == MilitaryInteriorType.FirstExpBook.getItemId()) {
/* 224 */       rewardResults(reward, MilitaryInteriorType.FirstExpBook, interiorSpecialBean, value, rate);
/* 225 */     } else if (interiorSpecialBean.getAttrId() == MilitaryInteriorType.SecondExpBook.getType() && itemId == MilitaryInteriorType.SecondExpBook.getItemId()) {
/* 226 */       rewardResults(reward, MilitaryInteriorType.SecondExpBook, interiorSpecialBean, value, rate);
/* 227 */     } else if (interiorSpecialBean.getAttrId() == MilitaryInteriorType.ThirdExpBook.getType() && itemId == MilitaryInteriorType.ThirdExpBook.getItemId()) {
/* 228 */       rewardResults(reward, MilitaryInteriorType.ThirdExpBook, interiorSpecialBean, value, rate);
/* 229 */     } else if (interiorSpecialBean.getAttrId() == MilitaryInteriorType.RefineStone.getType() && itemId == MilitaryInteriorType.RefineStone.getItemId()) {
/* 230 */       rewardResults(reward, MilitaryInteriorType.RefineStone, interiorSpecialBean, value, rate);
/* 231 */     } else if (interiorSpecialBean.getAttrId() == MilitaryInteriorType.BreakStone.getType() && itemId == MilitaryInteriorType.BreakStone.getItemId()) {
/* 232 */       rewardResults(reward, MilitaryInteriorType.BreakStone, interiorSpecialBean, value, rate);
/* 233 */     } else if (interiorSpecialBean.getAttrId() == MilitaryInteriorType.BreakCream.getType() && itemId == MilitaryInteriorType.BreakCream.getItemId()) {
/* 234 */       rewardResults(reward, MilitaryInteriorType.BreakCream, interiorSpecialBean, value, rate);
/* 235 */     } else if (interiorSpecialBean.getAttrId() == MilitaryInteriorType.TetureStenghStone.getType() && itemId == MilitaryInteriorType.TetureStenghStone.getItemId()) {
/* 236 */       rewardResults(reward, MilitaryInteriorType.TetureStenghStone, interiorSpecialBean, value, rate);
/* 237 */     } else if (interiorSpecialBean.getAttrId() == MilitaryInteriorType.FirstPetFood.getType() && itemId == MilitaryInteriorType.FirstPetFood.getItemId()) {
/* 238 */       rewardResults(reward, MilitaryInteriorType.FirstPetFood, interiorSpecialBean, value, rate);
/* 239 */     } else if (interiorSpecialBean.getAttrId() == MilitaryInteriorType.SecondPetFood.getType() && itemId == MilitaryInteriorType.SecondPetFood.getItemId()) {
/* 240 */       rewardResults(reward, MilitaryInteriorType.SecondPetFood, interiorSpecialBean, value, rate);
/* 241 */     } else if (interiorSpecialBean.getAttrId() == MilitaryInteriorType.ThirdPetFood.getType() && itemId == MilitaryInteriorType.ThirdPetFood.getItemId()) {
/* 242 */       rewardResults(reward, MilitaryInteriorType.ThirdPetFood, interiorSpecialBean, value, rate);
/* 243 */     } else if (interiorSpecialBean.getAttrId() == MilitaryInteriorType.DestinyStone.getType() && itemId == MilitaryInteriorType.DestinyStone.getItemId()) {
/* 244 */       rewardResults(reward, MilitaryInteriorType.DestinyStone, interiorSpecialBean, value, rate);
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
/*     */   public static void rewardResults(Reward reward, MilitaryInteriorType type, MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean, int[] value, int rate) {
/* 258 */     if (type.getAddType() == 1) {
/* 259 */       if (type.getType() == MilitaryInteriorType.Exp.getType()) {
/* 260 */         reward.num = (long)(reward.num * (1.0D + (interiorSpecialBean.getNum() + rate) / 10000.0D));
/* 261 */         value[type.ordinal()] = interiorSpecialBean.getNum() + rate;
/*     */       } else {
/* 263 */         reward.num = (long)(reward.num * (1.0D + interiorSpecialBean.getNum() / 10000.0D));
/* 264 */         value[type.ordinal()] = interiorSpecialBean.getNum();
/*     */       } 
/*     */     } else {
/* 267 */       reward.num += interiorSpecialBean.getNum();
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
/*     */   public static int[] DailyPoint(ArrayList<Reward> arrayList, int dailyIns, IPlayerSession playerSession, int rate) {
/* 280 */     int[] value = new int[MilitaryInteriorType.AddiTionEnd.ordinal()];
/*     */     
/* 282 */     if (dailyIns == 1) {
/* 283 */       value = getRewardResult(arrayList, MilitaryInsType.GoldIns.getType(), playerSession.getPlayer().getPlayerId(), 0);
/* 284 */     } else if (dailyIns == 2) {
/* 285 */       value = getRewardResult(arrayList, MilitaryInsType.RefineStoneIns.getType(), playerSession.getPlayer().getPlayerId(), 0);
/* 286 */     } else if (dailyIns == 4) {
/* 287 */       value = getRewardResult(arrayList, MilitaryInsType.BreakStoneIns.getType(), playerSession.getPlayer().getPlayerId(), 0);
/* 288 */     } else if (dailyIns == 5) {
/* 289 */       value = getRewardResult(arrayList, MilitaryInsType.TetureIns.getType(), playerSession.getPlayer().getPlayerId(), 0);
/* 290 */     } else if (dailyIns == 6) {
/* 291 */       value = getRewardResult(arrayList, MilitaryInsType.PetFoodIns.getType(), playerSession.getPlayer().getPlayerId(), 0);
/* 292 */     } else if (dailyIns == 3) {
/* 293 */       value = getRewardResult(arrayList, MilitaryInsType.ExpBookIns.getType(), playerSession.getPlayer().getPlayerId(), 0);
/*     */     } 
/* 295 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<Integer, Integer> getMilitaryByType(long playerId, int type) {
/* 305 */     HashMap<Integer, Integer> typeMap = new HashMap<>();
/* 306 */     MilitaryOfficeComponent militaryOfficeComponent = (MilitaryOfficeComponent)LookUpService.getComponent(playerId, MilitaryOfficeComponent.class);
/* 307 */     if (null == militaryOfficeComponent) {
/* 308 */       return typeMap;
/*     */     }
/* 310 */     Set<Integer> set = (Set<Integer>)militaryOfficeComponent.getTypeMap().get(Integer.valueOf(type));
/* 311 */     if (null != set) {
/* 312 */       for (Iterator<Integer> iterator = set.iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 313 */         if (militaryOfficeComponent.getOfficeList().get(Integer.valueOf(id)) != null) {
/* 314 */           typeMap.put(Integer.valueOf(id), (Integer)militaryOfficeComponent.getOfficeList().get(Integer.valueOf(id)));
/*     */         } }
/*     */     
/*     */     }
/* 318 */     return typeMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<Integer, Integer> additionRabit(long playerId) {
/* 327 */     Map<Integer, Integer> additionMap = new HashMap<>();
/* 328 */     Map<Integer, Integer> map = getMilitaryByType(playerId, 2);
/* 329 */     for (Iterator<Integer> iterator = map.keySet().iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 330 */       MilitaryBean militaryBean = (MilitaryBean)JsonTableService.getJsonData(id, MilitaryBean.class);
/* 331 */       if (null != militaryBean) {
/* 332 */         MilitaryBean.LevelBean levelBean = (MilitaryBean.LevelBean)militaryBean.getLevel().get(map.get(Integer.valueOf(id)));
/* 333 */         for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : levelBean.getInteriorSpecial()) {
/* 334 */           if (interiorSpecialBean.getType() == MilitaryInsType.Rabit.getType() && interiorSpecialBean.getAttrId() == MilitaryInteriorType.Gold.getType()) {
/* 335 */             additionMap.put(Integer.valueOf(MilitaryInteriorType.Gold.getItemId()), Integer.valueOf(((Integer)additionMap.getOrDefault(Integer.valueOf(MilitaryInteriorType.Gold.getItemId()), Integer.valueOf(0))).intValue() + interiorSpecialBean.getNum())); continue;
/* 336 */           }  if (interiorSpecialBean.getType() == MilitaryInsType.Rabit.getType() && interiorSpecialBean.getAttrId() == MilitaryInteriorType.Exp.getType()) {
/* 337 */             additionMap.put(Integer.valueOf(MilitaryInteriorType.Exp.getItemId()), Integer.valueOf(((Integer)additionMap.getOrDefault(Integer.valueOf(MilitaryInteriorType.Exp.getItemId()), Integer.valueOf(0))).intValue() + interiorSpecialBean.getNum()));
/*     */           }
/*     */         } 
/*     */       }  }
/*     */     
/* 342 */     return additionMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long[] getTempAttrbute(long playerId, int insType) {
/* 349 */     long[] attrs = new long[AttributeType.ATTRIB_TYPE_END.getType()];
/* 350 */     Map<Integer, Integer> map = getMilitaryByType(playerId, 3);
/* 351 */     for (Iterator<Integer> iterator = map.keySet().iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 352 */       MilitaryBean militaryBean = (MilitaryBean)JsonTableService.getJsonData(id, MilitaryBean.class);
/* 353 */       if (militaryBean != null) {
/* 354 */         MilitaryBean.LevelBean levelBean = (MilitaryBean.LevelBean)militaryBean.getLevel().get(map.get(Integer.valueOf(id)));
/* 355 */         for (MilitaryBean.LevelBean.CrusadeSpecialBean crusadeSpecialBean : levelBean.getCrusadeSpecial()) {
/* 356 */           if (crusadeSpecialBean.getType() == insType && crusadeSpecialBean.getAttrId() < 2000) {
/* 357 */             int attrId = crusadeSpecialBean.getAttrId() % 100;
/* 358 */             attrs[attrId] = attrs[attrId] + crusadeSpecialBean.getNum();
/*     */           } 
/*     */         } 
/*     */       }  }
/*     */     
/* 363 */     return attrs;
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
/*     */   public static int getTime(long playerId, int type, int time) {
/* 375 */     int value = 0;
/* 376 */     Map<Integer, Integer> map = getMilitaryByType(playerId, 2);
/* 377 */     for (Iterator<Integer> iterator = map.keySet().iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 378 */       MilitaryBean militaryBean = (MilitaryBean)JsonTableService.getJsonData(id, MilitaryBean.class);
/* 379 */       if (null != militaryBean) {
/* 380 */         MilitaryBean.LevelBean levelBean = (MilitaryBean.LevelBean)militaryBean.getLevel().get(map.get(Integer.valueOf(id)));
/* 381 */         for (MilitaryBean.LevelBean.InteriorSpecialBean interiorSpecialBean : levelBean.getInteriorSpecial()) {
/* 382 */           if (MilitaryInsType.GeneralIns.getType() == type && interiorSpecialBean.getAttrId() == MilitaryInteriorType.TimeRefuse.getType()) {
/* 383 */             value += interiorSpecialBean.getNum(); continue;
/* 384 */           }  if (MilitaryInsType.MilitaryHelp.getType() == type && interiorSpecialBean.getAttrId() == MilitaryInteriorType.TimeAdd.getType()) {
/* 385 */             value += interiorSpecialBean.getNum(); continue;
/* 386 */           }  if (MilitaryInsType.MilitaryHelp.getType() == type && interiorSpecialBean.getAttrId() == MilitaryInteriorType.TimeAddPercentage.getType()) {
/* 387 */             value = (int)(value + time * interiorSpecialBean.getNum() / 10000.0D);
/*     */           }
/*     */         } 
/*     */       }  }
/*     */     
/* 392 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void pushHelp(GroupEntity groupEntity, ArrayList<KeyValue> keyValues) {
/* 402 */     PushMilitaryHelpResponse response = new PushMilitaryHelpResponse();
/* 403 */     response.playerIds = keyValues;
/* 404 */     LookUpService.campBoradcast((ResponseBase)response, groupEntity);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\offices\MilitaryUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */