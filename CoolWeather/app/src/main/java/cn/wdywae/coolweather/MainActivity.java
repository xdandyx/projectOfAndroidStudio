package cn.wdywae.coolweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.wdywae.coolweather.util.L;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        L.level=7;
    }
}
