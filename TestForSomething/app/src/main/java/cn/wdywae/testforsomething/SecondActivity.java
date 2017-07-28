package cn.wdywae.testforsomething;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends BaseActivity implements View.OnClickListener
{
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            //startActivity(new Intent(SecondActivity.this,FirstActivity.class));
//                Intent intent=new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("http://www.wdywae.cn"));
//                startActivity(intent);
            case R.id.button_browser:
                Intent intent2=new Intent();
                intent2.putExtra("hello2", "this is return from Second;");
                setResult(RESULT_OK, intent2);
                finish();
                break;
            case R.id.button_tel:
                //startActivity(new Intent(SecondActivity.this,FirstActivity.class));
                // Log.e("ddd",Intent.ACTION_DIAL);
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10010"));
                startActivity(intent);
                break;
            case R.id.button_alert:
                AlertDialog.Builder dialog=new AlertDialog.Builder(SecondActivity.this);
                dialog.setTitle("Alert");
                dialog.setMessage("hello,world");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(SecondActivity.this,"Ok!",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(SecondActivity.this,"Cancle!",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                break;
            case R.id.button_progress:
                ProgressDialog pd=new ProgressDialog(SecondActivity.this);
                pd.setTitle("Progress");
                pd.setMessage("world,hello");
                pd.setCancelable(true);//如果设置为false，则一定要在加载完成后使用dismiss来关闭
                pd.show();
//                pd.dismiss();
                break;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button browser= (Button) findViewById(R.id.button_browser),
        tel= (Button) findViewById(R.id.button_tel),
        ad= (Button) findViewById(R.id.button_alert),
        pd= (Button) findViewById(R.id.button_progress);
        String data=getIntent().getStringExtra("hello");
        Toast.makeText(SecondActivity.this,data,Toast.LENGTH_SHORT).show();
        browser.setOnClickListener(this);
        tel.setOnClickListener(this);
        ad.setOnClickListener(this);
        pd.setOnClickListener(this);
    }

    @Override
    public void onBackPressed()
    {
        //super.onBackPressed();
        Intent intent2=new Intent();
        intent2.putExtra("hello2","this is return from Second;");
        setResult(RESULT_OK, intent2);
        finish();
    }
}
