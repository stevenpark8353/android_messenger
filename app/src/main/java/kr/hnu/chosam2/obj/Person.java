package kr.hnu.chosam2.obj;

import android.util.Log;

import java.io.Serializable;

public class Person implements Serializable {

    private String id;
    private String password;
    private String name;

    public Person() {

    }

    public Person(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
        Log.d("test123", id + ", " + password + ", " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return id + ", " + password + ", " + name;
    }
}
