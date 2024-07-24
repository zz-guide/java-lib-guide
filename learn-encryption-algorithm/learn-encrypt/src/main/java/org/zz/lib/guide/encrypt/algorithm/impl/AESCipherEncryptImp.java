package org.zz.lib.guide.encrypt.algorithm.impl;

import org.zz.lib.guide.encrypt.algorithm.CipherEncryptImpl;
import org.zz.lib.guide.encrypt.enums.AESEnum;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AESCipherEncryptImp extends CipherEncryptImpl<AESEnum> {
    public static final Logger logger = Logger.getLogger("AESCipherEncryptImp");

    private final static String AES_ALGORITHM_NAME = "AES";

    private final static int SALT_KEY_LENGTH = 16;
    private final static int VECTOR_KEY_LENGTH = 16;

    @Override
    protected byte[] encrypt(String content, String saltKey, String vectorKey, AESEnum algorithm) throws Exception {
        try {
            checkSaltKey(saltKey);
            checkAlgorithm(algorithm);

            Cipher cipher = Cipher.getInstance(algorithm.getAlgorithm());
            byte[] plaintext;
            if (AESEnum.CBC_NO_PADDING.equals(algorithm) || AESEnum.ECB_NO_PADDING.equals(algorithm)) {
                plaintext = handleNoPaddingEncryptFormat(cipher, content);
            } else {
                plaintext = content.getBytes();
            }

            SecretKey secretKey = new SecretKeySpec(saltKey.getBytes(), AES_ALGORITHM_NAME);
            if (AESEnum.CBC_NO_PADDING.equals(algorithm) || AESEnum.CBC_PKCS5PADDING.equals(algorithm)) {
                checkVectorKey(vectorKey);

                IvParameterSpec iv = new IvParameterSpec(vectorKey.getBytes());
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            }

            return cipher.doFinal(plaintext);
        } catch (Exception e) {
            logger.log(Level.INFO, "AESCipherEncryptImp encrypt error: {0}", new Object[]{e.getMessage()});
        }

        return null;
    }

    @Override
    protected String decrypt(byte[] content, String saltKey, String vectorKey, AESEnum algorithm) throws Exception {
        try {
            checkSaltKey(saltKey);
            checkAlgorithm(algorithm);

            Cipher cipher = Cipher.getInstance(algorithm.getAlgorithm());

            SecretKey secretKey = new SecretKeySpec(saltKey.getBytes(), AES_ALGORITHM_NAME);
            if (AESEnum.CBC_NO_PADDING.equals(algorithm) || AESEnum.CBC_PKCS5PADDING.equals(algorithm)) {
                checkVectorKey(vectorKey);

                IvParameterSpec iv = new IvParameterSpec(vectorKey.getBytes());
                cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
            }

            byte[] original = cipher.doFinal(content);
            return new String(original).trim();
        } catch (Exception e) {
            logger.log(Level.INFO, "AESCipherEncryptImp decrypt error: {0}", new Object[]{e.getMessage()});
        }

        return null;
    }

    private void checkSaltKey( String saltKey) {
        if (saltKey == null || saltKey.length() != SALT_KEY_LENGTH) {
            throw new IllegalArgumentException("saltKey is null or saltKey is not at " + SALT_KEY_LENGTH + "-bytes.");
        }
    }

    private void checkVectorKey(String vectorKey) {
        if (vectorKey == null || vectorKey.length() != VECTOR_KEY_LENGTH) {
            throw new IllegalArgumentException("vectorKey is null or vectorKey is not at " + VECTOR_KEY_LENGTH + "-bytes.");
        }
    }
}
