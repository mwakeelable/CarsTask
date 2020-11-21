package com.task.softxperttask.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static WebServices webServices;
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        if(retrofit==null)
            retrofit = new Retrofit.
                    Builder().
                    baseUrl("http://demo1585915.mockable.io/").
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
        else{
            return retrofit;
        }
        return retrofit;
    }

    public static WebServices getWebServices(){
        if(webServices == null)
            webServices = getRetrofit().create(WebServices.class);
        else{
            return webServices;
        }
        return webServices;
    }
}
