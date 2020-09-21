/*     */ package com.linlongyx.sanguo.webgame.processors.mail;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.mail.MailComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.mail.MailEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.mail.PubMailEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.MailParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.mail.MailNoticeRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MailInfo;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.DBIncrementService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import com.linlongyx.sanguo.webgame.service.PubMailService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MailUtil
/*     */ {
/*  36 */   private static final Object mailLock = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendPubMail(MailInfo mailInfo, Map<Integer, String> rules) {
/*  43 */     PubMailService service = (PubMailService)AppContext.getBean("pubMailService");
/*  44 */     DBIncrementService idService = (DBIncrementService)AppContext.getBean("dbIncrementService");
/*  45 */     int id = idService.generate("pubMail").intValue();
/*  46 */     PubMailEntity pubMailEntity = (PubMailEntity)service.entityProxy.createProxy(Integer.valueOf(id));
/*  47 */     pubMailEntity.setSendId(mailInfo.sendId);
/*  48 */     pubMailEntity.setType(mailInfo.type);
/*  49 */     pubMailEntity.setSendName(mailInfo.sendName);
/*  50 */     pubMailEntity.setSendTime(TimeUtil.currentTime());
/*  51 */     pubMailEntity.setTitle(mailInfo.title);
/*  52 */     pubMailEntity.setContext(mailInfo.context);
/*  53 */     pubMailEntity.setRewards(mailInfo.rewards);
/*  54 */     pubMailEntity.setRules(rules);
/*  55 */     service.entityProxy.addEntity((IMapEntity)pubMailEntity);
/*  56 */     service.saveToDB();
/*  57 */     LookUpService.OnlinePubMail(pubMailEntity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initMailComponent(IPlayer iPlayer) {
/*  65 */     MailComponent mailComponent = (MailComponent)iPlayer.createIfNotExist(MailComponent.class);
/*  66 */     mailComponent.sort();
/*  67 */     List<Integer> deList = new ArrayList<>();
/*  68 */     int now = TimeUtil.currentTime();
/*  69 */     MailParameter parameter = (MailParameter)ParameterConstant.getParameter(5);
/*  70 */     for (Map.Entry<String, IMapEntity> entry : (Iterable<Map.Entry<String, IMapEntity>>)mailComponent.getProxy().getEntityMap().entrySet()) {
/*  71 */       MailEntity mailEntity = (MailEntity)entry.getValue();
/*  72 */       if (mailEntity.getSendTime() < now - parameter.getMailMaxDay() * 24 * 3600) {
/*  73 */         deList.add(Integer.valueOf(mailEntity.getId()));
/*     */       }
/*     */     } 
/*  76 */     for (Integer id : deList) {
/*  77 */       MailEntity mailEntity = (MailEntity)mailComponent.getEntity(String.valueOf(id));
/*  78 */       if (null != mailEntity) {
/*  79 */         LogUtils.errorLog(new Object[] { "MailDelete:", Long.valueOf(mailEntity.getPlayerId()), mailEntity.toString() });
/*     */       }
/*  81 */       mailComponent.deleteMail(id.intValue());
/*     */     } 
/*     */     
/*  84 */     PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/*  85 */     PubMailService pubMailService = (PubMailService)AppContext.getBean("pubMailService");
/*  86 */     if (playerComponent.getPubIdCount() == -1) {
/*  87 */       playerComponent.setPubIdCount(pubMailService.getMaxId());
/*     */     }
/*  89 */     int pubId = playerComponent.getPubIdCount();
/*  90 */     while (pubId < pubMailService.getMaxId()) {
/*  91 */       pubId++;
/*  92 */       PubMailEntity pubMailEntity = pubMailService.getMailInfoById(pubId);
/*  93 */       if (pubMailEntity == null) {
/*     */         continue;
/*     */       }
/*  96 */       if (checkPubMail(iPlayer, pubMailEntity.getRules())) {
/*  97 */         sendPubMailToPlayer(playerComponent, mailComponent, pubMailEntity);
/*     */       }
/*     */     } 
/* 100 */     playerComponent.setPubIdCount(pubId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void sendPubMailToPlayer(PlayerComponent playerComponent, MailComponent mailComponent, PubMailEntity pubMailEntity) {
/*     */     int id;
/* 111 */     synchronized (mailLock) {
/* 112 */       id = playerComponent.addAndGetMailId();
/*     */     } 
/* 114 */     MailEntity mailEntity = (MailEntity)mailComponent.createEntity(Integer.valueOf(id));
/* 115 */     mailEntity.setType(pubMailEntity.getType());
/* 116 */     mailEntity.setSendName(pubMailEntity.getSendName());
/* 117 */     mailEntity.setSendTime(pubMailEntity.getSendTime());
/* 118 */     mailEntity.setTitle(pubMailEntity.getTitle());
/* 119 */     mailEntity.setContext(pubMailEntity.getContext());
/* 120 */     mailEntity.setRewards(pubMailEntity.getRewards());
/* 121 */     if (mailEntity.getRewards().size() == 0) {
/* 122 */       mailEntity.setIsExtract((byte)1);
/*     */     }
/* 124 */     mailComponent.addEntity(mailEntity);
/* 125 */     IPlayer iPlayer = LookUpService.getByPlayerId(playerComponent.getPlayerId());
/* 126 */     if (null != iPlayer && null != iPlayer.getSession()) {
/* 127 */       (new MailNoticeProcessor()).handle(iPlayer.getSession(), (RequestBase)new MailNoticeRequest());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkPubMail(IPlayer iPlayer, Map<Integer, String> rules) {
/* 138 */     PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/* 139 */     for (Map.Entry<Integer, String> entry : rules.entrySet()) {
/* 140 */       int groupId; GroupMemberComponent groupMemberComponent; int channel; switch (((Integer)entry.getKey()).byteValue()) {
/*     */         
/*     */         case 1:
/* 143 */           if (playerComponent.getLevel() < Integer.parseInt((String)entry.getValue())) {
/* 144 */             return false;
/*     */           }
/*     */ 
/*     */         
/*     */         case 2:
/* 149 */           if (playerComponent.getLevel() > Integer.parseInt((String)entry.getValue())) {
/* 150 */             return false;
/*     */           }
/*     */ 
/*     */         
/*     */         case 3:
/* 155 */           groupId = Integer.parseInt(entry.getValue());
/* 156 */           groupMemberComponent = (GroupMemberComponent)iPlayer.createIfNotExist(GroupMemberComponent.class);
/* 157 */           if (groupMemberComponent.getId() != groupId) {
/* 158 */             return false;
/*     */           }
/*     */ 
/*     */         
/*     */         case 4:
/* 163 */           if (!((String)entry.getValue()).contains(String.valueOf(playerComponent.getPlayerId()))) {
/* 164 */             return false;
/*     */           }
/*     */ 
/*     */         
/*     */         case 11:
/* 169 */           if (playerComponent.getTotalValue() < Integer.parseInt(entry.getValue())) {
/* 170 */             return false;
/*     */           }
/*     */ 
/*     */         
/*     */         case 12:
/* 175 */           if (playerComponent.getTotalValue() > Integer.parseInt(entry.getValue())) {
/* 176 */             return false;
/*     */           }
/*     */ 
/*     */         
/*     */         case 13:
/* 181 */           if (TimeUtil.getDayDiff(playerComponent.getCreateTime()) < Integer.parseInt((String)entry.getValue())) {
/* 182 */             return false;
/*     */           }
/*     */ 
/*     */         
/*     */         case 14:
/* 187 */           if (TimeUtil.getDayDiff(playerComponent.getCreateTime()) > Integer.parseInt((String)entry.getValue())) {
/* 188 */             return false;
/*     */           }
/*     */ 
/*     */         
/*     */         case 15:
/* 193 */           if (playerComponent.getVip() < Integer.parseInt((String)entry.getValue())) {
/* 194 */             return false;
/*     */           }
/*     */ 
/*     */         
/*     */         case 16:
/* 199 */           if (playerComponent.getVip() > Integer.parseInt((String)entry.getValue())) {
/* 200 */             return false;
/*     */           }
/*     */ 
/*     */         
/*     */         case 17:
/* 205 */           channel = Integer.parseInt(entry.getValue());
/* 206 */           if (channel != -1 && playerComponent.getChannel() != channel) {
/* 207 */             return false;
/*     */           }
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 214 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendSysMail(long playerId, ArrayList<Reward> attachments, String title, String context) {
/*     */     int mailId;
/*     */     PlayerComponent playerComponent;
/* 227 */     synchronized (mailLock) {
/* 228 */       playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/* 229 */       if (null == playerComponent) {
/* 230 */         LogUtil.errorLog(new Object[] { "MailUtil:MailNull1", Long.valueOf(playerId), title, context, attachments.toString() });
/*     */         return;
/*     */       } 
/* 233 */       mailId = playerComponent.addAndGetMailId();
/*     */     } 
/* 235 */     MailComponent mailComponent = (MailComponent)LookUpService.getComponent(playerId, MailComponent.class);
/* 236 */     if (null == mailComponent) {
/* 237 */       mailComponent = (MailComponent)LookUpService.createIfNotExist(playerId, MailComponent.class);
/*     */     }
/* 239 */     if (null == mailComponent) {
/* 240 */       LogUtil.errorLog(new Object[] { "MailUtil:MailNull2", Long.valueOf(playerId), title, context, attachments.toString() });
/*     */       return;
/*     */     } 
/* 243 */     MailParameter mailParameter = (MailParameter)ParameterConstant.getParameter(5);
/* 244 */     LogUtil.errorLog(new Object[] { "MailUtil:sendSysMail", Long.valueOf(playerId), Integer.valueOf(mailId), title, context });
/* 245 */     MailEntity mailEntity = (MailEntity)mailComponent.getProxy().createProxy(Integer.valueOf(mailId));
/* 246 */     mailEntity.setSendId(0L);
/* 247 */     mailEntity.setSendName(mailParameter.getMailName());
/* 248 */     mailEntity.setType((byte)1);
/* 249 */     mailEntity.setSendTime(TimeUtil.currentTime());
/* 250 */     mailEntity.setTitle(title);
/* 251 */     mailEntity.setContext(context);
/* 252 */     mailEntity.setRewards(attachments);
/* 253 */     if (mailEntity.getRewards().size() == 0) {
/* 254 */       mailEntity.setIsExtract((byte)1);
/*     */     }
/* 256 */     mailComponent.addEntity(mailEntity);
/* 257 */     boolean flag = mailComponent.saveToDB();
/* 258 */     if (!flag) {
/* 259 */       LogUtil.errorLog(new Object[] { "sendMail saveToDB failure", Long.valueOf(playerId), mailEntity.toString() });
/*     */     }
/* 261 */     LogUtil.gameLog(LogType.MAIL, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerComponent.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(mailId), Long.valueOf(mailEntity.getSendId()), 
/* 262 */           Integer.valueOf(mailEntity.getSendTime()), mailEntity.getTitle(), mailEntity.getContext(), mailEntity.getRewards(), Integer.valueOf(TimeUtil.currentTime()) });
/*     */     
/* 264 */     IPlayer iPlayer = LookUpService.getByPlayerId(playerId);
/* 265 */     if (iPlayer != null && iPlayer.getSession() != null) {
/* 266 */       (new MailNoticeProcessor()).handle(iPlayer.getSession(), (RequestBase)new MailNoticeRequest());
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
/*     */   public static void sendSysRewardBeanMail(long playerId, ArrayList<RewardBean> attachments, String title, String context) {
/* 278 */     sendSysMail(playerId, FinanceUtil.rewardBeanGet(attachments), title, context);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mail\MailUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */