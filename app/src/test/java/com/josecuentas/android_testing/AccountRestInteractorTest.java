package com.josecuentas.android_testing;

import android.os.Bundle;

import com.josecuentas.android_testing.data.repository.AccountRestInteractor;
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

import static org.mockito.Mockito.verify;

/**
 * Created by jcuentas on 18/04/17.
 */

public class AccountRestInteractorTest {

    private AccountInteractor mAccountInteractor;
    @Mock StorageCallback mStorageCallback;
    @Captor private ArgumentCaptor<Bundle> mBundleArgumentCaptor;
    @Before
    public void setup() throws Exception {
        mAccountInteractor = new AccountRestInteractor();
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

        mAccountInteractor.logIn(code, password, appId, typeDevice, tokenDevice, appVersion, mStorageCallback);
//        verify(mStorageCallback, times(1)).onSuccess(null);
//        verify(mStorageCallback, times(1)).onSuccess(argThat(new ObjectEqualityArgumentMatcher(Bundle.class)));
//        verify(mStorageCallback, times(1)).onSuccess(isA(Bundle.class));
        //verify(mStorageCallback, times(1)).onSuccess(ArgumentMatchers.any());
        verify(mStorageCallback).onSuccess(mBundleArgumentCaptor.capture());

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

}
