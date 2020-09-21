/*     */ package com.linlongyx.sanguo.webgame.processors;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.IModule;
/*     */ import com.linlongyx.core.framework.base.IProcessor;
/*     */ import com.linlongyx.core.framework.base.Module;
/*     */ import com.linlongyx.core.framework.base.ModuleEntrance;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MsgDispatcher
/*     */ {
/*  26 */   private static final Logger logger = LoggerFactory.getLogger(MsgDispatcher.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   private static final List<ModuleEntrance> entrances = new ArrayList<>(); private List<IModule> modules; static {
/*  36 */     int moduleSize = 0;
/*  37 */     for (MsgProcessorRegister register : MsgProcessorRegister.values()) {
/*  38 */       short type = getModIdByMsg(register.getMsgCode());
/*  39 */       if (moduleSize < type) {
/*  40 */         moduleSize = type;
/*     */       }
/*     */     } 
/*  43 */     for (int i = 0; i < moduleSize + 1; i++) {
/*  44 */       entrances.add(i, new ModuleEntrance());
/*     */     }
/*     */     
/*  47 */     for (MsgProcessorRegister register : MsgProcessorRegister.values()) {
/*  48 */       short requestId = register.getMsgCode();
/*  49 */       short moduleId = getModIdByMsg(requestId);
/*  50 */       ModuleEntrance moduleEntrance = entrances.get(moduleId);
/*  51 */       moduleEntrance.init(requestId, register.getRequest());
/*     */     } 
/*  53 */     logger.info("初始化 消息处理器成功");
/*     */   }
/*     */   private IPlayerSession playerSession; private long oldTime;
/*     */   public MsgDispatcher() throws Exception {
/*  57 */     this.modules = new ArrayList<>();
/*  58 */     initModules();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initModules() {
/*  65 */     int moduleSize = 0;
/*  66 */     for (MsgProcessorRegister register : MsgProcessorRegister.values()) {
/*  67 */       short type = getModIdByMsg(register.getMsgCode());
/*  68 */       if (moduleSize < type)
/*  69 */         moduleSize = type; 
/*     */     } 
/*  71 */     for (int i = 0; i < moduleSize + 1; i++) {
/*  72 */       ModuleEntrance moduleEntrance = entrances.get(i);
/*  73 */       this.modules.add(i, new Module(i, moduleEntrance.getModuleEntrance()));
/*     */     } 
/*     */     
/*  76 */     for (MsgProcessorRegister register : MsgProcessorRegister.values()) {
/*  77 */       addToModule(register);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addToModule(MsgProcessorRegister register) {
/*  87 */     short moduleId = getModIdByMsg(register.getMsgCode());
/*  88 */     IModule module = this.modules.get(moduleId);
/*  89 */     ModuleEntrance moduleEntrance = entrances.get(moduleId);
/*  90 */     IProcessor processor = getProcessor(register.getMsgProcessor());
/*  91 */     if (null == processor)
/*  92 */       return;  processor.setEntrance(moduleEntrance.getEntrance(register.getMsgCode()));
/*  93 */     module.addProcessor(register.getMsgCode(), processor);
/*     */   }
/*     */   
/*     */   public IProcessor getProcessor(Class<IProcessor> processor) {
/*  97 */     if (null != processor) {
/*     */       try {
/*  99 */         return processor.newInstance();
/* 100 */       } catch (InstantiationException|IllegalAccessException e) {
/* 101 */         e.printStackTrace();
/*     */       } 
/*     */     }
/* 104 */     return null;
/*     */   }
/*     */   
/*     */   public void setClientIp(int clientIp) {
/* 108 */     this.playerSession.setClientIp(clientIp);
/*     */   }
/*     */   
/*     */   public void setPlayerSession(IPlayerSession playerSession) {
/* 112 */     this.playerSession = playerSession;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setModuleOpen(short moduleId, boolean openFlag) {
/* 123 */     ModuleEntrance moduleEntrance = entrances.get(moduleId);
/* 124 */     if (null == moduleEntrance)
/*     */       return; 
/* 126 */     moduleEntrance.setModuleEntrance(openFlag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setProcessorOpen(short msgId, boolean openFlag) {
/* 137 */     short moduleId = getModIdByMsg(msgId);
/* 138 */     ModuleEntrance moduleEntrance = entrances.get(moduleId);
/* 139 */     if (null == moduleEntrance)
/*     */       return; 
/* 141 */     moduleEntrance.setEntrance(msgId, openFlag);
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
/*     */   public void dispatch(IPlayerSession playerSession, RequestBase request) {
/* 153 */     short requestId = request.getEventId();
/* 154 */     IModule module = getMsgModule(requestId);
/* 155 */     if (null == module) {
/* 156 */       logger.error("module is null when request id is " + requestId);
/*     */       
/*     */       return;
/*     */     } 
/* 160 */     IProcessor processor = module.getProcessor(requestId);
/* 161 */     if (null == processor) {
/* 162 */       logger.error("processor is null when request id is " + requestId);
/*     */       
/*     */       return;
/*     */     } 
/* 166 */     if (!module.isOpen() || !processor.isOpen()) {
/* 167 */       processor.handleException(playerSession, (short)10061);
/*     */       return;
/*     */     } 
/* 170 */     short eventId = request.getEventId();
/*     */     
/* 172 */     if (eventId != 10003 && eventId != 10002 && eventId != 10001 && eventId != 10010) {
/*     */       
/* 174 */       PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 175 */       int count = ((AtomicInteger)playerComponent.getStatisticRequest().getValue()).incrementAndGet();
/* 176 */       int curTime = TimeUtil.currentTime();
/* 177 */       if (curTime - ((Integer)playerComponent.getStatisticRequest().getKey()).intValue() >= 10) {
/* 178 */         playerComponent.getStatisticRequest().setKey(Integer.valueOf(curTime));
/* 179 */         ((AtomicInteger)playerComponent.getStatisticRequest().getValue()).set(0);
/* 180 */         if (!MContext.getDebug() && TimeUtil.currentTimeMillis() >= playerComponent.getLoginTime().getTime() + 25000L && count / 10 >= 35) {
/* 181 */           processor.handleException(playerSession, (short)10308);
/* 182 */           long playerId = playerSession.getPlayer().getPlayerId();
/* 183 */           if (LookUpService.isOnline(playerId))
/*     */           {
/*     */ 
/*     */             
/* 187 */             LogUtil.errorLog(new Object[] { "dispatch350", Long.valueOf(playerId), Short.valueOf(eventId), Integer.valueOf(count) });
/*     */           }
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 195 */     processor.handle(playerSession, request);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RequestBase getRequest(short requestId) {
/* 200 */     ModuleEntrance moduleEntrance = entrances.get(getModIdByMsg(requestId));
/* 201 */     if (null == moduleEntrance)
/* 202 */       return null; 
/* 203 */     return moduleEntrance.getRequest(requestId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private IModule getMsgModule(short requestId) {
/* 209 */     short msgId = getModIdByMsg(requestId);
/* 210 */     if (msgId >= this.modules.size())
/* 211 */       return null; 
/* 212 */     return this.modules.get(msgId);
/*     */   }
/*     */   
/*     */   public static short getModIdByMsg(short msgId) {
/* 216 */     return (short)(msgId / 100 % 100);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\MsgDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */