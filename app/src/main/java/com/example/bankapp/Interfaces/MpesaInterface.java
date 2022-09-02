package com.example.bankapp.Interfaces;

import com.example.bankapp.models.LipaNaMpesa;
import com.example.bankapp.models.MpesaAccess;
import com.example.bankapp.models.MpesaStkResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MpesaInterface {


    //https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials

    @GET("/oauth/v1/generate?grant_type=client_credentials")
    Call<MpesaAccess> getData(
            @Header("Authorization") String basicToken
    );
    //https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest
    @POST("/mpesa/stkpush/v1/processrequest")
    Call<MpesaStkResponse> getRequest(
            @Header("Authorization") String basicToken,
            @Body LipaNaMpesa lipaNaMpesa
    );
}
