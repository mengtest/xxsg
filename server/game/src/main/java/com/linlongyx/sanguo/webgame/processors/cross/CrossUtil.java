/*      */ package com.linlongyx.sanguo.webgame.processors.cross;
/*      */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.mounts.MountsComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.souls.SoulsComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupEntity;
/*      */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*      */ import com.linlongyx.sanguo.webgame.common.fight.fighter.CommonFighterData;
/*      */ import com.linlongyx.sanguo.webgame.common.structure.ODTime;
/*      */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*      */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*      */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*      */ import com.linlongyx.sanguo.webgame.processors.destiny.DestinyUtil;
/*      */ import com.linlongyx.sanguo.webgame.processors.player.VersionUtil;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyBetRecord;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ModelData;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RunewarPlayerData;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WxPlayerInfo;
/*      */ import com.linlongyx.sanguo.webgame.rmi.ICrossRmi;
/*      */ import com.linlongyx.sanguo.webgame.rmi.RmiManager;
/*      */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
/*      */ import com.linlongyx.sanguo.webgame.rmi.data.MatchState;
/*      */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*      */ import com.linlongyx.sanguo.webgame.rmi.data.RacePlayerData;
/*      */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*      */ import com.linlongyx.sanguo.webgame.service.rank.RankingLevel;
/*      */ import java.rmi.RemoteException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ 
/*      */ public class CrossUtil {
/*   41 */   public static Map<Long, Pair<String, Integer>> playerTokenMap = new HashMap<>();
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CROSS_BATTLE_REWARD_TYPE = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean needInit(String logstr) {
/*      */     try {
/*   52 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*   53 */       if (null != crossRmi) {
/*   54 */         if (crossRmi.needInit(Integer.parseInt(MContext.getServerId()))) {
/*   55 */           ((RmiManager)MContext.getBean("rmiManager")).registerLogicRmi();
/*      */         }
/*      */       } else {
/*   58 */         ((RmiManager)MContext.getBean("rmiManager")).init();
/*      */       } 
/*   60 */       return false;
/*   61 */     } catch (RemoteException e) {
/*      */       
/*   63 */       LogUtil.errorLog(new Object[] { logstr, "needInit:RemoteException" });
/*   64 */       return true;
/*   65 */     } catch (Exception e1) {
/*      */       
/*   67 */       LogUtil.errorLog(new Object[] { logstr, "needInit:Exception" });
/*   68 */       return true;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static boolean heartBeat(int serverId, int worldLevel) {
/*      */     try {
/*   74 */       if (needInit("needInit"))
/*   75 */         return false; 
/*   76 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*   77 */       if (null != crossRmi) {
/*   78 */         return crossRmi.heartBeat(serverId, worldLevel);
/*      */       }
/*   80 */     } catch (RemoteException e) {
/*   81 */       e.printStackTrace();
/*   82 */       LogUtil.errorLog(new Object[] { Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*   84 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ArrayList<DestinyPlayerData> destinyRecomment(String logstr, DestinyPlayerData myplayerData, HashSet<Long> battles, int recommentSize) {
/*      */     try {
/*   96 */       if (needInit("needInit"))
/*   97 */         return null; 
/*   98 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*   99 */       if (null != crossRmi) {
/*  100 */         return crossRmi.destinyRecomment(myplayerData, battles, recommentSize);
/*      */       }
/*  102 */     } catch (RemoteException e) {
/*  103 */       e.printStackTrace();
/*  104 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  106 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updateDestinyPlayerData(String logstr, DestinyPlayerData destinyPlayerData) {
/*      */     try {
/*  117 */       if (null == destinyPlayerData)
/*      */         return; 
/*  119 */       if (needInit("needInit"))
/*      */         return; 
/*  121 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  122 */       if (null != crossRmi) {
/*  123 */         crossRmi.updateDestinyPlayerData(destinyPlayerData);
/*      */       }
/*  125 */     } catch (RemoteException e) {
/*  126 */       e.printStackTrace();
/*  127 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void updateTalismanMap(String logstr, long playerId, HashMap<Long, HashMap<Integer, Integer>> talismanMap) {
/*      */     try {
/*  133 */       if (needInit("needInit"))
/*      */         return; 
/*  135 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  136 */       if (null != crossRmi) {
/*  137 */         crossRmi.updateTalismanMap(MContext.getServerIdInt(), playerId, talismanMap);
/*      */       }
/*  139 */     } catch (RemoteException e) {
/*  140 */       e.printStackTrace();
/*  141 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ArrayList<DestinyPlayerData> getDestinyPlayerData(String logstr, HashSet<Long> playerId) {
/*      */     try {
/*  154 */       if (needInit("needInit"))
/*  155 */         return null; 
/*  156 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  157 */       if (null != crossRmi) {
/*  158 */         return crossRmi.getDestinyPlayerData(MContext.getServerIdInt(), playerId);
/*      */       }
/*  160 */     } catch (RemoteException e) {
/*  161 */       e.printStackTrace();
/*  162 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  164 */     return null;
/*      */   }
/*      */   
/*      */   public static DestinyRankData getDestinyRankData(String logstr, long playerId) {
/*      */     try {
/*  169 */       if (needInit("needInit"))
/*  170 */         return null; 
/*  171 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  172 */       if (null != crossRmi) {
/*  173 */         return crossRmi.getDestinyRankData(MContext.getServerIdInt(), playerId);
/*      */       }
/*  175 */     } catch (RemoteException e) {
/*  176 */       e.printStackTrace();
/*  177 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  179 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int addDestinyPoint(String logstr, int serverId, long playerId, int destinyPoint) {
/*      */     try {
/*  192 */       if (needInit("needInit"))
/*  193 */         return -1; 
/*  194 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  195 */       if (null != crossRmi) {
/*  196 */         return crossRmi.addDestinyPoint(serverId, playerId, destinyPoint);
/*      */       }
/*  198 */     } catch (RemoteException e) {
/*  199 */       e.printStackTrace();
/*  200 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  202 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void addDestinyDef(String logstr, long playerId, int type, int time, int robNum, boolean win, long pkPlayerId, String pkPlayerName, Reward reward) {
/*      */     try {
/*  213 */       if (needInit("needInit"))
/*      */         return; 
/*  215 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  216 */       if (null != crossRmi) {
/*  217 */         crossRmi.addDestinyDef(MContext.getServerIdInt(), playerId, type, time, robNum, win, pkPlayerId, pkPlayerName, reward);
/*      */       }
/*  219 */     } catch (RemoteException e) {
/*  220 */       e.printStackTrace();
/*  221 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int clearCrossPlayerData(String logstr) {
/*      */     try {
/*  235 */       if (needInit("needInit"))
/*  236 */         return -1; 
/*  237 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  238 */       if (null != crossRmi) {
/*  239 */         crossRmi.clearCrossPlayerData(MContext.getServerIdInt());
/*      */       }
/*  241 */     } catch (RemoteException e) {
/*  242 */       e.printStackTrace();
/*  243 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  245 */     return -1;
/*      */   }
/*      */   
/*      */   public static int getZoneId(String logstr) {
/*      */     try {
/*  250 */       if (needInit("needInit"))
/*  251 */         return 0; 
/*  252 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  253 */       if (null != crossRmi) {
/*  254 */         return crossRmi.getZoneId(MContext.getServerIdInt());
/*      */       }
/*  256 */     } catch (RemoteException e) {
/*  257 */       e.printStackTrace();
/*  258 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  260 */     return 0;
/*      */   }
/*      */   
/*      */   public static MatchState getCurrentMatchState(String logstr) {
/*      */     try {
/*  265 */       if (needInit("needInit"))
/*  266 */         return null; 
/*  267 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  268 */       if (null != crossRmi) {
/*  269 */         return crossRmi.getCurrentMatchState(MContext.getServerIdInt());
/*      */       }
/*  271 */     } catch (RemoteException e) {
/*  272 */       e.printStackTrace();
/*  273 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  275 */     return null;
/*      */   }
/*      */   
/*      */   public static void getPkDataItems(String logstr, int type) {
/*      */     try {
/*  280 */       if (needInit("needInit"))
/*      */         return; 
/*  282 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  283 */       if (null != crossRmi) {
/*  284 */         DestinyUtil.groupPkDataMap.putAll(crossRmi.getPkDataItems(MContext.getServerIdInt(), type));
/*      */       }
/*  286 */     } catch (RemoteException e) {
/*  287 */       e.printStackTrace();
/*  288 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*      */   }
/*      */   
/*      */   public static HashMap<Long, Integer> getBetNum(String logstr, String pkId) {
/*      */     try {
/*  294 */       if (needInit("needInit"))
/*  295 */         return null; 
/*  296 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  297 */       if (null != crossRmi) {
/*  298 */         return crossRmi.getBetNum(MContext.getServerIdInt(), pkId);
/*      */       }
/*  300 */     } catch (RemoteException e) {
/*  301 */       e.printStackTrace();
/*  302 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  304 */     return null;
/*      */   }
/*      */   
/*      */   public static int addPlayerBetNum(String logstr, String pkId, long playerId, long targetPlayerId, int betNum, int type) {
/*      */     try {
/*  309 */       if (needInit("needInit"))
/*  310 */         return -1; 
/*  311 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  312 */       if (null != crossRmi) {
/*  313 */         return crossRmi.addPlayerBetNum(MContext.getServerIdInt(), pkId, playerId, targetPlayerId, betNum, type);
/*      */       }
/*  315 */     } catch (RemoteException e) {
/*  316 */       e.printStackTrace();
/*  317 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  319 */     return -1;
/*      */   }
/*      */   
/*      */   public static void gmMatch(String logstr, int state) {
/*      */     try {
/*  324 */       if (needInit("needInit"))
/*      */         return; 
/*  326 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  327 */       if (null != crossRmi) {
/*  328 */         crossRmi.gmMatch(MContext.getServerIdInt(), state);
/*      */       }
/*  330 */     } catch (RemoteException e) {
/*  331 */       e.printStackTrace();
/*  332 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void gmBattle(String logstr, int state) {
/*      */     try {
/*  339 */       if (needInit("needInit"))
/*      */         return; 
/*  341 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  342 */       if (null != crossRmi) {
/*  343 */         crossRmi.gmBattle(MContext.getServerIdInt(), state);
/*      */       }
/*  345 */     } catch (RemoteException e) {
/*  346 */       e.printStackTrace();
/*  347 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isBattleOpen(String logstr) {
/*      */     try {
/*  354 */       if (needInit("needInit"))
/*  355 */         return false; 
/*  356 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  357 */       if (null != crossRmi) {
/*  358 */         return crossRmi.isBattleOpen();
/*      */       }
/*  360 */     } catch (RemoteException e) {
/*  361 */       e.printStackTrace();
/*  362 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  364 */     return false;
/*      */   }
/*      */   
/*      */   public static ArrayList<DestinyBetRecord> getPlayerBetRecord(String logstr, long playerId) {
/*      */     try {
/*  369 */       if (needInit("needInit"))
/*  370 */         return new ArrayList<>(); 
/*  371 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  372 */       if (null != crossRmi) {
/*  373 */         return crossRmi.getPlayerBetRecord(MContext.getServerIdInt(), playerId);
/*      */       }
/*  375 */     } catch (RemoteException e) {
/*  376 */       e.printStackTrace();
/*  377 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  379 */     return new ArrayList<>();
/*      */   }
/*      */   
/*      */   public static PkRecord getPkRecord(String logstr, String pkId) {
/*      */     try {
/*  384 */       if (needInit("needInit"))
/*  385 */         return null; 
/*  386 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  387 */       if (null != crossRmi) {
/*  388 */         return crossRmi.getPkRecord(MContext.getServerIdInt(), pkId);
/*      */       }
/*  390 */     } catch (RemoteException e) {
/*  391 */       e.printStackTrace();
/*  392 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  394 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean hasMatch(String logstr, long playerId) {
/*      */     try {
/*  406 */       if (needInit("needInit"))
/*  407 */         return false; 
/*  408 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  409 */       if (null != crossRmi) {
/*  410 */         return crossRmi.hasMatch(MContext.getServerIdInt(), playerId);
/*      */       }
/*  412 */     } catch (RemoteException e) {
/*  413 */       e.printStackTrace();
/*  414 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  416 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ArrayList<Long> getCurBetPkPlayers(String logstr) {
/*      */     try {
/*  427 */       if (needInit("needInit"))
/*  428 */         return null; 
/*  429 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  430 */       if (null != crossRmi) {
/*  431 */         return crossRmi.getCurBetPkPlayers(MContext.getServerIdInt());
/*      */       }
/*  433 */     } catch (RemoteException e) {
/*  434 */       e.printStackTrace();
/*  435 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  437 */     return null;
/*      */   }
/*      */   
/*      */   public static PkDataItem getCurPkDataItem(String logstr) {
/*      */     try {
/*  442 */       if (needInit("needInit"))
/*  443 */         return null; 
/*  444 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  445 */       if (null != crossRmi) {
/*  446 */         return crossRmi.getCurPkDataItem(MContext.getServerIdInt());
/*      */       }
/*  448 */     } catch (RemoteException e) {
/*  449 */       e.printStackTrace();
/*  450 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  452 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static DestinyPlayerData buildLocalDestinyPlayerData(long playerId) {
/*      */     try {
/*  458 */       PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/*  459 */       if (null == playerComponent) {
/*  460 */         return null;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  465 */       DestinyPlayerData destinyPlayerData = new DestinyPlayerData();
/*  466 */       destinyPlayerData.setUserId(playerComponent.getUserId());
/*  467 */       destinyPlayerData.setServerId(Integer.parseInt(MContext.getServerId()));
/*  468 */       destinyPlayerData.setPlayerId(playerId);
/*  469 */       destinyPlayerData.setPlayerName(VersionUtil.getCrossPlayerName(playerId, playerComponent.getPlayerName()));
/*  470 */       destinyPlayerData.setLevel(playerComponent.getLevel());
/*  471 */       destinyPlayerData.setSex(playerComponent.getSex());
/*  472 */       destinyPlayerData.setHead(PlayerUtil.getHeadUrl(playerId));
/*  473 */       destinyPlayerData.setNickname(playerComponent.getPlayerName());
/*  474 */       destinyPlayerData.setVip((short)playerComponent.getVip());
/*  475 */       destinyPlayerData.setFightValue(playerComponent.getTotalValue());
/*  476 */       destinyPlayerData.setModelData(buildModelData(playerId));
/*  477 */       destinyPlayerData.setQuality(playerComponent.getQuality());
/*  478 */       destinyPlayerData.setFirstHandVal((int)playerComponent.getFirstHand());
/*  479 */       destinyPlayerData.setDestinyPoint(0);
/*      */ 
/*      */ 
/*      */       
/*  483 */       Map<Integer, CommonFighterData> fighters = new HashMap<>();
/*  484 */       int pos = 0;
/*  485 */       for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  486 */         pos++;
/*  487 */         if (pid == -1L || pid > 0L) {
/*  488 */           CommonFighterData commonFighterData = FightUtil.getFighterData(pid, playerId, (pid == -1L) ? 0 : 1);
/*  489 */           if (commonFighterData != null) {
/*  490 */             fighters.put(Integer.valueOf(pos), commonFighterData);
/*      */           }
/*      */         }  }
/*      */ 
/*      */ 
/*      */       
/*  496 */       List<Pair<Integer, Integer>> candidateFighterList = new ArrayList<>();
/*  497 */       SoulsComponent soulsComponent = (SoulsComponent)LookUpService.getComponent(playerId, SoulsComponent.class);
/*  498 */       if (soulsComponent != null) {
/*  499 */         for (Iterator<Integer> iterator1 = playerComponent.getSoulsFighter().iterator(); iterator1.hasNext(); ) { int id = ((Integer)iterator1.next()).intValue();
/*  500 */           if (id > 0) {
/*  501 */             candidateFighterList.add(new Pair(Integer.valueOf(id), Integer.valueOf(soulsComponent.getEntity(id).getStar())));
/*      */           } }
/*      */       
/*      */       }
/*  505 */       destinyPlayerData.setCandidateFighters(candidateFighterList);
/*      */ 
/*      */       
/*  508 */       WarLineupComponent warLineupComponent = (WarLineupComponent)LookUpService.getComponent(playerId, WarLineupComponent.class);
/*  509 */       if (warLineupComponent != null) {
/*  510 */         WarLineupEntity entity = warLineupComponent.getBattleWarLineup();
/*  511 */         if (entity != null) {
/*  512 */           destinyPlayerData.setZhenfa(new Pair(Integer.valueOf(entity.getWarLineupId()), Integer.valueOf(entity.getStar())));
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  517 */       CommonFighterData petFighterData = FightUtil.getFighterData(-1L, playerId, (byte)3);
/*  518 */       if (petFighterData != null) {
/*  519 */         fighters.put(Integer.valueOf(0), petFighterData);
/*      */       }
/*      */       
/*  522 */       CommonFighterData stageFighterData = FightUtil.getFighterData(-1L, playerId, (byte)4);
/*  523 */       if (stageFighterData != null) {
/*  524 */         fighters.put(Integer.valueOf(10), stageFighterData);
/*      */       }
/*  526 */       destinyPlayerData.setFighters(fighters);
/*      */       
/*  528 */       return destinyPlayerData;
/*  529 */     } catch (Exception e) {
/*  530 */       LogUtil.errorLog(new Object[] { Arrays.toString((Object[])e.getStackTrace()) });
/*      */       
/*  532 */       return null;
/*      */     } 
/*      */   }
/*      */   public static PlayerData buildLocalPlayerData(long playerId) {
/*      */     try {
/*  537 */       PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/*  538 */       if (null == playerComponent) {
/*  539 */         return null;
/*      */       }
/*  541 */       PlayerData playerData = new PlayerData();
/*  542 */       playerData.setPlayerId(playerId);
/*  543 */       playerData.setUserId(playerComponent.getUserId());
/*  544 */       playerData.setPlayerName(VersionUtil.getCrossPlayerName(playerId, playerComponent.getPlayerName()));
/*  545 */       playerData.setLevel(playerComponent.getLevel());
/*  546 */       playerData.setSex(playerComponent.getSex());
/*  547 */       playerData.setHead(playerComponent.getHead());
/*  548 */       playerData.setVip(playerComponent.getVip());
/*  549 */       playerData.setTitleId(playerComponent.getWearTitle());
/*  550 */       playerData.setFightValue(playerComponent.getFightValue());
/*  551 */       playerData.setTotalValue(playerComponent.getTotalValue());
/*  552 */       playerData.setModelData(buildModelData(playerId));
/*  553 */       playerData.setServerId(MContext.getServerIdInt());
/*  554 */       playerData.setQuality(playerComponent.getQuality());
/*  555 */       playerData.setFirstHandVal((int)playerComponent.getFirstHand());
/*  556 */       playerData.setStatus(playerComponent.getStatus());
/*  557 */       GroupEntity groupEntity = GroupUtil.getCurGroup(playerId);
/*  558 */       if (groupEntity != null) {
/*  559 */         playerData.setBlocId(groupEntity.getId());
/*  560 */         playerData.setBlocName(VersionUtil.getCrossBlocName(playerId, groupEntity.getGroupName()));
/*      */       } 
/*      */       
/*  563 */       Map<Integer, CommonFighterData> fighters = new HashMap<>();
/*  564 */       int pos = 0;
/*  565 */       for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  566 */         pos++;
/*  567 */         if (pid == -1L || pid > 0L) {
/*  568 */           CommonFighterData commonFighterData = FightUtil.getFighterData(pid, playerId, (pid == -1L) ? 0 : 1);
/*  569 */           if (commonFighterData != null) {
/*  570 */             fighters.put(Integer.valueOf(pos), commonFighterData);
/*      */           }
/*      */         }  }
/*      */ 
/*      */ 
/*      */       
/*  576 */       List<Pair<Integer, Integer>> candidateFighterList = new ArrayList<>();
/*  577 */       SoulsComponent soulsComponent = (SoulsComponent)LookUpService.getComponent(playerId, SoulsComponent.class);
/*  578 */       if (soulsComponent != null) {
/*  579 */         for (Iterator<Integer> iterator1 = playerComponent.getSoulsFighter().iterator(); iterator1.hasNext(); ) { int id = ((Integer)iterator1.next()).intValue();
/*  580 */           if (id > 0) {
/*  581 */             candidateFighterList.add(new Pair(Integer.valueOf(id), Integer.valueOf(soulsComponent.getEntity(id).getStar())));
/*      */           } }
/*      */       
/*      */       }
/*  585 */       playerData.setCandidateFighters(candidateFighterList);
/*      */ 
/*      */       
/*  588 */       WarLineupComponent warLineupComponent = (WarLineupComponent)LookUpService.getComponent(playerId, WarLineupComponent.class);
/*  589 */       if (warLineupComponent != null) {
/*  590 */         WarLineupEntity entity = warLineupComponent.getBattleWarLineup();
/*  591 */         if (entity != null) {
/*  592 */           playerData.setZhenfa(new Pair(Integer.valueOf(entity.getWarLineupId()), Integer.valueOf(entity.getStar())));
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  597 */       CommonFighterData petFighterData = FightUtil.getFighterData(-1L, playerId, (byte)3);
/*  598 */       if (petFighterData != null) {
/*  599 */         fighters.put(Integer.valueOf(0), petFighterData);
/*      */       }
/*      */       
/*  602 */       CommonFighterData stageFighterData = FightUtil.getFighterData(-1L, playerId, (byte)4);
/*  603 */       if (stageFighterData != null) {
/*  604 */         fighters.put(Integer.valueOf(10), stageFighterData);
/*      */       }
/*  606 */       playerData.setFighters(fighters);
/*      */       
/*  608 */       return playerData;
/*  609 */     } catch (Exception e) {
/*  610 */       LogUtil.errorLog(new Object[] { Arrays.toString((Object[])e.getStackTrace()) });
/*      */       
/*  612 */       return null;
/*      */     } 
/*      */   }
/*      */   private static ModelData buildModelData(long playerId) {
/*  616 */     PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/*  617 */     if (null == playerComponent) {
/*  618 */       return null;
/*      */     }
/*  620 */     MountsComponent mountsComponent = (MountsComponent)LookUpService.getComponent(playerId, MountsComponent.class);
/*  621 */     ModelData modelData = new ModelData();
/*  622 */     modelData.playerId = playerId;
/*  623 */     modelData.sex = playerComponent.getSex();
/*  624 */     modelData.title = playerComponent.getWearTitle();
/*  625 */     modelData.fashion = playerComponent.getWearFashion();
/*  626 */     modelData.nickname = playerComponent.getPlayerName();
/*  627 */     if (mountsComponent != null) {
/*  628 */       modelData.mounts = mountsComponent.getTurnMounts();
/*      */     }
/*  630 */     return modelData;
/*      */   }
/*      */   
/*      */   public static void updateCurWxPlayerInfo(String logstr, int serverId, WxPlayerInfo wxPlayerInfo, long playerId) {
/*      */     try {
/*  635 */       if (needInit("needInit"))
/*      */         return; 
/*  637 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  638 */       if (null != crossRmi) {
/*  639 */         crossRmi.updateWxFriendInfo(serverId, wxPlayerInfo, playerId);
/*      */       }
/*  641 */     } catch (Exception e) {
/*  642 */       e.printStackTrace();
/*  643 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void uploadPlayerRankData(String logstr, int actId, int rankType, int limit, boolean isDesc, int condition, RankingData data) {
/*      */     try {
/*  650 */       if (needInit("needInit"))
/*      */         return; 
/*  652 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  653 */       if (null != crossRmi) {
/*  654 */         crossRmi.uploadPlayerRankData(actId, rankType, limit, isDesc, condition, MContext.getServerIdInt(), data);
/*      */       }
/*  656 */     } catch (Exception e) {
/*  657 */       e.printStackTrace();
/*  658 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void addRankServer(String logstr, int actId, int rankType, int limit, boolean isDesc, int condition) {
/*      */     try {
/*  665 */       if (needInit("needInit"))
/*      */         return; 
/*  667 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  668 */       if (null != crossRmi) {
/*  669 */         crossRmi.addRankServer(actId, rankType, limit, isDesc, condition, MContext.getServerIdInt());
/*      */       }
/*  671 */     } catch (Exception e) {
/*  672 */       e.printStackTrace();
/*  673 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static ArrayList<RankingData> getRankList(String logstr, int actId, boolean isClose) {
/*      */     try {
/*  680 */       if (needInit("needInit"))
/*  681 */         return new ArrayList<>(); 
/*  682 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  683 */       if (null != crossRmi) {
/*  684 */         return crossRmi.getRankList(actId, isClose);
/*      */       }
/*  686 */     } catch (Exception e) {
/*  687 */       e.printStackTrace();
/*  688 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  690 */     return new ArrayList<>();
/*      */   }
/*      */   
/*      */   public static void updatePlayerStatus(String logstr, long playerId, HashMap<Integer, ODTime> status) {
/*      */     try {
/*  695 */       if (needInit("needInit"))
/*      */         return; 
/*  697 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  698 */       if (null != crossRmi) {
/*  699 */         crossRmi.updatePlayerStatus(playerId, status);
/*      */       }
/*  701 */     } catch (Exception e) {
/*  702 */       e.printStackTrace();
/*  703 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int updatePlayerRacePoint(String logstr, long playerId, int point) {
/*      */     try {
/*  716 */       if (needInit("needInit"))
/*  717 */         return -1; 
/*  718 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  719 */       if (null != crossRmi) {
/*  720 */         return crossRmi.updatePlayerRacePoint(MContext.getServerIdInt(), playerId, point);
/*      */       }
/*  722 */     } catch (Exception e) {
/*  723 */       e.printStackTrace();
/*  724 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  726 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static RacePlayerData recommentOne(String logstr, long playerId, long totalFightValue, int point) {
/*      */     try {
/*  740 */       if (needInit("needInit"))
/*  741 */         return null; 
/*  742 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  743 */       if (null != crossRmi) {
/*  744 */         return crossRmi.recommentOne(MContext.getServerIdInt(), playerId, totalFightValue, point);
/*      */       }
/*  746 */     } catch (Exception e) {
/*  747 */       e.printStackTrace();
/*  748 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  750 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ArrayList<RankingData> getRaceRankList(String logstr, int pageSize) {
/*      */     try {
/*  761 */       if (needInit("needInit"))
/*  762 */         return new ArrayList<>(); 
/*  763 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  764 */       if (null != crossRmi) {
/*  765 */         return crossRmi.getRaceRankList(MContext.getServerIdInt(), pageSize);
/*      */       }
/*  767 */     } catch (Exception e) {
/*  768 */       e.printStackTrace();
/*  769 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  771 */     return new ArrayList<>();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static RankingData getPlayerRaceRank(String logstr, long playerId, int curPoint) {
/*      */     try {
/*  783 */       if (needInit("needInit"))
/*  784 */         return null; 
/*  785 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  786 */       if (null != crossRmi) {
/*  787 */         return crossRmi.getPlayerRaceRank(MContext.getServerIdInt(), playerId, curPoint);
/*      */       }
/*  789 */     } catch (Exception e) {
/*  790 */       e.printStackTrace();
/*  791 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  793 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getCurRaceId(String logstr) {
/*      */     try {
/*  803 */       if (needInit("needInit"))
/*  804 */         return 0; 
/*  805 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  806 */       if (null != crossRmi) {
/*  807 */         return crossRmi.getCurRaceId();
/*      */       }
/*  809 */     } catch (Exception e) {
/*  810 */       e.printStackTrace();
/*  811 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  813 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int newRace(String logstr) {
/*      */     try {
/*  823 */       if (needInit("needInit"))
/*  824 */         return 0; 
/*  825 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  826 */       if (null != crossRmi) {
/*  827 */         return crossRmi.newRace();
/*      */       }
/*  829 */     } catch (Exception e) {
/*  830 */       e.printStackTrace();
/*  831 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  833 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isRaceOpen(String logstr) {
/*      */     try {
/*  843 */       if (needInit("needInit"))
/*  844 */         return false; 
/*  845 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  846 */       if (null != crossRmi) {
/*  847 */         return crossRmi.isRaceOpen();
/*      */       }
/*  849 */     } catch (Exception e) {
/*  850 */       e.printStackTrace();
/*  851 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  853 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void playerJoinRace(String logstr, PlayerData playerData) {
/*      */     try {
/*  863 */       if (needInit("needInit"))
/*      */         return; 
/*  865 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  866 */       if (null != crossRmi) {
/*  867 */         crossRmi.playerJoinRace(RankingLevel.getWorldLevel(), playerData);
/*      */       }
/*  869 */     } catch (Exception e) {
/*  870 */       e.printStackTrace();
/*  871 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getRaceState(String logstr) {
/*      */     try {
/*  882 */       if (needInit("needInit"))
/*  883 */         return 0; 
/*  884 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  885 */       if (null != crossRmi) {
/*  886 */         return crossRmi.getRaceState();
/*      */       }
/*  888 */     } catch (Exception e) {
/*  889 */       e.printStackTrace();
/*  890 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  892 */     return 0;
/*      */   }
/*      */   
/*      */   public static void updateCandidateFighters(String logstr, long playerId, ArrayList<Pair<Integer, Integer>> candidateFighters) {
/*      */     try {
/*  897 */       if (needInit("needInit"))
/*      */         return; 
/*  899 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  900 */       if (null != crossRmi) {
/*  901 */         crossRmi.updateCandidateFighters(MContext.getServerIdInt(), playerId, candidateFighters);
/*      */       }
/*  903 */     } catch (Exception e) {
/*  904 */       e.printStackTrace();
/*  905 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void updateZhenfa(String logstr, long playerId, Pair<Integer, Integer> zhenfa) {
/*      */     try {
/*  911 */       if (needInit("needInit"))
/*      */         return; 
/*  913 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  914 */       if (null != crossRmi) {
/*  915 */         crossRmi.updateZhenfa(MContext.getServerIdInt(), playerId, zhenfa);
/*      */       }
/*  917 */     } catch (Exception e) {
/*  918 */       e.printStackTrace();
/*  919 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*      */   }
/*      */   
/*      */   public static List<RankingData> getTowerData(String logstr, PlayerData playerData, int curTowerId) {
/*      */     try {
/*  925 */       if (needInit("needInit"))
/*  926 */         return new ArrayList<>(); 
/*  927 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  928 */       if (null != crossRmi) {
/*  929 */         return crossRmi.getTowerData(playerData, curTowerId);
/*      */       }
/*  931 */     } catch (Exception e) {
/*  932 */       e.printStackTrace();
/*  933 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  935 */     return new ArrayList<>();
/*      */   }
/*      */   
/*      */   public static Pair<Integer, ArrayList<RankingData>> getTowerRankList(String logstr, long playerId) {
/*      */     try {
/*  940 */       if (needInit("needInit"))
/*  941 */         return new Pair(Integer.valueOf(0), null); 
/*  942 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  943 */       if (null != crossRmi) {
/*  944 */         return crossRmi.getTowerRankList(MContext.getServerIdInt(), playerId);
/*      */       }
/*  946 */     } catch (Exception e) {
/*  947 */       e.printStackTrace();
/*  948 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  950 */     return new Pair(Integer.valueOf(0), null);
/*      */   }
/*      */   
/*      */   public static Pair<RankingData, Pair<Integer, PlayerData>> fightTowerLayer(String logstr, long playerId, int layerId) {
/*      */     try {
/*  955 */       if (needInit("needInit"))
/*  956 */         return new Pair(null, new Pair(Integer.valueOf(0), null)); 
/*  957 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  958 */       if (null != crossRmi) {
/*  959 */         return crossRmi.fightTowerLayer(MContext.getServerIdInt(), playerId, layerId);
/*      */       }
/*  961 */     } catch (Exception e) {
/*  962 */       e.printStackTrace();
/*  963 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  965 */     return new Pair(null, new Pair(Integer.valueOf(0), null));
/*      */   }
/*      */   
/*      */   public static boolean fightTowerResult(String logstr, long playerId, long targetPlayerId, int layerId, boolean win) {
/*      */     try {
/*  970 */       if (needInit("needInit"))
/*  971 */         return false; 
/*  972 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  973 */       if (null != crossRmi) {
/*  974 */         return crossRmi.fightTowerResult(MContext.getServerIdInt(), playerId, targetPlayerId, layerId, win);
/*      */       }
/*  976 */     } catch (Exception e) {
/*  977 */       e.printStackTrace();
/*  978 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  980 */     return false;
/*      */   }
/*      */   
/*      */   public static Pair<Integer, RankingData> towerLayerState(String logstr, long playerId, int layerId) {
/*      */     try {
/*  985 */       if (needInit("needInit"))
/*  986 */         return new Pair(Integer.valueOf(0), null); 
/*  987 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/*  988 */       if (null != crossRmi) {
/*  989 */         return crossRmi.towerLayerState(MContext.getServerIdInt(), playerId, layerId);
/*      */       }
/*  991 */     } catch (Exception e) {
/*  992 */       e.printStackTrace();
/*  993 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*  995 */     return new Pair(Integer.valueOf(0), null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void addTowerDefRecord(String logstr, int layerId, long playerId, int type, int time, boolean win, long pkPlayerId, String pkPlayerName) {
/*      */     try {
/* 1011 */       if (needInit("needInit"))
/*      */         return; 
/* 1013 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/* 1014 */       if (null != crossRmi) {
/* 1015 */         crossRmi.addTowerDefRecord(MContext.getServerIdInt(), layerId, playerId, type, time, win, pkPlayerId, pkPlayerName);
/*      */       }
/* 1017 */     } catch (Exception e) {
/* 1018 */       e.printStackTrace();
/* 1019 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*      */   }
/*      */   
/*      */   public static boolean giveupTowerLayer(String logstr, long playerId, int layerId) {
/*      */     try {
/* 1025 */       if (needInit("needInit"))
/* 1026 */         return false; 
/* 1027 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/* 1028 */       if (null != crossRmi) {
/* 1029 */         return crossRmi.giveupTowerLayer(MContext.getServerIdInt(), playerId, layerId);
/*      */       }
/* 1031 */     } catch (Exception e) {
/* 1032 */       e.printStackTrace();
/* 1033 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/* 1035 */     return false;
/*      */   }
/*      */   
/*      */   public static void runewarPlayerJoin(String logstr, PlayerData playerData) {
/*      */     try {
/* 1040 */       if (needInit("needInit"))
/*      */         return; 
/* 1042 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/* 1043 */       if (null != crossRmi) {
/* 1044 */         crossRmi.runewarPlayerJoin(playerData);
/*      */       }
/* 1046 */     } catch (Exception e) {
/* 1047 */       e.printStackTrace();
/* 1048 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/*      */   }
/*      */   
/*      */   public static List<RunewarPlayerData> getRunewardPlayerData(String logstr, long playerId) {
/*      */     try {
/* 1054 */       if (needInit("needInit"))
/* 1055 */         return new ArrayList<>(); 
/* 1056 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/* 1057 */       if (null != crossRmi) {
/* 1058 */         return crossRmi.getRunewardPlayerData(MContext.getServerIdInt(), playerId);
/*      */       }
/* 1060 */     } catch (Exception e) {
/* 1061 */       e.printStackTrace();
/* 1062 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/* 1064 */     return new ArrayList<>();
/*      */   }
/*      */   
/*      */   public static RunewarFightersData getRunewarFighterData(String logstr, long playerId, long targetPlayerId) {
/*      */     try {
/* 1069 */       if (needInit("needInit"))
/* 1070 */         return null; 
/* 1071 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/* 1072 */       if (null != crossRmi) {
/* 1073 */         return crossRmi.getRunewarFighterData(MContext.getServerIdInt(), playerId, targetPlayerId);
/*      */       }
/* 1075 */     } catch (Exception e) {
/* 1076 */       e.printStackTrace();
/* 1077 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/* 1079 */     return null;
/*      */   }
/*      */   
/*      */   public static List<RankingData> getRunewarRankList(String logstr) {
/*      */     try {
/* 1084 */       if (needInit("needInit"))
/* 1085 */         return new ArrayList<>(); 
/* 1086 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/* 1087 */       if (null != crossRmi) {
/* 1088 */         return crossRmi.getRunewarRankList(MContext.getServerIdInt());
/*      */       }
/* 1090 */     } catch (Exception e) {
/* 1091 */       e.printStackTrace();
/* 1092 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/* 1094 */     return new ArrayList<>();
/*      */   }
/*      */   
/*      */   public static PlayerData getRunewarTargetPlayerdata(String logstr, long playerId, long targetPlayerId) {
/*      */     try {
/* 1099 */       if (needInit("needInit"))
/* 1100 */         return null; 
/* 1101 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/* 1102 */       if (null != crossRmi) {
/* 1103 */         return crossRmi.getRunewarTargetPlayerdata(MContext.getServerIdInt(), playerId, targetPlayerId);
/*      */       }
/* 1105 */     } catch (Exception e) {
/* 1106 */       e.printStackTrace();
/* 1107 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/* 1109 */     return null;
/*      */   }
/*      */   
/*      */   public static int runewarFightResult(String logstr, long playerId, long targetPlayerId, int targetServerId, int point, boolean win, BattleRecordData record) {
/*      */     try {
/* 1114 */       if (needInit("needInit"))
/* 1115 */         return 0; 
/* 1116 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/* 1117 */       if (null != crossRmi) {
/* 1118 */         return crossRmi.runewarFightResult(MContext.getServerIdInt(), playerId, targetPlayerId, targetServerId, point, win, record);
/*      */       }
/* 1120 */     } catch (Exception e) {
/* 1121 */       e.printStackTrace();
/* 1122 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/* 1124 */     return 0;
/*      */   }
/*      */   
/*      */   public static boolean isRunewarOpen(String logstr) {
/*      */     try {
/* 1129 */       if (needInit("needInit"))
/* 1130 */         return false; 
/* 1131 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/* 1132 */       if (null != crossRmi) {
/* 1133 */         return crossRmi.isRunewarOpen();
/*      */       }
/* 1135 */     } catch (Exception e) {
/* 1136 */       e.printStackTrace();
/* 1137 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/* 1139 */     return false;
/*      */   }
/*      */   
/*      */   public static List<RunewarPlayerData> refreshPlayerCoordinate(String logstr, long playerId) {
/*      */     try {
/* 1144 */       if (needInit("needInit"))
/* 1145 */         return null; 
/* 1146 */       ICrossRmi crossRmi = RmiManager.getICrossRmi();
/* 1147 */       if (null != crossRmi) {
/* 1148 */         return crossRmi.refreshPlayerCoordinate(MContext.getServerIdInt(), playerId);
/*      */       }
/* 1150 */     } catch (Exception e) {
/* 1151 */       e.printStackTrace();
/* 1152 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*      */     } 
/* 1154 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getRankStr(int type) {
/* 1159 */     int rank = 1 << type - 1;
/* 1160 */     switch (rank)
/*      */     { case 1:
/* 1162 */         rankStr = "冠军";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1170 */         return rankStr;case 2: rankStr = "亚军"; return rankStr; }  String rankStr = rank + "强"; return rankStr;
/*      */   }
/*      */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\cross\CrossUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */