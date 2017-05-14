package stereo.com.infomanger;

import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stereo.com.infomanger.db.DBManager;

public class SBTypeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<RelativeLayout> relativeLayoutList;

    private String s1, s2, s3;
    private List<Map<String, String>> mapList11, mapList12, mapList13;

    private int index1, index2, index3;
    private ListView listView1, listView2, listView3;
    private SimpleAdapter simpleAdapter1, simpleAdapter2, simpleAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbtype);

        viewPager = (ViewPager) findViewById(R.id.viewpage);
        relativeLayoutList = new ArrayList<>();

        s1 = "1";
        mapList11 = new ArrayList<>();
        mapList12 = new ArrayList<>();
        mapList13 = new ArrayList<>();

        index1 = 0;
        index2 = 0;
        index3 = 0;
        loadDb1();
        loadDb2();
        loadDb3();
        initViewPaerData();
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==1)
                {

//                    listView2.setOnItemSelectedListener(onItemSelectedListener2);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        listView1.setAdapter(simpleAdapter1);
        listView2.setAdapter(simpleAdapter2);
        listView3.setAdapter(simpleAdapter3);

//
//        listView1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                index1 = (int)l;
//                loadDb2();
//                listView2.setAdapter(simpleAdapter2);
//                listView2.setOnItemSelectedListener(null);
//                viewPager.setCurrentItem(1);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index1 = (int)l;
                loadDb2();
                listView2.setAdapter(simpleAdapter2);
                viewPager.setCurrentItem(1);

            }
        });


        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index2 = (int)l;
                loadDb3();
                listView3.setAdapter(simpleAdapter3);
                viewPager.setCurrentItem(2);
            }
        });

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index3 = (int)l;
                Intent intent=new Intent();
                intent.putExtra("code",mapList13.get(index3).get("code"));
                intent.putExtra("name",mapList13.get(index3).get("name"));
                setResult(1,intent);
                finish();
            }
        });

    }

    AdapterView.OnItemSelectedListener onItemSelectedListener2=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            index2 = (int)l;
            loadDb3();
            listView3.setAdapter(simpleAdapter3);
            viewPager.setCurrentItem(2);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private void loadDb1() {
        Cursor cursor = DBManager.getDbManager().getSBLB("1");
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<>();
            map.put("name", cursor.getString(2));
            map.put("code", cursor.getString(1));
            mapList11.add(map);
        }

        cursor.close();
        simpleAdapter1 = new SimpleAdapter(this, mapList11,
                android.R.layout.simple_selectable_list_item, new String[]{"name"},
                new int[]{android.R.id.text1});


    }

    private void loadDb2() {
        mapList12.clear();
        Cursor cursor = DBManager.getDbManager().getSBLB(mapList11.get(index1).get("code"));
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<>();
            map.put("name", cursor.getString(2));
            map.put("code", cursor.getString(1));
            mapList12.add(map);
        }
        cursor.close();
        simpleAdapter2 = new SimpleAdapter(this, mapList12,
                android.R.layout.simple_selectable_list_item, new String[]{"name"},
                new int[]{android.R.id.text1});
    }

    private void loadDb3() {
        mapList13.clear();
        Cursor cursor = DBManager.getDbManager().getSBLB(mapList12.get(index2).get("code"));
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<>();
            map.put("name", cursor.getString(2));
            map.put("code", cursor.getString(1));
            mapList13.add(map);
        }
        cursor.close();
        simpleAdapter3 = new SimpleAdapter(this, mapList13,
                android.R.layout.simple_selectable_list_item, new String[]{"name"},
                new int[]{android.R.id.text1});
    }

    private void initViewPaerData() {

        RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        listView1 = new ListView(this);
        listView1.setLayoutParams(layoutParams);
        relativeLayout.addView(listView1);
        relativeLayoutList.add(relativeLayout);

        relativeLayout = new RelativeLayout(this);
        layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        listView2 = new ListView(this);
        listView2.setLayoutParams(layoutParams);
        relativeLayout.addView(listView2);
        relativeLayoutList.add(relativeLayout);

        relativeLayout = new RelativeLayout(this);
        layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        listView3 = new ListView(this);
        listView3.setLayoutParams(layoutParams);
        relativeLayout.addView(listView3);
        relativeLayoutList.add(relativeLayout);
    }


    PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {


            return relativeLayoutList.size();
        }


        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }


        @Override
        public int getItemPosition(Object object) {

            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return "";
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(relativeLayoutList.get(position));
            return relativeLayoutList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    };

}
