package com.example.bloodbank.veiw.activity;

import android.support.v7.app.AppCompatActivity;

import com.example.bloodbank.veiw.fragment.BaseFragment;

public class BaseActivity extends AppCompatActivity {
    public BaseFragment basefragment;

    public void superBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onBackPressed() {
        basefragment.onBack();
    }
}
