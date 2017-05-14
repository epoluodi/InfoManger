package stereo.com.infomanger.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 14-6-5.
 */
public class DBManager {

    public static String DBPath;

    public String userdbpath="";
    public SQLiteDatabase db;
    private Context context;
    SQLiteOpenHelper sqLiteOpenHelper;

    private static DBManager dbManager;

    public static DBManager getDbManager() {
        return dbManager;
    }

    public static void setDbManager(DBManager dbManager) {
        DBManager.dbManager = dbManager;
    }

    public DBManager(Context context, String userDbpath) {
        sqLiteOpenHelper = new SQLiteOpenHelper(context,userDbpath,null,1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

            }
        };

        userdbpath= userDbpath;
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        this.context = context;

        db  = sqLiteOpenHelper.getWritableDatabase();
    }




    public void closeDB() {
        if (db !=null)
            db.close();
    }


    public Cursor getSBLB(String lbcode)
    {
        Cursor cursor = db.rawQuery("select * from TX_SB_LBGL where P_LB_CODE = ?",new String[]{lbcode});
        return cursor;
    }



    public Cursor getSBInfo(String key)
    {
        Cursor cursor = db.rawQuery("select * from TX_SBTZ_PUB where SB_NAME like ? or SB_BH like ?",
                new String[]{"%" + key + "%", "%" + key + "%"});
        return cursor;
    }

    public String getSBLXname(String lbcode)
    {
        Cursor cursor = db.rawQuery("select * from TX_SB_LBGL where LB_CODE = ?",new String[]{lbcode});
        cursor.moveToNext();
        String s=cursor.getString(2);
        cursor.close();
        return s;
    }
}
