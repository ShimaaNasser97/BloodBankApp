package com.example.bloodbank.veiw.fragment.ActoinCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;
import com.example.bloodbank.veiw.activity.MapsActivity;
import com.example.bloodbank.veiw.fragment.BaseFragment;
import com.example.bloodbank.veiw.fragment.HomeCycle.Home.DonationFragment;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.example.bloodbank.data.api.ApiClient.getClient;
import static com.example.bloodbank.helper.GeneralRequests.getSpinnerData;


public class DonationOrderFragment extends BaseFragment {

    @BindView(R.id.fragment_donation_order_ET_name)
    EditText fragmentDonationOrderETName;
    @BindView(R.id.fragment_donation_order_ET_age)
    EditText fragmentDonationOrderETAge;
    @BindView(R.id.fragment_donation_order_sp_bloodType)
    Spinner fragmentDonationOrderSpBloodType;
    @BindView(R.id.fragment_donation_order_ET_numbers)
    EditText fragmentDonationOrderETNumbers;
    @BindView(R.id.fragment_donation_order_ET_hosbital_name)
    EditText fragmentDonationOrderETHosbitalName;
    @BindView(R.id.fragment_donation_order_ET_hospital_adress)
    EditText fragmentDonationOrderETHospitalAdress;
    @BindView(R.id.fragment_donation_order_sp_gavernment)
    Spinner fragmentDonationOrderSpGavernment;
    @BindView(R.id.fragment_donation_order_sp_city)
    Spinner fragmentDonationOrderSpCity;
    @BindView(R.id.fragment_donation_order_ET_pone)
    EditText fragmentDonationOrderETPone;
    @BindView(R.id.fragment_donation_order_ET_notes)
    EditText fragmentDonationOrderETNotes;
    Unbinder unbinder;
    private SpinnerAdapter bloodTypeAdabter;
    private Call<GeneralResponse> getClint;
    private SpinnerAdapter gavernmentAdapter;
    private SpinnerAdapter cityadapter;
    private String name;
    private String age;
    private int bloodTypeId;
    private String bagsNumber;
    private String hospitalName;
    private String hospitaladress;
    private int cityId;

    private String phone;
    private String notes;

    GoogleMap googleMap;
    private ApiService apiService;

    public DonationOrderFragment() {
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
        View view = inflater.inflate(R.layout.fragment_donation_order, container, false);
        unbinder = ButterKnife.bind(this, view);

         apiService=getClient();
        bloodTypeAdabter = new SpinnerAdapter(getActivity());
        gavernmentAdapter = new SpinnerAdapter(getActivity());
        cityadapter = new SpinnerAdapter(getActivity());

        getSpinnerData(getClient().getPloodTypes(), fragmentDonationOrderSpBloodType,
                bloodTypeAdabter, "choose blood type");

        getSpinnerData(apiService.getGavervment(), fragmentDonationOrderSpGavernment, gavernmentAdapter, getString(R.string.choose_gavernment),
                apiService.getCites(gavernmentAdapter.selectedId), fragmentDonationOrderSpCity, cityadapter, getString(R.string.choose_city));


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

    @OnClick({R.id.fragment_donation_order_btn_location, R.id.fragment_donation_ordert_BT_send_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_donation_order_btn_location:

                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_donation_ordert_BT_send_order:

                name = fragmentDonationOrderETName.getText().toString();
                age = fragmentDonationOrderETAge.getText().toString();
                bloodTypeId = bloodTypeAdabter.selectedId;
                bagsNumber = fragmentDonationOrderETNumbers.getText().toString();
                hospitalName = fragmentDonationOrderETHosbitalName.getText().toString();
                hospitaladress = fragmentDonationOrderETHospitalAdress.getText().toString();
                cityId = cityadapter.selectedId;
                phone = fragmentDonationOrderETPone.getText().toString();
                notes = fragmentDonationOrderETNotes.getText().toString();

                if (name.isEmpty()) {
                    fragmentDonationOrderETName.setError("name is required");
                    fragmentDonationOrderETName.requestFocus();
                    return;
                }

                if (age.isEmpty()) {
                    fragmentDonationOrderETAge.setError("name is required");
                    fragmentDonationOrderETAge.requestFocus();
                    return;
                }

               /* if (bloodTypeId==0) {

                    return;
                }*/
                if (bagsNumber.isEmpty()) {
                    fragmentDonationOrderETNumbers.setError("name is required");
                    fragmentDonationOrderETNumbers.requestFocus();
                    return;
                }

                if (hospitalName.isEmpty()) {
                    fragmentDonationOrderETHosbitalName.setError("name is required");
                    fragmentDonationOrderETHosbitalName.requestFocus();
                    return;
                }

                if (hospitaladress.isEmpty()) {
                    fragmentDonationOrderETHospitalAdress.setError("name is required");
                    fragmentDonationOrderETHospitalAdress.requestFocus();
                    return;
                }

                /*if (cityId==0) {
                     return;
                }*/

                if (phone.isEmpty()) {
                    fragmentDonationOrderETPone.setError("name is required");
                    fragmentDonationOrderETPone.requestFocus();
                    return;
                }

                if (notes.isEmpty()) {
                    fragmentDonationOrderETNotes.setError("name is required");
                    fragmentDonationOrderETNotes.requestFocus();
                    return;
                }

                getClient().getCreate("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", name, age, bloodTypeId, bagsNumber,
                        hospitalName, hospitaladress, cityId, phone, notes, MapsActivity.latidude, MapsActivity.longitude).enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {

                        if (response.body().getStatus() == 1) {

                            Toast.makeText(baseActivity, "onResponse..... Done", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {
                        Toast.makeText(baseActivity, "onFailure.... " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                break;
        }
    }
}
