package com.task.softxperttask.presentation.cars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.task.softxperttask.R;
import com.task.softxperttask.data.cars.models.CarsData;

import java.util.ArrayList;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarsViewHolder> {
    private ArrayList<CarsData> data;
    private Context context;

    class CarsViewHolder extends RecyclerView.ViewHolder {
        TextView brand, isNew, constYear;
        ImageView carImg;

        CarsViewHolder(View view) {
            super(view);
            carImg = view.findViewById(R.id.car_img);
            brand = view.findViewById(R.id.brand_txt);
            isNew = view.findViewById(R.id.new_txt);
            constYear = view.findViewById(R.id.year_txt);
        }
    }

    public CarsAdapter(ArrayList<CarsData> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public CarsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_item, parent, false);
        return new CarsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CarsViewHolder holder, final int position) {
        CarsData car = data.get(position);
        holder.brand.setText(car.getBrand());
        if (car.getConstractionYear() != null)
            holder.constYear.setText(car.getConstractionYear());

        if (car.getIsUsed()) {
            holder.isNew.setText("Used");
        } else {
            holder.isNew.setText("New");
        }
        Glide.with(context).load(car.getImageUrl()).into(holder.carImg);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
