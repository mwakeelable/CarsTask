package com.task.softxperttask.utils;

import com.task.softxperttask.data.cars.CarsRepository;
import com.task.softxperttask.data.cars.remote.CarsRemoteDataSource;
import com.task.softxperttask.domain.FetchCarsDataUseCase;
import com.task.softxperttask.domain.usecase.UseCaseHandler;

public class FactoryInjection {
    public static UseCaseHandler provideUseCaseHandler() {

        return UseCaseHandler.getInstance();
    }

    public static CarsRemoteDataSource injectCarsRemoteDataSource() {
        return CarsRemoteDataSource.getInstance();
    }

    public static CarsRepository injectCarsRepository() {
        return CarsRepository.getInstance(injectCarsRemoteDataSource());
    }

    public static FetchCarsDataUseCase injectFetchCarsDataUseCase() {
        return new FetchCarsDataUseCase(injectCarsRepository());
    }
}
