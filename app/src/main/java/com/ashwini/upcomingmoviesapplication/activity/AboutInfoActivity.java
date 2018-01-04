package com.ashwini.upcomingmoviesapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.ashwini.upcomingmoviesapplication.R;
import com.ashwini.upcomingmoviesapplication.utility.ConnectionManager;

public class AboutInfoActivity extends AppCompatActivity {

    private TextView textViewToolbar,tvCreatorInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_info);

        initialisation();
    }

    private void initialisation() {

        textViewToolbar = findViewById(R.id.toolbar_title);
        textViewToolbar.setText(R.string.information_title);

        tvCreatorInfo = findViewById(R.id.tvCreatorInfo);
        tvCreatorInfo.startAnimation(AnimationUtils.loadAnimation(AboutInfoActivity.this,R.anim.blink_anim));
    }
}
