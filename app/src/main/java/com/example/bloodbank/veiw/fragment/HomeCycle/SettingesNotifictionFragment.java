package com.example.bloodbank.veiw.fragment.HomeCycle;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.SettingsNotificationAdapter;

import com.example.bloodbank.data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;
import com.example.bloodbank.veiw.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;


public class SettingesNotifictionFragment extends BaseFragment {

    @BindView(R.id.fragment_setting_notification_tv)
    TextView fragmentSettingNotificationTv;
    @BindView(R.id.fragment_setting_notification_rv_blood_type)
    RecyclerView fragmentSettingNotificationRvBloodType;
    @BindView(R.id.fragment_setting_notification_rv_gavernment)
    RecyclerView fragmentSettingNotificationRvGavernment;
    Unbinder unbinder;
    private ArrayList<String> arrayList = new ArrayList<String>();

    private ArrayList<GeneralResponseData> list=new ArrayList<>();
    boolean  Checked=false;
    GeneralResponseData generalResponseData;
    private SettingsNotificationAdapter settingsNotificationAdapter;

    public SettingesNotifictionFragment() {
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
        View view = inflater.inflate(R.layout.fragment_notification_siettinges, container, false);
        unbinder = ButterKnife.bind(this, view);


        init();
        return view;
    }

    private void init() {
             GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
             fragmentSettingNotificationRvBloodType.setLayoutManager(gridLayoutManager);
             settingsNotificationAdapter =new SettingsNotificationAdapter(getActivity(),getActivity(),list,arrayList,Checked);
             fragmentSettingNotificationRvBloodType.setAdapter(settingsNotificationAdapter);

        getClient().getNotificationsSettings("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl").enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {

                try {
                    if (response.body().getStatus()==1) {

                         arrayList.addAll(generalResponseData.getBloodTypes());
                        getClient().getPloodTypes().enqueue(new Callback<GeneralResponse>() {
                          @Override
                          public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {

                              if (response.body().getStatus()==1) {
                                  list.addAll(response.body().getData());
                                  settingsNotificationAdapter.notifyDataSetChanged();
                              }
                               }

                          @Override
                          public void onFailure(Call<GeneralResponse> call, Throwable t) {

                          }});
                    }
                }catch (Exception e){
                    Toast.makeText(baseActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

            }
        });
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
