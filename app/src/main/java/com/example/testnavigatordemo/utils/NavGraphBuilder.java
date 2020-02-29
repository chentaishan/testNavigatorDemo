package com.example.testnavigatordemo.utils;

import android.content.ComponentName;

import com.example.testnavigatordemo.bean.Destination;
import com.example.testnavigatordemo.navigator.FixFragmentNavigator;

import java.util.HashMap;

import androidx.fragment.app.FragmentActivity;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.FragmentNavigator;

public class NavGraphBuilder {


    public static void build(NavController navController,  FragmentActivity activity, int resid){

        final NavigatorProvider navigatorProvider = navController.getNavigatorProvider();

//        final FragmentNavigator fragmentNavigator = navigatorProvider.getNavigator(FragmentNavigator.class);

        FixFragmentNavigator fragmentNavigator = new FixFragmentNavigator(activity, activity.getSupportFragmentManager(),resid);
        final ActivityNavigator activityNavigator = navigatorProvider.getNavigator(ActivityNavigator.class);

        navigatorProvider.addNavigator(fragmentNavigator);

        final NavGraph navGraph = new NavGraph(new NavGraphNavigator(navigatorProvider));

        final HashMap<String, Destination> destConfig = AppConfig.getDestConfig();

        for (Destination value :destConfig.values() ) {

            if (value.isFragment){

                final FragmentNavigator.Destination destination = fragmentNavigator.createDestination();
                destination.setClassName(value.className);
                destination.setId(value.id);
                destination.addDeepLink(value.pageUrl);

                navGraph.addDestinations(destination);
            }else{

                final ActivityNavigator.Destination destination = activityNavigator.createDestination();
                destination.setComponentName(new ComponentName(AppGlobals.getApplication().getPackageName(),value.className));
                destination.setId(value.id);
                destination.addDeepLink(value.pageUrl);

                navGraph.addDestinations(destination);
            }
            if (value.asStarter){

                navGraph.setStartDestination(value.id);
            }

        }

        navController.setGraph(navGraph);


    }
}
