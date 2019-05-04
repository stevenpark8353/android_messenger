package kr.hnu.chosam2.navigationtest01;

import java.io.Serializable;
import java.sql.Date;

public class Message implements Serializable {
    int id;
    String sender;
    String receiver;
    String title;
    String contents;
    String today;

    public Message() {

    }

    public Message(String sender, String receiver, String title, String contents, String today) {
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
        this.contents = contents;
        this.today = today;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
