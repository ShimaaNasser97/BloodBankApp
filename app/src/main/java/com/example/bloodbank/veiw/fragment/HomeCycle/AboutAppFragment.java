package com.example.bloodbank.veiw.fragment.HomeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;
import com.example.bloodbank.data.model.settings.Settings;
import com.example.bloodbank.veiw.activity.HomeCycleActivity;
import com.example.bloodbank.veiw.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;


public class AboutAppFragment extends BaseFragment {

    @BindView(R.id.fragment_home_tv_about_us)
    TextView fragmentHomeTvAboutUs;
    Unbinder unbinder;
    ApiService apiService;

    public AboutAppFragment() {
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
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        unbinder = ButterKnife.bind(this, view);

        HomeCycleActivity homeCycleActivity = (HomeCycleActivity) getActivity();
        homeCycleActivity.setVisibilityHome(View.VISIBLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        getClient().getSettings("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27").enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                fragmentHomeTvAboutUs.setText(response.body().getData().getAboutApp());
            }

            @Override
            public void onFailure(Call<Settings> call, Throwable t) {
            }
        });

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
}
