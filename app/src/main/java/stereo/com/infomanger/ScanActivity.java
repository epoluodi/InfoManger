package stereo.com.infomanger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScanActivity extends AppCompatActivity {

    private Switch sw_scan;
    private TextView txtcode;
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        sw_scan = (Switch)findViewById(R.id.sw_scan);
        txtcode = (TextView)findViewById(R.id.txtcode);
        list=(ListView)findViewById(R.id.list);

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode ==120)
        {
            //扫描案件
            return false;
        }
        return super.onKeyUp(keyCode, event);
    }
}
