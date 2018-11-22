package com.demo.orderserver.domain.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
// import org.springframework.http.converter.HttpMessageConverter;
// import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// import java.nio.charset.Charset;
// import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.orderserver.infrastructure.security.AccessTokenResponse;
import com.demo.orderserver.infrastructure.security.RefreshTokenResult;
// import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "/")
public class WebController {

    public static final String LOGIN = "login";

    public static final String APP = "app";

    @Value("${oauth.auth.url}")
    private String authUrl;

    @Value("${oauth.token.url}")
    private String accessTokenUrl;

    @Value("${oauth.token.refresh.url}")
    private String refreshTokenUrl;

    @Value("${oauth.id.secret}")
    private String encodedIdSecret;

    @Value("${oauth.token.expire}")
    private Integer accessTokenExpireTime;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping
    public String index(Model model, @RequestParam(value = "code", required = false) String authCode, HttpServletRequest request, HttpServletResponse response) {

        String page = APP;

        if (authCode != null && authCode.trim().length() > 0) {

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Basic ".concat(encodedIdSecret));
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
            String grantType = "?grant_type=authorization_code";
            String clientId = "&client_id=web-client";
            String clientSecret = "&client_secret=d2ViLWNsaWVudDp3ZWItY2xpZW50LXNlY3JldA==";
            String code = "&code=" + authCode;
            String redirect = "&redirect_uri=http://localhost:8080";
            // String url = accessTokenUrl + grantType + code + clientId + clientSecret + redirect;
            String url = "http://localhost:8383/oauth/token" + grantType + code + clientId + clientSecret + redirect;

            ResponseEntity<AccessTokenResponse> accessTokenResponse = restTemplate.exchange(url, HttpMethod.POST, httpEntity, AccessTokenResponse.class);

            if (accessTokenResponse.getStatusCode() == HttpStatus.ACCEPTED || accessTokenResponse.getStatusCode() == HttpStatus.OK) {

                Cookie cookie = new Cookie("access_token", accessTokenResponse.getBody().getAccessToken());
                cookie.setMaxAge(accessTokenExpireTime + 60);
                response.addCookie(cookie);

                request.getSession(true).setAttribute("refreshToken", accessTokenResponse.getBody().getRefreshToken());
            } else {
                model.addAttribute("authUrl", authUrl);
                page = LOGIN;
            }
        } else {
            model.addAttribute("authUrl", authUrl);
            page = LOGIN;
        }
        return page;
    }

    // @RequestMapping
    // public String index(Model model, @RequestParam(value = "code", required = false) String authCode, HttpServletRequest request, HttpServletResponse response) {

    //     String page = APP;

    //     if (authCode != null && authCode.trim().length() > 0) {

    //         HttpHeaders httpHeaders = new HttpHeaders();
    //         httpHeaders.add("Authorization", "Basic ".concat(encodedIdSecret));

    //         ResponseEntity<AccessTokenResponse> accessTokenResponse = restTemplate.exchange(String.format(accessTokenUrl, authCode), HttpMethod.GET, new HttpEntity<Object>(httpHeaders), AccessTokenResponse.class);

    //         if (accessTokenResponse.getStatusCode() == HttpStatus.ACCEPTED || accessTokenResponse.getStatusCode() == HttpStatus.OK) {
                
    //             Cookie cookie = new Cookie("access_token", accessTokenResponse.getBody().getAccessToken());
    //             cookie.setMaxAge(accessTokenExpireTime + 60);
    //             response.addCookie(cookie);

    //             request.getSession(true).setAttribute("refreshToken", accessTokenResponse.getBody().getRefreshToken());
    //         } else {
    //             model.addAttribute("authUrl", authUrl);
    //             page = LOGIN;
    //         }
    //     } else {
    //         model.addAttribute("authUrl", authUrl);
    //         page = LOGIN;
    //     }
    //     return page;
    // }

    @ResponseBody
    @RequestMapping(value = "refreshToken")
    public ResponseEntity<RefreshTokenResult> refreshToken(HttpServletRequest request) {

        RefreshTokenResult result = new RefreshTokenResult();
        String refreshToken = String.valueOf(request.getSession().getAttribute("refreshToken"));

        if (refreshToken != null) {

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Basic ".concat(encodedIdSecret));

            ResponseEntity<AccessTokenResponse> accessTokenResponse =
                restTemplate.exchange(String.format(refreshTokenUrl, refreshToken), HttpMethod.GET, new HttpEntity<Object>(httpHeaders), AccessTokenResponse.class);

            if (accessTokenResponse.getStatusCode() == HttpStatus.ACCEPTED || accessTokenResponse.getStatusCode() == HttpStatus.OK) {
                result.setAccessToken(accessTokenResponse.getBody().getAccessToken());
                result.setIsSuccess(true);
            } else {
                result.setIsSuccess(false);
            }
        } else {
            result.setIsSuccess(false);
        }
        return new ResponseEntity<RefreshTokenResult>(result, HttpStatus.OK);
    }
}