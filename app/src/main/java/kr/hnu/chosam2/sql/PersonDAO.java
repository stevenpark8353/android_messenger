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

    public Person getPersonById(String id) {
        Log.d("test123", "getAllPerson생성");
        String selectQuery = "SELECT * FROM tb_person WHERE _id='" + id + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);

        Person person = null;
        while (cursor.moveToNext()) {
            String _id = cursor.getString(0);
            String _pwd = cursor.getString(1);
            String _name = cursor.getString(2);
            person = new Person(_id, _pwd, _name);
        }

        return person;
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

    public boolean changePwd(String userId, String pwd) {
        String updateQuery= "UPDATE tb_person SET pw='" + pwd + "' WHERE _id='" + userId + "'";
        db.execSQL(updateQuery);
//        while (cursor.moveToNext()) {
//            Person person = new Person();
//            person.setId(cursor.getString(0));
//            person.setPassword(cursor.getString(1));
//            person.setName(cursor.getString(2));
//            personList.add(person);
//            Log.d("test123", person.toString());
//        }

        return true;
    }
}
