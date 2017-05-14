package stereo.com.infomanger.Adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import stereo.com.infomanger.R;

/**
 * Created by yangxiaoguang on 2017/5/14.
 */

public class DeviceInfoAdpter extends BaseAdapter {


    public List<Map<String,String>> mapList;
    private Context context;

    private TextView sbname,sbid,sblx;

    public DeviceInfoAdpter(List<Map<String,String>> list, Context context)
    {
        mapList = list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return mapList.size();
    }

    @Override
    public Object getItem(int i) {
        return mapList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.list_deviceinfo,null);
        sbname =(TextView)view.findViewById(R.id.sbname);
        sbid =(TextView)view.findViewById(R.id.sbid);
        sblx =(TextView)view.findViewById(R.id.sblx);

        Map<String,String>map=mapList.get(i);
        sbname.setText("设备名称:" + map.get("name"));
        sbid.setText("设备编号:" + map.get("sbid"));
        sblx.setText("设备类型:" + map.get("sblx"));

        return view;
    }
}
