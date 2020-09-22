/*     */ package linlongyx.sanguo.webgame.proto;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.util.CompressUtil;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import org.msgpack.MessagePack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ProtocolUtil
/*     */ {
/*     */   private static final int BYTE_MAX = 255;
/*     */   private static final int SHORT_MAX = 65535;
/*     */   private static final byte SIZE_TYPE_BYTE = 0;
/*     */   private static final byte SIZE_TYPE_SHORT = 1;
/*     */   
/*     */   public static int readSize(ByteBuf bytes) {
/*  19 */     int size = bytes.readShort();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  26 */     return size;
/*     */   }
/*     */   
/*     */   public static void writeSize(int size, ByteBuf bytes) {
/*  30 */     bytes.writeShort(size);
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
/*     */   public static String readUTF(ByteBuf in) {
/*  48 */     int utflen = in.readUnsignedByte();
/*  49 */     if (utflen < 0)
/*  50 */       throw new IndexOutOfBoundsException(); 
/*  51 */     if (utflen >= 255) {
/*  52 */       utflen = in.readUnsignedShort();
/*     */     }
/*  54 */     byte[] bytearr = new byte[utflen];
/*  55 */     in.readBytes(bytearr);
/*     */     try {
/*  57 */       return new String(bytearr, "utf-8");
/*  58 */     } catch (Exception exception) {
/*     */ 
/*     */       
/*  61 */       return "";
/*     */     } 
/*     */   }
/*     */   public static String readUTFStr(ByteBuf in) {
/*  65 */     int utflen = readStrArraySize(in);
/*  66 */     if (utflen < 0)
/*  67 */       throw new IndexOutOfBoundsException(); 
/*  68 */     if (utflen >= 255) {
/*  69 */       utflen = in.readUnsignedShort();
/*     */     }
/*  71 */     byte[] bytearr = new byte[utflen];
/*  72 */     in.readBytes(bytearr);
/*     */     try {
/*  74 */       return new String(bytearr, "utf-8");
/*  75 */     } catch (Exception exception) {
/*     */ 
/*     */       
/*  78 */       return "";
/*     */     } 
/*     */   }
/*     */   public static void writeUTF(String str, ByteBuf out) {
/*  82 */     if (null == str)
/*  83 */       str = ""; 
/*  84 */     byte[] bytearr = null;
/*     */     try {
/*  86 */       bytearr = str.getBytes("utf-8");
/*  87 */     } catch (Exception exception) {}
/*     */     
/*  89 */     if (bytearr != null) {
/*  90 */       int utflen = bytearr.length;
/*  91 */       if (utflen >= 255) {
/*  92 */         out.writeByte(255);
/*     */       } else {
/*     */         
/*  95 */         out.writeByte(utflen);
/*     */       } 
/*  97 */       out.writeBytes(bytearr);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeUTFBinary(ByteBuf out, String str) {
/* 102 */     if (null == str) str = ""; 
/* 103 */     byte[] bytearr = null;
/*     */     try {
/* 105 */       bytearr = str.getBytes("utf-8");
/* 106 */     } catch (Exception exception) {}
/*     */     
/* 108 */     if (bytearr != null) {
/* 109 */       writeStrArraySize(out, bytearr.length);
/* 110 */       out.writeBytes(bytearr);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String readUTFCompress(ByteBuf in) {
/* 115 */     int utflen = in.readUnsignedByte();
/* 116 */     if (utflen < 0)
/* 117 */       throw new IndexOutOfBoundsException(); 
/* 118 */     if (utflen >= 255) {
/* 119 */       utflen = in.readUnsignedShort();
/*     */     }
/* 121 */     byte[] bytearr = new byte[utflen];
/* 122 */     in.readBytes(bytearr);
/*     */     try {
/* 124 */       return CompressUtil.decompress(bytearr);
/* 125 */     } catch (Exception exception) {
/*     */ 
/*     */       
/* 128 */       return "";
/*     */     } 
/*     */   }
/*     */   public static Object readUTFJSON(MessagePack pack, ByteBuf in, Class clazz) {
/* 132 */     int utflen = in.readUnsignedByte();
/* 133 */     if (utflen < 0)
/* 134 */       throw new IndexOutOfBoundsException(); 
/* 135 */     if (utflen >= 255) {
/* 136 */       utflen = in.readUnsignedShort();
/*     */     }
/* 138 */     byte[] bytearr = new byte[utflen];
/* 139 */     in.readBytes(bytearr);
/*     */     try {
/* 141 */       return pack.read(bytearr, clazz);
/* 142 */     } catch (Exception exception) {
/*     */ 
/*     */       
/* 145 */       return "";
/*     */     } 
/*     */   }
/*     */   public static void writeUTFJSON(byte[] bytearr, ByteBuf out) {
/* 149 */     if (null == bytearr || bytearr.length <= 0)
/* 150 */       return;  int utflen = bytearr.length;
/* 151 */     if (utflen >= 255) {
/* 152 */       out.writeByte(255);
/* 153 */       out.writeShort(utflen);
/*     */     } else {
/* 155 */       out.writeByte(utflen);
/*     */     } 
/* 157 */     out.writeBytes(bytearr);
/*     */   }
/*     */   
/*     */   public static byte readUTFBinByte(ByteBuf in) {
/* 161 */     byte type = in.readByte();
/* 162 */     if (type < 122 && type > Byte.MIN_VALUE) return type; 
/* 163 */     if (type == 122) return in.readByte(); 
/* 164 */     return in.readByte();
/*     */   }
/*     */   
/*     */   public static short readUTFBinShort(ByteBuf in) {
/* 168 */     byte type = in.readByte();
/* 169 */     if (type < 122 && type > Byte.MIN_VALUE) return (short)type; 
/* 170 */     if (type == 122) return (short)in.readByte(); 
/* 171 */     return in.readShort();
/*     */   }
/*     */   
/*     */   public static int readUTFBinInt(ByteBuf in) {
/* 175 */     byte type = in.readByte();
/* 176 */     if (type < 122 && type > Byte.MIN_VALUE) return type; 
/* 177 */     if (type == 122) return in.readByte(); 
/* 178 */     if (type == 123) return in.readShort(); 
/* 179 */     return in.readInt();
/*     */   }
/*     */   
/*     */   public static long readUTFBinLong(ByteBuf in) {
/* 183 */     byte type = in.readByte();
/* 184 */     if (type < 122 && type > Byte.MIN_VALUE) return type; 
/* 185 */     if (type == 122) return in.readByte(); 
/* 186 */     if (type == 123) return in.readShort(); 
/* 187 */     if (type == 124) return in.readInt(); 
/* 188 */     return (long)in.readDouble();
/*     */   }
/*     */   
/*     */   public static float readUTFBinFloat(ByteBuf in) {
/* 192 */     byte type = in.readByte();
/* 193 */     if (type < 122 && type > Byte.MIN_VALUE) return type; 
/* 194 */     if (type == 122) return in.readByte(); 
/* 195 */     if (type == 123) return in.readShort(); 
/* 196 */     if (type == 124) return in.readInt(); 
/* 197 */     if (type == 125) return Float.parseFloat(String.valueOf(in.readDouble())); 
/* 198 */     return in.readFloat();
/*     */   }
/*     */   
/*     */   public static double readUTFBinDouble(ByteBuf in) {
/* 202 */     byte type = in.readByte();
/* 203 */     if (type < 122 && type > Byte.MIN_VALUE) return type; 
/* 204 */     if (type == 122) return in.readByte(); 
/* 205 */     if (type == 123) return in.readShort(); 
/* 206 */     if (type == 124) return in.readInt(); 
/* 207 */     if (type == 125) return in.readDouble(); 
/* 208 */     if (type == 126) return Double.parseDouble(String.valueOf(in.readFloat())); 
/* 209 */     return in.readDouble();
/*     */   }
/*     */   
/*     */   public static void writeByte(ByteBuf out, byte val) {
/* 213 */     if (val < 122 && val > Byte.MIN_VALUE) {
/* 214 */       out.writeByte(val);
/*     */     } else {
/* 216 */       out.writeByte(122);
/* 217 */       out.writeByte(val);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeShort(ByteBuf out, short val) {
/* 222 */     if (val < 122 && val > -128) {
/* 223 */       out.writeByte((byte)val);
/* 224 */     } else if (val < 128 && val > 122) {
/* 225 */       out.writeByte(122);
/* 226 */       out.writeByte((byte)val);
/*     */     } else {
/* 228 */       out.writeByte(123);
/* 229 */       out.writeShort(val);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeInt(ByteBuf out, int val) {
/* 234 */     if (val < 122 && val > -128) {
/* 235 */       out.writeByte((byte)val);
/* 236 */     } else if (val < 128 && val > 122) {
/* 237 */       out.writeByte(122);
/* 238 */       out.writeByte((byte)val);
/* 239 */     } else if (val < 32767 && val > -32768) {
/* 240 */       out.writeByte(123);
/* 241 */       out.writeShort((short)val);
/*     */     } else {
/* 243 */       out.writeByte(124);
/* 244 */       out.writeInt(val);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeLong(ByteBuf out, long val) {
/* 249 */     if (val < 122L && val > -128L) {
/* 250 */       out.writeByte((byte)(int)val);
/* 251 */     } else if (val < 128L && val > 122L) {
/* 252 */       out.writeByte(122);
/* 253 */       out.writeByte((byte)(int)val);
/* 254 */     } else if (val < 32767L && val > -32768L) {
/* 255 */       out.writeByte(123);
/* 256 */       out.writeShort((short)(int)val);
/* 257 */     } else if (val < 2147483647L && val > -2147483648L) {
/* 258 */       out.writeByte(124);
/* 259 */       out.writeInt((int)val);
/*     */     } else {
/* 261 */       out.writeByte(125);
/* 262 */       out.writeDouble(val);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeFloat(ByteBuf out, float val) {
/* 267 */     out.writeByte(126);
/* 268 */     out.writeFloat(val);
/*     */   }
/*     */   
/*     */   public static void writeDouble(ByteBuf out, double val) {
/* 272 */     out.writeByte(127);
/* 273 */     out.writeDouble(val);
/*     */   }
/*     */   
/*     */   public static int readStrArraySize(ByteBuf in) {
/* 277 */     byte type = in.readByte();
/* 278 */     if (type < 122) return type; 
/* 279 */     if (type == 122) return in.readByte(); 
/* 280 */     if (type == 123) return in.readShort(); 
/* 281 */     if (type == 124) return in.readInt(); 
/* 282 */     if (type == 125) return (int)in.readDouble(); 
/* 283 */     if (type == 126) return (int)in.readFloat(); 
/* 284 */     if (type == Byte.MAX_VALUE) return (int)in.readDouble(); 
/* 285 */     return 0;
/*     */   }
/*     */   
/*     */   public static void writeStrArraySize(ByteBuf out, int val) {
/* 289 */     if (val < 122) {
/* 290 */       out.writeByte((byte)val);
/* 291 */     } else if (val < 128) {
/* 292 */       out.writeByte(122);
/* 293 */       out.writeByte((byte)val);
/* 294 */     } else if (val < 32767) {
/* 295 */       out.writeByte(123);
/* 296 */       out.writeShort((short)val);
/* 297 */     } else if (val < Integer.MAX_VALUE) {
/* 298 */       out.writeByte(124);
/* 299 */       out.writeInt(val);
/*     */     } else {
/* 301 */       out.writeByte(125);
/* 302 */       out.writeDouble(val);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 307 */     double d = 2.3E9D;
/* 308 */     long l = Math.round(d);
/* 309 */     System.out.println((long)d);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\ProtocolUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */