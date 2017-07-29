package cn.wdywae.coolweather.util;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.wdywae.coolweather.db.City;
import cn.wdywae.coolweather.db.County;
import cn.wdywae.coolweather.db.Province;

/**
 * Created by hasee on 2017/7/29.
 */

public class Utility
{
    public static boolean handleProvinceResponse(String response)
    {
        if(!TextUtils.isEmpty(response))
        {
            try
            {
                JSONArray allProvince=new JSONArray(response);
                L.d("JSONArray", String.valueOf(allProvince.length()));
                for (int i = 0; i <allProvince.length() ; i++)
                {
                    JSONObject provinceObject=allProvince.getJSONObject(i);
                    Province p=new Province();
                    p.setName(provinceObject.getString("name"));
                    p.setCode(provinceObject.getInt("id"));
                    p.save();
                }
                return true;
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean handleCityResponse(String response,int provinceId)
    {
        if(!TextUtils.isEmpty(response))
        {
            try
            {
                JSONArray allCity=new JSONArray(response);
                for (int i = 0; i <allCity.length() ; i++)
                {
                    City c=new City();
                    JSONObject cityObject=allCity.getJSONObject(i);
                    c.setName(cityObject.getString("name"));
                    c.setCode(cityObject.getInt("id"));
                    c.setId_province(provinceId);
                    c.save();
                }
                return true;
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean handleCountyResponse(String response,int cityId)
    {
        if(!TextUtils.isEmpty(response))
        {
            L.e("handleCountyResponse",cityId+response);
            try
            {
                JSONArray allCounty=new JSONArray(response);
                for (int i = 0; i <allCounty.length() ; i++)
                {
                    County c=new County();
                    JSONObject countyObject=allCounty.getJSONObject(i);
                    c.setName(countyObject.getString("name"));
                    c.setId_weather(countyObject.getString("weather_id"));//就是因为这里的一个类型错误找了许久
                    c.setId_city(cityId);
                    c.save();
                    L.e("County Name",countyObject.getString("name"));
                }
                return true;
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }
}
