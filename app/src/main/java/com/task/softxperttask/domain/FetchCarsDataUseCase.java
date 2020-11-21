package com.task.softxperttask.domain;

import com.task.softxperttask.data.cars.CarsDataSource;
import com.task.softxperttask.data.cars.CarsRepository;
import com.task.softxperttask.data.cars.models.CarsResponse;
import com.task.softxperttask.domain.usecase.UseCase;

public class FetchCarsDataUseCase extends UseCase<FetchCarsDataUseCase.CarsDataRequestValues,
        FetchCarsDataUseCase.CarsDataResponseValue> {
    CarsRepository mCarsRepository;

    public FetchCarsDataUseCase(CarsRepository mCarsRepository) {
        this.mCarsRepository = mCarsRepository;
    }

    @Override
    protected void executeUseCase(CarsDataRequestValues requestValues) {
        mCarsRepository.fetchCarsData(requestValues.page, new CarsDataSource.FetchCarsData() {
            @Override
            public void fetchCarsDataSuccess(CarsResponse model) {
                CarsDataResponseValue carsDataResponseValue = new CarsDataResponseValue(model);
                getUseCaseCallback().onSuccess(carsDataResponseValue);
            }

            @Override
            public void fetchCarsDataFailed(String errMsg) {
                CarsDataResponseValue carsDataResponseValue = new CarsDataResponseValue(errMsg);
                getUseCaseCallback().onError(carsDataResponseValue);
            }
        });
    }

    public static final class CarsDataRequestValues implements UseCase.RequestValues {

        int page;

        public CarsDataRequestValues(int page) {
            this.page = page;
        }
    }

    public static final class CarsDataResponseValue implements UseCase.ResponseValue {
        CarsResponse model;
        String error;
        public CarsDataResponseValue(CarsResponse model) {
            this.model = model;
        }

        public CarsDataResponseValue(String error){
            this.error = error;
        }

        public CarsResponse getResponseModel() {
            return model;
        }

        public String getErrorMsg(){
            return error;
        }
    }
}
