package cn.wdywae.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hasee on 2017/7/29.
 */

public class Basic
{
    @SerializedName("city")//这是GSON提供的方法用以将JSON的字段和JAVA的字段映射起来
    public String cityName;
    @SerializedName("id")
    public String weatherId;
    public Update update;
    public class Update
    {
        @SerializedName("loc")
        public String updateTime;
    }
}
