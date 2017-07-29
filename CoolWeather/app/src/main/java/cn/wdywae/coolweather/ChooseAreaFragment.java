package cn.wdywae.coolweather;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.wdywae.coolweather.db.City;
import cn.wdywae.coolweather.db.County;
import cn.wdywae.coolweather.db.Province;
import cn.wdywae.coolweather.util.HttpUtil;
import cn.wdywae.coolweather.util.L;
import cn.wdywae.coolweather.util.Utility;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChooseAreaFragment extends Fragment
{
    public static final int LEVEL_PROVINCE=0;
    public static final int LEVEL_CITY=1;
    public static final int LEVEL_COUNTY=2;
    private ProgressDialog progressDialog;
    private TextView titleText;
    private Button backButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> dataList=new ArrayList<>();
    private List<Province> provinceList;
    private List<City> cityList;
    private List<County> countyList;
    private Province selectedProvince;
    private City selectedCity;
    private County selectedCounty;
    private int currentLevel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.choose_area,container,false);
        titleText= (TextView) view.findViewById(R.id.title_text);
        backButton= (Button) view.findViewById(R.id.back_button);
        listView= (ListView) view.findViewById(R.id.list_view);
        adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        queryProvinces();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(currentLevel==LEVEL_PROVINCE)
                {
                    selectedProvince=provinceList.get(position);
                    queryCities();
                }
                else if(currentLevel==LEVEL_CITY)
                {
                    selectedCity = cityList.get(position);
                    queryCounties();
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(currentLevel==LEVEL_COUNTY)
                    queryCities();
                else if(currentLevel==LEVEL_CITY)
                    queryProvinces();
            }
        });
    }
    private void queryProvinces()
    {
        titleText.setText("中国");
        backButton.setVisibility(View.GONE);
        provinceList= DataSupport.findAll(Province.class);
        if(provinceList.size()>0)
        {
            dataList.clear();
            for (Province p:provinceList )
            {
                dataList.add(p.getName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel=LEVEL_PROVINCE;
        }
        else
        {
            String addr="http://guolin.tech/api/china";
            queryFromServer(addr,"province");
        }
    }
    private void queryCities()
    {
        titleText.setText(selectedProvince.getName());
        backButton.setVisibility(View.VISIBLE);
        cityList= DataSupport.where("id_province=?", String.valueOf(selectedProvince.getId())).find(City.class);
        if(cityList.size()>0)
        {
            dataList.clear();
            for (City c:cityList )
            {
                dataList.add(c.getName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel=LEVEL_CITY;
        }
        else
        {
            int provinceCode=selectedProvince.getCode();
            String addr="http://guolin.tech/api/china/"+provinceCode;
            queryFromServer(addr,"city");
        }
    }
    private void queryCounties()
    {
        titleText.setText(selectedCity.getName());
        backButton.setVisibility(View.VISIBLE);
        countyList= DataSupport.where("id_city=?", String.valueOf(selectedCity.getId())).find(County.class);
        if(countyList.size()>0)
        {
            dataList.clear();
            for (County c:countyList )
            {
                dataList.add(c.getName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel=LEVEL_COUNTY;
        }
        else
        {
            int provinceCode=selectedProvince.getCode();
            int cityCode=selectedCity.getCode();
            String addr="http://guolin.tech/api/china/"+provinceCode+"/"+cityCode;
            queryFromServer(addr,"county");
        }
    }
    private void queryFromServer(String address, final String type)
    {
        L.e("queryFromServer",address+" "+type);
        showProgressDialog();
        L.e("step pass ","showDialog");
        HttpUtil.sendHttpRequest(address, new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                getActivity().runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        closeProgressDialog();
                        Toast.makeText(getContext(),"加载失败",Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {

                String responseText=response.body().string();
                L.e("step pass ","onResponse:"+responseText);

                boolean result=false;
                if("province".equals(type))
                    result= Utility.handleProvinceResponse(responseText);
                else if("city".equals(type))
                    result=Utility.handleCityResponse(responseText,selectedProvince.getId());
                else if("county".equals(type))
                    result=Utility.handleCountyResponse(responseText,selectedCity.getId());
                if(result)
                {
                    getActivity().runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            closeProgressDialog();
                            if("province".equals(type))
                            {
                                queryProvinces();
                            }
                            else if("city".equals(type))
                                queryCities();
                            else if("county".equals(type))
                                queryCounties();
                            else
                                L.e("type",type);
                        }
                    });
                }
            }
        });
    }
    private void showProgressDialog()
    {
        if(progressDialog==null)
        {
            progressDialog=new ProgressDialog(getContext());
            progressDialog.setMessage("正在加载");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }
    private void closeProgressDialog()
    {
        if(progressDialog!=null)
            progressDialog.dismiss();
    }
}
