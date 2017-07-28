package cn.wdywae.wdywae;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

/**
 * Created by hasee on 2017/7/23.
 */
public class BaseActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Toast.makeText(BaseActivity.this,getClass().getSimpleName(),Toast.LENGTH_SHORT).show();
        Log.d("BaseActivity", getClass().getSimpleName());
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.hide();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.navigation,menu);
        return true;
    }
}
