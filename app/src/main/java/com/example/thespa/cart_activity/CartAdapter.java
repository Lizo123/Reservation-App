package com.example.thespa.cart_activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thespa.Offers.OffersAdapter;
import com.example.thespa.R;
import com.example.thespa.models.Cart;
import com.example.thespa.models.Offer;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<Cart> cartModels;
    Context context;

    CartAdapter(Context context, ArrayList<Cart> cartModels) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.cartModels = cartModels;

    }
    /**
     *inflates the cell layout from xml when needed
     */
    @Override
    @NonNull
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_cart, parent, false);
        return new CartAdapter.ViewHolder(view);
    }

    /**
     *binds the data to the view in each cell
     */
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        holder.time.setText(cartModels.get(position).getTime());
        holder.amount.setText(cartModels.get(position).getAmount());
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
        return cartModels.size();
    }

    /**
     *stores and recycles views as they are scrolled off screen
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView amount;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            amount = itemView.findViewById(R.id.amount);
            image = itemView.findViewById(R.id.image);

        }
    }
}
