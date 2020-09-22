/*     */ package com.linlongyx.sanguo.webgame.processors;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.IModule;
/*     */ import com.linlongyx.core.framework.base.IProcessor;
/*     */ import com.linlongyx.core.framework.base.Module;
/*     */ import com.linlongyx.core.framework.base.ModuleEntrance;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MsgDispatcher
/*     */ {
/*  20 */   private static final Logger logger = LoggerFactory.getLogger(MsgDispatcher.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  29 */   private static final List<ModuleEntrance> entrances = new ArrayList<>(); private List<IModule> modules; static {
/*  30 */     int moduleSize = 0;
/*  31 */     for (MsgProcessorRegister register : MsgProcessorRegister.values()) {
/*  32 */       short type = getModIdByMsg(register.getMsgCode());
/*  33 */       if (moduleSize < type) {
/*  34 */         moduleSize = type;
/*     */       }
/*     */     } 
/*  37 */     for (int i = 0; i < moduleSize + 1; i++) {
/*  38 */       entrances.add(i, new ModuleEntrance());
/*     */     }
/*     */     
/*  41 */     for (MsgProcessorRegister register : MsgProcessorRegister.values()) {
/*  42 */       short requestId = register.getMsgCode();
/*  43 */       short moduleId = getModIdByMsg(requestId);
/*  44 */       ModuleEntrance moduleEntrance = entrances.get(moduleId);
/*  45 */       moduleEntrance.init(requestId, register.getRequest());
/*     */     } 
/*  47 */     logger.info("初始化 消息处理器成功");
/*     */   }
/*     */   private IPlayerSession playerSession; private long oldTime;
/*     */   public MsgDispatcher() throws Exception {
/*  51 */     this.modules = new ArrayList<>();
/*  52 */     initModules();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initModules() {
/*  59 */     int moduleSize = 0;
/*  60 */     for (MsgProcessorRegister register : MsgProcessorRegister.values()) {
/*  61 */       short type = getModIdByMsg(register.getMsgCode());
/*  62 */       if (moduleSize < type)
/*  63 */         moduleSize = type; 
/*     */     } 
/*  65 */     for (int i = 0; i < moduleSize + 1; i++) {
/*  66 */       ModuleEntrance moduleEntrance = entrances.get(i);
/*  67 */       this.modules.add(i, new Module(i, moduleEntrance.getModuleEntrance()));
/*     */     } 
/*     */     
/*  70 */     for (MsgProcessorRegister register : MsgProcessorRegister.values()) {
/*  71 */       addToModule(register);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addToModule(MsgProcessorRegister register) {
/*  79 */     short moduleId = getModIdByMsg(register.getMsgCode());
/*  80 */     IModule module = this.modules.get(moduleId);
/*  81 */     ModuleEntrance moduleEntrance = entrances.get(moduleId);
/*  82 */     IProcessor processor = getProcessor(register.getMsgProcessor());
/*  83 */     if (null == processor)
/*  84 */       return;  processor.setEntrance(moduleEntrance.getEntrance(register.getMsgCode()));
/*  85 */     module.addProcessor(register.getMsgCode(), processor);
/*     */   }
/*     */   
/*     */   public IProcessor getProcessor(Class<IProcessor> processor) {
/*  89 */     if (null != processor) {
/*     */       try {
/*  91 */         return processor.newInstance();
/*  92 */       } catch (InstantiationException|IllegalAccessException e) {
/*  93 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*  96 */     return null;
/*     */   }
/*     */   
/*     */   public void setClientIp(int clientIp) {
/* 100 */     this.playerSession.setClientIp(clientIp);
/*     */   }
/*     */   
/*     */   public void setPlayerSession(IPlayerSession playerSession) {
/* 104 */     this.playerSession = playerSession;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setModuleOpen(short moduleId, boolean openFlag) {
/* 114 */     ModuleEntrance moduleEntrance = entrances.get(moduleId);
/* 115 */     if (null == moduleEntrance)
/*     */       return; 
/* 117 */     moduleEntrance.setModuleEntrance(openFlag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setProcessorOpen(short msgId, boolean openFlag) {
/* 127 */     short moduleId = getModIdByMsg(msgId);
/* 128 */     ModuleEntrance moduleEntrance = entrances.get(moduleId);
/* 129 */     if (null == moduleEntrance)
/*     */       return; 
/* 131 */     moduleEntrance.setEntrance(msgId, openFlag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispatch(IPlayerSession playerSession, RequestBase request) {
/* 141 */     short requestId = request.getEventId();
/* 142 */     IModule module = getMsgModule(requestId);
/* 143 */     if (null == module) {
/* 144 */       logger.error("module is null when request id is " + requestId);
/*     */       
/*     */       return;
/*     */     } 
/* 148 */     IProcessor processor = module.getProcessor(requestId);
/* 149 */     if (null == processor) {
/* 150 */       logger.error("processor is null when request id is " + requestId);
/*     */       
/*     */       return;
/*     */     } 
/* 154 */     if (!module.isOpen() || !processor.isOpen()) {
/* 155 */       processor.handleException(playerSession, (short)10061);
/*     */       
/*     */       return;
/*     */     } 
/* 159 */     processor.handle(playerSession, request);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RequestBase getRequest(short requestId) {
/* 164 */     ModuleEntrance moduleEntrance = entrances.get(getModIdByMsg(requestId));
/* 165 */     if (null == moduleEntrance)
/* 166 */       return null; 
/* 167 */     return moduleEntrance.getRequest(requestId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private IModule getMsgModule(short requestId) {
/* 173 */     short msgId = getModIdByMsg(requestId);
/* 174 */     if (msgId >= this.modules.size())
/* 175 */       return null; 
/* 176 */     return this.modules.get(msgId);
/*     */   }
/*     */   
/*     */   public static short getModIdByMsg(short msgId) {
/* 180 */     return (short)(msgId / 100 % 100);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\MsgDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */