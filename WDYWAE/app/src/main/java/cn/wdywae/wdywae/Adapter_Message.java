package cn.wdywae.wdywae;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

/**
 * Created by hasee on 2017/7/25.
 */
public class Adapter_Message extends RecyclerView.Adapter<Adapter_Message.ViewHolder>
{
    private List<Message> list_msg;
    static class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout layoutLeft,layoutRight;
        TextView textLeft,textRight;
        public ViewHolder(View v)
        {
            super(v);
            layoutLeft= (LinearLayout) v.findViewById(R.id.chat_left);
            layoutRight= (LinearLayout) v.findViewById(R.id.chat_right);
            textLeft= (TextView) v.findViewById(R.id.text_left);
            textRight= (TextView) v.findViewById(R.id.text_right);
        }
    }
    public Adapter_Message(List<Message> msgList)
    {
        this.list_msg=msgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.chatting_item,parent,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Message msg=list_msg.get(position);
        if(msg.getType()==Message.TYPE_RECEIVE)
        {
            holder.layoutLeft.setVisibility(View.VISIBLE);
            holder.layoutRight.setVisibility(View.GONE);
            holder.textLeft.setText(msg.getContent());
        }
        else if(msg.getType()==Message.TYPE_SEND)
        {
            holder.layoutLeft.setVisibility(View.GONE);
            holder.layoutRight.setVisibility(View.VISIBLE);
            holder.textRight.setText(msg.getContent());
        }
    }
    @Override
    public int getItemCount()
    {
        return list_msg.size();
    }
}

