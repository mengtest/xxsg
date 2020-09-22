/*     */ package com.linlongyx.core.framework.httpclient;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.nio.charset.CodingErrorAction;
/*     */ import java.security.GeneralSecurityException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.util.List;
/*     */ import javax.net.ssl.HostnameVerifier;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.auth.AuthScope;
/*     */ import org.apache.http.auth.Credentials;
/*     */ import org.apache.http.auth.UsernamePasswordCredentials;
/*     */ import org.apache.http.client.CookieStore;
/*     */ import org.apache.http.client.CredentialsProvider;
/*     */ import org.apache.http.client.HttpClient;
/*     */ import org.apache.http.client.config.RequestConfig;
/*     */ import org.apache.http.client.entity.UrlEncodedFormEntity;
/*     */ import org.apache.http.client.methods.CloseableHttpResponse;
/*     */ import org.apache.http.client.methods.HttpGet;
/*     */ import org.apache.http.client.methods.HttpPost;
/*     */ import org.apache.http.client.methods.HttpUriRequest;
/*     */ import org.apache.http.client.protocol.HttpClientContext;
/*     */ import org.apache.http.config.ConnectionConfig;
/*     */ import org.apache.http.config.Lookup;
/*     */ import org.apache.http.config.Registry;
/*     */ import org.apache.http.config.RegistryBuilder;
/*     */ import org.apache.http.conn.HttpClientConnectionManager;
/*     */ import org.apache.http.conn.socket.ConnectionSocketFactory;
/*     */ import org.apache.http.conn.socket.PlainConnectionSocketFactory;
/*     */ import org.apache.http.conn.ssl.DefaultHostnameVerifier;
/*     */ import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
/*     */ import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
/*     */ import org.apache.http.cookie.Cookie;
/*     */ import org.apache.http.entity.ContentType;
/*     */ import org.apache.http.entity.StringEntity;
/*     */ import org.apache.http.impl.auth.BasicSchemeFactory;
/*     */ import org.apache.http.impl.auth.DigestSchemeFactory;
/*     */ import org.apache.http.impl.auth.KerberosSchemeFactory;
/*     */ import org.apache.http.impl.auth.NTLMSchemeFactory;
/*     */ import org.apache.http.impl.auth.SPNegoSchemeFactory;
/*     */ import org.apache.http.impl.client.BasicCookieStore;
/*     */ import org.apache.http.impl.client.BasicCredentialsProvider;
/*     */ import org.apache.http.impl.client.CloseableHttpClient;
/*     */ import org.apache.http.impl.client.HttpClients;
/*     */ import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
/*     */ import org.apache.http.message.BasicNameValuePair;
/*     */ import org.apache.http.protocol.HttpContext;
/*     */ import org.apache.http.ssl.SSLContextBuilder;
/*     */ import org.apache.http.ssl.TrustStrategy;
/*     */ import org.apache.http.util.EntityUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class HttpSyncClient {
/*  59 */   private Logger logger = LoggerFactory.getLogger(HttpSyncClient.class);
/*     */   
/*     */   private volatile CloseableHttpClient httpClient;
/*     */   
/*     */   private volatile CloseableHttpClient proxyHttpClient;
/*     */   
/*     */   HttpSyncClient(CredentialsEntity entity) {
/*     */     try {
/*  67 */       this.httpClient = createHttpClient(HttpClientConstant.defaultPoolSize, HttpClientConstant.defaultMaxPerRoute, HttpClientConstant.defaultSocketTimeout, HttpClientConstant.defaultConnectTimeout, false, null);
/*     */       
/*  69 */       if (entity != null) {
/*  70 */         this.proxyHttpClient = createHttpClient(HttpClientConstant.defaultPoolSize, HttpClientConstant.defaultMaxPerRoute, HttpClientConstant.defaultSocketTimeout, HttpClientConstant.defaultConnectTimeout, true, entity);
/*     */       }
/*     */     }
/*  73 */     catch (HttpClientException e) {
/*  74 */       this.logger.error(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   HttpSyncClient() {
/*  79 */     this(null);
/*     */   }
/*     */   
/*     */   public HttpClient getHttpClient() {
/*  83 */     return (HttpClient)this.httpClient;
/*     */   }
/*     */   
/*     */   public HttpClient getProxyClient() {
/*  87 */     return (HttpClient)this.proxyHttpClient;
/*     */   }
/*     */   
/*     */   public HttpClient getProxyClient(CredentialsEntity entity) throws HttpClientException {
/*  91 */     if (this.proxyHttpClient == null) {
/*  92 */       synchronized (this) {
/*  93 */         if (this.proxyHttpClient == null) {
/*  94 */           this.proxyHttpClient = createHttpClient(HttpClientConstant.defaultPoolSize, HttpClientConstant.defaultMaxPerRoute, HttpClientConstant.defaultSocketTimeout, HttpClientConstant.defaultConnectTimeout, true, entity);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  99 */     return (HttpClient)this.proxyHttpClient;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CloseableHttpClient createHttpClient(int poolSize, int maxPerRoute, int socketTimeout, int connectTimeout, boolean proxy, CredentialsEntity credentialsEntity) throws HttpClientException {
/*     */     try {
/* 106 */       SSLContextBuilder sslContextBuilder = SSLContextBuilder.create();
/* 107 */       sslContextBuilder.loadTrustMaterial((TrustStrategy)new TrustSelfSignedStrategy());
/* 108 */       SSLContext sslContext = sslContextBuilder.build();
/* 109 */       SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, (HostnameVerifier)new DefaultHostnameVerifier());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 115 */       Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslSocketFactory).build();
/*     */       
/* 117 */       RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
/*     */ 
/*     */       
/* 120 */       PoolingHttpClientConnectionManager conMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
/* 121 */       if (poolSize > 0) {
/* 122 */         conMgr.setMaxTotal(poolSize);
/*     */       }
/*     */       
/* 125 */       if (maxPerRoute > 0) {
/* 126 */         conMgr.setDefaultMaxPerRoute(maxPerRoute);
/*     */       } else {
/* 128 */         conMgr.setDefaultMaxPerRoute(10);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 133 */       ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE).setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8).build();
/*     */       
/* 135 */       conMgr.setDefaultConnectionConfig(connectionConfig);
/*     */       
/* 137 */       if (proxy) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 145 */         Registry registry = RegistryBuilder.create().register("Basic", new BasicSchemeFactory()).register("Digest", new DigestSchemeFactory()).register("NTLM", new NTLMSchemeFactory()).register("Negotiate", new SPNegoSchemeFactory()).register("Kerberos", new KerberosSchemeFactory()).build();
/*     */ 
/*     */         
/* 148 */         UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(credentialsEntity.getUsername(), credentialsEntity.getPassword());
/*     */         
/* 150 */         BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
/* 151 */         basicCredentialsProvider.setCredentials(AuthScope.ANY, (Credentials)credentials);
/*     */         
/* 153 */         return HttpClients.custom().setConnectionManager((HttpClientConnectionManager)conMgr)
/* 154 */           .setDefaultCredentialsProvider((CredentialsProvider)basicCredentialsProvider)
/* 155 */           .setDefaultAuthSchemeRegistry((Lookup)registry)
/* 156 */           .setProxy(new HttpHost(credentialsEntity.getHost(), credentialsEntity.getPort()))
/* 157 */           .setDefaultCookieStore((CookieStore)new BasicCookieStore())
/* 158 */           .setDefaultRequestConfig(requestConfig).build();
/*     */       } 
/* 160 */       return HttpClients.custom().setConnectionManager((HttpClientConnectionManager)conMgr)
/* 161 */         .setDefaultCookieStore((CookieStore)new BasicCookieStore())
/* 162 */         .setDefaultRequestConfig(requestConfig).build();
/*     */     }
/* 164 */     catch (NoSuchAlgorithmException|java.security.KeyStoreException|java.security.KeyManagementException e) {
/* 165 */       throw new HttpClientException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String httpGet(String url, List<BasicNameValuePair> parameters, List<Cookie> cookies) throws HttpClientException {
/* 170 */     HttpGet httpget = new HttpGet(url);
/* 171 */     CloseableHttpResponse response = null;
/* 172 */     String returnValue = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 179 */       HttpClientContext context = HttpClientContext.create();
/* 180 */       if (cookies != null && cookies.size() > 0) {
/* 181 */         BasicCookieStore basicCookieStore = new BasicCookieStore();
/* 182 */         for (Cookie cookie : cookies) {
/* 183 */           basicCookieStore.addCookie(cookie);
/*     */         }
/* 185 */         context.setCookieStore((CookieStore)basicCookieStore);
/*     */       } 
/*     */       
/* 188 */       if (null != parameters) {
/* 189 */         String params = EntityUtils.toString((HttpEntity)new UrlEncodedFormEntity(parameters, "UTF-8"));
/*     */         
/* 191 */         httpget.setURI(new URI(httpget.getURI().toString() + "?" + params));
/*     */         
/* 193 */         this.logger.info("httpGet-getUrl:{}", httpget.getURI());
/*     */       } 
/* 195 */       response = this.httpClient.execute((HttpUriRequest)httpget, (HttpContext)context);
/* 196 */       this.logger.info("httpGet-response: {}", response);
/* 197 */       int statusCode = response.getStatusLine().getStatusCode();
/* 198 */       if (statusCode == 200) {
/* 199 */         HttpEntity httpEntity = response.getEntity();
/* 200 */         returnValue = new String(EntityUtils.toByteArray(httpEntity), "UTF-8");
/*     */         
/* 202 */         return returnValue;
/*     */       }
/*     */     
/* 205 */     } catch (URISyntaxException|IOException e) {
/* 206 */       this.logger.error(e.getMessage());
/* 207 */       throw new HttpClientException(e);
/*     */     } finally {
/* 209 */       httpget.releaseConnection();
/* 210 */       if (null != response) {
/*     */         try {
/* 212 */           response.close();
/* 213 */         } catch (IOException e) {
/* 214 */           this.logger.error(e.getMessage());
/* 215 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/* 219 */     return returnValue;
/*     */   }
/*     */   
/*     */   public String httpPost(String url, List<BasicNameValuePair> parameters, List<Cookie> cookies) throws HttpClientException {
/*     */     try {
/* 224 */       UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
/* 225 */       return httpPost(url, (HttpEntity)urlEncodedFormEntity, cookies);
/* 226 */     } catch (UnsupportedEncodingException e) {
/* 227 */       this.logger.error("httpPost-UnsupportedEncodingException:{}", e.getMessage());
/* 228 */       throw new HttpClientException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String httpPost(String url, String requestBody, String mimeType, List<Cookie> cookies) throws HttpClientException {
/* 233 */     StringEntity stringEntity = new StringEntity(requestBody, ContentType.create(mimeType, "UTF-8"));
/* 234 */     return httpPost(url, (HttpEntity)stringEntity, cookies);
/*     */   }
/*     */   
/*     */   public String httpPost(String url, String requestBody, List<Cookie> cookies) throws HttpClientException {
/* 238 */     StringEntity stringEntity = new StringEntity(requestBody, "UTF-8");
/* 239 */     return httpPost(url, (HttpEntity)stringEntity, cookies);
/*     */   }
/*     */ 
/*     */   
/*     */   private String httpPost(String url, HttpEntity requestBody, List<Cookie> cookies) throws HttpClientException {
/* 244 */     HttpPost postMethod = new HttpPost(url);
/* 245 */     CloseableHttpResponse response = null;
/* 246 */     String returnValue = "";
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
/*     */     try {
/* 258 */       postMethod.setEntity(requestBody);
/* 259 */       this.logger.info("httpPost-getUrl:{}", postMethod.getURI());
/*     */       
/* 261 */       HttpClientContext context = HttpClientContext.create();
/* 262 */       if (cookies != null && cookies.size() > 0) {
/* 263 */         BasicCookieStore basicCookieStore = new BasicCookieStore();
/* 264 */         for (Cookie cookie : cookies) {
/* 265 */           basicCookieStore.addCookie(cookie);
/*     */         }
/* 267 */         context.setCookieStore((CookieStore)basicCookieStore);
/*     */       } 
/*     */       
/* 270 */       response = this.httpClient.execute((HttpUriRequest)postMethod, (HttpContext)context);
/* 271 */       this.logger.info("httpPost-response: {}", response);
/* 272 */       int statusCode = response.getStatusLine().getStatusCode();
/*     */       
/* 274 */       if (statusCode == 200) {
/* 275 */         HttpEntity httpEntity = response.getEntity();
/* 276 */         returnValue = new String(EntityUtils.toByteArray(httpEntity), "UTF-8");
/*     */         
/* 278 */         return returnValue;
/*     */       } 
/* 280 */     } catch (Exception e) {
/* 281 */       this.logger.error("httpPost-error: {}", e.getMessage());
/* 282 */       throw new HttpClientException(e);
/*     */     } finally {
/* 284 */       postMethod.releaseConnection();
/* 285 */       if (null != response) {
/*     */         try {
/* 287 */           response.close();
/* 288 */         } catch (IOException e) {
/* 289 */           this.logger.error(e.getMessage());
/* 290 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/* 294 */     return returnValue;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\httpclient\HttpSyncClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */