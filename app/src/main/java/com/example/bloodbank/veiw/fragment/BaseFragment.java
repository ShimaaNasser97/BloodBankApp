package com.example.bloodbank.veiw.fragment;

import android.support.v4.app.Fragment;

import com.example.bloodbank.veiw.activity.BaseActivity;

public class BaseFragment extends Fragment {

    public BaseActivity baseActivity;

    @Override
    public void onStart() {
        super.onStart();
        initFragment();
    }

    public void initFragment() {
        baseActivity = (BaseActivity) getActivity();
        baseActivity.basefragment = this;
    }

    public void onBack() {
        baseActivity.superBackPressed();
    }
}
