package kr.hnu.chosam2.sql;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SQLApp extends Application {
    public static final String TAG = "SQLApp";
    private static SQLiteDatabase db;

    public static SQLiteDatabase getDb() {
        return db;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = WordDBHelper.getInstance(getApplicationContext()).getWritableDatabase();
        Log.d(TAG, "db has created.");
    }
}
