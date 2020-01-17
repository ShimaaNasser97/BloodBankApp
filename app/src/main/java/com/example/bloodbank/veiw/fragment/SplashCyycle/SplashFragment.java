package com.example.bloodbank.veiw.fragment.SplashCyycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.R;
import com.example.bloodbank.data.local.SharedPreferencesManger;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.veiw.activity.HomeCycleActivity;
import com.example.bloodbank.veiw.fragment.BaseFragment;

import static com.example.bloodbank.data.local.SharedPreferencesManger.REMEMBER;


public class SplashFragment extends BaseFragment {

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        initFragment();
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPreferencesManger.LoadBoolean(getActivity(), REMEMBER) &&
                        SharedPreferencesManger.loadUserData(getActivity()) != null) {

                    Intent intent = new Intent(getActivity(), HomeCycleActivity.class);
                    getActivity().startActivity(intent);
                    getActivity().finish();
                } else {
                    SliderFragment sliderFragment = new SliderFragment();
                    HelperMethod.replace(sliderFragment, getActivity().getSupportFragmentManager(), R.id.splash_cycle_activity_fl_fragment_contaner, null, null);
                }

            }
        }, 2000);

        return view;
    }

    @Override
    public void onBack() {
        baseActivity.finish();
    }
}
