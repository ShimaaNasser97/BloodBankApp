package com.example.bloodbank.veiw.fragment.SplashCyycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bloodbank.R;
import com.example.bloodbank.veiw.activity.UserCycleActivity;
import com.example.bloodbank.veiw.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SliderFragment extends BaseFragment {

    @BindView(R.id.Slider_Fragment_Btn_Skap)
    Button SliderFragmentBtnSkap;
    Unbinder unbinder;

    public SliderFragment() {
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
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onBack() {
        baseActivity.finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Slider_Fragment_Btn_Skap)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), UserCycleActivity.class);
        getActivity().startActivity(intent);

    }
}
