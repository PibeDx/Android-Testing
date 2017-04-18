package com.josecuentas.android_testing.data.repository;

import android.os.Bundle;

import com.josecuentas.android_testing.data.rest.ApiClient;
import com.josecuentas.android_testing.data.rest.response.LoginResponse;
import com.josecuentas.android_testing.domain.interactor.AccountInteractor;
import com.josecuentas.android_testing.domain.interactor.StorageCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by jcuentas on 18/04/17.
 */

public class AccountRestInteractor implements AccountInteractor {

    ApiClient.ApiInterface mApiInterface;


    public AccountRestInteractor() {
    }

    public AccountRestInteractor(ApiClient.ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }

    public AccountRestInteractor(Retrofit retrofit) {
        mApiInterface = retrofit.create(ApiClient.ApiInterface.class);
    }

    @Override public void logIn(String code, String password, String appId, String typeDevice, String tokenDevice, int appVersion, final StorageCallback storageCallback) {
        /*Bundle bundle = new Bundle();
        bundle.putString("as","as");
        storageCallback.onSuccess(bundle);*/
        Call<LoginResponse> call = mApiInterface.logIn();
        call.enqueue(new Callback<LoginResponse>() {
            @Override public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Bundle bundle = new Bundle();
                bundle.putString("as","as");
                storageCallback.onSuccess(bundle);
            }

            @Override public void onFailure(Call<LoginResponse> call, Throwable t) {
                storageCallback.onFailure(null);
            }
        });

    }

    @Override public void logOut(String token, StorageCallback storageCallback) {

    }

    @Override public void forgotPassword(StorageCallback storageCallback) {

    }

    @Override public void register(StorageCallback storageCallback) {

    }
}
