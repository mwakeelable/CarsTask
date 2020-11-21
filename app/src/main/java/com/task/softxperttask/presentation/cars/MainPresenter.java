package com.task.softxperttask.presentation.cars;

import com.task.softxperttask.data.cars.models.CarsResponse;
import com.task.softxperttask.domain.FetchCarsDataUseCase;
import com.task.softxperttask.domain.usecase.UseCase;
import com.task.softxperttask.domain.usecase.UseCaseHandler;

class MainPresenter implements MainContract.CarsPresenter {
    private UseCaseHandler useCaseHandler;
    private MainContract.CarsView carsView;
    private FetchCarsDataUseCase fetchCarsDataUseCase;

    public MainPresenter(UseCaseHandler useCaseHandler, MainContract.CarsView carsView, FetchCarsDataUseCase fetchCarsDataUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.carsView = carsView;
        this.fetchCarsDataUseCase = fetchCarsDataUseCase;
        carsView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void fetchCarsData(int page) {
        if (carsView.isActive()) {
            carsView.setLoadingIndicator(true);
        }
        FetchCarsDataUseCase.CarsDataRequestValues requestValues = new FetchCarsDataUseCase.CarsDataRequestValues(page);
        useCaseHandler.execute(fetchCarsDataUseCase, requestValues, new UseCase.UseCaseCallback<FetchCarsDataUseCase.CarsDataResponseValue>() {
            @Override
            public void onSuccess(FetchCarsDataUseCase.CarsDataResponseValue response) {
                carsView.setLoadingIndicator(false);
                CarsResponse responseModel = response.getResponseModel();
                carsView.getCarsDataSuccess(responseModel);
            }

            @Override
            public void onError(FetchCarsDataUseCase.CarsDataResponseValue response) {
                carsView.showErrorMessage(response.getErrorMsg());
                carsView.setLoadingIndicator(false);
            }
        });
    }
}
