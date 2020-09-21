/*     */ package com.linlongyx.sanguo.webgame.processors.login;
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.StringUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.friend.FriendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.skill.SkillComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.skill.SkillEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.user.UserComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.net.http.ask.UpdatePlayerName;
/*     */ import com.linlongyx.sanguo.webgame.processors.crossRace.CrossRaceUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.destiny.DestinyUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.divine.DivineUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.skill.SkillUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.login.ChangeNameAndSexRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.login.ChangeNameAndSexResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PlayerInfo;
/*     */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.PlayerBaseService;
/*     */ import java.net.SocketAddress;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ChangeNameAndSexProcessor extends ProcessorBase<ChangeNameAndSexRequest, ChangeNameAndSexResponse> {
/*     */   protected void initResponse() {
/*  49 */     this.response = (ResponseBase)new ChangeNameAndSexResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, ChangeNameAndSexRequest request) {
/*  54 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 77))
/*  55 */       return 10061; 
/*  56 */     IPlayer player = playerSession.getPlayer();
/*  57 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*     */     
/*  59 */     BagComponent bagComponent = (BagComponent)player.createIfNotExist(BagComponent.class);
/*  60 */     if (!bagComponent.check(loginParameter.getChangeNameItem(), 1L)) {
/*  61 */       return 10050;
/*     */     }
/*     */ 
/*     */     
/*  65 */     short retcode = LoginUtil.checkPlayerName(request.playerName);
/*  66 */     if (retcode != 0) {
/*  67 */       return retcode;
/*     */     }
/*  69 */     UserComponent userComponent = (UserComponent)player.createIfNotExist(UserComponent.class);
/*  70 */     PlayerComponent playerComponent = (PlayerComponent)player.getComponent(PlayerComponent.class);
/*  71 */     if (request.sex == playerComponent.getSex() && request.playerName.equals(playerComponent.getPlayerName())) {
/*  72 */       return 10034;
/*     */     }
/*  74 */     if (request.sex != 1 && request.sex != 2) {
/*  75 */       return 10023;
/*     */     }
/*  77 */     if (!request.playerName.equals(playerComponent.getPlayerName())) {
/*  78 */       SocketAddress address = playerSession.getTcpSender().getChannel().remoteAddress();
/*  79 */       String ipStr = address.toString().split(":")[0].substring(1);
/*  80 */       int ipInt = StringUtil.IpStr2IpNum(ipStr);
/*  81 */       UpdatePlayerName event = new UpdatePlayerName();
/*  82 */       Map<String, String> map = new HashMap<>();
/*  83 */       map.put("avatarId", String.valueOf(userComponent.getUserId()));
/*  84 */       map.put("serverId", AppContext.getServerId());
/*  85 */       map.put("playerId", String.valueOf(player.getPlayerId()));
/*  86 */       map.put("playerName", request.playerName);
/*  87 */       map.put("sex", String.valueOf(request.sex));
/*  88 */       if (!MContext.getDebug()) {
/*  89 */         Map<String, Object> responseMap = event.request(map);
/*     */         
/*  91 */         int retCode = Integer.parseInt(String.valueOf(responseMap.get("code")));
/*  92 */         if (retCode != 10001) {
/*  93 */           LogUtil.errorLog(new Object[] { "ChangeNameProcessor return from http: ", Integer.valueOf(retCode), Long.valueOf(player.getPlayerId()), map });
/*  94 */           return 10031;
/*     */         } 
/*     */       } 
/*  97 */       String oldName = player.getPlayerName();
/*     */       
/*  99 */       playerComponent.setPlayerName(request.playerName);
/* 100 */       for (PlayerInfo playerInfo : userComponent.getPlayers()) {
/* 101 */         if (playerComponent.getPlayerId() == playerInfo.playerId && !playerInfo.name.equals(request.playerName)) {
/* 102 */           playerInfo.name = request.playerName;
/*     */         }
/*     */       } 
/* 105 */       userComponent.saveAllToDB();
/* 106 */       player.setPlayerName(request.playerName);
/*     */       
/* 108 */       PlayerBaseService playerBaseService = (PlayerBaseService)AppContext.getBean("playerBaseService");
/* 109 */       playerBaseService.update(oldName, request.playerName);
/*     */ 
/*     */       
/* 112 */       GroupMemberComponent groupMemberComponent = (GroupMemberComponent)player.createIfNotExist(GroupMemberComponent.class);
/* 113 */       GroupMemberEntity memberEntity = (GroupMemberEntity)groupMemberComponent.getEntity();
/*     */       
/* 115 */       if (null != memberEntity && 0L != memberEntity.getId() && memberEntity.getPosition() == 1) {
/* 116 */         GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 117 */         GroupEntity blocEntity = groupService.getGroupEntity(memberEntity.getId());
/* 118 */         if (null != blocEntity && blocEntity.getLeader() == player.getPlayerId()) {
/* 119 */           blocEntity.setLeaderName(player.getPlayerName());
/* 120 */           groupService.updateLeaderName(blocEntity.getId());
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 126 */       FriendComponent friendComponent = (FriendComponent)player.createIfNotExist(FriendComponent.class);
/* 127 */       String title = LanguageConstant.getLanguage(901);
/* 128 */       String mcontent = LanguageConstant.getAndReplaceLanguage(902, new String[] { oldName, request.playerName });
/* 129 */       for (Iterator<Long> iterator = friendComponent.getIds().iterator(); iterator.hasNext(); ) { long toPlayerId = ((Long)iterator.next()).longValue();
/* 130 */         MailUtil.sendSysMail(toPlayerId, new ArrayList(), title, mcontent); }
/*     */       
/* 132 */       DivineUtil.updatePlayerName(player.getPlayerId(), request.playerName);
/* 133 */       LogUtils.errorLog(new Object[] { "changeName:", " playerId", Long.valueOf(player.getPlayerId()), "old", oldName, "new", request.playerName });
/*     */     } 
/*     */     
/* 136 */     if (request.sex != playerComponent.getSex()) {
/* 137 */       SkillComponent skillComponent = (SkillComponent)playerSession.getPlayer().createIfNotExist(SkillComponent.class);
/* 138 */       FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/*     */       
/* 140 */       int newleaderId = loginParameter.getLeaderBySex(request.sex);
/* 141 */       FighterBean fighterBean2 = (FighterBean)JsonTableService.getJsonData(newleaderId, FighterBean.class);
/*     */       
/* 143 */       List<String> delSkills = new ArrayList<>();
/* 144 */       SkillEntity skillEntity = skillComponent.getSkillEntity(fighterBean.getSkill());
/* 145 */       if (null != skillEntity) {
/*     */         
/* 147 */         skillComponent.addSkillEntity(playerSession.getPlayer().getPlayerId(), fighterBean2.getSkill());
/* 148 */         SkillEntity newskillEntity = skillComponent.getSkillEntity(fighterBean2.getSkill());
/* 149 */         newskillEntity.setLevel(1);
/* 150 */         SkillUtil.sendNewSkill(playerSession.getPlayer(), skillComponent.getSkillEntity(fighterBean2.getSkill()));
/* 151 */         skillComponent.saveAllToDB();
/*     */         
/* 153 */         delSkills.add(String.valueOf(skillEntity.getSkillId()));
/*     */       } 
/* 155 */       SkillEntity skillEntity2 = skillComponent.getSkillEntity(fighterBean.getHotSkill());
/* 156 */       if (null != skillEntity2) {
/*     */         
/* 158 */         skillComponent.addSkillEntity(playerSession.getPlayer().getPlayerId(), fighterBean2.getHotSkill());
/* 159 */         SkillEntity newskillEntity2 = skillComponent.getSkillEntity(fighterBean2.getHotSkill());
/* 160 */         newskillEntity2.setLevel(1);
/* 161 */         SkillUtil.sendNewSkill(playerSession.getPlayer(), skillComponent.getSkillEntity(fighterBean2.getHotSkill()));
/* 162 */         skillComponent.saveAllToDB();
/*     */         
/* 164 */         delSkills.add(String.valueOf(skillEntity2.getSkillId()));
/*     */       } 
/* 166 */       for (String key : delSkills) {
/* 167 */         skillComponent.getProxy().delEntity(key);
/*     */       }
/* 169 */       playerComponent.setSex((byte)request.sex);
/* 170 */       playerComponent.setLeaderId(newleaderId);
/* 171 */       playerComponent.setHead(LoginUtil.updateHead(playerComponent.getHead(), playerComponent.getSex()));
/*     */     } 
/* 173 */     PartnerUtil.pushPartnerInfo(playerSession, PartnerUtil.tranformPartner2(playerComponent));
/* 174 */     bagComponent.deleteItem(loginParameter.getChangeNameItem(), 1, ResourceEvent.ChangeNameSex, true);
/* 175 */     LogUtils.errorLog(new Object[] { "changeNameAndSex:", " playerId", Long.valueOf(player.getPlayerId()) });
/* 176 */     DestinyUtil.uploadDestinyData(player);
/* 177 */     CrossRaceUtil.uploadPlayerRaceData(player);
/* 178 */     ((ChangeNameAndSexResponse)this.response).playerName = request.playerName;
/* 179 */     ((ChangeNameAndSexResponse)this.response).sex = request.sex;
/* 180 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\ChangeNameAndSexProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */