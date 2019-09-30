package com.example.thespa.Offers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thespa.R;
import com.example.thespa.models.Offer;

import java.util.ArrayList;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder>  {

    private LayoutInflater mInflater;
    private ArrayList<Offer> offerModels;
    Context context;

    OffersAdapter(Context context, ArrayList<Offer> offerModels) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.offerModels = offerModels;

    }
    /**
     *inflates the cell layout from xml when needed
     */
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_offer, parent, false);
        return new ViewHolder(view);
    }

    /**
     *binds the data to the view in each cell
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Offer_name.setText(offerModels.get(position).getOfferName());
//        Glide.with(context)
//                .load(offerModels.get(position).getOfferImage())
//                .into(holder.offer_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(context, ProductDetailsActivity.class);
//                i.putExtra("Source","Change_product");
//                i.putExtra("Products", offerModels.get(position).getProducts());
//
//                i.putExtra("ProductName",offerModels.get(position).getName());
//
//                context.startActivity(i);
            }
        });

    }
    /**
     *total number of cells
     */
    @Override
    public int getItemCount() {
        return offerModels.size();
    }

    /**
     *stores and recycles views as they are scrolled off screen
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Offer_name;
        ImageView offer_image;

        ViewHolder(View itemView) {
            super(itemView);
            Offer_name = itemView.findViewById(R.id.Offer_name);
            offer_image = itemView.findViewById(R.id.offer_image);

        }
    }
}
