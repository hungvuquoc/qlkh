package com.example.qlkh.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.EbsWarehouse;
import com.example.qlkh.entity.User;
import com.example.qlkh.service.UserService;
import com.example.qlkh.service.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.qlkh.constant.Variables.*;

@Component
public class EbsTokenUtils {
    private static UserService userService;
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());

    public EbsTokenUtils(UserService userServiceImpl) {
        userService = userServiceImpl;
    }

    public static String createAccessToken(User user) {
        Map<Long, EbsWarehouse> ebsWarehouses = userService.getWarehouseByUserId(user.getId());

        return createAccessToken(user, ebsWarehouses);
    }

    public static String createAccessToken(User user, Map<Long, EbsWarehouse> ebsWarehouses) {
        EbsPrincipal userPrincipal = UserMapper.INSTANCE.entityToPrincipal(user);
        userPrincipal.setWarehouses(ebsWarehouses);

        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("id", user.getId())
                .withClaim("user", EbsConvertUtils.toString(userPrincipal))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_TIME))
                .withClaim("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public static String createRefreshToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_TIME))
                .sign(algorithm);
    }
}
