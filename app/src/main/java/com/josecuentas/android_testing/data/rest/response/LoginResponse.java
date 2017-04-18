package com.josecuentas.android_testing.data.rest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jcuentas on 18/04/17.
 */

public class LoginResponse {


    @SerializedName("state") private int state;
    @SerializedName("msg") private String msg;
    @SerializedName("data") private Data data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("id") private int id;
        @SerializedName("name") private String name;
        @SerializedName("lastname") private String lastname;
        @SerializedName("photo") private String photo;
        @SerializedName("email") private String email;
        @SerializedName("code") private String code;
        @SerializedName("apitoken") private String apitoken;
        @SerializedName("flagactive") private String flagactive;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getApitoken() {
            return apitoken;
        }

        public void setApitoken(String apitoken) {
            this.apitoken = apitoken;
        }

        public String getFlagactive() {
            return flagactive;
        }

        public void setFlagactive(String flagactive) {
            this.flagactive = flagactive;
        }
    }
}
