package kr.hnu.chosam2.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.hnu.chosam2.activity.MainActivity;
import kr.hnu.chosam2.navigationtest01.R;

public class MessageListFragment extends Fragment {
    public static final String NAME = "받은 메시지 함";
    public static final String TAG = "MessageListFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_message_list, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        // Inflate menu list
        mainActivity.inflateListView(rootView);
        return rootView;
    }

}