/*     */ package com.linlongyx.sanguo.webgame.net.http.answer;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.logic.ISession;
/*     */ import com.linlongyx.core.framework.logic.PlayerSession;
/*     */ import com.linlongyx.core.utils.Code;
/*     */ import com.linlongyx.core.utils.GsonUtil;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.user.UserComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.player.Player;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ChargeBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.log.LogType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChargeEvent
/*     */   implements IAnswerHttpEvent
/*     */ {
/*     */   public String process(Map<String, List<String>> p) {
/*     */     Player player;
/*  40 */     Map<String, String> result = new HashMap<>();
/*  41 */     StringBuilder log = new StringBuilder();
/*  42 */     LogUtil.debugLog(new Object[] { "charge event:" + p });
/*  43 */     if (!p.containsKey("playerId") || !p.containsKey("num") || !p.containsKey("orderNum") || !p.containsKey("time") || !p.containsKey("sign") || !p.containsKey("subject")) {
/*  44 */       result.put("errorCode:", String.valueOf(10002));
/*  45 */       log.append(GsonUtil.toJson(result));
/*  46 */       LogUtil.debugLog(new Object[] { log.toString() });
/*  47 */       return GsonUtil.toJson(result);
/*     */     } 
/*  49 */     long playerId = Long.parseLong(((List<String>)p.get("playerId")).get(0));
/*  50 */     long num = Long.parseLong(((List<String>)p.get("num")).get(0));
/*  51 */     String orderNum = ((List<String>)p.get("orderNum")).get(0);
/*  52 */     long time = Long.parseLong(((List<String>)p.get("time")).get(0));
/*  53 */     String sign = ((List<String>)p.get("sign")).get(0);
/*  54 */     int subject = Integer.parseInt(((List<String>)p.get("subject")).get(0));
/*  55 */     String md5String = playerId + "" + num + "" + time + "" + subject + MContext.getSecretKey();
/*  56 */     if (!sign.equals(Code.getInstance().getCode(md5String))) {
/*  57 */       result.put("errorCode:", "sign is error");
/*  58 */       log.append(GsonUtil.toJson(result));
/*  59 */       LogUtil.debugLog(new Object[] { log.toString() });
/*  60 */       return GsonUtil.toJson(result);
/*     */     } 
/*     */     
/*  63 */     IPlayer iPlayer = LookUpService.getByPlayerId(playerId);
/*     */     
/*  65 */     if (iPlayer == null) {
/*  66 */       PlayerSession.PlayerSessionBuilder builder = new PlayerSession.PlayerSessionBuilder();
/*  67 */       builder.validateAndSetValues();
/*  68 */       IPlayerSession playerSession = (IPlayerSession)builder.status(ISession.Status.CLOSED).isLogin(false).writeable(false).build();
/*  69 */       player = new Player(playerSession);
/*  70 */       player.setPlayerId(playerId);
/*  71 */       playerSession.setPlayer((IPlayer)player);
/*     */     } 
/*     */     
/*  74 */     ChargeBean chargeBean = (ChargeBean)JsonTableService.getJsonData(subject, ChargeBean.class);
/*  75 */     UserComponent userComponent = (UserComponent)player.createIfNotExist(UserComponent.class);
/*  76 */     if (chargeBean == null) {
/*  77 */       PlayerComponent playerComponent1 = LookUpService.getPlayerComponent(playerId);
/*  78 */       if (playerComponent1 != null) {
/*  79 */         LogUtil.gameLog(LogType.CHARGE, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(userComponent.getUserId()), Long.valueOf(playerId), Integer.valueOf(0), Integer.valueOf(subject), TimeUtil.getFormatDate(), orderNum, Integer.valueOf(0), Integer.valueOf(1), Short.valueOf(playerComponent1.getLevel()), Long.valueOf(num) });
/*     */       }
/*  81 */       result.put("errorCode:", "have not such chargeBean!");
/*  82 */       return GsonUtil.toJson(result);
/*     */     } 
/*  84 */     if (chargeBean.getRmb() != num) {
/*  85 */       PlayerComponent playerComponent1 = LookUpService.getPlayerComponent(playerId);
/*  86 */       if (playerComponent1 != null) {
/*  87 */         LogUtil.gameLog(LogType.CHARGE, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(userComponent.getUserId()), Long.valueOf(playerId), Integer.valueOf(0), Integer.valueOf(subject), TimeUtil.getFormatDate(), orderNum, Integer.valueOf(0), Integer.valueOf(2), Short.valueOf(playerComponent1.getLevel()), Long.valueOf(num) });
/*     */       }
/*  89 */       result.put("errorCode:", "charge RMB is wrong!");
/*  90 */       return GsonUtil.toJson(result);
/*     */     } 
/*  92 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  93 */     if (null != playerComponent) {
/*  94 */       player.setPlayerName(playerComponent.getPlayerName());
/*     */     }
/*  96 */     ExtendComponent extendComponent = (ExtendComponent)player.createIfNotExist(ExtendComponent.class);
/*  97 */     boolean flag2 = (extendComponent != null && extendComponent.getFirstReChargeSet().size() == 0 && extendComponent.getNewFirstCharge().size() == 0);
/*  98 */     if (flag2) {
/*  99 */       result.put("isFirst", "1");
/*     */     } else {
/* 101 */       result.put("isFirst", "0");
/*     */     } 
/* 103 */     short chargeResult = PlayerUtil.charge((IPlayer)player, chargeBean);
/* 104 */     if (chargeResult != 0) {
/* 105 */       result.put("errorCode:", String.valueOf(chargeResult));
/* 106 */       return GsonUtil.toJson(result);
/*     */     } 
/*     */     
/* 109 */     LogUtil.gameLog(LogType.CHARGE, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(userComponent.getUserId()), Long.valueOf(playerId), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(chargeBean.getId()), TimeUtil.getFormatDate(), orderNum, Long.valueOf(playerComponent.getCurrency(CurrencyType.CCY)), Integer.valueOf(0), Short.valueOf(playerComponent.getLevel()), Long.valueOf(num) });
/* 110 */     result.put("errorCode", "0");
/* 111 */     result.put("gold", String.valueOf(chargeBean.getMoney()));
/* 112 */     result.put("orderNum", orderNum);
/* 113 */     return GsonUtil.toJson(result);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\ChargeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */