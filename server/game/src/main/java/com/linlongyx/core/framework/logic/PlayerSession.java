/*     */ package com.linlongyx.core.framework.logic;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.framework.protocol.mobile.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import java.time.LocalTime;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerSession
/*     */   extends Session
/*     */   implements IPlayerSession
/*     */ {
/*  17 */   protected IPlayer player = null;
/*     */   
/*     */   protected PlayerSession(PlayerSessionBuilder playerSessionBuilder) {
/*  20 */     super(playerSessionBuilder);
/*  21 */     this.player = playerSessionBuilder.player;
/*     */   }
/*     */ 
/*     */   
/*     */   public IPlayer getPlayer() {
/*  26 */     return this.player;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlayer(IPlayer player) {
/*  31 */     this.player = player;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendMessage(Object object) {
/*  36 */     sendMessage((ResponseBase)object);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendMessage(ResponseBase response) {
/*  41 */     ByteBuf byteBuf = Unpooled.buffer();
/*  42 */     if (response.retCode == 0) {
/*  43 */       ByteBuf out = Unpooled.buffer();
/*  44 */       response.serial(out);
/*  45 */       byteBuf.writeShort(response.eventResponseId);
/*  46 */       byteBuf.writeByte(response.codeType);
/*  47 */       byteBuf.writeShort(response.retCode);
/*  48 */       byteBuf.writeShort(out.readableBytes());
/*  49 */       byteBuf.writeBytes(out);
/*     */     } else {
/*  51 */       byteBuf.writeShort(response.eventResponseId);
/*  52 */       byteBuf.writeByte(response.codeType);
/*  53 */       byteBuf.writeShort(response.retCode);
/*     */     } 
/*  55 */     if (this.tcpSender != null) {
/*  56 */       this.tcpSender.sendMessage(byteBuf);
/*  57 */       if (response.retCode != 0)
/*  58 */         LogUtils.debugLog(new Object[] { "Send errorCode:" + response.retCode + ", responseId:" + response.eventResponseId }); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class PlayerSessionBuilder
/*     */     extends Session.SessionBuilder {
/*  64 */     protected IPlayer player = null;
/*     */ 
/*     */     
/*     */     public void validateAndSetValues() {
/*  68 */       super.validateAndSetValues();
/*     */     }
/*     */     
/*     */     public IPlayerSession build() {
/*  72 */       return new PlayerSession(this);
/*     */     }
/*     */     
/*     */     public PlayerSessionBuilder player(IPlayer player) {
/*  76 */       this.player = player;
/*  77 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendErrorCode(short errorCode, ResponseBase response) {
/*  84 */     response.setRetCode(errorCode);
/*  85 */     sendMessage(response);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendMessage(ResponseBase response) {
/*  90 */     ByteBuf out = Unpooled.buffer();
/*  91 */     if (response.getRetCode() == 0) {
/*  92 */       ByteBuf byteBuf = Unpooled.buffer();
/*  93 */       response.serial(byteBuf);
/*  94 */       out.writeInt(response.getCheckCode());
/*  95 */       out.writeInt(response.getEventId());
/*  96 */       out.writeShort(response.getRetCode());
/*  97 */       out.writeInt(response.getCorCode());
/*  98 */       out.writeShort(byteBuf.readableBytes());
/*  99 */       out.writeBytes(byteBuf);
/*     */     } else {
/* 101 */       out.writeInt(response.getCheckCode());
/* 102 */       out.writeInt(response.getEventId());
/* 103 */       out.writeShort(response.getRetCode());
/* 104 */       out.writeInt(response.getCorCode());
/* 105 */       out.writeShort(0);
/*     */     } 
/* 107 */     this.tcpSender.sendMessage(out);
/* 108 */     if (AppContext.getDebug()) {
/* 109 */       System.err.println(LocalTime.now() + "|" + Thread.currentThread().getName() + " | " + Integer.toHexString(response.getEventId()) + ",send respone data=" + response + " retCode=" + response.getRetCode() + " corCode=" + response.getCorCode());
/*     */     }
/*     */   }
/*     */   
/*     */   public void sendErrorCode(short errorCode, ResponseBase response) {
/* 114 */     response.setRetCode(errorCode);
/* 115 */     sendMessage(response);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\logic\PlayerSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */