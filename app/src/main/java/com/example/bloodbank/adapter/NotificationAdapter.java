package com.example.bloodbank.adapter;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.notifications.NotificationData;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.veiw.activity.BaseActivity;
import com.example.bloodbank.veiw.fragment.HomeCycle.Home.DonationFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class

NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context context;
    private BaseActivity activity;
    private List<NotificationData> notificationDataList = new ArrayList<>();
    public NotificationAdapter(Context context, Activity activity, List<NotificationData> notificationDataList) {
        this.context = context;
        this.activity = (BaseActivity) activity;
        this.notificationDataList = notificationDataList;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        if (notificationDataList.get(position).getPivot().equals("0")) {
            holder.itemNotificationIvIcon.setImageResource(R.drawable.non_read_notification);
        }else {
            holder.itemNotificationIvIcon.setImageResource(R.drawable.read_notification);
        }
        holder.itemNotificationTvText.setText(notificationDataList.get(position).getTitle());
        holder.itemNotificationTvDate.setText(notificationDataList.get(position).getCreatedAt());
    }

    private void setAction(ViewHolder holder, int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonationFragment donationFragment=new DonationFragment();
                HelperMethod.replace(donationFragment,activity.getSupportFragmentManager(),R.id.home_cycle_fl_fragment_contaner,null,null);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notificationDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_notification_iv_icon)
        ImageView itemNotificationIvIcon;
        @BindView(R.id.item_notification_tv_text)
        TextView itemNotificationTvText;
        @BindView(R.id.item_notification_tv_date)
        TextView itemNotificationTvDate;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
