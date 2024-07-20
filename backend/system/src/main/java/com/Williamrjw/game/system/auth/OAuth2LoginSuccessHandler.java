package com.Williamrjw.game.system.auth;

import com.Williamrjw.game.common.domain.entity.User;
import com.Williamrjw.game.common.user.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

@Component
@EnableConfigurationProperties(TokenProperties.class)
@Slf4j
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    TokenService tokenService;

    @Autowired
    TokenProperties properties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        User user = User.builder()
                .githubId(oAuth2User.getAttribute("id").toString())
                .username(oAuth2User.getAttribute("name"))
                .email(oAuth2User.getAttribute("email"))
                .build();
        // TODO 还未做权限标识
        LoginUser loginUser = new LoginUser(user,new HashSet<>());
        log.info("用户【{}】登录成功",loginUser.getUsername());
        // 生成token
        String token = tokenService.createToken(loginUser);

        // 创建 cookie
        Cookie cookie = new Cookie(properties.getKey(), token);
        cookie.setMaxAge(3600);  // 设置 cookie 有效期为1小时
        cookie.setPath("/");     // 设置Cookie对整个应用可用
        // 添加 cookie 到响应
        response.addCookie(cookie);

        // 重定向到根路径
        getRedirectStrategy().sendRedirect(request, response, "/");
    }
}
