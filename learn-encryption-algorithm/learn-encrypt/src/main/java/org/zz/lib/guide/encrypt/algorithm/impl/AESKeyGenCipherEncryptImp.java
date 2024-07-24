package org.zz.lib.guide.encrypt.algorithm.impl;

import org.zz.lib.guide.encrypt.algorithm.CipherEncryptImpl;
import org.zz.lib.guide.encrypt.enums.AESEnum;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AESKeyGenCipherEncryptImp extends CipherEncryptImpl<AESEnum> {
    public static final Logger logger = Logger.getLogger("AESKeyGenCipherEncryptImp");

    private final static String AES_ALGORITHM_NAME = "AES";
    private final static String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
    private final static int SALT_KEY_LENGTH = 128;
    private final static int VECTOR_KEY_LENGTH = 128;

    @Override
    protected byte[] encrypt(String content, String saltKey, String vectorKey, AESEnum algorithm) throws Exception {
        try {
            checkSaltKey(saltKey);
            checkAlgorithm(algorithm);

            Cipher cipher = Cipher.getInstance(algorithm.getAlgorithm());
            Key key = getSaltKey(saltKey);

            byte[] plaintext = null;
            if (AESEnum.CBC_NO_PADDING.equals(algorithm) || AESEnum.ECB_NO_PADDING.equals(algorithm)) {
                plaintext = handleNoPaddingEncryptFormat(cipher, content);
            } else {
                plaintext = content.getBytes();
            }

            if (AESEnum.CBC_NO_PADDING.equals(algorithm) || AESEnum.CBC_PKCS5PADDING.equals(algorithm)) {
                checkVectorKey(vectorKey);

                IvParameterSpec iv = getVectorKey(vectorKey);
                cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, key);
            }

            return cipher.doFinal(plaintext);
        } catch (Exception e) {
            logger.log(Level.INFO, "AESKeyGenCipherEncryptImp encrypt error: {0}", new Object[]{e.getMessage()});
        }
        return null;
    }

    @Override
    protected String decrypt(byte[] content, String saltKey, String vectorKey, AESEnum algorithm) throws Exception {
        try {
            checkSaltKey(saltKey);
            checkAlgorithm(algorithm);

            Cipher cipher = Cipher.getInstance(algorithm.getAlgorithm());
            Key key = getSaltKey(saltKey);
            if (AESEnum.CBC_NO_PADDING.equals(algorithm) || AESEnum.CBC_PKCS5PADDING.equals(algorithm)) {
                checkVectorKey(vectorKey);

                IvParameterSpec iv = getVectorKey(vectorKey);
                cipher.init(Cipher.DECRYPT_MODE, key, iv);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, key);
            }

            byte[] original = cipher.doFinal(content);
            return new String(original).trim();
        } catch (Exception e) {
			logger.log(Level.INFO, "AESKeyGenCipherEncryptImp decrypt error: {0}", new Object[]{e.getMessage()});
        }
        return null;
    }

    /**
     * 获取加密的密匙，传入的saltKey可以是任意长度的，作为SecureRandom的随机种子，
     * 而在KeyGenerator初始化时设置密匙的长度128bit(16位byte)
     */
    private static Key getSaltKey(String saltKey) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM_NAME);
        SecureRandom random = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM);
        random.setSeed(saltKey.getBytes());
        keyGenerator.init(SALT_KEY_LENGTH, random);
        Key key = keyGenerator.generateKey();
        return key;
    }

    /**
     * 获取加密的向量
     */
    private static IvParameterSpec getVectorKey(String vectorKey) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM_NAME);
        SecureRandom random = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM);
        random.setSeed(vectorKey.getBytes());
        keyGenerator.init(VECTOR_KEY_LENGTH, random);
        IvParameterSpec iv = new IvParameterSpec(keyGenerator.generateKey().getEncoded());
        return iv;
    }

    private void checkSaltKey(String saltKey) {
        if (saltKey == null) {
            throw new IllegalArgumentException("saltKey is null");
        }
    }

    private void checkVectorKey(String vectorKey) {
        if (vectorKey == null) {
            throw new IllegalArgumentException("vectorKey is null");
        }
    }
}
