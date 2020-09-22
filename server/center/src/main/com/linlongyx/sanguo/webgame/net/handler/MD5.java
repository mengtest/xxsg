/*     */ package com.linlongyx.sanguo.webgame.net.handler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MD5
/*     */ {
/*     */   private long[] m_buf;
/*     */   private long[] m_bits;
/*     */   private byte[] m_in;
/*  15 */   private char[] HEX = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*     */   
/*     */   public MD5() {
/*  18 */     this.m_buf = new long[4];
/*  19 */     this.m_bits = new long[2];
/*  20 */     this.m_in = new byte[64];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toDigest(String src) {
/*  28 */     byte[] digest = toDigest(src.getBytes());
/*  29 */     StringBuffer sb = new StringBuffer();
/*  30 */     for (int i = 0; i < digest.length; i++) {
/*  31 */       sb.append(this.HEX[(digest[i] & 0xFF) / 16]);
/*  32 */       sb.append(this.HEX[(digest[i] & 0xFF) % 16]);
/*     */     } 
/*     */     
/*  35 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public byte[] toDigest(byte[] src) {
/*  39 */     byte[] digest = new byte[16];
/*  40 */     int len = src.length;
/*  41 */     MD5Init();
/*  42 */     MD5Update(src, len);
/*  43 */     MD5Final(digest);
/*  44 */     return digest;
/*     */   }
/*     */   
/*     */   private void memset(byte[] des, int des_offset, byte dat, int len) {
/*  48 */     for (int i = 0; i < len; i++) {
/*  49 */       des[des_offset + i] = dat;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void memcpy(byte[] des, int des_offset, byte[] src, int src_offset, int len) {
/*  55 */     for (int i = 0; i < len; i++) {
/*  56 */       des[des_offset + i] = src[src_offset + i];
/*     */     }
/*     */   }
/*     */   
/*     */   private long bp2long(byte[] src, int offset_lng) {
/*  61 */     long ret = 0L;
/*  62 */     ret = src[offset_lng * 4 + 0] & 0xFFL | (src[offset_lng * 4 + 1] << 8) & 0xFF00L | (src[offset_lng * 4 + 2] << 16) & 0xFF0000L | (src[offset_lng * 4 + 3] << 24) & 0xFF000000L;
/*     */ 
/*     */ 
/*     */     
/*  66 */     return ret & 0xFFFFFFFFL;
/*     */   }
/*     */   
/*     */   private void MD5Init() {
/*  70 */     this.m_buf[0] = 1732584193L;
/*  71 */     this.m_buf[1] = 4023233417L;
/*  72 */     this.m_buf[2] = 2562383102L;
/*  73 */     this.m_buf[3] = 271733878L;
/*  74 */     this.m_bits[0] = 0L;
/*  75 */     this.m_bits[1] = 0L;
/*     */   }
/*     */   
/*     */   private void MD5Update(byte[] buf, int len) {
/*  79 */     long t = this.m_bits[0];
/*  80 */     this.m_bits[0] = t + (len << 3);
/*  81 */     if (this.m_bits[0] < t)
/*  82 */       this.m_bits[1] = this.m_bits[1] + 1L; 
/*  83 */     this.m_bits[1] = this.m_bits[1] + (len >> 29);
/*  84 */     t = t >> 3L & 0x3FL;
/*  85 */     if (t != 0L) {
/*  86 */       t = 64L - t & 0xFFFFFFFFL;
/*  87 */       if (len < t) {
/*  88 */         memcpy(this.m_in, (int)t, buf, 0, len);
/*     */         return;
/*     */       } 
/*  91 */       memcpy(this.m_in, (int)t, buf, 0, (int)t);
/*  92 */       MD5Transform();
/*  93 */       len = (int)(len - t);
/*     */     } 
/*  95 */     for (; len >= 64; len -= 64) {
/*  96 */       memcpy(this.m_in, 0, buf, (int)t, 64);
/*  97 */       MD5Transform();
/*  98 */       t += 64L;
/*     */     } 
/*     */     
/* 101 */     memcpy(this.m_in, 0, buf, (int)t, len);
/*     */   }
/*     */   
/*     */   private void MD5Final(byte[] digest) {
/* 105 */     long count = this.m_bits[0] >> 3L & 0x3FL;
/* 106 */     this.m_in[(int)count] = Byte.MIN_VALUE;
/* 107 */     long p = count + 1L;
/* 108 */     count = 63L - count;
/* 109 */     if (count < 8L) {
/* 110 */       memset(this.m_in, (int)p, (byte)0, (int)count);
/* 111 */       MD5Transform();
/* 112 */       memset(this.m_in, 0, (byte)0, 56);
/*     */     } else {
/* 114 */       memset(this.m_in, (int)p, (byte)0, (int)(count - 8L));
/*     */     } 
/* 116 */     this.m_in[56] = (byte)(int)(this.m_bits[0] & 0xFFL);
/* 117 */     this.m_in[57] = (byte)(int)(this.m_bits[0] >> 8L & 0xFFL);
/* 118 */     this.m_in[58] = (byte)(int)(this.m_bits[0] >> 16L & 0xFFL);
/* 119 */     this.m_in[59] = (byte)(int)(this.m_bits[0] >> 24L & 0xFFL);
/* 120 */     this.m_in[60] = (byte)(int)(this.m_bits[1] & 0xFFL);
/* 121 */     this.m_in[61] = (byte)(int)(this.m_bits[1] >> 8L & 0xFFL);
/* 122 */     this.m_in[62] = (byte)(int)(this.m_bits[1] >> 16L & 0xFFL);
/* 123 */     this.m_in[63] = (byte)(int)(this.m_bits[1] >> 24L & 0xFFL);
/* 124 */     MD5Transform();
/* 125 */     for (int i = 0; i < 4; i++) {
/* 126 */       digest[i * 4 + 0] = (byte)(int)(this.m_buf[i] & 0xFFL);
/* 127 */       digest[i * 4 + 1] = (byte)(int)(this.m_buf[i] >> 8L & 0xFFL);
/* 128 */       digest[i * 4 + 2] = (byte)(int)(this.m_buf[i] >> 16L & 0xFFL);
/* 129 */       digest[i * 4 + 3] = (byte)(int)(this.m_buf[i] >> 24L & 0xFFL);
/*     */     } 
/*     */     
/* 132 */     MD5Init();
/*     */   }
/*     */   
/*     */   private long F1(long x, long y, long z) {
/* 136 */     return (z ^ x & (y ^ z)) & 0xFFFFFFFFL;
/*     */   }
/*     */   
/*     */   private long F2(long x, long y, long z) {
/* 140 */     return F1(z, x, y);
/*     */   }
/*     */   
/*     */   private long F3(long x, long y, long z) {
/* 144 */     return (x ^ y ^ z) & 0xFFFFFFFFL;
/*     */   }
/*     */   
/*     */   private long F4(long x, long y, long z) {
/* 148 */     return (y ^ (x | z ^ 0xFFFFFFFFFFFFFFFFL)) & 0xFFFFFFFFL;
/*     */   }
/*     */ 
/*     */   
/*     */   private long MD5STEP(long w, long f, long x, long y, long z, long data, long s) {
/* 153 */     w = w + f + data & 0xFFFFFFFFL;
/* 154 */     w = (w << (int)s | w >> (int)(32L - s)) & 0xFFFFFFFFL;
/* 155 */     w = w + x & 0xFFFFFFFFL;
/* 156 */     return w;
/*     */   }
/*     */   
/*     */   private void MD5Transform() {
/* 160 */     long a = this.m_buf[0];
/* 161 */     long b = this.m_buf[1];
/* 162 */     long c = this.m_buf[2];
/* 163 */     long d = this.m_buf[3];
/* 164 */     a = MD5STEP(a, F1(b, c, d), b, c, d, bp2long(this.m_in, 0) + 3614090360L, 7L);
/* 165 */     d = MD5STEP(d, F1(a, b, c), a, b, c, bp2long(this.m_in, 1) + 3905402710L, 12L);
/*     */     
/* 167 */     c = MD5STEP(c, F1(d, a, b), d, a, b, bp2long(this.m_in, 2) + 606105819L, 17L);
/*     */     
/* 169 */     b = MD5STEP(b, F1(c, d, a), c, d, a, bp2long(this.m_in, 3) + 3250441966L, 22L);
/*     */     
/* 171 */     a = MD5STEP(a, F1(b, c, d), b, c, d, bp2long(this.m_in, 4) + 4118548399L, 7L);
/* 172 */     d = MD5STEP(d, F1(a, b, c), a, b, c, bp2long(this.m_in, 5) + 1200080426L, 12L);
/*     */     
/* 174 */     c = MD5STEP(c, F1(d, a, b), d, a, b, bp2long(this.m_in, 6) + 2821735955L, 17L);
/*     */     
/* 176 */     b = MD5STEP(b, F1(c, d, a), c, d, a, bp2long(this.m_in, 7) + 4249261313L, 22L);
/*     */     
/* 178 */     a = MD5STEP(a, F1(b, c, d), b, c, d, bp2long(this.m_in, 8) + 1770035416L, 7L);
/* 179 */     d = MD5STEP(d, F1(a, b, c), a, b, c, bp2long(this.m_in, 9) + 2336552879L, 12L);
/*     */     
/* 181 */     c = MD5STEP(c, F1(d, a, b), d, a, b, bp2long(this.m_in, 10) + 4294925233L, 17L);
/*     */     
/* 183 */     b = MD5STEP(b, F1(c, d, a), c, d, a, bp2long(this.m_in, 11) + 2304563134L, 22L);
/*     */     
/* 185 */     a = MD5STEP(a, F1(b, c, d), b, c, d, bp2long(this.m_in, 12) + 1804603682L, 7L);
/*     */     
/* 187 */     d = MD5STEP(d, F1(a, b, c), a, b, c, bp2long(this.m_in, 13) + 4254626195L, 12L);
/*     */     
/* 189 */     c = MD5STEP(c, F1(d, a, b), d, a, b, bp2long(this.m_in, 14) + 2792965006L, 17L);
/*     */     
/* 191 */     b = MD5STEP(b, F1(c, d, a), c, d, a, bp2long(this.m_in, 15) + 1236535329L, 22L);
/*     */     
/* 193 */     a = MD5STEP(a, F2(b, c, d), b, c, d, bp2long(this.m_in, 1) + 4129170786L, 5L);
/* 194 */     d = MD5STEP(d, F2(a, b, c), a, b, c, bp2long(this.m_in, 6) + 3225465664L, 9L);
/* 195 */     c = MD5STEP(c, F2(d, a, b), d, a, b, bp2long(this.m_in, 11) + 643717713L, 14L);
/*     */     
/* 197 */     b = MD5STEP(b, F2(c, d, a), c, d, a, bp2long(this.m_in, 0) + 3921069994L, 20L);
/*     */     
/* 199 */     a = MD5STEP(a, F2(b, c, d), b, c, d, bp2long(this.m_in, 5) + 3593408605L, 5L);
/* 200 */     d = MD5STEP(d, F2(a, b, c), a, b, c, bp2long(this.m_in, 10) + 38016083L, 9L);
/* 201 */     c = MD5STEP(c, F2(d, a, b), d, a, b, bp2long(this.m_in, 15) + 3634488961L, 14L);
/*     */     
/* 203 */     b = MD5STEP(b, F2(c, d, a), c, d, a, bp2long(this.m_in, 4) + 3889429448L, 20L);
/*     */     
/* 205 */     a = MD5STEP(a, F2(b, c, d), b, c, d, bp2long(this.m_in, 9) + 568446438L, 5L);
/* 206 */     d = MD5STEP(d, F2(a, b, c), a, b, c, bp2long(this.m_in, 14) + 3275163606L, 9L);
/*     */     
/* 208 */     c = MD5STEP(c, F2(d, a, b), d, a, b, bp2long(this.m_in, 3) + 4107603335L, 14L);
/*     */     
/* 210 */     b = MD5STEP(b, F2(c, d, a), c, d, a, bp2long(this.m_in, 8) + 1163531501L, 20L);
/*     */     
/* 212 */     a = MD5STEP(a, F2(b, c, d), b, c, d, bp2long(this.m_in, 13) + 2850285829L, 5L);
/*     */     
/* 214 */     d = MD5STEP(d, F2(a, b, c), a, b, c, bp2long(this.m_in, 2) + 4243563512L, 9L);
/* 215 */     c = MD5STEP(c, F2(d, a, b), d, a, b, bp2long(this.m_in, 7) + 1735328473L, 14L);
/*     */     
/* 217 */     b = MD5STEP(b, F2(c, d, a), c, d, a, bp2long(this.m_in, 12) + 2368359562L, 20L);
/*     */     
/* 219 */     a = MD5STEP(a, F3(b, c, d), b, c, d, bp2long(this.m_in, 5) + 4294588738L, 4L);
/* 220 */     d = MD5STEP(d, F3(a, b, c), a, b, c, bp2long(this.m_in, 8) + 2272392833L, 11L);
/*     */     
/* 222 */     c = MD5STEP(c, F3(d, a, b), d, a, b, bp2long(this.m_in, 11) + 1839030562L, 16L);
/*     */     
/* 224 */     b = MD5STEP(b, F3(c, d, a), c, d, a, bp2long(this.m_in, 14) + 4259657740L, 23L);
/*     */     
/* 226 */     a = MD5STEP(a, F3(b, c, d), b, c, d, bp2long(this.m_in, 1) + 2763975236L, 4L);
/* 227 */     d = MD5STEP(d, F3(a, b, c), a, b, c, bp2long(this.m_in, 4) + 1272893353L, 11L);
/*     */     
/* 229 */     c = MD5STEP(c, F3(d, a, b), d, a, b, bp2long(this.m_in, 7) + 4139469664L, 16L);
/*     */     
/* 231 */     b = MD5STEP(b, F3(c, d, a), c, d, a, bp2long(this.m_in, 10) + 3200236656L, 23L);
/*     */     
/* 233 */     a = MD5STEP(a, F3(b, c, d), b, c, d, bp2long(this.m_in, 13) + 681279174L, 4L);
/* 234 */     d = MD5STEP(d, F3(a, b, c), a, b, c, bp2long(this.m_in, 0) + 3936430074L, 11L);
/* 235 */     c = MD5STEP(c, F3(d, a, b), d, a, b, bp2long(this.m_in, 3) + 3572445317L, 16L);
/* 236 */     b = MD5STEP(b, F3(c, d, a), c, d, a, bp2long(this.m_in, 6) + 76029189L, 23L);
/* 237 */     a = MD5STEP(a, F3(b, c, d), b, c, d, bp2long(this.m_in, 9) + 3654602809L, 4L);
/* 238 */     d = MD5STEP(d, F3(a, b, c), a, b, c, bp2long(this.m_in, 12) + 3873151461L, 11L);
/* 239 */     c = MD5STEP(c, F3(d, a, b), d, a, b, bp2long(this.m_in, 15) + 530742520L, 16L);
/* 240 */     b = MD5STEP(b, F3(c, d, a), c, d, a, bp2long(this.m_in, 2) + 3299628645L, 23L);
/* 241 */     a = MD5STEP(a, F4(b, c, d), b, c, d, bp2long(this.m_in, 0) + 4096336452L, 6L);
/* 242 */     d = MD5STEP(d, F4(a, b, c), a, b, c, bp2long(this.m_in, 7) + 1126891415L, 10L);
/* 243 */     c = MD5STEP(c, F4(d, a, b), d, a, b, bp2long(this.m_in, 14) + 2878612391L, 15L);
/* 244 */     b = MD5STEP(b, F4(c, d, a), c, d, a, bp2long(this.m_in, 5) + 4237533241L, 21L);
/* 245 */     a = MD5STEP(a, F4(b, c, d), b, c, d, bp2long(this.m_in, 12) + 1700485571L, 6L);
/* 246 */     d = MD5STEP(d, F4(a, b, c), a, b, c, bp2long(this.m_in, 3) + 2399980690L, 10L);
/* 247 */     c = MD5STEP(c, F4(d, a, b), d, a, b, bp2long(this.m_in, 10) + 4293915773L, 15L);
/* 248 */     b = MD5STEP(b, F4(c, d, a), c, d, a, bp2long(this.m_in, 1) + 2240044497L, 21L);
/* 249 */     a = MD5STEP(a, F4(b, c, d), b, c, d, bp2long(this.m_in, 8) + 1873313359L, 6L);
/* 250 */     d = MD5STEP(d, F4(a, b, c), a, b, c, bp2long(this.m_in, 15) + 4264355552L, 10L);
/* 251 */     c = MD5STEP(c, F4(d, a, b), d, a, b, bp2long(this.m_in, 6) + 2734768916L, 15L);
/* 252 */     b = MD5STEP(b, F4(c, d, a), c, d, a, bp2long(this.m_in, 13) + 1309151649L, 21L);
/* 253 */     a = MD5STEP(a, F4(b, c, d), b, c, d, bp2long(this.m_in, 4) + 4149444226L, 6L);
/* 254 */     d = MD5STEP(d, F4(a, b, c), a, b, c, bp2long(this.m_in, 11) + 3174756917L, 10L);
/* 255 */     c = MD5STEP(c, F4(d, a, b), d, a, b, bp2long(this.m_in, 2) + 718787259L, 15L);
/* 256 */     b = MD5STEP(b, F4(c, d, a), c, d, a, bp2long(this.m_in, 9) + 3951481745L, 21L);
/* 257 */     this.m_buf[0] = this.m_buf[0] + a;
/* 258 */     this.m_buf[1] = this.m_buf[1] + b;
/* 259 */     this.m_buf[2] = this.m_buf[2] + c;
/* 260 */     this.m_buf[3] = this.m_buf[3] + d;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\net\handler\MD5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */