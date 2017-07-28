package cn.wdywae.wdywae;

/**
 * Created by hasee on 2017/7/25.
 */
public  interface DhttpCallback
{
    void onFinish(String result);
    void onError(Exception e);
}
