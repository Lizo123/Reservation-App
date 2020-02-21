package com.example.thespa.view.beauticians;

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
import com.bumptech.glide.request.RequestOptions;
import com.example.domain.entities.Beautician;
import com.example.domain.entities.Offer;
import com.example.thespa.R;
import com.example.thespa.view.offers.OffersAdapter;
import com.example.thespa.view.offers.offers_details.OfferDetailsActivity;

import java.util.ArrayList;

public class BeauticiansAdapter extends RecyclerView.Adapter<BeauticiansAdapter.ViewHolder>  {

    private LayoutInflater mInflater;
    private ArrayList<Beautician> beauticians;
    private Context context;

    public BeauticiansAdapter(Context context, ArrayList<Beautician> beauticians) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.beauticians = beauticians;

    }
    /**
     *inflates the cell layout from xml when needed
     */
    @Override
    @NonNull
    public BeauticiansAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_beauticians, parent, false);
        return new BeauticiansAdapter.ViewHolder(view);
    }

    /**
     *binds the data to the view in each cell
     */
    @Override
    public void onBindViewHolder(@NonNull BeauticiansAdapter.ViewHolder holder, int position) {
      //  TODO image and rate

        Beautician beautician = beauticians.get(position);
      holder.name.setText(beautician.getName());

      holder.job.setText(beautician.getJobTitle());
      Glide.with(context)
              .load(beautician.getImage())
              .apply(RequestOptions.circleCropTransform())
               .into(holder.image);
        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, BeauticiansDetails.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("beautician",beautician);
            context.startActivity(i);
        });

    }
    /**
     *total number of cells
     */
    @Override
    public int getItemCount() {
        return beauticians.size();
    }

    /**
     *stores and recycles views as they are scrolled off screen
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView job;
        TextView rate;

        ViewHolder(View itemView) {
            super(itemView);
           name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
            job = itemView.findViewById(R.id.job);
            rate = itemView.findViewById(R.id.rate);

        }
    }
}
