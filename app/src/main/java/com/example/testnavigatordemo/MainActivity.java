package com.example.testnavigatordemo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;

import com.example.testnavigatordemo.utils.NavGraphBuilder;
import com.example.testnavigatordemo.view.AppBottomBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppBottomBar navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();



        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //-------------------------*分割线*-------------------------------

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);


        navController = NavHostFragment.findNavController(fragment);
        NavGraphBuilder.build(navController,this,fragment.getId());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        navController.navigate(menuItem.getItemId());

        return !TextUtils.isEmpty(menuItem.getTitle());
    }
}
