package kr.hnu.chosam2.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kr.hnu.chosam2.activity.MainActivity;
import kr.hnu.chosam2.navigationtest01.R;
import kr.hnu.chosam2.obj.Message;
import kr.hnu.chosam2.sql.MessageDAO;
import kr.hnu.chosam2.util.Utils;

public class SendMessageFragment extends Fragment {
    public static final String TAG = "SendMessageFragment";
    MessageDAO messageDAO = new MessageDAO();
    TextView viewTitle;
    TextView viewReceiver;
    EditText viewContents;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_send_message, container, false);

        Button btnSend = (Button) rootView.findViewById(R.id.btnSend);
        Button btnCancel = (Button) rootView.findViewById(R.id.btnCancel);
        viewTitle = (TextView) rootView.findViewById(R.id.input_title);
        viewReceiver = (TextView) rootView.findViewById(R.id.input_receiver);
        viewContents = (EditText) rootView.findViewById(R.id.input_content);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                String sender = mainActivity.getUserId();
                String title = viewTitle.getText().toString();
                String receiver = viewReceiver.getText().toString();
                String contents = viewContents.getText().toString();
                String date = Utils.getDate();
                Message message = new Message(sender, receiver, title, contents, date);
                messageDAO.addMessage(message);
                mainActivity.switchFragment(R.id.nav_message);
                getFragmentManager().popBackStack();
            }
        });
        return rootView;
    }
}
