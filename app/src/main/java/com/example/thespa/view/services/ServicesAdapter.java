package com.example.thespa.view.services;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.domain.entities.Service;
import com.example.domain.request_entities.WithId;
import com.example.domain.usecases.DefaultObserver;
import com.example.domain.usecases.favorite_usecases.AddFavoriteUseCase;
import com.example.domain.usecases.favorite_usecases.RemoveFavoriteUseCase;
import com.example.thespa.R;

import java.util.ArrayList;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder>  {

private LayoutInflater mInflater;
private ArrayList<Service> services;
private Context context;
private AddFavoriteUseCase addFavoriteUseCase;
private RemoveFavoriteUseCase removeFavoriteUseCase;
Boolean isSigned;


public ServicesAdapter(Context context, ArrayList<Service> services,AddFavoriteUseCase addFavoriteUseCase,RemoveFavoriteUseCase removeFavoriteUseCase,Boolean isSigned) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.services = services;
        this.addFavoriteUseCase = addFavoriteUseCase;
        this.removeFavoriteUseCase = removeFavoriteUseCase;
        this.isSigned = isSigned;

        }
/**
 *inflates the cell layout from xml when needed
 */
@Override
@NonNull
public ServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_service, parent, false);
        return new ServicesAdapter.ViewHolder(view);
        }

/**
 *binds the data to the view in each cell
 */
@Override
public void onBindViewHolder(@NonNull ServicesAdapter.ViewHolder holder, int position) {
        Service service = services.get(position);
        holder.title.setText(service.getName());
    String url = "https://appthespa.com/"+service.getImage();
    Glide.with(context)
            .load(url)
            .into(holder.image);
    if(service.getIsFavorite().equals("1")) {

            holder.favorite.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_2));
            holder.favorite.setTag(R.drawable.ic_favorite_2);

    }
    else
    {
        holder.favorite.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_not_favorite));

        holder.favorite.setTag(R.drawable.ic_not_favorite);

    }

    holder.time.setText(service.getDuration());
    holder.amount.setText(service.getPrice());
    holder.favorite.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           if(holder.favorite.getDrawable().getConstantState() == context.getResources().getDrawable( R.drawable.ic_not_favorite).getConstantState())
           {
               if(isSigned) {
               holder.favorite.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_2));
               addFavoriteUseCase.execute(new AddFavoriteObserver(),new WithId("",service.getId()));
               }
               else
               {
                   Toast.makeText(context, context.getResources().getString(R.string.not_signed), Toast.LENGTH_SHORT).show();
               }
           }
           else
           {
               holder.favorite.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_not_favorite));
               removeFavoriteUseCase.execute(new RemoveFavoriteObserver(),new WithId("",service.getId()));
           }

        }
    });
    holder.cart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO add to cart
        }
    });

        holder.itemView.setOnClickListener(v -> {
        Intent i = new Intent(context, ServiceDetailsActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("service",service);
        context.startActivity(i);
        });

        }
/**
 *total number of cells
 */
@Override
public int getItemCount() {
        return services.size();
        }

/**
 *stores and recycles views as they are scrolled off screen
 */
class ViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView image;
    ImageView favorite;
    TextView time;
    TextView amount;
    ImageView cart;



    ViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        image = itemView.findViewById(R.id.image);
        favorite= itemView.findViewById(R.id.favorite);
        time = itemView.findViewById(R.id.time);
        amount = itemView.findViewById(R.id.amount);
        cart = itemView.findViewById(R.id.cart);


    }
}
    private final class AddFavoriteObserver extends DefaultObserver<String> {
        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String result) {


        }
    }
    private final class RemoveFavoriteObserver extends DefaultObserver<String> {
        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String result) {

        }
    }
}