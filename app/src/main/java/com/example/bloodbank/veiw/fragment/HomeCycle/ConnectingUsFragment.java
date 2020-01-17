package com.example.bloodbank.veiw.fragment.HomeCycle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;
import com.example.bloodbank.data.model.settings.Settings;
import com.example.bloodbank.veiw.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;


public class ConnectingUsFragment extends BaseFragment {

    @BindView(R.id.fragment_connect_us_et_phone)
    EditText fragmentConnectUsEtPhone;
    @BindView(R.id.fragment_connect_us_et_email)
    EditText fragmentConnectUsEtEmail;
    @BindView(R.id.fragment_connect_us_tv_text)
    TextView fragmentConnectUsTvText;
    @BindView(R.id.fragment_connect_us_et_massege_titel)
    EditText fragmentConnectUsEtMassegeTitel;
    @BindView(R.id.fragment_connect_us_et_massege_content)
    EditText fragmentConnectUsEtMassegeContent;
    Unbinder unbinder;

    private Intent intent;
    private GeneralResponseData generalResponseData;

    Settings settings;

    public ConnectingUsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_connecting_us, container, false);
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

    @OnClick({R.id.fragment_connect_us_iv_google, R.id.fragment_connect_us_iv_whats, R.id.fragment_connect_us_iv_insta, R.id.fragment_connect_us_iv_youyube, R.id.fragment_connect_us_iv_tweter, R.id.fragment_connect_us_iv_face, R.id.fragment_connect_us_btn_send})

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_connect_us_iv_google:
                getClient().getSettings("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27").enqueue(new Callback<Settings>() {
                    @Override
                    public void onResponse(Call<Settings> call, Response<Settings> response) {
                        try {
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getGoogleUrl()));
                            startActivity(intent);
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<Settings> call, Throwable t) {

                    }
                });
                break;
            case R.id.fragment_connect_us_iv_whats:
                getClient().getSettings("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27").enqueue(new Callback<Settings>() {
                    @Override
                    public void onResponse(Call<Settings> call, Response<Settings> response) {
                        try {
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getWhatsapp()));
                            startActivity(intent);
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<Settings> call, Throwable t) {

                    }
                });
                break;
            case R.id.fragment_connect_us_iv_insta:
                getClient().getSettings("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27").enqueue(new Callback<Settings>() {
                    @Override
                    public void onResponse(Call<Settings> call, Response<Settings> response) {
                        try {
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getInstagramUrl()));
                            startActivity(intent);
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<Settings> call, Throwable t) {

                    }
                });
                break;
            case R.id.fragment_connect_us_iv_youyube:
                getClient().getSettings("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27").enqueue(new Callback<Settings>() {
                    @Override
                    public void onResponse(Call<Settings> call, Response<Settings> response) {
                        try {
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getYoutubeUrl()));
                            startActivity(intent);
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<Settings> call, Throwable t) {

                    }
                });
                break;
            case R.id.fragment_connect_us_iv_tweter:
                getClient().getSettings("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27").enqueue(new Callback<Settings>() {
                    @Override
                    public void onResponse(Call<Settings> call, Response<Settings> response) {
                        try {
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getTwitterUrl()));
                            startActivity(intent);
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<Settings> call, Throwable t) {

                    }
                });
                break;
            case R.id.fragment_connect_us_iv_face:
                getClient().getSettings("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27").enqueue(new Callback<Settings>() {
                    @Override
                    public void onResponse(Call<Settings> call, Response<Settings> response) {
                        try {
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getFacebookUrl()));
                            startActivity(intent);
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<Settings> call, Throwable t) {

                    }
                });
                break;
            case R.id.fragment_connect_us_btn_send:
                break;
        }
    }


    @OnClick(R.id.fragment_connect_us_btn_send)
    public void onViewClicked() {

        String titel=fragmentConnectUsEtMassegeTitel.getText().toString();
        String massege=fragmentConnectUsEtMassegeContent.getText().toString();

        getClient().getConect("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27",titel,massege).enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                 try {

                     if (response.body().getStatus()==1) {
                         Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                     }
                 }catch (Exception e){
                     Toast.makeText(baseActivity, e.getMessage(), Toast.LENGTH_SHORT).show();

                 }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                Toast.makeText(baseActivity, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
