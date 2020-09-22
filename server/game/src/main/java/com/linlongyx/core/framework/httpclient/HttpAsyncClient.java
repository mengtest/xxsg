/*     */ package com.linlongyx.core.framework.httpclient;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.nio.charset.CodingErrorAction;
/*     */ import java.util.List;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import org.apache.http.Consts;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.auth.AuthScope;
/*     */ import org.apache.http.auth.Credentials;
/*     */ import org.apache.http.auth.UsernamePasswordCredentials;
/*     */ import org.apache.http.client.CookieStore;
/*     */ import org.apache.http.client.CredentialsProvider;
/*     */ import org.apache.http.client.config.RequestConfig;
/*     */ import org.apache.http.client.entity.UrlEncodedFormEntity;
/*     */ import org.apache.http.client.methods.HttpGet;
/*     */ import org.apache.http.client.methods.HttpPost;
/*     */ import org.apache.http.client.methods.HttpUriRequest;
/*     */ import org.apache.http.client.protocol.HttpClientContext;
/*     */ import org.apache.http.config.ConnectionConfig;
/*     */ import org.apache.http.config.Lookup;
/*     */ import org.apache.http.config.Registry;
/*     */ import org.apache.http.config.RegistryBuilder;
/*     */ import org.apache.http.cookie.Cookie;
/*     */ import org.apache.http.impl.auth.BasicSchemeFactory;
/*     */ import org.apache.http.impl.auth.DigestSchemeFactory;
/*     */ import org.apache.http.impl.auth.KerberosSchemeFactory;
/*     */ import org.apache.http.impl.auth.NTLMSchemeFactory;
/*     */ import org.apache.http.impl.auth.SPNegoSchemeFactory;
/*     */ import org.apache.http.impl.client.BasicCookieStore;
/*     */ import org.apache.http.impl.client.BasicCredentialsProvider;
/*     */ import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
/*     */ import org.apache.http.impl.nio.client.HttpAsyncClients;
/*     */ import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
/*     */ import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
/*     */ import org.apache.http.impl.nio.reactor.IOReactorConfig;
/*     */ import org.apache.http.message.BasicNameValuePair;
/*     */ import org.apache.http.nio.conn.NHttpClientConnectionManager;
/*     */ import org.apache.http.nio.conn.NoopIOSessionStrategy;
/*     */ import org.apache.http.nio.conn.SchemeIOSessionStrategy;
/*     */ import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
/*     */ import org.apache.http.nio.reactor.ConnectingIOReactor;
/*     */ import org.apache.http.nio.reactor.IOReactorException;
/*     */ import org.apache.http.protocol.HttpContext;
/*     */ import org.apache.http.ssl.SSLContexts;
/*     */ import org.apache.http.util.EntityUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class HttpAsyncClient {
/*  52 */   private final Logger logger = LoggerFactory.getLogger(HttpAsyncClient.class);
/*     */   
/*     */   private CloseableHttpAsyncClient asyncClient;
/*     */   
/*     */   private CloseableHttpAsyncClient proxyAsyncClient;
/*     */   
/*     */   HttpAsyncClient(CredentialsEntity entity) {
/*     */     try {
/*  60 */       this.asyncClient = createAsyncClient(HttpClientConstant.defaultPoolSize, HttpClientConstant.defaultMaxPerRoute, HttpClientConstant.defaultSocketTimeout, HttpClientConstant.defaultConnectTimeout, false, null);
/*     */       
/*  62 */       if (entity != null) {
/*  63 */         this.proxyAsyncClient = createAsyncClient(HttpClientConstant.defaultPoolSize, HttpClientConstant.defaultMaxPerRoute, HttpClientConstant.defaultSocketTimeout, HttpClientConstant.defaultConnectTimeout, true, entity);
/*     */       }
/*     */     }
/*  66 */     catch (HttpClientException e) {
/*  67 */       this.logger.error(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   HttpAsyncClient() {
/*  72 */     this(null);
/*     */   }
/*     */   
/*     */   public org.apache.http.nio.client.HttpAsyncClient getAsyncClient() {
/*  76 */     return (org.apache.http.nio.client.HttpAsyncClient)this.asyncClient;
/*     */   }
/*     */   
/*     */   public org.apache.http.nio.client.HttpAsyncClient getProxyAsyncClient() {
/*  80 */     return (org.apache.http.nio.client.HttpAsyncClient)this.proxyAsyncClient;
/*     */   }
/*     */   
/*     */   public org.apache.http.nio.client.HttpAsyncClient getProxyAsyncClient(CredentialsEntity entity) throws IOReactorException, HttpClientException {
/*  84 */     if (this.proxyAsyncClient == null) {
/*  85 */       synchronized (this) {
/*  86 */         if (this.proxyAsyncClient == null) {
/*  87 */           this.proxyAsyncClient = createAsyncClient(HttpClientConstant.defaultPoolSize, HttpClientConstant.defaultMaxPerRoute, HttpClientConstant.defaultSocketTimeout, HttpClientConstant.defaultConnectTimeout, true, entity);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  92 */     return (org.apache.http.nio.client.HttpAsyncClient)this.proxyAsyncClient;
/*     */   }
/*     */   
/*     */   private CloseableHttpAsyncClient createAsyncClient(int poolSize, int maxPerRoute, int socketTimeout, int connectTimeout, boolean proxy, CredentialsEntity credentialsEntity) throws HttpClientException {
/*     */     try {
/*  97 */       RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
/*     */       
/*  99 */       SSLContext sslContext = SSLContexts.createDefault();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 106 */       Registry<SchemeIOSessionStrategy> sessionStrategyRegistry = RegistryBuilder.create().register("http", NoopIOSessionStrategy.INSTANCE).register("https", new SSLIOSessionStrategy(sslContext)).build();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 111 */       IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setIoThreadCount(Runtime.getRuntime().availableProcessors()).build();
/*     */       
/* 113 */       DefaultConnectingIOReactor defaultConnectingIOReactor = new DefaultConnectingIOReactor(ioReactorConfig);
/* 114 */       PoolingNHttpClientConnectionManager conMgr = new PoolingNHttpClientConnectionManager((ConnectingIOReactor)defaultConnectingIOReactor, null, sessionStrategyRegistry, null);
/*     */ 
/*     */       
/* 117 */       if (poolSize > 0) {
/* 118 */         conMgr.setMaxTotal(poolSize);
/*     */       }
/*     */       
/* 121 */       if (maxPerRoute > 0) {
/* 122 */         conMgr.setDefaultMaxPerRoute(maxPerRoute);
/*     */       } else {
/* 124 */         conMgr.setDefaultMaxPerRoute(10);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 129 */       ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE).setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8).build();
/*     */       
/* 131 */       conMgr.setDefaultConnectionConfig(connectionConfig);
/*     */       
/* 133 */       if (proxy) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 141 */         Registry registry = RegistryBuilder.create().register("Basic", new BasicSchemeFactory()).register("Digest", new DigestSchemeFactory()).register("NTLM", new NTLMSchemeFactory()).register("Negotiate", new SPNegoSchemeFactory()).register("Kerberos", new KerberosSchemeFactory()).build();
/*     */ 
/*     */         
/* 144 */         UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(credentialsEntity.getUsername(), credentialsEntity.getPassword());
/*     */         
/* 146 */         BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
/* 147 */         basicCredentialsProvider.setCredentials(AuthScope.ANY, (Credentials)credentials);
/*     */         
/* 149 */         return HttpAsyncClients.custom().setConnectionManager((NHttpClientConnectionManager)conMgr)
/* 150 */           .setDefaultCredentialsProvider((CredentialsProvider)basicCredentialsProvider)
/* 151 */           .setDefaultAuthSchemeRegistry((Lookup)registry)
/* 152 */           .setProxy(new HttpHost(credentialsEntity.getHost(), credentialsEntity.getPort()))
/* 153 */           .setDefaultCookieStore((CookieStore)new BasicCookieStore())
/* 154 */           .setDefaultRequestConfig(requestConfig).build();
/*     */       } 
/* 156 */       return HttpAsyncClients.custom().setConnectionManager((NHttpClientConnectionManager)conMgr)
/* 157 */         .setDefaultCookieStore((CookieStore)new BasicCookieStore())
/* 158 */         .setDefaultRequestConfig(requestConfig).build();
/*     */     }
/* 160 */     catch (IOReactorException e) {
/* 161 */       throw new HttpClientException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void asyncReq(String baseUrl, boolean isPost, List<BasicNameValuePair> urlParams, List<BasicNameValuePair> postBody, List<Cookie> cookies, HttpClientCallback callback) throws HttpClientException {
/* 169 */     if (baseUrl == null) {
/* 170 */       this.logger.warn("we don't have base url, check config");
/* 171 */       throw new HttpClientException("missing base url");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 176 */     this.asyncClient.start();
/*     */     
/* 178 */     HttpClientContext localContext = HttpClientContext.create();
/* 179 */     if (cookies != null && cookies.size() > 0) {
/* 180 */       BasicCookieStore cookieStore = new BasicCookieStore();
/* 181 */       for (Cookie cookie : cookies) {
/* 182 */         cookieStore.addCookie(cookie);
/*     */       }
/* 184 */       localContext.setCookieStore((CookieStore)cookieStore);
/*     */     } 
/*     */     try {
/*     */       HttpGet httpGet;
/* 188 */       if (isPost) {
/* 189 */         HttpPost httpPost = new HttpPost(baseUrl);
/*     */         
/* 191 */         if (null != postBody) {
/* 192 */           this.logger.debug("exeAsyncReq post postBody={}", postBody);
/* 193 */           UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postBody, "UTF-8");
/*     */           
/* 195 */           httpPost.setEntity((HttpEntity)entity);
/*     */         } 
/*     */         
/* 198 */         if (null != urlParams)
/*     */         {
/* 200 */           String getUrl = EntityUtils.toString((HttpEntity)new UrlEncodedFormEntity(urlParams));
/*     */           
/* 202 */           httpPost.setURI(new URI(httpPost.getURI().toString() + "?" + getUrl));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 207 */         httpGet = new HttpGet(baseUrl);
/*     */         
/* 209 */         if (null != urlParams) {
/*     */ 
/*     */           
/* 212 */           String getUrl = EntityUtils.toString((HttpEntity)new UrlEncodedFormEntity(urlParams));
/*     */           
/* 214 */           httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + getUrl));
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 219 */       this.logger.debug("exeAsyncReq getparams:" + httpGet.getURI());
/*     */       
/* 221 */       this.asyncClient.execute((HttpUriRequest)httpGet, (HttpContext)localContext, callback);
/* 222 */     } catch (Exception e) {
/* 223 */       this.logger.error("post error ", e.getCause());
/* 224 */       throw new HttpClientException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\httpclient\HttpAsyncClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */