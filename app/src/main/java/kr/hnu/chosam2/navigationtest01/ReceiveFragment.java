package kr.hnu.chosam2.navigationtest01;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReceiveFragment extends Fragment {
    public static final String TAG = "ReceiveFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_receive, container, false);

        // Views
        TextView viewTitle = (TextView) rootView.findViewById(R.id.message_title);
        TextView viewWriter = (TextView) rootView.findViewById(R.id.message_sender);
        TextView viewDate = (TextView) rootView.findViewById(R.id.message_date);

        // Data Received
        String title = getArguments().getString("title");
        String writer = getArguments().getString("writer");
        String date = getArguments().getString("date");

        // Set data with data received.
        viewTitle.setText(title);
        viewWriter.setText(writer);
        viewDate.setText(date);

        return rootView;
    }

}



