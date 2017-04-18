package com.josecuentas.android_testing.domain.interactor;

/**
 * Created by jcuentas on 11/04/17.
 */

public interface StorageCallback {
    void onSuccess(Object object);
    void onFailure(Exception e);
}
