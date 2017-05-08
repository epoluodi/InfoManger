package stereo.com.infomanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    private LinearLayout menu1,menu2,menu3,menu4,menu5,
            menu6,menu7,menu8,menu9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu1 = (LinearLayout)findViewById(R.id.menu1);
        menu2 = (LinearLayout)findViewById(R.id.menu2);
        menu3 = (LinearLayout)findViewById(R.id.menu3);
        menu4 = (LinearLayout)findViewById(R.id.menu4);
        menu5 = (LinearLayout)findViewById(R.id.menu5);
        menu6 = (LinearLayout)findViewById(R.id.menu6);
        menu7 = (LinearLayout)findViewById(R.id.menu7);
        menu8 = (LinearLayout)findViewById(R.id.menu8);
        menu9 = (LinearLayout)findViewById(R.id.menu9);
        menu1.setOnClickListener(onClickListenermenu);
        menu2.setOnClickListener(onClickListenermenu);
        menu3.setOnClickListener(onClickListenermenu);
        menu4.setOnClickListener(onClickListenermenu);
        menu5.setOnClickListener(onClickListenermenu);
        menu6.setOnClickListener(onClickListenermenu);
        menu7.setOnClickListener(onClickListenermenu);
        menu8.setOnClickListener(onClickListenermenu);
        menu9.setOnClickListener(onClickListenermenu);



    }

    View.OnClickListener onClickListenermenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent;
            switch (view.getId())
            {
                case R.id.menu1://扫一扫
                    intent=new Intent(MainActivity.this,ScanActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //退出程序控制
        if (keyCode == 4) {
            Toast.makeText(this, "请长按退出", LENGTH_SHORT).show();
            return false;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            finish();
        }
        return super.onKeyLongPress(keyCode, event);

    }
}
