package com.example.thespa.view.services;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.domain.entities.Service;
import com.example.domain.entities.ServiceCategory;
import com.example.thespa.R;

import java.util.ArrayList;

public class ServicesCategoriesAdapter extends RecyclerView.Adapter<ServicesCategoriesAdapter.ViewHolder>  {

    private LayoutInflater mInflater;
    private ArrayList<ServiceCategory> categories;
    private Context context;

    public ServicesCategoriesAdapter(Context context, ArrayList<ServiceCategory> categories) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.categories = categories;

    }
    /**
     *inflates the cell layout from xml when needed
     */
    @Override
    @NonNull
    public ServicesCategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_service_category, parent, false);
        return new ServicesCategoriesAdapter.ViewHolder(view);
    }

    /**
     *binds the data to the view in each cell
     */
    @Override
    public void onBindViewHolder(@NonNull ServicesCategoriesAdapter.ViewHolder holder, int position) {
        ServiceCategory serviceCategory = categories.get(position);
        holder.name.setText(serviceCategory.getName());
        String image ="https://appthespa.com/"+serviceCategory.getImage();
        Glide.with(context)
                .load(image)
                .into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, ServicesActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("category_id",serviceCategory.getId());
            context.startActivity(i);
        });

    }
    /**
     *total number of cells
     */
    @Override
    public int getItemCount() {
        return categories.size();
    }

    /**
     *stores and recycles views as they are scrolled off screen
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;


        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);


        }
    }
}
