package com.deep.basket.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtServiceImpl implements JwtService{
    //보안키
    private String secretKey = "abdafekfoa@@efdkaef$dfkaljef!!!afjkdlaefa49024893205";

    @Override
    public String getToken(String key, Object value) {

        Date expTime = new Date();
        expTime.setTime(expTime.getTime() + 1000 * 60 * 5);
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ","JWT");
        headerMap.put("alg", "HS256");

        Map<String, Object> map = new HashMap<>();
        map.put(key,value);
        //맵에 키 밸류 넣어준다.
        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expTime)
                .signWith(SignatureAlgorithm.HS256, signKey);   //빨간줄떠서 바꿔놓음 위치

        return builder.compact();
    }
}
