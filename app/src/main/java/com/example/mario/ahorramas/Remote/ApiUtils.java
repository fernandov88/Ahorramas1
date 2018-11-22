package com.example.mario.ahorramas.Remote;

public class ApiUtils {
    public ApiUtils() {}

    public static final String BASE_URL = "https://apex.oracle.com/";
    public static APIService getApiService(){
        return RetrofitClient.getCliente(BASE_URL).create(APIService.class);
    }
}
