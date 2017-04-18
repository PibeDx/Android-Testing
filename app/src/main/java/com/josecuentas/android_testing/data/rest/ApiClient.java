package com.josecuentas.android_testing.data.rest;

import android.util.Log;

import com.josecuentas.android_testing.data.rest.response.LoginResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

/**
 * Created by jcuentas on 11/04/17.
 */

public class ApiClient {
    private static String PATH = "http://dev-jetperu.osp.pe/api/";
    private static final String TAG = "ApiClient";
    private static Retrofit retrofit;

    public static Retrofit build(String url) {
        retrofit= new Retrofit.Builder()
                .baseUrl(url)
                .client(getBasicClientInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    /*public static Retrofit buildLogin(String url) {
        retrofit= new Retrofit.Builder()
                .baseUrl(PATH)
                .client(getBasicClientInterceptor())
                .addConverterFactory(buildGsonConverter())
                .build();
        return retrofit;
    }*/

    public static ApiInterface getApiInterface() {
        retrofit= new Retrofit.Builder()
                .baseUrl(PATH)
                .client(getBasicClientInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        return apiInterface;
    }

    /*public static ApiInterface getApiInterfaceLogin() {
        retrofit= new Retrofit.Builder()
                .baseUrl(PATH)
                .client(getBasicClientInterceptor())
                .addConverterFactory(buildGsonConverter())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        return apiInterface;
    }*/

    public static ApiInterface getApiInterface(String token) {
        retrofit= new Retrofit.Builder()
                .baseUrl(PATH)
                .client(getClientInterceptor(token))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        return apiInterface;
    }

    public static  Retrofit getRetrofit(){
        return retrofit;
    }

    public interface ApiInterface {
        @POST("v1/user/login")
        Call<LoginResponse> logIn();
    }

    public static OkHttpClient getClientInterceptor(final String token) {
        //HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Add the interceptor to OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //builder.interceptors().add(new LoggingInterceptor());
        builder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Cookie", token)
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();

                Response response = chain.proceed(request);
                //return chain.proceed(request);

                ResponseBody responseBody = response.body();
                long contentLength = responseBody.contentLength();
                String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
                Log.v(TAG, "<-- " + response.code() + ' ' + response.message() + ' ' + response.request().url() + " (" + bodySize + " body" + ')');

                return response;
            }
        });
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.interceptors().add(logging);
        return builder.build();
    }

    public static OkHttpClient getBasicClientInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(logging);//LOGS
        builder.readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS);//TIMEOUT
        OkHttpClient client = builder.build();
        return client;
    }

    /*private static GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        // Adding custom deserializers
        gsonBuilder.registerTypeAdapter(LoginResponse.class, new LoginResponseDeserializer());
        Gson myGson = gsonBuilder.create();
        return GsonConverterFactory.create(myGson);
    }*/
}
