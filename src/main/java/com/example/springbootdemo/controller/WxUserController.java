package com.example.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springbootdemo.entity.WxUser;
import com.example.springbootdemo.service.WxUserService;
import com.example.springbootdemo.util.AesUtil;
import com.example.springbootdemo.util.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/wxlogin")
public class WxUserController {

    @Autowired
    WxUserService wxUserService;

    @RequestMapping("/getcode")
    @ResponseBody
    public Map getCode(String code , String encryptedData ,String iv){
        Map<String,Object> map = new HashMap<>();
        String APPID = "";   //appid
        String SECRET = "";     //
        String JSCODE = code;
        String grant_type ="authorization_code";
        String params = "appid=" + APPID + "&secret=" + SECRET + "&js_code=" + JSCODE + "&grant_type=" + grant_type;
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        JSONObject json = JSONObject.parseObject(sr);
        String session_key = json.get("session_key").toString();
        String openid = json.get("openid").toString();
        System.out.println("openid:" + openid);
        System.out.println("session_key"+session_key);

        try {
            String result = AesUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
//            String result = MyBase64.getUserInfo(encryptedData, session_key, iv);
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");
                JSONObject userInfoJSON = JSONObject.parseObject(result);
                Map<String,Object> userInfo = new HashMap<String,Object>();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                userInfo.put("session_key",session_key);
                map.put("userInfo", userInfo);
                System.out.println("map2:" + map);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", 0);
        map.put("msg", "解密失败");
        System.out.println("map3:" + map);
        return map;
    }


    @RequestMapping(value = "/useinfo",method = RequestMethod.POST)
    @ResponseBody
    public int insertUserInfo(WxUser wxUser){
        System.out.println(wxUser);
        System.out.println(wxUser.getOpenId());
        int has = wxUserService.selectByOpenId(wxUser.getOpenId());
        if (has==0){
            return wxUserService.insertSelective(wxUser);
        }else {
            return 0;
        }
    }


}
