package stereo.com.infomanger.Common;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import stereo.com.infomanger.R;

/**
 * Created by yangxiaoguang on 2017/5/14.
 */

public class Common {

    public static void CopyDb(Context context) {
        InputStream inputStream;
        try {
            inputStream = context.getResources().openRawResource(R.raw.basedb);
            byte[] bytebuff = new byte[inputStream.available()];
            inputStream.read(bytebuff);
            inputStream.close();
            File file = new File(Environment.getExternalStorageDirectory() + "/同步数据/base.db");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytebuff);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
