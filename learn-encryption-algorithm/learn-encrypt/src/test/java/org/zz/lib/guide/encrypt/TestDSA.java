package org.zz.lib.guide.encrypt;

import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Logger;

/**
 * DSA, 分为2个步骤
 * 准备秘钥相关参数
 * 执行签名
 *
 * SHA1withDSA
 * SHA224withDSA
 * SHA256withDSA
 * SHA384withDSA
 * SHA512withDSA
 */
public class TestDSA {
    public static final Logger logger = Logger.getLogger("TestDSA");
    static final String T_PLAINTEXT = "xiaobai security dsa";
    static final String DSA_ALGORITHM = "DSA";
    static final String SIGNATURE_ALGORITHM = "SHA1withDSA";

    @Test
    public void testDSA() throws Exception {
        byte[] plaintextBytes = T_PLAINTEXT.getBytes(StandardCharsets.UTF_8);
        int keyLength = 512;

        /* 一、初始化密钥 */
        // 1.获得DSA的KeyPairGenerator对象示例
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(DSA_ALGORITHM);
        // 2.修改key的长度
        keyPairGenerator.initialize(keyLength);
        // 3.得到KeyPair对象
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 4.获取公钥
        DSAPublicKey dsaPublicKey = (DSAPublicKey) keyPair.getPublic();
        // 5.获取私钥
        DSAPrivateKey dsaPrivateKey = (DSAPrivateKey) keyPair.getPrivate();

        /* 二、执行签名 */
        // 1.通过私钥进行签名，获取PKCS8EncodedKeySpec对象示例
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(dsaPrivateKey.getEncoded());
        // 2.通过DSA算法构建KeyFactory实例对象
        KeyFactory keyFactory = KeyFactory.getInstance(DSA_ALGORITHM);
        // 3.声明privateKey
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        // 4.创建签名对象，这里我们使用的是JDK的方式实现的所以在这里我们使用"SHA1withDSA"
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 5.执行签名的初始化方法
        signature.initSign(privateKey);
        // 6.修改需要处理的内容
        signature.update(plaintextBytes);
        // 7.执行签名
        byte[] result = signature.sign();

        System.out.println("jdk dsa sign:"+ Hex.encodeHexString(result));


        /* 三、验证签名 */
        // 1.通过公钥进行签名，获取X509EncodedKeySpec对象示例
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(dsaPublicKey.getEncoded());
        // 2.通过DSA算法构建KeyFactory实例对象  (此处是为了完整性，所以新创建了一个对象)
        keyFactory = KeyFactory.getInstance(DSA_ALGORITHM);
        // 3.获得PublicKey对象
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        // 4.创建签名对象，这里我们使用的是JDK的方式实现的所以在这里我们使用"SHA1withDSA"
        signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 5.验证签名的初始化方法
        signature.initVerify(publicKey);
        // 6.修改需要处理的内容
        signature.update(plaintextBytes);
        // 7.验证签名
        boolean resultBool = signature.verify(result);
        System.out.println("jdk dsa verify:" + resultBool);
    }
}
