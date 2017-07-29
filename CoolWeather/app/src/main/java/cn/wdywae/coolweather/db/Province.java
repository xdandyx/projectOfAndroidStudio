package cn.wdywae.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by hasee on 2017/7/29.
 */

public class Province extends DataSupport
{
    private int id;
    private String name;
    private int code;
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
