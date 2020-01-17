package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.donation.DonationData;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.veiw.activity.BaseActivity;
import com.example.bloodbank.veiw.fragment.ActoinCycle.DetailsOfDonationFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v4.content.ContextCompat.startActivity;

public class

donationAdapter extends RecyclerView.Adapter<donationAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private BaseActivity baseActivity;
    private List<DonationData> donationDataList = new ArrayList<>();

    public donationAdapter(Context context, Activity activity, BaseActivity baseActivity, List<DonationData> donationDataList) {
        this.context = context;
        this.activity = activity;
        this.baseActivity=baseActivity;
        this.donationDataList = donationDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_donation,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        holder.fragmentDonationTvName.setText(donationDataList.get(position).getPatientName());
        holder.fragmentOdonationTvHospital.setText(donationDataList.get(position).getHospitalName());
        holder.fragmentDonationTvCity.setText(donationDataList.get(position).getCity().getName());
        holder.fragmentOrdersTvCircle.setText(donationDataList.get(position).getBloodType().getName());
    }

    private void setAction(final ViewHolder holder, final int position) {
        holder.fragmentDonationBtnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsOfDonationFragment detailsOfDonationFragment=new DetailsOfDonationFragment();
                detailsOfDonationFragment.donation=donationDataList.get(position);
                HelperMethod.replace(detailsOfDonationFragment, baseActivity.getSupportFragmentManager(), R.id.home_cycle_fl_fragment_contaner, null, null);

            }
        });

        holder.fragmentDonationBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+donationDataList.get(position).getPhone()));
                Intent i=Intent.createChooser(intent,"make call with");
                 baseActivity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return donationDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.fragment_orders_tv_circle)
        TextView fragmentOrdersTvCircle;
        @BindView(R.id.fragment_donation_tv_name)
        TextView fragmentDonationTvName;
        @BindView(R.id.fragment_odonation_tv_hospital)
        TextView fragmentOdonationTvHospital;
        @BindView(R.id.fragment_donation_tv_city)
        TextView fragmentDonationTvCity;
        @BindView(R.id.fragment_donation_btn_details)
        Button fragmentDonationBtnDetails;
        @BindView(R.id.fragment_donation_btn_call)
        Button fragmentDonationBtnCall;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
