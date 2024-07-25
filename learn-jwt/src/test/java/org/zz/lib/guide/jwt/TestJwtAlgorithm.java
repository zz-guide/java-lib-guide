package org.zz.lib.guide.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.jwt.config.WebJwtProperties;
import org.zz.lib.guide.jwt.util.JwtRSAUtil;
import org.zz.lib.guide.jwt.util.JwtUtil;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * jwt的加密算法主要针对签名部分加密，使得无法被篡改
 * 支持的算法有HMAC系列, RSA系列, ECDSA系列
 */

public class TestJwtAlgorithm {
    public static final Logger logger = Logger.getLogger("TestJwtAlgorithm");

    @Test
    public void testHmac() {
        String secretKey = "12345678";
        Algorithm algorithm = Algorithm.HMAC256(secretKey); // HMACXXX都一样

        Map<String, Object> payload = new HashMap<>();
        payload.put("id", "123x");
        payload.put("name", "仔仔1");

        WebJwtProperties webJwtProperties = new WebJwtProperties();
        String subject = "仔仔";
        String issuer = webJwtProperties.getIssuer();
        Instant issuedAt = LocalDateTime.now().toInstant(JwtUtil.ZONE_OFFSET);
        Instant expireAt = LocalDateTime.now().plusSeconds(3600).toInstant(JwtUtil.ZONE_OFFSET);

        String jwtToken = JWT.create()
                .withSubject(subject) // 主体
                .withIssuer(issuer) // 签发者
                .withIssuedAt(issuedAt) // 签发时间
                .withExpiresAt(expireAt) // 过期时间
                .withPayload(payload) // payload
                .sign(algorithm);

        logger.log(Level.INFO, "jwt token: {0}", new Object[]{jwtToken});
        JWTVerifier build = JWT.require(algorithm).withIssuer(webJwtProperties.getIssuer()).build();
        DecodedJWT decodedJWT = build.verify(jwtToken);
    }

    @Test
    public void testRSA() throws Exception {
        // 初始化秘钥相关对象
        String resourcesPath = getResourcesPath();
        byte[] privateKeyBytes = JwtRSAUtil.validateKeyAndDecodeContent(
                Files.readString(Paths.get(resourcesPath,"rsa_private_pkcs8.pem")),
                JwtRSAUtil.PRIVATE_KEY
        );
        RSAPrivateKey privateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

        byte[] publicKeyBytes = JwtRSAUtil.validateKeyAndDecodeContent(
                Files.readString(Paths.get(resourcesPath,"rsa_public_x509.pem")),
                JwtRSAUtil.PUBLIC_KEY
        );

        RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));

        // 传入秘钥对象，jwt库会自动加密
        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey); // RSA 256,384,512都一样

        Map<String, Object> payload = new HashMap<>();
        payload.put("id", "123x");
        payload.put("name", "仔仔1");

        WebJwtProperties webJwtProperties = new WebJwtProperties();
        String subject = "仔仔";
        String issuer = webJwtProperties.getIssuer();
        Instant issuedAt = LocalDateTime.now().toInstant(JwtUtil.ZONE_OFFSET);
        Instant expireAt = LocalDateTime.now().plusSeconds(3600).toInstant(JwtUtil.ZONE_OFFSET);

        String jwtToken = JWT.create()
                .withSubject(subject) // 主体
                .withIssuer(issuer) // 签发者
                .withIssuedAt(issuedAt) // 签发时间
                .withExpiresAt(expireAt) // 过期时间
                .withPayload(payload) // payload
                .sign(algorithm);

        logger.log(Level.INFO, "jwt token: {0}", new Object[]{jwtToken});
        JWTVerifier build = JWT.require(algorithm).withIssuer(webJwtProperties.getIssuer()).build();
        DecodedJWT decodedJWT = build.verify(jwtToken);

        logger.log(Level.INFO, "claim name: {0}", new Object[]{decodedJWT.getClaim("name").asString()});
    }

    @Test
    public void testECDSA() throws Exception {
        String resourcesPath = getResourcesPath();
        byte[] privateKeyBytes = JwtRSAUtil.validateKeyAndDecodeContent(
                Files.readString(Paths.get(resourcesPath,"rsa_private_pkcs8.pem")),
                JwtRSAUtil.PRIVATE_KEY
        );
        RSAPrivateKey privateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

        byte[] publicKeyBytes = JwtRSAUtil.validateKeyAndDecodeContent(
                Files.readString(Paths.get(resourcesPath,"rsa_public_x509.pem")),
                JwtRSAUtil.PUBLIC_KEY
        );

        RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));

        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey); // RSA 256,384,512都一样

        Map<String, Object> payload = new HashMap<>();
        payload.put("id", "123x");
        payload.put("name", "仔仔1");

        WebJwtProperties webJwtProperties = new WebJwtProperties();
        String subject = "仔仔";
        String issuer = webJwtProperties.getIssuer();
        Instant issuedAt = LocalDateTime.now().toInstant(JwtUtil.ZONE_OFFSET);
        Instant expireAt = LocalDateTime.now().plusSeconds(3600).toInstant(JwtUtil.ZONE_OFFSET);

        String jwtToken = JWT.create()
                .withSubject(subject) // 主体
                .withIssuer(issuer) // 签发者
                .withIssuedAt(issuedAt) // 签发时间
                .withExpiresAt(expireAt) // 过期时间
                .withPayload(payload) // payload
                .sign(algorithm);

        logger.log(Level.INFO, "jwt token: {0}", new Object[]{jwtToken});
        JWTVerifier build = JWT.require(algorithm).withIssuer(webJwtProperties.getIssuer()).build();
        DecodedJWT decodedJWT = build.verify(jwtToken);

        logger.log(Level.INFO, "claim name: {0}", new Object[]{decodedJWT.getClaim("name").asString()});
    }

    @Test
    public void t() throws Exception {
        String resourcesPath = getResourcesPath();
        byte[] bytes = Files.readAllBytes(Paths.get(resourcesPath,"private.pem"));
        System.out.println("private.pem:"+new String(bytes));

        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bytes));
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(bytes));
    }

    private static String getResourcesPath(){
        return "E:\\jungle\\github\\zz-guide\\java-lib-guide\\秘钥\\rsa\\pkcs8";
        /*String classPath = Objects.requireNonNull(TestJwtAlgorithm.class.getResource("/")).getPath();
        System.out.println("classPath:" + classPath);
        String jarPath = new File(classPath).getParentFile().getParent();
        System.out.println("jarPath:" + jarPath);
        return Paths.get(jarPath, "/target/classes").normalize().toString();*/
    }
}
