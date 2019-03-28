package com.snow.snowcore.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.*;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/27 19:42
 * @description
 **/
public class ZipUtils {
    public ZipUtils() {
    }

    public static String gzip(String primStr) {
        if (primStr != null && primStr.length() != 0) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = null;

            try {
                gzip = new GZIPOutputStream(out);
                gzip.write(primStr.getBytes());
            } catch (IOException var12) {
                var12.printStackTrace();
            } finally {
                if (gzip != null) {
                    try {
                        gzip.close();
                    } catch (IOException var11) {
                        var11.printStackTrace();
                    }
                }

            }

            return (new BASE64Encoder()).encode(out.toByteArray());
        } else {
            return primStr;
        }
    }

    public static String gunzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        } else {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayInputStream in = null;
            GZIPInputStream ginzip = null;
            byte[] compressed = null;
            String decompressed = null;
            try {
                compressed = (new BASE64Decoder()).decodeBuffer(compressedStr);
                in = new ByteArrayInputStream(compressed);
                ginzip = new GZIPInputStream(in);
                byte[] buffer = new byte[1024];
                boolean var7 = true;

                int offset;
                while ((offset = ginzip.read(buffer)) != -1) {
                    out.write(buffer, 0, offset);
                }

                decompressed = out.toString();
            } catch (IOException var24) {
                var24.printStackTrace();
            } finally {
                if (ginzip != null) {
                    try {
                        ginzip.close();
                    } catch (IOException var23) {
                        ;
                    }
                }

                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException var22) {
                        ;
                    }
                }

                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException var21) {
                        ;
                    }
                }

            }

            return decompressed;
        }
    }

    public static final String zip(String str) {
        if (str == null) {
            return null;
        } else {
            ByteArrayOutputStream out = null;
            ZipOutputStream zout = null;
            String compressedStr = null;

            try {
                out = new ByteArrayOutputStream();
                zout = new ZipOutputStream(out);
                zout.putNextEntry(new ZipEntry("0"));
                zout.write(str.getBytes());
                zout.closeEntry();
                byte[] compressed = out.toByteArray();
                compressedStr = (new BASE64Encoder()).encodeBuffer(compressed);
            } catch (IOException var18) {
                Object var1 = null;
            } finally {
                if (zout != null) {
                    try {
                        zout.close();
                    } catch (IOException var17) {
                        ;
                    }
                }

                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException var16) {
                        ;
                    }
                }

            }

            return compressedStr;
        }
    }

    public static final String unzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        } else {
            ByteArrayOutputStream out = null;
            ByteArrayInputStream in = null;
            ZipInputStream zin = null;
            String decompressed = null;

            try {
                byte[] compressed = (new BASE64Decoder()).decodeBuffer(compressedStr);
                out = new ByteArrayOutputStream();
                in = new ByteArrayInputStream(compressed);
                zin = new ZipInputStream(in);
                zin.getNextEntry();
                byte[] buffer = new byte[1024];
                boolean var7 = true;

                int offset;
                while ((offset = zin.read(buffer)) != -1) {
                    out.write(buffer, 0, offset);
                }

                decompressed = out.toString();
            } catch (IOException var24) {
                decompressed = null;
            } finally {
                if (zin != null) {
                    try {
                        zin.close();
                    } catch (IOException var23) {
                        ;
                    }
                }

                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException var22) {
                        ;
                    }
                }

                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException var21) {
                        ;
                    }
                }

            }

            return decompressed;
        }
    }
}
