/*     */ package com.linlongyx.sanguo.logclient.event;
/*     */ 
/*     */ import com.linlongyx.sanguo.logclient.coder.EventDecoder;
/*     */ import com.linlongyx.sanguo.logclient.coder.EventEncoder;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import java.io.IOException;
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
/*     */ public class Events
/*     */ {
/*     */   public final class EventType
/*     */   {
/*     */     public static final int chatType = 0;
/*     */     public static final int swordChatType = 1;
/*     */     public static final int sanguoChatType = 2;
/*     */   }
/*     */   
/*     */   public final class SubChatType
/*     */   {
/*     */     public static final int sysChatType = 0;
/*     */     public static final int worldChatType = 1;
/*     */     public static final int areaChatType = 2;
/*     */     public static final int orgChatType = 3;
/*     */     public static final int teamChatType = 4;
/*     */     public static final int hornChatType = 5;
/*     */     public static final int blocChatType = 6;
/*     */     public static final int personalChatType = 7;
/*     */   }
/*     */   
/*     */   public final class SwordSubChatType
/*     */   {
/*     */     public static final int swordWorldChatType = 0;
/*     */     public static final int swordCampChatType = 1;
/*     */     public static final int swordSysChatType = 2;
/*     */     public static final int swordQuestionChatType = 3;
/*     */     public static final int swordSpaChatType = 4;
/*     */     public static final int swordFriendChatType = 7;
/*     */   }
/*     */   
/*     */   public final class SanguoSubChatType
/*     */   {
/*     */     public static final int compChatType = 0;
/*     */     public static final int armyChatType = 1;
/*     */     public static final int sysChatType = 2;
/*     */     public static final int orgChatType = 3;
/*     */     public static final int personalChatType = 4;
/*     */   }
/*  62 */   private static Map<Integer, Class<? extends Event>> EVENT_ID_TO_CLASS = new HashMap<>();
/*     */   
/*     */   static {
/*  65 */     EVENT_ID_TO_CLASS.put(Integer.valueOf(getEventId(0, 0)), DefaultChatEvent.class);
/*  66 */     EVENT_ID_TO_CLASS.put(Integer.valueOf(getEventId(0, 1)), DefaultChatEvent.class);
/*  67 */     EVENT_ID_TO_CLASS.put(Integer.valueOf(getEventId(0, 2)), DefaultChatEvent.class);
/*  68 */     EVENT_ID_TO_CLASS.put(Integer.valueOf(getEventId(0, 3)), DefaultChatEvent.class);
/*  69 */     EVENT_ID_TO_CLASS.put(Integer.valueOf(getEventId(0, 4)), DefaultChatEvent.class);
/*  70 */     EVENT_ID_TO_CLASS.put(Integer.valueOf(getEventId(0, 5)), DefaultChatEvent.class);
/*  71 */     EVENT_ID_TO_CLASS.put(Integer.valueOf(getEventId(0, 6)), DefaultChatEvent.class);
/*  72 */     EVENT_ID_TO_CLASS.put(Integer.valueOf(getEventId(0, 7)), DefaultChatEvent.class);
/*     */     
/*  74 */     EVENT_ID_TO_CLASS.put(Integer.valueOf(getEventId(1, 0)), DefaultChatEvent.class);
/*  75 */     EVENT_ID_TO_CLASS.put(Integer.valueOf(getEventId(1, 1)), DefaultChatEvent.class);
/*  76 */     EVENT_ID_TO_CLASS.put(Integer.valueOf(getEventId(1, 2)), DefaultChatEvent.class);
/*  77 */     EVENT_ID_TO_CLASS.put(Integer.valueOf(getEventId(1, 3)), DefaultChatEvent.class);
/*  78 */     EVENT_ID_TO_CLASS.put(Integer.valueOf(getEventId(1, 4)), DefaultChatEvent.class);
/*  79 */     EVENT_ID_TO_CLASS.put(Integer.valueOf(getEventId(1, 7)), DefaultChatEvent.class);
/*     */     
/*  81 */     EVENT_ID_TO_CLASS.put(Integer.valueOf(getEventId(2, 0)), DefaultChatEvent.class);
/*  82 */     EVENT_ID_TO_CLASS.put(Integer.valueOf(getEventId(2, 1)), DefaultChatEvent.class);
/*     */   }
/*     */   
/*     */   public static Event decodeToEvent(int eventId, EventDecoder decoder, byte[] source) throws IOException {
/*  86 */     return (Event)decoder.read(source, (Class)EVENT_ID_TO_CLASS.getOrDefault(Integer.valueOf(eventId), DefaultEvent.class));
/*     */   }
/*     */   
/*     */   public static byte[] getBytes(EventEncoder encoder, Event event) throws IOException {
/*  90 */     ByteBuf buf = Unpooled.buffer();
/*  91 */     buf.writeInt(event.getId());
/*  92 */     buf.writeInt((int)(System.currentTimeMillis() / 1000L));
/*  93 */     buf.writeBytes(encoder.write(event));
/*  94 */     return buf.array();
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
/*     */ 
/*     */   
/*     */   public static Event getObject(EventDecoder decoder, byte[] bytes, boolean needJudgeExpire, int timeLimit, int curtime) throws IOException {
/* 108 */     ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);
/* 109 */     int eventId = byteBuf.readInt();
/* 110 */     int writeTime = byteBuf.readInt();
/* 111 */     if (needJudgeExpire && 
/* 112 */       curtime - writeTime > timeLimit) {
/* 113 */       return null;
/*     */     }
/*     */     
/* 116 */     byte[] eventBytes = new byte[byteBuf.readableBytes()];
/* 117 */     byteBuf.readBytes(eventBytes);
/* 118 */     return (Event)decoder.read(eventBytes, EVENT_ID_TO_CLASS.get(Integer.valueOf(eventId)));
/*     */   }
/*     */   
/*     */   public static int getEventType(int eventId) {
/* 122 */     return eventId >>> 24 & 0xFF;
/*     */   }
/*     */   
/*     */   public static int getSubEventType(int eventId) {
/* 126 */     return eventId << 8 >>> 8;
/*     */   }
/*     */   
/*     */   public static int getEventId(int eventType, int subEventType) {
/* 130 */     return eventType << 24 | getSubEventType(subEventType);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\logclient\event\Events.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */