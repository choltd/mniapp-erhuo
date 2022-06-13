package com.erhuo.service.impl;

import com.erhuo.utils.HttpClientUtils;
import com.erhuo.utils.MD5Util;
import com.erhuo.utils.RedisUtil;
import com.erhuo.utils.WXCore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.HashMap;


public class UserServiceImplTest {

    public String codeDecrypt(String appid, String sessionKey, String encryptedData, String iv) {
        System.out.println(WXCore.decrypt(appid, encryptedData, sessionKey, iv));
        return null;
    }

    @Test
    public void keyCode() throws IOException {
//        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxf4d3f365a5b72581&secret=c9df117c88b7abca07e4618e6457bf11&js_code=021ZlT0w38SnDY2M822w3asz4r2ZlT0A&grant_type=authorization_code";
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(HttpClientUtils.doGet(url, new HashMap<>()));
String appid = "wxf4d3f365a5b72581";

        String e = "OCNiw8874kvvM2CDLj0k5N0ok+GxgCcEnws/BDvLfzFlmvzBrsEtyr70mGOBRuemrPcn+Jc6YXEMcu+XtqMdBtcBVSjnfBecvj1HdNGvJf7/mgcPqsk0mmnw1xHAQJHvjTJ2Hi6OdA+LW732Rlchsb9zFRj9aWeD0BWuifz30x2eDAddHRdu0SwlTLmFC8Feufoa0mLeckgSE4QQX5EbS+IEHYuWNd3bS1p7hrwp70BMhKMxPtkLuA4j5e+2ViBTE925IQM2cPPi1sEeIF07qZRrWs8/F+twK8YJOjt17aFie4fK141ytrD+pLbK3EhYOE9xJitG/Km2OYJ6cPlCsH2giF15tetx+251G8g6q8SXhq7+fYvfa/7i+dGZ9oyWypWCI+tCD/AGIFSyvoGxBGnDlURrZfOkoV6d0AuoWqM=";
        String iv = "Q4jdbN69nLexnZq0wkZpIg==";
        String sk= "FnanSiJi5etsC7TuGxfiEQ==";
        System.out.println(codeDecrypt(appid, sk, e, iv));

//        System.out.println("jsonnode===="+jsonNode.toString());
//        String openid = jsonNode.get("openid").textValue();
//        System.out.println("openid==="+openid);
//        String sessionKey = jsonNode.get("session_key").textValue();
//        System.out.println("sessionKey===="+sessionKey);
//        String md5 = MD5Util.getMD5(openid + sessionKey);
//        System.out.println("md5========="+md5);


    }
}