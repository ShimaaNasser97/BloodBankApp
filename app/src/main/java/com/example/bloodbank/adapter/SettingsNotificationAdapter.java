package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class

SettingsNotificationAdapter extends RecyclerView.Adapter<SettingsNotificationAdapter.ViewHolder> {
    boolean checkeed;
    private Context context;
    private Activity activity;
    private List<GeneralResponseData> bloodTypeList = new ArrayList<>();
    ArrayList<String> bloodType = new ArrayList<>();

    public SettingsNotificationAdapter(Context context, Activity activity, List<GeneralResponseData> bloodTypeList,
                                       ArrayList<String> bloodType , boolean checkeed) {
        this.context = context;
        this.activity = activity;
        this.bloodTypeList=bloodTypeList;
        this.bloodType=bloodType;
        this.checkeed=checkeed;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_setting_notification,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        holder.fragmentSettingNotificationCbxBloodType.setText(bloodTypeList.get(position).getName());

    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return bloodTypeList.size();
    }

    @OnClick(R.id.fragment_setting_notification_cbx_blood_type)
    public void onViewClicked() {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        @BindView(R.id.fragment_setting_notification_cbx_blood_type)
        CheckBox fragmentSettingNotificationCbxBloodType;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
