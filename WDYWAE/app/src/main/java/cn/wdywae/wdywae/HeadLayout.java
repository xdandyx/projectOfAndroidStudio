package cn.wdywae.wdywae;

import android.app.Activity;
//import android.app.ActionBar;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by hasee on 2017/7/24.
 */
public class HeadLayout extends LinearLayout
{
    public HeadLayout(Context context,AttributeSet attrs)
    {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_head,this);
//        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
//        if(actionBar!=null)
//        {
//            actionBar.hide();
//        }
        Button previous= (Button) findViewById(R.id.btn_back),
        next= (Button) findViewById(R.id.btn_next);
        previous.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((Activity)getContext()).finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getContext(),"这里创建一个菜单、下一页",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
