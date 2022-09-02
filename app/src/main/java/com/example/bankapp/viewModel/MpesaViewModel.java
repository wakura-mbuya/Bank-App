package com.example.bankapp.viewModel;

import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;

import com.example.bankapp.Interfaces.MpesaInterface;
import com.example.bankapp.Interfaces.RetrofitInstance;
import com.example.bankapp.models.LipaNaMpesa;
import com.example.bankapp.models.MpesaAccess;
import com.example.bankapp.models.MpesaStkResponse;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MpesaViewModel extends ViewModel {

    public void makeApiCall(){
        MpesaInterface mpesaInteface = RetrofitInstance.getRetrofitInstance().create(MpesaInterface.class);
        String basicToken = "Basic WFhIYlV2Z1BZYWQ4dlh2ODV5WkEyS21HZEN2OUUyVEM6elo0bVZSakNqV285aGc4Sg==";
        Call<MpesaAccess> call = mpesaInteface.getData(basicToken);
        call.enqueue(new Callback<MpesaAccess>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<MpesaAccess> call, Response<MpesaAccess> response) {
                if (response.code() != 200) {
                    Log.i("Tag","Error");
                }
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String businessCode = "174379";
                String amt = "1";
                String code = String.valueOf(businessCode);
                String passKey = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";
                SimpleDateFormat df1 = new SimpleDateFormat("YYYYMMddhhmmss");
                String timme = df1.format(timestamp);
                String combine = code+passKey+timme;
                String transact = "CustomerPayBillOnline";
                String test = "Pay for parking";
                String ph = "254719189576";
                String url = "https://02be-102-167-195-189.ngrok.io/api/mpesa/callback";
                String bas64 = Base64.getEncoder().encodeToString(combine.getBytes()).toString();
                //Lipa na mpesa body
                LipaNaMpesa lipaNaMpesa = new LipaNaMpesa(
                        code,
                        bas64,
                        timme,
                        transact,
                        amt,
                        ph,
                        code,
                        ph,
                        url,
                        test,
                        test
                );
                Call<MpesaStkResponse> call1 = mpesaInteface.getRequest("Bearer " +response.body().getAccess_token(), lipaNaMpesa);
                call1.enqueue(new Callback<MpesaStkResponse>() {
                    @Override
                    public void onResponse(Call<MpesaStkResponse> call, Response<MpesaStkResponse> response) {
                        if (response.code() != 200) {
                            Log.i("Tag","Error");
                        }

                    }

                    @Override
                    public void onFailure(Call<MpesaStkResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }

            @Override
            public void onFailure(Call<MpesaAccess> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
