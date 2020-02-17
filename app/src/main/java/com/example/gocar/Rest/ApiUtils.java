package com.example.gocar.Rest;

import com.example.gocar.Classes.DemoClass;

public class ApiUtils {
    private ApiUtils() {}

    private static final String ROOT_URL = "http://"+DemoClass.IP+":9001/api/v1/";

//    private static final String ROOT_URL = "http://72.255.61.208:9001/api/v1/";
//private static final String ROOT_URL = "http://192.168.10.22:9001/api/v1/";

    public static ApiInterface getAPIService() {

        return RetrofitClient.getClient(ROOT_URL).create(ApiInterface.class);
    }
}
