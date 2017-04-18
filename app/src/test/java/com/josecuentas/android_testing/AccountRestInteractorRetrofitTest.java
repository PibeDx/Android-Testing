package com.josecuentas.android_testing;

import android.os.Bundle;

import com.josecuentas.android_testing.data.repository.AccountRestInteractor;
import com.josecuentas.android_testing.data.rest.ApiClient;
import com.josecuentas.android_testing.domain.interactor.AccountInteractor;
import com.josecuentas.android_testing.domain.interactor.StorageCallback;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jcuentas on 18/04/17.
 */

public class AccountRestInteractorRetrofitTest {

    MockWebServer mServer;
    MockResponse mMockError = new MockResponse().setHttp2ErrorCode(500);
    MockResponse mMockResponse = new MockResponse().setBody(fromFile("json_account_login_success.json"));
    Retrofit mRetrofit;

    /*@Mock */AccountInteractor mAccountInteractor;
    @Mock StorageCallback mStorageCallback;
    //@Mock Response mResponse;
    @Mock Callback mCallback;

    @Captor private ArgumentCaptor<Callback> mCallbackArgumentCaptor;
    @Mock Call mCall;
    @Mock ApiClient.ApiInterface mApiInterface;
    @Captor private ArgumentCaptor<Bundle> mBundleArgumentCaptor;


    private static String fromFile(String name) {
        try {
            InputStream resource = AccountRestInteractorRetrofitTest.class.getClassLoader().getResourceAsStream(name);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
            StringBuilder result = new StringBuilder();
            String partial = reader.readLine();
            while (partial != null) {
                result.append(partial);
                partial = reader.readLine();
            }
            return result.toString();
        } catch (Exception ignored) {
            throw new IllegalArgumentException("File not found");
        }
    }

    @Before
    public void setup() throws Exception {
        mServer = new MockWebServer();
        mRetrofit = ApiClient.build(mServer.url("/").toString());
        mApiInterface = mRetrofit.create(ApiClient.ApiInterface.class);
        mAccountInteractor = new AccountRestInteractor(mApiInterface);

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void loginSuccess() throws Exception {

        String appId = "1";
        String typeDevice = "1"; //Android
        int appVersion = 2;
        String tokenDevice = "791818718787878178718178434";
        String code = "198512";
        String password = "123456";
        mServer.enqueue(mMockResponse);
        //Call<LoginResponse> call = mApiInterface.logIn();
        //when(mCall.execute()).thenReturn(mResponse);
//        when(mApiInterface.logIn()).thenReturn(mCall);
//        when(mCall.execute()).thenReturn(mResponse);

//        verify(mStorageCallback).onSuccess(mBundleArgumentCaptor.capture());
        when(mApiInterface.logIn()).thenReturn(mCall);
        //mAccountInteractor.logIn(code, password, appId, typeDevice, tokenDevice, appVersion, mStorageCallback);
        verify(mStorageCallback).onSuccess(mBundleArgumentCaptor.capture());


//        assertTrue(call.execute() != null);
//        verify(mStorageCallback, times(1)).onSuccess(null);
//        verify(mStorageCallback, times(1)).onSuccess(argThat(new ObjectEqualityArgumentMatcher(Bundle.class)));
//        verify(mStorageCallback, times(1)).onSuccess(isA(Bundle.class));
//        verify(mStorageCallback, times(1)).onSuccess(ArgumentMatchers.any());
    }



    private class ObjectEqualityArgumentMatcher<T> implements ArgumentMatcher<T> {
        Class<T> thisObject;

        public ObjectEqualityArgumentMatcher(Class<T> thisObject) {
            this.thisObject = thisObject;
        }

        /*@Override
        public boolean matches(Object argument) {
            return thisObject.isInstance(argument);
        }*/
        @Override
        public boolean matches(Object argument) {
            if (argument != null) {
                return thisObject.isInstance(argument);
            }
            return false;
        }


    }

    //@Captor ArgumentCaptor<Callback<Response>> callbackCaptor;



}
