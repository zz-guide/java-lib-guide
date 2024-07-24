package org.zz.lib.guide.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.common.util.NumCodeGenerateUtil;
import org.zz.lib.guide.jwt.config.WebJwtProperties;
import org.zz.lib.guide.jwt.util.JwtUtil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestJwtAlgorithm {
    public static final Logger logger = Logger.getLogger("TestJwtAlgorithm");

    @Test
    public void testNone(){
        Algorithm algorithm = Algorithm.none();

        Map<String, Object> payload = new HashMap<>();
        payload.put("id", "123");
        payload.put("name", "仔仔");

        WebJwtProperties webJwtProperties = new WebJwtProperties();
        String subject = "仔仔";
        String issuer = webJwtProperties.getIssuer();
        Instant issuedAt = LocalDateTime.now().toInstant(JwtUtil.ZONE_OFFSET);
        Instant expireAt = LocalDateTime.now()
                .plusSeconds(webJwtProperties.getEffectiveTimeSeconds())
                .toInstant(JwtUtil.ZONE_OFFSET);

        // 可通过 withClaim 单独设置值
        // 也可以通过 withPayload 将map里的键值对设置进去

        String jwtToken = JWT.create()
                .withSubject(subject) // 主体
                .withIssuer(issuer) // 签发者
                .withIssuedAt(issuedAt) // 签发时间
                .withExpiresAt(expireAt) // 过期时间
                .withPayload(payload) // payload
                .sign(algorithm);

        logger.log(Level.INFO, "jwt token: {0}", new Object[]{jwtToken});

        DecodedJWT decodedJWT = JWT.require(algorithm)
                .withIssuer(webJwtProperties.getIssuer())
                .build()
                .verify(jwtToken);

        String decodeIssuer = decodedJWT.getIssuer();
        logger.log(Level.INFO, " 解密 jwt issuer: {0}", new Object[]{decodeIssuer});

        String decodeSubject = decodedJWT.getSubject();
        logger.log(Level.INFO, " 解密 jwt subject: {0}", new Object[]{decodeSubject});

        List<String> decodeAudience = decodedJWT.getAudience();
        logger.log(Level.INFO, " 解密 jwt audience: {0}", new Object[]{decodeAudience});

        Date decodeExpiresAt = decodedJWT.getExpiresAt();
        logger.log(Level.INFO, " 解密 jwt expiresAt: {0}", new Object[]{decodeExpiresAt});

        Date decodeNotBefore = decodedJWT.getNotBefore();
        logger.log(Level.INFO, " 解密 jwt notBefore: {0}", new Object[]{decodeNotBefore});

        Date decodeIssuedAt = decodedJWT.getIssuedAt();
        logger.log(Level.INFO, " 解密 jwt issuedAt: {0}", new Object[]{decodeIssuedAt});

        String decodeJwtId = decodedJWT.getId();
        logger.log(Level.INFO, " 解密 jwt id: {0}", new Object[]{decodeJwtId});

        String claimId = decodedJWT.getClaim("id").asString();
        logger.log(Level.INFO, " 解密 jwt custom claim id: {0}", new Object[]{claimId});

        String claimName = decodedJWT.getClaim("name").asString();
        logger.log(Level.INFO, " 解密 jwt custom claim name: {0}", new Object[]{claimName});
    }
}
