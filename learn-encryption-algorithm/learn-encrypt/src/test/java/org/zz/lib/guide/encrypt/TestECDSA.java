package org.zz.lib.guide.encrypt;

import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Logger;

/**
 * TestECDSA, 分为2个步骤
 *
 * 准备秘钥相关参数
 * 执行签名
 *
 * NONEwithECDSA
 * RIPEMD160withECDSA
 * SHA1withECDSA
 * SHA224withECDSA
 * SHA256withECDSA
 * SHA384withECDSA
 * SHA512withECDSA
 */
public class TestECDSA {
    public static final Logger logger = Logger.getLogger("TestECDSA");
    static final String T_PLAINTEXT = "xiaobai security dsa";
    static final String EC_ALGORITHM = "EC";
    static final String SIGNATURE_ALGORITHM = "SHA1withDSA";

    @Test
    public void testECDSA() throws Exception {
        byte[] plaintextBytes = T_PLAINTEXT.getBytes(StandardCharsets.UTF_8);
        int keyLength = 256;

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EC_ALGORITHM);
        // 2.修改key的长度
        keyPairGenerator.initialize(keyLength);
        // 3.得到KeyPair对象
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 4.获取公钥
        ECPublicKey ecPublicKey = (ECPublicKey) keyPair.getPublic();
        // 5.获取私钥
        ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair.getPrivate();

        /* 二、执行签名 */
        // 1.通过私钥进行签名，获取PKCS8EncodedKeySpec对象示例
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());
        // 2.通过EC算法构建KeyFactory实例对象
        KeyFactory keyFactory = KeyFactory.getInstance(EC_ALGORITHM);
        // 3.声明privateKey
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        // 4.创建签名对象，这里我们使用的是JDK的方式实现的所以在这里我们使用"SHA1withECDSA"
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 5.执行签名的初始化方法
        signature.initSign(privateKey);
        // 6.修改需要处理的内容
        signature.update(plaintextBytes);
        // 7.执行签名
        byte[] result = signature.sign();

        System.out.println("jdk ecdsa sign:"+ Hex.encodeHexString(result));

        /* 三、验证签名 */
        // 1.通过公钥进行签名，获取X509EncodedKeySpec对象示例
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(ecPublicKey.getEncoded());
        // 2.通过EC算法构建KeyFactory实例对象  (此处是为了完整性，所以新创建了一个对象)
        keyFactory = KeyFactory.getInstance(EC_ALGORITHM);
        // 3.获得PublicKey对象
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        // 4.创建签名对象，这里我们使用的是JDK的方式实现的所以在这里我们使用"SHA1withECDSA"
        signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 5.验证签名的初始化方法
        signature.initVerify(publicKey);
        // 6.修改需要处理的内容
        signature.update(plaintextBytes);
        // 7.验证签名
        boolean resultBool = signature.verify(result);
        System.out.println("jdk ecdsa verify:" + resultBool);
    }
}
