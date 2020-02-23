package com.example.testnavigatordemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.libannotation.FragmentDestination;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

@FragmentDestination(pageUrl="main/tabs/my")
public class MyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final TextView textView = new TextView(getActivity());
        textView.setText("HHHHH");
        return textView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
