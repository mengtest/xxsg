/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ 
/*     */ public class LoginParameter extends AbstractParameter {
/*     */   private int bornSceneId;
/*     */   private int battleMaxCount;
/*     */   private Map<Integer, Integer> expMap;
/*     */   private int offlinePerSec;
/*     */   private Map<Integer, Integer> sexLeader;
/*     */   private int hangTime;
/*     */   private int combatItem;
/*     */   private ArrayList<Integer> costList;
/*     */   private int initCombatTimes;
/*     */   private int chatNum;
/*     */   private int initSkill;
/*     */   private ArrayList<Reward> welcomeReward;
/*     */   
/*     */   public LoginParameter() {
/*  20 */     super(0);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  25 */     this.expMap = new HashMap<>();
/*     */     
/*  27 */     this.sexLeader = new HashMap<>();
/*     */ 
/*     */ 
/*     */     
/*  31 */     this.costList = new ArrayList<>();
/*     */ 
/*     */ 
/*     */     
/*  35 */     this.welcomeReward = new ArrayList<>();
/*     */     
/*  37 */     this.coinExpMap = (Map<Integer, Integer>)new HashedMap();
/*     */     
/*  39 */     this.normalChargeId = new HashSet<>();
/*  40 */     this.mountsFixedReward = new ArrayList<>();
/*     */ 
/*     */     
/*  43 */     this.sendRebotTime = new ArrayList<>();
/*  44 */     this.dayToSecond = new HashMap<>();
/*  45 */     this.sendRebotId = new ArrayList<>();
/*  46 */     this.rebotVip = new ArrayList<>();
/*     */     
/*  48 */     this.fundAdd = new HashMap<>();
/*  49 */     this.chargeAdd = new HashMap<>();
/*     */     
/*  51 */     this.universalMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     this.serverList = new ArrayList<>();
/*  61 */     this.compensateAct = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     this.artiReward = new ArrayList<>();
/*  71 */     this.pcwbReward = new ArrayList<>();
/*     */     
/*  73 */     this.qqwbReward = new ArrayList<>();
/*     */   }
/*     */   private int defaultTaskId; private Map<Integer, Integer> coinExpMap; private int defaultVip; private Set<Integer> normalChargeId; private ArrayList<Reward> mountsFixedReward; private int changeNameItem; private int offineAddtion; private ArrayList<Integer> sendRebotTime; private Map<Integer, Integer> dayToSecond; private ArrayList<Integer> sendRebotId; private ArrayList<Integer> rebotVip; private int limitServer; private Map<Integer, Integer> fundAdd; private Map<Integer, Integer> chargeAdd; private int skipTimes; private Map<Integer, Integer> universalMap; private int groupChargeInit; private String oldServerTime; private ArrayList<Reward> questionReward; private ArrayList<Reward> shortCutReward; private ArrayList<Reward> realNameReward; private ArrayList<Reward> followReward; private ArrayList<Reward> weixinReward; private ArrayList<Reward> weixinFollowReward; private ArrayList<IntIntValue> serverList; private ArrayList<IntIntValue> compensateAct; private ArrayList<Reward> awyBindReward; private int rebateMultiple; private int rebateLimit; private int vipRebateMultiple; private int actRebate; private int rankActClose; private String continuityTime; private int starId; private ArrayList<Reward> artiReward; private ArrayList<Reward> pcwbReward;
/*     */   private int rankLevelNum;
/*     */   private ArrayList<Reward> qqwbReward;
/*     */   
/*     */   public int getCost(int num) {
/*  80 */     if (num >= this.costList.size()) {
/*  81 */       return ((Integer)this.costList.get(this.costList.size() - 1)).intValue();
/*     */     }
/*  83 */     return ((Integer)this.costList.get(num)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> GetExpList() {
/*  92 */     ArrayList<Integer> arrayList = new ArrayList<>();
/*  93 */     ArrayList<Integer> ids = new ArrayList<>(); Iterator<Integer> iterator;
/*  94 */     for (iterator = this.expMap.values().iterator(); iterator.hasNext(); ) { int num = ((Integer)iterator.next()).intValue();
/*  95 */       arrayList.add(Integer.valueOf(num)); }
/*     */     
/*  97 */     Collections.sort(arrayList);
/*  98 */     for (iterator = arrayList.iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/*  99 */       for (Iterator<Integer> iterator1 = this.expMap.keySet().iterator(); iterator1.hasNext(); ) { int expId = ((Integer)iterator1.next()).intValue();
/* 100 */         int num = ((Integer)this.expMap.get(Integer.valueOf(expId))).intValue();
/* 101 */         if (num == id) {
/* 102 */           ids.add(Integer.valueOf(expId));
/*     */         } }
/*     */        }
/*     */     
/* 106 */     return ids;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBornSceneId() {
/* 113 */     return this.bornSceneId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Reward> getWelcomeReward() {
/* 120 */     return this.welcomeReward;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDefaultTaskId() {
/* 128 */     return this.defaultTaskId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDefaultVip() {
/* 136 */     return this.defaultVip;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Integer> getNormalChargeId() {
/* 144 */     return this.normalChargeId;
/*     */   }
/*     */   
/*     */   private void initVip() {
/* 148 */     this.defaultVip = 0;
/* 149 */     Map<Integer, Object> map = JsonTableService.getJsonTable(VipAccessBean.class);
/* 150 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 151 */       VipAccessBean vipAccessBean = (VipAccessBean)entry.getValue();
/* 152 */       if (vipAccessBean.getTotal() == 0 && vipAccessBean.getVipLevel() > this.defaultVip) {
/* 153 */         this.defaultVip = vipAccessBean.getVipLevel();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void initCharge() {
/* 159 */     this.normalChargeId.clear();
/* 160 */     Map<Integer, Object> map = JsonTableService.getJsonTable(ChargeBean.class);
/* 161 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 162 */       ChargeBean chargeBean = (ChargeBean)entry.getValue();
/* 163 */       if (0 == chargeBean.getType()) {
/* 164 */         this.normalChargeId.add(Integer.valueOf(chargeBean.getId()));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOldOpenTime() {
/* 174 */     return TimeUtil.getTimeFromDate(getOldServerTime());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getContinuityTimes() {
/* 182 */     return TimeUtil.getTimeFromDate(getContinuityTime());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Reward> getQuestionReward() {
/* 189 */     return this.questionReward;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Reward> getShortCutReward() {
/* 197 */     return this.shortCutReward;
/*     */   }
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/* 202 */     this.bornSceneId = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/* 203 */     this.battleMaxCount = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue());
/*     */     
/* 205 */     this.expMap.clear();
/* 206 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue().split(";");
/* 207 */     for (String string : strings) {
/* 208 */       String[] strings2 = string.split(",");
/* 209 */       this.expMap.put(Integer.valueOf(Integer.parseInt(strings2[0])), Integer.valueOf(Integer.parseInt(strings2[1])));
/*     */     } 
/* 211 */     this.offlinePerSec = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue());
/*     */     
/* 213 */     this.sexLeader.clear();
/* 214 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue().split(";");
/* 215 */     for (String string : strings) {
/* 216 */       String[] strings2 = string.split(":");
/* 217 */       this.sexLeader.put(Integer.valueOf(Integer.parseInt(strings2[0])), Integer.valueOf(Integer.parseInt(strings2[1])));
/*     */     } 
/* 219 */     this.hangTime = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(7))).getValue());
/* 220 */     this.combatItem = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(8))).getValue());
/* 221 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(9))).getValue().split(",");
/* 222 */     for (String string : strings) {
/* 223 */       this.costList.add(Integer.valueOf(Integer.parseInt(string)));
/*     */     }
/* 225 */     this.initCombatTimes = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(10))).getValue());
/* 226 */     this.chatNum = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(11))).getValue());
/* 227 */     this.initSkill = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(12))).getValue());
/*     */     
/* 229 */     this.welcomeReward.clear();
/* 230 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(13))).getValue().split(";");
/* 231 */     for (String string : strings) {
/* 232 */       Reward reward = new Reward();
/* 233 */       String[] strings2 = string.split(",");
/* 234 */       tranform(reward, strings2);
/* 235 */       this.welcomeReward.add(reward);
/*     */     } 
/*     */ 
/*     */     
/* 239 */     this.defaultTaskId = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(15))).getValue());
/* 240 */     String[] string5 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(16))).getValue().split(":");
/* 241 */     this.coinExpMap.put(Integer.valueOf(Integer.parseInt(string5[0])), Integer.valueOf(Integer.parseInt(string5[1])));
/*     */     
/* 243 */     this.mountsFixedReward.clear();
/* 244 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(22))).getValue().split(";");
/* 245 */     for (String string : strings) {
/* 246 */       Reward reward = new Reward();
/* 247 */       String[] strings2 = string.split(",");
/* 248 */       tranform(reward, strings2);
/* 249 */       this.mountsFixedReward.add(reward);
/*     */     } 
/* 251 */     this.changeNameItem = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(23))).getValue());
/* 252 */     this.offineAddtion = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(24))).getValue());
/*     */     
/* 254 */     this.sendRebotTime.clear();
/* 255 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(25))).getValue().split(":");
/* 256 */     for (String string : strings) {
/* 257 */       this.sendRebotTime.add(Integer.valueOf(Integer.parseInt(string)));
/*     */     }
/*     */     
/* 260 */     this.dayToSecond.clear();
/* 261 */     String[] string26 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(26))).getValue().split(";");
/* 262 */     for (String s : string26) {
/* 263 */       String[] str = s.split(":");
/* 264 */       this.dayToSecond.put(Integer.valueOf(Integer.parseInt(str[0])), Integer.valueOf(Integer.parseInt(str[1])));
/*     */     } 
/* 266 */     this.sendRebotId.clear();
/* 267 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(27))).getValue().split(";");
/* 268 */     for (String string : strings) {
/* 269 */       this.sendRebotId.add(Integer.valueOf(Integer.parseInt(string)));
/*     */     }
/* 271 */     this.rebotVip.clear();
/* 272 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(28))).getValue().split(":");
/* 273 */     for (String string : strings) {
/* 274 */       this.rebotVip.add(Integer.valueOf(Integer.parseInt(string)));
/*     */     }
/*     */     
/* 277 */     this.limitServer = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(29))).getValue());
/*     */     
/* 279 */     this.fundAdd.clear();
/* 280 */     String[] string30 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(30))).getValue().split(";");
/* 281 */     for (String s : string30) {
/* 282 */       String[] str = s.split(":");
/* 283 */       this.fundAdd.put(Integer.valueOf(Integer.parseInt(str[0])), Integer.valueOf(Integer.parseInt(str[1])));
/*     */     } 
/*     */     
/* 286 */     this.chargeAdd.clear();
/* 287 */     String[] string32 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(32))).getValue().split(";");
/* 288 */     for (String s : string32) {
/* 289 */       String[] str = s.split(":");
/* 290 */       this.chargeAdd.put(Integer.valueOf(Integer.parseInt(str[0])), Integer.valueOf(Integer.parseInt(str[1])));
/*     */     } 
/* 292 */     this.skipTimes = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(33))).getValue());
/*     */     
/* 294 */     this.universalMap.clear();
/* 295 */     String[] string34 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(34))).getValue().split(";");
/* 296 */     for (String s : string34) {
/* 297 */       String[] str = s.split(":");
/* 298 */       this.universalMap.put(Integer.valueOf(Integer.parseInt(str[0])), Integer.valueOf(Integer.parseInt(str[1])));
/*     */     } 
/*     */     
/* 301 */     this.groupChargeInit = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(35))).getValue());
/*     */     
/* 303 */     this.oldServerTime = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(37))).getValue();
/*     */     
/* 305 */     String[] string38 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(38))).getValue().split(";");
/* 306 */     this.questionReward = new ArrayList<>();
/* 307 */     for (String rewardStr : string38) {
/* 308 */       if (!rewardStr.equals("")) {
/* 309 */         String[] tmp = rewardStr.split(",");
/* 310 */         if (tmp.length == 3) {
/* 311 */           Reward reward = new Reward();
/* 312 */           reward.type = Byte.parseByte(tmp[0]);
/* 313 */           reward.id = Integer.parseInt(tmp[1]);
/* 314 */           reward.num = Integer.parseInt(tmp[2]);
/* 315 */           this.questionReward.add(reward);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 320 */     String[] string39 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(39))).getValue().split(";");
/* 321 */     this.shortCutReward = new ArrayList<>();
/* 322 */     for (String rewardStr : string39) {
/* 323 */       if (!rewardStr.equals("")) {
/* 324 */         String[] tmp = rewardStr.split(",");
/* 325 */         if (tmp.length == 3) {
/* 326 */           Reward reward = new Reward();
/* 327 */           reward.type = Byte.parseByte(tmp[0]);
/* 328 */           reward.id = Integer.parseInt(tmp[1]);
/* 329 */           reward.num = Integer.parseInt(tmp[2]);
/* 330 */           this.shortCutReward.add(reward);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 335 */     String[] string40 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(40))).getValue().split(";");
/* 336 */     this.followReward = new ArrayList<>();
/* 337 */     for (String rewardStr : string40) {
/* 338 */       if (!rewardStr.equals("")) {
/* 339 */         String[] tmp = rewardStr.split(",");
/* 340 */         if (tmp.length == 3) {
/* 341 */           Reward reward = new Reward();
/* 342 */           reward.type = Byte.parseByte(tmp[0]);
/* 343 */           reward.id = Integer.parseInt(tmp[1]);
/* 344 */           reward.num = Integer.parseInt(tmp[2]);
/* 345 */           this.followReward.add(reward);
/*     */         } 
/*     */       } 
/*     */     } 
/* 349 */     String[] string41 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(41))).getValue().split(";");
/* 350 */     this.realNameReward = new ArrayList<>();
/* 351 */     for (String rewardStr : string41) {
/* 352 */       if (!rewardStr.equals("")) {
/* 353 */         String[] tmp = rewardStr.split(",");
/* 354 */         if (tmp.length == 3) {
/* 355 */           Reward reward = new Reward();
/* 356 */           reward.type = Byte.parseByte(tmp[0]);
/* 357 */           reward.id = Integer.parseInt(tmp[1]);
/* 358 */           reward.num = Integer.parseInt(tmp[2]);
/* 359 */           this.realNameReward.add(reward);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 364 */     String[] string44 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(44))).getValue().split(";");
/* 365 */     this.weixinReward = new ArrayList<>();
/* 366 */     for (String rewardStr : string44) {
/* 367 */       if (!rewardStr.equals("")) {
/* 368 */         String[] tmp = rewardStr.split(",");
/* 369 */         if (tmp.length == 3) {
/* 370 */           Reward reward = new Reward();
/* 371 */           reward.type = Byte.parseByte(tmp[0]);
/* 372 */           reward.id = Integer.parseInt(tmp[1]);
/* 373 */           reward.num = Integer.parseInt(tmp[2]);
/* 374 */           this.weixinReward.add(reward);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 379 */     String[] string45 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(45))).getValue().split(";");
/* 380 */     this.weixinFollowReward = new ArrayList<>();
/* 381 */     for (String rewardStr : string45) {
/* 382 */       if (!rewardStr.equals("")) {
/* 383 */         String[] tmp = rewardStr.split(",");
/* 384 */         if (tmp.length == 3) {
/* 385 */           Reward reward = new Reward();
/* 386 */           reward.type = Byte.parseByte(tmp[0]);
/* 387 */           reward.id = Integer.parseInt(tmp[1]);
/* 388 */           reward.num = Integer.parseInt(tmp[2]);
/* 389 */           this.weixinFollowReward.add(reward);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 394 */     String[] string46 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(46))).getValue().split(";");
/* 395 */     this.serverList = new ArrayList<>();
/* 396 */     for (String rewardStr : string46) {
/* 397 */       if (!rewardStr.equals("")) {
/* 398 */         String[] tmp = rewardStr.split(",");
/* 399 */         if (tmp.length == 2) {
/* 400 */           IntIntValue intIntValue = new IntIntValue();
/* 401 */           intIntValue.key = Integer.parseInt(tmp[0]);
/* 402 */           intIntValue.value = Integer.parseInt(tmp[1]);
/* 403 */           this.serverList.add(intIntValue);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 408 */     String[] string47 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(47))).getValue().split(";");
/* 409 */     this.compensateAct = new ArrayList<>();
/* 410 */     for (String rewardStr : string47) {
/* 411 */       if (!rewardStr.equals("")) {
/* 412 */         String[] tmp = rewardStr.split(",");
/* 413 */         if (tmp.length == 2) {
/* 414 */           IntIntValue intIntValue = new IntIntValue();
/* 415 */           intIntValue.key = Integer.parseInt(tmp[0]);
/* 416 */           intIntValue.value = Integer.parseInt(tmp[1]);
/* 417 */           this.compensateAct.add(intIntValue);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 422 */     String[] string48 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(48))).getValue().split(";");
/* 423 */     this.awyBindReward = new ArrayList<>();
/* 424 */     for (String rewardStr : string48) {
/* 425 */       if (!rewardStr.equals("")) {
/* 426 */         String[] tmp = rewardStr.split(",");
/* 427 */         if (tmp.length == 3) {
/* 428 */           Reward reward = new Reward();
/* 429 */           reward.type = Byte.parseByte(tmp[0]);
/* 430 */           reward.id = Integer.parseInt(tmp[1]);
/* 431 */           reward.num = Integer.parseInt(tmp[2]);
/* 432 */           this.awyBindReward.add(reward);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 437 */     this.rebateMultiple = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(49))).getValue());
/* 438 */     this.rebateLimit = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(50))).getValue());
/* 439 */     this.vipRebateMultiple = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(51))).getValue());
/* 440 */     this.actRebate = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(52))).getValue());
/* 441 */     this.rankActClose = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(53))).getValue());
/* 442 */     this.continuityTime = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(54))).getValue();
/*     */     
/* 444 */     this.starId = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(55))).getValue());
/*     */     
/* 446 */     String[] string56 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(56))).getValue().split(";");
/* 447 */     this.artiReward = new ArrayList<>();
/* 448 */     for (String rewardStr : string56) {
/* 449 */       if (!rewardStr.equals("")) {
/* 450 */         String[] tmp = rewardStr.split(",");
/* 451 */         if (tmp.length == 3) {
/* 452 */           Reward reward = new Reward();
/* 453 */           reward.type = Byte.parseByte(tmp[0]);
/* 454 */           reward.id = Integer.parseInt(tmp[1]);
/* 455 */           reward.num = Integer.parseInt(tmp[2]);
/* 456 */           this.artiReward.add(reward);
/*     */         } 
/*     */       } 
/*     */     } 
/* 460 */     String[] string57 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(57))).getValue().split(";");
/* 461 */     this.pcwbReward.clear();
/* 462 */     for (String rewardStr : string57) {
/* 463 */       if (!rewardStr.equals("")) {
/* 464 */         String[] tmp = rewardStr.split(",");
/* 465 */         if (tmp.length == 3) {
/* 466 */           Reward reward = new Reward();
/* 467 */           reward.type = Byte.parseByte(tmp[0]);
/* 468 */           reward.id = Integer.parseInt(tmp[1]);
/* 469 */           reward.num = Integer.parseInt(tmp[2]);
/* 470 */           this.pcwbReward.add(reward);
/*     */         } 
/*     */       } 
/*     */     } 
/* 474 */     String[] string59 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(59))).getValue().split(";");
/* 475 */     this.qqwbReward.clear();
/* 476 */     for (String rewardStr : string59) {
/* 477 */       if (!rewardStr.equals("")) {
/* 478 */         String[] tmp = rewardStr.split(",");
/* 479 */         if (tmp.length == 3) {
/* 480 */           Reward reward = new Reward();
/* 481 */           reward.type = Byte.parseByte(tmp[0]);
/* 482 */           reward.id = Integer.parseInt(tmp[1]);
/* 483 */           reward.num = Integer.parseInt(tmp[2]);
/* 484 */           this.qqwbReward.add(reward);
/*     */         } 
/*     */       } 
/*     */     } 
/* 488 */     this.rankLevelNum = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(58))).getValue());
/*     */     
/* 490 */     initVip();
/* 491 */     initCharge();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBattleMaxCount() {
/* 496 */     return this.battleMaxCount;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getExpMap() {
/* 500 */     return this.expMap;
/*     */   }
/*     */   
/*     */   public int getOfflinePerSec() {
/* 504 */     return this.offlinePerSec;
/*     */   }
/*     */   
/*     */   public int getLeaderBySex(int sex) {
/* 508 */     return ((Integer)this.sexLeader.getOrDefault(Integer.valueOf(sex), Integer.valueOf(1))).intValue();
/*     */   }
/*     */   
/*     */   public int getHangTime() {
/* 512 */     return this.hangTime;
/*     */   }
/*     */   
/*     */   public int getCombatItem() {
/* 516 */     return this.combatItem;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getCostList() {
/* 520 */     return this.costList;
/*     */   }
/*     */   
/*     */   public int getInitCombatTimes() {
/* 524 */     return this.initCombatTimes;
/*     */   }
/*     */   
/*     */   public int getInitSkill() {
/* 528 */     return this.initSkill;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getCoinExpMap() {
/* 532 */     return this.coinExpMap;
/*     */   }
/*     */   
/*     */   public ArrayList<Reward> getMountsFixedReward() {
/* 536 */     return this.mountsFixedReward;
/*     */   }
/*     */   
/*     */   public int getChangeNameItem() {
/* 540 */     return this.changeNameItem;
/*     */   }
/*     */   
/*     */   public int getOffineAddtion() {
/* 544 */     return this.offineAddtion;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getSendRebotTime() {
/* 548 */     return this.sendRebotTime;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getDayToSecond() {
/* 552 */     return this.dayToSecond;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getSendRebotId() {
/* 556 */     return this.sendRebotId;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getRebotVip() {
/* 560 */     return this.rebotVip;
/*     */   }
/*     */   
/*     */   public int getLimitServer() {
/* 564 */     return this.limitServer;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getFundAdd() {
/* 568 */     return this.fundAdd;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getChargeAdd() {
/* 572 */     return this.chargeAdd;
/*     */   }
/*     */   
/*     */   public int getSkipTimes() {
/* 576 */     return this.skipTimes;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getUniversalMap() {
/* 580 */     return this.universalMap;
/*     */   }
/*     */   
/*     */   public int getGroupChargeInit() {
/* 584 */     return this.groupChargeInit;
/*     */   }
/*     */   
/*     */   public String getOldServerTime() {
/* 588 */     return this.oldServerTime;
/*     */   }
/*     */   
/*     */   public ArrayList<Reward> getRealNameReward() {
/* 592 */     return this.realNameReward;
/*     */   }
/*     */   
/*     */   public ArrayList<Reward> getFollowReward() {
/* 596 */     return this.followReward;
/*     */   }
/*     */   
/*     */   public ArrayList<Reward> getWeixinReward() {
/* 600 */     return this.weixinReward;
/*     */   }
/*     */   
/*     */   public ArrayList<Reward> getWeixinFollowReward() {
/* 604 */     return this.weixinFollowReward;
/*     */   }
/*     */   
/*     */   public ArrayList<IntIntValue> getServerList() {
/* 608 */     return this.serverList;
/*     */   }
/*     */   
/*     */   public ArrayList<IntIntValue> getCompensateAct() {
/* 612 */     return this.compensateAct;
/*     */   }
/*     */   
/*     */   public ArrayList<Reward> getAwyBindReward() {
/* 616 */     return this.awyBindReward;
/*     */   }
/*     */   
/*     */   public int getRebateMultiple() {
/* 620 */     return this.rebateMultiple;
/*     */   }
/*     */   
/*     */   public int getRebateLimit() {
/* 624 */     return this.rebateLimit;
/*     */   }
/*     */   
/*     */   public int getVipRebateMultiple() {
/* 628 */     return this.vipRebateMultiple;
/*     */   }
/*     */   
/*     */   public int getActRebate() {
/* 632 */     return this.actRebate;
/*     */   }
/*     */   
/*     */   public int getRankActClose() {
/* 636 */     return this.rankActClose;
/*     */   }
/*     */   
/*     */   public String getContinuityTime() {
/* 640 */     return this.continuityTime;
/*     */   }
/*     */   
/*     */   public int getStarId() {
/* 644 */     return this.starId;
/*     */   }
/*     */   
/*     */   public ArrayList<Reward> getArtiReward() {
/* 648 */     return this.artiReward;
/*     */   }
/*     */   
/*     */   public ArrayList<Reward> getPcwbReward() {
/* 652 */     return this.pcwbReward;
/*     */   }
/*     */   
/*     */   public int getRankLevelNum() {
/* 656 */     return this.rankLevelNum;
/*     */   }
/*     */   
/*     */   public ArrayList<Reward> getQqwbReward() {
/* 660 */     return this.qqwbReward;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\LoginParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */