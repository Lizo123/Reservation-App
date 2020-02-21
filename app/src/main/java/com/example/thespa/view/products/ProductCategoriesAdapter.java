package com.example.thespa.view.products;

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
import com.example.domain.entities.ProductCategory;
import com.example.thespa.R;

import java.util.ArrayList;

public class ProductCategoriesAdapter extends RecyclerView.Adapter<ProductCategoriesAdapter.ViewHolder>  {

    private LayoutInflater mInflater;
    private ArrayList<ProductCategory> productCategories;
    private Context context;
    String source;

    public ProductCategoriesAdapter(Context context, ArrayList<ProductCategory> productCategories, String source) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.productCategories = productCategories;
        this.source = source;

    }
    /**
     *inflates the cell layout from xml when needed
     */
    @Override
    @NonNull
    public ProductCategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_product, parent, false);
        return new ProductCategoriesAdapter.ViewHolder(view);
    }

    /**
     *binds the data to the view in each cell
     */
    @Override
    public void onBindViewHolder(@NonNull ProductCategoriesAdapter.ViewHolder holder, int position) {
        ProductCategory productCategory = productCategories.get(position);
        if(source.equals("home"))
        {
            holder.title.setVisibility(View.GONE);
            holder.description.setVisibility(View.GONE);
        }
        holder.title.setText(productCategory.getName());
        holder.description.setText(productCategory.getDescription());
        String url = "https://appthespa.com/"+productCategory.getImage();
        Glide.with(context)
                .load(url)
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProductsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("category_id",productCategory.getId());
                context.startActivity(i);
            }
        });

    }
    /**
     *total number of cells
     */
    @Override
    public int getItemCount() {
        return productCategories.size();
    }

    /**
     *stores and recycles views as they are scrolled off screen
     */
    class ViewHolder extends RecyclerView.ViewHolder {
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
