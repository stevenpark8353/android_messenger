package kr.hnu.chosam2.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import kr.hnu.chosam2.navigationtest01.Message;

public class MessageDAO {
    public static final String TAG = "MessageDAO";
    SQLiteDatabase db;

    public MessageDAO() {
        this.db = SQLApp.getDb();
    }

    public void addMessage(Message msg) {
        Log.d("test123", "message add완료!");
        ContentValues values = new ContentValues();
        values.put("sender", msg.getSender());
        values.put("receiver", msg.getReceiver());
        values.put("title", msg.getTitle());
        values.put("contents", msg.getContents());
        values.put("today", msg.getToday());
        db.insert("tb_message", null, values);
    }

    // Messagea 테이블 관련
    public ArrayList<Message> getAllMessage() {
        Log.d("test123", "getAllMessage 생성");
        ArrayList<Message> msgList = new ArrayList<Message>();
        String selectQuery = "SELECT * FROM tb_message";
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Log.d("test123", cursor.getCount() + "");

        while (cursor.moveToNext()) {
            Message msg = new Message();
            int id = cursor.getInt(0);
            Log.d(TAG, "id: " + id);
            msg.setId(id);
            msg.setSender(cursor.getString(1));
            msg.setReceiver(cursor.getString(2));
            msg.setTitle(cursor.getString(3));
            msg.setContents(cursor.getString(4));
            msg.setToday(cursor.getString(5));
            msgList.add(msg);
            Log.d("test123", msg.toString());
        }

        return msgList;
    }
}
