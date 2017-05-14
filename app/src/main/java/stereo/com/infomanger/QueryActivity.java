package stereo.com.infomanger;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import stereo.com.infomanger.Adpter.DeviceInfoAdpter;
import stereo.com.infomanger.db.DBManager;

public class QueryActivity extends AppCompatActivity {

    private Button sblb;
    private EditText editText;
    private ListView listView;

    private String lbcode;
    private DeviceInfoAdpter deviceInfoAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        sblb = (Button) findViewById(R.id.sblb);
        editText = (EditText) findViewById(R.id.edit_query);
        listView = (ListView) findViewById(R.id.list);

        sblb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QueryActivity.this, SBTypeActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if (keyEvent.getKeyCode() == 66 && keyEvent.getAction() == KeyEvent.ACTION_UP)
                {
                    Log.i("搜索",editText.getText().toString());
                    if (editText.getText().toString().trim().equals(""))
                    {
                        Toast.makeText(QueryActivity.this,"请输入搜索内容",Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    Cursor cursor = DBManager.getDbManager().getSBInfo(editText.getText().toString());

                    List<Map<String,String>> list =new ArrayList<Map<String, String>>();
                    while (cursor.moveToNext())
                    {
                        Map<String,String>map=new HashMap<String, String>();
                        map.put("name",cursor.getString(2));
                        map.put("sbid",cursor.getString(1));
                        map.put("sblx",DBManager.getDbManager().getSBLXname(cursor.getString(3)));
                        list.add(map);
                    }
                    deviceInfoAdpter=new DeviceInfoAdpter(list,QueryActivity.this);
                    listView.setAdapter(deviceInfoAdpter);
                    InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(editText.getWindowToken(), 0);


                }
                return false;
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 1) {
                String code = data.getStringExtra("code");
                sblb.setText("类别：" + data.getStringExtra("name"));
            }
            return;
        }


    }
}
