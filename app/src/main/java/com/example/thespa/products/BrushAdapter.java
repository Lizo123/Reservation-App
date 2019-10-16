package com.example.thespa.products;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thespa.R;
import com.example.thespa.models.Brush;

import java.util.ArrayList;

public class BrushAdapter extends RecyclerView.Adapter<BrushAdapter.ViewHolder>  {

    private LayoutInflater mInflater;
    private ArrayList<Brush> products;
    Context context;

    BrushAdapter(Context context, ArrayList<Brush> products) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.products = products;

    }
    /**
     *inflates the cell layout from xml when needed
     */
    @Override
    @NonNull
    public BrushAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_product, parent, false);
        return new BrushAdapter.ViewHolder(view);
    }

    /**
     *binds the data to the view in each cell
     */
    @Override
    public void onBindViewHolder(@NonNull BrushAdapter.ViewHolder holder, int position) {
        holder.title.setText(products.get(position).getTitle());
        holder.description.setText(products.get(position).getDescription());
//        Glide.with(context)
//                .load(offerModels.get(position).getOfferImage())
//                .into(holder.offer_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProductDetailsActivity.class);
                context.startActivity(i);
            }
        });

    }
    /**
     *total number of cells
     */
    @Override
    public int getItemCount() {
        return products.size();
    }

    /**
     *stores and recycles views as they are scrolled off screen
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);

        }
    }
}
