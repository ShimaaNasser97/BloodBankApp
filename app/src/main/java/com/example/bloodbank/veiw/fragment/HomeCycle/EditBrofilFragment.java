package com.example.bloodbank.veiw.fragment.HomeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.R;
import com.example.bloodbank.veiw.fragment.BaseFragment;


public class EditBrofilFragment extends BaseFragment {

    public EditBrofilFragment() {
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
        return inflater.inflate(R.layout.fragment_emptyfragment, container, false);
    }

    @Override
    public void onBack() {
        baseActivity.finish();
    }
}
