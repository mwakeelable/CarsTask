package com.task.softxperttask.presentation.cars;

import com.task.softxperttask.data.cars.models.CarsResponse;
import com.task.softxperttask.presentation.base.BasePresenter;
import com.task.softxperttask.presentation.base.BaseView;

public interface MainContract {
    interface CarsPresenter extends BasePresenter {
        void fetchCarsData(int page);
    }

    interface CarsView extends BaseView {
        void getCarsDataSuccess(CarsResponse resp);

        void showErrorMessage(String msg);

        boolean isActive();

        void setLoadingIndicator(boolean b);
    }
}
