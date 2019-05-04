package kr.hnu.chosam2.navigationtest01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import kr.hnu.chosam2.sql.MessageDAO;
import kr.hnu.chosam2.utils.Utils;

public class SendMessageFragment extends Fragment {
    public static final String TAG = "SendMessageFragment";
    MessageDAO messageDAO = new MessageDAO();
    TextView viewTitle;
    TextView viewReceiver;
    TextView viewContents;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.send_message, container, false);

        Button btnSend = (Button) rootView.findViewById(R.id.btnSend);
//        TextView viewSender = (TextView) rootView.findViewById(R.id.);
        viewTitle = (TextView) rootView.findViewById(R.id.input_title);
        viewReceiver = (TextView) rootView.findViewById(R.id.input_receiver);
        viewContents = (TextView) rootView.findViewById(R.id.input_content);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                String sender = mainActivity.userId;
                String title = viewTitle.getText().toString();
                String receiver = viewReceiver.getText().toString();
                String contents = viewContents.getText().toString();
                String date = Utils.getDate();
                Message message = new Message(sender, receiver, title, contents, date);
                messageDAO.addMessage(message);
                mainActivity.inflateListView();
                getFragmentManager().popBackStack();
            }
        });
        return rootView;
    }
}
