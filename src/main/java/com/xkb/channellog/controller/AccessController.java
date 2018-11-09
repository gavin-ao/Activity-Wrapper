package com.xkb.channellog.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xkb.channellog.Entity.AccessEntity;
import com.xkb.channellog.utils.ChannelConvert;
import com.xkb.channellog.utils.IpUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@CrossOrigin
public class AccessController {
   @Autowired
   AccessEntity accessEntity;
   @Autowired
   JedisPool jedisPool;

   /**
    * 活动入口
    * @param request
    * @param channel
    * @return
    */
   @RequestMapping("/gate")
   public String redirect(HttpServletRequest request,
                          @RequestParam(name="channel",required=false,defaultValue="unknown")String channel){

      String ip = IpUtil.getIpAddr(request);
      log(ip,channel);
      return "redirect:https://wx3d3e228b169fb31f.h5.xiaoe-tech.com/coupon/get/cou_5bda7a189b55f-IlaVlH";
   }

   /**
    * 将请求信息存入到redis中
    * @param ip
    * @param channel
    */
   private void log(String ip, String channel) {
      Jedis jedis = null;
      Date accessTime = new Date();
      try {
         jedis = jedisPool.getResource();
         SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         String accessTimeStr = sf.format(accessTime);
         String channelValue = ChannelConvert.nameToValue(channel);
         StringBuilder key = new StringBuilder();
         key.append("accessList").append("-").append(channelValue);
         String keyStr = key.toString();
         StringBuilder access = new StringBuilder();
         access.append("ip=").append(ip).append(";").
                 append("channel=").append(channelValue).append(";").
                 append("accessTime=").append(accessTimeStr).append(";");
         jedis.lpush(keyStr, access.toString());
      }finally {
        jedis.close();
      }

   }

   /***
    * 统计分析渠道信息
    * @return
    */
   @RequestMapping("/channelanalysis")
   @ResponseBody
   public JSONObject channelanalysis(){
      Jedis jedis = null;
      try{
       jedis = jedisPool.getResource();
      Set<String> keySet =jedis.keys("accessList-*");
      JSONObject jsonObject = new JSONObject();
      for(String key:keySet){
         String channelValue = StringUtils.substringAfter(key,"-");
         String channelName = ChannelConvert.valueToName(channelValue);
        List<String> accesslist = jedis.lrange(key,0,-1);
         jsonObject.put(channelName, accesslist.size());
      }
         return jsonObject;
   }finally {
      jedis.close();
   }

   }

   @RequestMapping("/channeldetail")
   @ResponseBody
   public JSONArray detail(){
      Jedis jedis = null;
      try {
          jedis = jedisPool.getResource();
         Set<String> keySet = jedis.keys("accessList-*");
         JSONObject jsonObject = new JSONObject();
         JSONArray jsonArray = new JSONArray();
         for (String key : keySet) {
            List<String> accesslist = jedis.lrange(key, 0, -1);
            for (String value : accesslist) {
               jsonObject.put(key, value);
               jsonArray.add(jsonObject);

            }
            ;
         }
         return jsonArray;
      }finally {
         jedis.close();
      }
   }

}
