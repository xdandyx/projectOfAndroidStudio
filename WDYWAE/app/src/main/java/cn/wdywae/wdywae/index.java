package cn.wdywae.wdywae;

//import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class index extends BaseActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Button signin= (Button) findViewById(R.id.btn_signin),
                signup= (Button) findViewById(R.id.btn_signup);
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);
    }
    public void connectMysql()
    {
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_signin:
                Intent intent=new Intent(index.this,WebExplorer.class);
                startActivity(intent);
                break;
        }
    }
}
