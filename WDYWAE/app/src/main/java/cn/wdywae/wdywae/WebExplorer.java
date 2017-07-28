package cn.wdywae.wdywae;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebExplorer extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_explorer);
        HttpURLConnect();
       // WebView();
    }
    public void WebView()
    {
        WebView webView= (WebView) findViewById(R.id.web_box);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());//目的是如果需要从一个网页跳转至另一个网页仍然在WebView中打开，而不是打开系统浏览器
        webView.loadUrl("http://192.168.137.1/");
    }
    public void HttpURLConnect()
    {
        Dhttp dhttp=new Dhttp(8000,8000);
        String result;
        try
        {
            dhttp.connect("http://123.207.31.35/scripts/DsqlForAndroid.php",
                    "POST", "operation=login&db=user&tab=personalInfo&user=111111&password=111111", new DhttpCallback()
                    {
                        @Override
                        public void onFinish(final String result)
                        {
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    TextView textView = (TextView) findViewById(R.id.text_display);
                                    textView.setText(result);
                                }
                            });
                        }
                        @Override
                        public void onError(final Exception e)
                        {
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    new Dalert(WebExplorer.this,"请检查网络",e.toString(),false, new DalertCallback()
                                    {
                                        @Override
                                        public void isOk(DialogInterface dialog, int which)
                                        {
                                             Toast.makeText(WebExplorer.this,"ok",Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void isCancel(DialogInterface dialog, int which)
                                        {
                                             Toast.makeText(WebExplorer.this,"cancelled",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });

                        }
                    });
        }
        finally
        {
            dhttp.close();
        }
    }

}
