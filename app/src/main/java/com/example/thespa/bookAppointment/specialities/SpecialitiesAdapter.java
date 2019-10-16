package com.example.thespa.bookAppointment.specialities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thespa.R;
import com.example.thespa.models.Beautician;

import java.util.ArrayList;

public class SpecialitiesAdapter extends RecyclerView.Adapter<SpecialitiesAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<Beautician> beauticians;
    private Context context;


    SpecialitiesAdapter(Context context, ArrayList<Beautician> beauticians) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.beauticians = beauticians;


    }

    /**
     * inflates the cell layout from xml when needed
     */
    @Override
    @NonNull
    public SpecialitiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_beauticians, parent, false);
        return new SpecialitiesAdapter.ViewHolder(view);
    }

    /**
     * binds the data to the view in each cell
     */
    @Override
    public void onBindViewHolder(@NonNull final SpecialitiesAdapter.ViewHolder holder, final int position) {
        holder.name.setText(beauticians.get(position).getName());


    }

    /**
     * total number of cells
     */
    @Override
    public int getItemCount() {
        return beauticians.size();
    }

    /**
     * stores and recycles views as they are scrolled off screen
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);


        }
    }
}
