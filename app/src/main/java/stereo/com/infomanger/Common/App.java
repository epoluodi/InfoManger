package stereo.com.infomanger.Common;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import stereo.com.infomanger.db.DBManager;

/**
 * Created by yangxiaoguang on 2017/5/14.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("启动", "开始运行");

        //检查目录 创建数据
        File file = new File(Environment.getExternalStorageDirectory() + "/同步数据");
        if (!file.exists()) {
            file.mkdir();
            Common.CopyDb(this);
        }


        //连接数据
        DBManager dbManager=new DBManager(this,Environment.getExternalStorageDirectory() + "/同步数据/base.db");
        DBManager.setDbManager(dbManager);

    }
}
