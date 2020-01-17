package com.example.bloodbank.veiw.fragment.usearCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.new_password.NewPassword;
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

public class Chang_Password_Fregment extends BaseFragment {

    @BindView(R.id.fragment_chang_pass_ET_pin_code)
    EditText fragmentChangPassETPinCode;
    @BindView(R.id.fragment_chang_pass_ET_NewPassword)
    EditText fragmentChangPassETNewPassword;
    @BindView(R.id.fragment_chang_pass_ET_verify_password)
    EditText fragmentChangPassETVerifyPassword;
    @BindView(R.id.fragment_chang_pass_ET_phone)
    EditText fragmentChangPassETPhone;
    Unbinder unbinder;
    private String pin , newPass,vireyPass,phone;

    public Chang_Password_Fregment() {
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
        View view = inflater.inflate(R.layout.fragment_chang_password, container, false);

        unbinder = ButterKnife.bind(this, view);
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

    @OnClick(R.id.fragment_chang_pass_BT_changPassWord)
    public void onViewClicked() {
         pin=fragmentChangPassETPinCode.getText().toString();
        newPass=fragmentChangPassETNewPassword.getText().toString();
         vireyPass = fragmentChangPassETVerifyPassword.getText().toString();
         phone=fragmentChangPassETPhone.getText().toString();

         getClient().getNewPassword(newPass,vireyPass,pin,phone).enqueue(new Callback<NewPassword>() {
             @Override
             public void onResponse(Call<NewPassword> call, Response<NewPassword> response) {
                 try {
                     if (response.body().getStatus()==1) {
                         Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                         Intent intent=new Intent(getActivity(), HomeCycleActivity.class);
                         startActivity(intent);
                     }
                 }catch (Exception e){
                     Toast.makeText(baseActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                 }
             }

             @Override
             public void onFailure(Call<NewPassword> call, Throwable t) {
                 Toast.makeText(baseActivity, t.getMessage(), Toast.LENGTH_SHORT).show();

             }
         });


    }
}
