package com.task.softxperttask.presentation.cars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.task.softxperttask.R;
import com.task.softxperttask.data.cars.models.CarsData;
import com.task.softxperttask.data.cars.models.CarsResponse;
import com.task.softxperttask.presentation.base.BasePresenter;
import com.task.softxperttask.utils.FactoryInjection;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.CarsView, SwipeRefreshLayout.OnRefreshListener {
    MainPresenter mainPresenter;
    ProgressBar progressBar;
    RecyclerView carsList;
    SwipeRefreshLayout swipeRefreshLayout;
    CarsAdapter carsAdapter;
    ArrayList<CarsData> data;
    private boolean loadMore = true;
    int page = 1;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        doInjection();
    }

    private void initViews() {
        progressBar = findViewById(R.id.progressn);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        mainPresenter.fetchCarsData(page);
                    }
                }
        );
        data = new ArrayList<>();
        carsList = findViewById(R.id.cars_list);
        carsAdapter = new CarsAdapter(data, this);
        LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        carsList.setLayoutManager(mLayoutManager);
        carsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mLayoutManager.findLastCompletelyVisibleItemPosition() == data.size() - 1) {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                    if (loadMore) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            page += 1;
                            mainPresenter.fetchCarsData(page);
                        }
                    }
                }
            }
        });
        carsList.setAdapter(carsAdapter);
    }

    public void doInjection() {
        mainPresenter = new MainPresenter
                (FactoryInjection.provideUseCaseHandler(), this, FactoryInjection.injectFetchCarsDataUseCase());
    }

    @Override
    public void getCarsDataSuccess(CarsResponse resp) {
        if (resp.getData() != null) {
            loadMore = true;
            data.addAll(resp.getData());
        } else {
            loadMore = false;
        }
        carsAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showErrorMessage(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setLoadingIndicator(boolean showIndicator) {
        if (showIndicator) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        presenter.start();
    }

    @Override
    public void onRefresh() {
        data.clear();
        page = 1;
        mainPresenter.fetchCarsData(page);
    }
}