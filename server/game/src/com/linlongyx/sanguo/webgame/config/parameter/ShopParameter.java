/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ShopMysteryBean;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ShopParameter extends AbstractParameter {
/*     */   private int refreshTime;
/*     */   private int freeCount;
/*     */   private List<Integer> refreshCost;
/*     */   private int sum;
/*     */   private int crystalRefreshTime;
/*     */   private int crystalRreeCount;
/*     */   private List<Integer> crystalRefreshCost;
/*     */   private int crystalSum;
/*     */   private int bloodRefreshTime;
/*     */   private List<Integer> bloodRefreshCost;
/*     */   
/*     */   public ShopParameter() {
/*  20 */     super(31);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  25 */     this.refreshCost = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  30 */     this.crystalRefreshCost = new ArrayList<>();
/*     */ 
/*     */ 
/*     */     
/*  34 */     this.bloodRefreshCost = new ArrayList<>();
/*     */ 
/*     */ 
/*     */     
/*  38 */     this.goodsData = new HashMap<>();
/*  39 */     this.normalConfig = new HashMap<>();
/*     */     
/*  41 */     this.props = new HashMap<>();
/*  42 */     this.data_map = new HashMap<>();
/*     */     
/*  44 */     this.crystalProps = new HashMap<>();
/*  45 */     this.crystalDataMap = new HashMap<>();
/*     */     
/*  47 */     this.bloodProps = new HashMap<>();
/*  48 */     this.bloodDataMap = new HashMap<>();
/*  49 */     this.secretShopMap = new HashMap<>();
/*     */   } private int bloodSum; public Map<Integer, List<GoodsBean>> goodsData; private Map<Integer, ArrayList<Integer>> normalConfig; private Map<Integer, Integer> props; private Map<Integer, List<ShopMysteryBean>> data_map; private Map<Integer, Integer> crystalProps; private Map<Integer, List<ShopCrystalBean>> crystalDataMap; private Map<Integer, Integer> bloodProps; private Map<Integer, List<ShopHaeminBean>> bloodDataMap; private Map<Integer, List<Integer>> secretShopMap;
/*     */   public List<Integer> getGoodsData(int goodsType) {
/*  52 */     if (this.normalConfig.containsKey(Integer.valueOf(goodsType))) return this.normalConfig.get(Integer.valueOf(goodsType)); 
/*  53 */     return null;
/*     */   }
/*     */   
/*     */   public Map<Integer, ArrayList<Integer>> getNormalConfig() {
/*  57 */     return this.normalConfig;
/*     */   }
/*     */   
/*     */   public int getBloodRefreshTime() {
/*  61 */     return this.bloodRefreshTime;
/*     */   }
/*     */   
/*     */   public List<Integer> getBloodRefreshCost() {
/*  65 */     return this.bloodRefreshCost;
/*     */   }
/*     */   
/*     */   public int getBloodSum() {
/*  69 */     return this.bloodSum;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCost(int num, int type) {
/*  79 */     if (type == ShopUtil.ShopType.MysteryShop.getType()) {
/*  80 */       if (num <= this.refreshCost.size()) {
/*  81 */         return ((Integer)this.refreshCost.get(num - 1)).intValue();
/*     */       }
/*  83 */       return ((Integer)this.refreshCost.get(this.refreshCost.size() - 1)).intValue();
/*  84 */     }  if (type == ShopUtil.ShopType.CrystalSoulShop.getType()) {
/*  85 */       if (num <= this.crystalRefreshCost.size()) {
/*  86 */         return ((Integer)this.crystalRefreshCost.get(num - 1)).intValue();
/*     */       }
/*  88 */       return ((Integer)this.crystalRefreshCost.get(this.crystalRefreshCost.size() - 1)).intValue();
/*     */     } 
/*  90 */     if (num <= this.bloodRefreshCost.size()) {
/*  91 */       return ((Integer)this.bloodRefreshCost.get(num - 1)).intValue();
/*     */     }
/*  93 */     return ((Integer)this.bloodRefreshCost.get(this.bloodRefreshCost.size() - 1)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRefreshTime() {
/*  98 */     return this.refreshTime;
/*     */   }
/*     */   
/*     */   public int getFreeCount() {
/* 102 */     return this.freeCount;
/*     */   }
/*     */   
/*     */   public List<Integer> getRefreshCost() {
/* 106 */     return this.refreshCost;
/*     */   }
/*     */   
/*     */   public Map<Integer, List<GoodsBean>> getGoodsData() {
/* 110 */     return this.goodsData;
/*     */   }
/*     */   
/*     */   public int getCrystalRefreshTime() {
/* 114 */     return this.crystalRefreshTime;
/*     */   }
/*     */   
/*     */   public int getCrystalRreeCount() {
/* 118 */     return this.crystalRreeCount;
/*     */   }
/*     */   
/*     */   public List<Integer> getCrystalRefreshCost() {
/* 122 */     return this.crystalRefreshCost;
/*     */   }
/*     */   
/*     */   public int getCrystalSum() {
/* 126 */     return this.crystalSum;
/*     */   }
/*     */   
/*     */   private void initShopData() {
/* 130 */     this.data_map.clear();
/* 131 */     this.props.clear();
/*     */ 
/*     */     
/* 134 */     Map<Integer, Object> t_map = JsonTableService.getJsonTable(ShopMysteryBean.class);
/* 135 */     for (Map.Entry<Integer, Object> entry : t_map.entrySet()) {
/* 136 */       List<ShopMysteryBean> list; ShopMysteryBean shopMysteryBean = (ShopMysteryBean)entry.getValue();
/* 137 */       int level = shopMysteryBean.getLv();
/* 138 */       if (this.data_map.containsKey(Integer.valueOf(level))) {
/* 139 */         list = this.data_map.get(Integer.valueOf(level));
/*     */       } else {
/* 141 */         list = new ArrayList<>();
/*     */       } 
/* 143 */       list.add(shopMysteryBean);
/* 144 */       this.data_map.put(Integer.valueOf(level), list);
/* 145 */       if (this.props.containsKey(Integer.valueOf(level))) {
/* 146 */         this.props.put(Integer.valueOf(level), Integer.valueOf(((Integer)this.props.get(Integer.valueOf(level))).intValue() + shopMysteryBean.getProb())); continue;
/*     */       } 
/* 148 */       this.props.put(Integer.valueOf(level), Integer.valueOf(shopMysteryBean.getProb()));
/*     */     } 
/*     */ 
/*     */     
/* 152 */     this.crystalDataMap.clear();
/* 153 */     this.crystalProps.clear();
/*     */ 
/*     */     
/* 156 */     Map<Integer, Object> crystal_map = JsonTableService.getJsonTable(ShopCrystalBean.class);
/* 157 */     for (Map.Entry<Integer, Object> entry : crystal_map.entrySet()) {
/* 158 */       List<ShopCrystalBean> list1; ShopCrystalBean shopCrystalBean = (ShopCrystalBean)entry.getValue();
/* 159 */       int playerLevel = shopCrystalBean.getLv();
/* 160 */       if (this.crystalDataMap.containsKey(Integer.valueOf(playerLevel))) {
/* 161 */         list1 = this.crystalDataMap.get(Integer.valueOf(playerLevel));
/*     */       } else {
/* 163 */         list1 = new ArrayList<>();
/*     */       } 
/* 165 */       list1.add(shopCrystalBean);
/* 166 */       this.crystalDataMap.put(Integer.valueOf(playerLevel), list1);
/* 167 */       if (this.crystalProps.containsKey(Integer.valueOf(playerLevel))) {
/* 168 */         this.crystalProps.put(Integer.valueOf(playerLevel), Integer.valueOf(((Integer)this.crystalProps.get(Integer.valueOf(playerLevel))).intValue() + shopCrystalBean.getProb())); continue;
/*     */       } 
/* 170 */       this.crystalProps.put(Integer.valueOf(playerLevel), Integer.valueOf(shopCrystalBean.getProb()));
/*     */     } 
/*     */ 
/*     */     
/* 174 */     this.bloodDataMap.clear();
/* 175 */     this.bloodProps.clear();
/*     */ 
/*     */     
/* 178 */     Map<Integer, Object> bloodMap = JsonTableService.getJsonTable(ShopHaeminBean.class);
/* 179 */     for (Map.Entry<Integer, Object> entry : bloodMap.entrySet()) {
/* 180 */       List<ShopHaeminBean> list2; ShopHaeminBean shopHaeminBean = (ShopHaeminBean)entry.getValue();
/* 181 */       int playerLevel = shopHaeminBean.getLv();
/* 182 */       if (this.bloodDataMap.containsKey(Integer.valueOf(playerLevel))) {
/* 183 */         list2 = this.bloodDataMap.get(Integer.valueOf(playerLevel));
/*     */       } else {
/* 185 */         list2 = new ArrayList<>();
/*     */       } 
/* 187 */       list2.add(shopHaeminBean);
/* 188 */       this.bloodDataMap.put(Integer.valueOf(playerLevel), list2);
/* 189 */       if (this.bloodProps.containsKey(Integer.valueOf(playerLevel))) {
/* 190 */         this.bloodProps.put(Integer.valueOf(playerLevel), Integer.valueOf(((Integer)this.bloodProps.get(Integer.valueOf(playerLevel))).intValue() + shopHaeminBean.getProb())); continue;
/*     */       } 
/* 192 */       this.bloodProps.put(Integer.valueOf(playerLevel), Integer.valueOf(shopHaeminBean.getProb()));
/*     */     } 
/*     */ 
/*     */     
/* 196 */     this.secretShopMap.clear();
/*     */     
/* 198 */     Map<Integer, Object> secretMap = JsonTableService.getJsonTable(ShopSecretiBean.class);
/* 199 */     for (Iterator<Integer> iterator = secretMap.keySet().iterator(); iterator.hasNext(); ) { List<Integer> secretList; int shopLevel = ((Integer)iterator.next()).intValue();
/* 200 */       ShopSecretiBean shopSecretiBean = (ShopSecretiBean)secretMap.get(Integer.valueOf(shopLevel));
/* 201 */       if (this.secretShopMap.containsKey(Integer.valueOf(shopLevel))) {
/* 202 */         secretList = this.secretShopMap.get(Integer.valueOf(shopLevel));
/*     */       } else {
/* 204 */         secretList = new ArrayList<>();
/*     */       } 
/* 206 */       secretList.addAll(shopSecretiBean.getGoodsId().keySet());
/* 207 */       this.secretShopMap.put(Integer.valueOf(shopLevel), secretList); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public void initGoodsData() {
/* 213 */     HashedMap hashedMap = new HashedMap();
/* 214 */     this.normalConfig.clear();
/*     */     
/* 216 */     Map map = JsonTableService.getJsonTable(ShopNormalBean.class);
/* 217 */     map.values().forEach(obj -> {
/*     */           ShopNormalBean shopNormalBean = (ShopNormalBean)obj;
/*     */           if (this.normalConfig.containsKey(Integer.valueOf(shopNormalBean.getGoodsType()))) {
/*     */             ((ArrayList<Integer>)this.normalConfig.get(Integer.valueOf(shopNormalBean.getGoodsType()))).add(Integer.valueOf(shopNormalBean.getGoodsId()));
/*     */           } else {
/*     */             ArrayList<Integer> list = new ArrayList<>();
/*     */             list.add(Integer.valueOf(shopNormalBean.getGoodsId()));
/*     */             this.normalConfig.put(Integer.valueOf(shopNormalBean.getGoodsType()), list);
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/* 233 */     this.refreshTime = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/* 234 */     this.freeCount = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue());
/* 235 */     this.sum = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue());
/*     */     
/* 237 */     this.refreshCost.clear();
/* 238 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue().split(",");
/* 239 */     for (String str : strings) {
/* 240 */       this.refreshCost.add(Integer.valueOf(str));
/*     */     }
/* 242 */     this.crystalRefreshTime = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue());
/* 243 */     this.crystalRreeCount = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue());
/* 244 */     this.crystalSum = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(8))).getValue());
/*     */     
/* 246 */     this.crystalRefreshCost.clear();
/* 247 */     String[] string8 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(7))).getValue().split(",");
/* 248 */     for (String str : string8) {
/* 249 */       this.crystalRefreshCost.add(Integer.valueOf(str));
/*     */     }
/*     */     
/* 252 */     this.bloodRefreshTime = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(9))).getValue());
/* 253 */     this.bloodSum = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(11))).getValue());
/*     */     
/* 255 */     this.bloodRefreshCost.clear();
/* 256 */     String[] string10 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(10))).getValue().split(",");
/* 257 */     for (String str : string10) {
/* 258 */       this.bloodRefreshCost.add(Integer.valueOf(str));
/*     */     }
/*     */     
/* 261 */     initShopData();
/*     */     
/* 263 */     initGoodsData();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getMysteryShopIds(int playerLevel) {
/* 274 */     List<Integer> levels = new ArrayList<>(this.data_map.keySet());
/* 275 */     Collections.sort(levels);
/* 276 */     int selectLevel = ((Integer)levels.get(0)).intValue();
/* 277 */     for (Integer level : levels) {
/* 278 */       if (selectLevel < playerLevel && playerLevel <= level.intValue()) {
/* 279 */         selectLevel = level.intValue();
/*     */       }
/*     */     } 
/*     */     
/* 283 */     return generateList(this.data_map.get(Integer.valueOf(selectLevel)), ((Integer)this.props.get(Integer.valueOf(selectLevel))).intValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getCrystalShopIds(int playerLevel) {
/* 294 */     List<Integer> levels = new ArrayList<>(this.crystalDataMap.keySet());
/* 295 */     Collections.sort(levels);
/* 296 */     int selectLevel = ((Integer)levels.get(0)).intValue();
/* 297 */     for (Integer level : levels) {
/* 298 */       if (selectLevel < playerLevel && playerLevel <= level.intValue()) {
/* 299 */         selectLevel = level.intValue();
/*     */       }
/*     */     } 
/*     */     
/* 303 */     return generateCrystalList(this.crystalDataMap.get(Integer.valueOf(selectLevel)), ((Integer)this.crystalProps.get(Integer.valueOf(selectLevel))).intValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getBloodShopIds(int playerLevel) {
/* 314 */     List<Integer> levels = new ArrayList<>(this.bloodDataMap.keySet());
/* 315 */     Collections.sort(levels);
/* 316 */     int selectLevel = ((Integer)levels.get(0)).intValue();
/* 317 */     for (Integer level : levels) {
/* 318 */       if (selectLevel < playerLevel && playerLevel <= level.intValue()) {
/* 319 */         selectLevel = level.intValue();
/*     */       }
/*     */     } 
/*     */     
/* 323 */     return generateBloodList(this.bloodDataMap.get(Integer.valueOf(selectLevel)), ((Integer)this.bloodProps.get(Integer.valueOf(selectLevel))).intValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Integer> generateList(List<ShopMysteryBean> originList, int maxProp) {
/* 334 */     List<Integer> list = new ArrayList<>();
/* 335 */     for (int i = 0; i < 10000 && 
/* 336 */       list.size() != this.sum; i++) {
/*     */ 
/*     */       
/* 339 */       int id = generateOne(originList, maxProp);
/* 340 */       if (-1 != id && !list.contains(Integer.valueOf(id)))
/*     */       {
/*     */         
/* 343 */         list.add(Integer.valueOf(id)); } 
/*     */     } 
/* 345 */     Collections.sort(list);
/* 346 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Integer> generateCrystalList(List<ShopCrystalBean> originList, int maxProp) {
/* 357 */     List<Integer> list = new ArrayList<>();
/* 358 */     for (int i = 0; i < 10000 && 
/* 359 */       list.size() != this.crystalSum; i++) {
/*     */ 
/*     */       
/* 362 */       int id = generateCrystalOne(originList, maxProp);
/* 363 */       if (-1 != id && !list.contains(Integer.valueOf(id)))
/*     */       {
/*     */         
/* 366 */         list.add(Integer.valueOf(id)); } 
/*     */     } 
/* 368 */     Collections.sort(list);
/* 369 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Integer> generateBloodList(List<ShopHaeminBean> originList, int maxProp) {
/* 380 */     List<Integer> list = new ArrayList<>();
/* 381 */     for (int i = 0; i < 10000 && 
/* 382 */       list.size() != this.bloodSum; i++) {
/*     */ 
/*     */       
/* 385 */       int id = generateBloodOne(originList, maxProp);
/* 386 */       if (-1 != id && !list.contains(Integer.valueOf(id)))
/*     */       {
/*     */         
/* 389 */         list.add(Integer.valueOf(id)); } 
/*     */     } 
/* 391 */     Collections.sort(list);
/* 392 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int generateOne(List<ShopMysteryBean> originList, int maxProp) {
/* 403 */     int num = RandUtil.randNum(maxProp);
/* 404 */     for (ShopMysteryBean shopMysteryBean : originList) {
/* 405 */       if (num < shopMysteryBean.getProb()) {
/* 406 */         return shopMysteryBean.getGoodsId();
/*     */       }
/* 408 */       num -= shopMysteryBean.getProb();
/*     */     } 
/*     */     
/* 411 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int generateCrystalOne(List<ShopCrystalBean> originList, int maxProp) {
/* 422 */     int num = RandUtil.randNum(maxProp);
/* 423 */     for (ShopCrystalBean shopCrystalBean : originList) {
/* 424 */       if (num < shopCrystalBean.getProb()) {
/* 425 */         return shopCrystalBean.getGoodsId();
/*     */       }
/* 427 */       num -= shopCrystalBean.getProb();
/*     */     } 
/*     */     
/* 430 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int generateBloodOne(List<ShopHaeminBean> originList, int maxProp) {
/* 441 */     int num = RandUtil.randNum(maxProp);
/* 442 */     for (ShopHaeminBean shopHaeminBean : originList) {
/* 443 */       if (num < shopHaeminBean.getProb()) {
/* 444 */         return shopHaeminBean.getGoodsId();
/*     */       }
/* 446 */       num -= shopHaeminBean.getProb();
/*     */     } 
/*     */     
/* 449 */     return -1;
/*     */   }
/*     */   
/*     */   public Map<Integer, List<Integer>> getSecretShopMap() {
/* 453 */     return this.secretShopMap;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\ShopParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */