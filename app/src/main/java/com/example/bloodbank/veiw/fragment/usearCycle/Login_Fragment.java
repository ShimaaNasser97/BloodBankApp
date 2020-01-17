package com.example.bloodbank.veiw.fragment.usearCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.local.SharedPreferencesManger;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.helper.HelperMethod;
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
import static com.example.bloodbank.data.local.SharedPreferencesManger.USER_DATA;


public class Login_Fragment extends BaseFragment {


    @BindView(R.id.fragment_login_ET_phone)
    EditText fragmentLoginETPhone;
    @BindView(R.id.fragment_login_ET_password)
    EditText fragmentLoginETPassword;
    @BindView(R.id.fragment_login_CBX_remember)
    CheckBox fragmentLoginCBXRemember;
    Unbinder unbinder;
    private ApiService apiService;


    public Login_Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);

        apiService=getClient();

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

    @OnClick({R.id.fragment_login_BT_login, R.id.fragment_login_BT_CreateNewAccount})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_login_BT_login:

                    String phone = fragmentLoginETPhone.getText().toString();
                    String password = fragmentLoginETPassword.getText().toString();
                    validation(phone,password);

                    apiService.getLogin(phone, password).enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if (response.isSuccessful()) {

                         SharedPreferencesManger.SaveData(getActivity(), REMEMBER, fragmentLoginCBXRemember.isChecked());
                         SharedPreferencesManger.SaveData(getActivity(), USER_DATA, response.body().getData());

                         Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getActivity(), HomeCycleActivity.class);
                            getActivity().startActivity(intent);
                        }else{
                            Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Toast.makeText(baseActivity, "حدت شى ما", Toast.LENGTH_SHORT).show();
                    }
                });


                break;
            case R.id.fragment_login_BT_CreateNewAccount:
                Creat_New_Account_Fragment creat_new_account_fragment = new Creat_New_Account_Fragment();
                HelperMethod.replace(creat_new_account_fragment, getActivity().getSupportFragmentManager(), R.id.user_cycle_activity_fl_fragment_contaner, null, null);


        }
    }

    private void validation(String ph ,String pass) {



        if (ph.isEmpty()) {
            fragmentLoginETPhone.setError("phone is required");
            fragmentLoginETPhone.requestFocus();

            return;
        }

        if (pass.isEmpty()) {
            fragmentLoginETPassword.setError("password is required");
            fragmentLoginETPassword.requestFocus();
            return;
        }

        if (pass.length() < 6) {
            fragmentLoginETPassword.setError("password should be atleast 6 character long");
            fragmentLoginETPassword.requestFocus();

            return;
        }

    }


    @OnClick(R.id.fragment_login_TV_forget)
    public void onViewClicked() {
        Forget_Password_Fragment forget_password_fragment = new Forget_Password_Fragment();
        HelperMethod.replace(forget_password_fragment, getActivity().getSupportFragmentManager(), R.id.user_cycle_activity_fl_fragment_contaner, null, null);

    }


}
