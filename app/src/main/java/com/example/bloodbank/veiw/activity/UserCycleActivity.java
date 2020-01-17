package com.example.bloodbank.veiw.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bloodbank.R;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.veiw.fragment.usearCycle.Login_Fragment;

public class UserCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cycle);

        Login_Fragment login_fragment=new Login_Fragment();
        HelperMethod.replace(login_fragment,getSupportFragmentManager(),R.id.user_cycle_activity_fl_fragment_contaner,null,null);

    }
}
