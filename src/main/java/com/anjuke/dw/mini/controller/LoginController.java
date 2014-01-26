package com.anjuke.dw.mini.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;

import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import com.anjuke.dw.mini.model.AnjukeUser;


@Controller
@SessionAttributes({"currentUser", "loginFrom"})
public class LoginController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    @Qualifier("commonProperties")
    private Properties commonProperties;

    @RequestMapping("/login")
    public View login(ModelMap model,
            @RequestParam(value = "from", required = false) String from,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "access_token", required = false) String accessToken) throws Exception {

        RedirectView redirect = new RedirectView();
        redirect.setStatusCode(HttpStatus.FOUND);

        String oauthUrl = commonProperties.getProperty("oauth.url");
        String oauthClient = commonProperties.getProperty("oauth.client");
        String oauthSecret = commonProperties.getProperty("oauth.secret");

        if (accessToken != null) {

            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {

                HttpPost httpPost = new HttpPost(oauthUrl + "/resource.php");
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                nvps.add(new BasicNameValuePair("oauth_token", accessToken));
                nvps.add(new BasicNameValuePair("getinfo", "true"));
                httpPost.setEntity(new UrlEncodedFormEntity(nvps));

                CloseableHttpResponse response = httpClient.execute(httpPost);
                try {

                    JSONObject info = JSONObject.fromObject(EntityUtils.toString(response.getEntity()));
                    AnjukeUser user = new AnjukeUser();
                    user.setUsername(info.getString("username"));
                    user.setName(info.getString("name"));
                    user.setEmail(info.getString("email"));
                    model.addAttribute("currentUser", user);

                    redirect.setUrl((String) model.get("loginFrom"));
                    return redirect;

                } finally {
                    response.close();
                }

            } finally {
                httpClient.close();
            }
        }

        if (code != null) {
            redirect.setUrl(UriComponentsBuilder
                    .fromHttpUrl(oauthUrl)
                    .path("/token.php")
                    .queryParam("client_id", oauthClient)
                    .queryParam("client_secret", oauthSecret)
                    .queryParam("grant_type", "authorization_code")
                    .queryParam("code", code)
                    .build().toUriString());
            return redirect;
        }

        redirect.setUrl(UriComponentsBuilder
                .fromHttpUrl(commonProperties.getProperty("oauth.url"))
                .path("/authorize.php")
                .queryParam("client_id", commonProperties.getProperty("oauth.client"))
                .queryParam("response_type", "code")
                .build().toUriString());

        model.addAttribute("loginFrom", from == null ? servletContext.getContextPath() : from);

        return redirect;
    }

}
