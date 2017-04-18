package com.josecuentas.android_testing.domain.interactor;

/**
 * Created by jcuentas on 11/04/17.
 */

public interface AccountInteractor {
    void logIn(String code, String password, String appId, String typeDevice, String tokenDevice, int appVersion, StorageCallback storageCallback);
    void logOut(String token, StorageCallback storageCallback);
    void forgotPassword(StorageCallback storageCallback);
    void register(StorageCallback storageCallback);
}
