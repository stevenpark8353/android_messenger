package kr.hnu.chosam2.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WordDBHelper extends SQLiteOpenHelper {
    private static WordDBHelper INSTANCE;

    public static WordDBHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new WordDBHelper(context);
        }
        return INSTANCE;
    }

    private WordDBHelper(Context context) {
        super(context, "test3.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("test123", "person table 생성!");
        // person table
        db.execSQL("CREATE TABLE tb_person ( _id text PRIMARY KEY, pw text, name text);");

        // message table 생성
        db.execSQL("CREATE TABLE tb_message (_id INTEGER PRIMARY KEY AUTOINCREMENT, sender text, receiver text, title text, contents text, today date);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS person");
        onCreate(db);
    }
}