package com.example.agrishop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Home extends AppCompatActivity {

    private MeowBottomNavigation bottomNavigation;
    private View decorView;

    TextView txtLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener(){
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBar());
            }
        });
        txtLabel = findViewById(R.id.txtLabel);
        bottomNavigation = findViewById(R.id.bottom_nav);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_assignment_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_map_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_account_box_24));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @SuppressLint({"UseCompatLoadingForColorStateLists", "SetTextI18n"})
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
                        fragment = new seller();
                        txtLabel.setVisibility(View.GONE);
                        txtLabel.setText("Seller");
                        break;
                    case 2:
                        fragment = new map();
                        txtLabel.setVisibility(View.VISIBLE);
                        txtLabel.setText("Map");
                        break;
                    case 3:
                        fragment = new profile();
                        txtLabel.setVisibility(View.VISIBLE);
                        txtLabel.setText("Profile");
                }
                loadFragment(fragment);
            }
        });

        if (getIntent().hasExtra("fragment")) {
            String fragment = getIntent().getStringExtra("fragment");
            if (fragment.equals("about")) {
                bottomNavigation.show(3, true);
            } else {
                bottomNavigation.show(1, true);
            }
        } else {
            bottomNavigation.show(1, true); // Default to the first tab if no extras are found
        }


        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), "You Clicked: " + item.getId(), Toast.LENGTH_SHORT);
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), "You Reselected: " + item.getId(), Toast.LENGTH_SHORT);
            }
        });


    }

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        decorView.setSystemUiVisibility(hideSystemBar());
    }

    private int hideSystemBar(){
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

}