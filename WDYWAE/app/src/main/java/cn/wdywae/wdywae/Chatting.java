package cn.wdywae.wdywae;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Chatting extends BaseActivity
{
    private Adapter_Message msgAdapter;
    private List<Message> msgList=new ArrayList<>();
    private Button send;
    private EditText editText;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        init();
        send= (Button) findViewById(R.id.chat_send);
        editText= (EditText) findViewById(R.id.chat_edit);
        send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String tmp=editText.getText().toString();
                if(tmp.length()>0)
                {
                //    Toast.makeText(Chatting.this,msgList.size(),Toast.LENGTH_SHORT).show();
                    msgList.add(new Message(tmp,Message.TYPE_SEND));
                    //   msgAdapter.notifyItemInserted(msgList.size()-1);
                    msgAdapter.notifyDataSetChanged();//刷新列表
                    recyclerView.scrollToPosition(msgList.size()-1);
                    editText.setText("");
                }
                else
                    Toast.makeText(Chatting.this,"发送消息不能为空",Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView= (RecyclerView) findViewById(R.id.chat_box);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        msgAdapter=new Adapter_Message(msgList);
        recyclerView.setAdapter(msgAdapter);
    }
    public void init()
    {
        msgList.add(new Message("Nice to meet you",Message.TYPE_RECEIVE));
        msgList.add(new Message("Nice to meet you too",Message.TYPE_SEND));
        msgList.add(new Message("Bye",Message.TYPE_RECEIVE));
    }
}
