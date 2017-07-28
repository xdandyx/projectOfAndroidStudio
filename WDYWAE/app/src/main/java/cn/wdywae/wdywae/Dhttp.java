package cn.wdywae.wdywae;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hasee on 2017/7/25.
 */
public class Dhttp
{
    private int timeout_connect,timeout_read;
    HttpURLConnection httpURLConnection=null;
    BufferedReader bufferedReader=null;
    public Dhttp(int timeout_connect,int timeout_read)
    {
        this.timeout_connect=timeout_connect;
        this.timeout_read=timeout_read;
    }
    public void connect(final String action, final String method,
                        final String data,final DhttpCallback callback)
    {//此处开启了新的子线程，正常情况下，主线程不会因此阻塞，并且有可能先于子线程结束，为了使得服务器的响应得以处理，此处采用了回调机制
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                StringBuilder stringBuilder=new StringBuilder();
                try
                {
                    URL url=new URL(action);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod(method);
                    httpURLConnection.setConnectTimeout(timeout_connect);
                    httpURLConnection.setReadTimeout(timeout_read);
                    if(data!=null)
                    {
                        DataOutputStream dataOutputStream=new DataOutputStream(httpURLConnection.getOutputStream());
                        dataOutputStream.writeBytes(data);
                    }
                    InputStream inputStream=httpURLConnection.getInputStream();
                    bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                    String line;

                    while ((line=bufferedReader.readLine())!=null)
                    {
                        stringBuilder.append(line);
                    }
                    if(callback!=null)
                        callback.onFinish(stringBuilder.toString());
                }
                catch (IOException e)
                {
                    if(callback!=null)
                        callback.onError(e);
                }
            }
        }).start();
    }
    public void close()
    {
        if (bufferedReader != null)
        {
            try
            {
                bufferedReader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if(httpURLConnection!=null)
            httpURLConnection.disconnect();
    }
}
