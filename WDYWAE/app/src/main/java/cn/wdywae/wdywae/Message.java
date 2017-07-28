package cn.wdywae.wdywae;

/**
 * Created by hasee on 2017/7/25.
 */
public class Message
{
    public static final int TYPE_RECEIVE=0;
    public static final int TYPE_SEND=1;
    private String content;
    private int type;
    public Message(String content,int type)
    {
        this.content=content;
        this.type=type;
    }
    public String getContent()
    {
        return this.content;
    }
    public int getType()
    {
        return this.type;
    }
}
