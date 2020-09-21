/*     */ package com.linlongyx.sanguo.webgame.processors.rune;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RuneBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RuneLevelBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.rune.RuneSysLevelUpRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.rune.RuneSysLevelUpResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RuneSysLevelUpProcessor
/*     */   extends ProcessorBase<RuneSysLevelUpRequest, RuneSysLevelUpResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  32 */     this.response = (ResponseBase)new RuneSysLevelUpResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, RuneSysLevelUpRequest request) {
/*  37 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 69)) {
/*  38 */       return 10061;
/*     */     }
/*  40 */     RuneBagComponent runeBagComponent = (RuneBagComponent)playerSession.getPlayer().createIfNotExist(RuneBagComponent.class);
/*     */     
/*  42 */     int num = request.num;
/*  43 */     if (num != 1 && num != 5) {
/*  44 */       return 11808;
/*     */     }
/*     */     
/*  47 */     if (0L >= request.mid)
/*  48 */       return 17806; 
/*  49 */     RuneBagEntity runeBagEntity = runeBagComponent.getEntity(request.mid);
/*  50 */     if (null == runeBagEntity) {
/*  51 */       return 17806;
/*     */     }
/*  53 */     RuneBean runeBean = (RuneBean)JsonTableService.getJsonData(runeBagEntity.getItemId(), RuneBean.class);
/*  54 */     if (null == runeBean) {
/*  55 */       return 17806;
/*     */     }
/*  57 */     if (runeBean.getMlevel() <= runeBagEntity.getLevel()) {
/*  58 */       return 17808;
/*     */     }
/*     */     
/*  61 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(runeBagEntity.getItemId(), ItemBean.class);
/*  62 */     if (null == itemBean) {
/*  63 */       return 17806;
/*     */     }
/*  65 */     ArrayList<Reward> allCosts = new ArrayList<>();
/*  66 */     int runeLevel = runeBagEntity.getLevel();
/*  67 */     int levelUp = 0;
/*  68 */     for (int i = 0; i < num; i++) {
/*  69 */       RuneLevelBean runeLevelBean = (RuneLevelBean)JsonTableService.getJsonData(runeLevel, RuneLevelBean.class);
/*  70 */       runeLevel++;
/*  71 */       RuneLevelBean runeLevelBean2 = (RuneLevelBean)JsonTableService.getJsonData(runeLevel, RuneLevelBean.class);
/*  72 */       if (null == runeLevelBean2) {
/*     */         break;
/*     */       }
/*  75 */       if (runeBean.getMlevel() < runeLevel) {
/*     */         break;
/*     */       }
/*  78 */       levelUp++;
/*  79 */       ArrayList<Reward> costs = FinanceUtil.transformRatio(runeLevelBean.getUpCost(), runeBean.getCostCoin());
/*  80 */       allCosts.addAll(costs);
/*     */     } 
/*     */     
/*  83 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*     */ 
/*     */     
/*  86 */     short code = CostUtil.checkRewards(allCosts, playerSession, bagComponent);
/*  87 */     if (0 != code) {
/*  88 */       return code;
/*     */     }
/*     */     
/*  91 */     CostUtil.costs(allCosts, playerSession, bagComponent, ResourceEvent.RuneLevelCost);
/*     */     
/*  93 */     runeBagEntity.setLevel(runeBagEntity.getLevel() + levelUp);
/*  94 */     runeBagComponent.updateLevelToDB(runeBagEntity);
/*  95 */     AttrUpdateUtil.refreshRune(playerSession);
/*  96 */     runeBagComponent.updateHole(runeBagEntity);
/*  97 */     ((RuneSysLevelUpResponse)this.response).runeItem = RuneUtil.tranform(runeBagEntity);
/*  98 */     ((RuneSysLevelUpResponse)this.response).num = num;
/*  99 */     return 0;
/*     */   }
/*     */   
/*     */   protected void process(IPlayerSession playerSession, RuneSysLevelUpRequest request) throws Exception {
/* 103 */     short retCode = handleRequest(playerSession, request);
/* 104 */     ((RuneSysLevelUpResponse)this.response).setRetCode(retCode);
/* 105 */     playerSession.sendMessage(this.response);
/*     */     
/* 107 */     if (retCode != 0) {
/*     */       return;
/*     */     }
/* 110 */     RuneUtil.sysList(playerSession);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rune\RuneSysLevelUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */