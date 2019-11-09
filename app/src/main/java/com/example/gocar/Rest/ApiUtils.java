package com.example.gocar.Rest;

public class ApiUtils {
    private ApiUtils() {}

    private static final String ROOT_URL = "http://192.168.10.17:9000/api/v1/";

    public static ApiInterface getAPIService() {

        return RetrofitClient.getClient(ROOT_URL).create(ApiInterface.class);
    }
}
