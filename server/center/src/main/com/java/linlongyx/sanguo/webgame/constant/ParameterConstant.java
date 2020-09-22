/*     */ package linlongyx.sanguo.webgame.constant;
/*     */ 
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.AbstractParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.CrossParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.DestinyParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.FightParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RunewarParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TeamParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TowerOwnerParameter;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParameterConstant
/*     */ {
/*     */   public static final int TYPE_LOGIN = 0;
/*     */   public static final int TYPE_FIGHT = 1;
/*     */   public static final int TYPE_BOSS_HOME = 3;
/*     */   public static final int TYPE_ACHIEVE = 4;
/*     */   public static final int TYPE_MAIL = 5;
/*     */   public static final int TYPE_BAG = 7;
/*     */   public static final int TYPE_RECOVERY = 8;
/*     */   public static final int TYPE_SKILL = 9;
/*     */   public static final int TYPE_GROUP = 11;
/*     */   public static final int TYPE_GENERAL = 12;
/*     */   public static final int TYPE_SINGLE_INS = 13;
/*     */   public static final int TYPE_RECRUIT = 15;
/*     */   public static final int TYPE_COMMON = 16;
/*     */   public static final int TYPE_ARENA = 17;
/*     */   public static final int TYPE_UNPARALLELED = 18;
/*     */   public static final int TYPE_WELFARE = 19;
/*     */   public static final int TYPE_RANK_ACT = 20;
/*     */   public static final int TYPE_CONTINUITY = 21;
/*     */   public static final int TYPE_TASK = 22;
/*     */   public static final int TYPE_LIMIT = 23;
/*     */   public static final int TYPE_TURNPLATE = 24;
/*     */   public static final int TYPE_FUND = 25;
/*     */   public static final int TYPE_WARPET = 30;
/*     */   public static final int TYPE_SHOP = 31;
/*     */   public static final int TYPE_FRIEND = 32;
/*     */   public static final int TYPE_PARTNER = 33;
/*     */   public static final int TYPE_PARTNER_BATTLE = 34;
/*     */   public static final int TYPE_LUCKY_TURNTABLE = 35;
/*     */   public static final int TYPE_INVITATION = 36;
/*     */   public static final int TYPE_TEAM = 37;
/*     */   public static final int TYPE_MOUNTS = 42;
/*     */   public static final int TYPE_RUNE_WAR = 45;
/*     */   public static final int TYPE_GENERAL_GIFT = 46;
/*     */   public static final int TYPE_TOWER_OWNER = 67;
/*     */   public static final int TYPE_DESTINY = 74;
/*     */   public static final int TYPE_EQUIP = 78;
/*     */   public static final int TYPE_CAT = 80;
/*     */   public static final int TYPE_CROSS = 81;
/*     */   public static final int TYPE_PARAMETER_END = 82;
/*  96 */   private static AbstractParameter[] parameters = new AbstractParameter[82];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 103 */     parameters[1] = (AbstractParameter)new FightParameter();
/* 104 */     parameters[37] = (AbstractParameter)new TeamParameter();
/* 105 */     parameters[74] = (AbstractParameter)new DestinyParameter();
/* 106 */     parameters[81] = (AbstractParameter)new CrossParameter();
/* 107 */     parameters[67] = (AbstractParameter)new TowerOwnerParameter();
/* 108 */     parameters[45] = (AbstractParameter)new RunewarParameter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void init() {
/*     */     try {
/* 117 */       for (AbstractParameter parameter : parameters) {
/* 118 */         if (parameter != null) {
/* 119 */           parameter.load();
/*     */         }
/*     */       } 
/* 122 */     } catch (Exception e) {
/* 123 */       LogUtils.errorLog(new Object[] { "parameter load", Arrays.asList(e.getStackTrace()) });
/* 124 */       e.printStackTrace();
/* 125 */       throw e;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AbstractParameter getParameter(int type) {
/* 135 */     return parameters[type];
/*     */   }
/*     */ 
/*     */   
/*     */   public static void initMap(Map<Integer, Integer> map, int param, int secParam) {
/* 140 */     ParameterBean parameterBean = (ParameterBean)JsonTableService.getJsonData(param, ParameterBean.class);
/* 141 */     String[] strings = ((ParameterBean.SecBean)parameterBean.getSec().get(Integer.valueOf(secParam))).getValue().split(";");
/* 142 */     for (String string : strings) {
/* 143 */       String[] strings2 = string.split(":");
/* 144 */       if (strings2.length > 1) {
/* 145 */         map.put(Integer.valueOf(Integer.parseInt(strings2[0])), Integer.valueOf(Integer.parseInt(strings2[1])));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void initList(List<Integer> list, int param, int secParam) {
/* 151 */     ParameterBean parameterBean = (ParameterBean)JsonTableService.getJsonData(param, ParameterBean.class);
/* 152 */     String[] strings = ((ParameterBean.SecBean)parameterBean.getSec().get(Integer.valueOf(secParam))).getValue().split(";");
/* 153 */     for (String string : strings) {
/* 154 */       list.add(Integer.valueOf(Integer.parseInt(string)));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getParameterConstant(int type, int sec) {
/* 164 */     ParameterBean parameterBean = (ParameterBean)JsonTableService.getJsonData(type, ParameterBean.class);
/* 165 */     return Integer.parseInt(((ParameterBean.SecBean)parameterBean.getSec().get(Integer.valueOf(sec))).getValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public static class SimpleAttr
/*     */   {
/*     */     public int type;
/*     */     
/*     */     public int value;
/*     */     
/*     */     public SimpleAttr(int type, int value) {
/* 176 */       setType(type);
/* 177 */       setValue(value);
/*     */     }
/*     */     
/*     */     public int getType() {
/* 181 */       return this.type;
/*     */     }
/*     */     
/*     */     public void setType(int type) {
/* 185 */       this.type = type;
/*     */     }
/*     */     
/*     */     public int getValue() {
/* 189 */       return this.value;
/*     */     }
/*     */     
/*     */     public void setValue(int value) {
/* 193 */       this.value = value;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\constant\ParameterConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */