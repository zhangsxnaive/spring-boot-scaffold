package com.chmpay.idauth.common.util.RSA;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangshuxin
 * @date 2019-06-10
 */
public class RSACoder {
    public static final String RSA_KEY_ALGORITHM = "RSA";
    public static final String PUBLIC_KEY = "RSAPublicKey";
    public static final String PRIVATE_KEY = "RSAPrivateKey";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    public static final String KEY_PADDING = "RSA/ECB/PKCS1Padding";
    public static final String KEY_DES = "DESede";
    public static final String KEY_DES_PADDING = "DESede/ECB/PKCS5Padding";
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static final Provider PROVIDER = new BouncyCastleProvider();

    public static String signByPrivate(byte[] data, String privateKey) throws Exception {
        byte[] encodedKey = Base64.decodeBase64(privateKey);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PrivateKey privKey = keyFactory.generatePrivate(keySpec);

        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privKey);
        signature.update(data);
        return Base64.encodeBase64String(signature.sign());
    }

    public static boolean verify(byte[] data, String publickey, String sign) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publickey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(pubKey);
        signature.update(data);

        return signature.verify(Base64.decodeBase64(sign));
    }

    public static byte[] encryptByPrivate(byte[] data, String privateKey) throws Exception {
        return encryptByPrivate(data, privateKey, "RSA/ECB/PKCS5Padding");
    }

    public static byte[] encryptByPrivate(byte[] data, String privateKey, String transformation) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key priKey = keyFactory.generatePrivate(keySpec);

        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(1, priKey);

        return cipher.doFinal(data);
    }

    public static byte[] decryptByPublic(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key pubKey = keyFactory.generatePublic(keySpec);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, pubKey);
        return cipher.doFinal(data);
    }

    public static Map<String, Object> initKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);

        keyMap.put("RSAPublicKey", publicKey);
        keyMap.put("RSAPrivateKey", privateKey);
        return keyMap;
    }

    public static String getPublicKeyByCert(String certPath) {
        try {
            CertificateFactory cff = CertificateFactory.getInstance("X.509");
            FileInputStream fis = new FileInputStream(certPath);
            Certificate certificate = cff.generateCertificate(fis);
            PublicKey publicKey = certificate.getPublicKey();
            fis.close();
            return Base64.encodeBase64String(publicKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPrivateKeyByCert(String certPath, String password) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        InputStream fis = new FileInputStream(certPath);
        keyStore.load(fis, password.toCharArray());
        Enumeration<String> aliases = keyStore.aliases();
        String alias = null;
        if (aliases.hasMoreElements()) {
            alias = (String) aliases.nextElement();
        }
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, password.toCharArray());
        fis.close();
        return Base64.encodeBase64String(privateKey.getEncoded());
    }

    private static Key toKey(byte[] key) throws Exception {
        DESedeKeySpec dks = new DESedeKeySpec(key);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
        return skf.generateSecret(dks);
    }

    public static byte[] encryptDESec(byte[] data, byte[] key) throws Exception {
        Key keys = toKey(key);
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(1, keys);
        return cipher.doFinal(data);
    }

    public static byte[] decryptDESec(byte[] data, byte[] key) throws Exception {
        Key keys = toKey(key);
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(2, keys);
        return cipher.doFinal(data);
    }

    public static byte[] decryptByPrivateKeySp(byte[] encryptedData, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(2, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        int i = 0;

        while (inputLen - offSet > 0) {
            byte[] cache;
            // byte[] cache;
            if (inputLen - offSet > MAX_DECRYPT_BLOCK)
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    public static byte[] decryptByPublicKeySp(byte[] encryptedData, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM, PROVIDER);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(2, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        int i = 0;

        while (inputLen - offSet > 0) {
            byte[] cache;
            //
            if (inputLen - offSet > MAX_DECRYPT_BLOCK)
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicK = keyFactory.generatePublic(x509KeySpec);

        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(1, publicK);
        return cipher.doFinal(data);
    }

    public static byte[] encryByPublicKeySp(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicK = keyFactory.generatePublic(x509KeySpec);

        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(1, publicK);

        int inputLen = data.length;// 107
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        int i = 0;

        while (inputLen - offSet > 0) {
            byte[] cache;

            if (inputLen - offSet > MAX_ENCRYPT_BLOCK)
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    public static byte[] encryptByPrivateKeySp(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM, PROVIDER);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(1, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        int i = 0;

        while (inputLen - offSet > 0) {
            byte[] cache;

            if (inputLen - offSet > MAX_ENCRYPT_BLOCK)
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }
}
