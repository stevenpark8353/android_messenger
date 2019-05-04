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
import kr.hnu.chosam2.obj.Person;
import kr.hnu.chosam2.sql.PersonDAO;

public class EditFragment extends Fragment {
    public static final String TAG = "EditFragment";
    EditText editPw;
    String userId;
    PersonDAO personDAO = new PersonDAO();
    Person me;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_edit, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();
        userId = mainActivity.getUserId();
        me  = personDAO.getPersonById(userId);

        Button btnEditCheck = (Button) rootView.findViewById(R.id.btn_editCheck);
        editPw = (EditText) rootView.findViewById(R.id.edit_pw);
        TextView viewName = (TextView) rootView.findViewById(R.id.view_name);
        TextView viewId = (TextView) rootView.findViewById(R.id.view_id);

        if (me != null) {
            viewName.setText(me.getName());
            viewId.setText(me.getId());
        }


        btnEditCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = editPw.getText().toString();
                if (pwd.length() > 0) {
                    personDAO.changePwd(userId, pwd);
                    getFragmentManager().popBackStack();
                }
            }
        });

        return rootView;
    }
}
