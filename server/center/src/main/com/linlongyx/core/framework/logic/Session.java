/*     */ package com.linlongyx.core.framework.logic;
/*     */ 
/*     */ import com.linlongyx.core.framework.communication.MessageSender;
/*     */ import com.linlongyx.core.framework.service.IUniqueIDGeneratorService;
/*     */ import com.linlongyx.core.framework.service.impl.SessionIDGeneratorService;
/*     */ import com.linlongyx.core.utils.MD5;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import java.util.HashMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Session
/*     */   implements ISession
/*     */ {
/*     */   protected final String id;
/*     */   protected final Map<String, Object> sessionAttributes;
/*     */   protected final long creationTime;
/*     */   protected long lastReadWriteTime;
/*     */   protected volatile ISession.Status status;
/*     */   protected boolean isWriteable;
/*     */   protected volatile boolean isLogin;
/*  38 */   protected int clientIp = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   protected String key;
/*     */ 
/*     */ 
/*     */   
/*     */   protected volatile boolean isShuttingDown;
/*     */ 
/*     */ 
/*     */   
/*  50 */   protected MessageSender tcpSender = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatus(ISession.Status status) {
/*  55 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public ISession.Status getStatus() {
/*  60 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWriteable() {
/*  65 */     return this.isWriteable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWriteable(boolean writeable) {
/*  70 */     this.isWriteable = writeable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLogin() {
/*  75 */     return this.isLogin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLogin(boolean login) {
/*  80 */     this.isLogin = login;
/*     */   }
/*     */   
/*     */   public String getKey() {
/*  84 */     return this.key;
/*     */   }
/*     */   
/*     */   public void setKey(String key) {
/*  88 */     this.key = key;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTcpSender(MessageSender tcpSender) {
/*  93 */     this.tcpSender = tcpSender;
/*     */   }
/*     */   
/*     */   public MessageSender getTcpSender() {
/*  97 */     return this.tcpSender;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClientIp(int clientIp) {
/* 102 */     this.clientIp = clientIp;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getClientIp() {
/* 107 */     return this.clientIp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Session(SessionBuilder sessionBuilder) {
/* 114 */     sessionBuilder.validateAndSetValues();
/* 115 */     this.id = sessionBuilder.id;
/* 116 */     this.sessionAttributes = sessionBuilder.sessionAttributes;
/* 117 */     this.creationTime = sessionBuilder.creationTime;
/* 118 */     this.status = sessionBuilder.status;
/* 119 */     this.lastReadWriteTime = sessionBuilder.lastReadWriteTime;
/* 120 */     this.isWriteable = sessionBuilder.isWriteable;
/* 121 */     this.isShuttingDown = sessionBuilder.isShuttingDown;
/* 122 */     this.key = sessionBuilder.key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SessionBuilder
/*     */   {
/* 130 */     protected static final IUniqueIDGeneratorService ID_GENERATOR_SERVICE = (IUniqueIDGeneratorService)new SessionIDGeneratorService();
/* 131 */     protected String id = "";
/* 132 */     protected Map<String, Object> sessionAttributes = null;
/* 133 */     protected long creationTime = 0L;
/* 134 */     protected long lastReadWriteTime = 0L;
/* 135 */     protected ISession.Status status = ISession.Status.NOT_CONNECTED;
/*     */     protected boolean isWriteable = true;
/*     */     protected boolean isLogin = false;
/* 138 */     protected String key = "";
/*     */     
/*     */     protected volatile boolean isShuttingDown = false;
/*     */     
/*     */     public ISession build() {
/* 143 */       return new Session(this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void validateAndSetValues() {
/* 154 */       if (null == this.id) {
/* 155 */         this.id = String.valueOf(ID_GENERATOR_SERVICE.generateFor(Session.class));
/*     */       }
/* 157 */       if (null == this.sessionAttributes) {
/* 158 */         this.sessionAttributes = new HashMap<>();
/*     */       }
/* 160 */       this.creationTime = TimeUtil.currentTimeMillis();
/*     */     }
/*     */     
/*     */     public String getId() {
/* 164 */       return this.id;
/*     */     }
/*     */     
/*     */     public SessionBuilder id(String id) {
/* 168 */       this.id = id;
/* 169 */       return this;
/*     */     }
/*     */     
/*     */     public SessionBuilder sessionAttributes(Map<String, Object> sessionAttributes) {
/* 173 */       this.sessionAttributes = sessionAttributes;
/* 174 */       return this;
/*     */     }
/*     */     
/*     */     public SessionBuilder creationTime(long creationTime) {
/* 178 */       this.creationTime = creationTime;
/* 179 */       return this;
/*     */     }
/*     */     
/*     */     public SessionBuilder lastReadWriteTime(long lastReadWriteTime) {
/* 183 */       this.lastReadWriteTime = lastReadWriteTime;
/* 184 */       return this;
/*     */     }
/*     */     
/*     */     public SessionBuilder status(ISession.Status status) {
/* 188 */       this.status = status;
/* 189 */       return this;
/*     */     }
/*     */     
/*     */     public SessionBuilder key() {
/* 193 */       this.key = (new MD5()).toDigest(this.id + this.creationTime);
/* 194 */       return this;
/*     */     }
/*     */     
/*     */     public SessionBuilder writeable(boolean isWriteable) {
/* 198 */       this.isWriteable = isWriteable;
/* 199 */       return this;
/*     */     }
/*     */     
/*     */     public SessionBuilder isShuttingDown(boolean isShuttingDown) {
/* 203 */       this.isShuttingDown = isShuttingDown;
/* 204 */       return this;
/*     */     }
/*     */     
/*     */     public SessionBuilder isLogin(boolean isLogin) {
/* 208 */       this.isLogin = isLogin;
/* 209 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\logic\Session.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */