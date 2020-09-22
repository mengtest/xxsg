/*     */ package com.linlongyx.sanguo.webgame.app.gm;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.SystemUtil;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*     */ import com.linlongyx.sanguo.webgame.quartz.job.FiveMinutesJob;
/*     */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.Calendar;
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
/*     */ public class SysGm
/*     */   implements IGm
/*     */ {
/*     */   public boolean checkChangeTimeAllow() {
/*  31 */     boolean isTimeServer = false;
/*  32 */     String osName = System.getProperty("os.name");
/*     */     try {
/*  34 */       Process proc = null;
/*  35 */       if (osName.matches("^(?i)Windows.*$")) {
/*  36 */         proc = Runtime.getRuntime().exec("ipconfig");
/*     */       } else {
/*  38 */         proc = Runtime.getRuntime().exec("ifconfig");
/*     */       } 
/*     */ 
/*     */       
/*  42 */       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream())); String ls_1;
/*  43 */       while ((ls_1 = bufferedReader.readLine()) != null) {
/*  44 */         if (ls_1.contains("HWaddr")) {
/*  45 */           String hwaddr = ls_1.substring(ls_1.indexOf("HWaddr ") + 7).trim();
/*  46 */           if ("52:54:00:E3:D4:A4".equals(hwaddr) || "52:54:00:D7:92:CD".equals(hwaddr) || "FA:16:3E:70:8C:3F".equals(hwaddr) || "52:54:00:0E:7B:E9".equals(hwaddr)) {
/*  47 */             isTimeServer = true;
/*  48 */             System.out.println("HWaddr = " + hwaddr);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*  53 */       bufferedReader.close();
/*  54 */       proc.waitFor();
/*  55 */     } catch (IOException|InterruptedException e) {
/*  56 */       e.printStackTrace();
/*     */     } 
/*  58 */     return isTimeServer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void gm(IPlayerSession playerSession, String[] strings) {
/*  63 */     if (strings[2].equals("time")) {
/*  64 */       if (!checkChangeTimeAllow()) {
/*     */         return;
/*     */       }
/*  67 */       String osName = System.getProperty("os.name");
/*  68 */       if (strings[3].equals("set")) {
/*     */         try {
/*  70 */           String cmd = "";
/*  71 */           if (osName.matches("^(?i)Windows.*$")) {
/*  72 */             if (strings.length == 5) {
/*     */               
/*  74 */               Calendar calendar = Calendar.getInstance();
/*     */               
/*  76 */               String[] strings1 = strings[4].split(":");
/*  77 */               if (strings1.length != 3) {
/*     */                 return;
/*     */               }
/*  80 */               calendar.set(11, Integer.parseInt(strings1[0]));
/*  81 */               calendar.set(12, Integer.parseInt(strings1[1]));
/*  82 */               calendar.set(13, Integer.parseInt(strings1[2]));
/*  83 */               if (calendar.getTimeInMillis() < TimeUtil.currentTimeMillis()) {
/*     */                 return;
/*     */               }
/*     */               
/*  87 */               cmd = "cmd /c time " + strings[4];
/*  88 */               Runtime.getRuntime().exec(cmd);
/*  89 */             } else if (strings.length == 6) {
/*     */               
/*  91 */               Calendar calendar = Calendar.getInstance();
/*     */               
/*  93 */               String[] strings1 = strings[4].split("-");
/*  94 */               if (strings1.length != 3) {
/*     */                 return;
/*     */               }
/*  97 */               calendar.set(1, Integer.parseInt(strings1[0]));
/*  98 */               calendar.set(2, Integer.parseInt(strings1[1]) - 1);
/*  99 */               calendar.set(5, Integer.parseInt(strings1[2]));
/* 100 */               if (calendar.getTimeInMillis() < TimeUtil.currentTimeMillis()) {
/*     */                 return;
/*     */               }
/*     */               
/* 104 */               cmd = "cmd /c date " + strings[4];
/* 105 */               Runtime.getRuntime().exec(cmd);
/*     */               
/* 107 */               cmd = "cmd /c time " + strings[5];
/* 108 */               Runtime.getRuntime().exec(cmd);
/*     */             }
/*     */           
/* 111 */           } else if (strings.length == 5) {
/*     */             
/* 113 */             Calendar calendar = Calendar.getInstance();
/*     */             
/* 115 */             String[] strings1 = strings[4].split(":");
/* 116 */             if (strings1.length != 3) {
/*     */               return;
/*     */             }
/* 119 */             calendar.set(11, Integer.parseInt(strings1[0]));
/* 120 */             calendar.set(12, Integer.parseInt(strings1[1]));
/* 121 */             calendar.set(13, Integer.parseInt(strings1[2]));
/* 122 */             if (calendar.getTimeInMillis() < TimeUtil.currentTimeMillis()) {
/*     */               return;
/*     */             }
/* 125 */             cmd = "date -s " + strings[4];
/* 126 */             Runtime.getRuntime().exec(cmd);
/* 127 */           } else if (strings.length == 6) {
/* 128 */             Calendar calendar = Calendar.getInstance();
/*     */             
/* 130 */             String[] strings1 = strings[4].split("-");
/* 131 */             if (strings1.length != 3) {
/*     */               return;
/*     */             }
/* 134 */             calendar.set(1, Integer.parseInt(strings1[0]));
/* 135 */             calendar.set(2, Integer.parseInt(strings1[1]) - 1);
/* 136 */             calendar.set(5, Integer.parseInt(strings1[2]));
/* 137 */             if (calendar.getTimeInMillis() < TimeUtil.currentTimeMillis()) {
/*     */               return;
/*     */             }
/*     */             
/* 141 */             String dateString = strings[4].replace("-", "");
/* 142 */             cmd = "date -s " + dateString;
/* 143 */             Runtime.getRuntime().exec(cmd);
/*     */             
/* 145 */             cmd = "date -s " + strings[5];
/* 146 */             Runtime.getRuntime().exec(cmd);
/*     */           }
/*     */         
/* 149 */         } catch (IOException e) {
/* 150 */           e.printStackTrace();
/*     */         } 
/* 152 */       } else if (!strings[3].equals("reset")) {
/*     */         
/* 154 */         if (strings[3].equals("date")) {
/* 155 */           if (!checkChangeTimeAllow()) {
/*     */             return;
/*     */           }
/* 158 */           if (osName.matches("^(?i)Windows.*$")) {
/* 159 */             String cmd = "";
/*     */             
/* 161 */             Calendar calendar = Calendar.getInstance();
/*     */             
/* 163 */             String[] strings1 = strings[4].split("-");
/* 164 */             if (strings1.length != 3) {
/*     */               return;
/*     */             }
/* 167 */             calendar.set(1, Integer.parseInt(strings1[0]));
/* 168 */             calendar.set(2, Integer.parseInt(strings1[1]) - 1);
/* 169 */             calendar.set(5, Integer.parseInt(strings1[2]));
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             try {
/* 175 */               cmd = "cmd /c date " + strings[4];
/* 176 */               Runtime.getRuntime().exec(cmd);
/*     */               
/* 178 */               cmd = "cmd /c time 00:00:00";
/* 179 */               Runtime.getRuntime().exec(cmd);
/* 180 */             } catch (IOException e) {
/* 181 */               e.printStackTrace();
/*     */             } 
/*     */           } else {
/* 184 */             SystemUtil.executeLinuxShell("date -s " + strings[4]);
/*     */           } 
/*     */         } 
/*     */       } 
/* 188 */     } else if (strings[2].equals("logout")) {
/*     */ 
/*     */ 
/*     */       
/* 192 */       String cmd = strings[3];
/* 193 */       if ("user".equals(cmd)) {
/* 194 */         long id = Long.parseLong(strings[4]);
/* 195 */         LookUpService.beUserLogout(id);
/* 196 */       } else if ("player".equals(cmd)) {
/* 197 */         long id = Long.parseLong(strings[4]);
/* 198 */         LookUpService.bePlayerLogout(id);
/* 199 */       } else if ("all".equals(cmd)) {
/* 200 */         LookUpService.allLogout();
/*     */       } 
/* 202 */     } else if (strings[2].equals("openTime")) {
/* 203 */       Calendar calendar = Calendar.getInstance();
/*     */       
/* 205 */       String[] strings1 = strings[3].split("-");
/* 206 */       if (strings1.length != 3) {
/*     */         return;
/*     */       }
/* 209 */       calendar.set(Integer.parseInt(strings1[0]), Integer.parseInt(strings1[1]) - 1, Integer.parseInt(strings1[2]), 0, 0, 0);
/* 210 */       MContext.setOpenTimeInt((int)(calendar.getTimeInMillis() / 1000L));
/* 211 */       MContext.setOpenTime(strings[3] + " 00:00:00");
/* 212 */       ConstantService.update("openTime", MContext.getOpenTime());
/* 213 */       JsonTableService.hotImportAllJsonTable();
/* 214 */       ParameterConstant.init();
/* 215 */       RankActUtil.initRankActMap();
/*     */     }
/* 217 */     else if (strings[2].equals("reset0")) {
/* 218 */       playerSession.getPlayer().reset(0);
/* 219 */     } else if (strings[2].equals("reset5")) {
/* 220 */       playerSession.getPlayer().reset(18000);
/* 221 */     } else if (strings[2].equals("reset")) {
/* 222 */       LookUpService.reset(playerSession);
/* 223 */     } else if (strings[2].equals("fiveMinutes")) {
/* 224 */       FiveMinutesJob fiveMinutesJob = new FiveMinutesJob();
/* 225 */       fiveMinutesJob.process();
/* 226 */     } else if (strings[2].equals("restart")) {
/*     */       
/* 228 */       if ("in".equals(strings[3]))
/* 229 */       { SystemUtil.executeLinuxShell("sh /data/game/sanguo/start.sh restart"); }
/*     */       
/* 231 */       else if ("out".equals(strings[3]))
/* 232 */       { SystemUtil.executeLinuxShell("ssh root@120.92.139.226 sh /data/game/test/time/start.sh restart test time"); } 
/* 233 */     } else if (strings[2].equals("syncData")) {
/*     */       
/* 235 */       if (!checkChangeTimeAllow())
/*     */         return; 
/* 237 */       SystemUtil.executeLinuxShell("sh /data/game/sanguo/syncData.sh " + strings[3]);
/* 238 */     } else if (strings[2].equals("ptime")) {
/*     */       
/* 240 */       SystemUtil.executeLinuxShell("sh /data/rsync/server/push_time.sh /data3/sanguo_dev/target/ 192.168.0.13 " + strings[3] + " sanguo");
/* 241 */     } else if (strings[2].equals("updatetime")) {
/*     */       
/* 243 */       SystemUtil.executeLinuxShell("ssh root@192.168.0.13 sh /data/rsync/update.sh sanguo " + strings[3]);
/* 244 */     } else if (strings[2].equals("refresh")) {
/*     */       
/* 246 */       JsonTableService.hotImportAllJsonTable();
/* 247 */       ParameterConstant.init();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\SysGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */