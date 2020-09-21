/*     */ package com.linlongyx.sanguo.webgame.processors.group;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.BlocLevelBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.BlocSkillBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.GroupParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GroupData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GroupMemberData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GroupTipData;
/*     */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GroupUtil
/*     */ {
/*     */   public static final int SACRIFICE_TYPE0 = 0;
/*     */   public static final int SACRIFICE_TYPE1 = 1;
/*     */   public static final int SACRIFICE_TYPE2 = 2;
/*     */   public static final int SACRIFICE_TYPE3 = 3;
/*     */   public static final int POSITION_NOT_MEMBER = 0;
/*     */   public static final int POSITION_LEADER = 1;
/*     */   public static final int POSITION_VICE = 2;
/*     */   public static final int POSITION_MEMBER = 3;
/*     */   public static final int GROUP_SKILL_PARAM = 1000;
/*     */   
/*     */   public static GroupData getGroupData(GroupEntity groupEntity) {
/*  53 */     GroupData groupData = new GroupData();
/*  54 */     groupData.id = groupEntity.getId();
/*  55 */     groupData.groupName = groupEntity.getGroupName();
/*  56 */     groupData.leader = groupEntity.getLeader();
/*  57 */     groupData.leaderName = groupEntity.getLeaderName();
/*  58 */     groupData.level = groupEntity.getLevel();
/*  59 */     groupData.size = groupEntity.getMemberList().size();
/*  60 */     groupData.rank = groupEntity.getRank();
/*  61 */     groupData.notice = groupEntity.getNotice();
/*  62 */     groupData.exp = groupEntity.getExp();
/*  63 */     groupData.dayExp = groupEntity.getDayExp();
/*  64 */     return groupData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static GroupTipData getGroupTipData(GroupEntity groupEntity, long playerId) {
/*  75 */     GroupTipData groupTipData = new GroupTipData();
/*  76 */     groupTipData.id = groupEntity.getId();
/*  77 */     groupTipData.groupName = groupEntity.getGroupName();
/*  78 */     groupTipData.level = groupEntity.getLevel();
/*  79 */     groupTipData.size = groupEntity.getMemberList().size();
/*  80 */     groupTipData.rank = groupEntity.getRank();
/*  81 */     groupTipData.exp = groupEntity.getExp();
/*  82 */     groupTipData.isApplyed = groupEntity.getApplyList().contains(Long.valueOf(playerId));
/*  83 */     groupTipData.mayApply = (groupEntity.getMemberList().size() < getPersonNum(groupEntity.getLevel()));
/*  84 */     return groupTipData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getPersonNum(int level) {
/*  94 */     BlocLevelBean blocLevelBean = (BlocLevelBean)JsonTableService.getJsonData(level, BlocLevelBean.class);
/*  95 */     if (null == blocLevelBean) {
/*  96 */       return 0;
/*     */     }
/*  98 */     return blocLevelBean.getPersonNum();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getApplyNum(int level) {
/* 108 */     BlocLevelBean blocLevelBean = (BlocLevelBean)JsonTableService.getJsonData(level, BlocLevelBean.class);
/* 109 */     if (null == blocLevelBean) {
/* 110 */       return 0;
/*     */     }
/* 112 */     return blocLevelBean.getApplyNum();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized Set<Long> getMemberList(ArrayList<GroupMemberData> list, List<Long> memberList) {
/* 122 */     Set<Long> playerIds = new HashSet<>();
/* 123 */     int size = memberList.size();
/* 124 */     if (0 == size) {
/* 125 */       return playerIds;
/*     */     }
/* 127 */     String add = "(";
/* 128 */     int i = 0;
/* 129 */     for (Long id : memberList) {
/* 130 */       add = add + id + ((i == size - 1) ? "" : ",");
/* 131 */       i++;
/*     */     } 
/* 133 */     add = add + ")";
/*     */ 
/*     */     
/*     */     try {
/* 137 */       MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/* 138 */       JdbcTemplate template = mysql.getTemplate();
/* 139 */       String selectSql = "SELECT distinct p.playerId,p.playerName,p.head,p.sex,p.level,p.loginOutTime,p.totalValue,p.vip,m.position,m.totalOffer  FROM tb_player p, tb_groupMember m where p.playerId = m.playerId and p.playerId IN " + add;
/*     */ 
/*     */       
/* 142 */       List<Map<String, Object>> info = template.queryForList(selectSql);
/* 143 */       for (Map<String, Object> map : info) {
/* 144 */         GroupMemberData groupMemberData = new GroupMemberData();
/* 145 */         long playerId = ((Long)map.get("playerId")).longValue();
/* 146 */         groupMemberData.playerId = playerId;
/* 147 */         groupMemberData.vip = ((Integer)map.get("vip")).intValue();
/* 148 */         groupMemberData.playerName = (String)map.get("playerName");
/* 149 */         groupMemberData.head = (String)map.get("head");
/* 150 */         groupMemberData.level = ((Integer)map.get("level")).intValue();
/* 151 */         groupMemberData.lastTime = LookUpService.isOnline(playerId) ? 0 : ((Integer)map.get("loginOutTime")).intValue();
/* 152 */         groupMemberData.position = ((Integer)map.get("position")).intValue();
/* 153 */         IPlayer player = LookUpService.getByPlayerId(playerId);
/* 154 */         if (null != player) {
/* 155 */           PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 156 */           groupMemberData.fightValue = playerComponent.getTotalValue();
/*     */         } else {
/* 158 */           groupMemberData.fightValue = ((Long)map.get("totalValue")).longValue();
/*     */         } 
/* 160 */         groupMemberData.donate = ((Long)map.get("totalOffer")).longValue();
/* 161 */         playerIds.add(Long.valueOf(playerId));
/* 162 */         list.add(groupMemberData);
/*     */       } 
/* 164 */     } catch (Exception e) {
/* 165 */       LogUtil.errorLog(new Object[] { "GroupUtil::getMemberList", Arrays.toString((Object[])e.getStackTrace()) });
/* 166 */       e.printStackTrace();
/*     */     } 
/* 168 */     return playerIds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void clearApply(GroupMemberComponent groupMemberComponent) {
/* 177 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 178 */     Set<Long> applySet = groupMemberComponent.getApplySet();
/* 179 */     long playerId = groupMemberComponent.getPlayerId();
/* 180 */     if (!applySet.isEmpty()) {
/* 181 */       for (Iterator<Long> iterator = applySet.iterator(); iterator.hasNext(); ) { long id = ((Long)iterator.next()).longValue();
/* 182 */         GroupEntity groupEntity = groupService.getGroupEntity(id);
/* 183 */         groupService.removeApply(groupEntity, playerId); }
/*     */       
/* 185 */       applySet.clear();
/* 186 */       groupMemberComponent.setApplySet(applySet);
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
/*     */   public static BlocSkillBean getBlocSkillBean(int index, int level) {
/* 198 */     int id = index * 1000 + level;
/* 199 */     BlocSkillBean blocSkillBean = (BlocSkillBean)JsonTableService.getJsonData(id, BlocSkillBean.class);
/* 200 */     return blocSkillBean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void checkAutoTransfer() {
/*     */     try {
/* 209 */       MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/* 210 */       JdbcTemplate template = mysql.getTemplate();
/* 211 */       GroupParameter groupParameter = (GroupParameter)ParameterConstant.getParameter(11);
/* 212 */       int time = TimeUtil.currentTime() - groupParameter.getAutoDay() * 86400;
/* 213 */       String selectSql = "SELECT distinct b.id FROM tb_player p, tb_group b  where p.playerId = b.leader and p.loginOutTime > 0 and p.loginOutTime < " + time;
/*     */       
/* 215 */       List<Map<String, Object>> info = template.queryForList(selectSql);
/* 216 */       for (Map<String, Object> map : info) {
/* 217 */         Long id = (Long)map.get("id");
/* 218 */         transferLeader(id);
/*     */       } 
/* 220 */     } catch (Exception e) {
/* 221 */       LogUtil.errorLog(new Object[] { "GroupUtil::checkAutoTransfer", Arrays.toString((Object[])e.getStackTrace()) });
/* 222 */       e.getStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void transferLeader(Long id) {
/* 232 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 233 */     GroupEntity groupEntity = groupService.getGroupEntity(id.longValue());
/* 234 */     if (null == groupEntity) {
/*     */       return;
/*     */     }
/* 237 */     Set<Long> memberIds = groupEntity.getMemberList();
/* 238 */     if (memberIds.size() > 0) {
/* 239 */       ArrayList<GroupMemberData> list = new ArrayList<>();
/* 240 */       getMemberList(list, new ArrayList<>(memberIds));
/* 241 */       Set<Long> removes = new HashSet<>();
/* 242 */       for (Long playerId : memberIds) {
/* 243 */         GroupMemberComponent groupMemberComponent = (GroupMemberComponent)LookUpService.getComponent(playerId.longValue(), GroupMemberComponent.class);
/* 244 */         if (null != groupMemberComponent) {
/* 245 */           if (groupMemberComponent.getId() != id.longValue()) {
/* 246 */             removes.add(Long.valueOf(groupMemberComponent.getPlayerId())); continue;
/*     */           } 
/* 248 */           if (groupMemberComponent.getPosition() == 1 && groupMemberComponent.getPlayerId() != groupEntity.getLeader()) {
/* 249 */             groupMemberComponent.setPosition(3);
/* 250 */             groupMemberComponent.maybeSaveToDB();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 255 */       if (!removes.isEmpty()) {
/* 256 */         for (Long playerId : removes) {
/* 257 */           groupService.removeMember(groupEntity, playerId.longValue());
/*     */         }
/*     */       }
/*     */       
/* 261 */       long newLeader = getNewLeader(list);
/* 262 */       if (newLeader > 0L) {
/* 263 */         Long oldLeader = Long.valueOf(groupEntity.getLeader());
/* 264 */         PlayerComponent oldPlayerComponent = LookUpService.getPlayerComponent(oldLeader.longValue());
/* 265 */         PlayerComponent newPlayerComponent = LookUpService.getPlayerComponent(newLeader);
/* 266 */         GroupMemberComponent oldBlocMemberComponent = (GroupMemberComponent)LookUpService.getComponent(oldLeader.longValue(), GroupMemberComponent.class);
/* 267 */         GroupMemberComponent newBlocMemberComponent = (GroupMemberComponent)LookUpService.getComponent(newLeader, GroupMemberComponent.class);
/* 268 */         if (null != newPlayerComponent && null != oldPlayerComponent && null != oldBlocMemberComponent && null != newBlocMemberComponent) {
/* 269 */           if (newLeader == oldPlayerComponent.getPlayerId()) {
/*     */             return;
/*     */           }
/* 272 */           if (newBlocMemberComponent.getPosition() == 2) {
/* 273 */             groupService.removeVice(groupEntity, newLeader);
/*     */           }
/* 275 */           groupService.changeLeader(groupEntity, newLeader, newPlayerComponent.getPlayerName());
/* 276 */           newBlocMemberComponent.setPosition(1);
/* 277 */           oldBlocMemberComponent.setPosition(3);
/* 278 */           groupService.updateLeader(id.longValue());
/* 279 */           groupService.updateLeaderName(id.longValue());
/* 280 */           newBlocMemberComponent.saveToDB();
/* 281 */           oldBlocMemberComponent.saveToDB();
/*     */ 
/*     */           
/* 284 */           String title = LanguageConstant.getLanguage(1101);
/* 285 */           String content = LanguageConstant.getLanguage(1102);
/* 286 */           for (Long playerId : memberIds) {
/* 287 */             MailUtil.sendSysMail(playerId.longValue(), new ArrayList(), title, content);
/*     */           }
/*     */           
/* 290 */           LogUtils.errorLog(new Object[] { "transferLeader:", " loginOutTime:", Integer.valueOf(oldPlayerComponent.getLoginOutTime()) });
/* 291 */           LogUtils.errorLog(new Object[] { "transferLeader:", " from:", Long.valueOf(oldPlayerComponent.getPlayerId()), " to", Long.valueOf(newPlayerComponent.getPlayerId()) });
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static long getNewLeader(ArrayList<GroupMemberData> list) {
/* 304 */     long newLeader = 0L;
/* 305 */     int time = 0;
/* 306 */     ArrayList<GroupMemberData> temp = new ArrayList<>();
/* 307 */     for (GroupMemberData groupMemberData : list) {
/*     */       
/* 309 */       if (groupMemberData.lastTime > time) {
/* 310 */         time = groupMemberData.lastTime;
/* 311 */         newLeader = groupMemberData.playerId;
/*     */       } 
/*     */       
/* 314 */       if (0 == groupMemberData.lastTime) {
/* 315 */         temp.add(groupMemberData);
/*     */       }
/*     */     } 
/*     */     
/* 319 */     if (temp.size() > 0) {
/* 320 */       long fight = 0L;
/* 321 */       for (GroupMemberData groupMemberData : temp) {
/* 322 */         if (groupMemberData.fightValue > fight) {
/* 323 */           fight = groupMemberData.fightValue;
/* 324 */           newLeader = groupMemberData.playerId;
/*     */         } 
/*     */       } 
/*     */     } 
/* 328 */     return newLeader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getGroupLevel(long playerId) {
/* 338 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)LookUpService.getComponent(playerId, GroupMemberComponent.class);
/* 339 */     if (null == groupMemberComponent || groupMemberComponent.getId() == 0L) {
/* 340 */       return 0;
/*     */     }
/* 342 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 343 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/* 344 */     if (null == groupEntity) {
/* 345 */       return 0;
/*     */     }
/* 347 */     return groupEntity.getLevel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getGroupName(long playerId) {
/* 357 */     String name = "无";
/* 358 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)LookUpService.getComponent(playerId, GroupMemberComponent.class);
/* 359 */     if (null == groupMemberComponent || groupMemberComponent.getId() == 0L) {
/* 360 */       return name;
/*     */     }
/* 362 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 363 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/* 364 */     if (null == groupEntity) {
/* 365 */       return name;
/*     */     }
/* 367 */     return groupEntity.getGroupName();
/*     */   }
/*     */   
/*     */   public static GroupEntity getCurGroup(IPlayer player) {
/* 371 */     return getCurGroup(player.getPlayerId());
/*     */   }
/*     */   
/*     */   public static GroupEntity getCurGroup(long playerId) {
/* 375 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)LookUpService.getComponent(playerId, GroupMemberComponent.class);
/* 376 */     if (groupMemberComponent == null)
/* 377 */       return null; 
/* 378 */     GroupMemberEntity memberEntity = (GroupMemberEntity)groupMemberComponent.getEntity();
/*     */     
/* 380 */     if (memberEntity == null || memberEntity.getId() == 0L) {
/* 381 */       return null;
/*     */     }
/* 383 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 384 */     GroupEntity groupEntity = groupService.getGroupEntity(memberEntity.getId());
/* 385 */     if (null != groupEntity) {
/* 386 */       return groupEntity;
/*     */     }
/* 388 */     return null;
/*     */   }
/*     */   
/*     */   public static GroupEntity getGroupEntity(long groupId) {
/* 392 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 393 */     GroupEntity groupEntity = groupService.getGroupEntity(groupId);
/* 394 */     return groupEntity;
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
/*     */   public static boolean autoJoin(GroupEntity groupEntity, long playerId, GroupService groupService, IPlayerSession playerSession) {
/* 406 */     if (groupEntity.getAutoAdd() == 1) {
/* 407 */       return true;
/*     */     }
/* 409 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 410 */     if (0L != groupMemberComponent.getId()) {
/* 411 */       return false;
/*     */     }
/* 413 */     int size = getPersonNum(groupEntity.getLevel());
/* 414 */     if (groupEntity.getMemberList().size() < size) {
/* 415 */       GroupMemberComponent memberComponent = (GroupMemberComponent)LookUpService.getComponent(playerId, GroupMemberComponent.class);
/* 416 */       if (null != memberComponent) {
/* 417 */         if (memberComponent.getId() == 0L) {
/* 418 */           memberComponent.setId(groupEntity.getId());
/* 419 */           memberComponent.setPosition(3);
/* 420 */           memberComponent.setTotalOffer(0L);
/* 421 */           clearApply(memberComponent);
/* 422 */           memberComponent.saveToDB();
/*     */         } 
/* 424 */         groupService.removeApply(groupEntity, playerSession.getPlayer().getPlayerId());
/* 425 */         groupService.addMember(groupEntity, playerSession.getPlayer().getPlayerId());
/*     */ 
/*     */         
/* 428 */         PlayerUtil.sendRedNotice(Long.valueOf(playerId), RedNoticeConstant.GroupDeal);
/* 429 */         LogUtils.errorLog(new Object[] { "GroupDeal", Long.valueOf(groupEntity.getId()), Long.valueOf(playerSession.getPlayer().getPlayerId()), Long.valueOf(playerId) });
/*     */       } 
/*     */     } 
/* 432 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */