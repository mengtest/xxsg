/*     */ package com.linlongyx.sanguo.webgame.processors.title;
/*     */ 
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.IDAO;
/*     */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.utils.GsonUtil;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TitleBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.title.TitleInfoRequest;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TitleUtil
/*     */ {
/*     */   public static void checkTitleValid() {
/*  36 */     if (AppContext.getDAO().getType() == IDAO.TYPE.MYSQL) {
/*     */       List<Map<String, Object>> info;
/*     */       try {
/*  39 */         MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/*  40 */         JdbcTemplate template = mysql.getTemplate();
/*  41 */         String selectSql = "SELECT playerId, wearTitle, activeTitles FROM tb_player  ";
/*  42 */         info = template.queryForList(selectSql);
/*  43 */       } catch (Exception e) {
/*  44 */         LogUtil.errorLog(new Object[] { "TitleUtil::checkTitleValid", e.getMessage() });
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  49 */       Type type = (new TypeToken<HashMap<Integer, Integer>>() {  }).getType();
/*  50 */       int curTime = TimeUtil.currentTime();
/*  51 */       Map<Long, List<Integer>> playerTitleMap = new HashMap<>();
/*  52 */       for (Map<String, Object> map : info) {
/*  53 */         Long playerId = (Long)map.get("playerId");
/*  54 */         List<Integer> delTitle = new ArrayList<>();
/*  55 */         Map<Integer, Integer> activeTitles = (Map<Integer, Integer>)GsonUtil.fromJson((String)map.get("activeTitles"), type);
/*  56 */         if (null != activeTitles && activeTitles.size() > 0) {
/*  57 */           for (Map.Entry<Integer, Integer> entry : activeTitles.entrySet()) {
/*  58 */             if (((Integer)entry.getValue()).intValue() != 0 && ((Integer)entry.getValue()).intValue() < curTime) {
/*  59 */               delTitle.add(entry.getKey());
/*     */             }
/*     */           } 
/*     */         }
/*  63 */         if (delTitle.size() > 0) {
/*  64 */           playerTitleMap.put(playerId, delTitle);
/*     */         }
/*     */       } 
/*  67 */       if (playerTitleMap.size() == 0) {
/*     */         return;
/*     */       }
/*  70 */       for (Map.Entry<Long, List<Integer>> entry : playerTitleMap.entrySet()) {
/*  71 */         PlayerComponent playerComponent; long playerId = ((Long)entry.getKey()).longValue();
/*     */         
/*  73 */         IPlayer iPlayer = LookUpService.getByPlayerId(playerId);
/*  74 */         if (iPlayer != null) {
/*  75 */           playerComponent = (PlayerComponent)iPlayer.getSession().getPlayer().createIfNotExist(PlayerComponent.class);
/*     */         } else {
/*  77 */           playerComponent = new PlayerComponent(playerId);
/*  78 */           playerComponent.init();
/*     */         } 
/*  80 */         for (Integer title : entry.getValue()) {
/*  81 */           if (playerComponent.getActiveTitles().containsKey(title)) {
/*  82 */             Map<Integer, Integer> activeTitles = playerComponent.getActiveTitles();
/*  83 */             activeTitles.remove(title);
/*  84 */             playerComponent.setActiveTitles(activeTitles);
/*     */           } 
/*  86 */           if (playerComponent.getWearTitle() == title.intValue()) {
/*  87 */             playerComponent.setWearTitle(0);
/*     */           }
/*     */         } 
/*  90 */         if (iPlayer != null) {
/*  91 */           (new TitleInfoProcessor()).handle(iPlayer.getSession(), (RequestBase)new TitleInfoRequest());
/*     */         }
/*  93 */         playerComponent.saveToDB();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void checkTitleValid(IPlayerSession playerSession) {
/* 102 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 103 */     Map<Integer, Integer> activeTitles = playerComponent.getActiveTitles();
/* 104 */     int curTime = TimeUtil.currentTime();
/* 105 */     List<Integer> delTitle = new ArrayList<>();
/* 106 */     activeTitles.forEach((k, v) -> {
/*     */           if (v.intValue() != 0 && v.intValue() < curTime) {
/*     */             delTitle.add(k);
/*     */           }
/*     */         });
/*     */     
/* 112 */     if (delTitle.size() > 0) {
/* 113 */       delTitle.forEach(title -> {
/*     */             if (activeTitles.containsKey(title)) {
/*     */               activeTitles.remove(title);
/*     */               playerComponent.setActiveTitles(activeTitles);
/*     */             } 
/*     */             if (playerComponent.getWearTitle() == title.intValue()) {
/*     */               playerComponent.setWearTitle(0);
/*     */             }
/*     */           });
/* 122 */       playerComponent.saveToDB();
/*     */       
/* 124 */       for (int i = 0; i < delTitle.size(); i++) {
/* 125 */         TitleBean titleBean = (TitleBean)JsonTableService.getJsonData(((Integer)delTitle.get(i)).intValue(), TitleBean.class);
/* 126 */         if (null != titleBean) {
/* 127 */           String title = LanguageConstant.getLanguage(3801);
/* 128 */           String content = LanguageConstant.getAndReplaceLanguage(3802, new String[] { titleBean.getTitleName() });
/* 129 */           MailUtil.sendSysMail(playerSession.getPlayer().getPlayerId(), new ArrayList(), title, content);
/*     */         } 
/*     */       } 
/* 132 */       AttrUpdateUtil.refreshTitle(playerSession);
/* 133 */       LogUtil.errorLog(new Object[] { "checkTitleValid", Long.valueOf(playerSession.getPlayer().getPlayerId()), delTitle.toString() });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\title\TitleUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */