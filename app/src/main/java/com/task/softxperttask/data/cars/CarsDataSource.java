package com.task.softxperttask.data.cars;

import com.task.softxperttask.data.cars.models.CarsResponse;

public interface CarsDataSource {
    interface FetchCarsData {
        void fetchCarsDataSuccess(CarsResponse model);

        void fetchCarsDataFailed(String errMsg);
    }

    void fetchCarsData(int page, FetchCarsData callBack);
}
