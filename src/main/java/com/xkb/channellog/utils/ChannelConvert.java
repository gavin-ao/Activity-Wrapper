package com.xkb.channellog.utils;

import org.apache.commons.lang3.StringUtils;

public class ChannelConvert {
    public  static String nameToValue(String name){
        if(StringUtils.isNotEmpty(name)) {
            switch (name) {
                case "百度":
                    return "bd";
                case "微信群":
                    return "wx_group";
                case "微信个人号":
                    return "wx_personal";
                case "微信公众号":
                    return "wx_public";
                case "微博":
                    return "weibo";
                case "今日头条":
                    return "toutiao";
                case "知乎":
                    return "zhihu";
                case "搜狐":
                    return "sohu";
                case "新浪":
                    return "sina";
                case "百度贴吧":
                    return "bd_tieba";

            }
        }
          return "unknown";
    };

    public static String valueToName(String value){
        switch(value){
            case "bd": return "百度";
            case "wx_group":return "微信群";
            case "wx_personal":return"微信个人号";
            case"wx_public":return "微信公众号";
            case "weibo": return "微博";
            case "toutiao":return "今日头条";
            case "zhihu":return"知乎";
            case"sohu":return "搜狐";
            case "sina":return"新浪";
            case"bd_tieba":return "百度贴吧";

        }
        return "unknown";
    }
}
