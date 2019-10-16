package com.example.thespa.bookAppointment;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.thespa.R;
import com.example.thespa.models.Contact;

import java.util.ArrayList;

public class ContactsChoosenAdapter extends RecyclerView.Adapter<ContactsChoosenAdapter.ViewHolder>{

    private LayoutInflater mInflater;
    private ArrayList<String> contacts;
    Context context;


    ContactsChoosenAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.contacts = new ArrayList<>();


    }
    /**
     *inflates the cell layout from xml when needed
     */
    @Override
    @NonNull
    public ContactsChoosenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_choosen_friend, parent, false);
        return new ContactsChoosenAdapter.ViewHolder(view);
    }

    /**
     *binds the data to the view in each cell
     */
    @Override
    public void onBindViewHolder(@NonNull final ContactsChoosenAdapter.ViewHolder holder, final int position) {
        holder.name.setText(contacts.get(position));


//        Glide.with(context)
//                .load(offerModels.get(position).getOfferImage())
//                .into(holder.offer_image);

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });

    }
    /**
     *total number of cells
     */
    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void removeItem(int index)
    {
        contacts.remove(index);
        notifyDataSetChanged();
    }

    public void addItem(String contact)
    {
        contacts.add(contact);
        notifyDataSetChanged();
    }

    /**
     *stores and recycles views as they are scrolled off screen
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView remove;



        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            remove = itemView.findViewById(R.id.remove);

        }
    }
}
