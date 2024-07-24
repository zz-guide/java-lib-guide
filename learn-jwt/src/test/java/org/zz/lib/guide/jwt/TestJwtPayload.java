package org.zz.lib.guide.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.jwt.config.WebJwtProperties;
import org.zz.lib.guide.jwt.pojo.User;
import org.zz.lib.guide.jwt.util.JwtUtil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 1. withClaim 只支持基本数据类型和对应的装箱类型，以及List,Map,不支持自定义类型
 * 所以要么使用map,要么转成json之后存到claim字段中
 */

public class TestJwtPayload {
    public static final Logger logger = Logger.getLogger("TestJwtPayload");

    @Test
    public void testWithPayload(){
        Algorithm algorithm = Algorithm.none();

        Map<String, String> payload = new HashMap<>();
        payload.put("id", "123");
        payload.put("name", "仔仔");

        WebJwtProperties webJwtProperties = new WebJwtProperties();
        String subject = "仔仔";
        String issuer = webJwtProperties.getIssuer();
        Instant issuedAt = LocalDateTime.now().toInstant(JwtUtil.ZONE_OFFSET);
        Instant expireAt = LocalDateTime.now()
                .plusSeconds(webJwtProperties.getEffectiveTimeSeconds())
                .toInstant(JwtUtil.ZONE_OFFSET);

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

        String claimId = decodedJWT.getClaim("id").asString();
        logger.log(Level.INFO, " 解密 jwt custom claim id: {0}", new Object[]{claimId});

        String claimName = decodedJWT.getClaim("name").asString();
        logger.log(Level.INFO, " 解密 jwt custom claim name: {0}", new Object[]{claimName});
    }

    @Test
    public void testWithClaimMap(){
        Algorithm algorithm = Algorithm.none();

        Map<String, String> payload = new HashMap<>();
        payload.put("id", "123");
        payload.put("name", "仔仔");

        WebJwtProperties webJwtProperties = new WebJwtProperties();
        String subject = "仔仔";
        String issuer = webJwtProperties.getIssuer();
        Instant issuedAt = LocalDateTime.now().toInstant(JwtUtil.ZONE_OFFSET);
        Instant expireAt = LocalDateTime.now()
                .plusSeconds(webJwtProperties.getEffectiveTimeSeconds())
                .toInstant(JwtUtil.ZONE_OFFSET);

        String jwtToken = JWT.create()
                .withSubject(subject) // 主体
                .withIssuer(issuer) // 签发者
                .withIssuedAt(issuedAt) // 签发时间
                .withExpiresAt(expireAt) // 过期时间
                .withClaim("user", payload) // 过期时间
                .sign(algorithm);

        logger.log(Level.INFO, "jwt token: {0}", new Object[]{jwtToken});

        DecodedJWT decodedJWT = JWT.require(algorithm)
                .withIssuer(webJwtProperties.getIssuer())
                .build()
                .verify(jwtToken);

        Map<String, Object> user = decodedJWT.getClaim("user").asMap();

        String claimId = user.get("id").toString();
        logger.log(Level.INFO, " 解密 jwt custom claim id: {0}", new Object[]{claimId});

        String claimName = user.get("name").toString();;
        logger.log(Level.INFO, " 解密 jwt custom claim name: {0}", new Object[]{claimName});
    }

    @Test
    public void testWithClaimJsonString() throws JsonProcessingException {
        Algorithm algorithm = Algorithm.none();

        User user = new User();
        user.setId(1L);
        user.setName("仔仔");

        ObjectMapper objectMapper = new ObjectMapper();
        String payloadJsonString = objectMapper.writeValueAsString(user);

        WebJwtProperties webJwtProperties = new WebJwtProperties();
        String subject = "仔仔";
        String issuer = webJwtProperties.getIssuer();
        Instant issuedAt = LocalDateTime.now().toInstant(JwtUtil.ZONE_OFFSET);
        Instant expireAt = LocalDateTime.now()
                .plusSeconds(webJwtProperties.getEffectiveTimeSeconds())
                .toInstant(JwtUtil.ZONE_OFFSET);

        String jwtToken = JWT.create()
                .withSubject(subject) // 主体
                .withIssuer(issuer) // 签发者
                .withIssuedAt(issuedAt) // 签发时间
                .withExpiresAt(expireAt) // 过期时间
                .withClaim("user", payloadJsonString) // 可以把一个json对象直接设置到claim
                .sign(algorithm);

        logger.log(Level.INFO, "jwt token: {0}", new Object[]{jwtToken});

        DecodedJWT decodedJWT = JWT.require(algorithm)
                .withIssuer(webJwtProperties.getIssuer())
                .build()
                .verify(jwtToken);

        User decodeUser = objectMapper.readValue(decodedJWT.getClaim("user").asString(), User.class);
        logger.log(Level.INFO, " 解密 jwt custom user: {0}", new Object[]{decodeUser});
    }
}
