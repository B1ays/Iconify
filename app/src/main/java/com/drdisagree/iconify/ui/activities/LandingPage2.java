package com.drdisagree.iconify.ui.activities;

import static com.drdisagree.iconify.utils.SystemUtil.isDarkMode;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.RenderMode;
import com.drdisagree.iconify.R;
import com.drdisagree.iconify.utils.SystemUtil;

public class LandingPage2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!SystemUtil.isDarkMode()) {
            getWindow().getDecorView().setBackgroundColor(Color.parseColor("#EFF7FA"));
            getWindow().setStatusBarColor(Color.parseColor("#EFF7FA"));
            getWindow().setNavigationBarColor(Color.parseColor("#EFF7FA"));
        } else {
            getWindow().getDecorView().setBackgroundColor(Color.parseColor("#111617"));
            getWindow().setStatusBarColor(Color.parseColor("#111617"));
            getWindow().setNavigationBarColor(Color.parseColor("#111617"));
        }

        setContentView(R.layout.activity_landing_page_two);

        if (isDarkMode())
            ((LottieAnimationView) findViewById(R.id.welcome_anim)).setAnimation("Lottie/anim_view_two_night.lottie");

        ((LottieAnimationView) findViewById(R.id.welcome_anim)).setRenderMode(RenderMode.HARDWARE);

        ((Button) findViewById(R.id.btn_next)).setOnClickListener(v -> startActivity(new Intent(LandingPage2.this, LandingPage3.class)));

        ((Button) findViewById(R.id.btn_back)).setOnClickListener(v -> onBackPressed());

        ((Button) findViewById(R.id.btn_skip)).setOnClickListener(v -> startActivity(new Intent(LandingPage2.this, LandingPage3.class)));
    }
}