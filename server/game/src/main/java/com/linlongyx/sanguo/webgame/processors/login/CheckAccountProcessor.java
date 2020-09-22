/*    */ package com.linlongyx.sanguo.webgame.processors.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.Code;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.CheckAccountRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.CheckAccountResponse;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.jdbc.core.JdbcTemplate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CheckAccountProcessor
/*    */   extends ProcessorBase<CheckAccountRequest, CheckAccountResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new CheckAccountResponse();
/*    */   }
/*    */   
/* 30 */   MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/* 31 */   JdbcTemplate template = this.mysql.getTemplate();
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CheckAccountRequest request) {
/* 36 */     String regex = "^[A-Za-z0-9]+$";
/* 37 */     if (!request.accountId.matches(regex)) {
/* 38 */       return 10026;
/*    */     }
/* 40 */     if (!request.pwd.matches(regex)) {
/* 41 */       return 10027;
/*    */     }
/*    */     
/*    */     try {
/* 45 */       String selectSql = "SELECT account,passward,userId FROM tb_account where account = '" + request.accountId + "'";
/*    */ 
/*    */       
/* 48 */       List<Map<String, Object>> info = this.template.queryForList(selectSql);
/* 49 */       if (info.isEmpty()) {
/* 50 */         int userId = TimeUtil.currentTime();
/* 51 */         insert(request.accountId, request.pwd, userId);
/* 52 */         ((CheckAccountResponse)this.response).userId = userId;
/* 53 */         ((CheckAccountResponse)this.response).loginTime = userId;
/* 54 */         ((CheckAccountResponse)this.response).white = 1;
/* 55 */         ((CheckAccountResponse)this.response).serverId = Integer.parseInt(MContext.getServerId());
/* 56 */         ((CheckAccountResponse)this.response).serverStatus = 0;
/* 57 */         ((CheckAccountResponse)this.response).sign = Code.getInstance().getCode(((CheckAccountResponse)this.response).userId + "" + ((CheckAccountResponse)this.response).loginTime + "" + ((CheckAccountResponse)this.response).white + "" + MContext.getSecretKey());
/*    */       } else {
/* 59 */         for (Map<String, Object> map : info) {
/* 60 */           String pwd = (String)map.get("passward");
/* 61 */           if (!pwd.equals(request.pwd)) {
/* 62 */             return 10029;
/*    */           }
/* 64 */           int time = TimeUtil.currentTime();
/* 65 */           ((CheckAccountResponse)this.response).userId = ((Long)map.get("userId")).longValue();
/* 66 */           ((CheckAccountResponse)this.response).loginTime = time;
/* 67 */           ((CheckAccountResponse)this.response).white = 1;
/* 68 */           ((CheckAccountResponse)this.response).serverId = Integer.parseInt(MContext.getServerId());
/* 69 */           ((CheckAccountResponse)this.response).serverStatus = 0;
/* 70 */           ((CheckAccountResponse)this.response).sign = Code.getInstance().getCode(((CheckAccountResponse)this.response).userId + "" + ((CheckAccountResponse)this.response).loginTime + "" + ((CheckAccountResponse)this.response).white + "" + MContext.getSecretKey());
/*    */         } 
/*    */       } 
/* 73 */     } catch (Exception e) {
/* 74 */       LogUtil.errorLog(new Object[] { "CheckAccountProcessor::CheckAccountProcessor", Arrays.toString((Object[])e.getStackTrace()) });
/* 75 */       e.printStackTrace();
/*    */     } 
/* 77 */     return 0;
/*    */   }
/*    */   
/*    */   public void insert(String accountId, String passward, long userId) {
/*    */     try {
/* 82 */       String inserSql = "INSERT INTO tb_account (`account`,`passward`,`userId`) VALUES ('" + accountId + "','" + passward + "'," + userId + ")";
/* 83 */       this.template.update(inserSql);
/* 84 */     } catch (Exception e) {
/* 85 */       LogUtil.errorLog(new Object[] { "CheckAccountProcessor::insert", e.getMessage() });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\CheckAccountProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */