/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
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
/*     */ public class BagParameter
/*     */   extends AbstractParameter
/*     */ {
/*     */   private static final int TYPE_1 = 1;
/*     */   private static final int TYPE_2 = 2;
/*     */   private static final int TYPE_4 = 4;
/*     */   private static final int TYPE_5 = 5;
/*     */   private static final int TYPE_8 = 8;
/*     */   private static final int TYPE_9 = 9;
/*     */   private static final int TYPE_10 = 10;
/*     */   private static final int TYPE_11 = 11;
/*  52 */   private int defaultSize = 0;
/*     */   
/*  54 */   private int taxItemId = 0;
/*     */   
/*     */   private Reward gridPrice;
/*     */   
/*  58 */   private Map<Integer, Integer> vipAddCount = new HashMap<>();
/*  59 */   private Map<Integer, Integer> bagBoxMaxNum = new HashMap<>();
/*  60 */   private Map<Integer, Integer> bagBoxOpenKey = new HashMap<>();
/*  61 */   private Map<Integer, Integer> bagBoxFirstId = new HashMap<>();
/*  62 */   private Map<Integer, Integer> bagBoxProps = new HashMap<>();
/*  63 */   private Map<Integer, Reward> bagRewardOpens = new HashMap<>();
/*     */   
/*     */   public int getDefaultSize() {
/*  66 */     return this.defaultSize;
/*     */   }
/*     */   
/*     */   public Reward getGridPrice() {
/*  70 */     return this.gridPrice;
/*     */   }
/*     */   
/*     */   public int getTaxItemId() {
/*  74 */     return this.taxItemId;
/*     */   }
/*     */   
/*     */   public BagParameter() {
/*  78 */     super(7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVipAddCount(int vip) {
/*  87 */     return ((Integer)this.vipAddCount.getOrDefault(Integer.valueOf(vip), Integer.valueOf(0))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBagBoxMaxNum(int type) {
/*  96 */     return ((Integer)this.bagBoxMaxNum.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBagBoxOpenKey(int type) {
/* 105 */     return ((Integer)this.bagBoxOpenKey.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBagBoxFirstId(int type) {
/* 114 */     return ((Integer)this.bagBoxFirstId.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBagBoxProp(int id) {
/* 123 */     return ((Integer)this.bagBoxProps.getOrDefault(Integer.valueOf(id), Integer.valueOf(10000))).intValue();
/*     */   }
/*     */   
/*     */   public Reward getBagRewardOpen(int itemId) {
/* 127 */     if (this.bagRewardOpens.containsKey(Integer.valueOf(itemId)))
/* 128 */       return this.bagRewardOpens.get(Integer.valueOf(itemId)); 
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/* 134 */     this.defaultSize = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/*     */     
/* 136 */     String[] stringPrice = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue().split(",");
/* 137 */     this.gridPrice = new Reward();
/* 138 */     tranform(this.gridPrice, stringPrice);
/*     */     
/* 140 */     this.taxItemId = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tranform(Reward reward, String[] strings1) {
/* 196 */     reward.type = Byte.parseByte(strings1[0]);
/* 197 */     reward.id = Integer.parseInt(strings1[1]);
/* 198 */     reward.num = Integer.parseInt(strings1[2]);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\BagParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */