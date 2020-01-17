package com.example.bloodbank.veiw.activity;

import android.os.Bundle;

import com.example.bloodbank.R;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.veiw.fragment.SplashCyycle.SplashFragment;

public class splashCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SplashFragment splashFragment=new SplashFragment();
        HelperMethod.replace(splashFragment,getSupportFragmentManager(),R.id.splash_cycle_activity_fl_fragment_contaner,null,null);

    }
}
