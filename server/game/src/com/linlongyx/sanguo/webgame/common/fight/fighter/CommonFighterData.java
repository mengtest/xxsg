/*     */ package com.linlongyx.sanguo.webgame.common.fight.fighter;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import java.io.Serializable;
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
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class CommonFighterData
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private long pid;
/*     */   private long id;
/*     */   private byte type;
/*     */   private byte hurtType;
/*  25 */   private Map<Integer, Integer> skillMap = new HashMap<>();
/*     */   
/*     */   private short level;
/*     */   
/*     */   private String head;
/*     */   
/*     */   private String name;
/*     */   
/*     */   private long fightValue;
/*     */   
/*     */   private byte vip;
/*     */   private byte sex;
/*     */   private int quality;
/*     */   private int fashionId;
/*     */   private int firstHandVal;
/*     */   private Map<Integer, Integer> talisman;
/*  41 */   private long[] baseAttrs = new long[AttributeType.BASE_ATTR_END.getType()];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   private long[] attrs = new long[AttributeType.ATTRIB_TYPE_END.getType()];
/*     */ 
/*     */ 
/*     */   
/*     */   public CommonFighterData() {}
/*     */ 
/*     */   
/*     */   public CommonFighterData(long pid, long id, byte vip, byte sex, String head, String name, long fightValue, byte type, byte hurtType, Map<Integer, Integer> skillMap, short level, long[] baseAttrs, long[] attrs, int quality, int firstHandVal, int fashionId, Map<Integer, Integer> talisman) {
/*  54 */     this.pid = pid;
/*  55 */     this.id = id;
/*  56 */     this.vip = vip;
/*  57 */     this.sex = sex;
/*  58 */     this.head = head;
/*  59 */     this.name = name;
/*  60 */     this.fightValue = fightValue;
/*  61 */     this.type = type;
/*  62 */     this.hurtType = hurtType;
/*  63 */     this.skillMap = skillMap;
/*  64 */     this.level = level;
/*  65 */     this.baseAttrs = baseAttrs;
/*  66 */     this.attrs = attrs;
/*  67 */     this.quality = quality;
/*  68 */     this.firstHandVal = firstHandVal;
/*  69 */     this.fashionId = fashionId;
/*  70 */     this.talisman = talisman;
/*     */   }
/*     */ 
/*     */   
/*     */   public CommonFighterData(long pid, long id, byte type, short level, long[] baseAttrs, long[] attrs, Map<Integer, Integer> talisman) {
/*  75 */     this.pid = pid;
/*  76 */     this.id = id;
/*  77 */     this.type = type;
/*  78 */     this.level = level;
/*  79 */     this.baseAttrs = baseAttrs;
/*  80 */     this.attrs = attrs;
/*  81 */     this.talisman = talisman;
/*     */   }
/*     */ 
/*     */   
/*     */   public CommonFighterData(long pid, long id, byte type, short star) {
/*  86 */     this.pid = pid;
/*  87 */     this.id = id;
/*  88 */     this.type = type;
/*  89 */     this.level = star;
/*     */   }
/*     */   
/*     */   public long getPid() {
/*  93 */     return this.pid;
/*     */   }
/*     */   
/*     */   public void setPid(long pid) {
/*  97 */     this.pid = pid;
/*     */   }
/*     */   
/*     */   public long getId() {
/* 101 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(long id) {
/* 105 */     this.id = id;
/*     */   }
/*     */   
/*     */   public byte getType() {
/* 109 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(byte type) {
/* 113 */     this.type = type;
/*     */   }
/*     */   
/*     */   public byte getHurtType() {
/* 117 */     return this.hurtType;
/*     */   }
/*     */   
/*     */   public void setHurtType(byte hurtType) {
/* 121 */     this.hurtType = hurtType;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getSkillMap() {
/* 125 */     return this.skillMap;
/*     */   }
/*     */   
/*     */   public void setSkillMap(Map<Integer, Integer> skillMap) {
/* 129 */     this.skillMap = skillMap;
/*     */   }
/*     */   
/*     */   public short getLevel() {
/* 133 */     return this.level;
/*     */   }
/*     */   
/*     */   public void setLevel(short level) {
/* 137 */     this.level = level;
/*     */   }
/*     */   
/*     */   public String getHead() {
/* 141 */     return this.head;
/*     */   }
/*     */   
/*     */   public void setHead(String head) {
/* 145 */     this.head = head;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 149 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 153 */     this.name = name;
/*     */   }
/*     */   
/*     */   public long getFightValue() {
/* 157 */     return this.fightValue;
/*     */   }
/*     */   
/*     */   public void setFightValue(long fightValue) {
/* 161 */     this.fightValue = fightValue;
/*     */   }
/*     */   
/*     */   public byte getVip() {
/* 165 */     return this.vip;
/*     */   }
/*     */   
/*     */   public void setVip(byte vip) {
/* 169 */     this.vip = vip;
/*     */   }
/*     */   
/*     */   public byte getSex() {
/* 173 */     return this.sex;
/*     */   }
/*     */   
/*     */   public void setSex(byte sex) {
/* 177 */     this.sex = sex;
/*     */   }
/*     */   
/*     */   public int getQuality() {
/* 181 */     return this.quality;
/*     */   }
/*     */   
/*     */   public void setQuality(int quality) {
/* 185 */     this.quality = quality;
/*     */   }
/*     */   
/*     */   public int getFirstHandVal() {
/* 189 */     return this.firstHandVal;
/*     */   }
/*     */   
/*     */   public void setFirstHandVal(int firstHandVal) {
/* 193 */     this.firstHandVal = firstHandVal;
/*     */   }
/*     */   
/*     */   public long[] getBaseAttrs() {
/* 197 */     return this.baseAttrs;
/*     */   }
/*     */   
/*     */   public void setBaseAttrs(long[] baseAttrs) {
/* 201 */     this.baseAttrs = baseAttrs;
/*     */   }
/*     */   
/*     */   public long[] getAttrs() {
/* 205 */     return this.attrs;
/*     */   }
/*     */   
/*     */   public void setAttrs(long[] attrs) {
/* 209 */     this.attrs = attrs;
/*     */   }
/*     */   
/*     */   public int getFashionId() {
/* 213 */     return this.fashionId;
/*     */   }
/*     */   
/*     */   public void setFashionId(int fashionId) {
/* 217 */     this.fashionId = fashionId;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getTalisman() {
/* 221 */     return this.talisman;
/*     */   }
/*     */   
/*     */   public void setTalisman(Map<Integer, Integer> talisman) {
/* 225 */     this.talisman = talisman;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\CommonFighterData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */