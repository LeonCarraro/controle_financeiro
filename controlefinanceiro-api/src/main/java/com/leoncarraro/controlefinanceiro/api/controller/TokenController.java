package com.leoncarraro.controlefinanceiro.api.controller;

import com.leoncarraro.controlefinanceiro.api.config.property.APIProperty;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/tokens")
@AllArgsConstructor
public class TokenController {

    private final APIProperty APIProperty;

    @DeleteMapping(value = "/revoke")
    public void revoke(HttpServletRequest req, HttpServletResponse res) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", null);
        refreshTokenCookie.setSecure(APIProperty.getSecurity().isEnableHttps());
        refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");
        refreshTokenCookie.setMaxAge(0);

        res.addCookie(refreshTokenCookie);
        res.setStatus(HttpStatus.NO_CONTENT.value());
    }

}
