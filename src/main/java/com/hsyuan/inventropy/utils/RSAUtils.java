package com.hsyuan.inventropy.utils;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RSAUtils {
    private static final String RSA_ALGORITHM = "RSA";
    private static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";


    private static KeyPair keyPair;
    private static  String random;

    @Autowired
    private StringRedisTemplate redisTemplateAutowired;
    
    private static StringRedisTemplate redisTemplate;

    @PostConstruct
    private void init() {
        keyPair = generateRSAKeyPair();
        RSAUtils.redisTemplate = redisTemplateAutowired;
    }

    public static Map<String,String> getPublicKey() {
        Map<String,String> data = new HashMap<>();
        String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        data.put("publicKey", publicKey);
        data.put("algorithm",RSA_ALGORITHM);
        data.put("timestamp",String.valueOf(System.currentTimeMillis()));
        return data;
    }
    public static String decrypt(String passwordRSA) {
        try{
            //1.解密
            String decryptedData = decryptRSA(passwordRSA);

            //2.密码解密
            JSONObject data = new JSONObject(decryptedData);
            String password = data.getString("password");
            random = data.getString("random");
            long timestamp = Long.parseLong(data.getString("timestamp"));

            //3.验证时间
            long currentTime = System.currentTimeMillis();
            if(Math.abs(currentTime-timestamp) > 5 * 60 * 1000){
                throw new RuntimeException("密码已过期");
            }

            //4.检查随机数是否已经存在
            if(redisTemplate.hasKey("InvEntropy:random:"+random)){
                throw new RuntimeException("无效请求");
            }

            //5.返回用户密码
            return password;


        } catch (Exception e) {
            throw new RuntimeException("解密失败: " + e.getMessage(), e);
        }
    }

    // RSA解密方法
    private static String decryptRSA(String encryptedData) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    // 生成RSA密钥对
    private KeyPair generateRSAKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(RSA_ALGORITHM);
            keyGen.initialize(2048);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("生成RSA密钥对失败", e);
        }
    }

    public static void markRandomUsed(){
        redisTemplate.opsForValue().set("InvEntropy:random:"+random, "1", 5, TimeUnit.MINUTES);
    }

}