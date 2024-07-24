package org.zz.lib.guide.encrypt.algorithm.impl;

import org.zz.lib.guide.encrypt.algorithm.CipherEncryptImpl;
import org.zz.lib.guide.encrypt.enums.DESEnum;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DESCipherEncryptImp extends CipherEncryptImpl<DESEnum> {
    public static final Logger logger = Logger.getLogger("DESCipherEncryptImp");

    private final static String DES_ALGORITHM_NAME = "DES";

    private final static int SALT_KEY_LENGTH = 8;
    private final static int VECTOR_KEY_LENGTH = 8;

    @Override
    protected byte[] encrypt(String content, String saltKey, String vectorKey, DESEnum algorithm) throws Exception {
        try {
            checkSaltKey(saltKey);
            checkAlgorithm(algorithm);

            Cipher cipher = Cipher.getInstance(algorithm.getAlgorithm());
            byte[] plaintext;
            if (DESEnum.CBC_NO_PADDING.equals(algorithm) || DESEnum.ECB_NO_PADDING.equals(algorithm)) {
                plaintext = handleNoPaddingEncryptFormat(cipher, content);
            } else {
                plaintext = content.getBytes();
            }

            SecretKey secretKey = new SecretKeySpec(saltKey.getBytes(), DES_ALGORITHM_NAME);
            if (DESEnum.CBC_NO_PADDING.equals(algorithm) || DESEnum.CBC_PKCS5PADDING.equals(algorithm)) {
                checkVectorKey(vectorKey);

                IvParameterSpec iv = new IvParameterSpec(vectorKey.getBytes());
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            }

            return cipher.doFinal(plaintext);
        } catch (Exception e) {
			 logger.log(Level.INFO, "DESCipherEncryptImp encrypt error: {0}", new Object[]{e.getMessage()});
        }

        return null;
    }

    @Override
    protected String decrypt(byte[] content, String saltKey, String vectorKey, DESEnum algorithm) throws Exception {
        try {
            checkSaltKey(saltKey);
            checkAlgorithm(algorithm);

            Cipher cipher = Cipher.getInstance(algorithm.getAlgorithm());

            SecretKey secretKey = new SecretKeySpec(saltKey.getBytes(), DES_ALGORITHM_NAME);
            if (DESEnum.CBC_NO_PADDING.equals(algorithm) || DESEnum.CBC_PKCS5PADDING.equals(algorithm)) {
                checkVectorKey(vectorKey);

                IvParameterSpec iv = new IvParameterSpec(vectorKey.getBytes());
                cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
            }

            byte[] original = cipher.doFinal(content);
            return new String(original).trim();
        } catch (Exception e) {
            logger.log(Level.INFO, "DESCipherEncryptImp decrypt error: {0}", new Object[]{e.getMessage()});
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
