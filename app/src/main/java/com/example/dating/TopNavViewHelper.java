package com.example.dating;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.dating.Matches.MatchesActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class TopNavViewHelper {
    private static final String TAG = "TopNavViewHelper";



    public static void setupTopNavView(BottomNavigationViewEx tv) {
        Log.d(TAG, "setupTopNavView: setting up navView");
    }

    public static void enableNav(final Context context, BottomNavigationViewEx view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.ic_profile) {
                    Intent i = new Intent(context, SettingsActivity.class);
                    context.startActivity(i);
                } if (item.getItemId() == R.id.ic_matched) {
                    Intent i0 = new Intent(context, MatchesActivity.class);
                    context.startActivity(i0);
                }
                return false;

            }
        });
    }

}
