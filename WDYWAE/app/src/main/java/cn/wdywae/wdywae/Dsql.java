package cn.wdywae.wdywae;
import java.sql.*;
/**
 * Created by hasee on 2017/7/24.
 */
public class Dsql
{
    private Connection ct=null;
    private Statement st=null;
    private void loadDriver()
    {
        try
        {
            String DBDriver="app.libs.com.mysql.jdbc.Driver";//"org.gjt.mm.mysql.Driver";//"com.mysql.jdbc.Driver"
            Class.forName(DBDriver) ;
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public void connect()
    {
        try
        {
            this.ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","neversay");
            this.st=this.ct.createStatement();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void close()
    {
        try
        {
            this.st.close();
            this.ct.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void execute(String sql)
    {
        try
        {
            ResultSet res=this.st.executeQuery(sql);
            ResultSetMetaData rows=res.getMetaData();
            int cols=rows.getColumnCount();
            while(res.next())
            {
                for (int i=1;i<=cols;i++)
                {
                    System.out.println(res.getObject(i));
                }
            }
            res.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        Dsql obj=new Dsql();
        String sql="show databases";
        obj.loadDriver();
        obj.connect();
        obj.execute(sql);
        obj.close();
    }
}
