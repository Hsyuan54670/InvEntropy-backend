package com.hsyuan.inventropy.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

//Jwt工具类
public class JwtUtils {
    // 密钥
    private static final String SECRET_KEY_STRING = "Secret-Key-For-JWT-2025-Web-InvEntropy-Project-054670";  //长度要求:32字节以上
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());  // 生成HS256密钥
    private static final long EXPIRATION_TIME=3600 * 1000 * 12 ;//过期时间

    // 生成JWT令牌
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() +EXPIRATION_TIME )) // 12小时过期
                .signWith(SECRET_KEY, Jwts.SIG.HS256)
                .compact();
    }
    // 解析JWT令牌合法性校验
    public static Map<String, Object> parseToken(String token) throws Exception {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
