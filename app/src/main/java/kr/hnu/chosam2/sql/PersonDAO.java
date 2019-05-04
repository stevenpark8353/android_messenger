package kr.hnu.chosam2.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import kr.hnu.chosam2.obj.Person;

public class PersonDAO {
    SQLiteDatabase db;

    public PersonDAO() {
        this.db = SQLApp.getDb();
    }


    public void addPerson(Person person) {
        Log.d("test123", "person add완료!");
        ContentValues values = new ContentValues();
        values.put("_id", person.getId());
        values.put("pw", person.getPassword());
        values.put("name", person.getName());
        db.insert("tb_person", null, values);
    }

    public ArrayList<Person> getAllPerson() {
        Log.d("test123", "getAllPerson생성");
        ArrayList<Person> personList = new ArrayList<Person>();
        String selectQuery = "SELECT * FROM tb_person";
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Log.d("test123", cursor.getCount() + "");

        while (cursor.moveToNext()) {
            Person person = new Person();
            person.setId(cursor.getString(0));
            person.setPassword(cursor.getString(1));
            person.setName(cursor.getString(2));
            personList.add(person);
            Log.d("test123", person.toString());
        }

        return personList;
    }
}
