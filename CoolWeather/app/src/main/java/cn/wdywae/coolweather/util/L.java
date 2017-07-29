package cn.wdywae.coolweather.util;

import android.util.Log;

/**
 * Created by hasee on 2017/7/29.
 */

public class L
{
    public static int level=0;
    public static final int LV=1,LD=2,LI=3,LW=4,LE=5;
    public static void e(String tag,String msg)
    {
        if(level<=LE)
            Log.e(tag,msg);
    }
    public static void w(String tag,String msg)
    {
        if(level<=LW)
            Log.w(tag,msg);
    }
    public static void i(String tag,String msg)
    {
        if(level<=LI)
            Log.i(tag,msg);
    }
    public static void d(String tag,String msg)
    {
        if(level<=LD)
            Log.d(tag,msg);
    }
    public static void v(String tag,String msg)
    {
        if(level<=LV)
            Log.v(tag,msg);
    }
}
