/*     */ package com.linlongyx.core.framework.logic.moblie;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.logic.ISession;
/*     */ import com.linlongyx.core.framework.logic.Session;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.framework.protocol.mobile.ResponseBase;
/*     */ import com.linlongyx.core.utils.ProtocolUtil;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerSession
/*     */   extends Session
/*     */   implements IPlayerSession
/*     */ {
/*     */   private ThreadLocal<List<String>> errorThreadList;
/*  22 */   protected IPlayer player = null;
/*     */   
/*     */   protected PlayerSession(PlayerSessionBuilder playerSessionBuilder) {
/*  25 */     super(playerSessionBuilder);
/*     */ 
/*     */ 
/*     */     
/*  29 */     this.errorThreadList = new ThreadLocal<>();
/*     */     this.player = playerSessionBuilder.player;
/*     */   }
/*     */   public IPlayer getPlayer() {
/*  33 */     return this.player;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlayer(IPlayer player) {
/*  38 */     this.player = player;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMessage(Object object) {}
/*     */ 
/*     */   
/*     */   public void setAttribute(String key, Object val) {
/*  47 */     this.sessionAttributes.put(key, val);
/*     */   }
/*     */   
/*     */   public Object getAttribute(String key) {
/*  51 */     return this.sessionAttributes.get(key);
/*     */   }
/*     */   
/*     */   public void clearSessionAttributes() {
/*  55 */     if (this.sessionAttributes != null) this.sessionAttributes.clear(); 
/*     */   }
/*     */   
/*     */   public List<String> getErrorParamList() {
/*  59 */     List<String> list = this.errorThreadList.get();
/*  60 */     if (list == null) {
/*  61 */       list = new ArrayList<>();
/*  62 */       this.errorThreadList.set(list);
/*     */     } 
/*  64 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendMessage(ResponseBase response) {}
/*     */   
/*     */   public static class PlayerSessionBuilder
/*     */     extends Session.SessionBuilder
/*     */   {
/*  73 */     protected IPlayer player = null;
/*     */ 
/*     */     
/*     */     public void validateAndSetValues() {
/*  77 */       super.validateAndSetValues();
/*     */     }
/*     */     
/*     */     public PlayerSession build() {
/*  81 */       return new PlayerSession(this);
/*     */     }
/*     */     
/*     */     public PlayerSessionBuilder player(IPlayer player) {
/*  85 */       this.player = player;
/*  86 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendErrorCode(short errorCode, ResponseBase response) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMessage(ResponseBase response) {
/*  98 */     if (this.tcpSender == null)
/*  99 */       return;  ByteBuf out = Unpooled.buffer();
/* 100 */     if (response.getRetCode() == 0) {
/* 101 */       ByteBuf byteBuf = Unpooled.buffer();
/* 102 */       response.serial(byteBuf);
/* 103 */       out.writeInt(response.getCheckCode());
/* 104 */       out.writeInt(response.getEventId());
/* 105 */       out.writeShort(response.getRetCode());
/* 106 */       out.writeInt(response.getCorCode());
/* 107 */       out.writeInt(byteBuf.readableBytes());
/* 108 */       out.writeBytes(byteBuf);
/*     */     } else {
/* 110 */       ByteBuf byteBuf = Unpooled.buffer();
/* 111 */       writeErrorParam(byteBuf);
/* 112 */       out.writeInt(response.getCheckCode());
/* 113 */       out.writeInt(response.getEventId());
/* 114 */       out.writeShort(response.getRetCode());
/* 115 */       out.writeInt(response.getCorCode());
/* 116 */       out.writeInt(byteBuf.readableBytes());
/* 117 */       out.writeBytes(byteBuf);
/*     */     } 
/* 119 */     this.tcpSender.sendMessage(out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeErrorParam(ByteBuf out) {
/* 129 */     List<String> errorParamList = this.errorThreadList.get();
/* 130 */     if (errorParamList == null || errorParamList.isEmpty())
/* 131 */       return;  int size_0 = errorParamList.size();
/* 132 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 133 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 135 */       String tmp_0 = errorParamList.get(index_0);
/* 136 */       ProtocolUtil.writeUTFBinary(out, tmp_0);
/*     */     } 
/* 138 */     errorParamList.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendErrorCode(short errorCode, ResponseBase response) {
/* 144 */     response.setRetCode(errorCode);
/* 145 */     sendMessage(response);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\logic\moblie\PlayerSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */