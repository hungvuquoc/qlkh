package com.example.qlkh.config;

import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.EbsWarehouse;
import com.example.qlkh.dto.TokenDto;
import com.example.qlkh.entity.User;
import com.example.qlkh.service.UserService;
import com.example.qlkh.service.mapper.UserMapper;
import com.example.qlkh.utils.EbsConvertUtils;
import com.example.qlkh.utils.EbsTokenUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;

    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();

        userService.permanentLock(user.getUsername());

        Map<Long, EbsWarehouse> ebsWarehouses = userService.getWarehouseByUserId(user.getId());
        String accessToken = EbsTokenUtils.createAccessToken(user, ebsWarehouses);
        String refreshToken = EbsTokenUtils.createRefreshToken(user.getUsername());
        EbsPrincipal userPrincipal = UserMapper.INSTANCE.entityToPrincipal(user);
        userPrincipal.setWarehouses(ebsWarehouses);

        Map<String, Boolean> authorities = new HashMap<>();
        for (String authority : userPrincipal.getAuthorities()) {
            authorities.put(authority, true);
        }
        if (CollectionUtils.isNotEmpty(userPrincipal.getRoles())) {
            for (String role : userPrincipal.getRoles()) {
                authorities.put(role, true);
            }
        }

        TokenDto tokenDto = new TokenDto(accessToken, refreshToken, userPrincipal, authorities);
        response.getWriter().write(Objects.requireNonNull(EbsConvertUtils.toString(tokenDto)));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
