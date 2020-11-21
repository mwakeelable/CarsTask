package com.task.softxperttask.data.cars.remote;

import com.task.softxperttask.data.APIClient;
import com.task.softxperttask.data.cars.CarsDataSource;
import com.task.softxperttask.data.cars.models.CarsResponse;

import java.io.IOException;

import retrofit2.Response;

public class CarsRemoteDataSource implements CarsDataSource {
    private static CarsRemoteDataSource instance;

    public static CarsRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new CarsRemoteDataSource();
        } else {
            return instance;
        }
        return instance;

    }

    @Override
    public void fetchCarsData(int page, FetchCarsData callBack) {
        try{
            Response<CarsResponse> response = APIClient.getWebServices().fetchCarsData(page).execute();
            if (response.isSuccessful()){
                CarsResponse carsResponse = response.body();
                callBack.fetchCarsDataSuccess(carsResponse);
            }else {
                callBack.fetchCarsDataFailed("Something went wrong!");
            }
        }catch (IOException e) {
            callBack.fetchCarsDataFailed("Something went wrong!");
            e.printStackTrace();
        }
    }
}
