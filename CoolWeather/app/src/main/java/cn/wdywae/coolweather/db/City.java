package cn.wdywae.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by hasee on 2017/7/29.
 */

public class City extends DataSupport
{
    private int id;
    private String name;
    private int code;
    private int id_province;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public int getId_province()
    {
        return id_province;
    }

    public void setId_province(int id_province)
    {
        this.id_province = id_province;
    }
}
