/*     */ package com.linlongyx.sanguo.webgame.service;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*     */ import com.linlongyx.core.framework.dao.proxy.MapProxy;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.framework.service.IBussinessService;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.BlocLevelBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.group.GroupBossUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.group.GroupComparator;
/*     */ import com.linlongyx.sanguo.webgame.processors.group.GroupUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatByChannelResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ChatInfo;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GroupTipData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ public class GroupService implements IBussinessService {
/*  38 */   private MapProxy mapProxy = new MapProxy(GroupEntity.class);
/*  39 */   private Map<String, Long> nameToGroupId = new HashMap<>();
/*  40 */   private List<GroupEntity> groupData = new ArrayList<>();
/*  41 */   private static final Object LOCK = new Object();
/*     */ 
/*     */   
/*     */   public void initFromDB() {
/*  45 */     Table prefix = (Table)this.mapProxy.getEntityClass().getAnnotation(Table.class);
/*  46 */     String name = prefix.prefix();
/*  47 */     String dbKey = name.replace("%serverId", AppContext.getServerId());
/*  48 */     this.mapProxy.setEntityKeyId(dbKey);
/*  49 */     this.mapProxy.get();
/*     */     
/*  51 */     Map<String, IMapEntity> map = this.mapProxy.getEntityMap();
/*  52 */     synchronized (LOCK) {
/*  53 */       for (Map.Entry<String, IMapEntity> entry : map.entrySet()) {
/*  54 */         GroupEntity groupEntity = (GroupEntity)entry.getValue();
/*  55 */         this.groupData.add(groupEntity);
/*  56 */         this.nameToGroupId.put(groupEntity.getGroupName(), Long.valueOf(groupEntity.getId()));
/*     */       } 
/*     */     } 
/*  59 */     sort();
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveToDB() {
/*  64 */     GroupBossUtil.saveBossData();
/*  65 */     this.mapProxy.save();
/*     */   }
/*     */   
/*     */   public void repairGroupId() {
/*  69 */     MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/*  70 */     JdbcTemplate template = mysql.getTemplate();
/*  71 */     long baseServerId = MContext.getServerIdInt() * 1000000L;
/*     */     
/*  73 */     String executeSql = "update tb_group set id=" + baseServerId + " + id where id != 0 and id<=" + baseServerId + ";";
/*  74 */     LogUtil.errorLog(new Object[] { "update group Pre:" + executeSql });
/*  75 */     template.execute(executeSql);
/*     */     
/*  77 */     executeSql = "update tb_groupMember set id=" + baseServerId + " + id where id != 0 and id<=" + baseServerId + ";";
/*  78 */     LogUtil.errorLog(new Object[] { "update groupMember Pre:" + executeSql });
/*  79 */     template.execute(executeSql);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/*  86 */     LogUtils.errorLog(new Object[] { "GroupReset" });
/*  87 */     synchronized (LOCK) {
/*  88 */       for (GroupEntity groupEntity : this.groupData) {
/*  89 */         groupEntity.setSacrificeNum(0);
/*  90 */         groupEntity.setSacrificePoint(0);
/*  91 */         groupEntity.setDayExp(0L);
/*  92 */         groupEntity.setReset((byte)0);
/*  93 */         groupEntity.getDamageRecords().clear();
/*  94 */         updateSacrificeNum(groupEntity.getId());
/*  95 */         updateSacrificePoint(groupEntity.getId());
/*  96 */         updateDayExp(groupEntity.getId());
/*  97 */         updateReset(groupEntity.getId());
/*     */       } 
/*     */     } 
/* 100 */     saveToDB();
/* 101 */     GroupUtil.checkAutoTransfer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sort() {
/* 108 */     GroupEntity delGroupEntity = null;
/* 109 */     synchronized (LOCK) {
/* 110 */       this.groupData.sort((Comparator<? super GroupEntity>)new GroupComparator());
/* 111 */       int rank = 1;
/* 112 */       for (GroupEntity groupEntity : this.groupData) {
/* 113 */         if (groupEntity.getMemberList().size() == 0) {
/* 114 */           delGroupEntity = groupEntity;
/*     */           break;
/*     */         } 
/* 117 */         groupEntity.setRank(rank++);
/* 118 */         updateRank(groupEntity.getId());
/*     */       } 
/*     */     } 
/* 121 */     if (null != delGroupEntity) {
/* 122 */       LogUtils.errorLog(new Object[] { "GroupSizeZeroError", delGroupEntity.toString() });
/* 123 */       deleteGroupEntity(delGroupEntity);
/*     */     } else {
/* 125 */       saveToDB();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setUpdate(long groupId, String field) {
/* 130 */     this.mapProxy.setUpdate(String.valueOf(groupId), field);
/*     */   }
/*     */   
/*     */   public void updateGroupName(long groupId) {
/* 134 */     setUpdate(groupId, "groupName");
/*     */   }
/*     */   
/*     */   public void updateLeader(long groupId) {
/* 138 */     setUpdate(groupId, "leader");
/*     */   }
/*     */   
/*     */   public void updateLeaderName(long groupId) {
/* 142 */     setUpdate(groupId, "leaderName");
/*     */   }
/*     */   
/*     */   public void updateLevel(long groupId) {
/* 146 */     setUpdate(groupId, "level");
/*     */   }
/*     */   
/*     */   public void updateRank(long groupId) {
/* 150 */     setUpdate(groupId, "rank");
/*     */   }
/*     */   
/*     */   public void updateNotice(long groupId) {
/* 154 */     setUpdate(groupId, "notice");
/*     */   }
/*     */   
/*     */   public void updateApplyList(long groupId) {
/* 158 */     setUpdate(groupId, "applyList");
/*     */   }
/*     */   
/*     */   public void updateMemberList(long groupId) {
/* 162 */     setUpdate(groupId, "memberList");
/*     */   }
/*     */   
/*     */   public void updateVices(long groupId) {
/* 166 */     setUpdate(groupId, "vices");
/*     */   }
/*     */   
/*     */   public void updateSacrificePoint(long groupId) {
/* 170 */     setUpdate(groupId, "sacrificePoint");
/*     */   }
/*     */   
/*     */   public void updateReset(long groupId) {
/* 174 */     setUpdate(groupId, "reset");
/*     */   }
/*     */   
/*     */   public void updateSacrificeNum(long groupId) {
/* 178 */     setUpdate(groupId, "sacrificeNum");
/*     */   }
/*     */   
/*     */   public void updateExp(long groupId) {
/* 182 */     setUpdate(groupId, "exp");
/*     */   }
/*     */   
/*     */   public void updateDayExp(long groupId) {
/* 186 */     setUpdate(groupId, "dayExp");
/*     */   }
/*     */   
/*     */   public void updateOffices(long groupId) {
/* 190 */     setUpdate(groupId, "officeList");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized GroupEntity createGroup() {
/* 199 */     DBIncrementService dbIncrementService = (DBIncrementService)AppContext.getBean("dbIncrementService");
/* 200 */     long baseGroupId = MContext.getServerIdInt() * 1000000L;
/* 201 */     long groupId = baseGroupId + dbIncrementService.generate("groupId").intValue();
/* 202 */     return (GroupEntity)this.mapProxy.createProxy(Long.valueOf(groupId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GroupEntity getGroupEntity(long id) {
/* 212 */     synchronized (LOCK) {
/* 213 */       for (GroupEntity groupEntity : this.groupData) {
/* 214 */         if (groupEntity.getId() == id) {
/* 215 */           return groupEntity;
/*     */         }
/*     */       } 
/*     */     } 
/* 219 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGroupEntity(GroupEntity groupEntity) {
/* 228 */     LogUtils.errorLog(new Object[] { "addGroupEntity", Long.valueOf(groupEntity.getId()), groupEntity.toString() });
/* 229 */     synchronized (LOCK) {
/* 230 */       this.mapProxy.addEntity((IMapEntity)groupEntity);
/* 231 */       this.groupData.add(groupEntity);
/* 232 */       this.nameToGroupId.put(groupEntity.getGroupName(), Long.valueOf(groupEntity.getId()));
/*     */     } 
/* 234 */     sort();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteGroupEntity(GroupEntity groupEntity) {
/* 243 */     LogUtils.errorLog(new Object[] { "deleteGroupEntity", Long.valueOf(groupEntity.getId()), groupEntity.toString() });
/* 244 */     synchronized (LOCK) {
/* 245 */       this.nameToGroupId.remove(groupEntity.getGroupName());
/* 246 */       this.groupData.remove(groupEntity);
/* 247 */       this.mapProxy.delEntity((IMapEntity)groupEntity);
/*     */     } 
/* 249 */     sort();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getList(ArrayList<GroupTipData> datas, List<Long> showList, long playerId, int start, int end) {
/* 258 */     synchronized (LOCK) {
/* 259 */       List<GroupEntity> list = this.groupData;
/* 260 */       int size = list.size();
/* 261 */       for (int i = start; i <= end; i++) {
/* 262 */         if (i <= size - 1) {
/* 263 */           GroupEntity groupEntity = list.get(i);
/* 264 */           GroupTipData groupTipData = GroupUtil.getGroupTipData(groupEntity, playerId);
/* 265 */           datas.add(groupTipData);
/* 266 */           showList.add(Long.valueOf(groupEntity.getId()));
/*     */         } 
/*     */       } 
/* 269 */       return size;
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
/*     */   public short addApply(GroupEntity groupEntity, long playerId, GroupService groupService, IPlayerSession playerSession) {
/* 281 */     synchronized (LOCK) {
/* 282 */       List<Long> applyList = groupEntity.getApplyList();
/* 283 */       if (applyList.contains(Long.valueOf(playerId))) {
/* 284 */         return 11102;
/*     */       }
/* 286 */       if (applyList.size() >= GroupUtil.getApplyNum(groupEntity.getLevel())) {
/* 287 */         return 11133;
/*     */       }
/* 289 */       if (!GroupUtil.autoJoin(groupEntity, playerId, groupService, playerSession)) {
/* 290 */         return 11106;
/*     */       }
/* 292 */       applyList.add(Long.valueOf(playerId));
/* 293 */       groupEntity.setApplyList(applyList);
/* 294 */       updateApplyList(groupEntity.getId());
/*     */     } 
/* 296 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short removeApply(GroupEntity groupEntity, long playerId) {
/* 307 */     if (null == groupEntity) {
/* 308 */       return 0;
/*     */     }
/* 310 */     synchronized (LOCK) {
/* 311 */       List<Long> applyList = groupEntity.getApplyList();
/* 312 */       if (applyList.contains(Long.valueOf(playerId))) {
/* 313 */         applyList.remove(Long.valueOf(playerId));
/*     */       }
/* 315 */       groupEntity.setApplyList(applyList);
/* 316 */       updateApplyList(groupEntity.getId());
/*     */     } 
/* 318 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkName(String name) {
/* 328 */     synchronized (LOCK) {
/* 329 */       if (this.nameToGroupId.containsKey(name)) {
/* 330 */         return true;
/*     */       }
/*     */     } 
/* 333 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeMember(GroupEntity groupEntity, long playerId) {
/* 343 */     if (null == groupEntity) {
/*     */       return;
/*     */     }
/* 346 */     synchronized (LOCK) {
/* 347 */       Set<Long> memberList = groupEntity.getMemberList();
/* 348 */       if (memberList.contains(Long.valueOf(playerId))) {
/* 349 */         memberList.remove(Long.valueOf(playerId));
/*     */       }
/* 351 */       groupEntity.setMemberList(memberList);
/* 352 */       updateMemberList(groupEntity.getId());
/*     */       
/* 354 */       if (groupEntity.getOfficeList().containsKey(Long.valueOf(playerId))) {
/* 355 */         groupEntity.getOfficeList().remove(Long.valueOf(playerId));
/* 356 */         updateOffices(groupEntity.getId());
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
/*     */   public void addMember(GroupEntity groupEntity, long playerId) {
/* 368 */     if (null == groupEntity) {
/*     */       return;
/*     */     }
/* 371 */     synchronized (LOCK) {
/* 372 */       Set<Long> memberList = groupEntity.getMemberList();
/* 373 */       memberList.add(Long.valueOf(playerId));
/* 374 */       groupEntity.setMemberList(memberList);
/* 375 */       updateMemberList(groupEntity.getId());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeVice(GroupEntity groupEntity, long playerId) {
/* 386 */     if (null == groupEntity) {
/*     */       return;
/*     */     }
/* 389 */     synchronized (LOCK) {
/* 390 */       Set<Long> vices = groupEntity.getVices();
/* 391 */       if (vices.contains(Long.valueOf(playerId))) {
/* 392 */         vices.remove(Long.valueOf(playerId));
/*     */       }
/* 394 */       groupEntity.setVices(vices);
/* 395 */       updateVices(groupEntity.getId());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addVice(GroupEntity groupEntity, long playerId) {
/* 406 */     if (null == groupEntity) {
/*     */       return;
/*     */     }
/* 409 */     synchronized (LOCK) {
/* 410 */       Set<Long> vices = groupEntity.getVices();
/* 411 */       vices.add(Long.valueOf(playerId));
/* 412 */       groupEntity.setVices(vices);
/* 413 */       updateVices(groupEntity.getId());
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
/*     */   public void changeLeader(GroupEntity groupEntity, long leaderId, String leaderName) {
/* 425 */     if (null == groupEntity) {
/*     */       return;
/*     */     }
/* 428 */     synchronized (LOCK) {
/* 429 */       groupEntity.setLeader(leaderId);
/* 430 */       groupEntity.setLeaderName(leaderName);
/* 431 */       updateLeader(groupEntity.getId());
/* 432 */       updateLeaderName(groupEntity.getId());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addExp(GroupEntity groupEntity, long exp) {
/* 443 */     if (null == groupEntity || exp <= 0L) {
/*     */       return;
/*     */     }
/* 446 */     BlocLevelBean blocLevelBean = JsonTableService.<BlocLevelBean>getJsonData(groupEntity.getLevel(), BlocLevelBean.class);
/* 447 */     if (null == blocLevelBean) {
/*     */       return;
/*     */     }
/* 450 */     boolean isLevelUp = false;
/* 451 */     synchronized (LOCK) {
/* 452 */       long dayExp = groupEntity.getDayExp();
/* 453 */       if (dayExp >= blocLevelBean.getDailyLimited()) {
/*     */         return;
/*     */       }
/* 456 */       groupEntity.setDayExp(dayExp + exp);
/* 457 */       updateDayExp(groupEntity.getId());
/* 458 */       long groupExp = groupEntity.getExp();
/* 459 */       groupExp += exp;
/* 460 */       int level = groupEntity.getLevel();
/* 461 */       while (0 != blocLevelBean.getExp() && groupExp >= blocLevelBean.getExp()) {
/* 462 */         isLevelUp = true;
/* 463 */         groupExp -= blocLevelBean.getExp();
/* 464 */         level++;
/* 465 */         blocLevelBean = JsonTableService.<BlocLevelBean>getJsonData(level, BlocLevelBean.class);
/* 466 */         if (null == blocLevelBean) {
/*     */           break;
/*     */         }
/*     */       } 
/* 470 */       groupEntity.setExp(groupExp);
/* 471 */       updateExp(groupEntity.getId());
/* 472 */       if (isLevelUp) {
/* 473 */         groupEntity.setLevel(level);
/* 474 */         updateLevel(groupEntity.getId());
/*     */       } 
/*     */     } 
/* 477 */     if (isLevelUp) {
/* 478 */       sort();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPoint(GroupEntity groupEntity, int point) {
/* 489 */     if (null == groupEntity || point <= 0) {
/*     */       return;
/*     */     }
/* 492 */     synchronized (LOCK) {
/* 493 */       groupEntity.setSacrificePoint(groupEntity.getSacrificePoint() + point);
/* 494 */       updateSacrificePoint(groupEntity.getId());
/* 495 */       groupEntity.setSacrificeNum(groupEntity.getSacrificeNum() + 1);
/* 496 */       updateSacrificeNum(groupEntity.getId());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeOffices(GroupEntity groupEntity, long playerId) {
/* 506 */     synchronized (LOCK) {
/* 507 */       if (groupEntity.getOfficeList().containsKey(Long.valueOf(playerId))) {
/* 508 */         groupEntity.getOfficeList().remove(Long.valueOf(playerId));
/* 509 */         updateOffices(groupEntity.getId());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addOffices(IPlayerSession playerSession, GroupEntity groupEntity, long playerId, KeyValue keyValue, String militaryName) {
/* 520 */     synchronized (LOCK) {
/* 521 */       PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 522 */       groupEntity.getOfficeList().put(Long.valueOf(playerId), keyValue);
/* 523 */       updateOffices(groupEntity.getId());
/*     */       
/* 525 */       ChatByChannelResponse response = new ChatByChannelResponse();
/* 526 */       String context = LanguageConstant.getAndReplaceLanguage(903, new String[] { militaryName, String.valueOf(playerComponent.getPlayerId()) });
/* 527 */       ChatInfo chatInfo = new ChatInfo();
/* 528 */       chatInfo.type = 1;
/* 529 */       chatInfo.sendPlayerId = playerComponent.getPlayerId();
/* 530 */       chatInfo.sendPlayerName = playerComponent.getPlayerName();
/* 531 */       chatInfo.vip = playerComponent.getVip();
/* 532 */       chatInfo.titleId = playerComponent.getWearTitle();
/* 533 */       chatInfo.sex = playerComponent.getSex();
/* 534 */       chatInfo.head = PlayerUtil.getHeadUrl(playerComponent.getPlayerId());
/* 535 */       chatInfo.context = context;
/* 536 */       chatInfo.time = TimeUtil.currentTime();
/* 537 */       chatInfo.quality = playerComponent.getQuality();
/* 538 */       response.list.add(chatInfo);
/* 539 */       GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 540 */       GroupMemberEntity memberEntity = (GroupMemberEntity)groupMemberComponent.getEntity();
/* 541 */       if (memberEntity != null && memberEntity.getId() != 0L && 
/* 542 */         null != groupEntity) {
/* 543 */         LookUpService.campBoradcast((ResponseBase)response, groupEntity);
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
/*     */ 
/*     */   
/*     */   public void updateOffices(GroupEntity groupEntity, long playerId, KeyValue keyValue) {
/* 557 */     synchronized (LOCK) {
/* 558 */       groupEntity.getOfficeList().put(Long.valueOf(playerId), keyValue);
/* 559 */       updateOffices(groupEntity.getId());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\GroupService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */