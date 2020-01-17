package com.example.bloodbank.helper;


import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.data.model.generalResponse.GeneralResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class GeneralRequests {

    List<String> names =new ArrayList<>();
    List<Integer> ids =new ArrayList<>();
    public static void getSpinnerData(Call<GeneralResponse> method, final Spinner spinner
            , final SpinnerAdapter spinnerAdapter, final String hint) {

        method.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                   // spinner.setVisibility(View.VISIBLE);
                    if (response.body().getStatus() == 1) {
                        spinnerAdapter.setData(response.body().getData(), hint);
                        spinner.setAdapter(spinnerAdapter); }

                } catch (Exception e) {
                   }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }


    public static void getSpinnerData(final Call<GeneralResponse> methodGav, final Spinner spinnerGav,final SpinnerAdapter adapterGav, final String hintGav,
                                       final Call<GeneralResponse> methodCit,final Spinner spinnerCit,final SpinnerAdapter adapterCit,final String hintCit ) {

        methodGav.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {

                    if (response.body().getStatus() == 1) {

                        adapterGav.setData(response.body().getData(),hintGav);
                        spinnerGav.setAdapter(adapterGav);
                        spinnerGav.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position !=0) {
                                    getSpinnerData(methodCit,spinnerCit,adapterCit,hintCit);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                          }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

                 }
        });
    }
}
