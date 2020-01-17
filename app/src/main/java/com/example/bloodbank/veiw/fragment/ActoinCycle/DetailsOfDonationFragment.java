package com.example.bloodbank.veiw.fragment.ActoinCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.donation.DonationData;
import com.example.bloodbank.veiw.fragment.BaseFragment;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.nio.channels.AsynchronousSocketChannel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class DetailsOfDonationFragment extends BaseFragment {


    public DonationData donation;
    @BindView(R.id.fragment_details_of_donation_tv_name)
    TextView fragmentDetailsOfDonationTvName;
    @BindView(R.id.fragment_details_of_donation_tv_age)
    TextView fragmentDetailsOfDonationTvAge;
    @BindView(R.id.fragment_details_of_donation_tv_blood_type)
    TextView fragmentDetailsOfDonationTvBloodType;
    @BindView(R.id.fragment_details_of_donation_tv_numbers)
    TextView fragmentDetailsOfDonationTvNumbers;
    @BindView(R.id.fragment_details_of_donation_tv_hospital_name)
    TextView fragmentDetailsOfDonationTvHospitalName;
    @BindView(R.id.fragment_details_of_donation_tv_hospital_address)
    TextView fragmentDetailsOfDonationTvHospitalAddress;
    @BindView(R.id.fragment_details_of_donation_tv_phone)
    TextView fragmentDetailsOfDonationTvPhone;
    @BindView(R.id.fragment_details_of_donation_tv_details)
    TextView fragmentDetailsOfDonationTvDetails;
    @BindView(R.id.fragment_details_of_donation_btn_call)
    Button fragmentDetailsOfDonationBtnCall;
    Unbinder unbinder;
    @BindView(R.id.fragment_details_mv_map)
    MapView fragmentDetailsMvMap;
    String Longitude,latidude;
    private MapView mapView;
    public Marker marker=null;
    public  GoogleMap googleMap;
    private GoogleApiClient googleApiClient;

    public DetailsOfDonationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_details_of_donation, container, false);
        unbinder = ButterKnife.bind(this, view);

        fragmentDetailsOfDonationTvName.setText(donation.getPatientName());
        fragmentDetailsOfDonationTvAge.setText(donation.getPatientAge());
        fragmentDetailsOfDonationTvBloodType.setText(donation.getBloodType().getName());
        fragmentDetailsOfDonationTvNumbers.setText(donation.getBagsNum());
        fragmentDetailsOfDonationTvHospitalName.setText(donation.getHospitalName());
        fragmentDetailsOfDonationTvHospitalAddress.setText(donation.getHospitalAddress());
        fragmentDetailsOfDonationTvPhone.setText(donation.getPhone());
        fragmentDetailsOfDonationTvDetails.setText(donation.getNotes());

        fragmentDetailsMvMap.onCreate(savedInstanceState);
        fragmentDetailsMvMap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng latLng= new LatLng(Double.valueOf(donation.getLongitude()),Double.valueOf(donation.getLatitude()));
                marker = googleMap.addMarker(new MarkerOptions().position(latLng).title("location"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,12.0f));
            }
        });
              return view;
    }

    @Override
    public void onBack() {
        baseActivity.finish();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mapView.onStart();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mapView.onResume();
//    }
//
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mapView.onStop();
//    }
//

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
        unbinder.unbind();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
