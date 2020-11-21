package com.task.softxperttask.data;

import com.task.softxperttask.data.cars.models.CarsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServices {
    @GET("api/v1/cars")
    Call<CarsResponse> fetchCarsData(@Query("page") int page);
}
