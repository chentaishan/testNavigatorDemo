package com.example.testnavigatordemo.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.libannotation.FragmentDestination;
import com.example.libnetwork.ApiResponse;
import com.example.libnetwork.GetRequest;
import com.example.libnetwork.JsonCallBack;
import com.example.testnavigatordemo.R;

import org.json.JSONObject;

@FragmentDestination(pageUrl="main/tabs/home",asStarter = true)
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final GetRequest<JSONObject> jsonObjectGetRequest = new GetRequest<>("http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=2");

        jsonObjectGetRequest.execute(new JsonCallBack<JSONObject>() {
            @Override
            public void success(ApiResponse<JSONObject> response) {
                super.success(response);

                Log.d(TAG, "success: "+response.body);
            }

            @Override
            public void error(ApiResponse<JSONObject> response) {
                super.error(response);

                Log.d(TAG, "error: "+response);
            }

            @Override
            public void cache(ApiResponse<JSONObject> response) {
                super.cache(response);

                Log.d(TAG, "cache: ");
            }
        });


        Log.d(TAG, "onCreateView: ");
        return root;
    }

    private static final String TAG = "HomeFragment";
}