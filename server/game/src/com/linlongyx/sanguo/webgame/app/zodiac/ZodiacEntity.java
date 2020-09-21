/*     */ package com.linlongyx.sanguo.webgame.app.zodiac;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_zodiac", prefix = "zodiac_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*     */ public class ZodiacEntity
/*     */   implements IMapEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   @TableField(isKey = true)
/*     */   private final transient int id;
/*  21 */   private Map<Integer, Integer> zodiacGoods = new HashMap<>();
/*  22 */   private Map<Integer, Integer> zodiacShop = new HashMap<>();
/*  23 */   private Map<Integer, Integer> zodiaTasks = new HashMap<>();
/*  24 */   private Map<Integer, Integer> zodiaState = new HashMap<>();
/*     */   private int FirstLever;
/*     */   private boolean open;
/*     */   
/*     */   public ZodiacEntity(long playerId, int id) {
/*  29 */     this.playerId = playerId;
/*  30 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getPlayerId() {
/*  35 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public int getId() {
/*  39 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, Integer> getZodiacGoods() {
/*  46 */     return this.zodiacGoods;
/*     */   }
/*     */   
/*     */   public void setZodiacGoods(Map<Integer, Integer> zodiacGoods) {
/*  50 */     this.zodiacGoods = zodiacGoods;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getZodiacShop() {
/*  54 */     return this.zodiacShop;
/*     */   }
/*     */   
/*     */   public void setZodiacShop(Map<Integer, Integer> zodiacShop) {
/*  58 */     this.zodiacShop = zodiacShop;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getZodiaTasks() {
/*  62 */     return this.zodiaTasks;
/*     */   }
/*     */   
/*     */   public void setZodiaTasks(Map<Integer, Integer> zodiaTasks) {
/*  66 */     this.zodiaTasks = zodiaTasks;
/*     */   }
/*     */   
/*     */   public int getFirstLever() {
/*  70 */     return this.FirstLever;
/*     */   }
/*     */   
/*     */   public void setFirstLever(int firstLever) {
/*  74 */     this.FirstLever = firstLever;
/*     */   }
/*     */   
/*     */   public boolean isOpen() {
/*  78 */     return this.open;
/*     */   }
/*     */   
/*     */   public void setOpen(boolean open) {
/*  82 */     this.open = open;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getZodiaState() {
/*  86 */     return this.zodiaState;
/*     */   }
/*     */   
/*     */   public void setZodiaState(Map<Integer, Integer> zodiaState) {
/*  90 */     this.zodiaState = zodiaState;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  96 */     return "ZodiacEntity{playerId=" + this.playerId + ", id=" + this.id + ", zodiacGoods=" + this.zodiacGoods + ", zodiacShop=" + this.zodiacShop + ", zodiaTasks=" + this.zodiaTasks + ", zodiaState=" + this.zodiaState + ", FirstLever=" + this.FirstLever + ", open=" + this.open + '}';
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
/*     */   public Object mapKey() {
/* 110 */     return Integer.valueOf(getId());
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\zodiac\ZodiacEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */