package com.custchina.shequdemo.Comtroller;

import com.custchina.shequdemo.Service.UserService;
import com.custchina.shequdemo.dto.AccessTokenDTO;
import com.custchina.shequdemo.dto.GithubUser;
import com.custchina.shequdemo.mapper.UserMapper;
import com.custchina.shequdemo.model.User;
import com.custchina.shequdemo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by codedrinker on 2019/4/24.
 */
@Controller

public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        User user = new User();
        user.setToken(UUID.randomUUID().toString());
        user.setName(user.getName());
        user.setAccountId(String.valueOf(githubUser.getId()));

//        user.setAvaterUrl(githubUser.getAvatar_url());
//        System.out.println(githubUser.getAvatar_url());
        userService.createorupdate(user);
        request.getSession().setAttribute("user",githubUser);
        return "redirect:/";
        }
        @GetMapping("/logout")
        public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        session.invalidate();

        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
        }
    }



