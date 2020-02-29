package com.example.testnavigatordemo.ui.my;

import android.os.Bundle;
import android.util.Log;
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

    private static final String TAG = "MyFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final TextView textView = new TextView(getActivity());
        textView.setText("HHHHH");
        Log.d(TAG, "onCreateView: ");
        return textView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
