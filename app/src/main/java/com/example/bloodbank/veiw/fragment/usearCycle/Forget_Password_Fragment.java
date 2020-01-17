package com.example.bloodbank.veiw.fragment.usearCycle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.example.bloodbank.R;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.data.model.reset_password.ResetPassword;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.veiw.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;


public class Forget_Password_Fragment extends BaseFragment {

    @BindView(R.id.fragment_forget_password_ET_phone_number)
    EditText fragmentForgetPasswordETPhoneNumber;
    Unbinder unbinder;

    public Forget_Password_Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
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

    @OnClick(R.id.fragment_login_BT_send)

    public void onViewClicked() {

        String phone=fragmentForgetPasswordETPhoneNumber.getText().toString();

        if (phone.isEmpty()){
            fragmentForgetPasswordETPhoneNumber.setError("please enter your phone");
        }
        getClient().getResetPassword(phone).enqueue(new Callback<ResetPassword>() {
            @Override
            public void onResponse(Call<ResetPassword> call, Response<ResetPassword> response) {
                try {
                    if (response.body().getStatus()==1) {
                        Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        Chang_Password_Fregment chang_password_fregment=new Chang_Password_Fregment();
                        HelperMethod.replace(chang_password_fregment,getActivity().getSupportFragmentManager(),R.id.user_cycle_activity_fl_fragment_contaner,null,null);
                         }

                }catch (Exception e){
                    Toast.makeText(baseActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResetPassword> call, Throwable t) {
                Toast.makeText(baseActivity, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
           }
}
