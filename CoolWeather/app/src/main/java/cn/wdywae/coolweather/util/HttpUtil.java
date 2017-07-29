package cn.wdywae.coolweather.util;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by hasee on 2017/7/29.
 */

public class HttpUtil
{
    public static void sendHttpRequest(String address,okhttp3.Callback callback)
    {
        L.e("step pass ","sendHttpRequest"+address);

        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
