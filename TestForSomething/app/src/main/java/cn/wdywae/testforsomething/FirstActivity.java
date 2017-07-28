package cn.wdywae.testforsomething;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class FirstActivity extends BaseActivity  implements View.OnClickListener
{
    private ImageView img;
    @Override
    public void onClick(View v)
    {
        // finish();
        // Toast.makeText(FirstActivity.this, "Click me", Toast.LENGTH_SHORT).show();
        switch(v.getId())
        {
            case R.id.button_next:
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                String data = ((EditText) findViewById(R.id.edit_first)).getText().toString();
                intent.putExtra("hello", data);
                startActivityForResult(intent, 1);
                break;
            case R.id.image_dlx:
                img.setImageResource(R.drawable.compus);
//                        img.setImageResource(R.drawable.dlx);
                break;
            case R.id.button_next_en:
                ProgressBar pbc= (ProgressBar) findViewById(R.id.bar_circle),
                pbh= (ProgressBar) findViewById(R.id.bar_horizontal);
                if(pbc.getVisibility()==View.GONE)
                    pbc.setVisibility(View.VISIBLE);
                else
                    pbc.setVisibility(View.GONE);
                int progress=pbh.getProgress();
                progress+=10;
                pbh.setProgress(progress);
                break;
            default:
                Toast.makeText(FirstActivity.this, "Clicked ?", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button=(Button)findViewById(R.id.button_next),
        button1= (Button) findViewById(R.id.button_next_en);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        img= (ImageView) findViewById(R.id.image_dlx);
        img.setOnClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.item_add:
                Toast.makeText(FirstActivity.this,"Add clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_del:
                Toast.makeText(FirstActivity.this,"Del clicked",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        switch(requestCode)
        {
            case 1:
                if(resultCode==RESULT_OK)
                {
                    String str = data.getStringExtra("hello2");
                    Toast.makeText(FirstActivity.this,str, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(FirstActivity.this,"nothing",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
