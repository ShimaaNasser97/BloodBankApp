package com.example.bloodbank.veiw.fragment.ActoinCycle;

import android.app.Notification;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.NotificationAdapter;
import com.example.bloodbank.data.model.notifications.NotificationData;
import com.example.bloodbank.data.model.notifications.Notifications;
import com.example.bloodbank.helper.OnEndLess;
import com.example.bloodbank.veiw.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;


public class NotificationFragment extends BaseFragment {

    @BindView(R.id.frgment_notification_rv_recycler)
    RecyclerView frgmentNotificationRvRecycler;
    Unbinder unbinder;
    private List<NotificationData> notificationList=new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private NotificationAdapter notificationAdapter;
    private int maxPage=0;
    private OnEndLess onEndLess;

    public NotificationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        unbinder = ButterKnife.bind(this, view);

        initRecycler();
        return view;
    }

    private void initRecycler() {
        linearLayoutManager =new LinearLayoutManager(getActivity());
        frgmentNotificationRvRecycler.setLayoutManager(linearLayoutManager);

         onEndLess = new OnEndLess(linearLayoutManager,1) {
            @Override
            public void onLoadMore(int current_page) {

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                         getNotification(current_page);
                    } else {
                        onEndLess.current_page=onEndLess.previous_page;
                    }
                }
            }
        };
        notificationAdapter=new NotificationAdapter(getActivity(),getActivity(),notificationList);
        frgmentNotificationRvRecycler.setAdapter(notificationAdapter);

        getNotification(1);
    }

    private void getNotification(int page) {
        getClient().getNotification("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", page).enqueue(new Callback<Notifications>() {

            @Override
            public void onResponse(Call<Notifications> call, Response<Notifications> response) {
                try {
                    if (response.body().getStatus()==1) {
                        maxPage=response.body().getData().getLastPage();
                       notificationList.addAll(response.body().getData().getData());
                       notificationAdapter.notifyDataSetChanged();
                    }

                }catch (Exception e){
                    Toast.makeText(baseActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Notifications> call, Throwable t) {

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
