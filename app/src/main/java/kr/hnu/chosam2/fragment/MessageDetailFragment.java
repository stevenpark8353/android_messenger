package kr.hnu.chosam2.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kr.hnu.chosam2.navigationtest01.R;
import kr.hnu.chosam2.obj.Message;

public class MessageDetailFragment extends Fragment {
    public static final String TAG = "MessageDetailFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_receive, container, false);

        // Views
        TextView viewTitle = (TextView) rootView.findViewById(R.id.message_title);
        TextView viewWriter = (TextView) rootView.findViewById(R.id.message_sender);
        TextView viewDate = (TextView) rootView.findViewById(R.id.message_date);
        TextView viewContents = (TextView) rootView.findViewById(R.id.message_content);

        Message message = (Message) getArguments().getSerializable("message");

        // Data Received
        String title = message.getTitle();
        String writer = message.getSender();
        String date = message.getDate();
        String contents = message.getContents();

        // Set data with data received.
        viewTitle.setText(title);
        viewWriter.setText(writer);
        viewDate.setText(date);
        viewContents.setText(contents);
        return rootView;
    }

}



