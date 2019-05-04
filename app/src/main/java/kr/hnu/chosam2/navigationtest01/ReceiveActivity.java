package kr.hnu.chosam2.navigationtest01;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReceiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        getSupportActionBar().setTitle("메시지 읽기");

        Intent intent = getIntent();
        String writer = intent.getStringExtra("writer");

        Log.d("sex", intent.getStringExtra("writer"));
        Log.d("sex", intent.getStringExtra("title"));
        Log.d("sex", intent.getStringExtra("date"));

        TextView textViewWriter = (TextView) findViewById(R.id.message_sender);
        textViewWriter.setText(writer);
    }
}



