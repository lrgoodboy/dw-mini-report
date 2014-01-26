package com.anjuke.dw.mini.controller;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.anjuke.dw.mini.service.LoginService;


@Controller
@SessionAttributes({LoginService.SESSION_USER, LoginService.SESSION_FROM})
public class LoginController {

    @Autowired
    @Qualifier("commonProperties")
    private Properties commonProperties;

    @RequestMapping("/login")
    @ResponseBody
    public String login(ModelMap model) {

        return model.get(LoginService.SESSION_FROM).toString();

//        RedirectView redirect = new RedirectView();
//        redirect.setStatusCode(HttpStatus.FOUND);
//        redirect.setUrl(UriComponentsBuilder
//                .fromHttpUrl(commonProperties.getProperty("oauth.url"))
//                .path("/authorize.php")
//                .queryParam("client_id", commonProperties.getProperty("oauth.client"))
//                .queryParam("response_type", "code")
//                .build().toUriString());
//
//        return redirect;
    }

//    @RequestMapping("/login")
//    public String login(@RequestParam(value = "code", required = false) String code,
//            @RequestParam(value = "access_token", required = false) String accessToken) throws Exception {
//
//        SecurityContext security = SecurityContextHolder.getContext();
//        security.getAuthentication().setAuthenticated(false); // test
//
//        // authenticated
//        Authentication token = security.getAuthentication();
//        if (token.isAuthenticated()) {
//            return "redirect:/info";
//        }
//
//        // logged in
//        if (accessToken != null) {
//
//            CloseableHttpClient httpClient = HttpClients.createDefault();
//
//            HttpPost httpPost = new HttpPost(String.format(
//                    "%s/resource.php", oauthProperties.getProperty("oauth.url")));
//            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//            nvps.add(new BasicNameValuePair("oauth_token", accessToken));
//            httpPost.setEntity(new UrlEncodedFormEntity(nvps));;
//            CloseableHttpResponse response = httpClient.execute(httpPost);
//
//            System.out.println(EntityUtils.toString(response.getEntity()));
//            return "redirect:/info";
//
//
////            UserDetails user = new AnjukeUser("jizhang");
////            token = new UsernamePasswordAuthenticationToken(user, "oauth_token", user.getAuthorities());
////            security.setAuthentication(token);
//        }
//
//        // access token
//        if (code != null) {
//            return String.format("redirect:%s/authorize.php?client_id=%s&client_secret=%s&code=%s",
//                    oauthProperties.getProperty("oauth.url"),
//                    oauthProperties.getProperty("oauth.client"),
//                    oauthProperties.getProperty("oauth.secret"),
//                    code);
//        }
//
//        // request token
//        return String.format("redirect:%s/authorize.php?client_id=%s",
//                oauthProperties.getProperty("oauth.url"),
//                oauthProperties.getProperty("oauth.client"));
//
//
//    }

}
