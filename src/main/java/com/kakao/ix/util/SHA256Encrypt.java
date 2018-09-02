package com.kakao.ix.util;

import java.security.MessageDigest;

/**
 * 암호화를 위한 클래스
 */
public class SHA256Encrypt {
  public static String encrypt(String text) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      byte[] passBytes = text.getBytes();
      md.reset();
      byte[] digested = md.digest(passBytes);
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < digested.length; i++)
        sb.append(Integer.toHexString(0xff & digested[i]));
      return sb.toString();
    } catch (Exception e) {
      return text;
    }
  }
}
