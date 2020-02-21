package com.example.thespa.view.offers;

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
import com.example.domain.entities.Offer;
import com.example.thespa.R;
import com.example.thespa.view.offers.offers_details.OfferDetailsActivity;

import java.util.ArrayList;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder>  {

    private LayoutInflater mInflater;
    private ArrayList<Offer> offerModels;
    private Context context;

    public OffersAdapter(Context context, ArrayList<Offer> offerModels) {
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
        Offer offer = offerModels.get(position);
        holder.Offer_name.setText(offer.getName());
//        String url = "https://appthespa.com/"+offer.getImage();
//        Glide.with(context)
//                .load(url)
//                .into(holder.image);
        if(offer.getDiscountType().equals("percent)"))
           holder.offer_discount.setText(offer.getDiscount()+context.getResources().getString(R.string.percent_off));
        else
            holder.offer_discount.setText(offer.getDiscount()+context.getResources().getString(R.string.money_off));

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, OfferDetailsActivity.class);
            context.startActivity(i);
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
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Offer_name;
        ImageView offer_image;
        TextView offer_discount;

        ViewHolder(View itemView) {
            super(itemView);
            Offer_name = itemView.findViewById(R.id.Offer_name);
            offer_image = itemView.findViewById(R.id.offer_image);
            offer_discount = itemView.findViewById(R.id.offer_discount);

        }
    }
}
