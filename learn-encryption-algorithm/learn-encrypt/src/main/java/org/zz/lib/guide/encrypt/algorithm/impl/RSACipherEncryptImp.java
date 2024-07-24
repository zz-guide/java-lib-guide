package org.zz.lib.guide.encrypt.algorithm.impl;

import org.zz.lib.guide.encrypt.algorithm.CipherEncryptImpl;
import org.zz.lib.guide.encrypt.enums.RSAEnum;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RSACipherEncryptImp extends CipherEncryptImpl<RSAEnum> {
    public static final Logger logger = Logger.getLogger("RSACipherEncryptImp");

    private final static String RSA_ALGORITHM_NAME = "RSA";
    private final static String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
    private final static int SALT_KEY_LENGTH = 1024;// or 2048, 2048加密比1024强

    @Override
    protected byte[] encrypt(String content, String saltKey, String vectorKey, RSAEnum algorithm) throws Exception {
        try {
            checkSaltKey(saltKey);
            checkAlgorithm(algorithm);

            Cipher cipher = Cipher.getInstance(algorithm.getAlgorithm());
            byte[] decoded = getPublicKey(saltKey);
            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(RSA_ALGORITHM_NAME).generatePublic(new X509EncodedKeySpec(decoded));
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return cipher.doFinal(content.getBytes());
        } catch (Exception e) {
            logger.log(Level.INFO, "RSACipherEncryptImp encrypt error: {0}", new Object[]{e.getMessage()});
        }

        return null;
    }

    @Override
    protected String decrypt(byte[] content, String saltKey, String vectorKey, RSAEnum algorithm) throws Exception {
        try {
            checkSaltKey(saltKey);
            checkAlgorithm(algorithm);

            Cipher cipher = Cipher.getInstance(algorithm.getAlgorithm());
            byte[] decoded = getPrivateKey(saltKey);
            RSAPrivateKey pubKey = (RSAPrivateKey) KeyFactory.getInstance(RSA_ALGORITHM_NAME).generatePrivate(new PKCS8EncodedKeySpec(decoded));
            cipher.init(Cipher.DECRYPT_MODE, pubKey);
            byte[] original = cipher.doFinal(content);
            return new String(original).trim();
        } catch (Exception e) {
            logger.log(Level.INFO, "RSACipherEncryptImp decrypt error: {0}", new Object[]{e.getMessage()});
        }

        return null;
    }

    /**
     * 根据slatKey获取公匙，传入的slatKey作为SecureRandom的随机种子
     * 若使用new SecureRandom()创建公匙，则需要记录下私匙，解密时使用
     */
    private static byte[] getPublicKey(String slatKey) throws Exception {
        KeyPairGenerator keyPairGenerator  = KeyPairGenerator.getInstance(RSA_ALGORITHM_NAME);
        SecureRandom random = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM);
        random.setSeed(slatKey.getBytes());
        keyPairGenerator.initialize(SALT_KEY_LENGTH, random);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair.getPublic().getEncoded();
    }

    /**
     * 根据slatKey获取私匙，传入的slatKey作为SecureRandom的随机种子
     */
    private static byte[] getPrivateKey(String slatKey) throws Exception {
        KeyPairGenerator keyPairGenerator  = KeyPairGenerator.getInstance(RSA_ALGORITHM_NAME);
        SecureRandom random = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM);
        random.setSeed(slatKey.getBytes());
        keyPairGenerator.initialize(SALT_KEY_LENGTH, random);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair.getPrivate().getEncoded();
    }

    private void checkSaltKey(String saltKey) {
        if (saltKey == null) {
            throw new IllegalArgumentException("saltKey is null");
        }
    }
}
