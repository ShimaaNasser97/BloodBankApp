package com.example.bloodbank.veiw.fragment.usearCycle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.model.DateModel;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.veiw.activity.HomeCycleActivity;
import com.example.bloodbank.veiw.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;
import static com.example.bloodbank.data.local.SharedPreferencesManger.REMEMBER;
import static com.example.bloodbank.data.local.SharedPreferencesManger.SaveData;
import static com.example.bloodbank.data.local.SharedPreferencesManger.USER_DATA;
import static com.example.bloodbank.helper.GeneralRequests.getSpinnerData;
import static com.example.bloodbank.helper.HelperMethod.disappearKeypad;
import static com.example.bloodbank.helper.HelperMethod.showCalender;

public class Creat_New_Account_Fragment extends BaseFragment {

    @BindView(R.id.fragment_CreatNewAccount_ET_name)
    EditText fragmentCreatNewAccountETName;
    @BindView(R.id.fragment_CreatNewAccount_ET_email)
    EditText fragmentCreatNewAccountETEmail;
    @BindView(R.id.fragment_CreatNewAccount_ET_barthDate)
    EditText fragmentCreatNewAccountETBarthDate;
    @BindView(R.id.fragment_creatnewAccount_et_finaldate)
    EditText fragmentCreatnewAccountEtFinaldate;
    @BindView(R.id.fragment_CreatNewAccount_ET_pone)
    EditText fragmentCreatNewAccountETPone;
    @BindView(R.id.fragment_CreatNewAccount_ET_password)
    EditText fragmentCreatNewAccountETPassword;
    @BindView(R.id.fragment_CreatNewAccount_ET_verefiying)
    EditText fragmentCreatNewAccountETVerefiying;
    @BindView(R.id.fragment_CreatNewAccount_BT_signon)
    Button fragmentCreatNewAccountBTSignon;
    Unbinder unbinder;
    @BindView(R.id.fragment_CreatNewAccount_sp_bloodType)
    Spinner fragmentCreatNewAccountSpBloodType;
    @BindView(R.id.fragment_creatnewaccount_sp_gavernment)
    Spinner fragmentCreatnewaccountSpGavernment;
    @BindView(R.id.fragment_CreatNewAccount_sp_city)
    Spinner fragmentCreatNewAccountSpCity;

    private ApiService apiService;
    SpinnerAdapter cityAdapter;
    SpinnerAdapter governmentAdapter;
    SpinnerAdapter bloodtypeAdapter;
    private DateModel birthday , lastTime;


    public Creat_New_Account_Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_creat_new_accunt, container, false);
        unbinder = ButterKnife.bind(this, view);

        apiService = getClient();
        birthday= new DateModel("01","01","1970","1970-01-01");
        lastTime= new DateModel("01","01","1970","1970-01-01");

        // fragmentCreatnewaccountSpGavernment.setVisibility(View.GONE);
        bloodtypeAdapter = new SpinnerAdapter(getActivity());
        governmentAdapter = new SpinnerAdapter(getActivity());
        cityAdapter = new SpinnerAdapter(getActivity());

        disappearKeypad(getActivity(), view);

        getSpinnerData(apiService.getPloodTypes(), fragmentCreatNewAccountSpBloodType
                , bloodtypeAdapter, getString(R.string.choose_blood_type));

        getSpinnerData(apiService.getGavervment(), fragmentCreatnewaccountSpGavernment, governmentAdapter, getString(R.string.choose_gavernment),
                apiService.getCites(governmentAdapter.selectedId), fragmentCreatNewAccountSpCity, cityAdapter, getString(R.string.choose_city));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fragment_CreatNewAccount_BT_signon)
    public void onViewClicked() {
        userSignUp();
    }

    private void userSignUp() {

        String name = fragmentCreatNewAccountETName.getText().toString();
        String email = fragmentCreatNewAccountETEmail.getText().toString();
        String birthDate = fragmentCreatNewAccountETBarthDate.getText().toString();
        int bloodType = bloodtypeAdapter.selectedId;
        String finalDate = fragmentCreatnewAccountEtFinaldate.getText().toString();
        int city = cityAdapter.selectedId;
        String phone = fragmentCreatNewAccountETPone.getText().toString();
        String password = fragmentCreatNewAccountETPassword.getText().toString();
        String verfyPassword = fragmentCreatNewAccountETVerefiying.getText().toString();

        if (name.isEmpty()) {
            fragmentCreatNewAccountETName.setError("name is required");
            fragmentCreatNewAccountETName.requestFocus();

            return;
        }

        if (email.isEmpty()) {
            fragmentCreatNewAccountETEmail.setError("email is required");
            fragmentCreatNewAccountETEmail.requestFocus();

            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            fragmentCreatNewAccountETEmail.setError("email is required");
            fragmentCreatNewAccountETEmail.requestFocus();
            return;
        }

        if (birthDate.isEmpty()) {
            fragmentCreatNewAccountETBarthDate.setError("birthDate is required");
            fragmentCreatNewAccountETBarthDate.requestFocus();
            return;
        }


        if (finalDate.isEmpty()) {
            fragmentCreatnewAccountEtFinaldate.setError("finalDate is required");
            fragmentCreatnewAccountEtFinaldate.requestFocus();

            return;
        }


        if (phone.isEmpty()) {
            fragmentCreatNewAccountETPone.setError("phone is required");
            fragmentCreatNewAccountETPone.requestFocus();

            return;
        }

        if (password.isEmpty()) {
            fragmentCreatNewAccountETPassword.setError("password is required");
            fragmentCreatNewAccountETPassword.requestFocus();

            return;
        }

        if (password.length() < 6) {
            fragmentCreatNewAccountETPassword.setError("password should be atleast 6 character long");
            fragmentCreatNewAccountETPassword.requestFocus();

            return;
        }

        if (verfyPassword.isEmpty()) {
            fragmentCreatNewAccountETVerefiying.setError("verefyPassword is required");
            fragmentCreatNewAccountETVerefiying.requestFocus();

            return;
        }
        apiService.getSignup(name, email, birthDate, city,phone, finalDate, password, verfyPassword, bloodType).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        SaveData(getActivity(), USER_DATA, response.body().getData());
                        SaveData(getActivity(), REMEMBER, response.body().getData());
                        Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(getActivity(), HomeCycleActivity.class);
                        startActivity(intent);
                    }
                    Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(baseActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(baseActivity, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick({R.id.fragment_CreatNewAccount_ET_barthDate, R.id.fragment_creatnewAccount_et_finaldate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_CreatNewAccount_ET_barthDate:

                showCalender(getActivity(),getString(R.string.choose_date),fragmentCreatNewAccountETBarthDate
            ,birthday);
                break;
            case R.id.fragment_creatnewAccount_et_finaldate:

                showCalender(getActivity(),getString(R.string.choose_date),fragmentCreatnewAccountEtFinaldate
                        ,lastTime);
                break;
        }
    }
}