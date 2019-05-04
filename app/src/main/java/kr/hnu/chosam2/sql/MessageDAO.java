package kr.hnu.chosam2.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import kr.hnu.chosam2.obj.Message;

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
        values.put("today", msg.getDate());
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
            Message message = getMessageFromCursor(cursor);
            msgList.add(message);
        }
        return msgList;
    }

    public List<Message> getMessagesByReceiver(String receiver) {
        System.out.println("Receiver: " + receiver);
        Log.d(TAG, "getMessagesByReceiver: " + receiver);
        List<Message> messages = new ArrayList<Message>();
        String selectQuery = "SELECT * FROM tb_message WHERE receiver='" + receiver + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);

        Message message =  null;
        while(cursor.moveToNext()) {
            message = getMessageFromCursor(cursor);
            messages.add(message);
        }

        return messages;
    }

    public Message getMessageById(int id) {
        String selectQuery = "SELECT * FROM tb_message where _id=" + id;
        Cursor cursor = db.rawQuery(selectQuery, null);

        Message message =  null;
        while(cursor.moveToNext()) {
            message = getMessageFromCursor(cursor);
        }

        return message;
    }

    Message getMessageFromCursor(Cursor cursor) {
        int id = cursor.getInt(0);
        String sender = cursor.getString(1);
        String receiver = cursor.getString(2);
        String title = cursor.getString(3);
        String contents = cursor.getString(4);
        String date = cursor.getString(5);
        Message message = new Message(id, sender, receiver, title, contents, date);

        return message;
    }
}
