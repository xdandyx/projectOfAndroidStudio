package cn.wdywae.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by hasee on 2017/7/29.
 */

public class County extends DataSupport
{
    private int id;
    private String name;
    private String id_weather;
    private int id_city;

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

    public String getId_weather()
    {
        return id_weather;
    }

    public void setId_weather(String id_weather)
    {
        this.id_weather = id_weather;
    }

    public int getId_city()
    {
        return id_city;
    }

    public void setId_city(int id_city)
    {
        this.id_city = id_city;
    }
}
