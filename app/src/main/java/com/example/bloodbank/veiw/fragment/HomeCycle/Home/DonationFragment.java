package com.example.bloodbank.veiw.fragment.HomeCycle.Home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.adapter.donationAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.model.donation.Donation;
import com.example.bloodbank.data.model.donation.DonationData;
import com.example.bloodbank.helper.OnEndLess;
import com.example.bloodbank.veiw.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;
import static com.example.bloodbank.helper.GeneralRequests.getSpinnerData;


public class DonationFragment extends BaseFragment {

    @BindView(R.id.fragment_donation_sp_blood_type)
    Spinner fragmentDonationSpBloodType;
    @BindView(R.id.fragment_donation_sp_city)
    Spinner fragmentDonationSpCity;

    Unbinder unbinder;
    @BindView(R.id.fragment_donation_rv_donations)
    RecyclerView fragmentDonationRvDonations;
    private SpinnerAdapter bloodTypeAdabter;
    private List<DonationData> donationDataList = new ArrayList<>();
    private SpinnerAdapter gavernmentAdapter;
    private LinearLayoutManager linearLayoutManager;
    private donationAdapter donationAdapter;
    private ApiService apiService;
    private Integer maxPage = 0;
    private OnEndLess onEndLess;
    private boolean filter = false;
    public DonationData donation;


    public DonationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_donation, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiService = getClient();

        init();
         bloodTypeAdabter = new SpinnerAdapter(getActivity());
        getSpinnerData(apiService.getPloodTypes(), fragmentDonationSpBloodType
                , bloodTypeAdabter, "blood type");

        gavernmentAdapter = new SpinnerAdapter(getActivity());
        getSpinnerData(apiService.getGavervment(), fragmentDonationSpCity
                , gavernmentAdapter, "city");


        return view;
    }

    private void init() {

        linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentDonationRvDonations.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;

                        if (filter) {
                            onFilter(current_page);
                        } else {
                            getDonations(current_page);

                        }

                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }


                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };

        fragmentDonationRvDonations.addOnScrollListener(onEndLess);

        donationAdapter = new donationAdapter(getActivity(), getActivity(),baseActivity, donationDataList);
        fragmentDonationRvDonations.setAdapter(donationAdapter);

        getDonations(1);

    }

    private void getDonations(int page) {

        Call<Donation> call = getClient().getDonationRequests("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", page);

        startCall(call, page);
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

    @OnClick(R.id.fragment_donation_sv_filtr)
    public void onViewClicked() {

        onFilter(1);

    }

    private void onFilter(int page) {
        filter = true;

        onEndLess.current_page = 1;
        onEndLess.previousTotal = 0;
        onEndLess.previous_page = 1;
        donationDataList = new ArrayList<>();
        donationAdapter = new donationAdapter(getActivity(), getActivity(),baseActivity, donationDataList);
        fragmentDonationRvDonations.setAdapter(donationAdapter);


        Call<Donation> call = getClient().getDonationRequests("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", bloodTypeAdabter.selectedId, gavernmentAdapter.selectedId, page);
        startCall(call, page);
    }

    private void startCall(Call<Donation> call, int page) {

        call.enqueue(new Callback<Donation>() {
            @Override
            public void onResponse(Call<Donation> call, Response<Donation> response) {

                try {

                    if (response.body().getStatus() == 1) {

                        maxPage = response.body().getData().getLastPage();
                        donationDataList.addAll(response.body().getData().getData());
                        donationAdapter.notifyDataSetChanged();

                    }
                } catch (Exception e) {
                    Toast.makeText(baseActivity, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Donation> call, Throwable t) {

            }
        });
    }

}
