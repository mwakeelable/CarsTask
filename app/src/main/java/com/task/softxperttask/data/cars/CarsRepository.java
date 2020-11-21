package com.task.softxperttask.data.cars;

import com.task.softxperttask.data.cars.remote.CarsRemoteDataSource;

public class CarsRepository implements CarsDataSource {
    CarsDataSource carsRemoteDataSource;
    private static CarsRepository INSTANCE;

    private CarsRepository(CarsDataSource carsRemoteDataSource) {
        this.carsRemoteDataSource = carsRemoteDataSource;
    }

    public static CarsRepository getInstance(CarsRemoteDataSource carsDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new CarsRepository(carsDataSource);
        } else {
            return INSTANCE;
        }
        return INSTANCE;
    }

    @Override
    public void fetchCarsData(int page, FetchCarsData callBack) {
        carsRemoteDataSource.fetchCarsData(page, callBack);
    }
}
