package kr.hnu.chosam2.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.hnu.chosam2.navigationtest01.R;

public class ReceiveMessageFragment extends Fragment {
    public static final String TAG = "ReceiveMessageFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_receive_message, container, false);
        return rootView;
    }

}